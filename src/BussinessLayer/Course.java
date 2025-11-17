
package BussinessLayer;

import java.util.ArrayList;
import java.util.Objects;

public class Course {
    private String courseId;
    private String title;
    private String description;
    private String instructorId;
    private ArrayList<Lesson> lessons;
    private ArrayList<String> enrolledStudents = new ArrayList<>();

    public Course(String courseId, String title, String description, String instructorId,
                  ArrayList<Lesson> lessons, ArrayList<String> enrolledStudents) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = lessons != null ? lessons : new ArrayList<>();
        this.enrolledStudents = enrolledStudents != null ? enrolledStudents : new ArrayList<>();
    }

    public Course(String courseId, String title, String description, String instructorId, ArrayList<Lesson> lessons) {
        this.courseId = courseId;
        this.title = title;
        this.description = description;
        this.instructorId = instructorId;
        this.lessons = lessons != null ? lessons : new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }


    public Course() {
        this.lessons = new ArrayList<>();
        this.enrolledStudents = new ArrayList<>();
    }

    public String getCourseId() {
        return courseId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getInstructorId() {
        return instructorId;
    }

    public ArrayList<Lesson> getLessons() {
        if (lessons == null) lessons = new ArrayList<>();
        return lessons;
    }


    public ArrayList<String> getEnrolledStudents() {
        if (enrolledStudents == null) enrolledStudents = new ArrayList<>();
        return enrolledStudents;
    }

    public void setEnrolledStudents(ArrayList<String> enrolledStudents) {
        this.enrolledStudents = enrolledStudents != null ? enrolledStudents : new ArrayList<>();
    }

    
    public boolean addEnrolledStudent(String studentId) {
        if (studentId == null) return false;
        if (enrolledStudents == null) enrolledStudents = new ArrayList<>();
        if (!enrolledStudents.contains(studentId)) {
            return enrolledStudents.add(studentId);
        }
        return false;
    }

    public boolean removeEnrolledStudent(String studentId) {
        if (studentId == null || enrolledStudents == null) return false;
        return enrolledStudents.remove(studentId);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Course)) return false;
        Course course = (Course) o;
        return Objects.equals(courseId, course.courseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseId);
    }
}