package write.your.own.jvm.instruction;

import write.your.own.jvm.runtimedata.StackFrame;

/**
 * 每条指令都以一个单字节的操作码（opcode）开头，这就是字节码名称的由来。
 * 由于只使用一字节表示操作码，显而易见，Java虚拟机最多只能支持256条指令。
 * <p>
 * 和汇编语言类似，为了便于记忆，Java虚拟机规范给每个操作码都指定了一个助记符（mnemonic）。
 * 比如操作码是0x00这条指令，因为它什么也不做，所以它的助记符是nop（no operation）。
 * <p>
 * Java虚拟机使用的是变长指令，操作码后面可以跟零字节或多字节的操作数（operand）。
 * 如果把指令想象成函数的话，操作数就是它的参数。
 * 为了让编码后的字节码更加紧凑，很多操作码本身就隐含了操作数，比如把常数0推入操作数栈的指令是iconst_0。
 * <p>
 * 指令必须知道自己在操作什么类型的数据。这一点也直接反映在了操作码的助记符上。
 * 例如，iadd指令就是对int值进行加法操作；dstore指令把操作数栈顶的double值弹出，存储到局部变量表中.
 */
public interface Instruction {

    /**
     * instruction operate code value
     * zero means nop
     */
    int getOpCode();

    //    void readOperand();
    void execute(StackFrame frame);

    String getReadableName();
}
