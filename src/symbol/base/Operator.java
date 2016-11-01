package symbol.base;

/**
 * Created by dongyi on 16. 11. 1.
 */
public abstract class Operator extends Symbol {
    protected Symbol[] operands;
    protected Operator(Symbol... operands)
    {
        this.operands = operands;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            Operator op = (Operator)obj;
            if(this.operands.length != op.operands.length)
                return false;

            return true;
        }
        return false;
    }
}
