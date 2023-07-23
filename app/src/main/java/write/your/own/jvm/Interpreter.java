package write.your.own.jvm;

import write.your.own.jvm.classfile.MemberInfo;
import write.your.own.jvm.classfile.attribute.CodeAttribute;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.InstructionFactory;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;

public class Interpreter {

    public void interpret(MemberInfo memberInfo) {
        CodeAttribute codeAttribute = memberInfo.getCodeAttribute();
        int maxLocals = codeAttribute.getMaxLocals();
        int maxStack = codeAttribute.getMaxStack();
        byte[] code = codeAttribute.getCode();

        MyThread thread = new MyThread();
        StackFrame stackFrame = thread.newStackFrame(maxLocals, maxStack);
        thread.pushStackFrame(stackFrame);

        loop(thread, code);
    }

    public void loop(MyThread thread, byte[] code) {
        StackFrame frame = thread.popStackFrame();
        CodeReader codeReader = new CodeReader();
        while (true) {
            int pc = frame.getNextPc();
            thread.setPc(pc);

            codeReader.reset(code, pc);
            int opCode = codeReader.readUnsignedByte();
            Instruction instruction = InstructionFactory.create(opCode, codeReader);

            frame.setNextPc(codeReader.getPc());
            instruction.execute(frame);
        }
    }

}
