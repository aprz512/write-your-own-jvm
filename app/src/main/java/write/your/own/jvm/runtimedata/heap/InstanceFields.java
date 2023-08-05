package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.runtimedata.LocalVariableTable;
import write.your.own.jvm.runtimedata.Slot;

public class InstanceFields extends LocalVariableTable {
    public InstanceFields(int slotSize) {
        super(slotSize);
    }

    public InstanceFields cloneInstanceFields() {
        InstanceFields table = new InstanceFields(this.slots.length);
        for (int i = 0; i < this.slots.length; i++) {
            Slot slot = new Slot(null);
            slot.setRef(this.slots[i].getRef());
            slot.setValue(this.slots[i].getValue());
            table.setSlot(i, slot);
        }
        return table;
    }
}
