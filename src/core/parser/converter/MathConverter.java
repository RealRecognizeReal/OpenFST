package core.parser.converter;

import java.io.*;

/**
 * Created by waps12b on 2016. 11. 9..
 */
public class MathConverter {

    public static final String REMOVE_REGEX = "\\\\displaystyle|\\\\scriptstyle|\\\\limits|\\\\textstyle|\\\\scriptstyle";
    public static final String REPLACE_FRAC_REGEX = "\\\\tfrac|\\\\dfrac";

    public static String convertLaTex2MathML(String latex)
    {
        try{
            //remove useless tag
            latex = latex.replaceAll(REMOVE_REGEX,"");

            //replace various xfrac tags with frac
            latex = latex.replaceAll(REPLACE_FRAC_REGEX,"\\frac");

            //run nodejs script that convert latex string to mathml string
            Process p = Runtime.getRuntime().exec(new String[]{"node", "src/core/parser/converter/converter.js", latex.trim()});
            BufferedReader reader = new BufferedReader( new InputStreamReader( p.getInputStream()) );
            String result = "";
            String line;
            while(  (line = reader.readLine())  != null )
            {
                result += line.trim();
            }
            reader.close();
            return result;
        }catch (Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }

}
