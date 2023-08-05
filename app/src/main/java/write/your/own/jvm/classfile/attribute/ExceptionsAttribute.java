package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;

/**
 * Exceptions_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 number_of_exceptions;
 * u2 exception_index_table[number_of_exceptions];
 * }
 */
public class ExceptionsAttribute extends AttributeInfo {
    private final int numberOfExceptions;
    private final int[] exceptionIndexTable;

    public ExceptionsAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        exceptionIndexTable = reader.nextU2s();
        numberOfExceptions = exceptionIndexTable.length;
    }
}
