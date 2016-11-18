package app;

import formula.Formula;
import parser.MathMLFormulaParser;
import parser.converter.MathConverter;
import symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 15..
 */
public class ParserTest {

    public static void main(String[] args)
    {
        String[] latexes = {
                "a+b",
                "\\frac {a} {b}",

        };

        int index = 0 ;
        for(String latex : latexes)
        {
            index++;
            String mathml = MathConverter.convertLaTex2MathML(latex);
            try {
                MathMLFormulaParser parser = new MathMLFormulaParser(mathml);
                Formula formula = parser.generateFormula();
                System.out.println( String.format("(%3d) [ %s ]->[ %s ]", index, latex,  formula.toString()));
            }catch (Exception ex)
            {
                System.out.println( String.format("(%3d) [ %s ]->[ %s ]", index, latex,  " !! Failed !! "));
            }

        }
    }

}
