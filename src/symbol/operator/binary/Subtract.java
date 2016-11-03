package symbol.operator.binary;

import symbol.base.BinaryOperator;
import symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 1..
 */
public class Subtract extends BinaryOperator {

    protected Subtract(Symbol operand1, Symbol operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public boolean isDistributive() {
        return true;
    }

    @Override
    public String toLaTex() {
        return String.format("%s-%s",
                getLeftOperand().toLaTex(),
                getRightOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public String[] getMaskArray() {
        return new String[]{"-"};
    }
}
