package write.your.own.jvm;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.InstructionFactory;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.util.Log;

public class Interpreter {

    public void interpret(MyMethod method) {
        MyThread thread = new MyThread();
        StackFrame stackFrame = thread.newStackFrame(method);
        thread.pushStackFrame(stackFrame);

        loop(thread, method.getCode());
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

            Log.d("pc = " + pc + ", inst = " + instruction.getReadableName());

            frame.setNextPc(codeReader.getPc());
            instruction.execute(frame);
        }
    }

}
