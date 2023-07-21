package write.your.own.jvm.runtimedata;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StackFrameTest {

    @Test
    public void testLocalVars() {
        ObjRef ref = new ObjRef();
        LocalVars vars = new LocalVars(10);
        vars.setInt(0, 100);
        vars.setInt(1, -100);
        vars.setLong(2, 2997924580L);
        vars.setLong(4, -2997924580L);
        vars.setFloat(6, 3.1415926f);
        vars.setDouble(7, 2.71828182845d);
        vars.setRef(9, ref);

        assertEquals(vars.getInt(0), 100);
        assertEquals(vars.getInt(1), -100);
        assertEquals(vars.getLong(2), 2997924580L);
        assertEquals(vars.getLong(4), -2997924580L);
        assertEquals(vars.getFloat(6), 3.1415926f);
        assertEquals(vars.getDouble(7), 2.71828182845d);
        assertEquals(vars.getRef(9), ref);
    }

    @Test
    public void testOperandStack() {
        OperandStack stack = new OperandStack(10);
        ObjRef ref = new ObjRef();
        stack.pushInt(100);
        stack.pushInt(-100);
        stack.pushLong(2997924580L);
        stack.pushLong(-2997924580L);
        stack.pushFloat(3.1415926f);
        stack.pushDouble(2.71828182845d);
        stack.pushRef(ref);

        assertEquals(stack.popRef(), ref);
        assertEquals(stack.popDouble(), 2.71828182845d);
        assertEquals(stack.popFloat(), 3.1415926f);
        assertEquals(stack.popLong(), -2997924580L);
        assertEquals(stack.popLong(), 2997924580L);
        assertEquals(stack.popInt(), -100);
        assertEquals(stack.popInt(), 100);
    }

}
