# springBoot-elasticSearch #springboot #elasticsearch
Starting project for developing searches using spring boot and elasticsearch. 

1. Compile the project with mvn clean install
2. Run docker-compose up, 2 containers will be created: shaharcode/elastic:latest and docker.elastic.co/elasticsearch/elasticsearch:7.6.2

Then you can run the following queries:


Query by keywords

localhost:9000/items/tags?tags=<value1>%2B<value2>&page=0&size=4

curl -X GET "localhost:9200/_search?pretty" -H 'Content-Type: application/json' -d'
{
"query": {
  "bool" : {
    "should" : [
      {
        "term" : {
          "tags" : {
            "value" : "<value1>",
            "boost" : 1.0
          }
        }
      },
      {
        "term" : {
          "tags" : {
            "value" : "<value2",
            "boost" : 1.0
          }
        }
      }
    ],
    "adjust_pure_negative" : true,
    "boost" : 1.0
  }
}
}
'
              
              
Query by title and keywords

localhost:9000/items/tags/title?tags=<value1>%2B<value2>&page=0&size=4

