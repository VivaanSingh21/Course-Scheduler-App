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

public class MultiTableQueries {
    private static Connection connection;
    private static PreparedStatement getAllClassDescription;
    private static PreparedStatement getScheduledStudentsByClass;
    private static ResultSet resultSet;
    private static PreparedStatement getWaitlistedStudentsByClass;
    
    
    public static ArrayList<ClassDescription> getAllClassDescription(String semester) throws SQLException{
        connection = DBConnection.getConnection();
        ArrayList<ClassDescription> classes = new ArrayList<ClassDescription>();
        try
        {
            getAllClassDescription = connection.prepareStatement("select app.classentry.courseCode,app.courseentry.description , app.classentry.seats from app.classentry, app.courseentry where app.classentry.semester = ? and app.classentry.courseCode = app.courseentry.courseCode order by app.classentry.courseCode");
            getAllClassDescription.setString(1, semester);
            resultSet = getAllClassDescription.executeQuery();
            while(resultSet.next())
            {
                ClassDescription classs = new ClassDescription(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3));
                classes.add(classs);
            }
            
                
                
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
        return classes;
    }
    
    public static ArrayList<StudentEntry> getScheduledStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        
        try{
            getScheduledStudentsByClass = connection.prepareStatement("SELECT s.studentid, se.firstname, se.lastname FROM app.SCHEDULE s JOIN app.studententry se ON s.studentid = se.studentid WHERE s.semester = ? AND s.coursecode = ? and s.status = 'S'");
            getScheduledStudentsByClass.setString(1, semester);
            getScheduledStudentsByClass.setString(2, courseCode);
            resultSet = getScheduledStudentsByClass.executeQuery();
            
            while(resultSet.next()){
                StudentEntry student = new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                students.add(student);
            }
          
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
       return students;
    }
    
    public static ArrayList<StudentEntry> getWaitlistedStudentsByClass(String semester, String courseCode){
        connection = DBConnection.getConnection();
        ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
        
        try{
            getWaitlistedStudentsByClass = connection.prepareStatement("SELECT s.studentid, se.firstname, se.lastname FROM app.SCHEDULE s JOIN app.studententry se ON s.studentid = se.studentid WHERE s.semester = ? AND s.coursecode = ? and s.status = 'W'");
            getWaitlistedStudentsByClass.setString(1, semester);
            getWaitlistedStudentsByClass.setString(2, courseCode);
            resultSet = getWaitlistedStudentsByClass.executeQuery();
            
            while(resultSet.next()){
                StudentEntry student = new StudentEntry(resultSet.getString(1), resultSet.getString(2), resultSet.getString(3));
                students.add(student);
            }
          
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
       
        //System.out.println(students.get(0).getFirstName());
       return students; 
       
    }
    
    
    
}
