package write.your.own.jvm.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UtilTest {

    @Test
    public void testNumUtil() {
        byte b = -128;
        int sb = NumUtil.byteToSignedInt(b);
        int usb = NumUtil.byteToUnsignedInt(b);
        assertEquals(sb, -128);
        assertEquals(usb, 0x80);
    }

    @Test
    public void testSign() {
        byte b = -1;
        System.out.println(b);
    }

    @Test
    public void testCast() {
        Object x = 2;
        assertEquals(2, (int)x);
    }

    @Test
    public void testParse() {
        int i = ByteUtil.byteToInt(new byte[]{-128, 0, 0, 0});
        assertEquals(i, Integer.MIN_VALUE);
    }

}
