package parser;

import formula.Formula;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import symbol.base.Symbol;
import symbol.operator.binary.MinusPlus;
import symbol.operator.binary.Multiply;
import symbol.operator.unary.Minus;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;

/**
 * Created by waps12b on 2016. 11. 3..
 */
public class MathMLFormulaParser extends FormulaParserBase {



    private final Node documentNode ;


    public MathMLFormulaParser(String mathmlString) throws Exception
    {
        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(mathmlString.getBytes()));
//        xml.getDocumentElement().normalize();
//        XPath xpath = XPathFactory.newInstance().newXPath();
        documentNode = xml.getDocumentElement();
    }

    public MathMLFormulaParser(Node node)
    {
        documentNode = node;
    }


    @Override
    public Formula generateFormula() throws CanNotParseException {
        return null;

    }

    private Symbol extractSymbol(Node node)
    {
        if(node.getChildNodes().getLength() == 3)
        {
            if ( node.getNodeName().equals("mrow") )
            {
                return extractSymbol( node.getChildNodes().item(0) );
            }
        }


        return null;
    }


    private static String[][] table =
            {
                    { "&#x0003D;",  "&#x02260;", ">", "<", },
                    { },
            };



}
