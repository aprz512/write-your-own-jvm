package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

import static write.your.own.jvm.classfile.attribute.AttributeInfoFactory.newAttributeInfo;

/**
 * attribute_info {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u1 info[attribute_length];
 * }
 * 23 attributes are predefined by this specification.
 * <p>
 * Attribute	                            Section	    Java SE	    class file
 * ConstantValue	                        §4.7.2	    1.0.2	    45.3
 * Code	                                §4.7.3	    1.0.2	    45.3
 * StackMapTable	                        §4.7.4	    6	        50.0
 * Exceptions	                            §4.7.5	    1.0.2	    45.3
 * InnerClasses	                        §4.7.6	    1.1	        45.3
 * EnclosingMethod	                    §4.7.7	    5.0	        49.0
 * Synthetic	                            §4.7.8	    1.1	        45.3
 * Signature	                            §4.7.9	    5.0	        49.0
 * SourceFile	                            §4.7.10	    1.0.2	    45.3
 * SourceDebugExtension	                §4.7.11	    5.0	        49.0
 * LineNumberTable	                    §4.7.12	    1.0.2	    45.3
 * LocalVariableTable	                    §4.7.13	    1.0.2	    45.3
 * LocalVariableTypeTable	                §4.7.14	    5.0	        49.0
 * Deprecated	                            §4.7.15	    1.1	        45.3
 * RuntimeVisibleAnnotations	            §4.7.16	    5.0	        49.0
 * RuntimeInvisibleAnnotations	        §4.7.17	    5.0	        49.0
 * RuntimeVisibleParameterAnnotations	    §4.7.18	    5.0	        49.0
 * RuntimeInvisibleParameterAnnotations	§4.7.19	    5.0	        49.0
 * RuntimeVisibleTypeAnnotations	        §4.7.20	    8           52.0
 * RuntimeInvisibleTypeAnnotations	    §4.7.21	    8	        52.0
 * AnnotationDefault	                    §4.7.20	    5.0	        49.0
 * BootstrapMethods	                    §4.7.21	    7	        51.0
 * MethodParameters	                    §4.7.24	    8	        52.0
 */
public abstract class AttributeInfo {

    protected final int attributeNameIndex;
    protected final int attributeLength;

    public AttributeInfo(int attributeNameIndex, int attributeLength) {
        this.attributeNameIndex = attributeNameIndex;
        this.attributeLength = attributeLength;
    }

    public static AttributeInfo[] readAttributes(ClassReader reader, ConstantPool constantPool, int attributeCount) {
//        Log.d("readAttributes ---- :" + attributeCount);
        AttributeInfo[] attributes = new AttributeInfo[attributeCount];

        for (int i = 0; i < attributeCount; i++) {
            attributes[i] = readAttribute(reader, constantPool);
        }

        return attributes;
    }

    static AttributeInfo readAttribute(ClassReader reader, ConstantPool constantPool) {
        int attrNameIndex = reader.nextU2ToInt();
        String attrName = constantPool.getUtf8(attrNameIndex);
//        Log.d("readAttribute:" + attrName);
        int attrLength = reader.nextU4ToInt();
        return newAttributeInfo(attrName, attrNameIndex, attrLength, reader, constantPool);
    }

    public int getAttributeNameIndex() {
        return attributeNameIndex;
    }
}
