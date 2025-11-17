package PresentationLayar;

import BussinessLayer.Course;
import BussinessLayer.Instructor;
import BussinessLayer.Student;
import DataAccessLayer.JsonFileHandler;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.util.ArrayList;

public class InstructorDashboard {

    private JPanel mainPanel;
    private JTable Courses;
    private JButton addButton;
    private JButton editButton;
    private JButton deleteButton;
    private JButton viewStudentsButton;
    private JButton logoutButton;
    private JPanel InstructorDataPanal;
    private JLabel lbInstructorId;
    private JLabel lblUserName;
    private JLabel lblEmail;

    private Instructor instructor;
    private ArrayList<Course> courses;

    public InstructorDashboard(Instructor instructor) {
        this.instructor = instructor;

        try { initComponents(); } catch (Throwable ignored) {}

        ensureUIExists();
        bindInstructorToLabels();
        loadCoursesIntoTable();
        attachListeners();
    }

    private void ensureUIExists() {
        if (mainPanel == null) buildUIProgrammatically();

        if (Courses == null) Courses = new JTable();
        if (addButton == null) addButton = new JButton("Add");
        if (editButton == null) editButton = new JButton("Edit");
        if (deleteButton == null) deleteButton = new JButton("Delete");
        if (viewStudentsButton == null) viewStudentsButton = new JButton("View Students");
        if (logoutButton == null) logoutButton = new JButton("Logout");

        if (lbInstructorId == null) lbInstructorId = new JLabel("-");
        if (lblUserName == null) lblUserName = new JLabel("-");
        if (lblEmail == null) lblEmail = new JLabel("-");

        if (mainPanel.getComponentCount() == 0) {
            mainPanel.setLayout(new BorderLayout(8, 8));

            JPanel top = new JPanel(new BorderLayout());
            JPanel info = new JPanel(new GridLayout(3,2,4,4));
            info.add(new JLabel("Instructor ID:"));
            info.add(lbInstructorId);
            info.add(new JLabel("Name:"));
            info.add(lblUserName);
            info.add(new JLabel("Email:"));
            info.add(lblEmail);
            top.add(info, BorderLayout.WEST);

            JPanel buttons = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 8));
            buttons.add(addButton);
            buttons.add(editButton);
            buttons.add(deleteButton);
            buttons.add(viewStudentsButton);
            buttons.add(logoutButton);
            top.add(buttons, BorderLayout.EAST);

