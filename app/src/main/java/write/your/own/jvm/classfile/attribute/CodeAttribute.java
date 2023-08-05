package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

/**
 * Code_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 max_stack;
 * u2 max_locals;
 * u4 code_length;
 * u1 code[code_length];
 * u2 exception_table_length;
 * {   u2 start_pc;
 * u2 end_pc;
 * u2 handler_pc;
 * u2 catch_type;
 * } exception_table[exception_table_length];
 * u2 attributes_count;
 * attribute_info attributes[attributes_count];
 * }
 */
public class CodeAttribute extends AttributeInfo {
    private final int maxStack;
    private final int maxLocals;

    private final int codeLength;
    private final byte[] code;
    private final ExceptionTableEntry[] exceptionTable;

    private final int attributesCount;
    private final AttributeInfo[] attributes;


    public CodeAttribute(int attributeNameIndex, int attributeLength, ClassReader reader, ConstantPool constantPool) {
        super(attributeNameIndex, attributeLength);
        this.maxStack = reader.nextU2ToInt();
        this.maxLocals = reader.nextU2ToInt();
        this.codeLength = reader.nextU4ToInt();
        this.code = reader.nextBytes(codeLength);
        this.exceptionTable = ExceptionTableEntry.readExceptionTable(reader);
        this.attributesCount = reader.nextU2ToInt();
        this.attributes = AttributeInfo.readAttributes(reader, constantPool, attributesCount);
    }

    public int getMaxLocals() {
        return maxLocals;
    }

    public ExceptionTableEntry[] getExceptionTable() {
        return exceptionTable;
    }

    public int getMaxStack() {
        return maxStack;
    }

    public byte[] getCode() {
        return code;
    }

    public LineNumberTableAttribute getLineNumberTableAttribute() {
        for (AttributeInfo info : attributes) {
            if (info instanceof LineNumberTableAttribute) {
                return (LineNumberTableAttribute) info;
            }
        }

        return null;
    }

    public static class ExceptionTableEntry {
        public final int startPc;
        public final int endPc;
        public final int handlerPc;
        public final int catchType;

        public ExceptionTableEntry(int startPc, int endPc, int handlerPc, int catchType) {
            this.startPc = startPc;
            this.endPc = endPc;
            this.handlerPc = handlerPc;
            this.catchType = catchType;
        }

        static ExceptionTableEntry[] readExceptionTable(ClassReader reader) {
            int length = reader.nextU2ToInt();
            ExceptionTableEntry[] exceptionTableEntry = new ExceptionTableEntry[length];

            for (int i = 0; i < length; i++) {
                int startPc = reader.nextU2ToInt();
                int endPc = reader.nextU2ToInt();
                int handlerPc = reader.nextU2ToInt();
                int catchType = reader.nextU2ToInt();
                exceptionTableEntry[i] = new ExceptionTableEntry(startPc, endPc, handlerPc, catchType);
            }
            return exceptionTableEntry;
        }
    }

}
