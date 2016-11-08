package formula;

import javafx.util.Pair;
import symbol.base.Operator;
import symbol.base.Symbol;
import symbol.operator.unary.Integral;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by waps12b on 2016. 11. 1..
 */
public class Formula
{
    private Symbol rootSymbol;
    private int size;
    private TreeMap<Class<? extends  Symbol>, Integer> counter;



    public Formula(Symbol root)
    {
        this.size = 0;
        this.rootSymbol = root;
        this.counter = new TreeMap<>();
        dfs(root);
    }

    private void dfs(Symbol node)
    {
        this.size ++;
        if(false == counter.containsKey(node.getClass()))
            counter.put(node.getClass(), 0);

        int cnt = counter.get(node.getClass());
        counter.put(node.getClass(), cnt+1);

        if( node instanceof Operator)
        {
            Operator operator = (Operator)node;
            for(Symbol operand : operator.getOperands())
            {
                dfs(operand);
            }
        }
    }


    @Override
    public boolean equals(Object obj) {
        if(obj.getClass().equals( this.getClass()))
        {
            Formula op = (Formula) obj;
            if(this.size == op.size && this.counter.size() == op.counter.size())
            {
                for (Class<? extends Symbol> type : this.counter.keySet())
                {
                    if(false == op.counter.containsKey(type))
                        return false;
                    if(false == this.counter.get(type).equals( op.counter.get(type )))
                        return false;
                }
                return this.rootSymbol.equals( op.rootSymbol );
            }
        }
        return false;
    }

}
