package write.your.own.jvm.instruction.constant;

import write.your.own.jvm.classfile.constantpool.ConstantPool;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.base.IndexByte1OperandInstruction;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * Push item from run-time constant pool
 */
public class LDC extends IndexByte1OperandInstruction {

    private final ConstantPool constantPool;

    public LDC(CodeReader reader, ConstantPool constantPool) {
        super(reader);
        this.constantPool = constantPool;
    }

    @Override
    public int getOpCode() {
        return 0x12;
    }

    @Override
    public void execute(StackFrame frame) {
//        ConstantInfo info = constantPool.getConstantInfos()[indexByte];
//        if (info instanceof ConstantIntegerInfo) {
//            frame.getOperandStack().pushInt(((ConstantIntegerInfo) info).getIntValue());
//        } else if (info instanceof ConstantFloatInfo) {
//            frame.getOperandStack().pushFloat(((ConstantFloatInfo) info).getFloatValue());
//        } else if (info instanceof ConstantClassInfo) {
//            frame.getOperandStack().pushRef(info.getValue());
//        }
    }

    @Override
    public String getReadableName() {
        return null;
    }
}
