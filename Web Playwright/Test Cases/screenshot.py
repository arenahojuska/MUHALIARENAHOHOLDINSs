from datetime import datetime
from pathlib import Path
from robot.libraries.BuiltIn import BuiltIn


class screenshot:
    """Robot Framework library to save screenshots to a specific absolute path"""

    def take_screenshot(self):
        """
        Captured as 'screenshot.Take Screenshot' in Robot Framework.
        Saves to: C:\\Users\\arenaho.muhali\\PycharmProjects\\MUHALI_ARENAHO_HOLDINGS\\Web Playwright\\screenshots\\YYYY-MM-DD\\
        """

        # 1️⃣ Define the absolute base directory
        base_path = Path(r"C:\Users\arenaho.muhali\PycharmProjects\MUHALI_ARENAHO_HOLDINGS\Web Playwright\screenshots")

        # 2️⃣ Create sub-folder for today's date
        today = datetime.now().strftime("%Y-%m-%d")
        target_folder = base_path / today

        # Ensure the directory exists (parents=True creates the 'screenshots' folder if it's missing too)
        target_folder.mkdir(parents=True, exist_ok=True)

        # 3️⃣ Create timestamped filename
        timestamp = datetime.now().strftime("%H-%M-%S-%f")[:-3]
        file_path = target_folder / f"{timestamp}.png"

        # 4️⃣ Get screenshot from Browser as bytes
        screenshot_bytes = BuiltIn().run_keyword("Browser.Take Screenshot", "return_as=bytes")

        # 5️⃣ Save bytes to file
        with open(file_path, "wb") as f:
            f.write(screenshot_bytes)

        # Log the path to the Robot Framework log for easy debugging
        print(f"Screenshot saved to: {file_path}")

        return str(file_path)