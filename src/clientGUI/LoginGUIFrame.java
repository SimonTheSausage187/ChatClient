package clientGUI;

import connectionHandlers.ServerConnection;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LoginGUIFrame extends JFrame{
    private JTextField name;
    private JLabel passwordLabel;
    private JLabel usernameLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JPasswordField pwd;

    ServerConnection connection;

    public LoginGUIFrame(ServerConnection connection) {

        this.connection = connection;

        setContentPane(mainPanel);
        setTitle("Login");
        setSize(800, 480);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        setVisible(true);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    connection.login(name.getText(), pwd.getText());
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(LoginGUIFrame.this, ex, "ERROR!", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


    }

    public void showLoginErrorMessage() {
        JOptionPane.showMessageDialog(LoginGUIFrame.this, "Please restart the program and enter a valid username/password", "ERROR!", JOptionPane.ERROR_MESSAGE);
    }

}
