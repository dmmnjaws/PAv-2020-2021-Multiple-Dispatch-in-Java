package ist.meic.pava.domainExample.extensions;

public class VariableArity {

    public void poing(Integer i, Long l, Float f, String s){
        System.err.println("poing with Integer, Long, Float, String");
    }

    public void poing(Integer i, Long l, Float f, Object... options){
        System.err.println("poing with Integer, Long, Float and VariableArity options");
    }

    public void poing(Integer i, Long l, Float f, Double d, String s, Integer i2, Object... options){
        System.err.println("poing with Integer, Long, Float, Double, String, and VariableArity options");
    }


    public void poing(Integer i, Long l, Object... options){
        System.err.println("poing with Integer, Long and VariableArity options");
    }

    public void poing(Object... options){
        System.err.println("poing with VariableArity options");
    }

    /**
     * This one our MultipleDispatchExtended doesn't support,
     * because it can't distinguish between Options[] and the Options... varArgs array,
     * and so, it treats an Object[] one argument at the time instead of a single argument of type array.
     */
    public void poingWithArray(Object[] options){
        System.err.println("poing with Array");
    }

}
