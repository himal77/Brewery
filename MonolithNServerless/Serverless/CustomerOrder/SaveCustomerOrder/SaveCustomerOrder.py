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
def construct_json_customer_order(beerUpc, customerId, date, orderId, quantity, time):
    return {"beerUpc": beerUpc, "customerId": customerId, "date": date, "orderId": orderId, "quantity": quantity, "time": time}

def handler(event, context):
    # establish connection
    cursor = connection.cursor()

    # extracting information from query params
    orderId = event['body']['orderId']
    customerId = event['body']['customerId']
    beerUpc = event['body']['beerUpc']
    quantity = event['body']['quantity']
    date = event['body']['date']
    time = event['body']['time']

    print(orderId)
    print(customerId)
    print(beerUpc)
    print(quantity)
    print(date)
    print(time)

    # fetching information of the beer from database
    statement = "insert into customer_order (beer_upc, customer_id, date, order_id, quantity, time) values ({0}, {1}, {2}, {3}, {4}, {5})".format(beerUpc, customerId, date, orderId, quantity, time)
    print(statement)
    cursor.execute(statement)
    connection.commit()
    rows = cursor.fetchall()

    # fetching information of the beer from database
    statement = "select * from customer_order where order_id=" + orderId
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
        beer_inventory.append(construct_json_customer_order(row[0], row[1], row[2], row[3], row[4], row[5]))

    if len(beer_inventory) == 1:
        response['body'] = json.dumps(beer_inventory[0])
    else:
        response['body'] = json.dumps(beer_inventory) 

    # create response object
    return response
