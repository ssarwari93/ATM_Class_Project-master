package cosc.groupproject.option2.utilities;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by Pandaism(Mike Nguyen) on 3/29/16.
 */
public class InformationAPI {

    public static boolean exists(int ACCOUNTNUMBER) {
        return ACCOUNTNUMBER == getAccountNumber(ACCOUNTNUMBER);
    }

    //getters
    public static int getAccountNumber(int ACCOUNTNUMBER) {
        try {
            File accountFile = new File("AccountInformation.txt");
            Scanner scanner = new Scanner(accountFile);
            scanner.useDelimiter("\\n");
            String line;

            while(scanner.hasNext()) {
                if((line = scanner.next()).equals(String.valueOf(ACCOUNTNUMBER))) {
                    int accountNumber = Integer.parseInt(line);

                    return accountNumber;

                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getLastName(int ACCOUNTNUMBER) {
        try {
            File accountFile = new File("AccountInformation.txt");
            Scanner scanner = new Scanner(accountFile);
            scanner.useDelimiter("\\n");
            String line;

            while(scanner.hasNext()) {
                if((line = scanner.next()).equals(String.valueOf(ACCOUNTNUMBER))) {
                    int accountNumber = Integer.parseInt(line);
                    String Lname = scanner.next();

                    return Lname;

                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getFirstName(int ACCOUNTNUMBER) {
        try {
            File accountFile = new File("AccountInformation.txt");
            Scanner scanner = new Scanner(accountFile);
            scanner.useDelimiter("\\n");
            String line;

            while(scanner.hasNext()) {
                if((line = scanner.next()).equals(String.valueOf(ACCOUNTNUMBER))) {
                    int accountNumber = Integer.parseInt(line);
                    String Lname = scanner.next();
                    String Fname = scanner.next();

                    return Fname;
                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static double getMoney(int ACCOUNTNUMBER) {
        try {
            File accountFile = new File("AccountInformation.txt");
            Scanner scanner = new Scanner(accountFile);
            scanner.useDelimiter("\\n");
            String line;

            while(scanner.hasNext()) {
                if((line = scanner.next()).equals(String.valueOf(ACCOUNTNUMBER))) {
                    int accountNumber = Integer.parseInt(line);
                    String Lname = scanner.next();
                    String Fname = scanner.next();
                    double money = Double.parseDouble(scanner.next());

                    return money;

                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static String getStatus(int ACCOUNTNUMBER) {
        try {
            File accountFile = new File("AccountInformation.txt");
            Scanner scanner = new Scanner(accountFile);
            scanner.useDelimiter("\\n");
            String line;

            while(scanner.hasNext()) {
                if((line = scanner.next()).equals(String.valueOf(ACCOUNTNUMBER))) {
                    int accountNumber = Integer.parseInt(line);
                    String Lname = scanner.next();
                    String Fname = scanner.next();
                    double money = Double.parseDouble(scanner.next());
                    String status = scanner.next();

                    return status;

                }

            }

        } catch(IOException e) {
            e.printStackTrace();
        }
        return "Disable";
    }
}
