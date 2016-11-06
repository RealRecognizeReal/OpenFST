package symbol.operator.binary;

import symbol.base.BinaryOperator;
import symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class MinusPlus extends BinaryOperator {
    //private protectted public
    public MinusPlus(Symbol operand1, Symbol operand2) {
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
        return String.format("%s\\mp%s", getLeftOperand().toLaTex(), getRightOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }
}
