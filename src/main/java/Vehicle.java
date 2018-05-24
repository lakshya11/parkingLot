abstract class Vehicle {
    String registrtationNumber;
    String color;
    Spot parkedIn;


    public void setParkedIn(Spot spot) {
        parkedIn = spot;
    }

    public String getColor(){
        return color;
    }
}
