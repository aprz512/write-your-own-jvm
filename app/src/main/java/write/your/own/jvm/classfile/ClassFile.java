package write.your.own.jvm.classfile;

import write.your.own.jvm.classfile.attribute.AttributeInfo;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

/**
 * parse a class file
 * <a href="https://docs.oracle.com/javase/specs/jvms/se8/html/jvms-4.html">Chapter 4. The class File Format</a>
 */
public class ClassFile {
    private final ClassReader reader;
    // The magic item supplies the magic number identifying the class file format; it has the value 0xCAFEBABE.
    // we use signed int to present this value in java
    private int magic;
    // the minor version numbers of this class file.
    private int minorVersion;
    // the major version numbers of this class file.
    private int majorVersion;
    private ConstantPool constantPool;
    // The value of the access_flags item is a mask of flags used to denote access permissions to and properties of this class or interface.
    private int accessFlag;
    // The value of the this_class item must be a valid index into the constant_pool table.
    private int thisClassIndex;
    // the value of the super_class item either must be zero or must be a valid index into the constant_pool table.
    private int superClassIndex;
    // Each value in the interfaces array must be a valid index into the constant_pool table.
    private int[] interfaceIndexes;
    // Each value in the fields table must be a field_info (§4.5) structure giving a complete description of a field in this class or interface.
    private MemberInfo[] fields;
    // Each value in the methods table must be a method_info (§4.6) structure giving a complete description of a method in this class or interface.
    private MemberInfo[] methods;
    // Each value of the attributes table must be an attribute_info (§4.7) structure.
    // 类的一些属性暂时不会全部实现： InnerClasses (§4.7.6), EnclosingMethod (§4.7.7), Synthetic (§4.7.8),
    // Signature (§4.7.9), SourceFile (§4.7.10), SourceDebugExtension (§4.7.11), Deprecated (§4.7.15),
    // RuntimeVisibleAnnotations (§4.7.16), RuntimeInvisibleAnnotations (§4.7.17), and BootstrapMethods (§4.7.21)
    private AttributeInfo[] attributes;


    public ClassFile(byte[] classData) {
        reader = new ClassReader(classData);

        readAndCheckMagic();
        readAndCheckVersion();
        readConstantPool();
        readAccessFlag();
        readThisClassIndex();
        readSuperClassIndex();
        readInterfaceIndexes();
        readFields();
        readMethods();
    }

    private void readMethods() {
        methods = MemberInfo.readMembers(constantPool, reader);
    }

    private void readFields() {
        fields = MemberInfo.readMembers(constantPool, reader);
    }

    private void readInterfaceIndexes() {
        interfaceIndexes = reader.nextU2s();
    }

    private void readSuperClassIndex() {
        superClassIndex = reader.nextU2ToInt();
    }

    private void readThisClassIndex() {
        thisClassIndex = reader.nextU2ToInt();
    }

    private void readAccessFlag() {
        accessFlag = reader.nextU2ToInt();
    }

    private void readConstantPool() {
        constantPool = new ConstantPool(reader);
    }

    /**
     * 我们参考 Java 8，支持版本号为45.0~52.0的class文件。
     * |  java版本       | class文件版本号     |
     * |  jdk 1.0.2     |  45.0~45.3        |
     * |  jdk 1.1       |  45.0~45.65536    |
     * |  jdk 1.2       |  46.0             |
     * |  jdk 1.3       |  47.0             |
     * |  jdk 1.4       |  48.0             |
     * |  jdk 5.0       |  49.0             |
     * |  jdk 6         |  50.0             |
     * |  jdk 7         |  51.0             |
     * |  jdk 8         |  52.0             |
     */
    private void readAndCheckVersion() {
        minorVersion = reader.nextU2ToInt();
        majorVersion = reader.nextU2ToInt();
        if (majorVersion == 45) {
            return;
        }
        if (majorVersion >= 46 && majorVersion <= 52 && minorVersion == 0) {
            return;
        }
        throw new UnsupportedClassVersionError("class version not support:" + majorVersion + ":" + minorVersion);
    }

    /**
     * class file magic is cafebabe
     */
    private void readAndCheckMagic() {
        String magic = reader.nextU4ToHexString();
        if (!"cafebabe".equals(magic)) {
            throw new ClassFormatError("magic error: " + magic);
        }
    }

    public int getMagic() {
        return magic;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public int getMajorVersion() {
        return majorVersion;
    }

    public ConstantPool getConstantPool() {
        return constantPool;
    }

    public int getAccessFlag() {
        return accessFlag;
    }

    public int getThisClassIndex() {
        return thisClassIndex;
    }

    public int getSuperClassIndex() {
        return superClassIndex;
    }

    public int[] getInterfaceIndexes() {
        return interfaceIndexes;
    }

    public MemberInfo[] getFields() {
        return fields;
    }

    public MemberInfo[] getMethods() {
        return methods;
    }

    public AttributeInfo[] getAttributes() {
        return attributes;
    }

    public String getThisClassName() {
        return constantPool.getClassName(thisClassIndex);
    }

    public String getSuperClassName() {
        if (superClassIndex <= 0) {
            return "";
        }
        return constantPool.getClassName(superClassIndex);
    }

    public String[] getInterfaceNames() {
        String[] result = new String[interfaceIndexes.length];
        for (int i = 0; i < interfaceIndexes.length; i++) {
            result[i] = constantPool.getClassName(interfaceIndexes[i]);
        }
        return result;
    }
}
