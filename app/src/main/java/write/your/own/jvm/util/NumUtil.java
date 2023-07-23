package write.your.own.jvm.util;

public class NumUtil {

    public static long merge(int high, int low) {
        // be aware of sign bit
        long h = (high & 0xFFFFFFFFL) << 32;
        long l = low & 0xFFFFFFFFL;
        return h | l;
    }

    public static int getLow(long val) {
        return (int) (val & 0xFFFFFFFFL);
    }

    public static int getHigh(long val) {
        return (int) (val >> 32);
    }

    public static int byteToSignedInt(byte value) {
        return (int) value;
    }

    public static int byteToUnsignedInt(byte value) {
        return value & 0xFF;
    }

}
