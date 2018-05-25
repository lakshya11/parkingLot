public class Spot {
    Integer id;
    Integer level;              //For multistorey building
    String status;              //to tell whether a spot is free or occupied
    /**
     *Different sizes of vehicle(small,medium,large)
     */
//    enum size{
//        small,medium,large;
//    };

    public Spot(Integer spotId){
        this.id = spotId;
        this.level = 0; //ground parking
        this.status = "free";
    }

    public Integer getId(){
        return id;
    }

    public void setStatus(String status){
        this.status=status;
    }



}
