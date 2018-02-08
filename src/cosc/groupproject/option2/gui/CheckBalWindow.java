package cosc.groupproject.option2.gui;
import java.awt.*;
import javax.swing.*;
import cosc.groupproject.option2.utilities.InformationAPI;

/*
 * Created by Mohammad Sarwari
 */
public class CheckBalWindow extends JFrame {
    private CheckBalWindow instance = this;
    private int ACCOUNTNUMBER;

    public CheckBalWindow(int ACCOUNTNUMBER)
    {
        this.ACCOUNTNUMBER = ACCOUNTNUMBER;
        initSettings();
        initComponents();
    }

    private void initSettings()
    {
        this.setLayout(new GridLayout(3, 1));
        this.setSize(400,300);
        this.setTitle("Check Balance");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
    }

    private void initComponents()
    {
        JLabel name = new JLabel("Name: " + InformationAPI.getFirstName(this.ACCOUNTNUMBER)+ " " + InformationAPI.getLastName(this.ACCOUNTNUMBER));
        this.add(name);
        name.setHorizontalAlignment(JLabel.CENTER);

        JLabel acc = new JLabel("Account Number: " + InformationAPI.getAccountNumber(this.ACCOUNTNUMBER));
        this.add(acc);
        acc.setHorizontalAlignment(JLabel.CENTER);

        JLabel balance = new JLabel("Available Balance: " + InformationAPI.getMoney(this.ACCOUNTNUMBER));
        this.add(balance);
        balance.setHorizontalAlignment(JLabel.CENTER);
    }

}

