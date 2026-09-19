package RMA;

import org.apache.poi.xwpf.usermodel.*;
import org.apache.poi.util.Units;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Screenshot {

    // Shared state for the current test run (set once, reused for every screenshot)
    private static Path evidenceFolder;
    private static Path wordDocPath;
    private static boolean initialized = false;

    private static synchronized void initEvidenceRun() throws IOException {
        if (initialized) {
            return;
        }

        String projectDir = System.getProperty("user.dir");
        Path basePath = Paths.get(projectDir, "screenshots");

        String today = LocalDate.now().toString();

        // Second folder: "<date> Evidence"
        evidenceFolder = basePath.resolve(today + " Evidence");
        Files.createDirectories(evidenceFolder);

        // Word doc named using the time execution started
        String execStartTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        wordDocPath = evidenceFolder.resolve(execStartTime + ".docx");

        // Create a blank docx to start with
        try (XWPFDocument document = new XWPFDocument();
             FileOutputStream out = new FileOutputStream(wordDocPath.toFile())) {

            XWPFParagraph title = document.createParagraph();
            XWPFRun titleRun = title.createRun();
            titleRun.setText("Testing Evidence  " + LocalDateTime.now());
            titleRun.setBold(true);
            titleRun.setFontSize(14);

            document.write(out);
        }

        initialized = true;
    }

    private static synchronized void appendImageToWordDoc(Path imagePath, String currentUrl) {
        try (FileInputStream fis = new FileInputStream(wordDocPath.toFile());
             XWPFDocument document = new XWPFDocument(fis)) {

            // Caption: timestamp + URL
            XWPFParagraph caption = document.createParagraph();
            XWPFRun captionRun = caption.createRun();
            
            captionRun.setBold(true);
            captionRun.setFontSize(11);

            // Image
            XWPFParagraph imgParagraph = document.createParagraph();
            XWPFRun imgRun = imgParagraph.createRun();

            try (FileInputStream imgStream = new FileInputStream(imagePath.toFile())) {
                BufferedImage img = ImageIO.read(imagePath.toFile());
                int emuWidth = Units.toEMU(Math.min(img.getWidth(), 600)); // scale to fit page
                int emuHeight = Units.toEMU(Math.min(img.getWidth(), 600) * ((double) img.getHeight() / img.getWidth()));

                imgRun.addPicture(imgStream, XWPFDocument.PICTURE_TYPE_PNG,
                        imagePath.getFileName().toString(), emuWidth, emuHeight);
            }

            imgRun.addBreak();

            try (FileOutputStream out = new FileOutputStream(wordDocPath.toFile())) {
                document.write(out);
            }

        } catch (Exception e) {
            System.out.println("Failed to append screenshot to Word doc: " + e.getMessage());
        }
    }

    public static String takeScreenshot(WebDriver driver) {

        try {

            initEvidenceRun();

            // Wait until the page has completely loaded
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

            wait.until(webDriver ->
                    ((JavascriptExecutor) webDriver)
                            .executeScript("return document.readyState")
                            .equals("complete")
            );

            // Small pause to allow UI rendering/animations to finish
            Thread.sleep(1000);

            // Fetch current URL directly from WebDriver
            String currentUrl = driver.getCurrentUrl();

            // Project root directory
            String projectDir = System.getProperty("user.dir");
            Path basePath = Paths.get(projectDir, "screenshots");

            // Create date folder (original behavior, unchanged)
            String today = LocalDate.now().toString();
            Path targetFolder = basePath.resolve(today);
            Files.createDirectories(targetFolder);

            // Create timestamp filename
            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH-mm"));

            Path filePath = targetFolder.resolve(timestamp + ".png");

            // Capture screenshot file
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

            // Process image: Add top banner with URL
            BufferedImage originalImage = ImageIO.read(srcFile);

            int bannerHeight = 40;
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            BufferedImage combinedImage = new BufferedImage(width, height + bannerHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = combinedImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            g2d.setColor(new Color(30, 30, 30));
            g2d.fillRect(0, 0, width, bannerHeight);

            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
            g2d.drawString("URL: " + currentUrl, 15, 25);

            g2d.drawImage(originalImage, 0, bannerHeight, null);
            g2d.dispose();

            ImageIO.write(combinedImage, "png", filePath.toFile());

            System.out.println("Screenshot saved: " + filePath);

            // NEW: append this screenshot into the run's Word evidence document
            appendImageToWordDoc(filePath, currentUrl);

            return filePath.toString();

        } catch (IOException | InterruptedException e) {

            System.out.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}