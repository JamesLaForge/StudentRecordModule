/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jay_l
 */
public class StudentResults {
    private String student_firstName;
    private String student_lastName;
    private String assessment_name;
    private String date; 
    private String type;
    private int userID;
    private int score;
    private Integer percentile;
    private String teacher;
    
    public StudentResults() {
    }

    public StudentResults( String student_firstName, String student_lastName, String assessment_name, String date,String type, int userID, int score,  Integer percentile, String teacher) {
        this.student_firstName = student_firstName;
        this.student_lastName = student_lastName;
        this.assessment_name = assessment_name;
        this.date = date;
        this.type = type;
        this.userID = userID;
        this.score = score;
        this.percentile = percentile;
        this.teacher = teacher;
    }

    //Getter and Setter for First Name
    public void setFirstName(String student_firstName) {
        this.student_firstName = student_firstName;
    }
    
    public String getFirstName() {
        return student_firstName;
    }

    //Getter and Setter for Last Name
    public void setLastName(String student_lastName) {
        this.student_lastName = student_lastName;
    }
    
    public String getLastName() {
        return student_lastName;
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

    // Getter and Setter for percentile
    public Integer getPercentile() {
        return percentile;
    }

    public void setPercentile(Integer percentile) {
        this.percentile = percentile;
    }
    
    //Getter and Setter for Teacher
    public String getTeacher(){
        return teacher;
    }
    
    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
    
    @Override
    public String toString() {
        return "Name: " + student_firstName + " " + student_lastName + "      " + "Assessment= " + assessment_name + ",      Date= " + date +"      Score=" + score + ",     Percentile=" + percentile;
    }
}