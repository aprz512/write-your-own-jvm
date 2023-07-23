package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.lookupswitch">...</a>
 */
public class LookupSwitch implements Instruction {

    private final int defaultOffset;
    private final int npairs;
    private int[] matchOffsets;

    public LookupSwitch(CodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt();
        npairs = reader.readInt();
        matchOffsets = reader.readInts(npairs * 2);
    }

    @Override
    public int getOpCode() {
        return 0xab;
    }

    @Override
    public void execute(StackFrame frame) {
        int value = frame.getOperandStack().popInt();
        for (int i = 0; i < 2 * npairs; i += 2) {
            if (matchOffsets[i] == value) {
                frame.setNextPc(frame.getThread().getPc() + matchOffsets[i + 1]);
                return;
            }
        }
        frame.setNextPc(frame.getThread().getPc() + defaultOffset);
    }

    @Override
    public String getReadableName() {
        return "lookupswitch";
    }
}
