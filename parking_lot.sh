#!/bin/sh
mvn clean install
mvn compile
mvn package
if [ -z "$1" ]
  then
    echo "No argument supplied"
    java -jar target/parkingLot-1.0.jar
else
    java -jar target/parkingLot-1.0.jar $1
fi
