/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jay_l
 */
public class AcademicUser {
    
    private int sUserID;
    private String name;
   
    
    public AcademicUser() {
    }

    public AcademicUser(String name, int sUserID) {
        this.name = name;
        this.sUserID = sUserID;
        
    }
    
        
    public void setName(String name) {
        this.name = name;
    }
    
    public String getName() {
        return name;
    }

    
    public void setSUserID(int sUserID) {
        this.sUserID = sUserID;
    }
    
    public int getSUserID() {
        return sUserID;
    }

    
    
    
  @Override
    public String toString() {
        return name;
    }


}
