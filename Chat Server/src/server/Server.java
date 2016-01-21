package server;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/*
 * @author Ram
 */
public class Server {

    private final String ADMIN_PASS = "5BAA61E4C9B93F3F0682250B6CF8331B7EE68FD8";
    private int port;
    private ServerSocket server;
    private List<Client> clients = new ArrayList<Client>();

    public List<Client> getClients() {
        return clients;
    }

    public static void main(String args[]) {
        try {
            System.out.println("Enter port on which server will operate - ");
            int port = Integer.parseInt(new BufferedReader(new InputStreamReader(System.in)).readLine());
            new Server(port);
        } catch (Exception e) {
            System.out.println("Exception - " + e);
        }
    }

    public Server(int port) {
        this.port = port;
        try {
            server = new ServerSocket(port);
            listen();
        } catch (Exception e) {
            System.out.println("[ALERT] Could not initiate server.");
            e.printStackTrace();
        }
    }

    public void listen() {
        try {
            System.out.println("[SERVER] Server is online.");
            while (true) {
                Socket clientSocket = server.accept();

                if (clientSocket != null) {
                    System.out.println("[CLIENT] " + clientSocket + "  ---  Joined the chat session.");
                } else {
                    clientSocket.close();
                    continue;
                }

                DataOutputStream dout = new DataOutputStream(clientSocket.getOutputStream());
                DataInputStream din = new DataInputStream(clientSocket.getInputStream());
                String clientName = din.readUTF();
                String passwd = din.readUTF();
                boolean admin;
                if (passwd.equals(ADMIN_PASS)) {
                    admin = true;
                } else {
                    admin = false;
                }

                if (!nameExists(clientName)) {
                    dout.writeInt(clients.size());
                    for (int i = 0; i < clients.size(); i++) {
                        dout.writeUTF(clients.get(i).getName());
                    }
                    dout.writeBoolean(admin);
                    writeToAll("[Notice] Another person has joined the chat session! Name - " + clientName);
                    writeToAll("#newname " + clientName);
                    Client client = new Client(clientSocket, clientName, admin, dout, din);
                    clients.add(client);
                    dout.writeUTF("[SERVER] You have joined the chat session as " + clientName + ".");
                    if (admin == true) {
                        dout.writeUTF("[SERVER] You session is ADMINISTRATIVE.");
                    }
                    new ClientThread(client, this);
                } else {
                    dout.writeUTF("#namealreadytaken");
                    clientSocket.close();
                    continue;
                }
            }
        } catch (Exception e) {
            System.out.println("Exception - " + e);
        }
    }

    public void writeToAll(String message) throws Exception {
        for (int i = 0; i < clients.size(); i++) {
            clients.get(i).getDOUT().writeUTF(message);
        }
    }

    public void removeConnection(Client client) throws Exception {
        if (client != null) {
            if (client.getSocket() != null) {
                client.getSocket().close();
            }

            if (clients.contains(client)) {
                clients.remove(client);
            }
            writeToAll("#removename " + client.getName());
            writeToAll("[SERVER] " + client.getName() + " has left the session.");
            client = null;
        }
    }

    public boolean nameExists(String name) {
        boolean ret = false;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                ret = true;
            }
        }
        return ret;
    }

    public void setName(String orig, String newname) throws Exception {
        Client client = findClient(orig);
        if (client == null) {
            return;
        }
        client.setName(newname);
        writeToAll("[SERVER] " + orig + "'s name has been changed to " + newname + ".");
        writeToAll("#editname " + orig + " " + newname);
    }

    public Client findClient(String name) {
        Client client = null;
        for (int i = 0; i < clients.size(); i++) {
            if (clients.get(i).getName().toLowerCase().equals(name.toLowerCase())) {
                client = clients.get(i);
            }
        }
        return client;
    }
}
