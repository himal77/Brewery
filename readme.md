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
bash sam_command.sh deploy ${cloudstackname}
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