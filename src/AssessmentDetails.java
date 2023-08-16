/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author James LaForge
 */
public class AssessmentDetails {

    private String assessmentName;
    private java.sql.Date date;
    private String assessmentType;
    

    // Constructor
    public AssessmentDetails(String assessmentName, java.sql.Date date, String assessmentType) {
        this.assessmentName = assessmentName;
        this.date = date;
        this.assessmentType = assessmentType;
    }

    // Getters and Setters for assessmentName
    public String getAssessmentName() {
        return assessmentName;
    }

    public void setAssessmentName(String assessmentName) {
        this.assessmentName = assessmentName;
    }

    // Getters and Setters for date
    public java.sql.Date getDate() {
        return date;
    }

    public void setDate(java.sql.Date date) {
        this.date = date;
    }

    // Getters and Setters for assessmentType
    public String getAssessmentType() {
        return assessmentType;
    }

    public void setAssessmentType(String assessmentType) {
        this.assessmentType = assessmentType;
    }

   

    @Override
    public String toString() {
        return "AssessmentDetails{" +
                "assessmentName='" + assessmentName + '\'' +
                ", date=" + date +
                ", assessmentType='" + assessmentType + '\'' +
                '}';
    }
}
