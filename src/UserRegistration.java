import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;
import java.util.Scanner;

public class UserRegistration {
    public final double VIP_DISCOUNT_UNDER_18_BIRTHDAY = 25.0;
    public final double VIP_DISCOUNT_UNDER_18 = 20.0;
    public final double VIP_BASE_FEE = 100.0;

    public String fullName;
    public String emailAddress;
    public String dateOfBrith;
    public long cardNumber;
    public String cardProvider;
    public String cardExpiryDate;
    public int cvv;
    public String userType;
    public double feeToCharge;
    public boolean emailValid;
    public boolean minorAndBirthday;
    public boolean minor;
    public boolean ageValid;
    public boolean cardNumberValid;
    public boolean cardStillValid;
    public boolean validCVV;

    private Scanner scanner;

    public UserRegistration(){
        this.scanner = new Scanner(System.in);

    }

    public void registration(){
        System.out.println("Welcome to the ERyder Registration.");
        System.out.println("Here are your two options:");
        System.out.println("1. Register as a Regular User");
        System.out.println("2. Register as a VIP User");
        System.out.println("Please enter your choice (1 or 2):");

        String choice = scanner.nextLine();

        if (Objects.equals(choice, "1")){
            userType = "Regular User";
        }else{
            userType = "VIP User";
        }

        System.out.println("Please input your full name.");
        fullName = scanner.nextLine();

        System.out.println("Please input your email address.");
        emailAddress = scanner.nextLine();
        emailValid = analyseEmail(emailAddress);

        System.out.println("Please input your birthday(in the form of YYYY-MM-DD).");
        dateOfBrith = scanner.nextLine();
        LocalDate dob = LocalDate.parse(dateOfBrith);

        ageValid = analyseAge(dob);

        System.out.println("Please input your card number(only for Visa, MasterCard and American Express).");
        cardNumber = Long.parseLong(scanner.nextLine());
        cardNumberValid = analyseCardNumber(cardNumber);

        System.out.println("Please input your card deadline.");
        cardExpiryDate = scanner.nextLine();
        cardStillValid = analyseCardExpiryDate(cardExpiryDate);

        System.out.println("Please input your CVV.");
        cvv = Integer.parseInt(scanner.nextLine());
        validCVV = analyseCVV(cvv);

        finalCheckpoint();

    }

    private boolean analyseEmail(String emailAddress){
        if (emailAddress.contains("@") && emailAddress.contains(".")){
            System.out.println("Email is valid");
            return true;
        }else{
            System.out.println("Invalid email address. Going back to the start of the registration.");
            return false;
        }
    }

    private boolean analyseAge(LocalDate dob){
        LocalDate currentDate = LocalDate.now();
        Period period = Period.between(dob,currentDate);
        int age = period.getYears();
        boolean isBirthday = (dob.getMonth() == currentDate.getMonth() && dob.getDayOfMonth() == currentDate.getDayOfMonth());
        if (Objects.equals(userType, "1")){
            if (isBirthday && age > 12 && age < 18){
                System.out.println("Happy Birthday!\n" +
                        "You get 25% discount on the VIP subscription fee for being born today and being under 18!");
                minorAndBirthday = true;
            } else if (!isBirthday && age <= 18 && age > 12) {
                System.out.println("You get 20% discount on the VIP subscription fee for being under 18!");
                minor = true;
            }
        }

        if (age < 12 || age > 120){
            System.out.println("Looks like you are either too young or already dead. Sorry, you can’t be our user. Have a nice day");
            return false;
        }

        return true;
    }

