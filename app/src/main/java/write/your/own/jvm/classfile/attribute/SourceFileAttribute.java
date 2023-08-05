package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

/**
 * SourceFile_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 sourcefile_index;
 * }
 */
public class SourceFileAttribute extends AttributeInfo {
    // The value of the sourcefile_index item must be a valid index into the constant_pool table.
    // The constant_pool entry at that index must be a CONSTANT_Utf8_info structure (§4.4.7) representing a string.
    private final int sourceFileIndex;
    private final ConstantPool constantPool;

    public SourceFileAttribute(int attributeNameIndex, int attributeLength, ClassReader reader, ConstantPool constantPool) {
        super(attributeNameIndex, attributeLength);
        sourceFileIndex = reader.nextU2ToInt();
        this.constantPool = constantPool;
    }

    public String getSourceFile() {
        return constantPool.getUtf8(sourceFileIndex);
    }

}
