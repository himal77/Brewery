## Command used

#### test lambda locally using sam. docker is needed
```
sam local invoke -e ${jsonpath} BeerAvailableFn

```

#### install sql library
```
pip install -t $PWD pymysql
```

#### install postgres library
```
pip install psycopg2-binary -t .
```

### Imp:
The API gateway link can be found in stages in side tab.

### Command to clean s3 bucket and remove cloud formation
From the root folder Serverless which is inside MonolithNServerless
```
bash sam_command.sh clean ${cloudstackname}
```

### Command to deploy
```
bash sam_command.sh deploy ${lambda_name} ${cloudstackname}
```

##Commands
#### clean the s3 and remove the stack named brewery
```
bash sam_command.sh clean brewery
```
#### deploy all the lambdas
```
bash sam_command.sh deploy all brewery
```
#### deploy only getbeerinventory lambda
```
bash sam_command.sh deploy getbeerinventory brewery
```

#### deploy only changebeerquantity lambda
```
bash sam_command.sh deploy changebeerquantity brewery
```


### Possible/faced error and solution
#### API Internal server error for invoking lambda
Path: /beerinventories/getbeer  
This path should be always start with /. Otherwise there will be error during invocation.

#### Postgresql psycopg2 not found which is in import
Instead of installing psycopg2-binary use aws-psycopg2  
```
pip install aws-psycopg2 -t .
```

#### mysql not connecting to the aws
Always use separate name for security group for separate database.
This could lead to blocking of the connection.

#### Value do not change for the new invocation
This may be the reason that the body of the response is global variable. This needs to be empty everytime the response it sent
Either make it local or clear it before returning.

#### The order of the column which the data is returned
The column order in the database lies in the alphabetical order of column name.  
Result will be returned in the same order