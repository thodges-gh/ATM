import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;

/**
 * Created by Thomas Hodges on 9/9/2015.
 * Project: HodgesCMIS242Project2
 * Filename: ATM.java
 *
 * Course: CMIS 242
 * Professor: Ioan Salomie
 * Assignment: Project 2
 *
 * Platform: Windows 10, IntelliJ IDEA 14.1.4
 * Compiler: jdk1.8.0_45
 */
public class ATM extends JFrame {

    static final int WIDTH = 350, HEIGHT = 200;

    private JButton withdrawButton = new JButton("Withdraw");
    private JButton depositButton = new JButton("Deposit");
    private JButton transferToButton = new JButton("Transfer To");
    private JButton balanceButton = new JButton("Balance");
    private JRadioButton checkingRadio = new JRadioButton("Checking");
    private JRadioButton savingsRadio = new JRadioButton("Savings");
    private JTextField entry = new JTextField("");
    private ButtonGroup radios = new ButtonGroup();
    private JOptionPane frame = new JOptionPane();

    private static Account checking = new Account().new Checking();
    private static Account savings = new Account().new Savings();

    private static DecimalFormat df = new DecimalFormat("$0.00");

    public static void makeAccounts(double checkingStartingBalance,
                                    double savingsStartingBalance) {

        checking.setBalance(checkingStartingBalance);
        savings.setBalance(savingsStartingBalance);
    }

    class WithdrawButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (getEntryValue() > 0) {
                    if (checkingRadio.isSelected()) {
                        checking.withdraw(getEntryValue());
                        JOptionPane.showMessageDialog(frame, df.format(getEntryValue()) +
                                " withdrawn from Checking.");
                    } else if (savingsRadio.isSelected()) {
                        savings.withdraw(getEntryValue());
                        JOptionPane.showMessageDialog(frame, df.format(getEntryValue()) +
                                " withdrawn from Savings.");
                    } else JOptionPane.showMessageDialog(frame, "Please select an account.");
                    clearEntryValue();
                } else JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                clearEntryValue();
            } catch (InsufficientFunds insufficientFunds) {
                System.out.println("Caught in main.");
            }
        }
    }

    class DepositButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (getEntryValue() > 0) {
                if (checkingRadio.isSelected()) {
                    checking.deposit(getEntryValue());
                    JOptionPane.showMessageDialog(frame, df.format(getEntryValue()) +
                            " deposited into Checking");
                } else if (savingsRadio.isSelected()) {
                    savings.deposit(getEntryValue());
                    JOptionPane.showMessageDialog(frame, df.format(getEntryValue()) +
                            " deposited into Savings");
                } else JOptionPane.showMessageDialog(frame, "Please select an account.");
                clearEntryValue();
            } else JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
            clearEntryValue();
        }
    }

    class TransferToButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                if (getEntryValue() > 0) {
                    if (checkingRadio.isSelected()) {
                        savings.transferFrom(getEntryValue());
                        checking.transferTo(getEntryValue());
                        JOptionPane.showMessageDialog(frame, df.format(getEntryValue()) +
                                " transferred from Savings to Checking.");
                    } else if (savingsRadio.isSelected()) {
                        checking.transferFrom(getEntryValue());
                        savings.transferTo(getEntryValue());
                        JOptionPane.showMessageDialog(frame, df.format(getEntryValue()) +
                                " transferred from Checking to Savings.");
                    } else JOptionPane.showMessageDialog(frame, "Please select an account.");
                    clearEntryValue();
                } else JOptionPane.showMessageDialog(frame, "Please enter a valid number.");
                clearEntryValue();
            } catch (InsufficientFunds insufficientFunds) {
                System.out.println("Caught in main.");
            }
        }
    }

    class BalanceButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (checkingRadio.isSelected()) {
                JOptionPane.showMessageDialog(frame,
                        "Your checking account balance is: \n" +
                                df.format(checking.getBalance()));
            } else if (savingsRadio.isSelected()) {
                JOptionPane.showMessageDialog(frame,
                        "Your savings account balance is: \n" +
                                df.format(savings.getBalance()));
            } else JOptionPane.showMessageDialog(frame, "Please select an account.");
            clearEntryValue();
        }
    }

    public ATM(double checkingStartingBalance, double savingsStartingBalance) {

        super("ATM Machine");
        setFrame(WIDTH, HEIGHT);
        JPanel panel = new JPanel();
        setResizable(false);
        add(panel);
        panel.setLayout(new GridLayout(4, 2, 0, 5));
        panel.add(withdrawButton);
        panel.add(depositButton);
        panel.add(transferToButton);
        panel.add(balanceButton);
        radios.add(checkingRadio);
        radios.add(savingsRadio);
        panel.add(checkingRadio);
        panel.add(savingsRadio);
        //entry.setPreferredSize(new Dimension(250, 75));
        panel.add(entry, BorderLayout.CENTER);

        // Creates the checking and savings accounts
        makeAccounts(checkingStartingBalance, savingsStartingBalance);

        // Action listeners
        withdrawButton.addActionListener(new WithdrawButtonListener());
        depositButton.addActionListener(new DepositButtonListener());
        transferToButton.addActionListener(new TransferToButtonListener());
        balanceButton.addActionListener(new BalanceButtonListener());
    }

    public double getEntryValue() {
        try {
            return Double.parseDouble(entry.getText());
        } catch (NumberFormatException e) {
            System.out.println("Caught in getEntryValue\n");
            clearEntryValue();
            return 0;
        }
    }

    public void clearEntryValue() {
        entry.setText("");
    }

    private void setFrame(int width, int height) {
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void display() {
        setVisible(true);
    }

    public static void main(String[] args) {
        ATM frame = new ATM(100, 100);
        frame.display();
    }

}
