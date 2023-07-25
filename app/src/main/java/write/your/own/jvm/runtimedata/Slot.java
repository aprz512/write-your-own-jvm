package write.your.own.jvm.runtimedata;

import write.your.own.jvm.runtimedata.heap.MyObject;

public class Slot {

    // used for gc
    // if we not reference heap instance, object will be release
    private MyObject ref;
    private int value;

    public Slot(int value) {
        this.value = value;
    }

    public Slot(MyObject ref) {
        this.ref = ref;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public MyObject getRef() {
        return ref;
    }

    public void setRef(MyObject ref) {
        this.ref = ref;
    }
}
