import pymysql
import json

# temporary configuration value
host = "brewery.c4jdjnkep4td.us-west-2.rds.amazonaws.com"
username = "brewery"
password = "brewery123"
database = "brewery"

# connection
connection = pymysql.connect(
    host=host, user=username, passwd=password, db=database)

# function to convert to json
def construct_json( beerUpc, maxOnHand, minOnHand, quantityOnHand):
    return {"beerUpc": beerUpc, "maxOnHand": maxOnHand, "minOnHand": minOnHand, "quantityOnHand": quantityOnHand}


def handler(event, context):
    # establish connection
    cursor = connection.cursor()

    # for debuggine purpose, the executed statement is kept in variable.
    statement = ""

    # parse the query string
    if event['queryStringParameters'] is None:
        statement = "select * from beer_inventory"
    else:
        beerUpc = event['queryStringParameters']['beerUpc']
        statement = "select * from beer_inventory where beer_upc=" + beerUpc

    print(statement)
    cursor.execute(statement)
    rows = cursor.fetchall()

    # create the response
    response = {}
    response['statusCode'] = 200
    response['headers'] = {}
    response['headers']['Content-Type'] = 'application/json'

    # body to return
    beer_inventory = []
    for row in rows:
        beer_inventory.append(construct_json(row[0], row[1], row[2], row[3]))

    if len(beer_inventory) == 1:
        response['body'] = json.dumps(beer_inventory[0])
    else:
        response['body'] = json.dumps(beer_inventory) 

    # create response object
    return response
