package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.util.Log;
import write.your.own.jvm.util.NumUtil;

/**
 * 操作数栈
 * 操作数栈和局部变量表只存放数据的值，并不记录数据类型。
 */
public class OperandStack {
    private final Slot[] slots;

    // point to last free slot element index
    private int freeIndex;

    public OperandStack(int size) {
        slots = new Slot[size];
        for (int i = 0; i < size; i++) {
            slots[i] = new Slot(null);
        }
    }

    public Slot popSlot() {
        --freeIndex;
        return slots[freeIndex];
    }

    public void pushSlot(Slot slot) {
        slots[freeIndex++] = slot;
    }

    public void pushInt(int val) {
        pushSlot(new Slot(val));
    }

    public int popInt() {
        Slot slot = popSlot();
        return slot.getValue();
    }

    public void pushLong(long val) {
        int low = NumUtil.getLow(val);
        int high = NumUtil.getHigh(val);
        pushSlot(new Slot(high));
        pushSlot(new Slot(low));
    }

    public long popLong() {
        int low = this.popSlot().getValue();
        int high = this.popSlot().getValue();
        return NumUtil.merge(high, low);
    }

    public void pushFloat(float val) {
        int tmp = Float.floatToIntBits(val);
        pushSlot(new Slot(tmp));
    }

    public float popFloat() {
        int tmp = popSlot().getValue();
        return Float.intBitsToFloat(tmp);
    }

    public void pushDouble(double val) {
        long tmp = Double.doubleToLongBits(val);
        pushLong(tmp);
    }

    public double popDouble() {
        long tmp = this.popLong();
        return Double.longBitsToDouble(tmp);
    }

    public void pushRef(MyObject ref) {
        pushSlot(new Slot(ref));
    }

    public MyObject popRef() {
        return popSlot().getRef();
    }

    public MyObject getRefFromTop(int num) {
        return slots[freeIndex - 1 - num].getRef();
    }

    public void print() {
        Log.d("|----------operand stack----------");
        for (int i = 0; i < freeIndex; i++) {
            if (slots[i].isRef()) {
                Log.d("    Ref:slot[" + i + "]=" + slots[i].getRef());
            } else {
                Log.d("    Value:slot[" + i + "]=" + slots[i].getValue());
            }
        }
        Log.d("|----------operand stack----------");
    }

}
