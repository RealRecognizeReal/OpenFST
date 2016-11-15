package parser;

import formula.Formula;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import sun.jvm.hotspot.debugger.cdbg.Sym;
import symbol.base.Symbol;
import symbol.operator.binary.MinusPlus;
import symbol.operator.binary.Multiply;
import symbol.operator.unary.Minus;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 *
 */
public class MathMLFormulaParser extends FormulaParserBase {

    private final Node documentNode ;
    private Formula formula = null;

    /**
     * MathML 포맷의 문자열을 통해 파싱을 진행하려는 경우 사용하는 생성자
     * @param mathmlString
     * @throws Exception
     */
    public MathMLFormulaParser(String mathmlString) throws Exception
    {
        Document xml = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(new ByteArrayInputStream(mathmlString.getBytes()));
        documentNode = xml.getDocumentElement();
    }

    /**
     *
     * @return 생성된 수식의 Formula 객체
     * @throws CanNotParseException Formula 생성(파싱)에 실패한 경우 반환되는 객체
     */
    @Override
    public Formula generateFormula() throws CanNotParseException {
        if(this.formula == null)
            this.formula =  new Formula( extractSymbol( documentNode ) );
        return this.formula;
    }

    /**
     * 하나의 루트 노드를 기준으로 symbol 트리를 형성하고 그 트리의 루트 노드를 반환하는 메소드
     * @param node
     * @return 생성된 symbol 트리의 루트 노드
     */
    private Symbol extractSymbol(Node node)
    {
        if(node.getChildNodes().getLength() == 0)
        {

        }


        if(node.getChildNodes().getLength() == 3)
        {
            if ( node.getNodeName().equals("mrow") )
            {
                return extractSymbol( node.getChildNodes().item(0) );
            }



        }



        return null;
    }

    /**
     * 여러개로 나열된 node의 리스트를 하나의 루트노드를 선정하여 subtree형태를 생성하고, 그 트리의 루트 노드를 반환함
     * @param nodeList
     * @return 해당 리스트에 속한 노드들을 통해 생성한 트리의 루트노드 심볼
     */
    private Symbol extractSymbol(ArrayList<Node> nodeList)
    {
        return null;
    }

    /**
     * Symbol extractSymbol(ArrayList<Node> nodeList) 와 같음
     * @param nodeList
     * @return Symbol extractSymbol(ArrayList<Node> nodeList) 와 같음
     */
    private Symbol extractSymbol(NodeList nodeList)
    {
        ArrayList<Node> list =new ArrayList<>();
        for(int i = 0 ; i < nodeList.getLength(); i++)
            list.add(nodeList.item(i));
        return extractSymbol(list);
    }



}
