import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import static sun.security.util.KeyUtil.validate;

public class BikeRental {
    private boolean isRegisteredUser;
    private String emailAddress;
    private String location;
    private LocalDateTime tripStartTime;
    private String bikeID;
    private boolean locationValid;

    private UserRegistration userRegistration;
    private ActiveRental activeRental;
    private LinkedList<ActiveRental> activeRentalList;
    private Scanner scanner;

    public BikeRental(){
        this.userRegistration = new UserRegistration();
        this.activeRental = new ActiveRental();
        this.scanner = new Scanner(System.in);
    }

    public void simulateApplicationInput(){

        System.out.println("This is the simulation of the e-bike rental process.");

        System.out.println("State if the uer is a registered user (true/false)");
        isRegisteredUser = Boolean.parseBoolean(scanner.nextLine());


        if (!isRegisteredUser){
            return;
        }

        System.out.println("Enter the email address of the user:");
        emailAddress = scanner.nextLine();

        System.out.println("Enter the location of the bike:");
        location = scanner.nextLine();

        System.out.println("Simulating the analysis of the rental request.");

        String bikeID = analyseRequest(isRegisteredUser,emailAddress,location);

        if (!locationValid){
            return;
        }

        System.out.println("Simulating e-bike reservation…");
        reserveBike(bikeID);

        System.out.println("Displaying the active rentals…");
        viewActiveRentals();

        System.out.println("Simulating the end of the trip…");
        removeTrip(bikeID);

        System.out.println("Displaying the active rentals after trip end…");
        viewActiveRentals();

    }

    private String analyseRequest(boolean isRegisteredUser, String emailAddress, String location){
        if (isRegisteredUser == true){
            System.out.println("Welcome back," + emailAddress + "!");
        }else {
            System.out.println("You’re not our registered user. Please consider registering.");
            userRegistration.registration();
        }

        return validateLocation(location);
    }

    private String validateLocation(String location){
        for (Bike bike:BikeDatabase.bikes){
            if (bike.getLocation().equals(location) && bike.isAvailable()){
                System.out.println("A bike is available at the location you requested.");
                locationValid = true;
                return bike.getBikeID();
            }
        }

        System.out.println("Sorry, no bikes are available at the location you requested. Please try again later.");

        return null;

    }

    private void reserveBike(String bikeID){
        if (bikeID != null){
            for (Bike bike:BikeDatabase.bikes){
                if (bike.getBikeID().equals(bikeID)){
                    tripStartTime = LocalDateTime.now();

                    bike.setAvailable(false);
                    bike.setLastUsedTime(tripStartTime);

                    System.out.println(" Reserving the bike with the " + bikeID + ". Please following the on-screen instructions to locate the bike and start your pleasant journey.");

                    activeRental = new ActiveRental(bikeID, emailAddress, tripStartTime);
                    activeRentalList.add(activeRental);

                }
            }
        }else {
            System.out.println(" Sorry, we’re unable to reserve a bike at this time. Please try again later.");
        }
    }

    private void viewActiveRentals(){
        if (activeRentalList == null){
            System.out.println("No active rentals at the moment.");
        }else {
            for (ActiveRental rental:activeRentalList){
                System.out.println(rental);
            }
        }
    }

    private void removeTrip(String bikeID){
        Iterator<ActiveRental> iterator = activeRentalList.iterator();
        while (iterator.hasNext()) {
            ActiveRental rental = iterator.next();
            if (rental.getBikeID().equals(bikeID)) {
                iterator.remove();
                break;
            }
        }

        for (Bike bike:BikeDatabase.bikes){
            if (bike.getBikeID().equals(bikeID)){
                bike.setAvailable(true);
                bike.setLastUsedTime(LocalDateTime.now());
                break;
            }
        }

    }


}
