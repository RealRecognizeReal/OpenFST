package app;

import core.Formula;
import core.parser.LaTexFormulaParser;

/**
 * Created by waps12b on 2016. 11. 15..
 */
public class ParserTest {

    public static void main(String[] args)
    {
        String[] latexes = {
                "(a)+(b)",
                "a*b",
                "a-b",
                "a+b+c",
        };

        int index = 0 ;
        for(String latex : latexes)
        {
            index++;
            try {
                LaTexFormulaParser parser = new LaTexFormulaParser(latex);
                Formula formula = parser.generateFormula();
                System.out.println( String.format("(%3d) [ %s ]->[ %s ]", index, latex,  formula.toLatex()));
            }catch (Exception ex)
            {
                System.out.println( String.format("(%3d) [ %s ]->[ %s ]", index, latex,  " !! Failed !! "));
            }

        }
    }

}
