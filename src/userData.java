
import java.sql.*;


/**
// James LaForge Student Records Module
// User Data Class to get & set the data from SQL Sever
 */



public class userData {
    
    private static final String USERNAME = "liberty";
    private static final String PASSWORD = "liberty";
    private static final String DATABASE_URL = "jdbc:derby://localhost:1527/studentrecords";
    private final Connection connection;

    
    /**
     * Empty constructor that gets database connection.
    */
    public userData() throws SQLException {
        this.connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);
    }
    
    
    // Returns all users in list
    public ResultSet selectAllUsers() throws SQLException {
        final String SELECT_ALL_USERS = "SELECT ID, lastName, firstName, username, password, role FROM users ORDER BY lastName, firstName";
        return connection.createStatement().executeQuery(SELECT_ALL_USERS);
    }

    public ResultSet selectAllStudents() throws SQLException {
    final String SELECT_ALL_STUDENTS =
            "SELECT u.id AS user_id, u.username, u.firstName, u.lastName, u.role, s.id AS student_id, s.student_id,"
           + " s.teacher, s.parent_id "
           + "FROM students s "
           + "INNER JOIN users u ON s.username = u.username "
           + "WHERE u.role = 'Student'";

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STUDENTS);
    
    return preparedStatement.executeQuery();
}
    
    
    public ResultSet selectAllTeachers() throws SQLException {
    final String SELECT_ALL_TEACHERS =
            "SELECT id, firstName, lastName "  // added a space after lastName
            + "FROM users "
            + "WHERE role = 'Teacher'";

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TEACHERS);
    
    return preparedStatement.executeQuery();
}


    
    
    // Returns assessments for a specific userID
    public ResultSet selectAssessmentsByUser(int userID) throws SQLException {
        final String SELECT_STUDENT_ASSESSMENTS =
                "SELECT assessment_name, date, type FROM assessments"
                + " WHERE userID = ?"
                + " ORDER BY type";

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_ASSESSMENTS);
        preparedStatement.setInt(1, userID);
        return preparedStatement.executeQuery();
    }

    // Returns National Assessments for a specific userID
    public ResultSet selectNatAssessmentsByUser(int userID) throws SQLException {
        final String SELECT_STUDENT_NATASSESSMENTS =
                "SELECT assessment_name, date, type, score, percentile FROM assessments"
                + " WHERE USERID = ? AND type = 'National'"
                + " ORDER BY date";

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_NATASSESSMENTS);
        preparedStatement.setInt(1, userID);
        return preparedStatement.executeQuery();
    }

    // Returns State Assessments for a specific userID
    public ResultSet selectStateAssessmentsByUser(int userID) throws SQLException {
        final String SELECT_STUDENT_STATEASSESSMENTS =
                "SELECT assessment_name, date, type, score, percentile FROM assessments"
                + " WHERE USERID = ? AND type = 'State'"
                + " ORDER BY date";

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_STATEASSESSMENTS);
        preparedStatement.setInt(1, userID);
        return preparedStatement.executeQuery();
    }
  
     
     // Returns Local Assessments for a specific userID
     public ResultSet selectLocalAssessmentsByUser(int userID) throws SQLException {
        final String SELECT_STUDENT_LOCALASSESSMENTS =
            "SELECT assessment_name, date, type, score, percentile FROM assessments"
            + " WHERE USERID = ? AND type = 'Local'"
            + " ORDER BY date";

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENT_LOCALASSESSMENTS);
    preparedStatement.setInt(1, userID);
    return preparedStatement.executeQuery();
    }

    

    //Add a Student
    public int addUser(User user) throws SQLException {
        final String INSERT_USER = String.format(
                "INSERT INTO users (username, password, firstName, lastName, role)"
                + " VALUES ('%s', '%s', '%s', '%s', '%s')", user.getUsername(), user.getUserPassword(), user.getFirstName(), user.getLastName(), user.getRole());
        
        
        Statement insertStatement = connection.createStatement();
        insertStatement.executeUpdate(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
        ResultSet generatedKeys = insertStatement.getGeneratedKeys();
        
        while (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
        
        return -1;
    }
    
    //Delete a user
    public void deleteUser(User user) throws SQLException {
        final String DELETE_USER = String.format(
                "DELETE FROM users"
                + " WHERE users.username='%s'", user.getUsername());
        
        connection.createStatement().executeUpdate(DELETE_USER);
        
    }
    
    //Delete a Parent
    public void deleteParent(User user) throws SQLException {
        final String DELETE_USER = String.format(
                "DELETE FROM users"
                + " WHERE username='%s'", user.getUsername());
        final String DELETE_PARENT = String.format(
                "DELETE FROM parents"
                + " WHERE parents.username= '%s'", user.getUsername());
        
        connection.createStatement().executeUpdate(DELETE_USER);
        connection.createStatement().executeUpdate(DELETE_PARENT);
        
    }
    
    //Delete a Student
    public void deleteStudent(User user) throws SQLException {
        final String DELETE_ASSESSMENTS = String.format(
                "DELETE FROM assessments"
                + " WHERE assessments.userID=%d", user.getID());
        final String DELETE_STUDENT = String.format(
                "DELETE FROM students"
                + " WHERE username='%s'", user.getUsername());
        final String DELETE_USER = String.format(
                "DELETE FROM users"
                + " WHERE username='%s'", user.getUsername());
        
        connection.createStatement().executeUpdate(DELETE_ASSESSMENTS);
        connection.createStatement().executeUpdate(DELETE_STUDENT);
        connection.createStatement().executeUpdate(DELETE_USER);
              
    }
    
    // Get parent's ID by user ID
    public int getParentIdByUserId(int userID) throws SQLException {
        final String SELECT_PARENT_ID = "SELECT p.id FROM parents p JOIN users u ON p.username = u.username WHERE u.id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PARENT_ID);
        preparedStatement.setInt(1, userID);
        ResultSet rs = preparedStatement.executeQuery();
        return rs.next() ? rs.getInt(1) : -1; // Return the parent ID if found
    }
    
    //get students by parentID
    public ResultSet selectStudentsByParentId(int parentID) throws SQLException {
        final String SELECT_STUDENTS =
            "SELECT s.username AS student_username, u.id AS student_user_id, u.firstName, u.lastName" +
            " FROM students s" +
            " JOIN users u ON s.username = u.username" +
            " WHERE s.parent_id = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS);
        preparedStatement.setInt(1, parentID);
        return preparedStatement.executeQuery();
    }
 
    
    //Add an Assessment Name
    public int addAssessmentName(AssessmentName assessmentName) throws SQLException {
        final String INSERT_ASSESSMENTNAME = "INSERT INTO assessmentName (assessment_name, date, type) VALUES (?, ?, ?)";

        PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ASSESSMENTNAME, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1, assessmentName.getAssessment_name());
        preparedStatement.setString(2, assessmentName.getDate());
        preparedStatement.setString(3, assessmentName.getType());
        preparedStatement.executeUpdate();

        ResultSet generatedKeys = preparedStatement.getGeneratedKeys();

        if (generatedKeys.next()) {
            return generatedKeys.getInt(1);
        }
    
    return -1;
    }
    
    //Get a list of all assessments
    public ResultSet selectAllAssessments() throws SQLException {
         final String SELECT_ALL_ASSESSMENTS = "SELECT assessment_name, date, type FROM assessmentName ORDER BY assessment_name, date";
        return connection.createStatement().executeQuery(SELECT_ALL_ASSESSMENTS);
    } 
    
   public void deleteAssessmentName(String assessmentName) throws SQLException {
        // First, delete the entries in the assessments table
        final String SQL_DELETE_ENTRIES_IN_ASSESSMENTS = "DELETE FROM assessments WHERE assessment_name = ?";
        PreparedStatement statementForAssessments = connection.prepareStatement(SQL_DELETE_ENTRIES_IN_ASSESSMENTS);
        statementForAssessments.setString(1, assessmentName);
        statementForAssessments.executeUpdate();

        // Then, delete the entry in the assessmentName table
        final String SQL_DELETE_ASSESSMENT = "DELETE FROM assessmentName WHERE assessment_name = ?";
        PreparedStatement statementForAssessmentName = connection.prepareStatement(SQL_DELETE_ASSESSMENT);
        statementForAssessmentName.setString(1, assessmentName);
        statementForAssessmentName.executeUpdate();
    }


    public ResultSet selectScoresByAssessmentAndTeacher(String assessmentName, String teacherName) throws SQLException {
        final String SELECT_SCORES_BY_ASSESSMENT_AND_TEACHER = 
            "SELECT u.id AS student_id, u.firstName, u.lastName, s.score " +
            "FROM assessments s " +
            "JOIN users u ON s.userID = u.id " +
            "JOIN students st ON u.username = st.username " +
            "WHERE s.assessment_name = ? AND st.teacher = ? " +
            "ORDER BY u.firstName, u.lastName";

        PreparedStatement statement = connection.prepareStatement(SELECT_SCORES_BY_ASSESSMENT_AND_TEACHER);
        statement.setString(1, assessmentName);
        statement.setString(2, teacherName);
        return statement.executeQuery();
    }


    public ResultSet selectLocalAssessmentsForTeacher () throws SQLException {
        final String SELECT_ALL_LOCAL_ASSESSMETNS = "SELECT assessment_name, date, type " +
                "FROM assessmentName " +
                "Where type = 'Local' " +
                "ORDER BY assessment_name, date";
                
        return connection.createStatement().executeQuery(SELECT_ALL_LOCAL_ASSESSMETNS);
    }


    
   public ResultSet classAssessmentResults(StudentResults studentResults) throws SQLException {
    final String SELECT_STUDENTS_BY_ASSESSMENT_AND_TEACHER = 
        " SELECT u.firstName AS student_firstName, " +
        "u.lastName AS student_lastName, " +
        "s.teacher, a.assessment_name, " +
        "a.date, a.type, a.score, a.percentile " +
        "FROM assessments a " +
        "JOIN users u ON a.userID = u.id " +
        "JOIN students s ON u.username = s.username " +
        "WHERE a.assessment_name = ? " +
        "AND s.teacher = ?";

    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STUDENTS_BY_ASSESSMENT_AND_TEACHER);
    preparedStatement.setString(1, studentResults.getAssessment_name());  
    preparedStatement.setString(2, studentResults.getTeacher());          

    ResultSet rs = preparedStatement.executeQuery();
    return rs;
    }



    public String getTeacherFullNameById(int teacherID) throws SQLException {
        final String GET_TEACHER_NAME_BY_ID = 
            "SELECT firstName, lastName " +
            "FROM users " +
            "WHERE id = ? AND role = 'Teacher'"; 

        PreparedStatement preparedStatement = connection.prepareStatement(GET_TEACHER_NAME_BY_ID);
        preparedStatement.setInt(1, teacherID);

        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString("firstName") + " " + resultSet.getString("lastName");
        } else {
            return null; 
        }
    }


    public ResultSet getStudentsByTeacherName(String teacherFullName) throws SQLException {
        final String GET_STUDENTS_BY_TEACHER_NAME = 
            "SELECT u.firstName, u.lastName, u.id " +
            "FROM users u " +
            "JOIN students s ON u.username = s.username " +
            "WHERE s.teacher = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(GET_STUDENTS_BY_TEACHER_NAME);
        preparedStatement.setString(1, teacherFullName);

        return preparedStatement.executeQuery();
    }

    
   public ResultSet getStudentScoresByAssessment(String assessmentName) throws SQLException {
        final String SQL_FETCH_SCORES = 
            "SELECT s.firstName, s.lastName, s.USERNAME, a.score " +
            "FROM assessments a " +
            "JOIN users s ON a.userID = s.id " +
            "WHERE a.assessment_name = ?";

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_FETCH_SCORES);
        preparedStatement.setString(1, assessmentName);
        return preparedStatement.executeQuery();
    }

    
    
    public boolean scoresExistForAssessment(String assessmentName) throws SQLException{
            final String SQL_CHECK_SCORES_EXIST = 
                "SELECT COUNT(*) FROM assessments WHERE assessment_name = ?";
           
                PreparedStatement statement = connection.prepareStatement(SQL_CHECK_SCORES_EXIST);
                statement.setString(1, assessmentName);
                ResultSet resultSet = statement.executeQuery();
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
             
            return false;
    }

    
    public ResultSet getAllStudents() throws SQLException{
        final String SQL_FETCH_STUDENTS = "SELECT username FROM students";
        
            PreparedStatement statement = connection.prepareStatement(SQL_FETCH_STUDENTS);
            return statement.executeQuery();
        
    }


    public boolean scoreExistsForStudent(String assessmentName, Integer id) throws SQLException {
        final String SQL_CHECK_SCORE_EXISTS = 
            "SELECT COUNT(*) FROM assessments WHERE assessment_name = ? AND userid = ?";

            PreparedStatement statement = connection.prepareStatement(SQL_CHECK_SCORE_EXISTS);
            statement.setString(1, assessmentName);
            statement.setInt(2, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }

        return false;
    }

    public void updateScore(String assessmentName, StudentSaveScore score) throws SQLException {
        final String SQL_UPDATE_SCORE = 
            "UPDATE assessments SET score = ? WHERE assessment_name = ? AND userid = ?";

            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_SCORE);
            statement.setInt(1, score.getScore());
            statement.setString(2, assessmentName);
            statement.setInt(3, score.getId());
            statement.executeUpdate();

    
}

    public void insertScore(String assessmentName, AssessmentDetails details, StudentSaveScore score) throws SQLException {
        final String SQL_INSERT_SCORE = 
            "INSERT INTO assessments (assessment_name, userid, score, DATE, type) VALUES (?, ?, ?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(SQL_INSERT_SCORE);
        statement.setString(1, assessmentName);
        statement.setInt(2, score.getId());
        statement.setInt(3, score.getScore());
        statement.setDate(4, details.getDate());
        statement.setString(5, details.getAssessmentType());
        

        statement.executeUpdate();
    }



    public Integer getUserIdByName(String name) throws SQLException {
        final String SQL_GET_USER_ID = 
                "SELECT id FROM users WHERE firstName || ' ' || lastName = ?";
            PreparedStatement statement = connection.prepareStatement(SQL_GET_USER_ID);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        return null;  // If no user found with the given name
    }

    
    public AssessmentDetails getAssessmentDetailsByName(String assessmentName) throws SQLException {
        final String SQL_GET_ASSESSMENT_DETAILS = 
            "SELECT * FROM assessmentName WHERE assessment_name = ?";
        PreparedStatement statement = connection.prepareStatement(SQL_GET_ASSESSMENT_DETAILS);
        statement.setString(1, assessmentName);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {

            Date date = resultSet.getDate("DATE");
            String assessmentType = resultSet.getString("type");

            // Return them as an object for easier handling. 
            return new AssessmentDetails(assessmentName, date, assessmentType); 
        }
        return null;  // If no assessment found with the given name
    }





}   
    
    
    








