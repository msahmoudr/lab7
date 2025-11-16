package PresentationLayar;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import BussinessLayer.*;

public class AddCourse extends JFrame {
    private boolean save =false;
    private Instructor instructor;
    private ArrayList<Lesson> lessons;
    private JPanel Container1;
    private JTextField courseTitleTextField;
    private JLabel courseTitleLabel;
    private JTextField courseIdTextField;
    private JLabel courseIdLabel;
    private JTextArea courseDescriptiontextArea;
    private JLabel courseDescriptionLabel;
    private JButton addLessonButton;
    private JButton backButton;
    private JButton saveButton1;
    private JButton idCheckerButton;

    public AddCourse(Instructor instructor) {
        this.instructor=instructor;
        this.lessons = new ArrayList<>();
        this.setContentPane(Container1);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.pack();
        this.setTitle("Add Course");
        addLessonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddLesson addLesson = new AddLesson(lessons);
                addLesson.setVisible(true);

            }
        });
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddCourse.this.dispose();            }
        });
        idCheckerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str= courseIdTextField.getText().trim();
                if(!checkId(str)|| str.equals(""))
                {
                    save = false;
                    JOptionPane.showMessageDialog(AddCourse.this, "Invalid ID");
                }
                else
                {
                    save =true;
                }
            }
        });
        saveButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = courseTitleTextField.getText().trim();
                String id= courseIdTextField.getText().trim();
                String description= courseDescriptiontextArea.getText().trim();
                if(!save)
                {
                    JOptionPane.showMessageDialog(AddCourse.this, "Check the ID first");

                }
                else if(title.equals("")||id.equals("")||description.equals(""))
                {
                    JOptionPane.showMessageDialog(AddCourse.this, "Fill the empty fields");

                }
                else
                {

                    Course course =new Course(id,title,description,instructor.getUserId(),lessons);
                    CourseController cc =new CourseController();
                    cc.createCourse(course);
                }
            }
        });
    }









    public boolean checkId(String id)
    {
        CourseController cc=new CourseController();
        for(Course course: cc.getAllCourses())
        {
            if(course.getCourseId().equals(id))
            {
                return false;
            }
        }
        return true;
    }

}

