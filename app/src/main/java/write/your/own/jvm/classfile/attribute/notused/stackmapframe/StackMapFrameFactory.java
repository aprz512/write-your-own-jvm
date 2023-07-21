package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.classfile.ClassReader;

public class StackMapFrameFactory {

    public static StackMapFrame newInstance(int frameType, ClassReader reader) {
        if (frameType >= 0 && frameType <= 63) {
            return new SameFrame(frameType, reader);
        } else if (frameType >= 64 && frameType <= 127) {
            return new SameLocals1StackItemFrame(frameType, reader);
        } else if (frameType == 247) {
            return new SameLocals1StackItemFrameExtended(frameType, reader);
        } else if (frameType >= 248 && frameType <= 250) {
            return new ChopFrame(frameType, reader);
        } else if (frameType == 251) {
            return new SameFrameExtended(frameType, reader);
        } else if (frameType >= 252 && frameType <= 254) {
            return new AppendFrame(frameType, reader);
        } else if (frameType == 255) {
            return new FullFrame(frameType, reader);
        } else {
            throw new NotImplementedException();
        }
    }

}
