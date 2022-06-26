import psycopg2
from psycopg2.extras import RealDictCursor
import json

host = "brewery.c4jdjnkep4td.us-west-2.rds.amazonaws.com"
username = "brewery"
password = "U8y7tLXXgMnpZXkwEZZt"
database = "brewery"

connection = psycopg2.connect(
    host = host,
    database = database,
    user = username,
    password = password
)

def lambda_handler():
    cursor = connection.cursor()
    cursor.execute("select * from beer")

    rows = cursor.fetchall()
    
    for row in rows:
        print("{0} {1} {2} {3}".format(row[0], row[1], row[2], row[3]))

lambda_handler()

