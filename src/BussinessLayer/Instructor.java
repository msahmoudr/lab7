package BussinessLayer;

import java.util.ArrayList;

public class Instructor extends User
{
    private ArrayList<String> createdCourses;

    public Instructor(String userId, String userName, String email, String passwordHash, boolean role, ArrayList<String> createdCourses) {
        super(userId, userName, email, passwordHash, role);
        this.createdCourses = createdCourses;
    }

    public ArrayList<String> getCreatedCourses() {
        return createdCourses;
    }
}
