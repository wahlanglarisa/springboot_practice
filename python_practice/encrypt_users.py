import psycopg2
from connect import connect
from config import load_config
import psycopg2
import bcrypt

# ---- DB CONNECTION ----
config=load_config()
conn = connect(config)
print(conn)

cursor = conn.cursor()

# ---- GET ALL USERS ----
cursor.execute("SELECT user_id, password FROM \"user\"")
users = cursor.fetchall()

for user_id, plain_password in users:

    # Skip already hashed passwords
    if plain_password.startswith("$2"):
        continue

    # Convert to bytes
    password_bytes = plain_password.encode('utf-8')

    # Generate salt + hash
    hashed = bcrypt.hashpw(password_bytes, bcrypt.gensalt(rounds=10))

    # Convert back to string
    hashed_str = hashed.decode('utf-8')

    # Update DB
    cursor.execute(
        "UPDATE \"user\" SET password = %s WHERE user_id = %s",
        (hashed_str, user_id)
    )

conn.commit()
cursor.close()
conn.close()

print("✅ Password migration complete.")
