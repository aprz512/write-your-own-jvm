package write.your.own.jvm.classfile.attribute.notused.stackmapframe;

import write.your.own.jvm.classfile.ClassReader;

public class SameFrame extends StackMapFrame {
    public SameFrame(int frameType, ClassReader reader) {
        super(frameType, reader);
    }
}
