import psycopg2
from connect import connect
from config import load_config
import psycopg2
import bcrypt

config=load_config()
conn=connect(config)
print(conn)
cursor=conn.cursor()
cursor.execute("SELECT id FROM \"role\" where name='Admin'")
role_id=cursor.fetchone()
# print(users[0])
createAdminUserQuery="insert into \"user\"(email,password,role_id) values (%s,%s,%s)"
plain_password="123456789"
password_bytes = plain_password.encode('utf-8')
print(password_bytes)
    # Generate salt + hash
hashed = bcrypt.hashpw(password_bytes, bcrypt.gensalt(rounds=10))
print(hashed)
    # Convert back to string
hashed_str = hashed.decode('utf-8')
print(hashed_str)

cursor.execute(createAdminUserQuery,("admin@mgail.com",hashed_str,role_id[0]))
conn.commit()
print("Admin user created successfully")