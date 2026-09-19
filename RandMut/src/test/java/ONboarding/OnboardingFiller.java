package ONboarding;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

/**
 * Fills in a "member onboarding" Excel sheet (works with multiple template layouts,
 * e.g. "My Value Plus" and "IE Administrators" onboarding sheets):
 *  - Gets the path to the Excel file from RMA.TestData.getOnboardingsheet() (instead
 *    of asking the user to type/paste it)
 *  - Automatically finds the right sheet and the right columns by reading the header row
 *    (it looks for header text like "Member Type", "Main Member Link ID", "ID Number",
 *    "Date Of Birth" - so it adapts even if the column letters differ between templates)
 *  - Counts how many member rows are present (based on the "Member Type" column)
 *  - Generates that many TEST South African ID numbers via SAIDGenerator (NOT real
 *    member data - see warning below)
 *  - Writes each ID number into the "ID Number" column
 *  - Writes the FIRST id number generated into "Main Member Link ID" for every member
 *  - Derives each member's Date Of Birth from their own ID number and writes it into
 *    the "Date Of Birth" column as a real date (e.g. 1990-07-21)
 *  - Saves the changes back into the SAME file (in place)
 *
 * IMPORTANT: this version generates FAKE/TEST ID numbers via SAIDGenerator, for testing
 * the spreadsheet-filling logic only. It does NOT ask for or use real members' ID numbers.
 * Do not use the output of this test mode as a real onboarding submission.
 *
 * This class is in the default (no) package and reads RMA.TestData, which is in the
 * "RMA" package - so it must be compiled/run from a project root where the RMA
 * package folder is reachable on the classpath. See README.md for details.
 *
 * Usage:
 *   java -cp ".;lib/*" OnboardingFiller        (Windows)
 *   java -cp ".:lib/*" OnboardingFiller        (Mac/Linux)
 */
public class OnboardingFiller {

    // Header text to look for in the header row. If a template uses slightly different
    // wording, add an alternative here rather than changing the rest of the program.
    private static final String[] HEADER_MEMBER_TYPE = { "Member Type", "Client Type" };
    private static final String[] HEADER_MAIN_MEMBER_LINK_ID = { "Main Member Link ID" };
    private static final String[] HEADER_ID_NUMBER = { "ID Number" };
    private static final String[] HEADER_DATE_OF_BIRTH = { "Date Of Birth", "Date of Birth" };

    // Candidate sheet names to try, in order, across known templates.
    private static final String[] CANDIDATE_SHEET_NAMES = { "Onboarding", "Members" };

    // How many of the top rows to scan when looking for the header row.
    private static final int MAX_HEADER_SCAN_ROWS = 10;

    // Default parameters passed to SAIDGenerator for test ID generation.
    // Change these if you want different test ages / birth dates / gender.
    private static final int TEST_ID_AGE = 32;
    private static final int TEST_ID_MONTH = 4;
    private static final int TEST_ID_DAY = 18;
    private static final String TEST_ID_GENDER = "Male";

