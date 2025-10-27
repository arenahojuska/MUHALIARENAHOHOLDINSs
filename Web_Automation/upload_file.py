import pandas as pd

# Define the path to the Excel file
file_path = r'C:\Users\arenaho.muhali\PycharmProjects\MUHALI_ARENAHO_HOLDINGS\Web_Automation\Book11.xlsx'

try:
    # Read the Excel file
    # The 'engine' parameter specifies the engine to use for reading Excel files.
    # 'openpyxl' is recommended for .xlsx files.
    df = pd.read_excel(file_path, engine='openpyxl')

    # Print the header (column names)
    # df.columns returns the column labels (header).
    # .tolist() converts the column labels into a Python list for easy printing.
    print("Header columns:", df.columns.tolist())

except FileNotFoundError:
    # Handle the specific error if the file does not exist at the given path.
    print("The file was not found. Please check the file path.")
except Exception as e:
    # Handle any other general exceptions that might occur during file reading.
    print(f"An error occurred: {e}")