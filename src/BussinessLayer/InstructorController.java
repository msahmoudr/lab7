package BussinessLayer;

import DataAccessLayer.JsonFileHandler;

import java.util.ArrayList;

public class InstructorController
{

    private Instructor CurrentInstructor;
    public InstructorController(String userId)
    {

        this.CurrentInstructor = GetInstructorById(userId);
    }

    private Instructor GetInstructorById(String userId)
    {
        ArrayList<Instructor> Instructors = JsonFileHandler.returnInstructors();
        return  Instructors.stream().filter(s -> s.getUserId().equals(userId)).findFirst().get();
    }

    public Instructor getCurrentInstructor() {
        return CurrentInstructor;
    }

    
}
