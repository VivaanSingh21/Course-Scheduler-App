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
import java.sql.Timestamp;
import java.util.ArrayList;


public class ScheduleQueries {
    private static Connection connection;
    private static PreparedStatement addScheduleEntry;
    private static PreparedStatement getScheduleByStudent;
    private static PreparedStatement getScheduledStudentCount;
    private static PreparedStatement getWaitlistedStudentsByClass;
    private static PreparedStatement dropStudentScheduleByCourse;
    private static PreparedStatement dropScheduleByCourse;
    private static PreparedStatement updateScheduleEntry;
    private static ResultSet resultSet;
    
    
    public static void addScheduleEntry(ScheduleEntry entry){
           connection = DBConnection.getConnection();

            try
           {
               addScheduleEntry = connection.prepareStatement("INSERT INTO app.SCHEDULE (semester, coursecode, studentid, status, timestamp) VALUES (?, ?, ?, ?, ?)");
               addScheduleEntry.setString(1, entry.getSemester());
               addScheduleEntry.setString(2, entry.getCourseCode());
               addScheduleEntry.setString(3, entry.getStudentID());
               addScheduleEntry.setString(4, entry.getStatus());
               addScheduleEntry.setTimestamp(5, entry.getTimestamp());
              

               addScheduleEntry.executeUpdate();
           }

           catch(SQLException sqlException)
           {
               sqlException.printStackTrace();
           }
            
            

       }
    
     public static ArrayList<ScheduleEntry> getScheduleByStudent(String semester, String studentID){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry>schedule = new ArrayList<ScheduleEntry>();
        try{
            getScheduleByStudent = connection.prepareStatement("SELECT * FROM app.SCHEDULE WHERE semester = ? AND studentid = ?");
            getScheduleByStudent.setString(1, semester);
            getScheduleByStudent.setString(2, studentID);
            
            resultSet = getScheduleByStudent.executeQuery();
            
            
            while(resultSet.next()){
                String retrievedSemester = resultSet.getString("semester");
                String courseCode = resultSet.getString("coursecode");
                String retrievedStudentID = resultSet.getString("studentid");
                String status = resultSet.getString("status");
                Timestamp timestamp = resultSet.getTimestamp("timestamp");

                ScheduleEntry entry = new ScheduleEntry(retrievedSemester, courseCode, retrievedStudentID, status, timestamp);
                schedule.add(entry);
            }
            
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        return schedule;
    }
    
    public static int getScheduledStudentCount(String semester, String courseCode){
        connection = DBConnection.getConnection();
        int seats = 0;
        try
        {
            getScheduledStudentCount = connection.prepareStatement("select count(studentid) from Schedule where semester = ? and courseCode =?");
            getScheduledStudentCount.setString(1, semester);
            getScheduledStudentCount.setString(2, courseCode);
            
            resultSet = getScheduledStudentCount.executeQuery();
            
            seats = resultSet.getInt(1);
        }
            
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
            
                    
            
        return seats;
    }
    
    public static ArrayList<ScheduleEntry> getWaitlistedStudentsByClass(String semester, String coursecode){
        connection = DBConnection.getConnection();
        ArrayList<ScheduleEntry> students = new ArrayList<ScheduleEntry>();
        
        try{
             getWaitlistedStudentsByClass = connection.prepareStatement("select studentid, timestamp from app.schedule where semester = ? and coursecode = ? and status = 'W' ");
             getWaitlistedStudentsByClass.setString(1, semester);
             getWaitlistedStudentsByClass.setString(2, coursecode);
             
             resultSet = getWaitlistedStudentsByClass.executeQuery();
             
             while(resultSet.next()){
                 String studentid = resultSet.getString(1);
                 Timestamp timestamp = resultSet.getTimestamp(2);
                 
                 ScheduleEntry schedule = new ScheduleEntry(semester, coursecode, studentid, "W", timestamp);
                 students.add(schedule);
             }
        }
        catch(SQLException sqlException)
        {
            sqlException.printStackTrace();
        }
        
        
        
        return students;
            
    
    }
    
    public static void dropStudentScheduleByCourse(String semester, String studentID, String courseCode){
        connection = DBConnection.getConnection();
        
        try{
            dropStudentScheduleByCourse = connection.prepareStatement("Delete from app.schedule where semester = ? and coursecode = ? and studentid = ?");
            dropStudentScheduleByCourse.setString(1, semester);
            dropStudentScheduleByCourse.setString(2, courseCode);
            dropStudentScheduleByCourse.setString(3, studentID);
            
            dropStudentScheduleByCourse.executeUpdate();
            
            
        }
        catch (SQLException sqlException)
        {
        sqlException.printStackTrace();
        // Handle the exception or rethrow it as needed
        } 
        
    }
    
    public static void dropScheduleByCourse(String semester, String courseCode){
        connection = DBConnection.getConnection();
        
        try{
            dropScheduleByCourse = connection.prepareStatement("Delete from app.schedule where semester = ? and coursecode = ? ");
            dropScheduleByCourse.setString(1, semester);
            dropScheduleByCourse.setString(2, courseCode);
            
            dropScheduleByCourse.executeUpdate();
            
            
        }
        
        catch (SQLException sqlException)
        {
        sqlException.printStackTrace();
        // Handle the exception or rethrow it as needed
        } 
    }
    
    
    public static void updateScheduleEntry(ScheduleEntry entry){
        
       connection = DBConnection.getConnection();
        
        try{
            updateScheduleEntry = connection.prepareStatement("Update app.schedule set status = 'S' where semester = ? and coursecode = ? and studentid = ? ");
            updateScheduleEntry.setString(1, entry.getSemester());
            updateScheduleEntry.setString(2, entry.getCourseCode());
            updateScheduleEntry.setString(3, entry.getStudentID());
            
            updateScheduleEntry.executeUpdate();
            
            
        }
        
        catch (SQLException sqlException)
        {
        sqlException.printStackTrace();
        // Handle the exception or rethrow it as needed
        } 
        
        
        
        
    }
}
