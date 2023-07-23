package write.your.own.jvm.instruction.extended;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.CodeReader;
import write.your.own.jvm.instruction.Instruction;
import write.your.own.jvm.instruction.load.*;
import write.your.own.jvm.instruction.math.IInc;
import write.your.own.jvm.instruction.store.*;
import write.your.own.jvm.runtimedata.StackFrame;

/**
 * <a href="https://docs.oracle.com/javase/specs/jvms/se7/html/jvms-6.html#jvms-6.5.wide">return new </a>
 */
public class Wide implements Instruction {

    private Instruction instruction;

    public Wide(CodeReader reader) {
        int opCode = reader.readUnsignedByte();
        instruction = delegateInstruction(reader, opCode);
    }

    private Instruction delegateInstruction(CodeReader reader, int opCode) {
        switch (opCode) {
            // iload
            case 0x15:
                return new ILoadWide(reader);
            // // lload
            case 0x16:
                return new LLoadWide(reader);
            // fload
            case 0x17:
                return new FLoadWide(reader);
            // dload
            case 0x18:
                return new DLoadWide(reader);
            // aload
            case 0x19:
                return new ALoadWide(reader);
            // istore
            case 0x36:
                return new IStoreWide(reader);
            // lstore
            case 0x37:
                return new LStoreWide(reader);
            // fstore
            case 0x38:
                return new FStoreWide(reader);
            // dstore
            case 0x39:
                return new DStoreWide(reader);
            // astore
            case 0x3a:
                return new AStoreWide(reader);
            // iinc
            case 0x84:
                return new IIncWide(reader);
            default:
                throw new NotImplementedException();
        }
    }

    @Override
    public int getOpCode() {
        return 0xC4;
    }

    @Override
    public void execute(StackFrame frame) {
        instruction.execute(frame);
    }

    @Override
    public String getReadableName() {
        return "wide " + instruction.getReadableName();
    }

    private static class ILoadWide extends ILoad {
        public ILoadWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class LLoadWide extends LLoad {
        public LLoadWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class FLoadWide extends FLoad {
        public FLoadWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class DLoadWide extends DLoad {
        public DLoadWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class ALoadWide extends ALoad {
        public ALoadWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class IStoreWide extends IStore {
        public IStoreWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class LStoreWide extends LStore {
        public LStoreWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class FStoreWide extends FStore {
        public FStoreWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class DStoreWide extends DStore {
        public DStoreWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class AStoreWide extends AStore {
        public AStoreWide(CodeReader reader) {
            super(reader);
        }

        @Override
        protected int readOperand(CodeReader reader) {
            return reader.readUnsignedShort();
        }
    }

    private static class IIncWide extends IInc {
        public IIncWide(CodeReader reader) {
            super(reader);
        }

        @Override
        public int readOperand1(CodeReader reader) {
            return reader.readUnsignedShort();
        }

        @Override
        public int readOperand2(CodeReader reader) {
            return reader.readShort();
        }
    }

}
