/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author vivaansingh
 */
public class CourseEntry {
    private String CourseCode;
    private String description;

    public CourseEntry(String CourseCode, String description) {
        this.CourseCode = CourseCode;
        this.description = description;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getDescription() {
        return description;
    }
    
    
}
