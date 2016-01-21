/*
 * @author Ram Vakada
 */
package server;

public class UserHandler {

    public static void handleCommand(String[] handle, ClientThread clientT) throws Exception {
        if (handle[0].toLowerCase().equals("@whisper")) {
            Client client = clientT.getServer().findClient(handle[1]);
            if (client == null) {
                return;
            }
            client.getDOUT().writeUTF("[WHISPER-FRM] " + clientT.getClient().getName() + ": " + Tools.rebuildString(handle, 2));
            clientT.getClient().getDOUT().writeUTF("[WHISPER-TO] " + clientT.getClient().getName() + ": " + Tools.rebuildString(handle, 2));
        } else if (handle[0].toLowerCase().equals("#command2")) {
            //do shit
        } else {
            clientT.getClient().getDOUT().writeUTF("[SERVER] Command - " + handle[0] + " does not exsist!");
        }
    }
}
