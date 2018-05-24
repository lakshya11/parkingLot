import java.util.*;
import java.util.logging.Logger;

public class ParkingLot {

    private final static Logger LOGGER = Logger.getLogger(ParkingLot.class.getName());
    //Identifier in case we use different areas building as parking lot
    Integer zipCode;
    Integer parkingLotSize;
    Stack<Spot> availableSpots;
    HashMap<String,List<Vehicle>> parkingLotByColor;     //later elastic search can be used
    HashMap<Integer,Vehicle> parkingLot;

    public ParkingLot(){
        this.availableSpots = new Stack<Spot>();
        this.parkingLot = new HashMap<Integer,Vehicle>();
        this.parkingLotByColor = new HashMap<String,List<Vehicle>>();
    }


    public Spot placeVehicle(Vehicle vehicle){
        Spot spot=null;
        List <Vehicle> colorVehicleList;
        try {
            if (!this.availableSpots.empty()) {
                try {
                    spot = availableSpots.pop();
                    spot.setStatus("occupied");
                    vehicle.setParkedIn(spot);
                    this.parkingLot.put(spot.getId(),vehicle);
                    if (!parkingLotByColor.containsKey(vehicle.getColor())) {
                        colorVehicleList = new ArrayList<Vehicle>();
                        colorVehicleList.add(vehicle);
                        parkingLotByColor.put(vehicle.getColor(), colorVehicleList);
                    } else {
                        colorVehicleList = parkingLotByColor.get(vehicle.getColor());
                        colorVehicleList.add(vehicle);
                        parkingLotByColor.put(vehicle.getColor(), colorVehicleList);
                    }
                } catch (Exception exp) {
                    //TODO replace with logger looger error
                    exp.printStackTrace();
                }

            } else {
                System.out.println("Parking Lot is full");  // full size->overflow testcases
            }
        }catch (Exception exp){
            //TODO replace with logger looger error
            exp.printStackTrace();
        }
        return spot;
    }

    public void removeVehicle(Spot spot){

        spot.setStatus("free");
        LOGGER.info("Current available spots:- "+this.availableSpots.size());
        availableSpots.push(spot);
        parkingLot.remove(spot.getId());
    }

    public void showParkingStatus(){
        //get filled spots of vehicle
        System.out.println("Slot No.\tRegistration No"+"\t Color");
        for(Map.Entry<Integer,Vehicle> entry : parkingLot.entrySet()){
            System.out.println(entry.getKey()+"\t"+entry.getValue().registrtationNumber+"\t"+entry.getValue().color);
        }
    }
//
//    public List<Vehicle> fetchVehiclesByColour(String searchColor){
//        return List<>
//    }
    public void createParkingSlots(Integer size){
        LOGGER.info("Creating following parking spots:-");
        for(int i=size;i>0;--i){
            this.availableSpots.push(new Spot(i));
        }
        setAvailableSpots(this.availableSpots);
        LOGGER.info("Total size of available spots:"+this.availableSpots.size());
    }


    public Integer getParkingLotSize(){
        return this.parkingLotSize;
    }

    public void setParkingLotSize(Integer size){
        this.parkingLotSize = size;
        createParkingSlots(size);
    }

    public Stack<Spot> getAvailableSpots() {
        return availableSpots;
    }

    public void setAvailableSpots(Stack<Spot> availableSpots) {
        this.availableSpots = availableSpots;
    }
}
