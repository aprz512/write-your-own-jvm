package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;

/**
 * others attribute info
 * too complex to parse
 */
public class UnParsedAttribute extends AttributeInfo {
    public UnParsedAttribute(int attributeNameIndex, int attributeLength, ClassReader reader) {
        super(attributeNameIndex, attributeLength);
        // consumer next attribute bytes
        reader.nextBytes(attributeLength);
//        System.out.println("UnParsedAttribute" + attributeLength);
    }
}