            mainPanel.add(top, BorderLayout.NORTH);
            mainPanel.add(new JScrollPane(Courses), BorderLayout.CENTER);
        }
    }

    private void buildUIProgrammatically() {
        mainPanel = new JPanel(new BorderLayout());
        Courses = new JTable();
        addButton = new JButton("Add");
        editButton = new JButton("Edit");
        deleteButton = new JButton("Delete");
        viewStudentsButton = new JButton("View Students");
        logoutButton = new JButton("Logout");
        lbInstructorId = new JLabel("-");
        lblUserName = new JLabel("-");
        lblEmail = new JLabel("-");
    }

    private void attachListeners() {
        addButton.addActionListener(e -> {
            AddCourse addCourse= new AddCourse(InstructorDashboard.this.instructor);
            addCourse.setVisible(true);
        });

        editButton.addActionListener(e -> onEditCourse());

        deleteButton.addActionListener(e -> onDeleteCourse());
        viewStudentsButton.addActionListener(e -> onViewStudents());
        logoutButton.addActionListener(e -> onLogout());
    }

    private void bindInstructorToLabels() {
        if (instructor != null) {
            lbInstructorId.setText(instructor.getUserId());
            lblUserName.setText(instructor.getUserName());
            lblEmail.setText(instructor.getEmail());
        }
    }

    private void loadCoursesIntoTable() {
        String[] columns = {"Course ID", "Title", "Description", "Instructor ID", "Enrolled"};
        DefaultTableModel model = new DefaultTableModel(columns, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        Courses.setModel(model);

        courses = JsonFileHandler.readCourses();
        if (courses == null || courses.isEmpty()) {
            model.addRow(new Object[]{"No courses found", "", "", "", ""});
            return;
        }

        for (Course c : courses) {
            int enrolled = (c.getEnrolledStudents() == null) ? 0 : c.getEnrolledStudents().size();

            model.addRow(new Object[]{
                    c.getCourseId(),
                    c.getTitle(),
                    c.getDescription(),
                    c.getInstructorId(),
                    enrolled
            });
        }

        Courses.setRowSorter(new TableRowSorter<>(model));
        Courses.setAutoCreateRowSorter(true);
    }

    private void onDeleteCourse() {
        int sel = Courses.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Select a course first.");
            return;
        }

        int modelRow = Courses.convertRowIndexToModel(sel);
        String courseId = String.valueOf(Courses.getModel().getValueAt(modelRow, 0));

        int ok = JOptionPane.showConfirmDialog(mainPanel,
                "Delete course " + courseId + " ?", "Confirm", JOptionPane.YES_NO_OPTION);

        if (ok != JOptionPane.YES_OPTION) return;

        courses.removeIf(c -> c.getCourseId().equals(courseId));
        JsonFileHandler.writeCourses(courses);
        loadCoursesIntoTable();
    }

    private void onViewStudents() {
        int sel = Courses.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Select a course first.");
            return;
        }

        int modelRow = Courses.convertRowIndexToModel(sel);
        String courseId = String.valueOf(Courses.getModel().getValueAt(modelRow, 0));

        Course selected = null;
        for (Course c : courses) {
            if (c.getCourseId().equals(courseId)) {
                selected = c;
                break;
            }
        }

        if (selected == null) {
            JOptionPane.showMessageDialog(mainPanel, "Course not found!");
            return;
        }

        ArrayList<String> ids = selected.getEnrolledStudents();
        ArrayList<Student> all = JsonFileHandler.returnStudents();

        String[] cols = {"Student ID", "Name", "Email"};
        DefaultTableModel model = new DefaultTableModel(cols, 0);
        JTable tbl = new JTable(model);

        for (String id : ids) {
            Student s = null;
            for (Student st : all)
                if (st.getUserId().equals(id))
                    s = st;

            if (s != null)
                model.addRow(new Object[]{s.getUserId(), s.getUserName(), s.getEmail()});
            else
                model.addRow(new Object[]{id, "Unknown", "Unknown"});
        }

        JDialog dialog = new JDialog(
                SwingUtilities.getWindowAncestor(mainPanel),
                "Students - " + courseId,
                JDialog.ModalityType.APPLICATION_MODAL
        );

        dialog.setContentPane(new JScrollPane(tbl));
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(mainPanel);
        dialog.setVisible(true);
    }

    private void onLogout() {
        Window w = SwingUtilities.getWindowAncestor(mainPanel);
        if (w != null) w.dispose();
    }

    public JPanel getMainPanel() { return mainPanel; }

    private void onEditCourse() {
        int sel = Courses.getSelectedRow();
        if (sel == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Select a course first.");
            return;
        }

        // Direct access - works if table isn't sorted/filtered
        Course selectedCourse = courses.get(sel);

        EditCourse editCoursePanel = new EditCourse(selectedCourse);
        editCoursePanel.setVisible(true);
    }

    public static void main(String[] args) {
        Instructor demo = new Instructor(
                "i01",
                "John Instructor",
                "john@mail.com",
                "12345",
                true,
                new ArrayList<>()
        );

        SwingUtilities.invokeLater(() -> {
            InstructorDashboard dash = new InstructorDashboard(demo);
            JFrame f = new JFrame("Instructor Dashboard");
            f.setContentPane(dash.getMainPanel());
            f.setSize(900, 520);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setLocationRelativeTo(null);
            f.setVisible(true);
        });
    }

  

    private void initComponents() {}
}
