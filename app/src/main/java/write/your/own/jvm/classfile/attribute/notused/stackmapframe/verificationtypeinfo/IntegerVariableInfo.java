package write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo;

import write.your.own.jvm.classfile.ClassReader;

public class IntegerVariableInfo extends VerificationTypeInfo {
    public IntegerVariableInfo(int tag, ClassReader reader) {
        super(tag, reader);
    }
}
