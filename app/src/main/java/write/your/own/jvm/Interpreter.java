package write.your.own.jvm;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.InstructionFactory;
import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.util.Log;

public class Interpreter {

    public void interpret(MyThread thread) {
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

            instruction.execute(stackFrame);

            if (Cmd.Config.verboseInstFlag) {
                logInstruction(instruction, stackFrame);
            }


        } while (!thread.isStackFrameEmpty());
    }

    private void logInstruction(Instruction instruction, StackFrame stackFrame) {
        MyMethod myMethod = stackFrame.getMyMethod();
        String thisClassName = myMethod.getMyClass().getThisClassName();
        String methodName = myMethod.getName();
        int pc = stackFrame.getThread().getPc();

        Log.d(thisClassName + "." + methodName + "() #" + pc + " -> " + instruction.getReadableName());

        if (Cmd.Config.verboseFrameFlag) {
            OperandStack operandStack = stackFrame.getOperandStack();
            operandStack.print();
            LocalVariableTable localVariableTable = stackFrame.getLocalVariableTable();
            localVariableTable.print();
        }

        Log.d("");
    }

}
