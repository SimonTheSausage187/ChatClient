package listeners;

import connectionHandlers.ServerConnection;
import utils.TextUtils;

import java.io.IOException;

public class ServerOutputListener extends Thread{

    ServerConnection connection;
    TextUtils t = new TextUtils();

    public ServerOutputListener(ServerConnection connection) {
        this.connection = connection;
    }

    @Override
    public void run() {
        while (true) {
            String serverOutput = "";
            try {
                serverOutput = connection.reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (connection.clientGUI != null) {
                String clientChatText = connection.clientGUI.chatText.getText() + serverOutput;
                clientChatText = t.addTimeStamp(clientChatText)+ "\n";
                connection.clientGUI.chatText.setText(clientChatText);
            }
        }
    }
}
