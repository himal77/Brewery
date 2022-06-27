from decimal import MIN_EMIN
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

# function to construct sql statement
def construct_statement(quantity, beerUpc):
    return "update beer_inventory set quantity_on_hand={} where beer_upc={}".format(
        quantity, beerUpc)


def handler(event, context):
    # establish connection
    cursor = connection.cursor()

    # extracting information from query params
    beerUpc = event['queryStringParameters']['beerUpc']

    # fetching information of the beer from database
    statement = "select * from beer_inventory where beer_upc=" + beerUpc
    cursor.execute(statement)
    rows = cursor.fetchall()

    # to make sure, decrease should not be less than zero and increase should not be less than max
    available_quantity = 0
    min_quantity_on_hand = 0
    for row in rows:
        available_quantity = row[3]
        min_quantity_on_hand = row[2]

    # create the response
    response = {}
    response['statusCode'] = 200
    response['headers'] = {}
    response['headers']['Content-Type'] = 'application/json'

    if available_quantity <= min_quantity_on_hand:
        response['body'] = json.dumps({"islow":"true"})
    else:
        response['body'] = json.dumps({"islow":"false"})

    # create response object
    return response
