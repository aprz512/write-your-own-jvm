package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo.VerificationTypeInfo;

/**
 * full_frame {
 * u1 frame_type = FULL_FRAME;
 * u2 offset_delta;
 * u2 number_of_locals;
 * verification_type_info locals[number_of_locals];
 * u2 number_of_stack_items;
 * verification_type_info stack[number_of_stack_items];
 * }
 */

public class FullFrame extends StackMapFrame {
    public FullFrame(int frameType, ClassReader reader) {
        super(frameType, reader);
        int offsetDelta = reader.nextU1toInt();
        int numberOfLocals = reader.nextU2ToInt();
        VerificationTypeInfo[] local = VerificationTypeInfo.readVerificationTypeInfos(reader, numberOfLocals);
        int numberOfStackItems = reader.nextU2ToInt();
        VerificationTypeInfo[] stack = VerificationTypeInfo.readVerificationTypeInfos(reader, numberOfStackItems);
    }
}
