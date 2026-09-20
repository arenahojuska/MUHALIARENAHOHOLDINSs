import smtplib
import ssl
from email.mime.text import MIMEText
from email.mime.multipart import MIMEMultipart

# --- Configuration (using common Outlook/Office 365 settings) ---
SMTP_SERVER = 'smtp.office365.com'
SMTP_PORT = 587  # Standard port for TLS encryption

# Your Outlook Email Address (Username)
SENDER_EMAIL = "arenahojuska@outlook.com"

# Your Password (or App Password if 2FA is enabled)
SENDER_PASSWORD = "Arenaho5god@@"

# Recipient Email Address
RECEIVER_EMAIL = "arenahojuska@gmail.com"

# --- Email Content ---
SUBJECT = "Automated Test Email from Python"
BODY = """
Hello,

This email was sent automatically from your Python script using the Outlook/Office 365 SMTP server.

If you received this, the connection and authentication were successful!
"""

# Create a secure SSL context
context = ssl.create_default_context()

# Build the email message
message = MIMEMultipart()
message['From'] = SENDER_EMAIL
message['To'] = RECEIVER_EMAIL
message['Subject'] = SUBJECT

# Attach the plain text body
message.attach(MIMEText(BODY, 'plain'))

print("Attempting to connect and send email...")

try:
    # Connect to the SMTP server using 'with' to ensure the connection is closed
    with smtplib.SMTP(SMTP_SERVER, SMTP_PORT) as server:
        # Initiate TLS encryption
        server.starttls(context=context)

        # Log in to the email account
        server.login(SENDER_EMAIL, SENDER_PASSWORD)

        # Send the email
        server.sendmail(SENDER_EMAIL, RECEIVER_EMAIL, message.as_string())

        print(f"✅ Success! Email sent from {SENDER_EMAIL} to {RECEIVER_EMAIL}")

except smtplib.SMTPAuthenticationError:
    print("❌ Error: SMTP Authentication Failed.")
    print("Please double-check your password. If you have 2FA enabled, you must use an App Password.")
except Exception as e:
    print(f"❌ An unexpected error occurred: {e}")