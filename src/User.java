

/**
 *
 * @author James LaForge
 */
public class User {
    
    private int ID;
    private String firstName;
    private String lastName;
    private String role;
    private String userName;
    private String userPassword;

    public User() {
    }

    //fetches current user information
    public User(int ID, String firstName, String lastName, String role, String userName, String userPassword) {
        this.ID = ID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
        this.userName = userName;
        this.userPassword = userPassword;
        
    }
    
        
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getFirstName() {
        return firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setUserName(String userName) {
        this.userName = userName;
    }
    
    public String getUsername() {
        return userName;
    }
    
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
    
    public String getUserPassword(){
        return userPassword;
    }
    
    public void setRole (String role) {
        this.role = role;
    }
    
    public String getRole() {
        return role;
    }
    
    public void setid(int ID) {
        this.ID = ID;
    }
    
    public int getID() {
        return ID;
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s", lastName, firstName, role, userName);
    }

}