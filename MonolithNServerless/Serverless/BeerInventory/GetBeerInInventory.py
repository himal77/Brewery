import pymysql
import json

# confiuration value
host = "brewery.c4jdjnkep4td.us-west-2.rds.amazonaws.com"
username = "brewery"
password = "brewery123"
database = "brewery"

# connection
connection = pymysql.connect(host = host, user = username, passwd = password, db = database)

def handler(event, context):
    cursor = connection.cursor()
    cursor.execute("select * from beer")

    rows = cursor.fetchall()

    print(rows)
    
    for row in rows:
        print("{0} {1} {2} {3}".format(row[0], row[1], row[2], row[3]))

handler("", "")
