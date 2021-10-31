package clientGUI;

import com.ClientMain;
import connectionHandlers.ServerConnection;
import listeners.ServerOutputListener;
import utils.SizeCorrection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientGUIFrame extends JFrame{

    public JTextField userInputText;
    private JButton buttonSend;
    public JPanel mainPanel;

    public JEditorPane chatText;
    private JScrollPane scroll;

    public ClientGUIFrame(ServerConnection connection, ClientMain clientMain) {

        ServerOutputListener serverOutputListener = new ServerOutputListener(connection);
        serverOutputListener.start();
        SizeCorrection sizeCorrection = new SizeCorrection(this);

        chatText.setText(chatText.getText() + "\n");

        sizeCorrection.start();

        setResizable(true);
        setContentPane(mainPanel);
        setTitle("ChatClient v1.0.0");
        setSize(1600, 900);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        chatText.setMaximumSize(chatText.getSize());
        setVisible(true);
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userInputText.getText().length() > 0) {
                    clientMain.process(userInputText.getText());
                    chatText.setText(chatText.getText() + "You: " + userInputText.getText() + "\n");
                    userInputText.setText("");
                }
            }
        });
    }
    public void commandErrorMessage(String errorMessage) {
        JOptionPane.showMessageDialog(ClientGUIFrame.this, errorMessage, "Something went wrong...", JOptionPane.ERROR_MESSAGE);
    }

}
