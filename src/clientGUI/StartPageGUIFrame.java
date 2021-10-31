package clientGUI;

import com.ClientMain;
import connectionHandlers.ServerConnection;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class StartPageGUIFrame extends JFrame {
    private JTextPane infoText;
    private JPanel mainPanel;
    private JTextField fieldHost;
    private JTextField fieldPort;
    private JButton connectButton;
    private JLabel buttonConnect;
    private JButton xButton;

    ServerConnection serverConnection;

    public StartPageGUIFrame(ServerConnection serverConnection) {

        setContentPane(mainPanel);
        this.serverConnection = serverConnection;

        mainPanel.setBorder(new EmptyBorder(10, 20, 20, 20));
        infoText.setText("<Hier wird bald eine Dokumentation der Commands auftauchen> \n xuewrhajhsdgflkajdsrhfb  \n gjkhgkuzggk\nilzgtiulkuilziulhzoihjfdogihjfuiodhzudfhdgouisdhf\nuiwsrtfhgliouhdrgilushderkglliudyfhgdslriugh\n7346583745639847563948576");

        this.setUndecorated(true);
        setTitle("Start Page");
        setSize(800, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);
        connectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fieldHost.getText().length() > 0 && fieldPort.getText().length() > 0) {
                    try {
                        serverConnection.connectToServer(fieldHost.getText(), Integer.parseInt(fieldPort.getText()));
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(StartPageGUIFrame.this, ex, "ERROR!", JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                    }
                } else {
                    try {
                        serverConnection.connectToServer("localhost", 8080);
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(StartPageGUIFrame.this, ex, "ERROR!", JOptionPane.ERROR_MESSAGE);
                        System.exit(1);
                    }
                }
                setVisible(false);
            }
        });
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(69);
            }
        });
    }
}
