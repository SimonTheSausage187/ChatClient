package clientGUI;

import listeners.ServerOutputListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InfoPane extends JFrame{
    private JTextArea infoText;
    private JButton closeButton;
    private JPanel mainPanel;

    public InfoPane(String text) {

        infoText.setText(text);


        setContentPane(mainPanel);
        setTitle("ChatClient v1.0.0");
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

    }
}
