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
    changeCmdType = event['queryStringParameters']['changeCmdType']
    beerUpc = event['queryStringParameters']['beerUpc']
    quantity = int(event['queryStringParameters']['quantity'])

    # fetching information of the beer from database
    statement = "select * from beer_inventory where beer_upc=" + beerUpc
    cursor.execute(statement)
    rows = cursor.fetchall()

    # to make sure, decrease should not be less than zero and increase should not be less than max
    available_quantity = 0
    for row in rows:
        available_quantity = row[3]

    # if decrease, check to not make quantity in minus.
    if changeCmdType == "decrease":
        if available_quantity < quantity:
            statement = construct_statement(0, beerUpc)
        else:
            new_quantity = available_quantity - quantity
            statement = construct_statement(new_quantity, beerUpc)
    else:
        new_quantity = available_quantity + int(quantity)
        statement = construct_statement(new_quantity, beerUpc)

    # print the exectued statement for loggin purpose
    print(statement)
    cursor.execute(statement)
    connection.commit()

    # fetching information of the beer from database
    statement = "select * from beer_inventory where beer_upc=" + beerUpc
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
