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
        
    }
}
