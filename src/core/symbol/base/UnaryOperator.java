package core.symbol.base;

/**
 * Created by dongyi on 16. 11. 1.
 */
public abstract class UnaryOperator extends Operator {

    protected UnaryOperator(Symbol operand)
    {
        super(operand);
    }

    public Symbol getOperand()
    {
        return this.operands[0] ;
    }
    public void setOperand(Symbol operand){ this.operands[0] = operand; }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            UnaryOperator op = (UnaryOperator)obj;
            return this.getOperand().equals(op.getOperand() );
        }
        return false;
    }
}
