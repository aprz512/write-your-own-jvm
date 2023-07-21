package write.your.own.jvm.classfile.attribute.notused;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.AttributeInfo;

public class LocalVariableTypeTableAttribute extends AttributeInfo {

    public LocalVariableTypeTableAttribute(int attrNameIndex, int attrLength, ClassReader reader) {
        super(attrNameIndex, attrLength);
    }

}
