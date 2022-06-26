#!/bin/bash

if [ $1 == "deploy" ]
then
    # make a zip of source file and its dependency
    cd BeerInventory
    zip GetBeerInInventory.zip GetBeerInInventory.py psycopg2 aws_psycopg2-1.2.1.dist-info

    # come back to serverless root folder
    cd ..

    # create s3 bucket to deploy the code
    aws s3 mb s3://himalpuri

    # deploy via the sam
    sam deploy --s3-bucket himalpuri --stack-name $2 --capabilities CAPABILITY_IAM

    # clear zip after deploying
    cd BeerInventory
    rm GetBeerInInventory.zip

elif [ $1 == "clean" ]
then
    aws s3 rm s3://himalpuri --recursive
    aws cloudformation delete-stack --stack-name $2
else
    echo "wrong command entered, correct commands: deploy, clean"
fi