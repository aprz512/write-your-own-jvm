package write.your.own.jvm.classfile.attribute;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.classfile.constantpool.ConstantMethodHandleInfo;
import write.your.own.jvm.classfile.constantpool.ConstantMethodTypeInfo;
import write.your.own.jvm.classfile.constantpool.ConstantPool;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-4.html#jvms-4.7.21">...</a>
 * BootstrapMethods_attribute {
 * u2 attribute_name_index;
 * u4 attribute_length;
 * u2 num_bootstrap_methods;
 * {   u2 bootstrap_method_ref;
 * u2 num_bootstrap_arguments;
 * u2 bootstrap_arguments[num_bootstrap_arguments];
 * } bootstrap_methods[num_bootstrap_methods];
 * }
 */
public class BootstrapMethodsAttribute extends AttributeInfo {

    private final int numBootstrapMethods;
    private final BootstrapMethod[] bootstrapMethods;
    private final ConstantPool constantPool;

    public BootstrapMethodsAttribute(int attributeNameIndex, int attributeLength, ClassReader reader, ConstantPool constantPool) {
        super(attributeNameIndex, attributeLength);
        this.constantPool = constantPool;
        numBootstrapMethods = reader.nextU2ToInt();
        bootstrapMethods = new BootstrapMethod[numBootstrapMethods];
        for (int i = 0; i < numBootstrapMethods; i++) {
            bootstrapMethods[i] = new BootstrapMethod(reader, constantPool);
        }
    }

    public int getNumBootstrapMethods() {
        return numBootstrapMethods;
    }

    public BootstrapMethod[] getBootstrapMethods() {
        return bootstrapMethods;
    }

    public static class BootstrapMethod {
        // 方法索引
        private final int bootstrapMethodRef;
        // 参数个数
        private final int numBootstrapArguments;
        // 每个参数对应在常量池中的索引
        private final int[] bootstrapArguments;

        private final ConstantPool constantPool;

        public BootstrapMethod(ClassReader reader, ConstantPool constantPool) {
            this.constantPool = constantPool;

            bootstrapMethodRef = reader.nextU2ToInt();
            numBootstrapArguments = reader.nextU2ToInt();
            bootstrapArguments = new int[numBootstrapArguments];
            for (int i = 0; i < numBootstrapArguments; i++) {
                bootstrapArguments[i] = reader.nextU2ToInt();
            }
        }

        public int getBootstrapMethodRef() {
            return bootstrapMethodRef;
        }

        public int getNumBootstrapArguments() {
            return numBootstrapArguments;
        }

        public int[] getBootstrapArguments() {
            return bootstrapArguments;
        }

        public String getMethodTypeDescriptor() {
            // method type ref
            ConstantMethodTypeInfo constantInfo = (ConstantMethodTypeInfo) constantPool.getConstantInfos()[bootstrapArguments[0]];
            return constantInfo.getDescriptor();
        }

        public int getMethodHandleRef() {
            // method type ref
            ConstantMethodHandleInfo constantInfo = (ConstantMethodHandleInfo) constantPool.getConstantInfos()[bootstrapArguments[1]];
            return constantInfo.getReferenceIndex();
        }

    }


}
