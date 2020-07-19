package anonymousClasses;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleGUI {
    public static int clickCounter = 0;

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        JLabel label = new JLabel("");
        JButton button = new JButton(" click me ");

        // kontrolki
        label.setBounds(20, 20, 100, 20);
        frame.add(label);
        button.setBounds(20, 50, 100, 20);
        frame.add(button);

        // obsluga przycisku
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (clickCounter % 2 == 0) {
                    label.setText(" ON ");
                } else {
                    label.setText(" OFF ");
                }
                clickCounter++;
            }
        });

        // okno
        frame.setSize(200, 150);
        frame.setLayout(null);
        frame.setVisible(true);
        // kontrolki


    }

}
