//import org.apache.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


/*
The driver class to run the parking lot system
 */
public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void readFromFile(String fileName){

        URL filePath = Main.class.getClassLoader().getResource(fileName);
        System.out.println("File path : "+filePath);
        String path = filePath.getPath();

        //String fileName = path.substring(path.lastIndexOf('/') + 1);
        //System.out.println(fileName);
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(path);


            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" +
                            fileName + "'");
            System.out.println(ex);
        }
        catch(IOException ex) {
            System.out.println(ex);
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }


    public static void main(String args[]){
        if(args.length >0 && null!=args[0]){
            System.out.println(args[0]);
            readFromFile(args[0]);
        }
        else{
        System.out.println("Give input command to create and run parking lot");
        Scanner in = new Scanner(System.in);
        String command;
        ParkingLot parkingLot = new ParkingLot();
        do {

           command = in.nextLine();
            String[] parsed_command = command.split("\\s+");

            switch(parsed_command[0]){
                case "create_parking_lot":
                    Integer size = Integer.parseInt(parsed_command[1]);
                    parkingLot.setParkingLotSize(size);
                    System.out.println("Created a parking lot with "+size+ " slots");
                    break;
                case "park":
                    String registrationNumber = parsed_command[1];
                    String color = parsed_command[2];
                    Vehicle vehicle = new Car(registrationNumber,color);  // can use any abstraction(bus,motorcycle,truck)
                    Spot spot = parkingLot.placeVehicle(vehicle);
                    if(null!=spot) {
                        System.out.println("Allocated slot number: " + spot.getId());
                    }
                    break;
                case "leave":
                    Integer spotId = Integer.parseInt(parsed_command[1]);
                    Spot spotLocation = new Spot(spotId);
                    parkingLot.removeVehicle(spotLocation);
                    System.out.println("Slot number "+spotId+" is free");
                    break;
                case "status":
                    parkingLot.showParkingStatus();
                    break;
                case "registration_numbers_for_cars_with_colour":
                    String searchColor = parsed_command[1];
                    List<String> registrationNumbers= parkingLot.fetchVehiclesByColour(searchColor);
//                    System.out.println(coloredVehicles.stream()
//                            .map(number -> String.valueOf(number))
//                            .collect(toStringJoiner(", ")));
                    StringBuilder result = new StringBuilder();

                    for(String registrationNo : registrationNumbers){
                        result.append(registrationNo);
                        result.append(',');
                    }
                    System.out.println(result);
                    break;
                default:
                    //test case for this
                    System.out.println("No such command found in the system");




            }
            System.out.println("Type exit to terminate the program");
        }while(!command.equals("exit"));
        System.exit(0);
        }

    }
}
