# spring-boot-spring-kafka
Integration of a Spring Boot application with Apache Kafka, sending and consuming messages from the appliction.

[<img height="400px" width="550px" alt="Spring Boot with Kafka diagram" src="https://github.com/dmullandev/spring-boot-spring-kafka/blob/main/docs/spring-boot-kafka-diagrams_v2.PNG?raw=true"/>](https://app.diagrams.net//)
</br>

### Where to start
**Clone Repo**\
To start with clone this entire repo - then build the separate projects by their dependency hierarchy.

**Installation - Kafka + Zookeeper**\
Local install is needed of both Kafka + Zookeeper (Kafka 3.4+ contains packaged zookeeper, separate install not needed)
See link: https://kafka.apache.org/quickstart

Post install - start using files 1) **zookeeper-server-start** 2) **kafka-server-start**

**Start producer App**\
Using IDE menu or specifying maven goal '**spring-boot:run**' to start the producer.

**Postman**\
Using postman:
1) Set HTTP POST request: http://localhost:8099/api/v1/businessobjects
2) Set body as JSON with following format:
```json
    {
        "businessObjectId" : 999,
        "basicBusinessObjectInformation" : {
            "objectType" : "KAFKA_TYPE",
            "objectDescription" : "Object for relaying kafka object information"
        }
    }
```

### Producers
**- Timestamp Producer**\
This producer sends a string representation of the current timestamp to the timestamp topic.

**- BusinessObject Producer**\
This producer sends a custom java object to the businessobject topic as a result of a REST http POST call with business information. REST handling by the class 'KafkaProducerController'.

**- Wikimedia Producer**\
This producer sends a string to the wikimedia topic. The string of an event with the event source being the wikimedia recent changes endpoint, this was accomplished by using the OkHTTP library.
### Consumers
**- Timestamp Consumer**\
This is a basic consumer listening on the timestamp topic and receives the string representation of the timestamp at the time of sending from the producer.

**- Wikimedia Consumer**\
This consumer listens on the wikimedia topic. When the event string is consumed a custom java object 'WikimediaData' is created and the event is set as one of the fields. This object is then saved into a MySQL database using Spring JPA.
