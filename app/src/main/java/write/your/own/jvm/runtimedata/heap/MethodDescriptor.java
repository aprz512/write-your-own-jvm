package write.your.own.jvm.runtimedata.heap;

import java.util.List;

public class MethodDescriptor {

    private final List<String> parameterTypes;
    private final String returnType;

    public MethodDescriptor(List<String> paramTypes, String returnType) {
        this.parameterTypes = paramTypes;
        this.returnType = returnType;
    }

    public List<String> getParameterTypes() {
        return parameterTypes;
    }

    public String getReturnType() {
        return returnType;
    }
}
