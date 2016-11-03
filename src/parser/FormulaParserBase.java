package parser;


import formula.Formula;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public abstract class FormulaParserBase {


    public abstract Formula generateFormula() throws CanNotParseException;


    public static class CanNotParseException extends Exception{};

}
