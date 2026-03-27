import java.time.LocalDate;
import java.time.LocalDateTime;

public class ActiveRental {

    private String bikeID;
    private String userEmail;
    private LocalDateTime tripStartTime;

    public ActiveRental(String bikeID, String userEmail, LocalDateTime tripStartTime){
        this.bikeID = bikeID;
        this.userEmail = userEmail;
        this.tripStartTime = tripStartTime;
    }

    public ActiveRental(){

    }

    public String toString(){
        return "The bikeID is:" + bikeID +
               "The user's email is:" + userEmail +
               "The trip start time is:" + tripStartTime;
    }





    public String getBikeID() {
        return bikeID;
    }

    public void setBikeID(String bikeID) {
        this.bikeID = bikeID;
    }

    public LocalDateTime getTripStartTime() {
        return tripStartTime;
    }

    public void setTripStartTime(LocalDateTime tripStartTime) {
        this.tripStartTime = tripStartTime;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


}
