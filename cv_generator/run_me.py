import subprocess
import sys
import os

script_path = os.path.join( "muhali_cv_rebuilder_final.py")

if __name__ == "__main__":
    print(" Launching Muhali AI Test Case Generator.")

    subprocess.run([sys.executable, "-m", "streamlit", "        1run", script_path])