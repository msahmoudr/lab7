package DataAccessLayer;

import BussinessLayer.Course;
import BussinessLayer.Instructor;
import BussinessLayer.Student;
import BussinessLayer.User;
import DataAccessLayer.JsonFiles.FilesName;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory;


import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class JsonFileHandler
{
    public static Gson createGsonForUsers()
    {

        RuntimeTypeAdapterFactory<User> adapterFactory =
                RuntimeTypeAdapterFactory.of(User.class, "role")
                        .registerSubtype(Student.class, "false")
                        .registerSubtype(Instructor.class, "true");
        return new GsonBuilder()
                .registerTypeAdapterFactory(adapterFactory)
                .setPrettyPrinting()
                .create();
    }


    public ArrayList<User> readUsers()
    {
        Gson gson = createGsonForUsers();

        try(FileReader reader = new FileReader(FilesName.usersFile))
        {
            Type userListType = new TypeToken<ArrayList<User>>(){}.getType();
            ArrayList<User> userList = gson.fromJson(reader, userListType);
            return userList;
        }
        catch (IOException e)
        {

            return  null;
        }
    }

    public static void writeUsers(ArrayList<User> users)
    {
        Gson gson = createGsonForUsers();
        try(FileWriter writer = new FileWriter(FilesName.usersFile))
        {
            gson.toJson(users, writer);

        }
        catch (IOException e)
        {
            return;
        }
    }

    public static void writeCourses(ArrayList<Course> courses)
    {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try(FileWriter writer = new FileWriter(FilesName.coursesFile))
        {
            gson.toJson(courses, writer);
        }
        catch (IOException e)
        {
            return;
        }
    }

    public static ArrayList<Course> readCourses()
    {
        Gson gson = new Gson();
        try(FileReader reader = new FileReader(FilesName.coursesFile))
        {
            Type courseListType = new TypeToken<ArrayList<Course>>(){}.getType();
            ArrayList<Course> courseList = gson.fromJson(reader, courseListType);
            return courseList;
        }
        catch (IOException e)
        {
            return  null;
        }
    }
}
