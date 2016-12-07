package core.util;

import core.symbol.EOperator;
import core.symbol.base.Operator;
import core.symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 19..
 */
public class FSTUtils {


    public static boolean isFloatString(String string)
    {
        try
        {
            Double.parseDouble(string);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }
    public static boolean isIntegerString(String string)
    {
        try
        {
            Integer.parseInt(string);
            return true;
        }catch (Exception ex)
        {
            return false;
        }
    }


    public static long getGCD(long a, long b)
    {
        return ( a % b == 0 ) ? b : getGCD(b, a % b);
    }

    public static String getOperandLaTex(Operator operator, Symbol operand)
    {
        if(operand == null)
            return "";

        String latex = operand.toLaTex();
        if(EOperator.getByClass( operand.getClass() ) == null)
            return latex;

        EOperator parentOperator = EOperator.getByClass( operator.getClass());
        EOperator childOperator = EOperator.getByClass( operand.getClass() );

        if(childOperator.Rank < parentOperator.Rank)
            latex = "{" + latex + "}";
        return latex;
    }
}
