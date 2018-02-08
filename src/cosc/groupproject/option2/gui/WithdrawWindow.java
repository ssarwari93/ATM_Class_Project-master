
package cosc.groupproject.option2.gui;

import cosc.groupproject.option2.utilities.InformationAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

//Created by Ankit Babber

public class WithdrawWindow extends JFrame {
    private WithdrawWindow instance = this;

    private int ACCOUNTNUMBER;

    public WithdrawWindow(int ACCOUNTNUMBER) {
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
        initSettings();
        initComponents();

    }

    private void initSettings() {
        this.setLayout(new GridBagLayout());
        this.setSize(400, 200);
        this.setTitle("Withdraw");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel description = new JLabel("Amount of money in account " + this.ACCOUNTNUMBER + " : $" + InformationAPI.getMoney(this.ACCOUNTNUMBER));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(description, gridBagConstraints);

        JPanel withdrawPanel = new JPanel(new FlowLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        JLabel withdrawLabel = new JLabel("Amount to withdraw: ");
        withdrawPanel.add(withdrawLabel);

        JTextField withdrawField = new JTextField(20);
        withdrawPanel.add(withdrawField);

        this.add(withdrawPanel, gridBagConstraints);

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                withdraw(Double.parseDouble(withdrawField.getText()));
                description.setText("Amount of money in account " + instance.ACCOUNTNUMBER + " : $ " + InformationAPI.getMoney(instance.ACCOUNTNUMBER));
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(withdrawButton, gridBagConstraints);


    }

    private void withdraw(double withdrawAmount) {
        if ((InformationAPI.getMoney(this.ACCOUNTNUMBER) - withdrawAmount) <= 0) {
            PopWindows error = new PopWindows("Withdraw Error", "Your account has insufficient funds or will be below $0", "Back");
            error.setVisible(true);
        } else {
            try {
                Map<Integer, ArrayList<Object>> accountMap = new HashMap<>();
                ArrayList<Integer> accounts = new ArrayList<>();

                int accountNumber;

                File accountFile = new File("AccountInformation.txt");
                Scanner scanner = new Scanner(accountFile);
                scanner.useDelimiter("\\n");

                while (scanner.hasNext()) {
                    ArrayList<Object> informationArray = new ArrayList<>();
                    accountNumber = Integer.parseInt(scanner.next());
                    accounts.add(accountNumber);
                    for (int i = 0; i < 4; i++) {
                        informationArray.add(scanner.next());
                        accountMap.put(accountNumber, informationArray);
                    }
                }

                double mainInitalBal = Double.parseDouble(accountMap.get(this.ACCOUNTNUMBER).get(2).toString());
                double mainNewBal = mainInitalBal - withdrawAmount;

                accountMap.get(this.ACCOUNTNUMBER).set(2, mainNewBal);

                FileWriter writer = new FileWriter(accountFile);

                for (int i = 0; i < accounts.size(); i++) {
                    writer.write(accounts.get(i).toString() + "\n");
                    for (int j = 0; j < 4; j++) {
                        writer.write(accountMap.get(Integer.parseInt(accounts.get(i).toString())).get(j).toString() + "\n");
                    }
                }

                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

}