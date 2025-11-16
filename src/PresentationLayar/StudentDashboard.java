package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.CourseController;
import BussinessLayer.Student;
import BussinessLayer.StudentController;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

        availableCoursesTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {


                if (e.getClickCount() == 2) {
                    if (availableCoursesTable.getSelectedRow() == -1)
                    {
                        JOptionPane.showMessageDialog(StudentDashboard.this, "Please Select Student ID", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    else
                    {
                        String selectedCourseId = getSelectedCourseId();
                        if(!student.getEnrolledCourse().contains(selectedCourseId))
                        {
                            new StudentController().addEnrolledCourse(student.getUserId(), selectedCourseId);
                            CourseController courseController = new CourseController();
                            courseController.enrollStudent(courseController.getCourseById(selectedCourseId), student);
                            refreshCoursesList();
                        }
                    }
                }
            }
        });



        setVisible(true);

    }
    private String getSelectedCourseId()
    {

        int viewIndex = availableCoursesTable.getSelectedRow();
        int modelIndex = availableCoursesTable.convertRowIndexToModel(viewIndex);
        Object value = availableCoursesTable.getModel().getValueAt(modelIndex, 0);
        return  (String) value;
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
            data[i][2] = "avaliable";

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
