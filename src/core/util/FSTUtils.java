package core.util;

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
}
