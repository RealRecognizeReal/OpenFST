package symbol.operator.comparator;

import symbol.base.NonArithmeticOperator;
import symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class Equality extends NonArithmeticOperator {

    public Equality(Symbol formula1, Symbol formula2) {
        super(formula1, formula2);
    }

    @Override
    public boolean isCommutative() {
        return true;
    }

    @Override
    public String toLaTex() {
        return null;
    }

    @Override
    public String toMathML() {
        return null;
    }
}
