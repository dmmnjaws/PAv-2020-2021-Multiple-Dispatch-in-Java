package ist.meic.pava.domainExample.extensions;

public class BoxingUnboxing {
    public void bar(int i) {
        System.err.println("Int");
    }
    public void bar(Integer i) {
        System.err.println("Integer");
    }
}
