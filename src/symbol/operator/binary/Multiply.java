package symbol.operator.binary;

import symbol.base.BinaryOperator;
import symbol.base.Symbol;

/**
 * Created by Junghee on 2016-11-03.
 */


public class Multiply extends BinaryOperator{

    public Multiply(Symbol operand1, Symbol operand2) {
        super(operand1, operand2);
    }

    @Override
    public String toLaTex() {
        return String.format("%s*%s",getLeftOperand().toLaTex(),getRightOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public boolean isAssociative() {
        return true;
    }

    @Override
    public boolean isDistributive() {
        return true;
    }


}