package BussinessLayer;

import DataAccessLayer.JsonFileHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StudentController {

    private final ArrayList<Student> students;

    public StudentController() {
        ArrayList<Student> loaded = JsonFileHandler.returnStudents();
        if (loaded == null) this.students = new ArrayList<>();
        else this.students = new ArrayList<>(loaded);
    }

    public Student getStudentById(String studentId) {
        if (studentId == null) return null;
        for (Student s : students) {
            if (s != null && studentId.equals(s.getUserId())) return s;
        }
        return null;
    }

    public ArrayList<String> getEnrolledCourses(String studentId) {
        Student s = getStudentById(studentId);
        if (s == null) return new ArrayList<>();
        ArrayList<String> list = s.getEnrolledCourse();
        return list == null ? new ArrayList<>() : new ArrayList<>(list);
    }

    public boolean addEnrolledCourse(String studentId, String courseId) {
        if (studentId == null || courseId == null) return false;
        Student s = getStudentById(studentId);
        if (s == null) return false;
        ArrayList<String> enrolled = s.getEnrolledCourse();
        if (enrolled == null) {
            enrolled = new ArrayList<>();
            try {
                var f = Student.class.getDeclaredField("enrolledCourse");
                f.setAccessible(true);
                f.set(s, enrolled);
            } catch (Exception ignored) {}
        }
        if (!enrolled.contains(courseId)) enrolled.add(courseId);
        return saveAll();
    }

    public Map<String, ArrayList<String>> getProgress(String studentId) {
        Student s = getStudentById(studentId);
        if (s == null) return Collections.emptyMap();
        Map<String, ArrayList<String>> src = s.getProgress();
        if (src == null) return Collections.emptyMap();
        Map<String, ArrayList<String>> copy = new HashMap<>();
        for (Map.Entry<String, ArrayList<String>> e : src.entrySet()) {
            ArrayList<String> list = e.getValue();
            copy.put(e.getKey(), list == null ? new ArrayList<>() : new ArrayList<>(list));
        }
        return copy;
    }

    public boolean updateProgress(String studentId, String courseId, String lessonId) {
        if (studentId == null || courseId == null || lessonId == null) return false;
        Student s = getStudentById(studentId);
        if (s == null) return false;
        Map<String, ArrayList<String>> progress = s.getProgress();
        if (progress == null) {
            progress = new HashMap<>();
            try {
                var f = Student.class.getDeclaredField("progress");
                f.setAccessible(true);
                f.set(s, progress);
            } catch (Exception ignored) {}
        }
        ArrayList<String> lessons = progress.get(courseId);
        if (lessons == null) {
            lessons = new ArrayList<>();
            progress.put(courseId, lessons);
        }
        if (!lessons.contains(lessonId)) lessons.add(lessonId);
        return saveAll();
    }

    public boolean updateStudent(Student updated) {
        if (updated == null || updated.getUserId() == null) return false;
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s != null && updated.getUserId().equals(s.getUserId())) {
                students.set(i, updated);
                return saveAll();
            }
        }
        return false;
    }

    public boolean saveAll() {
        ArrayList<User> users = JsonFileHandler.readUsers();
        if (users == null) users = new ArrayList<>();
        for (Student st : students) {
            for (int i = 0; i < users.size(); i++) {
                User u = users.get(i);
                if (u != null && st.getUserId().equals(u.getUserId())) {
                    users.set(i, st);
                    break;
                }
            }
        }
        JsonFileHandler.writeUsers(users);
        return true;
    }
}
