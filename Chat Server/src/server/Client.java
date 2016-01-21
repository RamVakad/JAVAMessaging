package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

/*
 * @author Ram
 */
public class Client {

    private DataInputStream din;
    private DataOutputStream dout;
    private Socket socket;
    private String nickname;
    private boolean admin;

    public Client(Socket socket, String nickname, boolean admin, DataOutputStream dout, DataInputStream din) {
        this.socket = socket;
        this.nickname = nickname;
        this.admin = admin;
        this.din = din;
        this.dout = dout;

    }

    public DataInputStream getSocket() {
        return din;
    }

    public DataOutputStream getDOUT() {
        return dout;
    }

    public DataInputStream getDIN() {
        return din;
    }

    public String getName() {
        return nickname;
    }

    public void setName(String name) {
        this.nickname = name;
    }

    public boolean isAdmin() {
        return admin;
    }
}
