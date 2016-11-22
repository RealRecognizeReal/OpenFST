package core.parser;

import core.Formula;
import org.w3c.dom.*;
import core.symbol.Constant;
import core.symbol.Variable;
import core.symbol.base.Symbol;
import core.symbol.operator.binary.Add;
import core.util.FSTUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;

/**
 *
 */
public class MathMLFormulaParser extends FormulaParserBase {

    private final String mathmlString;
    private Document xml = null;
    private Formula formula = null;


    /**
     * MathML 포맷의 문자열을 통해 파싱을 진행하려는 경우 사용하는 생성자
     * @param mathmlString
     * @throws Exception
     */
    public MathMLFormulaParser(String mathmlString)
    {
        this.mathmlString = mathmlString;
    }

    /**
     *
     * @return 생성된 수식의 Formula 객체
     * @throws MathMLParsingException Formula 생성(파싱)에 실패한 경우 반환되는 객체
     */
    @Override
    public Formula generateFormula() throws MathMLFormulaParser.MathMLParsingException {
        if(this.formula == null)
        {
            Node documentNode ;
            try{
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                dbFactory.setIgnoringComments(true);
                dbFactory.setIgnoringElementContentWhitespace(true);

                xml = dbFactory.newDocumentBuilder().parse(new ByteArrayInputStream(mathmlString.getBytes()));
                documentNode = xml.getDocumentElement();
            }catch (Exception ex)
            {
                throw new MathMLParsingException();
            }

            this.formula =  new Formula( extractSymbol( documentNode ) );
        }

        return this.formula;
    }


    private void normalize(Node node)
    {
        if(node == null)
            return;

        if( node instanceof Text )
            return;

        String nodeName = node.getNodeName();
        String nodeText = node.getTextContent();


        if(!node.hasChildNodes())
            return;


        int size = node.getChildNodes().getLength();
        for(int i = 0 ; i < size; i++)
        {
            Node child = node.getChildNodes().item(i);
            node.removeChild(child);

            String childName = child.getNodeName();
            String childText = child.getTextContent();
            if(childText == null)   childText = "";

            if(childName.equals("mfrac"))
            {
                Element div = xml.createElement("mrow");

                div.appendChild(child.getFirstChild());

                Element op = xml.createElement("mo");
                op.setTextContent("/");

                div.appendChild(op);
                div.appendChild(child.getLastChild());

                node.replaceChild(div, child);
                continue;
            }else if(childText.equals("!"))
            {
                Element factorial = xml.createElement("mo");
                factorial.setTextContent("!");
                node.replaceChild(factorial, child);
                continue;
            }
        }

        for(int i = 0 ; i < size; i++)
        {
            normalize(node.getChildNodes().item(i));
        }
    }

    /**
     * 하나의 루트 노드를 기준으로 core.symbol 트리를 형성하고 그 트리의 루트 노드를 반환하는 메소드
     * @param node
     * @return 생성된 core.symbol 트리의 루트 노드
     */
    private Symbol extractSymbol(Node node)
    {
        if( node == null )
            return null;

        int nChild = node.getChildNodes().getLength();
        String nodeName = node.getNodeName();

        //mrow, math항목은 단순히 하위 차일드를 가진 의미없는 엘리먼트
        if( nodeName.equals("mrow") || nodeName.equals("math") )
        {
            if(nChild > 0 )
                return extractSymbol(node.getChildNodes());
            return null;
        }

        //leaf 노드
        if(node.getNodeName().equals("mi") )
        {
            String nodeText = node.getTextContent().trim();
            if(nodeText.length() <= 0)
                return null;

            if(FSTUtils.isDigitString( node.getTextContent() )) {
                return new Constant( Double.parseDouble(node.getTextContent()) );
            }else {
                return new Variable(node.getTextContent());
            }
        }

        //문서의 루트 노드
        if(node.getNodeName().equals("math"))
            return extractSymbol(node.getChildNodes());

        //<mrow>의 경우
        if(node.getNodeName().equals("mrow"))
        {
            return extractSymbol(node.getChildNodes());
//
//            if(node.getChildNodes().getLength()==1)
//                return extractSymbol(node.getChildNodes().item(0));
//            if(node.getChildNodes().getLength() == 3)
//                return extractSymbol( node.getChildNodes().item(1) );
//            return extractSymbol(node.getChildNodes());
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
        if(nodeList.size() == 1)
            return extractSymbol(nodeList.get(0));

        for(int i = 0 ;  i < nodeList.size(); i++)
        {
            if( isAdd( nodeList.get(i) ) )
            {
                ArrayList<Node> left, right;
                left = new ArrayList<>();
                right = new ArrayList<>();
                for(int j = 0 ; j < i ; j++)
                    left.add(nodeList.get(j));
                for(int j = i +1 ; j < nodeList.size();j ++)
                    right.add(nodeList.get(j));
                return new Add( extractSymbol(left), extractSymbol(right) );
            }
        }
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


    private boolean isAdd(Node node)
    {
        String text=  node.getTextContent();
        return node.getNodeName().equals("mo") && text.equals("+");
    }

    public static class MathMLParsingException extends Exception
    {}

}
