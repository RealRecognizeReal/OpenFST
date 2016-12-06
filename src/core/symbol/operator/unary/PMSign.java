package core.symbol.operator.unary;

import core.symbol.base.Symbol;
import core.symbol.base.UnaryOperator;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public class PMSign extends UnaryOperator {
    public PMSign(Symbol operand) {
        super(operand);
    }

    @Override
    public String toLaTex() {
        return String.format("\\pm{%s}", getOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }
}
