#!/bin/sh
mvn clean install -DskipTests
mvn compile
mvn package
#java -jar target/parkingLot-1.0.jar
if [ -z "$1" ]
  then
    echo "No argument supplied"
    #gnome-terminal
    java -jar target/parkingLot-1.0.jar
else
    java -jar target/parkingLot-1.0.jar $1
fi
