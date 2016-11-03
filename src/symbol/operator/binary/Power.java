package symbol.operator.binary;

import sun.jvm.hotspot.debugger.cdbg.Sym;
import symbol.base.BinaryOperator;
import symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 1..
 */
public class Power extends BinaryOperator {


    public Power(Symbol base, Symbol exponent)
    {
        super(base, exponent);

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

    public Symbol getBase()
    {
        return getLeftOperand();
    }

    public Symbol getExponent()
    {
        return getRightOperand();
    }

    @Override
    public String toLaTex() {
        return String.format("%s^%s",
                getBase().toLaTex(),
                getExponent().toLaTex());
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public String[] getMaskArray() {
        return new String[]{"^"};
    }

}
