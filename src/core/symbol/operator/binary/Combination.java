package core.symbol.operator.binary;

import core.symbol.base.BinaryOperator;
import core.symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public class Combination extends BinaryOperator {
    public Combination(Symbol n, Symbol r) {
        super(n, r);
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
        return String.format("\\binom{%s}{%s}", getLeftOperand(),getRightOperand());
    }

    @Override
    public String toMathML() {
        return null;
    }
}