    private boolean analyseCardNumber(Long cardNumber){
        String cardNumStr = String.valueOf(cardNumber);
        String firstTwoStr = cardNumStr.substring(0,2);
        int firstTwoDigits = Integer.parseInt(firstTwoStr);
        String firstFourStr = cardNumStr.substring(0,4);
        int firstFourDigits = Integer.parseInt(firstFourStr);

        if ((cardNumStr.length() == 13 || cardNumStr.length() == 15) && cardNumStr.startsWith("4")){
            cardProvider = "VISA";
            return true;

        }else if ((cardNumStr.length() == 16 && (firstTwoDigits >= 51 && firstTwoDigits <= 55)) || (firstFourDigits >= 2221 && firstFourDigits <= 2720)){
            cardProvider = "MasterCard";
            return true;
        }else if (cardNumStr.length() == 15 && (cardNumStr.startsWith("34") || cardNumStr.startsWith("37"))){
            cardProvider = "American Express";
            return true;
        }else {
            System.out.println("Sorry, but we accept only VISA, MasterCard, or American Express cards. Please try again with a valid card.\n" +
                    "Going back to the start of the registration. ");
            registration();
            return false;
        }

    }

    private boolean analyseCardExpiryDate(String cardExpiryDate){
        String monthStr = cardExpiryDate.substring(0,2);
        String yearStr = cardExpiryDate.substring(3,5);
        int month = Integer.parseInt(monthStr);
        int year = Integer.parseInt(yearStr) + 2000;

        LocalDate currentDate = LocalDate.now();
        int currentYear = currentDate.getYear();
        int currentMonth = currentDate.getMonthValue();

        if (year > currentYear || (year == currentYear && month >= currentMonth)){
            System.out.println("The card is still valid");
            return true;
        }else {
            System.out.println("Sorry, your card has expired. Please use a different card.\n" +
                    "Going back to the start fo the registration process…");
            registration();
            return false;
        }
    }

    private boolean analyseCVV(int cvv){
        String cvvStr = String.valueOf(cvv);
        boolean isValid = false;

        if (Objects.equals(cardProvider, "American Express") && cvvStr.length() == 4){
            isValid = true;
        } else if ((Objects.equals(cardProvider, "VISA")) || Objects.equals(cardProvider, "MasterCard") && cvvStr.length() == 3) {
            isValid = true;
        }

        if (isValid){
            System.out.println("Card CVV is valid.");
            return true;
        }else {
            System.out.println("Invalid CVV for the given card.\n" +
                    "Going back to the start of the registration process.");
            registration();
            return false;
        }
    }

    private void finalCheckpoint(){
        if (emailValid && ageValid && cardNumberValid && cardStillValid && validCVV){
            chargeFees();
        }else {
            System.out.println("Sorry, your registration was unsuccessful due to the following reason(s)");
            if (!emailValid){
                System.out.println("Invalid email address");
            }
            if (!ageValid) {
                System.out.println("Invalid age");
            }
            if (!cardNumberValid){
                System.out.println("Invalid card number");
            }
            if (!cardStillValid){
                System.out.println("Card has expire");
            }
            if (!validCVV){
                System.out.println("Invalid CVV");
            }

            System.out.println("Going back to the start of the registration process.");
        }
    }

    private void chargeFees(){
        if (minorAndBirthday){
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18_BIRTHDAY / 100);
        }else if (minor){
            feeToCharge = VIP_BASE_FEE * (1 - VIP_DISCOUNT_UNDER_18 / 100);
        }else {
            feeToCharge = VIP_BASE_FEE;
        }

        String cardNumStr = String.valueOf(cardNumber);
        String cardLastFour = cardNumStr.substring(cardNumStr.length() - 4);

        System.out.println("Thank you for your payment. \n" +
                "A fee of " + feeToCharge + " has been charged to your card ending with " + cardLastFour);
    }

    public String toString(){
        String cardNumStr = String.valueOf(cardNumber);
        String cardLastFour = cardNumStr.substring(cardNumStr.length() - 4);
        String censoredPart = cardNumStr.substring(0,cardNumStr.length() - 4);
        String censoredNumber = censoredPart + cardLastFour;

        StringBuilder sb = new StringBuilder();
        sb.append("Registration successful! Here are your details:");
        sb.append("User Type: ").append(userType);
        sb.append("Full Name: ").append(fullName);
        sb.append("Email Address: ").append(emailAddress);
        sb.append("Date of Birth: ").append(dateOfBrith);
        sb.append("Card Number: ").append(censoredNumber);
        sb.append("Card Provider: ").append(cardProvider);
        sb.append("Card Expiry Date: ").append(cardExpiryDate);

        return sb.toString();
    }

}


