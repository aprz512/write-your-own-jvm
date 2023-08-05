package write.your.own.jvm.instruction;

public class CodeReader {

    private byte[] code;

    private int pc;

    public void reset(byte[] code, int pc) {
        this.code = code;
        this.pc = pc;
    }

    public int readByte() {
        byte b = code[pc];
        pc += 1;
        return b;
    }

    /**
     * remove extended sign bit
     */
    public int readUnsignedByte() {
        byte b = code[pc];
        pc += 1;
        return b & 0xFF;
    }

    public int readShort() {
        int b1 = readUnsignedByte();
        int b2 = readUnsignedByte();
        return (short) (b1 << 8 | b2);
    }

    /**
     * remove extended sign bit
     */
    public int readUnsignedShort() {
        int b1 = readUnsignedByte();
        int b2 = readUnsignedByte();
        return (b1 << 8 | b2) & 0xFFFF;
    }

    public int readInt() {
        int b1 = readUnsignedByte();
        int b2 = readUnsignedByte();
        int b3 = readUnsignedByte();
        int b4 = readUnsignedByte();
        return b1 << 24 | b2 << 16 | b3 << 8 | b4;
    }

    public int[] readInts(int count) {
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = readInt();
        }
        return result;
    }

    public void skipPadding() {
        while (pc % 4 != 0) {
            readByte();
        }
    }

    public int getPc() {
        return pc;
    }
}
