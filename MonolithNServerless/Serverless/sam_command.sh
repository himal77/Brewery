#!/bin/bash

ACTION=$1
DEPLOY="deploy"
CLEAN="clean"
LAMBDA_NAME=$2
GET_BEER_INVENTORY="getbeerinventory"
CHANGE_BEER_QUANTITY="changebeerquantity"
BEER_QUANTITY_STATUS="beerquantitystatus"
SAVE_CUSTOMER_ORDER="savecustomerorder"
SAVE_BREW_ORDER="savebreworder"
ALL="all"
APPLICATION=$3

function cd_back_twice() {
    cd ..
    cd ..
}

function createzip_beerinventory_beerquantitystatus() {
    cd BeerInventory/BeerQuantityStatus
    zip -r BeerQuantityStatus.zip *
    cd_back_twice
}

function createzip_beerinventory_changebeerquanity() {
    cd BeerInventory/ChangeBeerQuantity
    zip -r ChangeBeerQuantity.zip *
    cd_back_twice
}

function createzip_beerinventory_getbeerinventory() {
    cd BeerInventory/GetBeerInventory
    zip -r GetBeerInventory.zip *
    cd_back_twice
}

function createzip_breworder_savebreworder() {
    cd BrewOrder/SaveBrewOrder
    zip -r SaveBrewOrder.zip *
    cd_back_twice
}

function createzip_customerorder_savecustomerorder() {
    cd CustomerOrder/SaveCustomerOrder
    zip -r SaveCustomerOrder.zip *
    cd_back_twice
}

function create_all_zips() {
    createzip_beerinventory_beerquantitystatus
    createzip_beerinventory_changebeerquanity
    createzip_beerinventory_getbeerinventory
    createzip_breworder_savebreworder
    createzip_customerorder_savecustomerorder
}

function clear_zips() {
    cd BeerInventory/BeerQuantityStatus
    rm BeerQuantityStatus.zip
    cd_back_twice

    cd BeerInventory/ChangeBeerQuantity
    rm ChangeBeerQuantity.zip
    cd_back_twice

    cd BeerInventory/GetBeerInventory
    rm GetBeerInventory.zip
    cd_back_twice

    cd BrewOrder/SaveBrewOrder
    rm SaveBrewOrder.zip
    cd_back_twice

    cd CustomerOrder/SaveCustomerOrder
    rm SaveCustomerOrder.zip
    cd_back_twice
}

function deploy_beerinventory_beerquantitystatus() {
    sam deploy --s3-bucket himalpuri --template template_beerquantitystatus.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_beerinventory_changebeerquantity() {
    sam deploy --s3-bucket himalpuri --template template_changebeerquantity.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_beerinventory_getbeerinventory() {
    sam deploy --s3-bucket himalpuri --template template_getbeerinventory.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_breworder_savebreworder() {
    sam deploy --s3-bucket himalpuri --template template_savebreworder.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_customerorder_savecustomerorder() {
    sam deploy --s3-bucket himalpuri --template template_savecustomerorder.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_all() {
    sam deploy --s3-bucket himalpuri --template template.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

if [ $ACTION == $DEPLOY ]
then
    
    # create s3 bucket to deploy the code
    aws s3 mb s3://himalpuri

    if [ $LAMBDA_NAME == $BEER_QUANTITY_STATUS ]
    then
        createzip_beerinventory_beerquantitystatus
        deploy_beerinventory_beerquantitystatus
    elif [ $LAMBDA_NAME == $CHANGE_BEER_QUANTITY ]
    then
        createzip_beerinventory_changebeerquanity
        deploy_beerinventory_changebeerquantity
    elif [ $LAMBDA_NAME == $GET_BEER_INVENTORY ]
    then
        createzip_beerinventory_getbeerinventory
        deploy_beerinventory_getbeerinventory
    elif [ $LAMBDA_NAME == $SAVE_BREW_ORDER ]
    then
        createzip_breworder_savebreworder
        deploy_breworder_savebreworder
    elif [ $LAMBDA_NAME == $SAVE_CUSTOMER_ORDER ]
    then
        createzip_customerorder_savecustomerorder
        deploy_customerorder_savecustomerorder
    elif [ $LAMBDA_NAME == $ALL ]
    then
        # create all the zips of availabel service and deploy
        create_all_zips
        deploy_all
    else
        echo "Enter the name of lambda"
    fi

    # clearing all the zips
    clear_zips

# cleaning the s3 bucket and stack
elif [ $ACTION == $CLEAN ]
then
    aws s3 rm s3://himalpuri --recursive
    aws cloudformation delete-stack --stack-name $2
else
    echo "wrong command entered, correct commands: deploy, clean"
fi