package hello;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestEvidenceWordReport {

    private static XWPFDocument document;
    private static FileOutputStream out;
    private static String fileName;

    // Start report
    public static void startReport(String testName) throws IOException {

        document = new XWPFDocument();

        fileName = "TestEvidence_" + testName + ".docx";
        out = new FileOutputStream(fileName);

        XWPFParagraph title = document.createParagraph();
        XWPFRun run = title.createRun();

        run.setBold(true);
        run.setFontSize(18);
        run.setText("Muhali Holdings Test Evidence Report");
        run.addBreak();

        run.setFontSize(14);
        run.setText("Test Case: " + testName);
        run.addBreak();
        run.addBreak();
    }

    // Add step text
    public static void addStep(String step) {

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setBold(true);
        run.setText("STEP: " + step);
        run.addBreak();
    }

    // Add screenshot (IMAGE inside Word)
    public static void addScreenshot(String imagePath) throws IOException {

        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        FileInputStream fis = new FileInputStream(imagePath);

        run.addPicture(
                fis,
                XWPFDocument.PICTURE_TYPE_PNG,
                imagePath,
                Units.toEMU(450),  
                Units.toEMU(250)  
        );

        fis.close();

        run.addBreak();
    }

    // End report
    public static void endReport() throws IOException {

        document.write(out);
        out.close();
        document.close();
    }
}