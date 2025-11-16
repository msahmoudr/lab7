package PresentationLayar;

import BussinessLayer.Lesson;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AddLesson extends JFrame {
    private ArrayList<Lesson> lessons;
    private JTextField titleTextField;
    private JTextArea resourcesTextArea;
    private JPanel resourcesLabel;
    private JLabel lessonTitleLabel;
    private JButton addButton;
    private JLabel lessonContentLabel;
    private JLabel lessonResourcesLabel;
    private JTextArea lessonContentTextArea;
    private static int counter=1;

    public AddLesson(ArrayList<Lesson> lessons) {
        this.lessons =lessons;
        this.setContentPane(resourcesLabel); // Use your main panel
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setTitle("Add New Lesson");
        this.pack();
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = "l-" +String.valueOf(counter);
                String title = titleTextField.getText().trim();
                String content= lessonContentTextArea.getText().trim();
                String resource=resourcesTextArea.getText().trim();
                if(title.equals("")||content.equals("")||resource.equals(""))
                {
                    JOptionPane.showMessageDialog(AddLesson.this, "Fill the empty fields");


                }
                else{
                    ArrayList<String> resources = new ArrayList<>();
                    resources.add(resource);
                    Lesson lesson = new Lesson(id,title,content,resources);
                    AddLesson.this.lessons.add(lesson);
                    counter++;
                    AddLesson.this.dispose();

                }


            }
        });
    }
}
