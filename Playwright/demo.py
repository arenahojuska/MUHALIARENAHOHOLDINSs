from playwright.sync_api import sync_playwright

with sync_playwright() as p:
    browser = p.chromium.launch(headless=False, args=["--start-maximized"])
    context = browser.new_context(no_viewport=True)  
    page = context.new_page()
    page.goto("http://localhost:3001/")
    page.wait_for_timeout(5000)
    browser.close()
