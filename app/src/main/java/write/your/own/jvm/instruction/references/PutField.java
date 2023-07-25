package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.*;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.FieldRef;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.putfield">...</a>
 */
public class PutField extends Operand1Instruction {

    public PutField(CodeReader reader) {
        super(reader);
    }

    private static void putIntField(OperandStack operandStack, int slotId) {
        int value = operandStack.popInt();
        MyObject refObj = operandStack.popRef();
        if (refObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        InstanceFields instanceFields = refObj.getInstanceFields();
        instanceFields.setInt(slotId, value);
    }

    private static void putFloatField(OperandStack operandStack, int slotId) {
        float value = operandStack.popFloat();
        MyObject refObj = operandStack.popRef();
        if (refObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        InstanceFields instanceFields = refObj.getInstanceFields();
        instanceFields.setFloat(slotId, value);
    }

    private static void putLongField(OperandStack operandStack, int slotId) {
        long value = operandStack.popLong();
        MyObject refObj = operandStack.popRef();
        if (refObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        InstanceFields instanceFields = refObj.getInstanceFields();
        instanceFields.setLong(slotId, value);
    }

    private static void putDoubleField(OperandStack operandStack, int slotId) {
        double value = operandStack.popDouble();
        MyObject refObj = operandStack.popRef();
        if (refObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        InstanceFields instanceFields = refObj.getInstanceFields();
        instanceFields.setDouble(slotId, value);
    }

    private static void putRefField(OperandStack operandStack, int slotId) {
        MyObject value = operandStack.popRef();
        MyObject refObj = operandStack.popRef();
        if (refObj == null) {
            throw new MyJvmException("java.lang.NullPointerException");
        }
        InstanceFields instanceFields = refObj.getInstanceFields();
        instanceFields.setRef(slotId, value);
    }

    @Override
    public int getOpCode() {
        return 0xb5;
    }

    @Override
    public void execute(StackFrame frame) {
        MyMethod myMethod = frame.getMyMethod();
        ConstantPool constantPool = myMethod.getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        FieldRef fieldRef = (FieldRef) constant.value;
        MyField field = fieldRef.resolvedField();
        MyClass fieldClass = field.getMyClass();
        if (field.isStatic()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }
        if (field.isFinal()) {
            if (myMethod.getMyClass() != fieldClass || !"<init>".equals(myMethod.getName())) {
                throw new MyJvmException("java.lang.IllegalAccessError");
            }
        }
        String descriptor = field.getDescriptor();
        OperandStack operandStack = frame.getOperandStack();
        int slotId = field.getSlotId();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                putIntField(operandStack, slotId);
                break;
            case 'F':
                putFloatField(operandStack, slotId);
                break;
            case 'J':
                putLongField(operandStack, slotId);
                break;
            case 'D':
                putDoubleField(operandStack, slotId);
                break;
            case 'L':
            case '[':
                putRefField(operandStack, slotId);
                break;
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public String getReadableName() {
        return "putfield";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
