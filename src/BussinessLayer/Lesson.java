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

public class Lesson {
    private String lessonId;
    private String title;
    private String content;
    private ArrayList<String> resources;
    CourseController cc=new CourseController();

    public Lesson(String lessonId, String title, String content, ArrayList<String> resources) {
        this.lessonId = lessonId;
        this.title = title;
        this.content = content;
        this.resources = resources;
    }

    public String getLessonId() {
        return lessonId;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public ArrayList<String> getResources() {
        return resources;
    }

    public void addResource(String resource) {
        this.resources.add(resource);
       cc.updateCourses();
    }
    public void updateLesson(Lesson lesson)
    {
        this.lessonId=lesson.lessonId;
        this.resources=lesson.resources;
        this.content=lesson.content;
        this.title=lesson.title;
        cc.updateCourses();
    }



    
    
    
    /*
    @Override
    public String toString()
    { 
        
        return this.lessonId + "^" + this.title + "^" + this.content + "^" + this.resources.toString() ;
    }*/
    
    
}
