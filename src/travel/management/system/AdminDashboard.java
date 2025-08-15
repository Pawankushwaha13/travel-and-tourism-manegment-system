package travel.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class AdminDashboard extends JFrame {

    private JTable table;

    public AdminDashboard() {
        setTitle("Admin Dashboard");
        setLayout(null);
        setSize(950, 700);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);

        JLabel label = new JLabel("Customer Details (Admin View)");
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setBounds(320, 10, 400, 30);
        add(label);

        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(20, 60, 900, 400);
        add(scrollPane);

        loadCustomerData();

        JButton deleteBtn = new JButton("Delete Selected");
        deleteBtn.setBounds(200, 480, 160, 40);
        deleteBtn.setBackground(Color.RED);
        deleteBtn.setForeground(Color.WHITE);
        add(deleteBtn);

        deleteBtn.addActionListener(e -> deleteSelectedCustomer());

        JButton backBtn = new JButton("Back");
        backBtn.setBounds(500, 480, 120, 40);
        backBtn.setBackground(Color.BLACK);
        backBtn.setForeground(Color.WHITE);
        add(backBtn);

        backBtn.addActionListener(e -> {
            new RoleSelectionDialog().setVisible(true);
            dispose();
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void loadCustomerData() {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM customer";
            ResultSet rs = c.s.executeQuery(query);
            table.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void deleteSelectedCustomer() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row to delete.");
            return;
        }

        String username = table.getValueAt(selectedRow, 0).toString(); // assuming username is in first column
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to delete user: " + username + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                Conn c = new Conn();
                String deleteQuery = "DELETE FROM customer WHERE username = ?";
                PreparedStatement ps = c.c.prepareStatement(deleteQuery);
                ps.setString(1, username);
                int rowsAffected = ps.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Customer deleted successfully.");
                    loadCustomerData();
                } else {
                    JOptionPane.showMessageDialog(this, "Deletion failed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error while deleting.");
            }
        }
    }

    // Optional: Run independently
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AdminDashboard().setVisible(true));
    }
}
