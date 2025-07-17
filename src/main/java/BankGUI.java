import javax.swing.*;
import java.awt.event.*;

public class BankGUI {
    private static double balance = 0.0;

    public static void main(String[] args) {
        // Show login dialog first
        boolean loggedIn = showLoginDialog();

        if (loggedIn) {
            // If login successful, show main app
            launchBankApp();
        } else {
            JOptionPane.showMessageDialog(null, "Too many failed attempts. Exiting.");
            System.exit(0);
        }
    }

    private static boolean showLoginDialog() {
        String correctUsername = "admin";
        String correctPassword = "admin123";
        int attempts = 0;

        while (attempts < 3) {
            JPanel panel = new JPanel();
            JLabel userLabel = new JLabel("Username:");
            JLabel passLabel = new JLabel("Password:");
            JTextField userField = new JTextField(10);
            JPasswordField passField = new JPasswordField(10);

            panel.add(userLabel);
            panel.add(userField);
            panel.add(passLabel);
            panel.add(passField);

            int result = JOptionPane.showConfirmDialog(null, panel, 
                "Login", JOptionPane.OK_CANCEL_OPTION);

            if (result == JOptionPane.OK_OPTION) {
                String username = userField.getText();
                String password = new String(passField.getPassword());

                if (username.equals(correctUsername) && password.equals(correctPassword)) {
                    return true;
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid credentials. Try again.");
                    attempts++;
                }
            } else {
                return false; // Cancel button clicked
            }
        }

        return false; // Too many failed attempts
    }

    private static void launchBankApp() {
        JFrame frame = new JFrame("Banking Program");
        frame.setSize(300, 300);
        frame.setLayout(null);

        JLabel label = new JLabel("Welcome to the Bank!");
        label.setBounds(70, 20, 200, 30);
        frame.add(label);

        JButton showBalanceBtn = new JButton("Show Balance");
        showBalanceBtn.setBounds(70, 60, 150, 30);
        frame.add(showBalanceBtn);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(70, 100, 150, 30);
        frame.add(depositBtn);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(70, 140, 150, 30);
        frame.add(withdrawBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(70, 180, 150, 30);
        frame.add(exitBtn);

        showBalanceBtn.addActionListener(e -> 
            JOptionPane.showMessageDialog(frame, "Current Balance: $" + String.format("%.2f", balance))
        );

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    JOptionPane.showMessageDialog(frame, "Deposited $" + amount);
                } else {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });

        withdrawBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter withdrawal amount:");
            try {
                double amount = Double.parseDouble(input);
                if (amount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                } else if (amount < 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive.");
                } else {
                    balance -= amount;
                    JOptionPane.showMessageDialog(frame, "Withdrew $" + amount);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });

        exitBtn.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Thank you! Goodbye!");
            frame.dispose();
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
