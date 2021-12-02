# clip

Java Application for Clip

Basic Java Command line Transaction application

Requirements: Java 8 JRE installed

To build the java project please use gradle build tool (https://gradle.org/)

Note: some .tx files will be created on running directory

This application manages 4 basic operations using the following command:

java -jar clip-assesment-0.1.0.jar

The allowed operations are:

ADD TRANSACTION

java -jar clip-assesment-0.1.0.jar <user_id> add <transaction_json>

Sample:

java -jar clip-assesment-0.1.0.jar 345 add { “amount”: 1.25, “description”: “Joes Tacos”, “date”:”2018-12-30”, “user_id”: 345 } 

Note: for the add command, opening and closing double quotes must be used (“ ”) , straight double quotes may be used preceded by a escape character ej: \"



SHOW TRANSACTION

java -jar clip-assesment-0.1.0.jar <user_id> <transaction_id>

Sample:

java -jar clip-assesment-0.1.0.jar 345 66106d94-73e2-466c-89e5-65b74cb1d5ca


LIST TRANSACTIONS

java -jar clip-assesment-0.1.0.jar <user_id> list

Sample:

java -jar clip-assesment-0.1.0.jar 345 list


SUM TRANSACTIONS

java -jar clip-assesment-0.1.0.jar <user_id> sum

Sample:

java -jar clip-assesment-0.1.0.jar 345 sum


Sample Output:


 :: Spring Boot ::        (v2.0.3.RELEASE)

Starting TransactionApplication on MacBook-Pro.local with PID 74813 (/Users/XXX/git/clip/Clip/build/libs/clip-assesment-0.1.0.jar started by XXX in /Users/XXX/git/clip/Clip

No active profile set, falling back to default profiles: default

Started TransactionApplication in 1.335 seconds (JVM running for 1.901)

{"user_id":"345","sum":7.42}
