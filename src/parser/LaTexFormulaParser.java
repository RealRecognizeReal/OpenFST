package parser;

import formula.Formula;
import javafx.util.Pair;
import symbol.base.Symbol;

import javax.xml.crypto.dsig.keyinfo.KeyValue;
import java.util.TreeMap;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class LaTexFormulaParser extends FormulaParserBase {

    private final String latexString;
//    private final Pair<Integer, Integer>[] brackets;
    private static TreeMap<String, String> BRACKET_PAIR = new TreeMap<>();
    static {
        String[][] pairs = new String[][]{
                {"(", ")"},
                {"\\left(", "\\right)"},
                {"{", ")"},
                {"\\left{", "\\right}"},
                {"[", "]"},
                {"\\left[", "\\right]"},
        };

        BRACKET_PAIR.put("(", ")");
        BRACKET_PAIR.put(")", "(");

        BRACKET_PAIR.put("\\left(", "\\right)");
        BRACKET_PAIR.put(")", "(");

        BRACKET_PAIR.put("\\{", "\\}");
        BRACKET_PAIR.put("\\}", "\\{");

        BRACKET_PAIR.put("[", "[");
        BRACKET_PAIR.put("]", "]");



    }


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


    public static class SymbolChecker
    {
        private Pair<Class< ? extends Symbol >, String[] >[] table;
        private TreeMap<String, Class< ? extends  Symbol> > latexToClass;

        public SymbolChecker(Pair<Class< ? extends Symbol >, String[] >[] table)
        {
            this.table = table;
            for(Pair<Class< ? extends Symbol >, String[] > pair : table)
            {
                for(String key : pair.getValue())
                {
                    latexToClass.put(key, pair.getKey());
                }
            }
        }

    }
}
