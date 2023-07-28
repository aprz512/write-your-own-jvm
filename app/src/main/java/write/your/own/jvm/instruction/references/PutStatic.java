package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ClassInit;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyField;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.FieldRef;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.putstatic">...</a>
 */
public class PutStatic extends Operand1Instruction {

    public PutStatic(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xb3;
    }

    @Override
    public void execute(StackFrame frame) {
        MyMethod myMethod = frame.getMyMethod();
        ConstantPool constantPool = myMethod.getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        FieldRef fieldRef = (FieldRef) constant.value;
        MyField field = fieldRef.getResolvedField();
        MyClass fieldClass = field.getMyClass();

        if (!fieldClass.isInitStarted()) {
            frame.revertPc();
            ClassInit.initMyClass(fieldClass, frame.getThread());
            return;
        }

        if (!field.isStatic()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }
        // 如果是final字段，则实际操作的是静态常量，只能在类初始化方法中给它赋值。
        if (field.isFinal()) {
            if (myMethod.getMyClass() != fieldClass || !"<clinit>".equals(myMethod.getName())) {
                throw new MyJvmException("java.lang.IllegalAccessError");
            }
        }
        String descriptor = field.getDescriptor();
        int slotId = field.getSlotId();
        LocalVariableTable staticVars = fieldClass.getStaticVars();
        OperandStack operandStack = frame.getOperandStack();
        switch (descriptor.charAt(0)) {
            case 'Z':
            case 'B':
            case 'C':
            case 'S':
            case 'I':
                staticVars.setInt(slotId, operandStack.popInt());
                break;
            case 'F':
                staticVars.setFloat(slotId, operandStack.popFloat());
                break;
            case 'J':
                staticVars.setLong(slotId, operandStack.popLong());
                break;
            case 'D':
                staticVars.setDouble(slotId, operandStack.popDouble());
                break;
            case 'L':
            case '[':
                staticVars.setRef(slotId, operandStack.popRef());
                break;
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public String getReadableName() {
        return "putstatic";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
