package BussinessLayer;

import DataAccessLayer.JsonFileHandler;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class AuthController {

    private User currentUser = null;


    public static class PasswordHasher {
        public static String hashPassword(String password) {
            try {
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[] hashedBytes = md.digest(password.getBytes());

                // Convert byte array to hexadecimal string
                StringBuilder sb = new StringBuilder();
                for (byte b : hashedBytes) {
                    sb.append(String.format("%02x", b));
                }
                return sb.toString();

            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException( e);
            }
        }
    }


    public String validateInputs(String username, String email, String password) {

        if (username == null || username.trim().isEmpty()) {
            return "Username is required";
        }
        if (email == null || email.trim().isEmpty()) {
            return "Email is required";
        }
        if (!isValidEmail(email)) {
            return "Invalid email format";
        }
        if (password == null || password.trim().isEmpty()) {
            return "Password is required";
        }
        if (password.length() < 3) {
            return "Password must be at least 4 characters";
        }


        return null;
    }

    public boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        return email.matches(emailRegex);
    }


    public boolean isDublicatedEmail(String email)
    {
        ArrayList<User> users = JsonFileHandler.readUsers();
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getEmail().equalsIgnoreCase(email))
            {
                return true;
            }
        }
        return false ;
    }

    public static User findUserById(String userId) {

        ArrayList<User> users = JsonFileHandler.readUsers();
        if (users == null || users.isEmpty()) {
            return null;
        }

        for (User user : users) {
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }

        return null;
    }


    public String Signup(String username, String email, String plainPassword, Boolean role) {


        String validationError = validateInputs(username, email, plainPassword);
        if (validationError != null) {
            return validationError;
        }

        ArrayList<User> users = JsonFileHandler.readUsers();
        if (users == null) {
            users = new ArrayList<>();
        }


      if(isDublicatedEmail(email))
          return "dublicated email";


        String passwordHash = PasswordHasher.hashPassword(plainPassword);


        String userId = UUID.randomUUID().toString();


        ArrayList<String> createdCourses = new ArrayList<>();


        ArrayList<String> enrolledCourses = new ArrayList<>();
        Map<String, ArrayList<String>> progress = new HashMap<>();

        User newUser;


        if (role) {
            newUser = new Instructor(userId, username, email, passwordHash, true, createdCourses);
        }

        else {
            newUser = new Student(userId, username, email, passwordHash, false, enrolledCourses, progress
            );
        }

        users.add(newUser);
        JsonFileHandler.writeUsers(users);

        return "User created successfully - Role: " + (role ? "Instructor" : "Student");
    }


    public String Login(String email, String plainPassword) {


        if (email == null || email.trim().isEmpty()) {
            return "Email is required";
        }
        if(!isValidEmail(email))
            return "invalid email";
        if (plainPassword == null || plainPassword.trim().isEmpty()) {
            return "Password is required";
        }

        ArrayList<User> users = JsonFileHandler.readUsers();
        if ( users.isEmpty()) {
            return "Email not found";
        }


        String passwordHash = PasswordHasher.hashPassword(plainPassword);

        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);

            if (u.getEmail().equalsIgnoreCase(email)) {

                if (u.getPasswordHash().equals(passwordHash)) {
                    currentUser = u;
                    String roleName = u.isRole() ? "Instructor" : "Student";
                    return "Login successful - Welcome " + u.getUserName() + " (" + roleName + ")";
                } else {
                    return "Wrong password";
                }
            }
        }

        return "Email not found";
    }


    public String Logout() {
        if (currentUser == null) {
            return "No user is logged in";
        }
        String username = currentUser.getUserName();
        currentUser = null;
        return "Logged out successfully " + username;
    }


    public User GetCurrentUser() {
        return currentUser;
    }


    public String GetCurrentUserRole() {
        if (currentUser == null) {
            return "No user logged in";
        }
        return currentUser.isRole() ? "Instructor" : "Student";
    }
}