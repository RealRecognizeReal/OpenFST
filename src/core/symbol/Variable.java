package core.symbol;

import core.symbol.base.Numeric;
import core.symbol.base.Symbol;

/**
 * Created by dongyi on 16. 11. 1.
 */
public class Variable extends Numeric {
    private String text;
    private Symbol under;

    public Variable(String text)
    {
        this.text = text;
    }

    public Variable(String text, Symbol under)
    {
        this(text);
        this.under = under;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            Variable v = (Variable)obj;
            return  text.equals(v.text);
        }
        return false;
    }

    @Override
    public String toLaTex() {
        if(under == null)
            return text;
        return String.format("%s_{%s}",text, under.toLaTex());
    }

    @Override
    public String toMathML() {
        return text;
    }
}
