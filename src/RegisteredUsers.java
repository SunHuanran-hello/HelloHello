public class RegisteredUsers {

    public String fullName;
    public String emailAddress;
    public String dateOfBrith;
    public long cardNumber;
    public String cardProvider;
    public String cardExpiryDate;
    public int cvv;
    public String userType;
    private String[] lastThreeTrips;

    public RegisteredUsers(String fullName,String emailAddress,String dateOfBrith,long cardNumber,String cardProvider,String cardExpiryDate,int cvv,String userType,String[] lastThreeTrips){
        this.fullName = fullName;
        this.emailAddress = emailAddress;
        this.dateOfBrith = dateOfBrith;
        this.cardNumber = cardNumber;
        this.cardProvider = cardProvider;
        this.cardExpiryDate = cardExpiryDate;
        this.cvv = cvv;
        this.userType = userType;
        this.lastThreeTrips = lastThreeTrips;

    }

    public String toString() {

        return "RegisteredUsers{" +
                "fullName='" + fullName + "\n" +
                ", emailAddress='" + emailAddress + "\n" +
                ", cardNumber=" + cardNumber + "\n" +
                ", cardProvider='" + cardProvider + "\n" +
                ", cardExpiryDate='" + cardExpiryDate + "\n" +
                ", cvv=" + cvv + "\n" +
                ", userType='" + userType + "\n" +
                ", lastThreeTrips=" + lastThreeTrips.toString() +
                '}';
    }






    public String[] getLastThreeTrips() {
        return lastThreeTrips;
    }

    public void setLastThreeTrips(String[] lastThreeTrips) {
        this.lastThreeTrips = lastThreeTrips;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardProvider() {
        return cardProvider;
    }

    public void setCardProvider(String cardProvider) {
        this.cardProvider = cardProvider;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(long cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getDateOfBrith() {
        return dateOfBrith;
    }

    public void setDateOfBrith(String dateOfBrith) {
        this.dateOfBrith = dateOfBrith;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
