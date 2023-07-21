package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo.VerificationTypeInfo;

public class SameLocals1StackItemFrame extends StackMapFrame {
    public SameLocals1StackItemFrame(int frameType, ClassReader reader) {
        super(frameType, reader);
        VerificationTypeInfo[] stack = VerificationTypeInfo.readVerificationTypeInfos(reader, 1);
    }
}
