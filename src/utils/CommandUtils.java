package utils;


import connectionHandlers.ServerConnection;

import java.util.Arrays;

public class CommandUtils {


    ServerConnection connection;
    public CommandUtils(ServerConnection connection) {
        this.connection = connection;
    }

    public String login(String name, String pwd) {
        String out = null;
        System.out.println(name.length() + " " + pwd.length());
        if (name.length() > 0 && pwd.length() > 0) {
            out = ("login " + name + " " + pwd);
        } else {
            return null;
        }
        System.out.println(out);
        return out;
    }

    //TODO missing commands: list command, rgb commands, /help

    public void callCommand(String s) {
        String[] tokens = s.substring(1, s.length()).split(" ");
        String cmd = tokens[0];
        int optionalAddition = 2;
        System.out.println(Arrays.toString(tokens));
        if (tokens.length <= 1) {
            optionalAddition = 0;
        }
        String[] args = s.substring(cmd.length() + optionalAddition, s.length()).split(" ");

        if (cmd.equalsIgnoreCase("quit") || cmd.equalsIgnoreCase("logoff") || cmd.equalsIgnoreCase("exit")) {
            connection.writer.println("logoff");
            System.out.println("logoff");
        } else if(cmd.equalsIgnoreCase("rbf")) {
            rgbCommands(); /* TODO RGB-Implementation im Client? */
        } else if(cmd.equalsIgnoreCase("database") || cmd.equalsIgnoreCase("db")) {
            databaseCommands(args);
        } else if(cmd.equalsIgnoreCase("whoami")) {
            connection.writer.println("whoami");
        }else if(cmd.equals("list")){

        }else {
            connection.clientGUI.commandErrorMessage("This is no valid command. Type /help in order to get a list of all commands");
        }
    }


    private void rgbCommands() {
        connection.clientGUI.commandErrorMessage("We are very sorry to say this but RGB is not available in our ChatClient. Use your terminal in order to be able to chat with 144 fps!");
    }

    private void databaseCommands(String[] args) {
        System.out.println(Arrays.toString(args));
        String finalCommand = "";
        System.out.println(args[1]);
        if (args[0].equalsIgnoreCase("user")) {
            if (args[1].equalsIgnoreCase("create")) {
                finalCommand += createUser(args);
            } else if(args[1].equalsIgnoreCase("delete")) {
                finalCommand += deleteUser(args);
            } else if(args[1].equalsIgnoreCase("change")) {
                finalCommand += changeUser(args);
            } else {
                connection.clientGUI.commandErrorMessage("Typo alert! You can only change, create and delete users.");
            }
        }else if (args[0].equalsIgnoreCase("group")) {
            finalCommand =  groupCommands(args);
        } else {
            connection.clientGUI.commandErrorMessage("Parameter error: insert 'user' or 'group'");
        }
        if (!finalCommand.equals("")) {
            connection.writer.println(finalCommand);
            System.out.println(finalCommand);
        }
    }

    //TODO Group Change: args[0] = group, args[1] = change; argws[2] = thing to change; args[3] = group name; args[4] = add/remove/new name

    private String groupCommands(String[] args){
        String finalCommand = "";
        if (args[0].equalsIgnoreCase("create")) {
            finalCommand += createGroup(args);
        } else if(args[0].equalsIgnoreCase("delete")) {
            finalCommand += deleteGroup(args);
        } else if(args[0].equalsIgnoreCase("change")) {
            finalCommand += changeGroup(args);
        } else {
            connection.clientGUI.commandErrorMessage("Typo alert! You can only change, create and delete users.");
        }
        return finalCommand;
    }

    private String changeGroup(String[] args) {
        if (args[2].equalsIgnoreCase("name")) {
            return "group change name " + args[3] + " " + args[4];
        } else if (args[2].equalsIgnoreCase("members")) {
            String out = "";
            for (int i = 0; i < args.length; i++) {
                out += (args[i] + " ");
            }
            return out;
        } else {
            connection.clientGUI.commandErrorMessage("Something went wrong. Type /help to get a list of all commands.");
            return null;
        }
    }

    private String deleteGroup(String[] args) {
        if (args.length < 3) {
            connection.clientGUI.commandErrorMessage("Wrong input! Type: /db group delete <group name>");
        } else {
            return "group delete " + args[2];
        }
        return null;
    }

    //TODO /db create group name

    private String createGroup(String[] args) {
        if (args.length < 4) {
            connection.clientGUI.commandErrorMessage("Wrong input! Type: /db group create <group name> <user1> <user2> ... <userN>");
        } else {
            String out = "";
            for (int i = 0; i < args.length; i++) {
                out += (args[i] + " ");
            }
            return out;
        }
        return null;
    }

    ;

    private String changeUser(String[] args) {
        if (args.length  == 6) {
            if (args[2].equalsIgnoreCase("name")) {
                return "user change name " + args[4] +" "+ args[5];
            } else if(args[2].equalsIgnoreCase("password") || args[2].equalsIgnoreCase("pwd")) {
                return "user change pwd " + args[4] +" "+ args[5];
            } else {
                connection.clientGUI.commandErrorMessage("Wrong input: Type '/db user change name/password <name> <password> <new name/password>' in order to change a user's name or password");
                return null;
            }
        } else {
            connection.clientGUI.commandErrorMessage("Wrong input: Type '/db user change name/password <name> <password> <new name/password>' in order to change a user's name or password");
            return null;
        }
    }

    private String deleteUser(String[] args) {
        if (args.length == 4) {
            return "user delete " + args[2] + " " + args[3];
        } else {
            connection.clientGUI.commandErrorMessage("Wrong input: Type '/db user delete <name> <password>' in order to delete a user");
            return null;
        }
    }

    private String createUser(String[] args) {
        if (args.length == 4) {
            return "user create " + args[2] + " " + args[3];
        } else {
            connection.clientGUI.commandErrorMessage("Wrong input: Type '/db user create <name> <password>' in order to create a new user");
            return null;
        }
    }


    public void privateMessage(String s) {
        String[] tokens = s.substring(1, s.length()).split(" ");
        connection.writer.println("msg" + s.substring(tokens[0].length() + 1, s.length())+" "+ tokens[0]);
        System.out.println("msg" + s.substring(tokens[0].length() + 1, s.length())+" "+ tokens[0]);
    }

    public void sendToAll(String s) {
        connection.writer.println("send " + s);
        System.out.println("send " + s);
    }




}
