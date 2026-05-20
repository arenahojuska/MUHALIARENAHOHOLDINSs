package API_Testing;

import org.testng.ITestContext;
import org.testng.annotations.AfterTest;  
import org.testng.annotations.Test;

public class ApiRegressionSuite {

    Post postStep = new Post();
    Get getStep = new Get();
    Delete deleteStep = new Delete();

    @Test(priority = 1)
    public void flow_CreateEmployee() {
        postStep.setup();
        postStep.createNewEmployeeViaPost();
    }

    @Test(priority = 2, dependsOnMethods = "flow_CreateEmployee")
    public void flow_VerifyApiAndDatabaseConsistency() throws Exception {
        getStep.setup();
        getStep.validateEmployeeDataIndependently();
    }

    @Test(priority = 3, dependsOnMethods = "flow_VerifyApiAndDatabaseConsistency")
    public void flow_CleanupEmployee() {
        deleteStep.setup();
        deleteStep.deleteEmployeeViaDelete();
    }

    @AfterTest 
    public void generateMuhaliReport(ITestContext context) {
        System.err.println(">>> AFTERSUITE FIRED RAW");
        try {
            int passed = context.getPassedTests().size();
            int failed = context.getFailedTests().size();
            int skipped = context.getSkippedTests().size();

            System.err.println(">>> Passed=" + passed + " Failed=" + failed + " Skipped=" + skipped);
            WordReportGenerator.generateStatusReport(passed, failed, skipped);

        } catch (Throwable t) {
            System.err.println(">>> CRASH: " + t.getClass().getName());
            System.err.println(">>> MESSAGE: " + t.getMessage());
            t.printStackTrace();
        }
    }
}