package symbol.operator.unary;

import symbol.Variable;
import symbol.base.Symbol;
import symbol.base.UnaryOperator;

/**
 * Created by dongyi on 16. 11. 1.
 */
public class Sigma extends UnaryOperator {
    private Variable iterator;
    private Symbol begin;
    private Symbol end;

    public Sigma(Variable iterator, Symbol begin, Symbol end, Symbol innerFormula)
    {
        this(iterator, innerFormula);
        this.begin = begin;
        this.end = end;
    }

    public Sigma(Variable iterator, Symbol innerFormula)
    {
        super(innerFormula);
        this.iterator = iterator;
        this.begin = null;
        this.end = null;
    }

    public Symbol getInnerFormula()
    {
        return getOperand();
    }

    @Override
    public String toLaTex() {
        if(begin!=null && end!=null)
            return String.format("\\sum\\limits_{%s=%s}^%s %s", iterator.toLaTex(), begin.toLaTex(), end.toLaTex(), getOperand().toLaTex());
        return String.format("\\sum %s", getOperand().toLaTex());
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

    @Override
    public String[] getMaskArray() {
        return new String[]{"\\sum"};
    }
}
