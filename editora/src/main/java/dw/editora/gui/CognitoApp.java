package dw.editora.gui;

import dw.editora.CognitoClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CognitoApp {

    private JFrame frame;
    private CognitoClient cognitoClient = new CognitoClient();

    public CognitoApp() {
        initializeUI();
    }

    private void initializeUI() {
        frame = new JFrame("Cognito Authentication");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new CardLayout());

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                cognitoClient.signIn(username, password);
                JOptionPane.showMessageDialog(frame, "Login Attempted");
            }
        });
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        loginPanel.add(new JLabel());
        loginPanel.add(loginButton);

        JPanel registerPanel = new JPanel(new GridLayout(4, 2));
        JTextField regUsernameField = new JTextField();
        JPasswordField regPasswordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JButton registerButton = new JButton("Register");
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = regUsernameField.getText();
                String password = new String(regPasswordField.getPassword());
                String email = emailField.getText();
                cognitoClient.signUp(username, password, email);
                JOptionPane.showMessageDialog(frame, "Registration Attempted");
            }
        });
        registerPanel.add(new JLabel("Username:"));
        registerPanel.add(regUsernameField);
        registerPanel.add(new JLabel("Password:"));
        registerPanel.add(regPasswordField);
        registerPanel.add(new JLabel("Email:"));
        registerPanel.add(emailField);
        registerPanel.add(new JLabel());
        registerPanel.add(registerButton);

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Login", loginPanel);
        tabbedPane.addTab("Register", registerPanel);

        frame.add(tabbedPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new CognitoApp();
    }
}
