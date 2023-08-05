package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.classfile.constantpool.ConstantInfo;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.MyString;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.ldc">...</a>
 */
public class Ldc extends Operand1Instruction {

    public Ldc(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0x12;
    }

    @Override
    public void execute(StackFrame frame) {
        OperandStack operandStack = frame.getOperandStack();
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        MyClassLoader currentClassLoader = constantPool.getMyClass().getClassLoader();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        switch (constant.tag) {
            case ConstantInfo.CONST_TAG_INTEGER:
                operandStack.pushInt((Integer) constant.value);
                break;
            case ConstantInfo.CONST_TAG_FLOAT:
                operandStack.pushFloat((Float) constant.value);
                break;
            case ConstantInfo.CONST_TAG_CLASS:
                ClassRef classRef = (ClassRef) constant.value;
                MyObject jClass = classRef.getResolvedClass().getJClass();
                operandStack.pushRef(jClass);
                break;
            case ConstantInfo.CONST_TAG_STRING:
                // string
                MyObject stringObject = MyString.create((String) constant.value, currentClassLoader);
                operandStack.pushRef(stringObject);
                break;
            default:
                throw new NotImplementedException("tag = " + constant.tag);
        }
    }

    @Override
    public String getReadableName() {
        return "ldc";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedByte();
    }
}
