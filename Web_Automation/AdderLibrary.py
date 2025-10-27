
import pandas as pd
from robot.api.deco import keyword

class AdderLibrary:
    @keyword("Add Two Numbers")
    def add_numbers(self, a, b):
        return int(a) - int(b)


    @keyword("Load Credentials")
    def load_credentials(self, file_path, sheet_name=None):
        if sheet_name:
            self.df = pd.read_excel(file_path, sheet_name=sheet_name)
        else:
            self.df = pd.read_excel(file_path)
        self.credentials = self.df.to_dict(orient='records')
        self.print_credentials_table()
        return len(self.credentials)

    @keyword("Get Credential By Index")
    def get_credential_by_index(self, index):
        if not hasattr(self, 'credentials') or self.credentials is None:
            raise Exception("Credentials not loaded. Use `Load Credentials` first.")
        idx = int(index)
        return self.credentials[idx]["username"], self.credentials[idx]["password"]

    def print_credentials_table(self):
        if not hasattr(self, 'df') or self.df is None:
            raise Exception("Credentials not loaded. Use `Load Credentials` first.")
        print(self.df.to_string(index=False))
        return len(self.df)
