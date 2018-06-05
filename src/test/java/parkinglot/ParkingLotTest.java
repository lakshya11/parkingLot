package parkinglot;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParkingLotTest {

    static ParkingLot parkingLot;

    @BeforeClass
    public static void setUp() throws Exception {
        parkingLot = new ParkingLot();
        parkingLot.createParkingSlots(2);
    }

    @After
    public void tearDown() throws Exception {
    }

    /*
        Test case to place vehicles
     */
    @Test
    public void placeVehicle() {
        String registrationNumber1 = "KA-01-HH-1234";
        String registrationNumber2 = "KA-01-HH-9999";
        parkingLot.placeVehicle(new Car(registrationNumber1,"White"));
        parkingLot.placeVehicle(new Car(registrationNumber2,"Black"));
        assertEquals(2,parkingLot.parkingLot.size());

    }

    /*
        Test case to place a vehicle in full parking lot
     */
    @Test
    public void placeVehicleFull() {
        String registrationNumber = "KA-03-HH-2035";
        Spot spot = parkingLot.placeVehicle(new Car(registrationNumber,"Black"));
        assertNull(null,spot);
        assertEquals(0,parkingLot.availableSpots.size());
    }

    /*
        Test case to try to remove invalid spot
     */
    @Test
    public void removeVehicleInvalidSpot() {
        Boolean removedVehicle = parkingLot.removeVehicle(new Spot(3));
        assertEquals(false,removedVehicle);

    }

    /*
        Test case to search vehicle by non existing color
     */
    @Test
    public void fetchVehiclesByInvalidColour() {
        String color = "Red";
        List<String> vehiclesRegistrationNumbers = parkingLot.fetchVehiclesByColour(color);
        assertNull(vehiclesRegistrationNumbers);
    }


    @Test
    public void fetchVehicleSlotsByInvalidRegistraionNumber() {

    }
}