    public static void main(String[] args) {
        System.out.println("NOTE: this run generates TEST/DUMMY ID numbers for trying out the");
        System.out.println("spreadsheet-filling logic. These are NOT real people. Do not use this");
        System.out.println("output as a real onboarding submission.");
        System.out.println();

        String filePath = RMA.TestData.getOnboardingsheet();
        if (filePath == null || filePath.trim().isEmpty()) {
            System.out.println("RMA.TestData.getOnboardingsheet() returned an empty value. "
                    + "Check the 'Onboardingsheet' column in TestData.xlsx.");
            return;
        }
        filePath = filePath.trim();

        File file = new File(filePath);
        if (!file.exists()) {
            System.out.println("Could not find a file at the path from TestData.xlsx: " + filePath);
            return;
        }

        System.out.println("Using onboarding sheet from TestData: " + filePath);

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = findMemberSheet(workbook);
            if (sheet == null) {
                System.out.println("Could not find a member/onboarding sheet in this file "
                        + "(looked for a sheet containing a 'Member Type' column).");
                return;
            }

            HeaderLayout layout = findHeaderLayout(sheet);
            if (layout == null) {
                System.out.println("Could not find the expected headers (Member Type, "
                        + "Main Member Link ID, ID Number, Date Of Birth) on sheet '"
                        + sheet.getSheetName() + "'.");
                return;
            }

            System.out.println("Using sheet '" + sheet.getSheetName() + "', header row "
                    + (layout.headerRowIndex + 1) + ".");

            int memberCount = countMembers(sheet, layout);
            if (memberCount == 0) {
                System.out.println("No members found below the header row. Nothing to do.");
                return;
            }

            System.out.println("Found " + memberCount + " member(s) on the sheet.");
            System.out.println("Generating " + memberCount + " test ID number(s) via SAIDGenerator...");

            List<String> idNumbers = SAIDGenerator.generateIds(
                    memberCount, TEST_ID_AGE, TEST_ID_MONTH, TEST_ID_DAY, TEST_ID_GENDER);
            String mainMemberId = idNumbers.get(0);

            CellStyle dateStyle = createDateStyle(workbook);

            int firstDataRow = layout.headerRowIndex + 1;
            for (int i = 0; i < memberCount; i++) {
                int rowIndex = firstDataRow + i;
                Row row = sheet.getRow(rowIndex);
                if (row == null) {
                    row = sheet.createRow(rowIndex);
                }

                String idNumber = idNumbers.get(i);

                // ID Number column
                setIdCell(row, layout.idNumberCol, idNumber);

                // Main Member Link ID column - always the first id generated
                setIdCell(row, layout.mainMemberLinkIdCol, mainMemberId);

                // Date of Birth column - derived from this member's own id number
                LocalDate dob = dateOfBirthFromSouthAfricanId(idNumber);
                Cell dobCell = row.getCell(layout.dateOfBirthCol);
                if (dobCell == null) {
                    dobCell = row.createCell(layout.dateOfBirthCol);
                }
                dobCell.setCellValue(dob);
                dobCell.setCellStyle(dateStyle);

                System.out.println("Row " + (rowIndex + 1) + " -> ID: " + idNumber + ", DOB: " + dob);
            }

            // Save back into the SAME file (overwrite in place).
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

            System.out.println("Done. Updated file saved to: " + filePath);

        } catch (IOException e) {
            System.out.println("Error reading/writing the Excel file: " + e.getMessage());
        }
    }

    /** Tries known sheet names first, then falls back to scanning every sheet for a Member Type header. */
    private static Sheet findMemberSheet(Workbook workbook) {
        for (String name : CANDIDATE_SHEET_NAMES) {
            Sheet sheet = workbook.getSheet(name);
            if (sheet != null && findHeaderLayout(sheet) != null) {
                return sheet;
            }
        }
        // Fallback: scan every sheet in the workbook for one with the right headers.
        for (Sheet sheet : workbook) {
            if (findHeaderLayout(sheet) != null) {
                return sheet;
            }
        }
        return null;
    }

    /** Holds the discovered header row index and column positions for the fields we care about. */
    private static class HeaderLayout {
        int headerRowIndex;
        int memberTypeCol;
        int mainMemberLinkIdCol;
        int idNumberCol;
        int dateOfBirthCol;
    }

    /** Scans the first few rows of a sheet looking for a row containing all the required headers. */
    private static HeaderLayout findHeaderLayout(Sheet sheet) {
        int maxRow = Math.min(sheet.getLastRowNum(), MAX_HEADER_SCAN_ROWS);
        for (int rowIndex = 0; rowIndex <= maxRow; rowIndex++) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) continue;

            Integer memberTypeCol = findColumnByHeader(row, HEADER_MEMBER_TYPE);
            Integer mainMemberLinkIdCol = findColumnByHeader(row, HEADER_MAIN_MEMBER_LINK_ID);
            Integer idNumberCol = findColumnByHeader(row, HEADER_ID_NUMBER);
            Integer dateOfBirthCol = findColumnByHeader(row, HEADER_DATE_OF_BIRTH);

            if (memberTypeCol != null && mainMemberLinkIdCol != null
                    && idNumberCol != null && dateOfBirthCol != null) {
                HeaderLayout layout = new HeaderLayout();
                layout.headerRowIndex = rowIndex;
                layout.memberTypeCol = memberTypeCol;
                layout.mainMemberLinkIdCol = mainMemberLinkIdCol;
                layout.idNumberCol = idNumberCol;
                layout.dateOfBirthCol = dateOfBirthCol;
                return layout;
            }
        }
        return null;
    }

    /** Searches a row's cells for one whose text matches (case-insensitively) any of the given header options. */
    private static Integer findColumnByHeader(Row row, String[] headerOptions) {
        for (Cell cell : row) {
            if (cell.getCellType() != CellType.STRING) continue;
            String value = cell.getStringCellValue().trim();
            for (String option : headerOptions) {
                if (value.equalsIgnoreCase(option)) {
                    return cell.getColumnIndex();
                }
            }
        }
        return null;
    }

    /** Counts consecutive member rows below the header row, based on the Member Type column being non-empty. */
    private static int countMembers(Sheet sheet, HeaderLayout layout) {
        int count = 0;
        int rowIndex = layout.headerRowIndex + 1;
        while (true) {
            Row row = sheet.getRow(rowIndex);
            if (row == null) break;
            Cell cell = row.getCell(layout.memberTypeCol);
            if (cell == null || cell.getCellType() == CellType.BLANK) break;
            String value = cell.toString().trim();
            if (value.isEmpty()) break;
            count++;
            rowIndex++;
        }
        return count;
    }

    /** Writes an ID number into a cell as text, preserving any leading zeros (e.g. 0501015509081). */
    private static void setIdCell(Row row, int colIndex, String digits) {
        Cell cell = row.getCell(colIndex);
        if (cell == null) {
            cell = row.createCell(colIndex);
        }
        cell.setCellValue(digits);
    }

    /** Builds a yyyy-mm-dd date cell style matching the existing template formatting. */
    private static CellStyle createDateStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        CreationHelper helper = workbook.getCreationHelper();
        style.setDataFormat(helper.createDataFormat().getFormat("yyyy-mm-dd"));
        return style;
    }

    /**
     * Derives a date of birth from a 13-digit South African ID number (format YYMMDDSSSSCAZ).
     * Century rule: if YY is greater than the current year's last two digits, assume 1900s,
     * otherwise assume 2000s.
     */
    private static LocalDate dateOfBirthFromSouthAfricanId(String idNumber) {
        LocalDate dob = dateOfBirthFromSouthAfricanIdSafe(idNumber);
        if (dob == null) {
            throw new IllegalArgumentException("Invalid ID number: " + idNumber);
        }
        return dob;
    }

    /** Same as above but returns null instead of throwing, for validation purposes. */
    private static LocalDate dateOfBirthFromSouthAfricanIdSafe(String idNumber) {
        if (idNumber == null || !idNumber.matches("\\d{13}")) {
            return null;
        }
        try {
            int yy = Integer.parseInt(idNumber.substring(0, 2));
            int mm = Integer.parseInt(idNumber.substring(2, 4));
            int dd = Integer.parseInt(idNumber.substring(4, 6));

            int currentYY = LocalDate.now().getYear() % 100;
            int century = (yy > currentYY) ? 1900 : 2000;
            int year = century + yy;

            return LocalDate.of(year, mm, dd);
        } catch (Exception e) {
            return null;
        }
    }
}
