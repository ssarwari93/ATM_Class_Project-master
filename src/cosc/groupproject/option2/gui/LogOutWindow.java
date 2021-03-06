package cosc.groupproject.option2.gui;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.*;
import cosc.groupproject.option2.utilities.*;

/*
 * Created by Mohammad Sarwari
 */
public class LogOutWindow extends JFrame {
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();
    private LogOutWindow instance = this;

    private JTextField userField;

    private final int WIDTH;
    private final int HEIGHT;

    public LogOutWindow(int WIDTH, int HEIGHT)
    {
        this.WIDTH = WIDTH;
        this.HEIGHT = HEIGHT;

        initSetting();
        initComponents();
    }

    private void initSetting() {
        this.setSize(this.WIDTH, this.HEIGHT);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setLayout(new GridBagLayout());
    }

    private void initComponents() {

        PopWindows pop = new PopWindows("Log Out Successful", "You have been successfully logged out.", "Continue");

        JPanel userPanel = new JPanel(new FlowLayout());
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;

        JLabel userLabel = new JLabel("Account Number:");
        userPanel.add(userLabel);

        userField = new JTextField(15);
        userPanel.add(userField);

        this.add(userPanel, gridBagConstraints);

        JPanel passwordPanel = new JPanel(new FlowLayout());
        gridBagConstraints.insets = new Insets(0, 45, 0, 0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;

        JLabel passLabel = new JLabel("Password:");
        passwordPanel.add(passLabel);

        JPasswordField passField = new JPasswordField(15);
        passwordPanel.add(passField);

        this.add(passwordPanel,gridBagConstraints);

        JPanel buttonPanel = new JPanel(new FlowLayout());
        gridBagConstraints.insets = new Insets(0, 130, 0 ,0);
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;

        JButton logInButton = new JButton("Log In");
        logInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                reLogIn(userField.getText(), passField.getPassword());
            }
        });
        buttonPanel.add(logInButton);

        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.dispose();
            }
        });
        buttonPanel.add(cancelButton);

        this.add(buttonPanel, gridBagConstraints);
    }

    private void reLogIn(String user, char[] pass) {
        try {
            File logIn = new File("Password.txt");
            FileReader fileReader = new FileReader(logIn);
            BufferedReader reader = new BufferedReader(fileReader);
            String line;


            while ((line = reader.readLine()) != null) {
                String checkedValue;
                String checkedParameters;
                HashMap<String, String> map;

                int x = 0;

                do {
                    checkedValue = line.substring(x);
                    x++;
                } while(checkedValue.contains(" "));
                checkedParameters = line.substring(0, x - 2);

                map = new HashMap<String, String>();
                map.put(checkedParameters, checkedValue);

                if(map.get(user).equals(String.valueOf(pass))) {
                    PopWindows popWindows= new PopWindows("Log In Successful", "Your log in was successful.", "Proceed");

                    this.dispose();

                    MenuWindow menuWindow = new MenuWindow(References.MENU_WINDOW_WIDTH, References.MENU_WINDOW_HEIGHT, References.MENU_WINDOW_TITLE, Integer.parseInt(this.userField.getText()));
                    menuWindow.setVisible(true);
                } else {
                    PopWindows popWindows = new PopWindows("Log In Failed", "Your log has failed. Please try again.", "Return");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {

        }
    }
}

