package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.MethodRef;
import write.your.own.jvm.util.Log;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokevirtual">...</a>
 */
public class InvokeVirtual extends Operand1Instruction {

    public InvokeVirtual(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xb6;
    }

    @Override
    public void execute(StackFrame frame) {
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        MethodRef methodRef = (MethodRef) constant.value;
        if ("println".equals(methodRef.getName())) {
            OperandStack operandStack = frame.getOperandStack();
            switch (methodRef.getDescriptor()) {
                case "(Z)V":
                    Log.d(String.valueOf(operandStack.popInt() != 0));
                    break;
                case "(C)V":
                case "(I)V":
                case "(B)V":
                case "(S)V":
                    Log.d(String.valueOf(operandStack.popInt()));
                    break;
                case "(F)V":
                    Log.d(String.valueOf(operandStack.popFloat()));
                    break;
                case "(J)V":
                    Log.d(String.valueOf(operandStack.popLong()));
                    break;
                case "(D)V":
                    Log.d(String.valueOf(operandStack.popDouble()));
                    break;
                default:
                    throw new NotImplementedException();
            }
            operandStack.popRef();
        }
    }

    @Override
    public String getReadableName() {
        return "invokevirtual";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
