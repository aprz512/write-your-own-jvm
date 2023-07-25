package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.MyMethod;

public class StackFrame {

    private OperandStack operandStack;
    private LocalVariableTable localVariableTable;

    private MyThread thread;
    private int nextPc;

    private MyMethod myMethod;


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
}
