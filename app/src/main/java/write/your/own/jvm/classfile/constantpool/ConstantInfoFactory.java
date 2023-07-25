package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.exception.NotImplementedException;

import static write.your.own.jvm.classfile.constantpool.ConstantInfo.*;

public class ConstantInfoFactory {

    // Factory method
    static ConstantInfo createConstantInfo(int tag, ClassReader reader, ConstantPool constPool) {
        switch (tag) {
            // 数字
            case CONST_TAG_INTEGER:
                return new ConstantIntegerInfo(reader);
            case CONST_TAG_FLOAT:
                return new ConstantFloatInfo(reader);
            case CONST_TAG_LONG:
                return new ConstantLongInfo(reader);
            case CONST_TAG_DOUBLE:
                return new ConstantDoubleInfo(reader);

            case CONST_TAG_UTF8:
                return new ConstantUft8Info(reader);

            // 以下3个引用UTF8
            case CONST_TAG_STRING:
                return new ConstantStringInfo(constPool, reader);
            case CONST_TAG_CLASS:
                return new ConstantClassInfo(constPool, reader);
            case CONST_TAG_NAME_AND_TYPE:
                return new ConstantNameAndTypeInfo(constPool, reader);

            // 以下3个引用CLASS+NAME_AND_TYPE
            case CONST_TAG_FIELD_REF:
            case CONST_TAG_METHOD_REF:
            case CONST_TAG_INTERFACE_METHOD_REF:
                return new ConstantMemberRefInfo(constPool, reader, tag);

            // 是Java SE 7才添加到class文件中的，目的是支持新增的 invokedynamic 指令
            // 暂不处理
            case CONST_TAG_METHOD_HANDLE:
            case CONST_TAG_METHOD_TYPE:
            case CONST_TAG_INVOKE_DYNAMIC:
                throw new NotImplementedException();

            default:
                throw new ClassFormatError("constant pool tag!");
        }
    }
}
