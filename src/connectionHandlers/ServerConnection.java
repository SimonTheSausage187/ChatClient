package connectionHandlers;

import clientGUI.ClientGUIFrame;
import clientGUI.LoginGUIFrame;
import clientGUI.StartPageGUIFrame;
import com.ClientMain;
import utils.CommandUtils;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerConnection extends Thread{

    ClientMain clientMain = new ClientMain();
    Socket socket = null;
    public PrintWriter writer = null;
    InputStream inputStream = null;
    StartPageGUIFrame startPage = null;
    LoginGUIFrame loginPage = null;
    public BufferedReader reader = null;
    public ClientGUIFrame clientGUI = null;

    CommandUtils commands = new CommandUtils(this);
    private boolean loginDone;


    public ServerConnection () {
    }

    public void handleConnection() {
        startPage = new StartPageGUIFrame(this);
    }

    public void connectToServer(String address, int port) throws IOException {
        socket = new Socket(address, port);
        writer = new PrintWriter(socket.getOutputStream(), true);
        inputStream = socket.getInputStream();
        reader = new BufferedReader(new InputStreamReader(inputStream));
        loginPage = new LoginGUIFrame(this);
    }

    public void login(String username, String password) throws IOException {
        String loginCommand = commands.login(username, password);
        if(loginCommand != null) {
            writer.println(loginCommand);
        }
        else {
            loginPage.showLoginErrorMessage();
            System.exit(1);
        }
        loginPage.setVisible(false);
        clientGUI = new ClientGUIFrame(this, clientMain);
    }


    @Override
    public void run() {
        handleConnection();
    }
}
