package core.symbol.operator.comparator;

import core.symbol.base.BinaryOperator;
import core.symbol.base.NonArithmeticOperator;
import core.symbol.base.Symbol;

/**
 * Created by Junghee on 2016-11-03.
 */
public class Less extends NonArithmeticOperator{

    public Less(Symbol operand1, Symbol operand2) {
        super(operand1, operand2);
    }

    @Override
    public String toLaTex() {
        return String.format("%s < %s",getLeftFormula().toLaTex(),getRightFormula().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public Class<? extends NonArithmeticOperator> getReversedOperator() {
        return Greater.class;
    }
}
