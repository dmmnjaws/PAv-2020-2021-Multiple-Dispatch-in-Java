package ist.meic.pava.domainExample.extensions;

/**
 * Simple class to test Boxing/Unboxing of arguments in the Multiple Dispatch algorithm.
 * (For future considerations)
 */
public class BoxingUnboxing {
    public void bar(int i) {
        System.err.println("Int");
    }
    public void bar(Integer i) {
        System.err.println("Integer");
    }
}
