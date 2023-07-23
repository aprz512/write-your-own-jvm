package write.your.own.jvm;

import org.junit.jupiter.api.Test;
import write.your.own.jvm.util.NumUtil;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Util {

    @Test
    public void testNumUtil() {
        byte b = (byte) 0xFF;
        int sb = NumUtil.byteToSignedInt(b);
        int usb = NumUtil.byteToUnsignedInt(b);
        assertEquals(sb, -1);
        assertEquals(usb, 0xFF);
    }

}
