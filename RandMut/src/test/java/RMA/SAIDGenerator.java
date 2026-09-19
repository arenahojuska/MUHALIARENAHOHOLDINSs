package RMA;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Generates TEST / DUMMY South African ID numbers using:
 * https://www.axonwireless.com/toolbox/sa-id-number-generator/
 *
 * These IDs are ONLY for automation testing.
 * They are NOT real people IDs and must not be used for real submissions.
 */
public class SAIDGenerator {


    /**
     * Generates SA test IDs.
     *
     * @param count  number of IDs required
     * @param age    age between 21-50
     * @param month  month between 1-12
     * @param day    day between 1-28
     * @param gender Male/Female
     * @return generated ID list
     */
    public static List<String> generateIds(
            int count,
            int age,
            int month,
            int day,
            String gender
    ) {


        // Validation
        if(age < 21 || age > 50) {
            throw new IllegalArgumentException(
                    "Age must be between 21 and 50"
            );
        }


        if(month < 1 || month > 12) {
            throw new IllegalArgumentException(
                    "Month must be between 1 and 12"
            );
        }


        if(day < 1 || day > 28) {
            throw new IllegalArgumentException(
                    "Day must be between 1 and 28"
            );
        }



        List<String> generatedIds = new ArrayList<>();


        ChromeOptions options = new ChromeOptions();

        // Run browser hidden if required
        // options.addArguments("--headless=new");


        WebDriver driver = new ChromeDriver(options);



        try {


            driver.manage()
                  .window()
                  .maximize();


            driver.get(
                "https://www.axonwireless.com/toolbox/sa-id-number-generator/"
            );


            WebDriverWait wait =
                    new WebDriverWait(
                            driver,
                            Duration.ofSeconds(20)
                    );



            // AGE
            WebElement ageField =
                    wait.until(
                    ExpectedConditions.visibilityOfElementLocated(
                            By.id("said_age")
                    ));


            ageField.clear();
            ageField.sendKeys(
                    String.valueOf(age)
            );



            // MONTH
            WebElement monthField =
                    driver.findElement(
                            By.id("said_month")
                    );


            monthField.clear();

            monthField.sendKeys(
                    String.valueOf(month)
            );



            // DAY
            WebElement dayField =
                    driver.findElement(
                            By.id("said_day")
                    );


            dayField.clear();

            dayField.sendKeys(
                    String.valueOf(day)
            );



            // COUNT
            WebElement countField =
                    driver.findElement(
                            By.id("said_count")
                    );


            countField.clear();

            countField.sendKeys(
                    String.valueOf(count)
            );



            // GENDER
            driver.findElement(
                    By.xpath(
                    "//option[@value='" + gender + "']"
                    )
            ).click();




            // GENERATE BUTTON
            WebElement generateButton =
                    driver.findElement(
                            By.id("said_generate_object")
                    );


            ((JavascriptExecutor)driver)
                    .executeScript(
                    "arguments[0].scrollIntoView(true);",
                    generateButton
                    );


            Thread.sleep(1000);



            ((JavascriptExecutor)driver)
                    .executeScript(
                    "arguments[0].click();",
                    generateButton
                    );




            // Wait for results
            wait.until(
                    ExpectedConditions.presenceOfAllElementsLocatedBy(
                            By.xpath(
                            "//*[@id='said_generated_results']/table/tbody/tr/td[1]"
                            )
                    )
            );



            List<WebElement> idCells =
                    driver.findElements(
                    By.xpath(
                    "//*[@id='said_generated_results']/table/tbody/tr/td[1]"
                    ));




            for(WebElement cell : idCells){


                String id =
                        cell.getText()
                            .trim();


                if(!id.isEmpty()){

                    generatedIds.add(id);

                }

            }



        }
        catch(Exception e){

            throw new RuntimeException(
                    "Failed generating SA IDs: "
                    + e.getMessage()
            );

        }
        finally {

            driver.quit();

        }



        if(generatedIds.size() < count){

            throw new IllegalStateException(
                    "Expected "
                    + count
                    + " IDs but received "
                    + generatedIds.size()
            );

        }



        return generatedIds.subList(
                0,
                count
        );

    }




    /**
     * Standalone execution
     */
    public static void main(String[] args) {



        Random random = new Random();



        // AGE 21 - 50
        int age =
                random.nextInt(30) + 21;



        // MONTH 1 - 12
        int month =
                random.nextInt(12) + 1;



        // DAY 1 - 28
        int day =
                random.nextInt(28) + 1;



        int count = 8;


        String gender = "Male";



        System.out.println(
                "=============================="
        );

        System.out.println(
                "TEST DATA USED"
        );


        System.out.println(
                "Age    : " + age
        );


        System.out.println(
                "Month  : " + month
        );


        System.out.println(
                "Day    : " + day
        );


        System.out.println(
                "Gender : " + gender
        );


        System.out.println(
                "Count  : " + count
        );


        System.out.println(
                "=============================="
        );




        List<String> ids =
                generateIds(
                        count,
                        age,
                        month,
                        day,
                        gender
                );




        System.out.println(
                "GENERATED TEST SA IDS"
        );


        for(String id : ids){

            System.out.println(id);

        }


        System.out.println(
                "=============================="
        );

    }

}