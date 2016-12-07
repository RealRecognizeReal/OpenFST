package core.symbol.operator.binary;

import core.symbol.EOperator;
import core.symbol.base.BinaryOperator;
import core.symbol.base.Symbol;
import core.util.FSTUtils;

/**
 * Created by waps12b on 2016. 11. 1..
 */
public class Add extends BinaryOperator {

    public Add(Symbol left, Symbol right)
    {
        super(left,right);
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

    @Override
    public String toLaTex() {
        return String.format("{%s}+{%s}",
                getLeftOperand().toLaTex(),
                getRightOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }

}
