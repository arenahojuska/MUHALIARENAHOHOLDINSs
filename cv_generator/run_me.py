import subprocess
import sys
import os

# Ensure the path points correctly to your script
script_path = os.path.join("muhali_cv_rebuilder_final.py")

if __name__ == "__main__":
    print("Launching Muhali AI Test Case Generator.")

    # Remove the spaces before "run"
    subprocess.run([sys.executable, "-m", "streamlit", "run", script_path])