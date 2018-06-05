package parkinglot;

import java.io.*;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;


/*
The driver class to run the parking lot system
 */
public class Main {

    private final static Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static ParkingLot parkingLot;

    public static void executeCommand(String[] command){
        String searchColor;
        String registrationNumber;
        switch(command[0]){
            case "create_parking_lot":
                Integer size = Integer.parseInt(command[1]);
                parkingLot.setParkingLotSize(size);
                System.out.println("Created a parking lot with "+size+ " slots");
                break;
            case "park":
                registrationNumber = command[1];
                String color = command[2];
                Vehicle vehicle = new Car(registrationNumber,color);  // can use any abstraction(bus,motorcycle,truck)
                Spot spot = parkingLot.placeVehicle(vehicle);
                if(null!=spot) {
                    System.out.println("Allocated slot number: " + spot.getId());
                }
                break;
            case "leave":
                Integer spotId = Integer.parseInt(command[1]);
                Spot spotLocation = new Spot(spotId);
                parkingLot.removeVehicle(spotLocation);
                System.out.println("Slot number "+spotId+" is free");
                break;
            case "status":
                parkingLot.showParkingStatus();
                break;
            case "registration_numbers_for_cars_with_colour":
                searchColor = command[1];
                List<String> registrationNumbers= parkingLot.fetchVehiclesByColour(searchColor);
                if(null!=registrationNumbers) {
                    String result = String.join(",", registrationNumbers);
                    System.out.println(result);
                }else{
                    System.out.println("Not found");
                }
                break;
            case "slot_numbers_for_cars_with_colour":
                searchColor = command[1];
                List<String> vehiclesSlots = parkingLot.fetchVehicleSlotsByColor(searchColor);
                if(null!=vehiclesSlots) {
                    String slotNumbers = String.join(",", vehiclesSlots);
                    System.out.println(slotNumbers);
                }
                else{
                    System.out.println("Not found");
                }
                break;
            case "slot_number_for_registration_number":
                registrationNumber = command[1];
                Integer spotNumber = parkingLot.fetchVehicleSlotsByRegistraionNumber(registrationNumber);
                System.out.println(null!=spotNumber ? spotNumber:"Not found");
                break;
            default:
                //TODO test case for this
                System.out.println("No such command found in the system");

        }

    }

    public static void readFromFile(String fileName){

        InputStream filePath = Main.class.getClassLoader().getResourceAsStream(fileName);
        String line = null;
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(filePath));
            String command;
            while((line = bufferedReader.readLine()) != null) {
                command = line;
                String[] parsed_command = command.split("\\s+");
                executeCommand(parsed_command);
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
        parkingLot = new ParkingLot();
        if(args.length >0 && null!=args[0]){
            readFromFile(args[0]);
        }
        else{
        System.out.println("Give input command to create and run parking lot");
        Scanner in = new Scanner(System.in);
        String command;
        do {
           command = in.nextLine();
            String[] parsed_command = command.split("\\s+");
            executeCommand(parsed_command);
            System.out.println("Type exit to terminate the program");
        }while(!command.equals("exit"));
        System.exit(0);
        }

    }
}
