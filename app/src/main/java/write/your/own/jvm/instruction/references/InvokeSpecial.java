package write.your.own.jvm.instruction.references;

import write.your.own.jvm.exception.MyJvmException;
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

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.invokespecial">...</a>
 */
public class InvokeSpecial extends Operand1Instruction {

    public InvokeSpecial(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xb7;
    }

    @Override
    public void execute(StackFrame frame) {
        ConstantPool constantPool = frame.getMyMethod().getMyClass().getConstantPool();
        ConstantPool.Constant constant = constantPool.getConstant(operand);
        MethodRef methodRef = (MethodRef) constant.value;
        MyMethod resolvedMethod = methodRef.getResolvedMethod();
        MyClass resolvedClass = methodRef.getResolvedClass();
        MyClass currentClass = frame.getMyMethod().getMyClass();

        // ..., objectref, [arg1, [arg2 ...]] →
        OperandStack operandStack = frame.getOperandStack();
        MyObject ref = operandStack.getRefFromTop(resolvedMethod.getArgsSlotCount() - 1);
        if (ref == null) {
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

        MyMethod lookupMethod = resolvedMethod;
        if (currentClass.isSuper() && resolvedClass.isSuperClassOf(currentClass)
                && !resolvedMethod.getName().equals("<init>")) {
            lookupMethod = MethodRef.lookupMethodInClass(currentClass.getSuperClass(), resolvedMethod.getName(), resolvedMethod.getDescriptor());
        }
        if (lookupMethod == null || lookupMethod.isAbstract()) {
            throw new MyJvmException("java.lang.AbstractMethodError");
        }
        InvokeMethod.invokeMethod(frame, lookupMethod);
    }

    @Override
    public String getReadableName() {
        return "invokespecial";
    }

    @Override
    protected int readOperand(CodeReader reader) {
        return reader.readUnsignedShort();
    }
}
