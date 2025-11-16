package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.CourseController;
import BussinessLayer.Student;
import BussinessLayer.StudentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

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
        if (s==null)
            throw new IllegalArgumentException("Student Data cannot be null.");
        this.student = s;
        setTitle("Student Dashboard");
        add(StudentDashboard);
     
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
        prepareStudentData();
       refreshCoursesList();



        setVisible(true);

    }
    private void refreshCoursesList() {
        ArrayList<Course> courses = new CourseController().getAllCourses();

        String[] columnNames = {"CourseID", "Title","State"};
        Object[][] data = new Object[courses.size()][3];

        for (int i = 0; i < courses.size(); i++) {
            Course course = courses.get(i);
            data[i][0] = course.getCourseId();
            data[i][1] = course.getTitle();
           if(course.getEnrolledStudents().contains(student.getUserId()))
            data[i][2] = "Enrolled";
           else
            data[i][2] = "Not Enrolled";

        }

        if (availableCoursesTable != null) {
            availableCoursesTable.setModel(new DefaultTableModel(data, columnNames) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }

                @Override
                public Class getColumnClass(int columnIndex) {

                    return String.class;
                }


            });

        }


    }

    

    private void prepareStudentData()
    {
        lblEmail.setText(student.getEmail());
        lblUserName.setText(student.getUserName());
        lblStudentId.setText(student.getUserId());
    }

    public static void main(String[] args) {
        new StudentDashboard(new StudentController().getStudentById("s01"));
    }
}
