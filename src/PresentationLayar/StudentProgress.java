package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.Student;

import javax.swing.*;

public class StudentProgress extends JFrame {
    private JPanel StudentProgress;
    private JLabel lblCourseId;
    private JLabel lblCourseTitle;
    private JProgressBar progressBar1;
    private JComboBox cbNotCompletedLesson;
    private JButton completeButton;
    private JPanel CouseInfoPanel;
    private JPanel ProgressParPanel;
    private JPanel CompleteLessonPanel;
    private JComboBox cbLessonToStudy;
    private JButton btnSearch;
    private JComboBox cbresources;
    private JLabel lblLessonTitle;
    private JLabel lblContentLesson;
    private JComboBox cbCourseLessons;
    private JComboBox cbCompletedLesson;
    private Course course;
    private Student  student;

}
