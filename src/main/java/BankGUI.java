import javax.swing.*;
import java.awt.event.*;

public class BankGUI {
    private static double balance = 0.0;

    public static void main(String[] args) {
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
            frame.dispose();  // close the window
        });

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
