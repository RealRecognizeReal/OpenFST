package core.parser;

import core.Formula;
import core.symbol.operator.binary.Power;
import core.symbol.operator.unary.Factorial;
import org.w3c.dom.*;
import core.symbol.Constant;
import core.symbol.Variable;
import core.symbol.base.Symbol;
import core.symbol.operator.binary.Add;
import core.util.FSTUtils;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.Stack;
import java.util.TreeMap;

public class MathMLFormulaParser extends FormulaParserBase {
    private final String mathmlString;
    private Document xml = null;
    private Formula formula = null;

    private static final String SYMBOL_INTGRAL = "∫";
    private static final String SYMBOL_SIGMA   = "∑";
    private static final String SYMBOL_MULTIPLY= "×";
    private static final String SYMBOL_PLUS    = "+";
    private static final String SYMBOL_MINUS   = "-";
    private static final String SYMBOL_DIVIDE  = "÷";
    private static final String SYMBOL_EQUAL   = "=";
    private static final String SYMBOL_FACTORIAL = "!";

    private static final Map<String, Integer> mapSym2Priority = new TreeMap<>();
    static {
        int prior = 0;
        mapSym2Priority.put(SYMBOL_EQUAL, ++prior);


    }



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
                documentNode = xml.getDocumentElement().getChildNodes().item(0).getChildNodes().item(0);
            }catch (Exception ex)
            {
                throw new MathMLParsingException();
            }

//            reverseRelativeOp(documentNode);
            convertBrackets2mrow(documentNode);
            insertMultiplier(documentNode);
            convertFactorial(documentNode);

            this.formula =  new Formula( extractSymbol( documentNode ) );
        }

        return this.formula;
    }


    private void convertFactorial(Node parent) {
        if (parent == null || parent instanceof Text)
            return;

        int size = parent.getChildNodes().getLength();
        if (size < 2)
            return;

        Node[] childs = new Node[size];
        boolean exist = false;
        for (int i = 0; i < size; i++)
        {
            childs[i] = parent.getChildNodes().item(i);
            String text = childs[i].getTextContent();
            if(childs[i] instanceof Text) continue;
            if(text != null && text.equals(SYMBOL_FACTORIAL))
            {
                exist = true;
            }
        }

        if(exist)
        {
            for(int i = 0 ; i<size; i++)
                parent.removeChild(childs[i]);

            ArrayList<Node> nodes = new ArrayList<>();
            for(int i = 0; i < size; i++){
                Node node = childs[i];
                String text = node.getTextContent();
                if(text == null) continue;
                if(text.equals(SYMBOL_FACTORIAL))
                {
                    Node operand = nodes.get(nodes.size()-1);
                    nodes.remove(nodes.size()-1);

                    Node factorial = xml.createElement("mfactorial");
                    factorial.appendChild(operand);

                    node = factorial;
                }
                nodes.add(node);
            }

            for(Node node : nodes)
                parent.appendChild(node);
        }

        for(int i = 0 ; i < parent.getChildNodes().getLength(); i++)
            convertBrackets2mrow( parent.getChildNodes().item(i) );
    }

    private void convertSigma(Node parent)
    {
        if(parent == null || parent instanceof Text)
            return;

        int size = parent.getChildNodes().getLength();
        if(size == 0)   return;;

    }

    private void insertMultiplier(Node parent)
    {
        if(parent == null || parent instanceof Text)
            return;

        int size = parent.getChildNodes().getLength();
        if(parent.getNodeName().equals("mrow") && size > 1)
        {
            Node[] childs = new Node[size];
            for(int i =0 ; i < size; i++)
                childs[i] = parent.getChildNodes().item(i);

            for(int i = 0; i < size;i++)
                parent.removeChild(childs[i]);


            ArrayList<Node> nodes = new ArrayList<>();
            Node last = null;
            for(int i =0 ; i <size; i++){
                Node node = childs[i];
                if(!node.getNodeName().equals("mo") && last != null && !last.getNodeName().equals("mo"))
                {
                    Node mul = xml.createElement("mo");
                    mul.setTextContent(SYMBOL_MULTIPLY);
                    nodes.add(mul);
                }
                nodes.add(node);
                last = node;
            }

            for(Node node: nodes)
                parent.appendChild(node);
        }

        for(int i = 0 ; i < parent.getChildNodes().getLength(); i++)
            insertMultiplier(parent.getChildNodes().item(i));
    }
