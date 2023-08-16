
/**
 *
 * @author James LaForge
 */
public class AssessmentName {
    private String assessment_name;
    private String date; 
    private String type;
    private int id;
    
    
    public AssessmentName() {
    }

    public AssessmentName( String assessment_name, String date,String type, int id) {
        this.assessment_name = assessment_name;
        this.date = date;
        this.type = type;
        this.id = id;
        
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
    
    // Getter and Setter for type
    public int getID(){
        return id;
    }
    
    public void setID (int id) {
        this.id = id;
    }

    
    @Override
    public String toString() {
        return assessment_name + "        "+ date + "          " + type;
    }
}
