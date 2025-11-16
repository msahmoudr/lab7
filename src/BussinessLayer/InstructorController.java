package BussinessLayer;

import DataAccessLayer.JsonFileHandler;

import java.util.ArrayList;

public class InstructorController
{

    private Instructor CurrentInstructor;
    public InstructorController(String userId)
    {

        this.CurrentInstructor = GetInstructorById(userId);
    }

    private Instructor GetInstructorById(String userId)
    {
        ArrayList<Instructor> Instructors = JsonFileHandler.returnInstructors();
        return  Instructors.stream().filter(s -> s.getUserId().equals(userId)).findFirst().get();
    }

    public Instructor getCurrentInstructor() {
        return CurrentInstructor;
    }

    public ArrayList<Course> getCreatedCourses()
    {
        ArrayList<Course> CreatedCourses = new ArrayList<>();
        for (String s : CurrentInstructor.getCreatedCourses())
        {
            //Search Course by id from Course Controller
        }
        return CreatedCourses;
    }

    public void AddCourse(String courseId, String title, String description, String instructorId, ArrayList<Lesson> lessons, ArrayList<String> enrolledStudents)
    {
        Course course = new Course(courseId, title, description, instructorId, lessons, enrolledStudents);
        //Add Validation if Course is Repeated
        //Add course form Course Controller
    }

    

}
