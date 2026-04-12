import subprocess
import sys
import os

script_path = os.path.join( "cvGenerator.py")

if __name__ == "__main__":
    print(" Launching Muhali AI Test Case Generator.")

    subprocess.run([sys.executable, "-m", "streamlit", "run", script_path])