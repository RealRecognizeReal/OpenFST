package core.parser;


import core.Formula;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public abstract class FormulaParserBase {


    public abstract Formula generateFormula() throws  MathMLFormulaParser.MathMLParsingException;



}
