

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vivaansingh
 */
public class ClassQueries {
    
    private static Connection connection;
    private static PreparedStatement addClass;
    private static PreparedStatement getAllCourseCodes;
    private static PreparedStatement getClassSeats;
    private static PreparedStatement dropClass;
    private static ResultSet resultSet;
    private static PreparedStatement updateClassSeats;
    
    public static void addClass(ClassEntry classs){
        connection = DBConnection.getConnection();
        
         try
        {
            addClass = connection.prepareStatement("insert into app.ClassEntry values (?,?,?)");
            addClass.setString(1, classs.getSemester());
            addClass.setString(2, classs.getCourseCode());
            addClass.setInt(3, classs.getSeats());
            
            addClass.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
    }
    
    public static ArrayList<String> getAllCourseCodes(String semester){
        connection = DBConnection.getConnection();
        ArrayList<String> courses = new ArrayList<String>();
        try{
            getAllCourseCodes = connection.prepareStatement("SELECT CourseCode FROM app.ClassEntry WHERE SEMESTER = ? ORDER BY CourseCode");
            getAllCourseCodes.setString(1, semester);
            resultSet = getAllCourseCodes.executeQuery();
            
            
            while(resultSet.next()){
                courses.add(resultSet.getString(1));
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return courses;
    }
     
    public static int getClassSeats(String semester, String courseCode){
        
        connection = DBConnection.getConnection();
        int seats = 0;
        try{
            getClassSeats = connection.prepareStatement("select seats from app.ClassEntry where semester = ? and courseCode = ?");
            getClassSeats.setString(1, semester);
            getClassSeats.setString(2, courseCode);
            resultSet = getClassSeats.executeQuery();
            
            while (resultSet.next()){
                seats = resultSet.getInt(1);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return seats;
       
    }
    
    
    
    
    
    
    
    public static void updateClassSeats(String semester, String courseCode, int seats){
        connection = DBConnection.getConnection();
        try{
            updateClassSeats = connection.prepareStatement("UPDATE app.classentry set seats = ? where semester = ? and coursecode = ?");
            updateClassSeats.setInt(1, seats);
            updateClassSeats.setString(2, semester);
            updateClassSeats.setString(3, courseCode);
            updateClassSeats.executeUpdate();
                   
        }catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
    public static void dropClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        
        try{
            dropClass = connection.prepareStatement("Delete from app.classentry where semester = ? and coursecode = ?");
            dropClass.setString(1, semester);
            dropClass.setString(2, courseCode);
            
            dropClass.executeUpdate();
        }
        
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
    }
    
}
