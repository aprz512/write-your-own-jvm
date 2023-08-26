package write.your.own.jvm.instruction.reference;

import write.your.own.jvm.classfile.attribute.BootstrapMethodsAttribute;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.Operand2Instruction;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;
import write.your.own.jvm.runtimedata.heap.constants.InvokeDynamicConstant;
import write.your.own.jvm.runtimedata.heap.constants.MethodRef;

/**
 * This instruction only support simple lambda
 */
public class InvokeDynamic extends Operand2Instruction {

    public InvokeDynamic(CodeReader reader) {
        super(reader);
    }

    @Override
    public int getOpCode() {
        return 0xba;
    }

    @Override
    public void execute(StackFrame frame) {
        // 只是简单的支持 lambda 表达式
        MyClass myClass = frame.getMyMethod().getMyClass();
        ConstantPool constantPool = myClass.getConstantPool();

        // invokeDynamic
        ConstantPool.Constant dynamicConstant = constantPool.getConstant(op1);
        InvokeDynamicConstant invokeDynamic = (InvokeDynamicConstant) dynamicConstant.value;

        String[] nameAndType = invokeDynamic.getNameAndType();
        String lambdaInterfaceName = nameAndType[1].replace("()L", "").replace(";", "");

        BootstrapMethodsAttribute bootstrapMethodsAttribute = myClass.getBootstrapMethodsAttribute();
        BootstrapMethodsAttribute.BootstrapMethod[] bootstrapMethods = bootstrapMethodsAttribute.getBootstrapMethods();
        BootstrapMethodsAttribute.BootstrapMethod bootstrapMethod = bootstrapMethods[invokeDynamic.getBootstrapMethodAttrIndex()];

        // 接口描述
        String descriptor = bootstrapMethod.getMethodTypeDescriptor();

        // 方法在常量池中的引用
        int methodHandleRef = bootstrapMethod.getMethodHandleRef();
        ConstantPool.Constant constant = constantPool.getConstant(methodHandleRef);

        // method to be executed
        MethodRef ref = (MethodRef) constant.value;
        MyMethod resolvedMethod = ref.getResolvedMethod();

        // create a lambda class is too complex
        // 可以创建一个虚拟的 lambda 类，但是类里面的信息却搞不定，比如，常量池等等，所以方法没法执行
        MyClass lambdaClass = MyClass.createLambdaClass(
                myClass,
                myClass.getThisClassName(),
                lambdaInterfaceName,
                frame.getMyMethod().getName(),
                op1,
                resolvedMethod);
        MyObject myObject = lambdaClass.newObject();

        frame.getOperandStack().pushRef(myObject);
    }

    @Override
    public String getReadableName() {
        return "invokedynamic";
    }

    @Override
    public int readOperand1(CodeReader reader) {
        return reader.readUnsignedShort();
    }

    @Override
    public int readOperand2(CodeReader reader) {
        return reader.readShort();
    }

}
