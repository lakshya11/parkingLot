# parkingLot
Design solution to parking lot problem 

Check current permission on executable parking_lot.sh
->ls -l parking_lot.sh

To give executable permission to parking_lot.sh
->chmod +x parking_lot.sh 

To run in mode of reading input from file.The file "file_inputs" is bo present in 
/parkingLot/src/main/resources/file_inputs.txt 
->path/to/parkingLot$ ./parking_lot.sh file_inputs.txt


To run in interactive mode
->path/to/parkingLot$./parking_lot.sh
Type "exit" to exit the interactive mode of running the program

Highlights and Extensions
Data structure used : stack and hashmap
Extensions: The problem can have extension in terms sizes of vehicle such as small(bicycle), medium(cars), large(truck), xtra-large(buses).
Assumption:Currently only ground floor parking is taken into consideration but the design can have extensions to incorporate multi level parking also.
