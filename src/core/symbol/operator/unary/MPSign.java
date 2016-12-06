package core.symbol.operator.unary;

import core.symbol.base.Symbol;
import core.symbol.base.UnaryOperator;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public class MPSign extends UnaryOperator {
    public MPSign(Symbol operand) {
        super(operand);
    }

    @Override
    public String toLaTex() {
        return String.format("\\mp{%s}", getOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }
}
