package core.symbol.operator.unary;

import core.symbol.base.Symbol;
import core.symbol.base.UnaryOperator;

/**
 * Created by waps12b on 2016. 11. 23..
 */
public class Factorial extends UnaryOperator {
    public Factorial(Symbol operand) {
        super(operand);
    }

    @Override
    public String toLaTex() {
        return String.format( String.format("{%s!}", getOperand().toLaTex()) );
    }

    @Override
    public String toMathML() {
        return null;
    }
}
