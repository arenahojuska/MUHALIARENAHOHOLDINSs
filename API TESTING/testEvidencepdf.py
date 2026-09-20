import os
from collections import defaultdict
from PIL import Image
from datetime import datetime

# === CONFIGURATION ===
BASE_EVIDENCE_DIR = r'C:/Users/arenaho.muhali/PycharmProjects/MUHALI_ARENAHO_HOLDINGS/Web_Automation/Web_Automation/Test_Evidence'

today = datetime.today().strftime('%Y-%m-%d')
IMAGE_FOLDER = os.path.join(BASE_EVIDENCE_DIR, today)
OUTPUT_FOLDER = os.path.join(IMAGE_FOLDER, 'combined_pdfs')


def group_images_by_first_word(folder):
    groups = defaultdict(list)
    for file in os.listdir(folder):
        if file.lower().endswith('.png'):
            first_word = file.split('_')[0]
            groups[first_word].append(os.path.join(folder, file))
    return groups


def create_pdfs(image_groups, output_folder):
    os.makedirs(output_folder, exist_ok=True)

    for group_name, image_files in image_groups.items():
        image_files.sort()
        images = [Image.open(img).convert('RGB') for img in image_files]

        if images:
            pdf_path = os.path.join(output_folder, f"{group_name}.pdf")
            images[0].save(pdf_path, save_all=True, append_images=images[1:])
            print(f"✅ Created PDF: {pdf_path}")


if __name__ == "__main__":
    print(f"Looking for images in: {IMAGE_FOLDER}")
    if not os.path.exists(IMAGE_FOLDER):
        print(f"❌ Folder not found: {IMAGE_FOLDER}")
    else:
        grouped = group_images_by_first_word(IMAGE_FOLDER)
        if not grouped:
            print("⚠️ No PNG images found to process.")
        else:
            print(f"Found groups: {list(grouped.keys())}")
            create_pdfs(grouped, OUTPUT_FOLDER)
            print("✅ All PDFs generated and opened.")
