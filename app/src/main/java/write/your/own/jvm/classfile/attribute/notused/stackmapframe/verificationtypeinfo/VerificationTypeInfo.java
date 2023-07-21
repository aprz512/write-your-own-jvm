package write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo;

import write.your.own.jvm.classfile.ClassReader;


public abstract class VerificationTypeInfo {

    protected final int tag;
    protected final ClassReader reader;

    public VerificationTypeInfo(int tag, ClassReader reader) {
        this.tag = tag;
        this.reader = reader;
    }

    public static VerificationTypeInfo[] readVerificationTypeInfos(ClassReader reader, int typeInfosCount) {
        VerificationTypeInfo[] typeInfos = new VerificationTypeInfo[typeInfosCount];

        for (int i = 0; i < typeInfosCount; i++) {
            typeInfos[i] = readVerificationTypeInfo(reader);
        }

        return typeInfos;
    }

    public static VerificationTypeInfo readVerificationTypeInfo(ClassReader reader) {
        int tag = reader.nextU1toInt();
        return VerificationTypeInfoFactory.newInstance(tag, reader);
    }


}
