package core.symbol;

import core.symbol.base.Symbol;
import core.symbol.operator.binary.*;
import core.symbol.operator.comparator.*;
import core.symbol.operator.unary.Factorial;
import core.symbol.operator.unary.Integral;
import core.symbol.operator.unary.Sigma;

import java.util.TreeMap;

/**
 * Created by waps12b on 2016. 12. 7..
 */
public enum EOperator {

    EQUAL    ("=",   1, 100, Equality.class),
    LESS     ("<",   2, 100, Less.class),
    LEQ      ("≤",   3, 100, LessOrEqual.class),
    GREATER  (">",   4, 100, Greater.class),
    GEQ      ("≥",   5, 100, GreaterOrEqual.class),
    NEQ      ("≠",   6, 100, Inequality.class),
    PLUS     ("+",   7, 100, Add.class),
    MINUS    ("-",   8, 100, Subtract.class),
    PM       ("±",   9, 100, PlusMinus.class),
    MP       ("∓",   1, 100, MinusPlus.class),
    MULTIPLY ("×",  11, 300, Multiply.class),
    DIVIDE   ("÷",  12, 300, Divide.class),
    FACTORIAL("!",  13, 400, Factorial.class),
    INTGRAL  ("∫",  14, 400, Integral.class),
    SIGMA    ("∑",  15, 400, Sigma.class),



    ;
    public final Class<? extends Symbol> OpClass;
    public final int       Priority;
    public final int       Rank;
    public final String    MathMLSymbol;
    EOperator(String mathMLSymbol, int priority, int rank, Class<? extends Symbol> opClass)
    {
        this.Rank = rank;
        this.OpClass = opClass;
        this.Priority = priority;
        this.MathMLSymbol = mathMLSymbol;
    }

    private static final TreeMap<String, EOperator> mapSymbol2Op = new TreeMap<>();
    private static final TreeMap<String, EOperator> mapClass2Op = new TreeMap<>();

    static {
        EOperator[] operators= EOperator.values();
        for(EOperator op : operators)
        {
            mapSymbol2Op.put( op.MathMLSymbol, op);
            mapClass2Op.put(  op.OpClass.getCanonicalName(), op);
        }
    }

    public static EOperator getBySymbol(String mathMLSymbol)
    {
        return mapSymbol2Op.get(mathMLSymbol);
    }
    public static EOperator getByClass(Class<? extends Symbol> cls)
    {
        return mapClass2Op.get(cls.getCanonicalName());
    }


}
