package write.your.own.jvm.classfile.attribute.notused.stackmapframe.verificationtypeinfo;

import write.your.own.jvm.classfile.ClassReader;
import write.your.own.jvm.exception.NotImplementedException;

public class VerificationTypeInfoFactory {

    public static VerificationTypeInfo newInstance(int tag, ClassReader reader) {
        switch (tag) {
            case 0:
                return new TopVariableInfo(tag, reader);
            case 1:
                return new IntegerVariableInfo(tag, reader);
            case 2:
                return new FloatVariableInfo(tag, reader);
            case 5:
                return new NullVariableInfo(tag, reader);
            case 6:
                return new UninitializedThisVariableInfo(tag, reader);
            case 7:
                return new ObjectVariableInfo(tag, reader);
            case 8:
                return new UninitializedVariableInfo(tag, reader);
            case 3:
                return new DoubleVariableInfo(tag, reader);
            case 4:
                return new LongVariableInfo(tag, reader);
            default:
                throw new NotImplementedException();
        }
    }

}
