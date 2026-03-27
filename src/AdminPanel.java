import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class AdminPanel {
    List<RegisteredUsers> registeredUsers = new ArrayList<>();

    public void  userManagementOptions(){
        System.out.println("Welcome to E-Ryder Admininstrator Panel.");
        System.out.println("What do you want to do?");
        System.out.println("1. Add New Users");
        System.out.println("2. View Registered Users");
        System.out.println("3. Remove Registered Users");
        System.out.println("4. Update Registered Users");
        System.out.println("5. Demo the Bike Rental System");
        System.out.println("6. EXIT");

        Scanner scanner = new Scanner(System.in);

        String choice = scanner.nextLine();

        switch (choice){
            case "1":
                addNewUsers();
                break;
            case "2":
                viewRegisteredUsers();
                break;
            case "3":
                removeRegisteredUsers();
                break;
            case "4":
                updateRegisteredUsers();
                break;
            case "5":
                BikeRental bikeRental = new BikeRental();
                bikeRental.simulateApplicationInput();
                break;
            case "6":
                return;
            default:
                System.out.println("Invalid choice. Please try again.");
        }

    }

    private void addNewUsers(){
        String fullName;
        String emailAddress;
        String dateOfBrith;
        long cardNumber;
        String cardProvider = "";
        String cardExpiryDate;
        int cvv;
        String userType = "";
        String[] lastThreeTrips = new String[3];

        Scanner scanner = new Scanner(System.in);

        System.out.println("How many users you want to add?");
        int numberNeedAdd = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < numberNeedAdd; i ++){
            System.out.println("Please input your full name.");
            fullName = scanner.nextLine();

            System.out.println("Please input your email address.");
            emailAddress = scanner.nextLine();

            System.out.println("Please input your birthday(in the form of YYYY-MM-DD).");
            dateOfBrith = scanner.nextLine();
            LocalDate dob = LocalDate.parse(dateOfBrith);

            System.out.println("Please input your card number(only for Visa, MasterCard and American Express).");
            cardNumber = Long.parseLong(scanner.nextLine());

            System.out.println("Please input your card deadline.");
            cardExpiryDate = scanner.nextLine();

            System.out.println("Please input your CVV.");
            cvv = Integer.parseInt(scanner.nextLine());

            System.out.println("Please input the last three trips.");

            for (int j = 0; j < 3; j ++){
                System.out.println("Input the date (YYYY-MM-DD) :");
                String tripDate = scanner.nextLine();

                System.out.println("Input the source and the destination:");
                String route = scanner.nextLine();

                System.out.println("Input the fee:");
                double tripFee = Double.parseDouble(scanner.nextLine());

                System.out.println("Input your feedback (can null) :");
                String feedback = scanner.nextLine();
                if (feedback.isEmpty()){
                    feedback = "NULL";
                }

                StringBuilder tripInfo = new StringBuilder();
                tripInfo.append("Date:").append(tripDate)
                        .append("Route:").append(route)
                        .append("Fee:").append(tripFee)
                        .append("Feedback:").append(feedback);

                lastThreeTrips[j] = tripInfo.toString();

            }

            RegisteredUsers user = new RegisteredUsers(fullName, emailAddress, dateOfBrith, cardNumber, cardExpiryDate, cardProvider, cvv, userType, lastThreeTrips);

            registeredUsers.add(user);

        }


    }



    private void viewRegisteredUsers(){
        if (registeredUsers.isEmpty()){
            System.out.println("No registered users to display");
        }else {
            for (int i = 0; i < registeredUsers.size(); i ++){
                System.out.println(registeredUsers.get(i));
            }
        }
    }



    private void removeRegisteredUsers(){
        Scanner scanner = new Scanner(System.in);

        if (registeredUsers.isEmpty()){
            System.out.println("No registered users to remove");
        }else{
            String emailToRemove = scanner.nextLine();
            boolean found = false;
            Iterator<RegisteredUsers> iterator = registeredUsers.iterator();

            while (iterator.hasNext()){
                RegisteredUsers user = iterator.next();

                if (user.getEmailAddress().equals(emailToRemove)){
                   found = true;
                   iterator.remove();
                   break;
                }
            }

            if (!found){
                System.out.println("No user found with this email address");
            }
        }
    }

    private void updateRegisteredUsers(){
        boolean found = false;
        Scanner scanner = new Scanner(System.in);
        String emailToUpdate = scanner.nextLine();
        RegisteredUsers userToUpdate = null;

        for (RegisteredUsers user : registeredUsers){
            if (user.getEmailAddress().equals(emailToUpdate)){
                userToUpdate = user;

            }else {
                System.out.println("No user found with this email address");
                userToUpdate = null;
            }
        }

        if (userToUpdate == null){
            System.out.println("No registered users to remove");
            return;
        }

        System.out.println("Type new full name(Press ENTER for no change):");
        String newFullName = scanner.nextLine();
        if (!newFullName.isEmpty()){
            userToUpdate.setFullName(newFullName);
        }

        System.out.println("Type new card number(Enter '0' for no change):");
        String newCardNumberInput = scanner.nextLine();
        if (!newCardNumberInput.isEmpty() && !newCardNumberInput.equals("0")){
            long newCardNumber = Long.parseLong(newCardNumberInput);
            userToUpdate.setCardNumber(newCardNumber);
        }


    }

}
