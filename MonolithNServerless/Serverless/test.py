import json
import uuid

from BeerInventory.ChangeBeerQuantity.ChangeBeerQuantity import handler as change_handler
from BeerInventory.GetBeerInventory.GetBeerInventory import handler as get_handler
from BeerInventory.BeerQuantityStatus.BeerQuantityStatus import handler as status_handler
from CustomerOrder.SaveCustomerOrder.SaveCustomerOrder import handler as customer_order_handler
from BrewOrder.SaveBrewOrder.SaveBrewOrder import handler as brew_order_handler

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
    queryStringParameters = {"beerUpc": BEER_UPC}
    events = {"queryStringParameters": queryStringParameters}
    response = status_handler(events, "")
    body = json.loads(response['body'])
    print(body)
    print("No Error TEST STATUS PASSED")

def test_customer_order_should_not_get_exception():
    # constructing the query parameter
    body = {"beerUpc": "123", "customerId": "111", "quantity": 15}
    events = {"body": json.dumps(body)}
    response = customer_order_handler(events, "")
    print(json.loads(response['body']))
    print("No Error CUSTOMER ORDER PASSED")

def test_brew_order_should_not_get_exception():
    # constructing the query parameter
    body = {"beerUpc": "123", "breweryId": "111", "quantity": 15}
    events = {"body": json.dumps(body)}
    response = brew_order_handler(events, "")
    print(json.loads(response['body']))
    print("No Error CUSTOMER ORDER PASSED")

beer_quantity_change_test("decrease", 400)
beer_quantity_change_test("increase", 7)
test_status_should_not_have_exception()
test_customer_order_should_not_get_exception()
test_brew_order_should_not_get_exception()



''''
Test json for the step function


{
   "OrderType":"CUSTOMER_ORDER",
   "customerId":"123",
   "beerUpc":"0631234200036",
   "quantity":999
}
{
   "OrderType":"BREW_ORDER",
   "breweryId":"brewery1",
   "beerUpc":"0631234200036",
   "quantity":12
}
'''
{
    \"OrderType\":\"CUSTOMER_ORDER\",
    \"customerId":\"123\",
    \"beerUpc":\"0631234200036\",
    \"quantity\":999
}