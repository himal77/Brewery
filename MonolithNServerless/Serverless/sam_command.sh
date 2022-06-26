#!/bin/bash

ACTION=$1
DEPLOY="deploy"
CLEAN="clean"
LAMBDA_NAME=$2
GET_BEER_INVENTORY="getbeerinventory"
CHANGE_BEER_QUANTITY="changebeerquantity"
ALL="all"
APPLICATION=$3

function cd_twice() {
    cd ..
    cd ..
}

function createzip_beerinventory_changebeerquanity() {
    # make a zip of source file and its dependency
    cd BeerInventory/ChangeBeerQuantity
    zip -r ChangeBeerQuantity.zip *
    cd_twice
}

function createzip_beerinventory_getbeerinventory() {
   # make a zip of source file and its dependency
    cd BeerInventory/GetBeerInventory
    zip -r GetBeerInventory.zip *
    cd_twice
}

function clear_zips() {
    # clear zip after deploying
    cd BeerInventory/GetBeerInventory
    rm GetBeerInventory.zip
    cd_twice

    # clear zip after deploying
    cd BeerInventory/ChangeBeerQuantity
    rm ChangeBeerQuantity.zip
    cd_twice
}

function deploy_beerinventory_getbeerinventory() {
    sam deploy --s3-bucket himalpuri --template template_getbeerinventory.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_beerinventory_changebeerquantity() {
    sam deploy --s3-bucket himalpuri --template template_changebeerquantity.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

function deploy_all() {
    sam deploy --s3-bucket himalpuri --template template.yaml --stack-name $APPLICATION --capabilities CAPABILITY_IAM
}

if [ $ACTION == $DEPLOY ]
then
    
    # create s3 bucket to deploy the code
    aws s3 mb s3://himalpuri

    if [ $LAMBDA_NAME == $GET_BEER_INVENTORY ]
    then
        # creating get beer inventory zip and deploying
        createzip_beerinventory_getbeerinventory
        deploy_beerinventory_getbeerinventory
    elif [ $LAMBDA_NAME == $CHANGE_BEER_QUANTITY ]
    then
        # creating change beer quantity zip and deploying
        createzip_beerinventory_changebeerquanity
        deploy_beerinventory_changebeerquantity
    elif [ $LAMBDA_NAME == $ALL ]
    then
        # create all the zips of availabel service and deploy
        createzip_beerinventory_getbeerinventory
        createzip_beerinventory_changebeerquanity
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