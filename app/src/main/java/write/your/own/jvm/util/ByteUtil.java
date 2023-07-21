package write.your.own.jvm.util;

public class ByteUtil {

    public static int byteToInt(byte[] codes) {
        String s1 = byteToHexString(codes);
        // if hex string overflow, it will throw exception
        return Integer.valueOf(s1, 16);
    }

    public static String byteToHexString(byte[] codes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : codes) {
            int value = b & 0xFF;
            String strHex = Integer.toHexString(value);
            if (strHex.length() < 2) {
                strHex = "0" + strHex;
            }
            sb.append(strHex);
        }
        return sb.toString();
    }

}
