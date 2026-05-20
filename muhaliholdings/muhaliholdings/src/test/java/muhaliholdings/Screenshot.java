package muhaliholdings;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshot {

    public static String takeScreenshot(WebDriver driver) {

        try {
            // 🔹 Project root directory (portable for Git)
            String projectDir = System.getProperty("user.dir");

            Path basePath = Paths.get(projectDir, "screenshots");

            // 🔹 Date folder
            String today = LocalDate.now().toString();
            Path targetFolder = basePath.resolve(today);

            // Create folders if they don't exist
            Files.createDirectories(targetFolder);

            // 🔹 Timestamp filename
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH-mm-ss-SSS"));

            Path filePath = targetFolder.resolve(timestamp + ".png");

            // 🔹 Take screenshot
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // 🔹 Save file
            Files.copy(srcFile.toPath(), filePath, StandardCopyOption.REPLACE_EXISTING);

            System.out.println("Screenshot saved: " + filePath);

            return filePath.toString();

        } catch (IOException e) {
            System.out.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}