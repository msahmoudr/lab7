package BussinessLayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Student  extends User
{
    private ArrayList<String> enrolledCourse = new ArrayList<>();
    Map<String, ArrayList<String>> progress = new HashMap<>();

    public Student(String userId, String userName, String email, String passwordHash, boolean role, ArrayList<String> enrolledCourse, Map<String, ArrayList<String>> progress) {
        super(userId, userName, email, passwordHash, role);
        if (enrolledCourse != null)
        this.enrolledCourse = enrolledCourse;
        if (progress != null)
        this.progress = progress;
    }

    public ArrayList<String> getEnrolledCourse() {
        return enrolledCourse;
    }

    public Map<String, ArrayList<String>> getProgress() {
        return progress;
    }
}
