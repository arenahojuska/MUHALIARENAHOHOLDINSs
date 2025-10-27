import os
from docx import Document

# Target folder (unchanged)
target_dir = r"C:\Users\arenaho.muhali\OneDrive - MultiChoice\Documents\Multichoice AJ\STS ANGOLA\Test Results\Renew STS Customers"
os.makedirs(target_dir, exist_ok=True)

# Python list with your full set of test cases
# Python list of the 24 STS activation test cases
# Python list of subscription upgrade test cases
# Python list of STS cross-activation test cases








tests = [
    "TS077_TC02	Verify  Renew STS Family Mais Customer",

]







for test in tests:
    # Create document
    doc = Document()
    doc.add_heading(test, level=1)  # heading with test name


    # Save as Word doc
    filename = f"{test}.docx"
    filepath = os.path.join(target_dir, filename)
    doc.save(filepath)

print("✅ Word documents created successfully in:")
print(target_dir)
