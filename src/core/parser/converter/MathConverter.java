package core.parser.converter;

import java.io.*;

/**
 * Created by waps12b on 2016. 11. 9..
 */
public class MathConverter {

    public static String convertLaTex2MathML(String latex)
    {
        try{
            //remove useless styletag
            latex = latex.replaceAll("\\\\displaystyle","");
            latex = latex.replaceAll("\\\\textstyle","");
            latex = latex.replaceAll("\\\\scriptstyle","");
            latex = latex.replaceAll("\\\\tfrac","\\frac");
            latex = latex.replaceAll("\\\\dfrac","\\frac");


            //run python script that convert latex string to mathml string
            Process p = Runtime.getRuntime().exec(new String[]{"python", "src/core.parser/converter/script.py", latex});
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
