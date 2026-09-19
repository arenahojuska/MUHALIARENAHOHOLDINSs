package ONboarding;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Generates TEST / DUMMY South African ID numbers using the public generator at
 * axonwireless.com. This is for testing OnboardingFiller's logic only - the IDs
 * produced here are NOT real people and must never be used for real member
 * submissions on an actual onboarding sheet.
 *
 * This class is normally called directly by OnboardingFiller's test mode, but it
 * can still be run on its own (via main) for a quick standalone check.
 */
public class SAIDGenerator {

    /**
     * Generates the given number of test SA ID numbers.
     *
     * @param count  how many ID numbers to generate
     * @param age    age in years used by the generator site (e.g. 30)
     * @param month  birth month (1-12) used by the generator site
     * @param day    birth day (1-31) used by the generator site
     * @param gender "Male" or "Female" - must match one of the site's <option value="..."> entries
     * @return a list of generated 13-digit ID numbers, as text (so leading zeros are preserved)
     */
    public static List<String> generateIds(int count, int age, int month, int day, String gender) {
        List<String> validIds = new ArrayList<>();
        final int MAX_ATTEMPTS = 10;
        int attempts = 0;

        while (validIds.size() < count) {
            if (++attempts > MAX_ATTEMPTS) {
                throw new IllegalStateException(
                        "Could not get " + count + " valid IDs (not ending in 0) after "
                        + MAX_ATTEMPTS + " attempts. Check the generator site.");
            }

            int needed = count - validIds.size();
            System.out.println("Generating " + needed + " ID(s) (attempt " + attempts + ")...");

            List<String> batch = generateBatch(needed, age, month, day, gender);

            for (String id : batch) {
                if (!id.endsWith("0")) {
                    validIds.add(id);
                } else {
                    System.out.println("  Skipping " + id + " (ends with 0, regenerating)");
                }
            }
        }

        return validIds.subList(0, count);
    }

    /** Single call to the generator site returning exactly `count` raw IDs (may include ones ending in 0). */
    private static List<String> generateBatch(int count, int age, int month, int day, String gender) {
        List<String> generatedIds = new ArrayList<>();

        ChromeOptions options = new ChromeOptions();
        // Uncomment the next line to run Chrome without opening a visible window:
        // options.addArguments("--headless=new");

        WebDriver driver = new ChromeDriver(options);
        try {
            driver.manage().window().maximize();
            driver.get("https://www.axonwireless.com/toolbox/sa-id-number-generator/");
            sleep(3000);

            WebElement ageField = driver.findElement(By.id("said_age"));
            ageField.clear();
            ageField.sendKeys(String.valueOf(age));

            WebElement monthField = driver.findElement(By.id("said_month"));
            monthField.clear();
            monthField.sendKeys(String.valueOf(month));

            WebElement dayField = driver.findElement(By.id("said_day"));
            dayField.clear();
            dayField.sendKeys(String.valueOf(day));

            WebElement countField = driver.findElement(By.id("said_count"));
            countField.clear();
            countField.sendKeys(String.valueOf(count));

            driver.findElement(By.xpath("//option[@value='" + gender + "']")).click();

            WebElement generateButton = driver.findElement(By.id("said_generate_object"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", generateButton);
            sleep(1000);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", generateButton);
            sleep(3000);

            List<WebElement> idCells = driver.findElements(
                    By.xpath("//*[@id='said_generated_results']/table/tbody/tr/td[1]"));

            for (WebElement cell : idCells) {
                String id = cell.getText().trim();
                if (!id.isEmpty()) {
                    generatedIds.add(id);
                }
            }
        } finally {
            driver.quit();
        }

        if (generatedIds.size() < count) {
            throw new IllegalStateException("Expected " + count + " generated IDs but only got "
                    + generatedIds.size() + ". The generator site may have changed or been slow to load.");
        }

        return generatedIds.subList(0, count);
    }

    private static void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /** Standalone quick check: generates 8 test IDs (age 30, Jan 1, Male) and prints them. */
    public static void main(String[] args) {
        List<String> ids = generateIds(8, 30, 1, 1, "Male");
        System.out.println("========== GENERATED TEST SA IDS (NOT REAL PEOPLE) ==========");
        for (String id : ids) {
            System.out.println(id);
        }
        System.out.println("===============================================================");
    }
}