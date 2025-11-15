/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BussinessLayer;

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
    
    
    
    
    @Override
    public String toString()
    { 
        
        return this.lessonId + "^" + this.title + "^" + this.content + "^" + this.resources.toString() ;
    }
    
    
}
