# testdata.py
BASE_URL = "https://eshopmuhali.netlify.app/"

# Selectors
SHOP_LINK = "xpath=//*[@id='root']/nav/div[2]/a[2]"
SHOP_HEADER = "xpath=//*[@id='root']/div/h2"
CART_ICON = "xpath=//*[@id='root']/nav/div[1]/div[3]/a/span"

# We use {index} as a placeholder for the loop
PRODUCT_ADD_BTN = "xpath=//*[@id='root']/div/div[3]/div[{index}]/div[2]/button"
CART_TOTAL_TEXT = "xpath=//*[@id='root']/div/div/div[25]/div/div/div[1]/span[2]"

# Expected Values
EXPECTED_CART_COUNT = "23"