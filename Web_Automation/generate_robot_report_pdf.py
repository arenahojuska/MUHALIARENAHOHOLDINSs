import pdfkit
import os
path_wkhtmltopdf = r"C:/Program Files/wkhtmltopdf/bin/wkhtmltopdf.exe"
config = pdfkit.configuration(wkhtmltopdf=path_wkhtmltopdf)

pdfkit.from_file('report.html', 'report.pdf', configuration=config)



# Paths
log_dir = "C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/log"
report_html = os.path.join(log_dir, "report.html")
output_pdf = os.path.join(log_dir, "Robot_Report.pdf")



# Generate PDF
pdfkit.from_file(report_html, output_pdf)  # add config=config if needed

print(f"✅ Report generated at: {output_pdf}")
