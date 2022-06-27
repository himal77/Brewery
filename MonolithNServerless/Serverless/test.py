import json
from BeerInventory.ChangeBeerQuantity.ChangeBeerQuantity import handler as change_handler
from BeerInventory.GetBeerInventory.GetBeerInventory import handler as get_handler

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

beer_quantity_change_test("decrease", 4)
beer_quantity_change_test("increase", 7)