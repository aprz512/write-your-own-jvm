package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.InstanceFields;
import write.your.own.jvm.runtimedata.heap.MyField;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.FieldRef;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.getfield">...</a>
 */
public class GetField extends Operand1Instruction {

    public GetField(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xb4;
    }

    @Override
    public void execute(StackFrame frame) {
        MyMethod myMethod = frame.getMyMethod();
        ConstantPool constantPool = myMethod.getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        FieldRef fieldRef = (FieldRef) constant.value;
        MyField field = fieldRef.resolvedField();
        if (field.isStatic()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }
        OperandStack operandStack = frame.getOperandStack();
        MyObject refObj = operandStack.popRef();
        if (refObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        InstanceFields instanceFields = refObj.getInstanceFields();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                operandStack.pushInt(instanceFields.getInt(slotId));
                break;
            case 'F':
                operandStack.pushFloat(instanceFields.getFloat(slotId));
                break;
            case 'J':
                operandStack.pushLong(instanceFields.getLong(slotId));
                break;
            case 'D':
                operandStack.pushDouble(instanceFields.getDouble(slotId));
                break;
            case 'L':
            case '[':
                operandStack.pushRef(instanceFields.getRef(slotId));
                break;
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public String getReadableName() {
        return "getfield";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
