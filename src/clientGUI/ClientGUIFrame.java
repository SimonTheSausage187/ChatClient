package clientGUI;

import com.ClientMain;
import connectionHandlers.ServerConnection;
import listeners.ServerOutputListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUIFrame extends JFrame{

    private JTextField userInputText;
    private JButton buttonSend;
    private JPanel mainPanel;

    public JTextPane chatText;
    private JScrollPane scroll;

    public ClientGUIFrame(ServerConnection connection, ClientMain clientMain) {
        setLayout(new GridLayout(1, 1));
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        ServerOutputListener serverOutputListener = new ServerOutputListener(connection);
        serverOutputListener.start();

        chatText.setText(chatText.getText() + "\n");
        chatText.setAutoscrolls(true);

        setContentPane(mainPanel);
        setTitle("FotzApp v1.0.0");
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clientMain.process(userInputText.getText());
                chatText.setText(chatText.getText() + userInputText.getText() +"\n");
                userInputText.setText("");
            }
        });
    }
    public void commandErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(ClientGUIFrame.this, errorMessage, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
