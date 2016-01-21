/*
 * @author Ram Vakada
 * needs to be redone
 */
package server;

public class AdminHandler {

    public static void handleCommand(String[] handle, ClientThread clientT) throws Exception {
        if (handle[0].toLowerCase().equals("#shutdown")) {
            clientT.getServer().writeToAll("[SERVER] Server is shutting down in T minus 5 seconds."); //Yes, I know, I'm super cool.
            Thread.sleep(5000);
            clientT.getServer().writeToAll("#kill");
            System.exit(0);
        } else if (handle[0].toLowerCase().equals("#disconnect")) {//#disconnect <name> or <[everyone]>
            if (handle[1].equals("everyone")) {
                clientT.getServer().writeToAll("#kill");
            } else {
                Client client = clientT.getServer().findClient(handle[1]);
                if (client == null) {
                    return;
                }
                client.getDOUT().writeUTF("#kill");
            }
        } else if (handle[0].toLowerCase().equals("#notice")) { //#notice <message>
            clientT.getServer().writeToAll("[Notice] " + Tools.rebuildString(handle, 1));
        } else if (handle[0].toLowerCase().equals("#sendall")) { //#sendall <message>
            StringBuilder sb = new StringBuilder();
            for (int i = 1; i < handle.length; i++) {
                sb.append(handle[i]).append(" ");
            }
            clientT.getServer().writeToAll(sb.toString());
        } else if (handle[0].toLowerCase().equals("#setname")) { //#sendall <message>
            clientT.getServer().setName(handle[1], handle[2]);
        } else if (handle[0].toLowerCase().equals("#sendto")) { //#sendall <name> <message>
            Client client = clientT.getServer().findClient(handle[1]);
            if (client == null) {
                return;
            }
            client.getDOUT().writeUTF(Tools.rebuildString(handle, 2));
        } else if (handle[0].toLowerCase().equals("#alert")) { //#sendall <name> <message>
            Client client = clientT.getServer().findClient(handle[1]);
            if (client == null) {
                return;
            }
            client.getDOUT().writeUTF("#alert " + Tools.rebuildString(handle, 2));
        } else {
            clientT.getClient().getDOUT().writeUTF("[SERVER] Command - " + handle[0] + " does not exsist!");
        }
    }

    
}
