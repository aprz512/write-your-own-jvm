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
        return b & 0xFF;
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
        int b1 = readByte();
        int b2 = readByte();
        return b1 << 8 | b2;
    }

    /**
     * remove extended sign bit
     */
    public int readUnsignedShort() {
        int b1 = readByte();
        int b2 = readByte();
        return (b1 << 8 | b2) & 0xFFFF;
    }

    public int readInt() {
        int b1 = readByte();
        int b2 = readByte();
        int b3 = readByte();
        int b4 = readByte();
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
