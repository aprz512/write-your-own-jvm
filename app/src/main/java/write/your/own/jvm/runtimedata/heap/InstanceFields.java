package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.runtimedata.LocalVariableTable;

public class InstanceFields extends LocalVariableTable {
    public InstanceFields(int slotSize) {
        super(slotSize);
    }

}
