package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo.VerificationTypeInfo;

/**
 * append_frame {
 * u1 frame_type = APPEND;
 * u2 offset_delta;
 * verification_type_info locals[frame_type - 251];
 * }
 */
public class AppendFrame extends StackMapFrame {

    public AppendFrame(int frameType, ClassReader reader) {
        super(frameType, reader);
        int offsetDelta = reader.nextU2ToInt();
        VerificationTypeInfo[] locals = VerificationTypeInfo.readVerificationTypeInfos(reader, frameType - 251);
    }
}

