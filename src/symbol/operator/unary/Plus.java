package symbol.operator.unary;

import symbol.base.Symbol;
import symbol.base.UnaryOperator;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class Plus extends UnaryOperator {
    protected Plus(Symbol operand) {
        super(operand);
    }

    @Override
    public String toLaTex() {
        return null;
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public String[] getMaskArray() {
        return new String[0];
    }
}
