import smtplib
from email.mime.text import MIMEText

sender_email = "arenahomuhalil@yahoo.com"
receiver_email = "recipient@example.com"
app_password = "uhone4god@@"

msg = MIMEText("Hi")
msg["Subject"] = "Greeting"
msg["From"] = sender_email
msg["To"] = receiver_email

try:
    with smtplib.SMTP("smtp.mail.yahoo.com", 587) as smtp:
        smtp.login(sender_email, app_password)
        smtp.send_message(msg)
    print("✅ Email sent successfully via Yahoo!")
except Exception as e:
    print(f"❌ Failed to send email: {e}")
