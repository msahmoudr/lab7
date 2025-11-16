package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.Student;

import javax.swing.*;

public class StudentProgress extends JFrame {
    private JPanel StudentProgress;
    private JLabel lblCourseId;
    private JLabel lblCourseLessons;
    private JLabel lblCourseTitle;
    private JLabel lblCompletedLessons;
    private JProgressBar progressBar1;
    private JComboBox comboBox1;
    private JButton completeButton;
    private Course course;
    private Student  student;
    public StudentProgress(Course course, Student student) {
        this.course = course;
        this.student = student;
        if (course==null||student==null)
            throw new IllegalArgumentException("Student Data cannot be null.");
        this.student = s;
        setTitle("Student Dashboard");
        add(StudentProgress);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
    }
}
