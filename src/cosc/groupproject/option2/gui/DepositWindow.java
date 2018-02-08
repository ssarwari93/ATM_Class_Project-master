package cosc.groupproject.option2.gui;

import cosc.groupproject.option2.utilities.InformationAPI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by Pandaism(Mike Nguyen) on 3/28/16.
 */
public class DepositWindow extends JFrame {
    private DepositWindow instance = this;

    private int ACCOUNTNUMBER;

    private JLabel currentAmount;

    public DepositWindow(int ACCOUNTNUMBER) {
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;

        initSettings();
        initComponents();
    }

    private void initSettings() {
        this.setLayout(new GridBagLayout());
        this.setSize(400, 200);
        this.setTitle("Deposit");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        currentAmount = new JLabel("Amount of Money in account " + InformationAPI.getAccountNumber(this.ACCOUNTNUMBER) + ": $" + InformationAPI.getMoney(this.ACCOUNTNUMBER));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(currentAmount, gridBagConstraints);

        JPanel depositPanel = new JPanel(new FlowLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        JLabel depositLabel = new JLabel("Enter your desire amount: ");
        depositPanel.add(depositLabel);

        JTextField depositField = new JTextField(10);
        depositPanel.add(depositField);
        this.add(depositPanel, gridBagConstraints);

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deposit(Double.parseDouble(depositField.getText()));
                currentAmount.setText("Amount of Money in account " + InformationAPI.getAccountNumber(instance.ACCOUNTNUMBER) + ": $" + InformationAPI.getMoney(instance.ACCOUNTNUMBER));
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new Insets(30, 0, 0, 0);
        this.add(depositButton, gridBagConstraints);
    }

    private void deposit(double depositAmount) {
        try {
            Map<Integer, ArrayList<Object>> accountMap = new HashMap<>();
            ArrayList<Integer> accounts = new ArrayList<>();

            int accountNumber;

            File accountFile = new File("AccountInformation.txt");
            Scanner scanner = new Scanner(accountFile);
            scanner.useDelimiter("\\n");

            while(scanner.hasNext()) {
                ArrayList<Object> informationArray = new ArrayList<>();
                accountNumber = Integer.parseInt(scanner.next());
                accounts.add(accountNumber);
                for(int i = 0; i < 4; i++) {
                    informationArray.add(scanner.next());
                    accountMap.put(accountNumber, informationArray);
                }
            }

            double initalBal = Double.parseDouble(accountMap.get(this.ACCOUNTNUMBER).get(2).toString());
            double newBal = initalBal + depositAmount;

            accountMap.get(this.ACCOUNTNUMBER).set(2, newBal);

            FileWriter writer = new FileWriter(accountFile);

            for(int i = 0; i < accounts.size(); i++) {
                writer.write(accounts.get(i).toString() + "\n");
                for(int j = 0; j < 4; j++) {
                    writer.write(accountMap.get(Integer.parseInt(accounts.get(i).toString())).get(j).toString() + "\n");
                }
            }

            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
