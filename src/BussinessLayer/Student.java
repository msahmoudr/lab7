package BussinessLayer;

import java.util.ArrayList;
import java.util.Map;

public class Student  extends User
{
    private ArrayList<String> enrolledCourse;
    Map<String, ArrayList<String>> progress;

    public Student(String userId, String userName, String email, String passwordHash, boolean role, ArrayList<String> enrolledCourse, Map<String, ArrayList<String>> progress) {
        super(userId, userName, email, passwordHash, role);
        this.enrolledCourse = enrolledCourse;
        this.progress = progress;
    }

    public ArrayList<String> getEnrolledCourse() {
        return enrolledCourse;
    }

    public Map<String, ArrayList<String>> getProgress() {
        return progress;
    }
}
