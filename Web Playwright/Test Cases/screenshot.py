import os
from datetime import datetime
from pathlib import Path
from robot.libraries.BuiltIn import BuiltIn


class screenshot:
    """Robot Framework library to save screenshots to a specific absolute path"""

    def take_screenshot(self):
        """
        Captured as 'screenshot.Take Screenshot' in Robot Framework.
        Saves to: [Project Root]/Web Playwright/screenshots/YYYY-MM-DD/
        """

        # 1. Define the base directory relative to this file
        # This finds the folder where screenshot.py is located
        current_file_path = Path(__file__).resolve()

        # We move up to the 'Web Playwright' directory
        # (Assuming screenshot.py is in 'Web Playwright' or a subfolder like 'Libraries')
        # If it's in 'Test Cases', we go up one level.
        base_path = current_file_path.parent / "screenshots"

        # 2. Create sub-folder for today's date
        today = datetime.now().strftime("%Y-%m-%d")
        target_folder = base_path / today

        # Ensure the directory exists
        target_folder.mkdir(parents=True, exist_ok=True)

        # 3. Create timestamped filename
        timestamp = datetime.now().strftime("%H-%M-%S-%f")[:-3]
        file_path = target_folder / f"{timestamp}.png"

        # 4. Get screenshot from Browser as bytes
        # Note: Ensure 'Library Browser' is initialized in your .robot file
        screenshot_bytes = BuiltIn().run_keyword("Browser.Take Screenshot", "None", "return_as=bytes")

        # 5. Save bytes to file
        with open(file_path, "wb") as f:
            f.write(screenshot_bytes)

        # Log the path to the Robot Framework log for easy debugging
        print(f"Screenshot saved to: {file_path}")

        return str(file_path)