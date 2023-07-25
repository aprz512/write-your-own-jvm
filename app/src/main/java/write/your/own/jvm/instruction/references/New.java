package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.new">...</a>
 */
public class New extends Operand1Instruction {


    public New(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xbb;
    }

    @Override
    public void execute(StackFrame frame) {
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        ClassRef classRef = (ClassRef) constant.value;
        MyClass refClass = classRef.resolvedClass();
        if (refClass.isAbstract() || refClass.isInterface()) {
            throw new MyJvmException("java.lang.InstantiationError");
        }
        MyObject myObject = refClass.newObject();
        frame.getOperandStack().pushRef(myObject);
    }

    @Override
    public String getReadableName() {
        return "new";
    }

    /**
     * The unsigned indexbyte1 and indexbyte2 are used to construct an index into the run-time constant pool of the current class (§2.6),
     * where the value of the index is (indexbyte1 << 8) | indexbyte2.
     */
    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
