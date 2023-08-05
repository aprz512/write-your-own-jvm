package write.your.own.jvm.runtimedata.heap;

import write.your.own.jvm.classfile.attribute.CodeAttribute;
import write.your.own.jvm.runtimedata.heap.constants.ClassRef;
import write.your.own.jvm.runtimedata.heap.constants.ConstantPool;

public class ExceptionTable {

    private final ExceptionHandler[] handlers ;


    public ExceptionTable(ExceptionHandler[] handlers) {
        this.handlers = handlers;
    }

    public ExceptionHandler[] getHandlers() {
        return handlers;
    }

    public ExceptionHandler findExceptionHandler(MyClass exceptionClass, int pc) {
        if (handlers == null) {
            return null;
        }
        for (ExceptionHandler handler : handlers) {
            if (handler.startPc <= pc && pc <= handler.endPc) {
                if (handler.catchType == null) {
                    return handler;
                }
                MyClass catchClass = handler.catchType.getResolvedClass();
                if (catchClass == exceptionClass || catchClass.isSuperClassOf(exceptionClass)) {
                    return handler;
                }
            }
        }
        return null;
    }

    public static ExceptionTable newExceptionTable(CodeAttribute.ExceptionTableEntry[] entries, ConstantPool constantPool) {
        ExceptionHandler[] handlers = new ExceptionHandler[entries.length];
        for (int i = 0; i < entries.length; i++) {
            handlers[i] = new ExceptionHandler(entries[i], constantPool);
        }
        return new ExceptionTable(handlers);
    }


    public static class ExceptionHandler {
        public int startPc;
        public int endPc;
        public int handlerPc;

        /**
         * 异常处理项的catchType有可能是0。
         * 我们知道0是无效的常量池索引，但是在这里0并非表示catch-none，而是表示catch-all，
         */
        ClassRef catchType;

        public ExceptionHandler(CodeAttribute.ExceptionTableEntry entry, ConstantPool constantPool) {
            this.startPc = entry.startPc;
            this.endPc = entry.endPc;
            this.handlerPc = entry.handlerPc;
            if (entry.catchType == 0) {
                catchType = null;
            } else  {
             this.catchType = (ClassRef) constantPool.getConstant(entry.catchType).value;
            }
        }
    }

}
