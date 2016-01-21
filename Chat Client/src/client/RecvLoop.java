/*
 * @author Ram Vakada
 */
package client;

import java.io.DataInputStream;
import javax.swing.JOptionPane;

public class RecvLoop extends Thread {

    private Client c;

    public RecvLoop(Client c) {
        this.c = c;
        start();
    }

    public void run() {
        try {
            while (true) {
                String msg = c.recvMessage();
                if (msg.startsWith("#")) {
                    ClientHandler.handle(msg, c);
                } else {
                    c.getGUI().addMessage(msg);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Connection has been lost.", "Alert", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }
}
