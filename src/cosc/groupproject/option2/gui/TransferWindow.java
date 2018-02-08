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

/**
 * Created by Pandaism(Mike Nguyen) on 4/7/16.
 */
public class TransferWindow extends JFrame {
    private TransferWindow instance = this;

    private int ACCOUNTNUMBER;

    public TransferWindow(int ACCOUNTNUMBER) {
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;

        initSettings();
        initComponents();
    }

    private void initSettings() {
        this.setLayout(new GridBagLayout());
        this.setSize(400, 200);
        this.setTitle("Transfer");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel description = new JLabel("Account of money in account " + this.ACCOUNTNUMBER + ": $" + InformationAPI.getMoney(this.ACCOUNTNUMBER));
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(description, gridBagConstraints);

        JPanel transferToPanel = new JPanel(new FlowLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        JLabel transferToLabel = new JLabel("Account to be transferred to: ");
        transferToPanel.add(transferToLabel);

        JTextField transferToField = new JTextField(10);
        transferToPanel.add(transferToField);

        this.add(transferToPanel, gridBagConstraints);

        JPanel transferAmountPanel = new JPanel(new FlowLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;

        JLabel transferAmountLabel = new JLabel("Amount being transferred:");
        transferAmountPanel.add(transferAmountLabel);

        JTextField tranferAmountField = new JTextField(10);
        transferAmountPanel.add(tranferAmountField);

        this.add(transferAmountPanel, gridBagConstraints);

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                transfer(Double.parseDouble(tranferAmountField.getText()), Integer.parseInt(transferToField.getText()));
                description.setText("Account of money in account " + instance.ACCOUNTNUMBER + ": $" + InformationAPI.getMoney(instance.ACCOUNTNUMBER));
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        this.add(transferButton, gridBagConstraints);
    }

    private void transfer(double transferAmount, int transferToAccount) {
        if(InformationAPI.getAccountNumber(transferToAccount) == this.ACCOUNTNUMBER) {
            PopWindows error = new PopWindows("Account Error", "You cannot transfer to your own account", "Back");
            error.setVisible(true);
        } else if(InformationAPI.exists(transferToAccount)) {
            if((InformationAPI.getMoney(this.ACCOUNTNUMBER) - transferAmount) <= 1) {
                PopWindows error = new PopWindows("Account Error", "Your account have insufficient funds or will be below $1", "Back");
                error.setVisible(true);
            } else {
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

                    double mainInitalBal = Double.parseDouble(accountMap.get(this.ACCOUNTNUMBER).get(2).toString());
                    double mainNewBal = mainInitalBal - transferAmount;

                    double transferedIntialBal = Double.parseDouble(accountMap.get(transferToAccount).get(2).toString());
                    double transferedNewBal = transferedIntialBal + transferAmount;

                    accountMap.get(this.ACCOUNTNUMBER).set(2, mainNewBal);
                    accountMap.get(transferToAccount).set(2, transferedNewBal);

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
        } else  {
            PopWindows error = new PopWindows("Account Error", "The account " + transferToAccount + " was not found", "Back");
            error.setVisible(true);
        }
    }

}
