package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.Lesson;
import BussinessLayer.Student;
import DataAccessLayer.JsonFileHandler;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

public class RemoveLesson extends JFrame {
    private Course course;
    private JTextField idTextField;
    private JLabel idLabel;
    private JButton backButton;
    private JButton deleteButton;
    private JPanel mainPanel;

    public RemoveLesson(Course course) {
        this.course =course;
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Remove Lesson");
        this.setSize(300,300);
        this.setContentPane(mainPanel);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RemoveLesson.this.dispose();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id= idTextField.getText().trim();
                if(id.equals(""))
                {
                    JOptionPane.showMessageDialog(RemoveLesson.this, "Enter the id of the lesson");
                }else if(!lessonid(id,course))
                {
                    JOptionPane.showMessageDialog(RemoveLesson.this, "Lesson ID not found");
                }else{
                    removeLesson(id,course);
                }

            }
        });
    }
    public boolean lessonid(String id,Course course)
    {
        Iterator<Lesson> i = course.getLessons().iterator();
        while(i.hasNext())
        {
            Lesson lesson = i.next();
            if(lesson.getLessonId().equals(id))
            {
                return true;
            }
        }
        return false;
    }
    public void removeLesson(String id, Course course)
    {
        Iterator<Lesson> i = course.getLessons().iterator();
        while(i.hasNext())
        {
            Lesson lesson = i.next();
            if(lesson.getLessonId().equals(id))
            {
                i.remove();
                break;
            }
        }

    }
}