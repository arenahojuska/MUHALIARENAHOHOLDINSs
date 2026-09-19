package RMA;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.LoadState;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.*;
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

        // Folder: "<date> Evidence"
        evidenceFolder = basePath.resolve(today + " Evidence");
        Files.createDirectories(evidenceFolder);

        // Word doc named using execution start time
        String execStartTime = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("HH-mm-ss"));
        wordDocPath = evidenceFolder.resolve(execStartTime + ".docx");

        // Writes the main title ONCE in the document header upon initialization
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

    private static synchronized void appendImageToWordDoc(Path imagePath) {
        try (FileInputStream fis = new FileInputStream(wordDocPath.toFile());
             XWPFDocument document = new XWPFDocument(fis)) {

            // Image only (No separate text caption paragraph)
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

    public static String takeScreenshot(Page page) {

        try {

            initEvidenceRun();

            page.waitForLoadState(LoadState.LOAD);
            page.waitForTimeout(1000);

            // Fetch current URL directly from Playwright Page
            String currentUrl = page.url();

            String projectDir = System.getProperty("user.dir");
            Path basePath = Paths.get(projectDir, "screenshots");

            String today = LocalDate.now().toString();
            Path targetFolder = basePath.resolve(today);
            Files.createDirectories(targetFolder);

            String timestamp = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("HH-mm-ss"));

            Path filePath = targetFolder.resolve(timestamp + ".png");

            byte[] screenshotBytes = page.screenshot();

            // Draw URL banner ON THE PICTURE ONLY using Java Graphics2D
            BufferedImage originalImage = ImageIO.read(new ByteArrayInputStream(screenshotBytes));

            int bannerHeight = 40;
            int width = originalImage.getWidth();
            int height = originalImage.getHeight();

            BufferedImage combinedImage = new BufferedImage(width, height + bannerHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = combinedImage.createGraphics();

            g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Dark background banner
            g2d.setColor(new Color(30, 30, 30));
            g2d.fillRect(0, 0, width, bannerHeight);

            // White text for URL stamped onto image
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("SansSerif", Font.BOLD, 14));
            g2d.drawString("URL: " + currentUrl, 15, 25);

            // Render original screenshot below the banner
            g2d.drawImage(originalImage, 0, bannerHeight, null);
            g2d.dispose();

            ImageIO.write(combinedImage, "png", filePath.toFile());

            System.out.println("Screenshot saved: " + filePath);

            // Append the image to Word doc without duplicating text captions
            appendImageToWordDoc(filePath);

            return filePath.toString();

        } catch (IOException e) {

            System.out.println("Screenshot failed: " + e.getMessage());
            return null;
        }
    }
}