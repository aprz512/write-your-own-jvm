package write.your.own.jvm.runtimedata;

import java.util.Stack;

public class MyThread {
    // if stackFrame bigger than maxStackFrame, we throw StackOverFlowException
    private final int maxStackFrame = 1024;
    private int pc;
    private final Stack<StackFrame> stack = new Stack<>();

    public void pushStackFrame(StackFrame frame) {
        if (stack.size() >= 100) {
            throw new StackOverflowError("method invoke chain too long!!!");
        }
        stack.push(frame);
    }

    public StackFrame popStackFrame() {
        if (stack.size() == 0) {
            throw new IllegalStateException("no method in stack!!!");
        }
        return stack.pop();
    }

    public StackFrame currentStackFrame() {
        if (stack.size() == 0) {
            throw new IllegalStateException("no method in stack!!!");
        }
        return stack.peek();
    }

    public int getPc() {
        return pc;
    }

    public void setPc(int pc) {
        this.pc = pc;
    }

    public StackFrame newStackFrame(int maxLocals, int maxStack) {
        return new StackFrame(this, maxLocals, maxStack);
    }
}

