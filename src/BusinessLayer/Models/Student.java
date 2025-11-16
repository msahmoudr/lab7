/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BusinessLayer.Models;

import java.util.*;

public class Student extends User {
    private List<String> enrolledCourses = new ArrayList<>();
    private Map<String, List<String>> progress = new HashMap<>(); // courseId -> list of completed lessonIds

    public Student() { this.role = "student"; }

    public Student(String userId, String username, String email, String passwordHash) {
        super(userId, username, email, passwordHash, "student");
    }

    public List<String> getEnrolledCourses() { return enrolledCourses; }
    public void setEnrolledCourses(List<String> enrolledCourses) { this.enrolledCourses = enrolledCourses; }

    public void addEnrolledCourse(String courseId) {
        if (!enrolledCourses.contains(courseId)) enrolledCourses.add(courseId);
    }

    public Map<String, List<String>> getProgress() { return progress; }
    public void setProgress(Map<String, List<String>> progress) { this.progress = progress; }

    public void updateProgress(String courseId, String lessonId) {
        progress.computeIfAbsent(courseId, k -> new ArrayList<>());
        List<String> completed = progress.get(courseId);
        if (!completed.contains(lessonId)) completed.add(lessonId);
    }
}

