package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.InvokeMethod;
import write.your.own.jvm.instruction.base.Operand1Instruction;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
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

    private static void print(MethodRef methodRef, OperandStack operandStack) {
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
        MyMethod resolvedMethod = methodRef.getResolvedMethod();
        MyClass resolvedClass = methodRef.getResolvedClass();
        MyClass currentClass = frame.getMyMethod().getMyClass();

        if (resolvedMethod.isStatic()) {
            throw new MyJvmException("java.lang.IncompatibleClassChangeError");
        }

        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.getRefFromTop(resolvedMethod.getArgsSlotCount() - 1);
        if (ref == null) {
            // System.out.println hack
            if ("println".equals(methodRef.getName())) {
                print(methodRef, operandStack);
                operandStack.popRef();
                return;
            }
            throw new MyJvmException("java.lang.NullPointerException");
        }


        // 确保protected方法只能被声明该方法的类或子类调用
        if (resolvedMethod.isProtected()
                && resolvedClass.isSuperClassOf(currentClass)
                && !resolvedClass.getPackageName().equals(currentClass.getPackageName())
                && ref.getMyClass() != currentClass
                && !ref.getMyClass().isSubClassOf(currentClass)) {
            throw new MyJvmException("java.lang.IllegalAccessError");
        }

        MyMethod lookupMethod = MethodRef.lookupMethodInClass(ref.getMyClass(), resolvedMethod.getName(), resolvedMethod.getDescriptor());
        if (lookupMethod == null || lookupMethod.isAbstract()) {
            throw new MyJvmException("java.lang.AbstractMethodError");
        }
        InvokeMethod.invokeMethod(frame, lookupMethod);

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
