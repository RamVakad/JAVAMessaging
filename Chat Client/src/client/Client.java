package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/*
 * @author Ram Vakada
 */
public class Client {

    private ClientGUI gui;
    private DataInputStream din;
    private DataOutputStream dout;
    private Socket socket;
    private String host;
    private int port;
    private String name;
    private String password;
    private boolean admin;
    public ArrayList<String> targets = new ArrayList<String>();
    public ArrayList<String> names = new ArrayList<String>();

    public Client(String host, int port, String name, String password, ClientGUI gui) throws Exception {
        this.host = host;
        this.port = port;
        this.name = name;
        this.password = Tools.byteArrayToHexString(Tools.computeHash(password));
        this.gui = gui;
        gui.setClient(this);
        initConnection();

    }

    public void initConnection() {
        try {
            socket = new Socket(host, port);
            din = new DataInputStream(socket.getInputStream());
            dout = new DataOutputStream(socket.getOutputStream());
            dout.writeUTF(name);
            dout.writeUTF(password);
            int y = din.readInt();
            for (int i = 0; i < y; i++) {
                gui.addName(din.readUTF());
            }
            boolean adm = din.readBoolean();
            admin = adm;
            new RecvLoop(this);
            gui.addName(name);
            gui.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "There was a problem connecting to the server. Try again later.", "Alert", JOptionPane.PLAIN_MESSAGE);
            System.exit(0);
        }
    }

    public void sendMessage(String msg) throws IOException {
        dout.writeUTF(msg);
    }

    public String recvMessage() throws IOException {
        return din.readUTF();
    }

    public ClientGUI getGUI() {
        return gui;
    }

    public boolean isAdmin() {
        return admin;
    }

    public String getName() {
        return name;
    }
}
