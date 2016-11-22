package core.symbol.base;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public abstract class NonArithmeticOperator extends Operator {

    protected NonArithmeticOperator(Symbol formula1, Symbol formula2)
    {
        super(formula1, formula2);
    }

    public abstract boolean isCommutative();

    public Symbol getLeftFormula() { return this.operands[0]; }
    public Symbol getRightFormula() { return this.operands[1]; }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            NonArithmeticOperator op = (NonArithmeticOperator) obj;
            if(this.getLeftFormula().equals( op.getLeftFormula()) && this.getRightFormula().equals(op.getRightFormula()))
                return true;
        }
        return false;
    }
}