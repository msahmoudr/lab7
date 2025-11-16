package PresentationLayar;

import BussinessLayer.AuthController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Signup extends JFrame {
    private JPanel mainPanel;
    private JLabel UserName;
    private JTextField usernameField;
    private JLabel Email;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JLabel Role;
    private JComboBox roleComboBox;
    private JButton signupButton;
    private JButton backButton;

    private AuthController authController;

    public Signup() {
        authController = new AuthController();

        setTitle("Skill Forge - Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(mainPanel);
        setSize(400, 300);
        setLocationRelativeTo(null);

        setupComboBox();


        setupEventListeners();
    }

    private void setupComboBox() {

        if (roleComboBox.getItemCount() == 0) {
            roleComboBox.addItem("Student");
            roleComboBox.addItem("Instructor");
        }
    }

    private void setupEventListeners() {

        signupButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleSignUp();
            }
        });

        // زر Back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                goBackToLogin();
            }
        });

        usernameField.addActionListener(e -> handleSignUp());
        emailField.addActionListener(e -> handleSignUp());
        passwordField.addActionListener(e -> handleSignUp());
    }

    private void handleSignUp() {

        String username = usernameField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();


        boolean role = "Instructor".equals(selectedRole);


        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }


        String result = authController.Signup(username, email, password, role);


        if (result.startsWith("User created successfully")) {
            JOptionPane.showMessageDialog(this, "Account created successfully!\nYou can now login with your email and password.", "Success", JOptionPane.INFORMATION_MESSAGE);


         goBackToLogin();

        } else {
            JOptionPane.showMessageDialog(this,
                    result,
                    "Sign Up Failed",
                    JOptionPane.ERROR_MESSAGE);


            if (result.contains("Email already exists") || result.contains("dublicated email")) {
                emailField.setText("");
            }
            passwordField.setText("");
        }
    }

    private void goBackToLogin() {

        Login loginForm = new Login( authController);
        loginForm.setVisible(true);


        this.dispose();
    }


    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Signup().setVisible(true);
            }
        });
    }
}