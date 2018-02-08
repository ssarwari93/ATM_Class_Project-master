package cosc.groupproject.option2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pandaism(Mike Nguyen) on 3/22/16.
 */
public class MenuWindow extends JFrame {
    private MenuWindow instance = this;

    private final int WIDTH;
    private final int HEIGHT;
    private final String TITLE;
    private int ACCOUNTNUMBER;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();

    public MenuWindow(int WIDTH, int HEIGHT, String TITLE, int ACCOUNTNUMBER) {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;
        this.TITLE = TITLE;
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;

        initSettings();
        initComponents();
    }

    private void initSettings() {
        this.setLayout(new GridBagLayout());
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setTitle(this.TITLE);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents() {

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DepositWindow depositWindow = new DepositWindow(instance.ACCOUNTNUMBER);
                depositWindow.setVisible(true);
            }
        });
        depositButton.setPreferredSize(new Dimension(120, 50));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 30, 60);
        this.add(depositButton, gridBagConstraints);

        JButton checkbalButton = new JButton("<html>Check<br/>Balance</html>");
        checkbalButton.setPreferredSize(new Dimension(120, 50));
        checkbalButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CheckBalWindow checkBalanceWindow = new CheckBalWindow(instance.ACCOUNTNUMBER);
                checkBalanceWindow.setVisible(true);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        this.add(checkbalButton, gridBagConstraints);

        JButton passwordButton = new JButton("<html>Change<br/>Password</html>");
        passwordButton.setPreferredSize(new Dimension(120, 50));
        passwordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ChangePassWindow changePassWindow = new ChangePassWindow(instance.ACCOUNTNUMBER);
                changePassWindow.setVisible(true);
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(passwordButton, gridBagConstraints);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.setPreferredSize(new Dimension(120, 50));
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WithdrawWindow withdrawWindow = new WithdrawWindow(instance.ACCOUNTNUMBER);
                withdrawWindow.setVisible(true);
            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new Insets(0, 0, 30, 0);
        this.add(withdrawButton, gridBagConstraints);

        JButton transferButton = new JButton("<html>Make a<br/>Transfer</html>");
        transferButton.setPreferredSize(new Dimension(120, 50));
        transferButton.addActionListener(new ActionListener() {
            @Override public void actionPerformed(ActionEvent e) {
                TransferWindow transferWindow = new TransferWindow(instance.ACCOUNTNUMBER);
                transferWindow.setVisible(true);
            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 1;
        this.add(transferButton, gridBagConstraints);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(120, 50));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LogOutWindow logOffWindow = new LogOutWindow(WIDTH, HEIGHT);
                logOffWindow.setVisible(true);

                instance.dispose();
            }
        });
        gridBagConstraints.gridx = 1;
        gridBagConstraints.gridy = 2;
        this.add(logoutButton, gridBagConstraints);
    }
}
