package write.your.own.jvm.util;

public class ByteUtil {

    public static int byteToInt(byte[] codes) {
        if (codes.length < 4) {
            return Integer.valueOf(byteToHexString(codes), 16);
        }
        int ch1 = codes[0] & 0xFF;
        int ch2 = codes[1] & 0xFF;
        int ch3 = codes[2] & 0xFF;
        int ch4 = codes[3] & 0xFF;
        return ((ch1 << 24) + (ch2 << 16) + (ch3 << 8) + (ch4));
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
