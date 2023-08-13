package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.runtimedata.LocalVariableTable;

public class StaticFields extends LocalVariableTable {
    public StaticFields(int slotSize) {
        super(slotSize);
    }
}