//    private void reverseRelativeOp(Node root)
//    {
//        if(root == null || root instanceof Text)
//            return;
//
//        int size = root.getChildNodes().getLength();
//        if(size < 2)    return;
//
//        Node[] childs = new Node[size];
//        for(int i = 0 ; i < size; i++)
//            childs[i] = root.getChildNodes().item(i);
//
//        boolean exist = false;
//        boolean cant = false;
//        for(int i = 0 ; i < size; i++)
//        {
//            Node node = childs[i];
//            if(node instanceof Text || node.getTextContent() == null)
//                continue;
//
//            if(!node.getNodeName().equals("mo"))
//                continue;
//
//            String text = node.getTextContent();
//            switch (text)
//            {
//                case "≤":
//                case "<":
//                    exist = true;
//                    break;
//                case ">":
//                case "≥":
//                    cant = true;
//                    break;
//            }
//            if(exist||cant) break;
//        }
//
//        if(!exist || cant)
//            return;
//
//        Stack<Object> stack = new Stack<>();
//        ArrayList<Node> last = null;
//        for(int i = 0 ; i  < size; i++)
//        {
//            Node node = childs[i];
//            String name = node.getNodeName();
//            boolean pushed = false;
//            if(node.getNodeName().equals("mo"))
//            {
//                switch(name)
//                {
//                    case "=":
//                    case "<":
//                    case ">":
//                    case "≤":
//                    case "≥":
//                        stack.push(node);
//                        pushed = true;
//                        last = null;
//                        break;
//                }
//            }
//            if(pushed)  continue;
//            if(last == null) {
//                last = new ArrayList<>();
//                stack.push(last);
//            }
//
//            last.add(node);
//        }
//
//        for(int i = 0 ; i < size; i++)
//            root.removeChild(childs[i]);
//
//        while(stack.empty())
//        {
//            Object obj = stack.pop();
//            if(obj instanceof Node)
//            {
//                Node node = (Node)obj;
//                String text = node.getTextContent();
//                switch (text)
//                {
//                    case "<":
//                        node.setTextContent(">");
//                        break;
//                    case "≤":
//                        node.setTextContent("≥");
//                        break;
//                }
//                root.appendChild(node);
//            }else
//            {
//                ArrayList<Node> list = (ArrayList<Node>)obj;
//                for(Node node : list)
//                    root.appendChild(node);
//            }
//        }
//    }
//





    private void convertBrackets2mrow(Node parent)
    {
        if(parent == null || parent instanceof  Text )
            return;

        int size =  parent.getChildNodes().getLength() ;
        if(size < 2)   return;;

        Node[] childs = new Node[size];
        for(int i = 0 ; i < size; i++)
            childs[i] = parent.getChildNodes().item(i);

        for(int i = 0 ; i < size; i++)
            parent.removeChild(childs[i]);

        ArrayList<Node> temp = new ArrayList<>();
        Stack<Integer> s = new Stack<>();
        for(int i = 0 ; i < size; i++)
        {
            Node node = childs[i];

            String text = childs[i].getTextContent();
            boolean inserted = false;
            if(text != null)
            {
                switch (text)
                {
                    case "(":
                    case "{":
                    case "[":
                        s.push(i);
                        inserted = true;
                        break;

                    case ")":
                    case "}":
                    case "]":
                        int from = s.pop() + 1;
                        int to = i - 1;
                        if(s.size() == 0)
                        {
                            Node row = xml.createElement("mrow");
                            for(int idx = from; idx <= to; idx++)
                                row.appendChild(childs[idx]);
                            temp.add(row);
                        }
                        inserted = true;
                        break;
                }
            }
            if(!inserted && s.size() == 0)
                temp.add(node);
        }

        for(Node node : temp)
            parent.appendChild(node);

        for(Node node : temp)
            convertBrackets2mrow(node);
    }


//    private static final Map<String, String> opRev = new TreeMap<>();
//    static
//    {
//        opRev.put("+", "-");
//        opRev.put("-", "+");
//        opRev.put("±", "∓");
//        opRev.put("∓", "±");
//    }
//
//
//    private void removeSign(Node parent)
//    {
//        if(parent == null || parent instanceof  Text )
//            return;
//
//        int size =  parent.getChildNodes().getLength() ;
//        if(size < 2)   return;
//
//        Node[] childs =
//    }

    /**
     * 하나의 루트 노드를 기준으로 core.symbol 트리를 형성하고 그 트리의 루트 노드를 반환하는 메소드
     * @param node
     * @return 생성된 core.symbol 트리의 루트 노드
     */
    private Symbol extractSymbol(Node node)
    {
        if( node == null )
            return null;

        String nodeName = node.getNodeName().toLowerCase();

        switch (nodeName)
        {
            case "merror":
            case "annoration":
                return null;

            case "mrow":
            case "math":
            case "semantic":
                return extractSymbol(node.getChildNodes());

            case "mn":
                String nodeText = node.getTextContent();
                if(FSTUtils.isFloatString(nodeText))
                    return new Constant( Double.parseDouble(nodeText) );
                if(FSTUtils.isIntegerString(nodeText))
                    return new Constant( Integer.parseInt(nodeText) );
                return null;

            case "mi":
                return new Variable( node.getTextContent() );

            case "mfactorial":
                return new Factorial( extractSymbol(node.getChildNodes()));

            case "mroot":
                return new Power( extractSymbol(node.getFirstChild()) , extractSymbol(node.getLastChild()));

            case "msqrt":
                return new Power( extractSymbol(node.getFirstChild()), new Constant(1,2));

            case "munderover":
            case "msubsup":
                Node firstChild = node.getFirstChild();
                if(firstChild == null)
                    return null;

                switch (firstChild.getTextContent())
                {
                    case SYMBOL_INTGRAL:

                        break;
                    case SYMBOL_SIGMA:

                }

                return null;
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

    public enum MathMLSymbols
    {
//        Add(core.symbol.operator.binary.Add.class, "+", 880, true, true)
        ;

        public final int priority;
        public final String tag;
        public final Class<? extends Symbol> symbolClass;
        public final boolean isBinary;
        public final int isChainable;
        private MathMLSymbols(Class<? extends Symbol> symbolClass, String tag, int priority, boolean isBinary, int isChainable )
        {
            this.symbolClass = symbolClass;
            this.tag = tag ;
            this.priority = priority;
            this.isBinary = isBinary;
            this.isChainable = isChainable;
        }


        public static final int CHAIN_NONE = 0;
        public static final int CHAIN_ALL  = 1;
        public static final int CHAIN_WITHOUT_HEAD = 2;
        public static MathMLSymbols getSymbolFromNode(Node node)
        {

            return null;
        }
    }
}
