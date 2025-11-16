/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Models;

import java.util.*;

public class Instructor extends User {
    private List<String> createdCourses = new ArrayList<>();

    public Instructor() { this.role = "instructor"; }

    public Instructor(String userId, String username, String email, String passwordHash) {
        super(userId, username, email, passwordHash, "instructor");
    }

    public List<String> getCreatedCourses() { return createdCourses; }
    public void setCreatedCourses(List<String> createdCourses) { this.createdCourses = createdCourses; }

    public void addCreatedCourse(String courseId) {
        if (!createdCourses.contains(courseId)) createdCourses.add(courseId);
    }

    public void removeCreatedCourse(String courseId) {
        createdCourses.remove(courseId);
    }
}

