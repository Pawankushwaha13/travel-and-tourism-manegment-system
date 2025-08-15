package travel.management.system;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class RoleSelectionDialog extends JFrame {
    public RoleSelectionDialog() {
        setTitle("Select Role");
        setLayout(null);
        getContentPane().setBackground(Color.WHITE);
        setSize(400, 300);
        setLocationRelativeTo(null);

        JLabel heading = new JLabel("Select Role");
        heading.setFont(new Font("Tahoma", Font.BOLD, 24));
        heading.setBounds(130, 30, 200, 30);
        add(heading);

        JButton adminButton = new JButton("Admin");
        adminButton.setBounds(130, 90, 120, 40);
        adminButton.setBackground(new Color(30, 144, 255));
        adminButton.setForeground(Color.WHITE);
        add(adminButton);

        JButton customerButton = new JButton("Customer");
        customerButton.setBounds(130, 150, 120, 40);
        customerButton.setBackground(new Color(34, 139, 34));
        customerButton.setForeground(Color.WHITE);
        add(customerButton);

        // Admin login navigation
        adminButton.addActionListener(e -> {
            new AdminLogin().setVisible(true);
            dispose(); // close this dialog
        });

        // Customer login navigation
        customerButton.addActionListener(e -> {
            new Login().setVisible(true); // Assuming your existing customer login class is Login.java
            dispose();
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        new RoleSelectionDialog().setVisible(true);
    }
}
