package core.util;

/**
 * Created by waps12b on 2016. 11. 19..
 */
public class FSTUtils {

    public static boolean isDigitString(String string)
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
}
