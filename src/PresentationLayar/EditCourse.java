package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.CourseController;
import BussinessLayer.Lesson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EditCourse extends JFrame {
    Course course;
    private JTextField courseIdTextField;
    private JTextField courseTitleTextField;
    private JLabel courseTitleLabel;
    private JTextArea descriptionTextArea;
    private JLabel descriptionLabel;
    private JButton addLessonButton;
    private JButton removeLessonButton;
    private JButton saveButton;
    private JButton backButton;
    private JPanel mainPanel;


    public EditCourse(Course course) {
        this.course=course;
        courseIdTextField.setText(course.getCourseId());
        courseIdTextField.setEditable(false);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(400,400);
        this.setContentPane(mainPanel);
        this.setTitle("Edit Course");
        addLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLesson addLesson = new AddLesson(course.getLessons());
                addLesson.setVisible(true);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditCourse.this.dispose();
            }
        });
        removeLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveLesson removeLesson = new RemoveLesson(course);
                removeLesson.setVisible(true);


            }
        });
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = courseTitleTextField.getText().trim();
                String description = descriptionTextArea.getText().trim();
                ArrayList<Lesson> lessons=course.getLessons();
                String id =course.getCourseId();
                String insId=course.getInstructorId();
                ArrayList<String> students= course.getEnrolledStudents();
                if(title.equals("")&&description.equals(""))
                {
                    JOptionPane.showMessageDialog(EditCourse.this, "Add something to change");
                } else if(title.equals(""))
                {
                    title = course.getTitle();
                    Course newCourse =new Course(id,title,description,insId,lessons,students);
                    CourseController cc = new CourseController();
                    cc.updateCourse(newCourse);
                    JOptionPane.showMessageDialog(EditCourse.this, "Done");
                    EditCourse.this.dispose();


                }else if(description.equals(""))
                {
                    description = course.getDescription();
                    Course newCourse =new Course(id,title,description,insId,lessons,students);
                    CourseController cc = new CourseController();
                    cc.updateCourse(newCourse);
                    JOptionPane.showMessageDialog(EditCourse.this, "Done");
                    EditCourse.this.dispose();

                }
                else {
                    Course newCourse =new Course(id,title,description,insId,lessons,students);
                    CourseController cc = new CourseController();
                    cc.updateCourse(newCourse);
                    JOptionPane.showMessageDialog(EditCourse.this, "Done");
                    EditCourse.this.dispose();
                }
            }
        });
    }
}
