package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AdminLogin extends JFrame {
    AdminLogin() {
        setTitle("Admin Login");
        setLayout(null);
        setSize(400, 300);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 60, 100, 30);
        add(userLabel);

        JTextField userField = new JTextField();
        userField.setBounds(150, 60, 180, 30);
        add(userField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 110, 100, 30);
        add(passLabel);

        JPasswordField passField = new JPasswordField();
        passField.setBounds(150, 110, 180, 30);
        add(passField);

        JButton loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 160, 100, 35);
        loginBtn.setBackground(new Color(30, 144, 255));
        loginBtn.setForeground(Color.WHITE);
        add(loginBtn);

        // Login Action
        loginBtn.addActionListener(e -> {
            String username = userField.getText();
            String password = new String(passField.getPassword());

            if (username.equals("admin") && password.equals("admin123")) {
                new AdminDashboard().setVisible(true);
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
