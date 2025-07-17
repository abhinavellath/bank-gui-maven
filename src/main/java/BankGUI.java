import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class BankGUI {
    private static double balance = 0.0;
    private static List<String> transactions = new ArrayList<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("Banking Program");
        frame.setSize(350, 350);
        frame.setLayout(null);

        JLabel label = new JLabel("Welcome to the Bank!");
        label.setBounds(90, 20, 200, 30);
        frame.add(label);

        JButton showBalanceBtn = new JButton("Show Balance");
        showBalanceBtn.setBounds(90, 60, 150, 30);
        frame.add(showBalanceBtn);

        JButton depositBtn = new JButton("Deposit");
        depositBtn.setBounds(90, 100, 150, 30);
        frame.add(depositBtn);

        JButton withdrawBtn = new JButton("Withdraw");
        withdrawBtn.setBounds(90, 140, 150, 30);
        frame.add(withdrawBtn);

        JButton historyBtn = new JButton("View History");
        historyBtn.setBounds(90, 180, 150, 30);
        frame.add(historyBtn);

        JButton exitBtn = new JButton("Exit");
        exitBtn.setBounds(90, 220, 150, 30);
        frame.add(exitBtn);

        showBalanceBtn.addActionListener(e -> 
            JOptionPane.showMessageDialog(frame, "Current Balance: $" + String.format("%.2f", balance))
        );

        depositBtn.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(frame, "Enter deposit amount:");
            try {
                if (input == null || input.trim().isEmpty()) return;
                double amount = Double.parseDouble(input);
                if (amount > 0) {
                    balance += amount;
                    transactions.add("Deposited $" + String.format("%.2f", amount));
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
                if (input == null || input.trim().isEmpty()) return;
                double amount = Double.parseDouble(input);
                if (amount > balance) {
                    JOptionPane.showMessageDialog(frame, "Insufficient balance.");
                } else if (amount < 0) {
                    JOptionPane.showMessageDialog(frame, "Amount must be positive.");
                } else {
                    balance -= amount;
                    transactions.add("Withdrew $" + String.format("%.2f", amount));
                    JOptionPane.showMessageDialog(frame, "Withdrew $" + amount);
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input.");
            }
        });

        historyBtn.addActionListener(e -> {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "No transactions yet.");
            } else {
                StringBuilder history = new StringBuilder("Transaction History:\n");
                for (String t : transactions) {
                    history.append(t).append("\n");
                }
                JOptionPane.showMessageDialog(frame, history.toString());
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
