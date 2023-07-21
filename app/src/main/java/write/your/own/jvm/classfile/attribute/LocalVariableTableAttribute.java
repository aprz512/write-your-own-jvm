package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;

/**
 * LocalVariableTable_attribute {
 *     u2 attribute_name_index;
 *     u4 attribute_length;
 *     u2 local_variable_table_length;
 *     {   u2 start_pc;
 *         u2 length;
 *         u2 name_index;
 *         u2 descriptor_index;
 *         u2 index;
 *     } local_variable_table[local_variable_table_length];
 * }
 */
public class LocalVariableTableAttribute extends AttributeInfo {

    private final int localVariableTableLength;
    private final LocalVariableTable[] localVariableTables;

    public LocalVariableTableAttribute(int attrNameIndex, int attrLength, ClassReader reader) {
        super(attrNameIndex, attrLength);
        localVariableTableLength = reader.nextU2ToInt();
        localVariableTables = LocalVariableTable.readLocalVariableTables(reader, localVariableTableLength);
    }

    static class LocalVariableTable {
        public final int startPc;
        public final int length;
        public final int nameIndex;
        public final int descriptorIndex;
        public final int index;

        public LocalVariableTable(ClassReader reader) {
            startPc = reader.nextU2ToInt();
            length = reader.nextU2ToInt();
            nameIndex = reader.nextU2ToInt();
            descriptorIndex = reader.nextU2ToInt();
            index = reader.nextU2ToInt();
        }

        public static LocalVariableTable[] readLocalVariableTables(ClassReader reader, int localVariableTableLength) {
            LocalVariableTable[] localVariableTables = new LocalVariableTable[localVariableTableLength];
            for (int i = 0; i <localVariableTableLength; i++) {
                localVariableTables[i] = new LocalVariableTable(reader);
            }
            return localVariableTables;
        }
    }

}
