/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer;

import java.util.ArrayList;
import java.util.Iterator;

import DataAccessLayer.JsonFileHandler;

/**
 *
 * @author msahm
 */
public class CourseController {

    private ArrayList<Course> courses;

    public CourseController() {
        this.courses = JsonFileHandler.readCourses();

    }

    public ArrayList<Course> getAllCourses() {
        return courses;
    }

    public Course getCourseById(String id){
        Course course= null;
        for(Course i:courses)
        {
            if(i.getCourseId().equals(id))
            {
                course=i;
                return course;
            }
        }
        return course;
    }
    public User getUserById(String id)
    {
        ArrayList<User> users = new JsonFileHandler().readUsers() ;
        User user = null;
        for(User i :users)
        {
            if (i.getUserId().equals(id))
            {
                user= i;
                return user;
            }
        }
        return user;

    }

    public ArrayList<String> getEnrolledStudents(Course course)
    {

        ArrayList<String> enrolledStudents = new ArrayList<>();
        for(int i=0;i<course.getEnrolledStudents().toArray().length; i++)
        {
            String str = course.getEnrolledStudents().get(i);
            User user=getUserById(str);
            str+= ","+ user.getUserName();
            str+=","+user.getEmail();
            enrolledStudents.add(str);

        }
        return enrolledStudents;
    }
    public void addLesson (Course course,Lesson lesson)
    {
        course.getLessons().add(lesson);
        JsonFileHandler.writeCourses(this.courses);

    }
    public void removeLesson (Course course,Lesson lesson)
    {
        course.getLessons().remove(lesson);
        JsonFileHandler.writeCourses(this.courses);

    }
    public void enrollStudent(Course course, Student student)
    {
        course.getEnrolledStudents().add(student.getUserId());
        JsonFileHandler.writeCourses(this.courses);

    }
    public void removeStudent(Course course, Student student)
    {
        course.getEnrolledStudents().remove(student.getUserId());
        JsonFileHandler.writeCourses(this.courses);
    }
    public void createCourse(Course course)
    {
        this.courses.add(course);
        JsonFileHandler.writeCourses(this.courses);


    }
    public void updateCourse(Course course)
    {
        int j=0;
        for(Course i:this.courses)
        {
            if(i.getCourseId().equals(course.getCourseId()))
            {
                this.courses.set(j, course);
                break;
            }
            j++;
        }
        JsonFileHandler.writeCourses(this.courses);
    }
    public void deleteCourse(String id){
        Iterator<Course> i = this.courses.iterator();
        while (i.hasNext()) {
            Course course = i.next();
            if (course.getCourseId().equals(id)) {
                ArrayList<String> studentsId = course.getEnrolledStudents();
                for(String str: studentsId)
                {
                    Student student= (Student) getUserById(str);
                    student.getEnrolledCourse().remove(id);
                }

                
                i.remove();
                break;
            } //maynfa34 for loop 3l4an bt throw ConcurrentModificationException
        }
        JsonFileHandler.writeCourses(this.courses);
    }
    public ArrayList<Course> getCourseByInstructor(String id){
        ArrayList<Course> instructorCourses=new ArrayList<>();
        for(Course i: this.courses)
        {
            if(i.getInstructorId().equals(id))
            {
                instructorCourses.add(i);
            }
        }
        return instructorCourses;
    }
    public ArrayList<Lesson> getLessonsByCourse(String id){
        Course course = getCourseById(id);
        return course.getLessons();
    }
    public void updateCourses()
    {
        JsonFileHandler.writeCourses(this.courses);
    }


}
