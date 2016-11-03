package symbol.operator.binary;

import sun.jvm.hotspot.debugger.cdbg.Sym;
import symbol.base.BinaryOperator;
import symbol.base.Symbol;

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
        return String.format("%s+%s",
                getLeftOperand().toLaTex(),
                getRightOperand().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }

}
