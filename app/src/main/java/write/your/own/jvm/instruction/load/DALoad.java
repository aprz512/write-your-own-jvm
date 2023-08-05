package write.your.own.jvm.instruction.load;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.daload">...</a>
 * <p>
 * byte or boolean
 */
public class DALoad implements Instruction {

    @Override
    public int getOpCode() {
        return 0x31;
    }

    @Override
    public void execute(StackFrame frame) {
        int index = frame.getOperandStack().popInt();
        ArrayObject arrayObject = (ArrayObject) frame.getOperandStack().popRef();
        if (arrayObject == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        checkIndex(index, arrayObject.getArrayLength());
        frame.getOperandStack().pushDouble((double) arrayObject.getArrayElement(index));
    }

    private void checkIndex(int index, int arrayLength) {
        if (index < 0 || index >= arrayLength) {
            throw new MyJvmException("ArrayIndexOutOfBoundsException");
        }
    }

    @Override
    public String getReadableName() {
        return "daload";
    }
}
