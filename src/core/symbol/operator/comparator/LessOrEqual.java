package core.symbol.operator.comparator;

import core.symbol.base.BinaryOperator;
import core.symbol.base.NonArithmeticOperator;
import core.symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public class LessOrEqual extends NonArithmeticOperator {
    public LessOrEqual(Symbol operand1, Symbol operand2) {
        super(operand1, operand2);
    }

    @Override
    public boolean isCommutative() {
        return false;
    }


    @Override
    public String toLaTex() {
        return String.format("%s \\leq %s");
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public Class<? extends NonArithmeticOperator> getReversedOperator() {
        return GreaterOrEqual.class;
    }
}
