package parser;

import formula.Formula;
import javafx.util.Pair;
import parser.converter.MathConverter;
import symbol.base.Symbol;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.TreeMap;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class LaTexFormulaParser extends FormulaParserBase {

    private final String latexString;
    private final MathMLFormulaParser parser;

    public LaTexFormulaParser(String latexString) throws Exception
    {

        this.latexString = latexString.trim();
        String mathml = MathConverter.convertLaTex2MathML(latexString);

        this.parser = new MathMLFormulaParser(mathml);
    }

    @Override
    public Formula generateFormula() throws CanNotParseException {
        return parser.generateFormula();
    }
}
