import pymysql
import json
import datetime
import uuid

# temporary configuration value
host = "brewery.c4jdjnkep4td.us-west-2.rds.amazonaws.com"
username = "brewery"
password = "brewery123"
database = "brewery"

# connection
connection = pymysql.connect(
    host=host, user=username, passwd=password, db=database)

# function to convert to json
def construct_json_customer_order(orderId, beerUpc, customerId, date, quantity, time):
    temp_date = "{0}-{1}-{2}".format(date.year, date.month, date.day)
    temp_time = str(time)
    return {"orderId":orderId, "beerUpc": beerUpc, "customerId": customerId, "date": temp_date, "quantity": quantity, "time": temp_time}

def handler(event, context):
    # establish connection
    cursor = connection.cursor()

    # extracting information from query params
    decodedBody = json.loads(event['body'])
    customerId = decodedBody['customerId']
    beerUpc = decodedBody['beerUpc']
    quantity = decodedBody['quantity']

    # constructing current date and time
    e = datetime.datetime.now()
    date = "{0}-{1}-{2}".format(e.year, e.month, e.day)
    time = "{0}:{1}:{2}".format(e.hour, e.minute, e.second)
    orderId = uuid.uuid1()

    # fetching information of the beer from database
    statement = "insert into customer_order (order_id, beer_upc, customer_id, date, quantity, time) values ('{0}', {1}, {2}, '{3}', {4}, '{5}')".format(
        orderId, beerUpc, customerId, date, quantity, time)
    print(statement)
    cursor.execute(statement)
    connection.commit()
    rows = cursor.fetchall()

    # fetching information of the beer from database
    statement = "select * from customer_order where beer_upc='{0}' and time='{1}'".format(beerUpc, time)
    print(statement)
    cursor.execute(statement)
    rows = cursor.fetchall()

    # create the response
    response = {}
    response['statusCode'] = 200
    response['headers'] = {}
    response['headers']['Content-Type'] = 'application/json'

    # body to return
    customer_order = []
    for row in rows:
        customer_order.append(construct_json_customer_order(
            row[0], row[1], row[2], row[3], row[4], row[5]))

    if len(customer_order) == 1:
        response['body'] = json.dumps(customer_order[0])
    else:
        response['body'] = json.dumps(customer_order)

    # create response object
    return response
