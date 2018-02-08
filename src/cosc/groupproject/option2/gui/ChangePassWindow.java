package cosc.groupproject.option2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

//Created by Ankit Babber

public class ChangePassWindow extends JFrame {
    private ChangePassWindow instance = this;
    private int ACCOUNTNUMBER;
    private JPasswordField newPasswordField;


    public ChangePassWindow(int ACCOUNTNAME) {
        this.ACCOUNTNUMBER = ACCOUNTNAME;

        initSettings();
        initComponents();
    }

    private void initSettings() {
        this.setLayout(new GridBagLayout());
        this.setSize(400, 200);
        this.setTitle("Change Password");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents() {
        GridBagConstraints gridBagConstraints = new GridBagConstraints();

        JLabel description = new JLabel("Requesting password change for " + this.ACCOUNTNUMBER);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        this.add(description, gridBagConstraints);

        JPanel newPasswordPanel = new JPanel(new FlowLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        JLabel newPasswordLabel = new JLabel("New Password: ");
        newPasswordPanel.add(newPasswordLabel);

        newPasswordField = new JPasswordField(10);
        newPasswordPanel.add(newPasswordField);

        this.add(newPasswordPanel, gridBagConstraints);

        JButton transferButton = new JButton("Change Password");
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                changePassword(instance.ACCOUNTNUMBER, newPasswordField.getPassword());
            }
        });
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        this.add(transferButton, gridBagConstraints);
    }

    private void changePassword(int ACCOUNTNUMBER, char[] password) {
        try {
            Map<Integer, String> accountInfo = new HashMap<>();
            FileReader inputReader = new FileReader("./Password.txt");
            BufferedReader reader = new BufferedReader(inputReader);
            String line;


            while ((line = reader.readLine()) != null) {
                String checkedValue;
                String checkedParameters;


                int x = 0;

                do {
                    checkedValue = line.substring(x);
                    x++;
                } while(checkedValue.contains(" "));
                checkedParameters = line.substring(0, x - 2);

                accountInfo.put(Integer.parseInt(checkedParameters), checkedValue);
                accountInfo.put(ACCOUNTNUMBER, String.valueOf(password));

            }
            FileWriter writer = new FileWriter("./Password.txt");
            for(Map.Entry<Integer, String> entry : accountInfo.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
