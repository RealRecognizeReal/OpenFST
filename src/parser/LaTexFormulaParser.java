package parser;

import formula.Formula;
import symbol.base.Symbol;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class LaTexFormulaParser extends FormulaParserBase {

    private final String latexString;

    public LaTexFormulaParser(String latexString)
    {
        this.latexString = latexString.trim();
    }

    @Override
    public Formula generateFormula() throws CanNotParseException {
        Symbol root = extractSymbol(latexString);

        return null;
    }

    private Symbol extractSymbol(String str)
    {
        return null;
    }

}
