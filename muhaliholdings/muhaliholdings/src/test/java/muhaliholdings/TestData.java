package muhaliholdings;

public class TestData {

    private static final String FILE_PATH = "C:\\Users\\ARENAHO JUSKA\\eclipse-workspace\\muhaliholdings\\TestData.xlsx";
    private static final String SHEET_NAME = "Untitled"; 
    private static final int DATA_ROW = 1;

    public static String getBaseUrl() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 0); 
    }

    public static String getAdminEmail() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 1); 
    }

    public static String getAdminPassword() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 2); 
    }

    public static String getExpectedTitle() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 3); 
    }

    public static String getExpectedCartCount() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 4); 
    }
}