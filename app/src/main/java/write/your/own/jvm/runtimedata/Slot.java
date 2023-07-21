package write.your.own.jvm.runtimedata;

public class Slot {

    // used for gc
    // if we not reference heap instance, object will be release
    private ObjRef ref;
    private int value;

    public Slot(int value) {
        this.value = value;
    }

    public Slot(ObjRef ref) {
        this.ref = ref;
    }

    public int getValue() {
        return value;
    }

    public ObjRef getRef() {
        return ref;
    }
}
