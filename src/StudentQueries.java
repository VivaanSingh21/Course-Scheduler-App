/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vivaansingh
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
public class StudentQueries {
    private static Connection connection;
    private static PreparedStatement addStudent;
    private static PreparedStatement getAllStudents;
    private static PreparedStatement getStudentID;
    private static PreparedStatement getStudent;
    private static PreparedStatement dropStudent;
    
          
    
    private static ResultSet resultSet;
    
    public static void addStudent(StudentEntry student){
           connection = DBConnection.getConnection();

            try
           {
               addStudent = connection.prepareStatement("insert into app.StudentEntry values (?,?,?)");
               addStudent.setString(1, student.getStudentID());
               addStudent.setString(2, student.getFirstName());
               addStudent.setString(3, student.getLastName());

               addStudent.executeUpdate();
           }

           catch(SQLException sqlException)
           {
               sqlException.printStackTrace();
           }

       }
    
    public static ArrayList<StudentEntry> getAllStudents(){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry>students = new ArrayList<StudentEntry>();
        try{
            getAllStudents = connection.prepareStatement("select * from app.StudentEntry order by lastname, firstname ");
            
            resultSet = getAllStudents.executeQuery();
            
            
            while(resultSet.next()){
                StudentEntry entry = new StudentEntry(resultSet.getString(1),resultSet.getString(2), resultSet.getString(3));
                students.add(entry);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return students;
    }
    
    public static String getStudentID(String firstName, String lastName) {
    Connection connection = DBConnection.getConnection();
    String studentID = null;

    try {
        // Prepare the statement with the correct SQL query
        PreparedStatement getStudentID = connection.prepareStatement("SELECT studentID FROM app.StudentEntry WHERE firstname = ? AND lastname = ?");
        getStudentID.setString(1, firstName);
        getStudentID.setString(2, lastName);

        // Execute the query and obtain the result set
        ResultSet resultSet = getStudentID.executeQuery();

        // Check if there is a result and retrieve the studentID
        if (resultSet.next()) {
            studentID = resultSet.getString("studentID");
        }
    } catch (SQLException sqlException) {
        sqlException.printStackTrace();
        // Handle the exception or rethrow it as needed
    } 
    

    return studentID;
}
    
    
    public static StudentEntry getStudent(String studentID){
        Connection connection = DBConnection.getConnection();
        StudentEntry student = null;
        
        try 
        {
        // Prepare the statement with the correct SQL query
        PreparedStatement getStudent = connection.prepareStatement("SELECT * FROM app.StudentEntry WHERE studentid = ?");
        getStudent.setString(1, studentID);
        
        resultSet = getStudent.executeQuery();
        
        while (resultSet.next()){
            String studentIDD = resultSet.getString(1);
            String firstName = resultSet.getString(2);
            String lastName = resultSet.getString(3);
            
            student = new StudentEntry(studentIDD, firstName, lastName);
            
            
        }
        }
        catch (SQLException sqlException)
        {
        sqlException.printStackTrace();
        // Handle the exception or rethrow it as needed
        } 
        
        
        return student;
        
    }
    
    public static void dropStudent(String studentID){
        Connection connection = DBConnection.getConnection();
        
        try
        {
            PreparedStatement dropStudent = connection.prepareStatement("DELETE from app.Studententry where studentid = ?");
            dropStudent.setString(1, studentID);
            
            resultSet= dropStudent.executeQuery();
                    
        }
        catch (SQLException sqlException)
        {
        sqlException.printStackTrace();
        // Handle the exception or rethrow it as needed
        } 
        
            
           
        
        
    }

        
    
    
}
