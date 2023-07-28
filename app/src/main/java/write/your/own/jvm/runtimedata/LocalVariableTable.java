package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.MyObject;
import write.your.own.jvm.util.NumUtil;

/**
 * 局部变量表
 * 操作数栈和局部变量表只存放数据的值，并不记录数据类型。
 */
public class LocalVariableTable {

    private final Slot[] slots;

    public LocalVariableTable(int slotSize) {
        slots = new Slot[slotSize];
        for (int i = 0; i < slotSize; i++) {
            slots[i] = new Slot(null);
        }
    }

    public void setSlot(int index, Slot slot) {
        slots[index] = slot;
    }

    public void setInt(int index, int val) {
        slots[index].setValue(val);
    }

    public int getInt(int index) {
        return slots[index].getValue();
    }

    public void setFloat(int index, float val) {
        int floatValue = Float.floatToIntBits(val);
        slots[index].setValue(floatValue);
    }

    public float getFloat(int index) {
        int floatValue = slots[index].getValue();
        return Float.intBitsToFloat(floatValue);
    }

    public void setRef(int index, MyObject ref) {
        slots[index].setRef(ref);
    }

    public MyObject getRef(int index) {
        return slots[index].getRef();
    }

    public long getLong(int index) {
        int high = slots[index].getValue();
        int low = slots[index + 1].getValue();
        return NumUtil.merge(high, low);
    }

    public void setLong(int index, long val) {
        int high = NumUtil.getHigh(val);
        int low = NumUtil.getLow(val);

        slots[index] = new Slot(high);
        slots[index + 1] = new Slot(low);
    }

    public void setDouble(int index, double val) {
        long tmp = Double.doubleToLongBits(val);
        setLong(index, tmp);
    }

    public double getDouble(int index) {
        long tmp = this.getLong(index);
        return Double.longBitsToDouble(tmp);
    }

}