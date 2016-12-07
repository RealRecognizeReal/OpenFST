package core.symbol.base;

/**
 * Created by dongyi on 16. 11. 1.
 */
public abstract class Symbol {

    public static final int RANK_CONSTANT   = 0;
    public static final int RANK_VARIABLE   = 1;
    public static final int RANK_SYMBOL     = 2;


    public abstract String toLaTex();
    public abstract String toMathML();
    public int getRank()
    {
        return RANK_SYMBOL;
    }


    @Override
    public boolean equals(Object obj) {
        return obj.getClass().equals(this.getClass()) ;
    }

}
