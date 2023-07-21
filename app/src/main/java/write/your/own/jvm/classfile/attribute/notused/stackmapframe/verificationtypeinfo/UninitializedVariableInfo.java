package write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo;

import write.your.own.jvm.classfile.ClassReader;

public class UninitializedVariableInfo extends VerificationTypeInfo {
    public UninitializedVariableInfo(int tag, ClassReader reader) {
        super(tag, reader);
        int offset = reader.nextU2ToInt();
    }
}
