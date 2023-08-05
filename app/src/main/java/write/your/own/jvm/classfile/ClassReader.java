package write.your.own.jvm.classfile;

import write.your.own.jvm.util.ByteUtil;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;

public class ClassReader {

    private final byte[] codes;
    private int pos;

    public ClassReader(byte[] aClassData) {
        this.codes = aClassData;
        this.pos = 0;
    }

    /**
     * read next one unsigned byte, convert to int
     */
    public int nextU1toInt() {
        return ByteUtil.byteToInt(new byte[]{codes[pos++]});
    }

    /**
     * read next two unsigned bytes, convert to int
     */
    public int nextU2ToInt() {
        return ByteUtil.byteToInt(new byte[]{codes[pos++], codes[pos++]});
    }

    /**
     * read next four unsigned bytes, convert to int
     */
    public int nextU4ToInt() {
        return ByteUtil.byteToInt(new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]});
    }

    /**
     * read next four unsigned bytes, consider it float
     */
    public float nextU4ToFloat() {
        byte[] bytes = nextBytes(4);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getFloat();
    }

    /**
     * read next four unsigned bytes, convert to hex string.
     * for example: 7f123456
     */
    public String nextU4ToHexString() {
        return ByteUtil.byteToHexString((new byte[]{codes[pos++], codes[pos++], codes[pos++], codes[pos++]}));
    }

    /**
     * read next ${len} bytes
     */
    public byte[] nextBytes(int len) {
        if (pos + len > codes.length) {
            throw new ArrayIndexOutOfBoundsException("pos = " + pos + ", len = " + len + ", codes.length = " + codes.length);
        }
        byte[] data = Arrays.copyOfRange(codes, pos, pos + len);
        pos += len;
        return data;
    }

    /**
     * read next 8 unsigned bytes, consider it long
     */
    public long nextU8ToLong() {
        byte[] bytes = nextBytes(8);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getLong();
    }

    /**
     * read next 8 unsigned bytes, consider it double
     */
    public double nextU8ToDouble() {
        byte[] bytes = nextBytes(8);
        return ByteBuffer.wrap(bytes).order(ByteOrder.BIG_ENDIAN).getDouble();
    }

    /**
     * read u2 table
     * |- u2 count -|- u2 -|- u2 -| ... |
     *
     * @return 先读一个 u2 的值，为 x，再读 x 个 u2 的值
     */
    public int[] nextU2s() {
        int count = nextU2ToInt();
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = nextU2ToInt();
        }
        return result;
    }

    /**
     * back n bytes
     */
    public void back(int n) {
        this.pos -= n;
    }

}
