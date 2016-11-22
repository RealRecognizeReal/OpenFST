package core.parser;

import core.Formula;
import core.parser.converter.MathConverter;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class LaTexFormulaParser extends FormulaParserBase {

    private final String latexString;
    private MathMLFormulaParser parser;
    public LaTexFormulaParser(String latexString)
    {

        this.latexString = latexString.trim();
        String mathml = MathConverter.convertLaTex2MathML(latexString);

    }

    @Override
    public Formula generateFormula() throws   MathMLFormulaParser.MathMLParsingException  {
        if(parser == null)
        {
            String mathmlString = MathConverter.convertLaTex2MathML(latexString);
            this.parser = new MathMLFormulaParser(mathmlString);
        }
        return parser.generateFormula();
    }

}
