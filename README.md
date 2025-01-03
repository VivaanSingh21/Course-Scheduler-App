# Course Scheduler

This is a Course Scheduling application developed for a college to enable users to manage and schedule courses by semester. The application supports two types of users: Admin and Student. Admins can set up the database and manage courses, while Students can schedule classes and view their schedules.

This project was developed as the final project for CMPSC 221 - Object-Oriented Programming Principles at Penn State, taught by Al Verbanec.

## Features

### Admin Functions
- **Add Semester**: Add a new semester to the database.
- **Add Course**: Add a new course with a course code and description.
- **Add Class**: Add a new class for a specific semester, course code, and maximum seat limit.
- **Add Student**: Add a new student with a student ID, first name, and last name.
- **Display Class List of Students**: View all students scheduled or waitlisted for a class in the current semester.
- **Drop Student**: Remove a student from all classes and update the schedule for other students on the waitlist.
- **Drop Class**: Remove a class from the current semester and update the schedules of all affected students.

### Student Functions
- **Schedule Class**: Enroll in a class if seats are available, or get waitlisted if not.
- **Display Schedule**: View the current schedule of classes, including course codes and statuses (scheduled or waitlisted).
- **Display Classes**: View all classes available in the current semester.
- **Drop Class**: Remove a class from the schedule and update the waitlist for that class.

## Project Structure

### GUI
- Developed with a user-friendly interface.
- Combo Boxes used for drop-down lists (e.g., for semesters, student names, and course codes).
- Displays results of actions on the same screen.

### Object-Oriented Design
- **Semester**: Represented as a string.
- **CourseEntry**: Represents a course with a code and description.
- **ClassEntry**: Represents a class with a semester, course code, and seat limit.
- **StudentEntry**: Represents a student with an ID, first name, and last name.
- **ScheduleEntry**: Represents a student's schedule with a semester, course code, student ID, status (Scheduled/Waitlisted), and timestamp.

### Database
- **Tables**: Semester, Course, Class, Student, Schedule.
- **Primary Keys**: Marked with an asterisk (*) in the design.
- **Database Technology**: Apache Derby.
- **Access**: Implemented using PreparedStatements to ensure secure SQL operations.

### Queries
- **SemesterQueries**: Add semesters, retrieve semester lists.
- **CourseQueries**: Add courses, retrieve course codes.
- **ClassQueries**: Manage classes, retrieve class details, drop classes.
- **StudentQueries**: Manage students, retrieve student information, drop students.
- **ScheduleQueries**: Manage schedules, retrieve student schedules, update schedules.
- **MultiTableQueries**: Retrieve comprehensive class descriptions and student lists.

## Setup Instructions

1. Clone the repository:

2. Open the project in NetBeans (or your preferred IDE).

3. Ensure Apache Derby is configured and running.

4. Set the database credentials:
   - Username: `java`
   - Password: `java`

5. Run the application and use the GUI to perform Admin and Student operations.

6. Ensure database tables are empty before initial setup.

## Technologies Used
- **Programming Language**: Java
- **Database**: Apache Derby
- **GUI Framework**: Swing


