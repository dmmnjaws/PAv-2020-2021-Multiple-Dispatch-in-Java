package ist.meic.pava.domainExample.extensions;

import ist.meic.pava.domainExample.quadruple.*;

public class VariableArity {

    public void poing(Number n1, Number n2){
        System.err.println("poing with Number, Number");
    }
    
    public void poing(Integer i, Long l, Float f, String s){
        System.err.println("poing with Integer, Long, Float, String");
    }

    public void poing(Integer i, Long l, Float f, Object... options){
        System.err.println("poing with Integer, Long, Float and Object... options");
    }

    public void poing(Integer i, Long l, Float f, Number... options){
        System.err.println("poing with Integer, Long, Float and Number... options");
    }

    public void poing(Integer i, Long l, Float f, Double d, String s, Integer i2, Object... options){
        System.err.println("poing with Integer, Long, Float, Double, String, and Object... options");
    }

    public void poing(Number n, Object... options){
        System.err.println("poing with Number and Object... options");
    }

    public void poing(Integer i, Object... options){
        System.err.println("poing with Integer and Object... options");
    }

    public void poing(Integer i, Number n, Object... options){
        System.err.println("poing with Integer, Number and Object... options");
    }

    public void poing(Integer i, Long l, Object... options){
        System.err.println("poing with Integer, Long and Object... options");
    }

    public void poing(Integer i, Number... options){
        System.err.println("poing with Integer and Number... options");
    }

    public void poing(Object... options){
        System.err.println("poing with Object... options");
    }

    public void poing(Object o, Brush b, StrokeWidth sw, Object... options){
        System.err.println("poing with a Object, Brush, StrokeWidth and Object... (you sure visited another package, uh!?)");
    }

    public void poing(Number i, Float l){
        System.err.println("poing with Number, Float");
    }

    public void poing(Integer i, Long l){
        System.err.println("poing with Integer, Long");
    }

    public void poing(Integer i, Line l){
        System.err.println("poing with Integer, Line");
    }

    public void poing(Integer i, Brush l){
        System.err.println("poing with Integer, Brush");
    }

    public void poing(Line l, Fat f){
        System.err.println("poing with Line, Fat");
    }

    /**
     * This one our MultipleDispatchExtended doesn't support,
     * because it can't distinguish between Options[] and the Options... varArgs array,
     * and so, it treats an Object[] one argument at the time instead of a single Object[] argument.
     */
    public void poingWithArray(Object[] options){
        System.err.println("poing with Array");
    }

}
