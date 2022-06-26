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