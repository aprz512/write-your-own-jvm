package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo.VerificationTypeInfo;

public class SameLocals1StackItemFrameExtended extends StackMapFrame {
    public SameLocals1StackItemFrameExtended(int frameType, ClassReader reader) {
        super(frameType, reader);
        int offsetDelta = reader.nextU2ToInt();
        VerificationTypeInfo stack[] = VerificationTypeInfo.readVerificationTypeInfos(reader, 1);
    }
}
