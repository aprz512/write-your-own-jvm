package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;

/**
 * LineNumberTable_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 line_number_table_length;
 * {   u2 start_pc;
 * u2 line_number;
 * } line_number_table[line_number_table_length];
 * }
 */
public class LineNumberTableAttribute extends AttributeInfo {

    private final int lineNumberTableLength;
    private final LineNumberTable[] lineNumberTable;

    public LineNumberTableAttribute(int attrNameIndex, int attrLength, ClassReader reader) {
        super(attrNameIndex, attrLength);
        lineNumberTableLength = reader.nextU2ToInt();
        lineNumberTable = LineNumberTable.read(reader, lineNumberTableLength);
    }

    static class LineNumberTable {

        public final int startPc;
        public final int lineNumber;

        public LineNumberTable(ClassReader reader) {
            this.startPc = reader.nextU2ToInt();
            this.lineNumber = reader.nextU2ToInt();
        }

        static LineNumberTable[] read(ClassReader reader, int lineNumberTableLength) {
            LineNumberTable[] tables = new LineNumberTable[lineNumberTableLength];
            for (int i = 0; i < lineNumberTableLength; i++) {
                tables[i] = new LineNumberTable(reader);
            }
            return tables;
        }
    }
}
