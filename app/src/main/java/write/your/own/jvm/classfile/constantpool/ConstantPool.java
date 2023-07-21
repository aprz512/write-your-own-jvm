package write.your.own.jvm.classfile.constantpool;

import write.your.own.jvm.classfile.ClassReader;

/**
 * The constant_pool is a table of structures representing various
 * string constants, class and interface names, field names,
 * and other constants that are referred to within the ClassFile structure and its substructures.
 * 常量池的结构：
 * cp_info {
 * u1 tag;
 * u1 info[];
 * }
 * info[] 数组的值是一个 tag，tag对应的是一个常量类型。Java虚拟机规范一共定义了14种常量。
 * 这里，我们使用接口来模拟这tag值。
 * <p>
 * 常量池内容大致如下：
 * constant_methodref_info
 * constant_fieldref_info
 * constant_fieldref_info
 * constant_class_info
 * constant_string_info
 * constant_utf8_info
 * constant_utf8_info
 * constant_utf8_info
 * constant_utf8_info
 * constant_utf8_info
 * constant_utf8_info
 * <p>
 * <p>
 * constant_class_info 储存的是 constant_utf8_info 的引用（位置）
 */
public class ConstantPool {

    private final int size;
    private final ConstantInfo[] constantInfos;

    public ConstantPool(ClassReader reader) {
        // 0是无效索引，表示不指向任何常量。
        size = reader.nextU2ToInt();
        constantInfos = new ConstantInfo[size];
        constantInfos[0] = new InvalidConstantInfo();
        for (int i = 1; i < size; i++) {
            int tag = reader.nextU1toInt();
            ConstantInfo constInfo = ConstantInfoFactory.createConstantInfo(tag, reader, this);
            constantInfos[i] = constInfo;
            // double and long 占2个位置
            if (tag == ConstantInfo.CONST_TAG_DOUBLE || tag == ConstantInfo.CONST_TAG_LONG) {
                i++;
            }
        }
    }

    /**
     * 这里是一个递归的逻辑：
     * A -> getUTF8 -> B -> getUTF8
     *
     * @param index
     * @return
     */
    public String getUTF8(int index) {
        ConstantInfo constInfo = this.constantInfos[index];
        return constInfo.getValue();
    }

    // should use deep copy?
    public ConstantInfo[] getConstantInfos() {
        return constantInfos;
    }

    public int getSize() {
        return size;
    }
}
