package PresentationLayar;

import BussinessLayer.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

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
    public StudentProgress(Course course, Student student) {
        this.course = course;
        this.student = student;
        System.out.println("dsgsfg");
        if (course==null||student==null)
            throw new IllegalArgumentException("Student Data cannot be null.");
        setTitle("Student Dashboard");
        add(StudentProgress);
        progressBar1.setMinimum(0);
        progressBar1.setMaximum(100);
        progressBar1.setStringPainted(true);
        setResizable(false);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        prepareForm();
        pack();
        btnSearch.addActionListener(new  ActionListener() {
                                        public void actionPerformed(ActionEvent e)
                                        {
                                            Lesson lesson = null;
                                            for(Lesson l:course.getLessons())
                                            {
                                                if(l.getLessonId().equals(cbLessonToStudy.getSelectedItem().toString()))
                                                {
                                                    lesson = l;
                                                }
                                            }
                                            if(lesson==null)
                                                return;
                                            lblLessonTitle.setText(lesson.getTitle());
                                            lblContentLesson.setText(lesson.getContent());
                                            prepareResourcesComboBox();
                                        }
                                    }

        );
        completeButton.addActionListener(new  ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                completeLesson();

            }

        });


        setVisible(true);
    }
    private void completeLesson ()
    {
        StudentController controller=new StudentController();
        controller.updateProgress(student.getUserId(),course.getCourseId(), cbNotCompletedLesson.getSelectedItem().toString());
        controller = new StudentController();
        this.student = controller.getStudentById(student.getUserId());
        prepareForm();
    }

    private void prepareForm()
    {
        lblCourseId.setText(course.getCourseId());
        lblCourseTitle.setText(course.getTitle());
        prepareCourseLessonComboBox();
        prepareCompletedLessonComboBox();
        prepareLessonToStudyComboBox();
        prepareResourcesComboBox();
        preareProgressBar();
    }
    private void preareProgressBar()
    {
        ArrayList<String> lessons = student.getProgress().get(course.getCourseId());
        if(lessons==null)
            return;
        progressBar1.setValue((lessons.size()/course.getLessons().size())*100);
    }

    private void prepareCourseLessonComboBox()
    {
        cbCourseLessons.removeAllItems();
        if(course.getLessons() == null)
            return;
        for (Lesson lesson : course.getLessons())
        {
            cbCourseLessons.addItem(lesson.getLessonId());
        }
        cbCourseLessons.setSelectedIndex(0);
        cbCourseLessons.setEditable(false);
    }


    private void prepareCompletedLessonComboBox()
    {
        cbCompletedLesson.removeAllItems();
        ArrayList<String> lessons = student.getProgress().get(course.getCourseId());
        if (lessons==null)
        {
            return;
        }
        for (String s : lessons)
        {
            cbCompletedLesson.addItem(s);
        }
        cbCompletedLesson.setSelectedIndex(0);
        cbCompletedLesson.setEditable(false);
    }



    private void prepareLessonToStudyComboBox()
    {
        cbLessonToStudy.removeAllItems();
        if(course.getLessons() == null)
            return;
        for (Lesson lesson : course.getLessons())
        {
            cbLessonToStudy.addItem(lesson.getLessonId());
        }
        cbLessonToStudy.setSelectedIndex(0);
        cbLessonToStudy.setEditable(false);
    }


    private void prepareResourcesComboBox()
    {
        cbresources.removeAllItems();
        ArrayList<String> resources = null;
        for (Lesson lesson : course.getLessons())
        {
            if(lesson.getLessonId().equals(cbLessonToStudy.getSelectedItem().toString()))
                resources =  lesson.getResources();
        }
        if(resources == null)
            return;
        for (String s : resources)
        {
            cbresources.addItem(s);
        }
        cbresources.setSelectedIndex(0);
        cbresources.setEditable(false);
    }
    private void prepareNotCompletedLessonComboBox()
    {
        cbNotCompletedLesson.removeAllItems();
        ArrayList<String> lessons = student.getProgress().get(course.getCourseId());
        for(Lesson lesson : course.getLessons() )
        {
            if(!lessons.contains(lesson.getLessonId()))
                cbNotCompletedLesson.addItem(lesson.getLessonId());
        }
        cbNotCompletedLesson.setSelectedIndex(0);
        cbNotCompletedLesson.setEditable(false);
    }
}
