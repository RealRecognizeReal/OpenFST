package symbol;


import symbol.base.Numeric;

/**
 * Created by dongyi on 16. 11. 1.
 */

public class Constant extends Numeric {
    private Number value;
    public Constant(Number value)
    {
        this.value =value;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            Constant c = (Constant)obj;
            return value.equals(c.value);
        }


        return false;
    }


    @Override
    public String toLaTex() {
        return value.toString();
    }

    @Override
    public String toMathML() {
        return value.toString();
    }
}
