package write.your.own.jvm.classfile.attribute.notused.stackmapframe;


import write.your.own.jvm.classfile.ClassReader;

/**
 * union stack_map_frame {
 * same_frame;
 * same_locals_1_stack_item_frame;
 * same_locals_1_stack_item_frame_extended;
 * chop_frame;
 * same_frame_extended;
 * append_frame;
 * full_frame;
 * }
 */
public abstract class StackMapFrame {

    protected final int frameType;

    public StackMapFrame(int frameType, ClassReader reader) {
        this.frameType = frameType;
    }

    public static StackMapFrame[] readStackMapFrames(ClassReader reader, int frameCount) {
        StackMapFrame[] stackMapFrames = new StackMapFrame[frameCount];

        for (int i = 0; i < frameCount; i++) {
            stackMapFrames[i] = readStackMapFrame(reader);
        }

        return stackMapFrames;
    }

    public static StackMapFrame readStackMapFrame(ClassReader reader) {
        int frameType = reader.nextU1toInt();
        return StackMapFrameFactory.newInstance(frameType, reader);
    }

}
