#!/bin/sh
mvn clean install -DskipTests
javac src/main/java/Main.java
cd src/main/java
if [ -z "$1" ]
  then
    echo "No argument supplied"
    #gnome-terminal
    java Main
else
    java Main $1
fi
