package net.loveruby.cflat.asm;

abstract public class AsmOperand implements OperandPattern {
    abstract public String toSource();

    public boolean isRegister() {
        return false;
    }

    public boolean isMemoryReference() {
        return false;
    }

    public IntegerLiteral integerLiteral() {
        return null;
    }

    abstract public void collectStatistics(AsmStatistics stats);

    // default implementation
    public boolean match(AsmOperand operand) {
        return equals(operand);
    }
}