package write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo;

import write.your.own.jvm.classfile.ClassReader;

public class ObjectVariableInfo extends VerificationTypeInfo {
    public ObjectVariableInfo(int tag, ClassReader reader) {
        super(tag, reader);
        int cpoolIndex = reader.nextU2ToInt();
    }
}
