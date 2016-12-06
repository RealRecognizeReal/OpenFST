package core.symbol.operator.binary;

import core.symbol.base.BinaryOperator;
import core.symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public class PlusMinus extends BinaryOperator {
    public PlusMinus(Symbol operand1, Symbol operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public boolean isAssociative() {
        return false;
    }

    @Override
    public boolean isDistributive() {
        return false;
    }

    @Override
    public String toLaTex() {
        return String.format("{%s}\\pm{%s}", getLeftOperand().toLaTex(), getRightOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }
}
