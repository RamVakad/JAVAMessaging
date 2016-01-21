package server;

/*
 * @author Ram
 */
public class ClientThread extends Thread {

    private Client client;
    private Server server;

    public ClientThread(Client client, Server server) {
        this.client = client;
        this.server = server;
        start();
    }

    public void run() {
        try {
            while (true) {
                String message = client.getDIN().readUTF();
                if (!message.equals("")) {
                    if (message.startsWith("@")) {
                        System.out.println("[MESSAGE] User command - " + message + " - has been recived by " + client.getName() + ".");
                        String[] command = message.split(" ");
                        UserHandler.handleCommand(command, this);
                        continue;
                    }
                    if (message.startsWith("#")) {
                        if (client.isAdmin()) {
                            System.out.println("[MESSAGE] Admin command - " + message + " - has been recived by " + client.getName() + ".");
                            String[] command = message.split(" ");
                            AdminHandler.handleCommand(command, this);
                        } else {
                            client.getDOUT().writeUTF("[SERVER] Do not start a sentence with #");
                        }
                    } else {
                        System.out.println("[MESSAGE] Message '" + message + "' has been recived by " + client.getName() + ".");
                        server.writeToAll(client.getName() + " : " + message);
                    }
                    Thread.sleep(1000);
                }
            }
        } catch (Exception e) {
            System.out.println("Connection lost with " + client.getName() + ".");
        } finally {
            try {
                server.removeConnection(client);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public Client getClient() {
        return client;
    }

    public Server getServer() {
        return server;
    }
}
