package write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo;

import write.your.own.jvm.classfile.ClassReader;

public class UninitializedThisVariableInfo extends VerificationTypeInfo {
    public UninitializedThisVariableInfo(int tag, ClassReader reader) {
        super(tag, reader);
    }
}
