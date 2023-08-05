package write.your.own.jvm.runtimedata;

import org.checkerframework.checker.units.qual.A;
import write.your.own.jvm.runtimedata.heap.MyMethod;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.function.Consumer;

public class MyThread {
    // if stackFrame bigger than maxStackFrame, we throw StackOverFlowException
    private final int maxStackFrame = 1024;
    private final Stack<StackFrame> stack = new Stack<>();
    private int pc;

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

    public StackFrame newStackFrame(MyMethod method) {
        return new StackFrame(this, method);
    }

    public boolean isStackFrameEmpty() {
        return stack.isEmpty();
    }

    public void clearStack() {
        stack.clear();;
    }

    public List<StackFrame> getStackTraceFrames(int skipFrame) {
        List<StackFrame> frames = new ArrayList<>(stack);
        while (skipFrame > 0) {
            frames.remove(frames.size() - 1);
            skipFrame--;
        }
        return frames;
    }
}

