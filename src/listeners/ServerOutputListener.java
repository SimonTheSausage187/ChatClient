package listeners;

import connectionHandlers.ServerConnection;
import utils.TextUtils;

import java.io.IOException;

public class ServerOutputListener extends Thread{

    ServerConnection connection;
    TextUtils t = new TextUtils();
    boolean kill = false;

    public ServerOutputListener(ServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (!kill) {
            String serverOutput = "";
            try {
                serverOutput = connection.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection.clientGUI != null) {
                String clientChatText = connection.clientGUI.chatText.getText() + serverOutput + "\n";
                clientChatText = t.addTimeStamp(clientChatText);
                connection.clientGUI.chatText.setText(clientChatText);
            }
        }
    }
}
