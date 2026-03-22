import os
from docx import Document

# Target folder
target_dir = r"C:\Users\arenaho.muhali\OneDrive - MultiChoice\Documents\Multichoice AJ\STS ANGOLA\Test Results\Akhil"
os.makedirs(target_dir, exist_ok=True)







tests = [


    "FSS TSM Zambia",
    "FSS TSM Ethiopia",
    "FSS TSM Ghana",
    "FSS TSM Kenya",
    "FSS TSM Uganda",
    "FSS TSM Mozambique",
    "FSS TSM Angola"



]











for test in tests:
    doc = Document()
    doc.add_heading(test, level=1)

    # Sanitize filename
    safe_filename = test.replace('\t', ' ').replace(':', '').replace('|', '').replace('/', '-')
    filename = f"{safe_filename}.docx"
    filepath = os.path.join(target_dir, filename)

    doc.save(filepath)

print("✅ Word documents created successfully in:")
print(target_dir)