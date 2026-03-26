import java.util.Locale;

public class Feedback {
    private String firstName;
    private String lastName;
    private String email;
    private String completeFeedback;
    private String reviewID;
    private boolean longFeedback;

    public Feedback(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.completeFeedback = "";
        this.reviewID = "";
        this.longFeedback = false;
    }

    public void analyseFeedback(boolean isConcatenation,String sent1,String sent2,String sent3,String sent4,String sent5){
        if (isConcatenation){
            this.completeFeedback = feedbackUsingConcatenation(sent1,sent2,sent3,sent4,sent5);
            this.reviewID = createReviewId(this.firstName,this.lastName,this.email);
            this.longFeedback = checkFeedbackLength(this.completeFeedback);
        }else {
            StringBuilder sb = feedbackUsingStringBuilder(sent1,sent2,sent3,sent4,sent5);
            this.completeFeedback = sb.toString();
        }
    }

    public String feedbackUsingConcatenation(String sent1, String sent2, String sent3, String sent4, String sent5){
        String concatenatedFeedback = sent1 + sent2 + sent3 + sent4 + sent5;
        return concatenatedFeedback;
    }

    private StringBuilder feedbackUsingStringBuilder(String sent1, String sent2, String sent3, String sent4, String sent5){
        StringBuilder sb = new StringBuilder();
        sb.append(sent1);
        sb.append(sent2);
        sb.append(sent3);
        sb.append(sent4);
        sb.append(sent5);

        return sb;
    }

    public boolean checkFeedbackLength(String completeFeedback){
        if (completeFeedback.length() > 500){
            return true;
        }else return false;
    }

    public String createReviewId(String firstName,String lastName,String email){
        String name = firstName + lastName;
        String part1 = name.substring(2,6).toUpperCase();
        String part2 = completeFeedback.substring(10,15).toLowerCase();
        String part3 = String.valueOf(completeFeedback.length());
        String part4 = "_";
        String part5 = String.valueOf(System.currentTimeMillis());

        String reviewID = part1 + part2 + part3 + part4 + part5;
        reviewID = reviewID.replace(" ","");

        return reviewID;
    }

    public String toString(){
        return  "Name:" + firstName + lastName +
                "Email:" + email +
                "Complete Feedback:" + completeFeedback;

    }

    public String getCompleteFeedback() {
        return completeFeedback;
    }

    public boolean isLongFeedback() {
        return longFeedback;
    }

    public void setLongFeedback(boolean longFeedback) {
        this.longFeedback = longFeedback;
    }

    public void setCompleteFeedback(String completeFeedback) {
        this.completeFeedback = completeFeedback;
    }

    public String getReviewID() {
        return reviewID;
    }

    public void setReviewID(String reviewID) {
        this.reviewID = reviewID;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

}


