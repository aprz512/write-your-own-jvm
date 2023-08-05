package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.MyMethod;

public class StackFrame {

    private final OperandStack operandStack;
    private final LocalVariableTable localVariableTable;

    private final MyThread thread;
    private int nextPc;

    private final MyMethod myMethod;


    public StackFrame(MyThread thread, MyMethod myMethod) {
        this.thread = thread;
        this.myMethod = myMethod;
        this.localVariableTable = new LocalVariableTable(myMethod.getMaxLocals());
        this.operandStack = new OperandStack(myMethod.getMaxStack());
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public LocalVariableTable getLocalVariableTable() {
        return localVariableTable;
    }

    public MyThread getThread() {
        return thread;
    }

    public int getNextPc() {
        return nextPc;
    }

    public void setNextPc(int nextPc) {
        this.nextPc = nextPc;
    }

    public MyMethod getMyMethod() {
        return myMethod;
    }

    public void revertPc() {
        this.nextPc = this.thread.getPc();
    }
}
