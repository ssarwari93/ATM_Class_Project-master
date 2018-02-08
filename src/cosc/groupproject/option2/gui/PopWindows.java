package cosc.groupproject.option2.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Pandaism(Mike Nguyen) on 3/22/16.
 */
public class PopWindows extends JFrame {
    private PopWindows instance = this;

    public PopWindows(String title, String warning, String buttonMessage) {
        JLabel label = new JLabel(warning);
        JButton confirmButton = new JButton(buttonMessage);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                instance.dispose();
            }
        });

        this.setSize(label.getPreferredSize().width + 20, (confirmButton.getPreferredSize().height * 2) + label.getPreferredSize().height + 20);
        this.setLayout(new FlowLayout());
        this.setAlwaysOnTop(true);
        this.setTitle(title);
        this.setVisible(true);
        this.setLocationRelativeTo(null);

        this.add(label);
        this.add(confirmButton);
    }
}
