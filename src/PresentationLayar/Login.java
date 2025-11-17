package PresentationLayar;

import BussinessLayer.AuthController;
import BussinessLayer.Instructor;
import BussinessLayer.StudentController;
import BussinessLayer.User;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    private JPanel mainPanel;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton signUpButton;

    private AuthController authController;

    public Login(AuthController authController) {
        this.authController = new AuthController();

        setTitle("Skill Forge - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400, 400);
        setLocationRelativeTo(null);

        setupEventListeners();
    }

    private void setupEventListeners() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogin();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openSignUpForm();
            }
        });

        emailField.addActionListener(e -> handleLogin());
        passwordField.addActionListener(e -> handleLogin());
    }

    private void handleLogin() {
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String result = authController.Login(email, password);

        if (result.startsWith("Login successful")) {
            JOptionPane.showMessageDialog(this, result, "Success", JOptionPane.INFORMATION_MESSAGE);

            String role = authController.GetCurrentUserRole();
            dispose();
             User logged=authController.GetCurrentUser();
            if ("Instructor".equals(role)) {

                Instructor instr = (Instructor) logged;

                InstructorDashboard dash = new InstructorDashboard(instr);

                JFrame f = new JFrame("Instructor Dashboard");
                f.setContentPane(dash.getMainPanel());
                f.setSize(900, 520);
                f.setLocationRelativeTo(null);
                f.setVisible(true);

                dispose();
            }
                else {
                new StudentDashboard(new StudentController().getStudentById(authController.GetCurrentUser().getUserId()));
            }

        } else {
            JOptionPane.showMessageDialog(this, result, "Login Failed", JOptionPane.ERROR_MESSAGE);
            passwordField.setText("");
        }
    }

    private void openSignUpForm() {
        Signup signUpForm = new Signup();
        signUpForm.setVisible(true);
        this.dispose();
    }



    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> new Login(new AuthController()).setVisible(true));
    }
}
