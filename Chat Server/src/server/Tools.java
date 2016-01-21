package server;

/*
 * @author Ram
 */
public class Tools {

    public static String rebuildString(String[] split, int fromIdx) {
        StringBuilder ret = new StringBuilder();
        for (int i = fromIdx; i < split.length; i++) {
            ret.append(split[i]).append(" ");
        }
        return ret.toString();
    }
}
