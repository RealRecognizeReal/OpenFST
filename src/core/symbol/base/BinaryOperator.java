package core.symbol.base;

/**
 * Created by dongyi on 16. 11. 1.
 */
public abstract class BinaryOperator extends Operator{

    protected static int LEFT = 0;
    protected static int RIGHT = 1;

    protected BinaryOperator(Symbol operand1, Symbol operand2)
    {
        super(operand1, operand2);
    }

    public Symbol getLeftOperand()
    {
        return this.operands[LEFT];
    }
    public Symbol getRightOperand()
    {
        return this.operands[RIGHT];
    }
    public void setLeftOperand(Symbol operand) { this.operands[LEFT] = operand; }
    public void setRightOperand(Symbol operand) { this.operands[RIGHT] = operand; }

    public abstract boolean isCommutative();
    public abstract boolean isAssociative();
    public abstract boolean isDistributive();

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            BinaryOperator op = (BinaryOperator) obj;

            if( getLeftOperand().equals(op.getLeftOperand()) && getRightOperand().equals(op.getRightOperand())
                    || (isCommutative() && getLeftOperand().equals(op.getRightOperand()) && getRightOperand().equals(op.getLeftOperand())))
            {
                return true;
            }
        }
        return false;
    }
}
