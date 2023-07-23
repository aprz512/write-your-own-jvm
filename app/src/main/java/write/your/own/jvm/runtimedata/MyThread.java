package write.your.own.jvm.runtimedata;

import java.util.Stack;

public class MyThread {
    // if stackFrame bigger than maxStackFrame, we throw StackOverFlowException
    private final int maxStackFrame = 1024;
    private int pc;
    private Stack<StackFrame> stack;

    public void pushStackFrame(StackFrame frame) {
        if (stack.size() >= 100) {
            throw new StackOverflowError("method invoke chain too long!!!");
        }
        stack.push(frame);
    }

    public void popStackFrame() {
        if (stack.size() == 0) {
            throw new IllegalStateException("no method in stack!!!");
        }
        stack.pop();
    }

    public StackFrame currentStackFrame() {
        if (stack.size() == 0) {
            throw new IllegalStateException("no method in stack!!!");
        }
        return stack.peek();
    }
}

