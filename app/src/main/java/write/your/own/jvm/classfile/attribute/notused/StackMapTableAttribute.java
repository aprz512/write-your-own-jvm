package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;
import write.your.own.jvm.classfile.attribute.notused.stackmapframe.StackMapFrame;

/**
 * StackMapTable_attribute {
 *     u2              attribute_name_index;
 *     u4              attribute_length;
 *     u2              number_of_entries;
 *     stack_map_frame entries[number_of_entries];
 * }
 * 该属性不包含运行时所需的信息，仅用作 Class 文件的类型检验。
 * just read and ignore
 */
public class StackMapTableAttribute extends AttributeInfo {

    private final int numberOfEntries;
    private final StackMapFrame[] entries;

    public StackMapTableAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        this.numberOfEntries = reader.nextU2ToInt();
        this.entries = StackMapFrame.readStackMapFrames(reader, numberOfEntries);
    }

}
