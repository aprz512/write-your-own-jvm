package write.your.own.jvm.runtimedata;

public class StackFrame {

    private OperandStack operandStack;
    private LocalVars localVars;

    private MyThread thread;
    private int nextPc;


    public StackFrame(MyThread thread, int maxLocals, int maxStack) {
        this.thread = thread;
        this.localVars = new LocalVars(maxLocals);
        this.operandStack = new OperandStack(maxStack);
    }

    public OperandStack getOperandStack() {
        return operandStack;
    }

    public LocalVars getLocalVars() {
        return localVars;
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
}
