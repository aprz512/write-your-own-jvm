package write.your.own.jvm.instruction.store;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.aastore">...</a>
 */
public class AAStore implements Instruction {

    @Override
    public int getOpCode() {
        return 0x53;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject value = frame.getOperandStack().popRef();
        int index = frame.getOperandStack().popInt();
        ArrayObject arrayObject = (ArrayObject) frame.getOperandStack().popRef();
        if (arrayObject == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        checkIndex(index, arrayObject.getArrayLength());
        arrayObject.setArrayElement(index, value);
    }

    private void checkIndex(int index, int arrayLength) {
        if (index < 0 || index >= arrayLength) {
            throw new MyJvmException("ArrayIndexOutOfBoundsException");
        }
    }

    @Override
    public String getReadableName() {
        return "aastore";
    }

}
