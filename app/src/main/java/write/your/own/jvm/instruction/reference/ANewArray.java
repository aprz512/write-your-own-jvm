package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.anewarray">...</a>
 */
public class ANewArray extends Operand1Instruction {

    public ANewArray(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xbd;
    }

    @Override
    public void execute(StackFrame frame) {
        int count = frame.getOperandStack().popInt();
        if (count < 0) {
            throw new MyJvmException("java.lang.NegativeArraySizeException");
        }
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        // must be a symbolic reference to a class, array, or interface type.
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        ClassRef classRef = (ClassRef) constant.value;
        MyClass resolvedClass = classRef.getResolvedClass();
        MyClass arrayClass = resolvedClass.toArrayClass();
        ArrayObject arrayObject = arrayClass.newArrayObject(count);
        frame.getOperandStack().pushRef(arrayObject);
    }

    private MyClass createPrimitiveArrayClass(MyClassLoader loader, int type) {
        switch (type) {
            case ArrayType.T_BOOLEAN:
                return loader.loadClass("[Z");
            case ArrayType.T_BYTE:
                return loader.loadClass("[B");
            case ArrayType.T_CHAR:
                return loader.loadClass("[C");
            case ArrayType.T_SHORT:
                return loader.loadClass("[S");
            case ArrayType.T_INT:
                return loader.loadClass("[I");
            case ArrayType.T_LONG:
                return loader.loadClass("[J");
            case ArrayType.T_FLOAT:
                return loader.loadClass("[F");
            case ArrayType.T_DOUBLE:
                return loader.loadClass("[D");
            default:
                throw new MyJvmException("unknown type : " + type);
        }
    }

    @Override
    public String getReadableName() {
        return "anewarray";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
