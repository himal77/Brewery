import pymysql
import json

# temporary configuration value
host = "brewery.c4jdjnkep4td.us-west-2.rds.amazonaws.com"
username = "brewery"
password = "brewery123"
database = "brewery"

# connection
connection = pymysql.connect(host = host, user = username, passwd = password, db = database)

# function to convert to json
def add_to_beer_inventory(beer_inventory, beerUpc, quantityOnHand, minOnHand, maxOnHand): 
    beer_inventory.append({"beerUpc":beerUpc ,"quantityOnHand": quantityOnHand,"minOnHand": minOnHand,"maxOnHand": maxOnHand})

def handler(event, context):
    # establish connection
    cursor = connection.cursor()

    # for debuggine purpose, the executed statement is kept in variable.
    statement = ""

    # parse the query string
    if event['queryStringParameters'] is None:
        statement = "select * from beer_inventory";
    else:
        beerUpc = event['queryStringParameters']['beerUpc']
        statement = "select * from beer_inventory where beer_upc=" + beerUpc
    
    print(statement)
    cursor.execute(statement)
    
    # fetch data
    rows = cursor.fetchall()

    # body to return
    beer_inventory = [] 
    
    # converting sql query to json
    for row in rows:
        add_to_beer_inventory(beer_inventory, row[0], row[1], row[2], row[3])

    # create the response
    response = {}
    response['statusCode'] = 200
    response['headers'] = {}
    response['headers']['Content-Type'] = 'application/json'
    response['body'] = json.dumps(beer_inventory)

    # create response object
    return response