package write.your.own.jvm.instruction.control;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.tableswitch">...</a>
 */
public class TableSwitch implements Instruction {

    private final int defaultOffset;
    private final int low;
    private final int high;
    private final int[] jumpOffsets;

    public TableSwitch(CodeReader reader) {
        reader.skipPadding();
        defaultOffset = reader.readInt();
        low = reader.readInt();
        high = reader.readInt();
        jumpOffsets = reader.readInts(high - low + 1);
    }

    @Override
    public int getOpCode() {
        return 0xAA;
    }

    @Override
    public void execute(StackFrame frame) {
        int index = frame.getOperandStack().popInt();
        int offset;
        if (index <= high && index >= low) {
            offset = jumpOffsets[index - low];
        } else {
            offset = defaultOffset;
        }
        frame.setNextPc(frame.getThread().getPc() + offset);
    }

    @Override
    public String getReadableName() {
        return "tableswitch";
    }
}
