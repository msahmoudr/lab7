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
        CourseController CourseController = new CourseController();
        if (CurrentInstructor.getCreatedCourses() == null)
            return null;
        for (String s : CurrentInstructor.getCreatedCourses())
        {
            CreatedCourses.add(CourseController.getCourseById(s));
        }
        return CreatedCourses;
    }

    private void updateInstructor(Instructor instructor)
    {
        ArrayList<User> users = JsonFileHandler.readUsers();
        for(User user : users)
        {
            if(user.getUserId().equals(instructor.getUserId()))
            {
                user = instructor;
            }
        }
        CurrentInstructor = instructor;
        JsonFileHandler.writeUsers(users);
    }

    public boolean AddCourse(String courseId, String title, String description, String instructorId, ArrayList<Lesson> lessons, ArrayList<String> enrolledStudents)
    {
        Course course = new Course(courseId, title, description, instructorId, lessons, enrolledStudents);

        if(course == null)
            return false;
        CurrentInstructor.getCreatedCourses().add(courseId);
        CourseController courseController = new CourseController();
        courseController.createCourse(course);
        updateInstructor(getCurrentInstructor());
        return true;

    }

    public boolean DeleteCourse(String courseId)
    {

    }

    public  boolean UpdateCourse(String courseId, String title, String description, String instructorId, ArrayList<Lesson> lessons, ArrayList<String> enrolledStudents)
    {
        Course course = new Course(courseId, title, description, instructorId, lessons, enrolledStudents);
        if(course == null)
            return false;
        CurrentInstructor.getCreatedCourses().remove(courseId);
        CourseController courseController = new CourseController();
        courseController.updateCourse(course);
        updateInstructor(getCurrentInstructor());
        return true;

    }

    public ArrayList<String> GetEnrolledStudents()
    {
        ArrayList<String> EnrolledStudents = new ArrayList<>();
        ArrayList<Course> CreatedCourses = getCreatedCourses();
        if(CreatedCourses == null)
            return null;
        for (Course course : CreatedCourses)
        {
            for (String s : course.getEnrolledStudents())
                {
                EnrolledStudents.add(s);
                }
        }
        return EnrolledStudents;

    }
    

}
