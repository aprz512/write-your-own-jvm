package write.your.own.jvm.instruction;

import write.your.own.jvm.exception.NotImplementedException;
import write.your.own.jvm.instruction.comparison.*;
import write.your.own.jvm.instruction.constant.*;
import write.your.own.jvm.instruction.control.*;
import write.your.own.jvm.instruction.conversion.*;
import write.your.own.jvm.instruction.exception.AThrow;
import write.your.own.jvm.instruction.extended.GotoW;
import write.your.own.jvm.instruction.extended.IfNonNull;
import write.your.own.jvm.instruction.extended.IfNull;
import write.your.own.jvm.instruction.extended.Wide;
import write.your.own.jvm.instruction.load.*;
import write.your.own.jvm.instruction.math.*;
import write.your.own.jvm.instruction.reference.*;
import write.your.own.jvm.instruction.reserved.InvokeNative;
import write.your.own.jvm.instruction.stack.*;
import write.your.own.jvm.instruction.store.*;

public class InstructionFactory {

    public static Instruction create(int opcode, CodeReader reader) {
        switch (opcode) {
            case 0x0:
                return new Nop();
            case 0x01:
                return new AConstNull();
            case 0x02:
                return new IConstM1();
            case 0x03:
                return new IConst0();
            case 0x04:
                return new IConst1();
            case 0x05:
                return new IConst2();
            case 0x06:
                return new IConst3();
            case 0x07:
                return new IConst4();
            case 0x08:
                return new IConst5();
            case 0x09:
                return new LConst0();
            case 0x0a:
                return new LConst1();
            case 0x0b:
                return new FConst0();
            case 0x0c:
                return new FConst1();
            case 0x0d:
                return new FConst2();
            case 0x0e:
                return new DConst0();
            case 0x0f:
                return new DConst1();
            case 0x10:
                return new BiPush(reader);
            case 0x11:
                return new SiPush(reader);
            case 0x12:
                return new Ldc(reader);
            case 0x13:
                return new LdcW(reader);
            case 0x14:
                return new Ldc2W(reader);
            case 0x15:
                return new ILoad(reader);
            case 0x16:
                return new LLoad(reader);
            case 0x17:
                return new FLoad(reader);
            case 0x18:
                return new DLoad(reader);
            case 0x19:
                return new ALoad(reader);
            case 0x1a:
                return new ILoad0();
            case 0x1b:
                return new ILoad1();
            case 0x1c:
                return new ILoad2();
            case 0x1d:
                return new ILoad3();
            case 0x1e:
                return new LLoad0();
            case 0x1f:
                return new LLoad1();
            case 0x20:
                return new LLoad2();
            case 0x21:
                return new LLoad3();
            case 0x22:
                return new FLoad0();
            case 0x23:
                return new FLoad1();
            case 0x24:
                return new FLoad2();
            case 0x25:
                return new FLoad3();
            case 0x26:
                return new DLoad0();
            case 0x27:
                return new DLoad1();
            case 0x28:
                return new DLoad2();
            case 0x29:
                return new DLoad3();
            case 0x2a:
                return new ALoad0();
            case 0x2b:
                return new ALoad1();
            case 0x2c:
                return new ALoad2();
            case 0x2d:
                return new ALoad3();
            case 0x2e:
                return new IALoad();
            case 0x2f:
                return new LALoad();
            case 0x30:
                return new FALoad();
            case 0x31:
                return new DALoad();
            case 0x32:
                return new AALoad();
            case 0x33:
                return new BALoad();
            case 0x34:
                return new CALoad();
            case 0x35:
                return new SALoad();
            case 0x36:
                return new IStore(reader);
            case 0x37:
                return new LStore(reader);
            case 0x38:
                return new FStore(reader);
            case 0x39:
                return new DStore(reader);
            case 0x3a:
                return new AStore(reader);
            case 0x3b:
                return new IStore0();
            case 0x3c:
                return new IStore1();
            case 0x3d:
                return new IStore2();
            case 0x3e:
                return new IStore3();
            case 0x3f:
                return new LStore0();
            case 0x40:
                return new LStore1();
            case 0x41:
                return new LStore2();
            case 0x42:
                return new LStore3();
            case 0x43:
                return new FStore0();
            case 0x44:
                return new FStore1();
            case 0x45:
                return new FStore2();
            case 0x46:
                return new FStore3();
            case 0x47:
                return new DStore0();
            case 0x48:
                return new DStore1();
            case 0x49:
                return new DStore2();
            case 0x4a:
                return new DStore3();
            case 0x4b:
                return new AStore0();
            case 0x4c:
                return new AStore1();
            case 0x4d:
                return new AStore2();
            case 0x4e:
                return new AStore3();
            case 0x4f:
                return new IAStore();
            case 0x50:
                return new LAStore();
            case 0x51:
                return new FAStore();
            case 0x52:
                return new DAStore();
            case 0x53:
                return new AAStore();
            case 0x54:
                return new BAStore();
            case 0x55:
                return new CAStore();
            case 0x56:
                return new SAStore();
            case 0x57:
                return new Pop();
            case 0x58:
                return new Pop2();
            case 0x59:
                return new Dup();
            case 0x5a:
                return new DupX1();
            case 0x5b:
                return new DupX2();
            case 0x5c:
                return new Dup2();
            case 0x5d:
                return new Dup2X1();
            case 0x5e:
                return new Dup2X2();
            case 0x5f:
                return new Swap();
            case 0x60:
                return new IAdd();
            case 0x61:
                return new LAdd();
            case 0x62:
                return new FAdd();
            case 0x63:
                return new DAdd();
            case 0x64:
                return new ISub();
            case 0x65:
                return new LSub();
            case 0x66:
                return new FSub();
            case 0x67:
                return new DSub();
            case 0x68:
                return new IMul();
            case 0x69:
                return new LMul();
            case 0x6a:
                return new FMul();
            case 0x6b:
                return new DMul();
            case 0x6c:
                return new IDiv();
            case 0x6d:
                return new LDiv();
            case 0x6e:
                return new FDiv();
            case 0x6f:
                return new DDiv();
            case 0x70:
                return new IRem();
            case 0x71:
                return new LRem();
            case 0x72:
                return new FRem();
            case 0x73:
                return new DRem();
            case 0x74:
                return new INeg();
            case 0x75:
                return new LNeg();
            case 0x76:
                return new FNeg();
            case 0x77:
                return new DNeg();
            case 0x78:
                return new IShl();
            case 0x79:
                return new LShl();
            case 0x7a:
                return new IShr();
            case 0x7b:
                return new LShr();
            case 0x7c:
                return new IUShr();
            case 0x7d:
                return new LUShr();
            case 0x7e:
                return new IAnd();
            case 0x7f:
                return new LAnd();
            case 0x80:
                return new IOr();
            case 0x81:
                return new LOr();
            case 0x82:
                return new IXor();
            case 0x83:
                return new LXor();
            case 0x84:
                return new IInc(reader);
            case 0x85:
                return new I2L();
            case 0x86:
                return new I2F();
            case 0x87:
                return new I2D();
            case 0x88:
                return new L2I();
            case 0x89:
                return new L2F();
            case 0x8a:
                return new L2D();
            case 0x8b:
                return new F2I();
            case 0x8c:
                return new F2L();
            case 0x8d:
                return new F2D();
            case 0x8e:
                return new D2I();
            case 0x8f:
                return new D2L();
            case 0x90:
                return new D2F();
            case 0x91:
                return new I2B();
            case 0x92:
                return new I2C();
            case 0x93:
                return new I2S();
            case 0x94:
                return new LCmp();
            case 0x95:
                return new FCmpl();
            case 0x96:
                return new FCmpg();
            case 0x97:
                return new DCmpl();
            case 0x98:
                return new DCmpg();
            case 0x99:
                return new IfEq(reader);
            case 0x9a:
                return new IfNe(reader);
            case 0x9b:
                return new IfLt(reader);
            case 0x9c:
                return new IfGe(reader);
            case 0x9d:
                return new IfGt(reader);
            case 0x9e:
                return new IfLe(reader);
            case 0x9f:
                return new IfICmpEq(reader);
            case 0xa0:
                return new IfICmpNe(reader);
            case 0xa1:
                return new IfICmpLt(reader);
            case 0xa2:
                return new IfICmpGe(reader);
            case 0xa3:
                return new IfICmpGt(reader);
            case 0xa4:
                return new IfICmpLe(reader);
            case 0xa5:
                return new IfACmpEq(reader);
            case 0xa6:
                return new IfACmpNe(reader);
            case 0xa7:
                return new Goto(reader);
            // case 0xa8:
            // 	return &JSR{}
            // case 0xa9:
            // 	return &RET{}
            case 0xaa:
                return new TableSwitch(reader);
            case 0xab:
                return new LookupSwitch(reader);
            case 0xac:
                return new IReturn();
            case 0xad:
                return new LReturn();
            case 0xae:
                return new FReturn();
            case 0xaf:
                return new DReturn();
            case 0xb0:
                return new AReturn();
            case 0xb1:
                return new Return();
            case 0xb2:
                return new GetStatic(reader);
            case 0xb3:
                return new PutStatic(reader);
            case 0xb4:
                return new GetField(reader);
            case 0xb5:
                return new PutField(reader);
            case 0xb6:
                return new InvokeVirtual(reader);
            case 0xb7:
                return new InvokeSpecial(reader);
            case 0xb8:
                return new InvokeStatic(reader);
            case 0xb9:
                return new InvokeInterface(reader);
            case 0xba:
                return new InvokeDynamic(reader);
            case 0xbb:
                return new New(reader);
            case 0xbc:
                return new NewArray(reader);
            case 0xbd:
                return new ANewArray(reader);
            case 0xbe:
                return new ArrayLength();
            case 0xbf:
                return new AThrow();
            case 0xc0:
                return new CheckCast(reader);
            case 0xc1:
                return new InstanceOf(reader);
            // case 0xc2:
            // 	return monitorenter
            // case 0xc3:
            // 	return monitorexit
            case 0xc4:
                return new Wide(reader);
            case 0xc5:
                return new MultiANewArray(reader);
            case 0xc6:
                return new IfNull(reader);
            case 0xc7:
                return new IfNonNull(reader);
            case 0xc8:
                return new GotoW(reader);
            // case 0xc9:
            // 	return &JSR_W{}
            // case 0xca: breakpoint
            case 0xfe:
                return new InvokeNative();
            // case 0xff: impdep2
            default:
                throw new NotImplementedException("opcode = 0x" + Integer.toHexString(opcode));
        }
    }

}
