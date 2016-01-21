package client;

import java.util.regex.Pattern;

/*
 * @author Ram
 */
public class Tools {

    public static byte[] computeHash(String x) throws Exception {
        java.security.MessageDigest d = null;
        d = java.security.MessageDigest.getInstance("SHA-1");
        d.reset();
        d.update(x.getBytes());
        return d.digest();
    }

    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            int v = b[i] & 0xff;
            if (v < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(v));
        }
        return sb.toString().toUpperCase();
    }

    public static boolean nameIsValid(String name) {
        if (name.length() < 4 || name.length() > 12) {
            return false;
        }

        return Pattern.compile("[a-zA-Z0-9_-]{3,12}").matcher(name).matches();
    }

    public static String rebuildString(String[] split, int fromIdx) {
        StringBuilder ret = new StringBuilder();
        for (int i = fromIdx; i < split.length; i++) {
            ret.append(split[i]).append(" ");
        }
        return ret.toString();
    }
}
