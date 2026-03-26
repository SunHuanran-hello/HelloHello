public class ERyder {
    private int bikeId;
    private int batteryLevel;
    private boolean isAvailable;
    private double kmDriven;

    private final String companyName = "ERyder";
    private final double baseFare = 1.0;
    private final double per_minuteFare = 0.5;

    private final String linkedAccount;
    private final String linkedNumber;

    private double totalUsageInTime;
    private double totalFare;


    public ERyder(int bikeId, int batteryLevel, boolean isAvailable, double kmDriven){
        this.bikeId = 1;
        this.batteryLevel = 100;
        this.isAvailable = true;
        this.kmDriven = 0;

        this.linkedAccount = "none";
        this.linkedNumber = "none";

    }

    public ERyder(int bikeId, int batteryLevel, boolean isAvailable, double kmDriven, String linkedAccount, String linkedNumber){
        this.bikeId = bikeId;
        setBatteryLevel(batteryLevel);
        this.isAvailable = isAvailable;
        this.kmDriven = kmDriven;
        this.linkedAccount = linkedAccount;
        this.linkedNumber = linkedNumber;
    }

    public void ride(){
        if (isAvailable && batteryLevel > 0 && batteryLevel <= 100){
            System.out.println("The bike is available.");
        }else System.out.println("The bike is not available.");
    }

    public String getLinkedAccount() {
        return linkedAccount;
    }

    public void printBikeDetail(){
        System.out.println("The company is " + companyName);
        System.out.println("The bikeId is " + bikeId);
        System.out.println("The batteryLevel is " + batteryLevel);
        if (isAvailable){
            System.out.println("The bike is available.");
        }else System.out.println("The bike is not available.");
        System.out.println("The distance travelled is " + kmDriven);
    }

    public double getTotalFare() {
        return totalFare;
    }

    public void setTotalFare(double totalFare) {
        this.totalFare = totalFare;
    }

    public double getTotalUsageInTime() {
        return totalUsageInTime;
    }

    public void setTotalUsageInTime(double totalUsageInTime) {
        this.totalUsageInTime = totalUsageInTime;
    }

    public String getLinkedNumber() {
        return linkedNumber;
    }

    private double calculateFare(int totalUsageInTime){
        return baseFare + (per_minuteFare * totalUsageInTime);

    }

    public void printRideDetail(int usageInMinutes){
        System.out.println("The account is " + linkedAccount);
        System.out.println("The phone number is " + linkedNumber);
        System.out.println("The bikeId is " + bikeId);
        System.out.println("The usage in minutes is " + usageInMinutes);
        this.totalFare = calculateFare(usageInMinutes);
        System.out.println("The total fare is " + totalFare);

    }

    public void setBikeId(int bikeId) {
        this.bikeId = bikeId;
    }

    public int getBikeId() {
        return bikeId;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public double getKmDriven() {
        return kmDriven;
    }

    public void setKmDriven(double kmDriven) {
        this.kmDriven = kmDriven;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

}
