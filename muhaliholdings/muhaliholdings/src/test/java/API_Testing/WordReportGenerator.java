package API_Testing;


	import org.apache.poi.util.Units;
	import org.apache.poi.xwpf.usermodel.*;
	import org.jfree.chart.ChartFactory;
	import org.jfree.chart.ChartUtils;
	import org.jfree.chart.JFreeChart;
	import org.jfree.chart.plot.PiePlot;
	import org.jfree.data.general.DefaultPieDataset;

	import java.awt.Color;
	import java.io.*;
	import java.text.SimpleDateFormat;
	import java.util.Date;

	public class WordReportGenerator {
		private static final String TEMPLATE = "C:/Users/ARENAHO JUSKA/eclipse-workspace/muhaliholdings/muhaliholdings/DAILY STATUS REPORTING/MUHALI HOLDINGS DAILY STATUS REPORT.docx";

	    public static void generateStatusReport(int pass, int fail, int skip) {
	        System.out.println(">>> Initializing Report Generation: " + pass + " Pass, " + fail + " Fail, " + skip + " Skip");

	        File templateFile = new File(TEMPLATE);
	        if (!templateFile.exists()) {
	            System.err.println("CRITICAL ERROR: Template not found at " + TEMPLATE);
	            return;
	        }

	        try (FileInputStream fis = new FileInputStream(templateFile);
	             XWPFDocument doc = new XWPFDocument(fis)) {

	            // ── 1. HEADER ──────────────────────────────────────────────────────
	            XWPFParagraph head = doc.createParagraph();
	            head.setAlignment(ParagraphAlignment.CENTER);
	            XWPFRun headRun = head.createRun();
	            headRun.setBold(true);
	            headRun.setFontSize(22);
	            headRun.setColor("1D4E89");
	            headRun.setText("MUHALI HOLDINGS - DAILY AUTOMATION STATUS");

	            // ── 2. LABEL ───────────────────────────────────────────────────────
	            XWPFParagraph labelPara = doc.createParagraph();
	            labelPara.setSpacingBefore(200);
	            XWPFRun labelRun = labelPara.createRun();
	            labelRun.setFontSize(14);
	            labelRun.setBold(true);
	            labelRun.setText("Final Execution Results:");

	          
	            XWPFParagraph passPara = doc.createParagraph();
	            XWPFRun passRun = passPara.createRun();
	            passRun.setFontSize(13);
	            passRun.setColor("4CAF50");
	            passRun.setText("  Total Passed:  " + pass);

	          
	            XWPFParagraph failPara = doc.createParagraph();
	            XWPFRun failRun = failPara.createRun();
	            failRun.setFontSize(13);
	            failRun.setColor("F44336");
	            failRun.setText("  Total Failed:  " + fail);

	          
	            XWPFParagraph skipPara = doc.createParagraph();
	            XWPFRun skipRun = skipPara.createRun();
	            skipRun.setFontSize(13);
	            skipRun.setColor("9E9E9E");
	            skipRun.setText("⚪  Total Skipped: " + skip);

	            // ── 6. PIE CHART ───────────────────────────────────────────────────
	            File chartFile = createPieChart(pass, fail, skip);

	            XWPFParagraph imgPara = doc.createParagraph();
	            imgPara.setAlignment(ParagraphAlignment.CENTER);
	            XWPFRun imgRun = imgPara.createRun();

	            try (FileInputStream chartStream = new FileInputStream(chartFile)) {
	                imgRun.addPicture(
	                    chartStream,
	                    XWPFDocument.PICTURE_TYPE_PNG,
	                    "chart.png",
	                    Units.toEMU(432),   
	                    Units.toEMU(288)    
	                );
	            }

	            // ── 7. RESOLVE "Test Reporting" FOLDER & SAVE ─────────────────────
	            String dateStr = new SimpleDateFormat("yyyy-MM-dd_HHmm").format(new Date());
	            String outputFileName = "MUHALI_HOLDINGS_REPORT_" + dateStr + ".docx";

	            // Locate the folder next to the template; create it if absent
	            File reportingFolder = new File(templateFile.getParent(), "Test Reporting");
	            if (!reportingFolder.exists()) {
	                boolean created = reportingFolder.mkdirs();
	                if (!created) {
	                    System.err.println("WARNING: Could not create 'Test Reporting' folder at "
	                            + reportingFolder.getAbsolutePath()
	                            + " — saving to template directory instead.");
	                    reportingFolder = templateFile.getParentFile(); 
	                } else {
	                    System.out.println(">>> Created folder: " + reportingFolder.getAbsolutePath());
	                }
	            }

	            File outputFile = new File(reportingFolder, outputFileName);

	            try (FileOutputStream out = new FileOutputStream(outputFile)) {
	                doc.write(out);
	            }
	            EmailEngine.sendReport(outputFile.getAbsolutePath());
	            // ── 8. CLEANUP & OPEN ──────────────────────────────────────────────
	            if (chartFile.exists()) chartFile.delete();
	            System.out.println(" Report saved: " + outputFile.getAbsolutePath());

	            if (java.awt.Desktop.isDesktopSupported()) {
	                java.awt.Desktop.getDesktop().open(outputFile);
	            }

	        } catch (Exception e) {
	            System.err.println(" Error generating Word Report: " + e.getMessage());
	            e.printStackTrace();
	        }
	    }

	    private static File createPieChart(int pass, int fail, int skip) throws IOException {
	        DefaultPieDataset dataset = new DefaultPieDataset();
	        if (pass > 0) dataset.setValue("Passed ("  + pass + ")", pass);
	        if (fail > 0) dataset.setValue("Failed ("  + fail + ")", fail);
	        if (skip > 0) dataset.setValue("Skipped (" + skip + ")", skip);

	        JFreeChart chart = ChartFactory.createPieChart(
	            "Test Execution Summary", dataset, true, true, false
	        );

	        PiePlot plot = (PiePlot) chart.getPlot();
	        plot.setSectionPaint("Passed ("  + pass + ")", new Color(76,  175, 80));
	        plot.setSectionPaint("Failed ("  + fail + ")", new Color(244, 67,  54));
	        plot.setSectionPaint("Skipped (" + skip + ")", new Color(189, 189, 189));
	        plot.setBackgroundPaint(Color.WHITE);
	        plot.setOutlineVisible(false);

	        File file = new File("temp_chart_image.png");
	        ChartUtils.saveChartAsPNG(file, chart, 600, 400);
	        return file;
	    }
	}