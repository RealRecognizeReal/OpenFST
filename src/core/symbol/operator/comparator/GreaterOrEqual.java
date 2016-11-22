package core.symbol.operator.comparator;

import core.symbol.base.NonArithmeticOperator;
import core.symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 6..
 */
public class GreaterOrEqual extends NonArithmeticOperator {
    public GreaterOrEqual(Symbol formula1, Symbol formula2) {
        super(formula1, formula2);
    }

    @Override
    public boolean isCommutative() {
        return false;
    }

    @Override
    public String toLaTex() {
        return String.format("%s \\geq %s",
                getLeftFormula().toLaTex(),
                getRightFormula().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }
}
