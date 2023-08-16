
/**
 *
 * @author James LaForge
 */
public class LocalAssessment {
    private String assessment_name;
    private String date; 
    private String type;
    private int userID;
    private int score;
    
    
    public LocalAssessment() {
    }

    public LocalAssessment( String assessment_name, String date,String type, int userID, int score) {
        this.assessment_name = assessment_name;
        this.date = date;
        this.type = type;
        this.userID = userID;
        this.score = score;
        
    }

    // Getter and Setter for assessment_name
    public String getAssessment_name() {
        return assessment_name;
    }

    public void setAssessment_name(String assessment_name) {
        this.assessment_name = assessment_name;
    }

    // Getter and Setter for date
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    // Getter and Setter for type
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    // Getter and Setter for userID
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    // Getter and Setter for score
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    
    
    @Override
    public String toString() {
        return "Assessment= " + assessment_name + ",      Date= " + date +"      Score=" + score ;
    }
}
