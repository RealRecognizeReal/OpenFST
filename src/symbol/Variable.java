package symbol;

import symbol.base.Numeric;

/**
 * Created by dongyi on 16. 11. 1.
 */
public class Variable extends Numeric {
    private String text;

    public Variable(String text)
    {
        this.text = text;
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
        return text;
    }

    @Override
    public String toMathML() {
        return text;
    }
}
