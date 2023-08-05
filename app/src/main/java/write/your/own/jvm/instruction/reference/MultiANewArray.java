package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand2Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.multianewarray">...</a>
 */
public class MultiANewArray extends Operand2Instruction {

    public MultiANewArray(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xc5;
    }

    @Override
    public void execute(StackFrame frame) {
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(op1);
        ClassRef classRef = (ClassRef) constant.value;
        MyClass arrClass = classRef.getResolvedClass();
        int[] dimensions = popAndCheckCounts(frame.getOperandStack(), op2);
        ArrayObject arrayObject = newMultiDimensionalArray(dimensions, arrClass);
        frame.getOperandStack().pushRef(arrayObject);
    }

    private ArrayObject newMultiDimensionalArray(int[] dimensions, MyClass arrClass) {
        int dimension = dimensions[0];
        ArrayObject arrayObject = arrClass.newArrayObject(dimension);
        if (dimensions.length > 1) {
            for (int i = 0; i < arrayObject.getArrayLength(); i++) {
                arrayObject.setArrayElement(i, newMultiDimensionalArray(subArray(1, dimensions), arrClass.getComponentClass()));
            }
        }
        return arrayObject;
    }


    private int[] subArray(int index, int[] array) {
        int[] result = new int[array.length - index];
        if (array.length - index >= 0) System.arraycopy(array, index, result, 0, array.length - index);
        return result;
    }

    private int[] popAndCheckCounts(OperandStack stack, int dimensions) {
        int[] result = new int[dimensions];
        for (int i = dimensions - 1; i >= 0; i--) {
            result[i] = stack.popInt();
            if (result[i] < 0) {
                throw new MyJvmException("java.lang.NegativeArraySizeException");
            }
        }
        return result;
    }

    @Override
    public String getReadableName() {
        return "multianewarray";
    }

    @Override
    public int readOperand1(CodeReader reader) {
        return reader.readUnsignedShort();
    }

    @Override
    public int readOperand2(CodeReader reader) {
        return reader.readUnsignedByte();
    }
}
