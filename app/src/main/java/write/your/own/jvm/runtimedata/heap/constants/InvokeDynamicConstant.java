package write.your.own.jvm.runtimedata.heap.constants;

public class InvokeDynamicConstant {

    private final String[] nameAndType;
    private final int bootstrapMethodAttrIndex;

    public InvokeDynamicConstant(String[] nameAndType, int bootstrapMethodAttrIndex) {
        this.nameAndType = nameAndType;
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public String[] getNameAndType() {
        return nameAndType;
    }

}
