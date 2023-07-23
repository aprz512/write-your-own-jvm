package write.your.own.jvm.instruction.extended;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.control.Goto;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.goto_w">...</a>
 */
public class GotoW extends Goto {

    public GotoW(CodeReader reader) {
        super(reader);
    }

    @Override
    public String getReadableName() {
        return "goto_w";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readInt();
    }
}
