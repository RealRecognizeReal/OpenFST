package core.symbol.operator.unary;

import core.symbol.Variable;
import core.symbol.base.Symbol;
import core.symbol.base.UnaryOperator;

/**
 * Created by waps12b on 2016. 11. 1..
 */
public class Integral extends UnaryOperator {
    private Symbol lower;
    private Symbol upper;
    private Variable variable;

    public Integral(Symbol innerFormula)
    {
        super(innerFormula);
        this.lower = null;
        this.upper = null;
        this.variable = null;
    }

    public Integral(Symbol innerFormula, Variable variable)
    {
        this(innerFormula);
        this.variable = variable;
    }

    public Integral(Symbol innerFormula, Symbol lower, Symbol uppper, Variable variable)
    {
        this(innerFormula, variable);
        this.upper = uppper;
        this.lower = lower;
    }

    public Symbol getInnerFormula()
    {
        return getOperand();
    }

    @Override
    public String toLaTex() {
        StringBuilder latex = new StringBuilder("\\int");
        if(lower != null)
            latex.append(String.format("_{%s}", lower.toLaTex()));
        if(upper != null)
            latex.append(String.format("^{$s}", upper.toLaTex()));
        latex.append(" " + getInnerFormula().toLaTex());
        if(variable != null)
            latex.append(String.format(" d%s", variable.toLaTex()));
        return latex.toString();
    }

    @Override
    public String toMathML() {
        return null;
    }
}
