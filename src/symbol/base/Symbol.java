package symbol.base;

/**
 * Created by dongyi on 16. 11. 1.
 */
public abstract class Symbol {


    public abstract String toLaTex();
    public abstract String toMathML();


    @Override
    public boolean equals(Object obj) {
        return obj.getClass() == this.getClass();
    }
}
