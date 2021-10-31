package com;

import clientGUI.ClientGUIFrame;
import clientGUI.InfoPane;
import clientGUI.LoginGUIFrame;
import clientGUI.StartPageGUIFrame;
import connectionHandlers.ServerConnection;
import utils.CommandUtils;

public class ClientMain {

    static ServerConnection connection = new ServerConnection();
    static CommandUtils c = new CommandUtils(connection);


    public void process(String s) {
        if (s.startsWith("@")) {
            c.privateMessage(s);
        } else if (s.startsWith("/")) {
            c.callCommand(s);
        } else {
            c.sendToAll(s);
        }
    }

    public static void main(String[] args) {
        connection.start();


    }
}
