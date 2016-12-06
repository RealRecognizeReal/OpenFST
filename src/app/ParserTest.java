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
                "1+b",
                "0.5+1",
                "a+b+c",
                "b+a+c",
                "b+{a+c}",
                "{b+c}+{{a}}",
                "x=\\frac{-b\\pm\\sqrt{b^2-4ac}}{2a}",
                "\\frac{-b\\pm\\sqrt{b^2-4ac}}{2a}=x",
        };

        int index = 0 ;
        for(String latex : latexes)
        {
            index++;
            try {
                LaTexFormulaParser parser = new LaTexFormulaParser(latex);
                Formula formula = parser.generateFormula();
                System.out.println( String.format("(%3d) [ %20s ]->[ %20s ]", index, latex,  formula.toLatex()));
            }catch (Exception ex)
            {
                System.out.println( String.format("(%3d) [ %20s ]->[ %20s ]", index, latex,  " !! Failed !! "));
                ex.printStackTrace();
            }

        }
    }

}
