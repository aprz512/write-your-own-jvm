package write.your.own.jvm.instruction.references;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.instanceof">...</a>
 */
public class InstanceOf extends Operand1Instruction {

    public InstanceOf(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xc1;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        MyObject myObject = operandStack.popRef();
        if (myObject == null) {
            operandStack.pushInt(0);
            return;
        }
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        ClassRef classRef = (ClassRef) constant.value;
        MyClass refClass = classRef.getResolvedClass();
        if (myObject.getMyClass().instanceOf(refClass)) {
            operandStack.pushInt(1);
        } else {
            operandStack.pushInt(0);
        }
    }

    @Override
    public String getReadableName() {
        return "instanceof";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
