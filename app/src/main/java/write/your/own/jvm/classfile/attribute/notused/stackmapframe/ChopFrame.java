package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.classfile.ClassReader;

public class ChopFrame extends StackMapFrame {
    public ChopFrame(int frameType, ClassReader reader) {
        super(frameType, reader);
        int offsetDelta = reader.nextU2ToInt();
    }
}
