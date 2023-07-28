package write.your.own.jvm;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.InstructionFactory;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.util.Log;

public class Interpreter {

    public void interpret(MyMethod method) {
        MyThread thread = new MyThread();
        StackFrame stackFrame = thread.newStackFrame(method);
        thread.pushStackFrame(stackFrame);

        loop(thread);
    }

    public void loop(MyThread thread) {
        CodeReader codeReader = new CodeReader();
        do {
            StackFrame stackFrame = thread.currentStackFrame();
            MyMethod method = stackFrame.getMyMethod();
            int pc = stackFrame.getNextPc();
            thread.setPc(pc);

            // decode instruction
            codeReader.reset(method.getCode(), pc);
            int opCode = codeReader.readUnsignedByte();
            Instruction instruction = InstructionFactory.create(opCode, codeReader);
            stackFrame.setNextPc(codeReader.getPc());

            if (Cmd.Config.verboseInstFlag) {
                logInstruction(instruction, stackFrame);
            }

            instruction.execute(stackFrame);

        } while (!thread.isStackFrameEmpty());
    }

    private void logInstruction(Instruction instruction, StackFrame stackFrame) {
        MyMethod myMethod = stackFrame.getMyMethod();
        String thisClassName = myMethod.getMyClass().getThisClassName();
        String methodName = myMethod.getName();
        int pc = stackFrame.getThread().getPc();

        OperandStack operandStack = stackFrame.getOperandStack();
        operandStack.print();

        Log.d(thisClassName + "." + methodName + "() #" + pc + " -> " + instruction.getReadableName());
    }

}
