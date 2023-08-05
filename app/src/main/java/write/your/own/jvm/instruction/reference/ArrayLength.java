package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyObject;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.arraylength">...</a>
 */
public class ArrayLength implements Instruction {

    @Override
    public int getOpCode() {
        return 0xbe;
    }

    @Override
    public void execute(StackFrame frame) {
        MyObject myObject = frame.getOperandStack().popRef();
        if (myObject == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        int arrayLength = ((ArrayObject) myObject).getArrayLength();
        frame.getOperandStack().pushInt(arrayLength);
    }

    @Override
    public String getReadableName() {
        return "arraylength";
    }

}
