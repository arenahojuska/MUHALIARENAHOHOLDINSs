package RMA;

import java.io.File;

public class TestData {

	private static final String FILE_PATH = 
		    System.getProperty("user.dir") 
		    + File.separator + "src" 
		    + File.separator + "test" 
		    + File.separator + "java" 
		    + File.separator + "RMA" 
		    + File.separator + "TestData.xlsx";
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

    public static String getPolicyType() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 4); 
    }
    
    public static String getOnboardingsheet() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 5); 
    }
    public static String getUploadpdf() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 6); 
    }
    public static String getApproveEmail() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 7); 
    }
    public static String getId() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 8); 
    }
    public static String getIdapprover() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 9); 
    }
    public static String getIDRegister() { 
        return Utilities.getExcelData(FILE_PATH, SHEET_NAME, DATA_ROW, 10); 
    }
}