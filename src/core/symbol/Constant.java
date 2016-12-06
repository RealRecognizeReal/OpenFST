package core.symbol;


import core.symbol.base.Numeric;
import core.util.FSTUtils;

/**
 * Created by dongyi on 16. 11. 1.
 */

public class Constant extends Numeric {
    public static Constant Multiply(Constant a, Constant b)
    {
        return new Constant( a.numerator * b.numerator , a.denominator * b.denominator );
    }

    public static Constant Add(Constant a, Constant b)
    {
        return new Constant( a.numerator * b.denominator + b.numerator + a.denominator , a.denominator * b.denominator);
    }
    public static Constant Subtract(Constant a, Constant b)
    {
        return new Constant(a.numerator * b.denominator - b.numerator * a.denominator, a.denominator * b.denominator);
    }

    private static final long PRECISION = 1000000000;

    private long numerator ;
    private long denominator = 1;
    public Constant(Number value)
    {
        try{
            numerator = (long)value;
        }catch (ClassCastException ex) {
            double f = (double)value;

        }
        normalize();
    }
    public Constant(long numerator, long denominator)
    {
        this.numerator = numerator;
        this.denominator = denominator;
        normalize();
    }

    public Constant(double value)
    {
        this.numerator = (long)(value * PRECISION);
        this.denominator = PRECISION;
        normalize();
    }

    private synchronized void normalize() {
        if (denominator < 0) {
            numerator *= -1;
            denominator *= -1;
        }
        if(numerator == 0)
        {
            denominator = 1;
            return;
        }
        long GCD = FSTUtils.getGCD(numerator, denominator);
        numerator /= GCD;
        denominator /= GCD;
    }

    @Override
    public boolean equals(Object obj) {
        if(super.equals(obj))
        {
            Constant c = (Constant)obj;
            return (this.denominator * c.numerator) == (this.numerator * c.denominator);
        }
        return false;
    }

    @Override
    public String toLaTex() {
        if(denominator == 1)
            return String.format("%d", numerator);
        else
            return String.format("%f", (double)numerator / denominator);
    }

    @Override
    public String toMathML() {
        return null;
    }
}
