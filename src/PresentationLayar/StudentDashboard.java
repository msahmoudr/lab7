package PresentationLayar;

import BussinessLayer.Student;

import javax.swing.*;

public class StudentDashboard extends javax.swing.JFrame {
    private JPanel StudentDashboard;
    private JPanel StudentDataPanal;
    private JTable availableCoursesTable;
    private JPanel CoursesTablePanal;
    private JLabel lblStudentId;
    private JLabel lblUserName;
    private JLabel lblEmail;
    private Student student;

    public StudentDashboard(Student s)
    {
        if (student==null)
            throw new IllegalArgumentException("Student Data cannot be null.");
        this.student = s;
        setTitle("Student Dashboard");
        add(StudentDashboard);
        add(CoursesTablePanal);
        add(StudentDataPanal);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        prepareStudentData();




        setVisible(true);

    }

    

    private void prepareStudentData()
    {
        lblEmail.setText(student.getEmail());
        lblUserName.setText(student.getUserName());
        lblStudentId.setText(student.getUserId());
    }
}
