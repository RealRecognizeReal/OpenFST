package core.symbol.operator.unary;

import core.symbol.Variable;
import core.symbol.base.Symbol;
import core.symbol.base.UnaryOperator;

/**
 * Created by dongyi on 16. 11. 1.
 */
public class Sigma extends UnaryOperator {
    private Symbol begin;
    private Symbol end;

    public Sigma(Symbol begin, Symbol end, Symbol innerFormula)
    {
        this(innerFormula);
        this.begin = begin;
        this.end = end;
    }

    public Sigma(Symbol innerFormula)
    {
        super(innerFormula);
        this.begin = null;
        this.end = null;
    }

    public Symbol getInnerFormula()
    {
        return getOperand();
    }

    @Override
    public String toLaTex() {
        StringBuilder builder = new StringBuilder("\\sum");
        if(begin!=null)
            builder.append(String.format("_{%s}", begin.toLaTex()));
        if(end!=null)
            builder.append(String.format("^{%s}", end.toLaTex()));
        if(getInnerFormula()!=null)
            builder.append(String.format("{%s}", getInnerFormula().toLaTex()));
        return builder.toString();
    }

    @Override
    public String toMathML() {
        return null;
    }

    @Override
    public boolean equals(Object obj) {
        if (super.equals(obj)) {
            Sigma op = (Sigma) obj;
            return getInnerFormula().equals(op.getInnerFormula());
        }
        return false;
    }

}
