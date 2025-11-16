/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer;

import java.util.ArrayList;
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
}
