/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer;

import DataAccessLayer.JsonFileHandler;

import java.util.ArrayList;

/**
 *
 * @author msahm
 */
public class LessonController {
    CourseController cc =new CourseController();
    public void addLesson (Course course,Lesson lesson)
    {
        CourseController.addLesson(course, lesson);


    }
    
}
