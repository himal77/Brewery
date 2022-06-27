import json
import uuid

from BeerInventory.ChangeBeerQuantity.ChangeBeerQuantity import handler as change_handler
from BeerInventory.GetBeerInventory.GetBeerInventory import handler as get_handler
from BeerInventory.BeerQuantityStatus.BeerQuantityStatus import handler as status_handler
from CustomerOrder.SaveCustomerOrder.SaveCustomerOrder import handler as customer_order_handler

BEER_UPC = "0083783375213"


def changeBeerQuantity(changeCmdType, quantity):
    # constructing the query parameter
    queryStringParameters = {"changeCmdType": changeCmdType,
                             "beerUpc": BEER_UPC, "quantity": quantity}
    events = {"queryStringParameters": queryStringParameters}
    return change_handler(events, "")

def getBeerQuantity():
    # constructing the query parameter
    queryStringParameters = {"beerUpc": BEER_UPC}
    events = {"queryStringParameters": queryStringParameters}
    return get_handler(events, "")

def beer_quantity_change_test(changeCmdType, quantity):
    get_all_response = getBeerQuantity()
    change_beer_quantity = changeBeerQuantity(changeCmdType, quantity)

    old_item = json.loads(get_all_response['body'])
    new_item = json.loads(change_beer_quantity['body'])

    old_quantity = int(old_item['quantityOnHand'])
    new_quantity = int(new_item['quantityOnHand'])
    
    if(old_quantity == new_quantity):
        print("CHANGE BEER QUANTITY TEST FAILED!!!!")
    else:
        print("CHANGE BEER QUANTITY TEST PASSED!!!!")


def test_status_should_not_have_exception():
    try:
        queryStringParameters = {"beerUpc": BEER_UPC}
        events = {"queryStringParameters": queryStringParameters}
        response = status_handler(events, "")
        body = json.loads(response['body'])
        print(body)
        print("No Error TEST STATUS PASSED")
    except:
        print("Error TEST STATUS FAILED")

def test_customer_order_should_not_get_exception():
    # try:
    # constructing the query parameter
    body = {"beerUpc": "123", "customerId": "111", "date": '2022-04-03', "orderId": "fc5d21f6-f653-11ec-b939-0242ac120002", "quantity": 15, "time": None}
    events = {"body": body}
    response = customer_order_handler(events, "")
    print(json.loads(response['body']))
    print("No Error CUSTOMER ORDER PASSED")
    # except:
    #     print("Error CUSTOMER ORDER FAILED")

# beer_quantity_change_test("decrease", 400)
# beer_quantity_change_test("increase", 7)
# test_status_should_not_have_exception()
test_customer_order_should_not_get_exception()