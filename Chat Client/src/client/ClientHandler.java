package client;

import javax.swing.JOptionPane;

/*
 * @author Ram Vakada
 */
public class ClientHandler {

    public static void handle(String msg, Client client) throws Exception {
        String[] handle = msg.split(" ");
        if (handle[0].toLowerCase().equals("#kill")) {
            if (client.isAdmin()) {
                return;
            }
            JOptionPane.showMessageDialog(null, "You have been force disconnected.", "Alert", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        } else if (handle[0].toLowerCase().equals("#namealreadytaken")) {
            JOptionPane.showMessageDialog(null, "It seems that the nickname you have chosen is already in use, please reconnect using another nickname.", "Connection Lost", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        } else if (handle[0].toLowerCase().equals("#newname")) {
            client.getGUI().addName(handle[1]);
        } else if (handle[0].toLowerCase().equals("#removename")) {
            client.getGUI().removeName(handle[1]);
        } else if (handle[0].toLowerCase().equals("#editname")) {
            client.getGUI().removeName(handle[1]);
            client.getGUI().addName(handle[2]);
        } else if (handle[0].toLowerCase().equals("#alert")) {
            JOptionPane.showMessageDialog(null, Tools.rebuildString(handle, 1), "Alert", JOptionPane.PLAIN_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, Tools.rebuildString(handle, 0), "Received invalid command from server.", JOptionPane.PLAIN_MESSAGE);
        }
    }
}
