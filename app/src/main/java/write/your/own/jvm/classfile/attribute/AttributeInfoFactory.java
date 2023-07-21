package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

import javax.xml.transform.Source;

public class AttributeInfoFactory {

    /**
     * "ConstantValue",
     * "Code",
     * "StackMapTable",
     * "Exceptions",
     * "InnerClasses",
     * "EnclosingMethod",
     * "Synthetic",
     * "Signature",
     * "SourceFile",
     * "SourceDebugExtension",
     * "LineNumberTable",
     * "LocalVariableTable",
     * "LocalVariableTypeTable",
     * "Deprecated",
     * "RuntimeVisibleAnnotations",
     * "RuntimeInvisibleAnnotations",
     * "RuntimeVisibleParameterAnnotations",
     * "RuntimeInvisibleParameterAnnotations",
     * "RuntimeVisibleTypeAnnotations",
     * "RuntimeInvisibleTypeAnnotations",
     * "AnnotationDefault",
     * "BootstrapMethods",
     * "MethodParameters"
     */
    static AttributeInfo newAttributeInfo(
            String attrName, int attrNameIndex, int attrLength, ClassReader reader, ConstantPool constantPool) {
        switch (attrName) {
            case "Code":
                return new CodeAttribute(attrNameIndex, attrLength, reader, constantPool);
            case "ConstantValue":
                return new ConstantValueAttribute(attrNameIndex, attrLength, reader);
            case "Exceptions":
                return new ExceptionsAttribute(attrNameIndex, attrLength, reader);
            case "Synthetic":
                return new SyntheticAttribute(attrNameIndex, attrLength);
            case "SourceFile":
                return new SourceFileAttribute(attrNameIndex, attrLength, reader);
            case "LineNumberTable":
                return new LineNumberTableAttribute(attrNameIndex, attrLength, reader);
            case "LocalVariableTable":
                return new LocalVariableTableAttribute(attrNameIndex, attrLength, reader);
            case "Deprecated":
                return new DeprecatedAttribute(attrNameIndex, attrLength);
            default:
                return new UnParsedAttribute(attrNameIndex, attrLength, reader);
        }
    }

}
