package write.your.own.jvm;

import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.InstructionFactory;
import write.your.own.jvm.runtimedata.MyThread;
import write.your.own.jvm.runtimedata.OperandStack;
import write.your.own.jvm.runtimedata.StackFrame;
import write.your.own.jvm.runtimedata.heap.ArrayObject;
import write.your.own.jvm.runtimedata.heap.MyClass;
import write.your.own.jvm.runtimedata.heap.MyClassLoader;
import write.your.own.jvm.runtimedata.heap.MyMethod;
import write.your.own.jvm.util.Log;

import java.util.List;

public class Interpreter {

    public void interpret(MyMethod method, List<String> args) {
        MyThread thread = new MyThread();
        StackFrame stackFrame = thread.newStackFrame(method);
        thread.pushStackFrame(stackFrame);
        // support read args from cmd
        ArrayObject arrayObject = createArgs(args, method.getMyClass().getClassLoader());
        stackFrame.getLocalVariableTable().setRef(0, arrayObject);

        loop(thread);
    }

    private ArrayObject createArgs(List<String> args, MyClassLoader classLoader) {
        MyClass stringClass = classLoader.loadClass("java/lang/String");
        MyClass arrayClass = stringClass.getArrayClass();
        ArrayObject arrayObject = arrayClass.newArrayObject(args.size());
        for (int i = 0; i < args.size(); i++) {
            arrayObject.setArrayElement(i, args.get(i));
        }
        return arrayObject;
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

        if (Cmd.Config.verboseOperandStackFlag) {
            OperandStack operandStack = stackFrame.getOperandStack();
            operandStack.print();
        }

        Log.d(thisClassName + "." + methodName + "() #" + pc + " -> " + instruction.getReadableName());
    }

}
