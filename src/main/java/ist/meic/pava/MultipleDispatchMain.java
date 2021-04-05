package ist.meic.pava;

import ist.meic.pava.MultipleDispatch.UsingMultipleDispatch;
import ist.meic.pava.MultipleDispatchExtended.UsingMultipleDispatchExtended;
import ist.meic.pava.domainExample.extensions.BoxingUnboxing;
import ist.meic.pava.domainExample.extensions.VariableArity;
import ist.meic.pava.domainExample.quadruple.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDispatchMain {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDispatchMain.class, args);

        if (args.length != 0) {
            switch (args[0]) {
                case "double":
                    doubleDispatchTest();
                    break;
                case "triple":
                    tripleDispatchTest();
                    break;
                case "quadruple":
                    quadrupleDispatchTest();
                    break;
                case "boxunbox":
                    boxingUnboxingTest();
                    break;
                case "variablearity":
                    variableArityTest();
                    break;

                default:
                    defaultDispatchTest();

            }

        } else { //other specialized tests

        }
    }

    /**
     * Invokes UsingMultipleDispatch.invoke() in a Double Dispatch scenario.
     */
    private static void doubleDispatchTest() {

        Device[] devices = new Device[] { new Screen(), new Printer() };
        Shape[] shapes = new Shape[] { new Line(), new Circle() };
        for (Device device : devices) {
            for (Shape shape : shapes) {
                //device.draw(shape); //without dynamic invocation
                UsingMultipleDispatch.invoke(device, "draw",shape);
            }
        }
    }

    /**
     * Invokes UsingMultipleDispatch.invoke() in a Triple Dispatch scenario.
     */
    private static void tripleDispatchTest() {

        Device[] devices = new Device[]{new Screen(), new Printer()};
        Shape[] shapes = new Shape[]{new Line(), new Circle()};
        Brush[] brushes = new Brush[]{new Pencil(), new Crayon()};
        for (Device device : devices) {
            for (Shape shape : shapes) {
                for (Brush brush : brushes) {
                    UsingMultipleDispatch.invoke(device, "draw", shape, brush);
                }
            }
        }
    }

    /**
     * Invokes UsingMultipleDispatch.invoke() in a Quadruple Dispatch scenario.
     */
    private static void quadrupleDispatchTest() {

        Device[] devices = new Device[] { new Screen(), new Printer() };
        Shape[] shapes = new Shape[] { new Line(), new Circle() };
        Brush[] brushes = new Brush[] { new Pencil(), new Crayon() };
        StrokeWidth[] strokeWidths = new StrokeWidth[] { new Thin(), new Fat() };
        for (Device device : devices) {
            for (Shape shape : shapes) {
                for (Brush brush : brushes) {
                    for (StrokeWidth strokeWidth : strokeWidths){
                        UsingMultipleDispatch.invoke(device, "draw", shape, brush, strokeWidth);
                    }
                }
            }
        }
    }

    /**
     * Invokes UsingMultipleDispatch.invoke() in the Triple Dispatch scenario described in the project statement.
     */
    private static void defaultDispatchTest() {

        ist.meic.pava.domainExample.statement.Device[] devices = new ist.meic.pava.domainExample.statement.Device[]{
                        new ist.meic.pava.domainExample.statement.Screen(),
                        new ist.meic.pava.domainExample.statement.Printer()};

        ist.meic.pava.domainExample.statement.Shape[] shapes = new ist.meic.pava.domainExample.statement.Shape[]{
                new ist.meic.pava.domainExample.statement.Line(),
                new ist.meic.pava.domainExample.statement.Circle()};

        ist.meic.pava.domainExample.statement.Brush[] brushes = new ist.meic.pava.domainExample.statement.Brush[]{
                new ist.meic.pava.domainExample.statement.Pencil(),
                new ist.meic.pava.domainExample.statement.Crayon()};

        for (ist.meic.pava.domainExample.statement.Device device : devices) {
            for (ist.meic.pava.domainExample.statement.Shape shape : shapes) {
                for (ist.meic.pava.domainExample.statement.Brush brush : brushes) {
                    UsingMultipleDispatch.invoke(device, "draw", shape, brush);
                }
            }
        }
    }

    /**
     * Invokes UsingMultipleDispatchExtended.invoke() in a Boxing/Unboxing scenario.
     */
    private static void boxingUnboxingTest(){
        BoxingUnboxing foo = new BoxingUnboxing();
        foo.bar(1);
        foo.bar(Integer.valueOf(1));
        UsingMultipleDispatchExtended.invoke(new BoxingUnboxing(), "bar", 1);
        UsingMultipleDispatchExtended.invoke(new BoxingUnboxing(), "bar", Integer.valueOf(1));
    }

    /**
     * Invokes UsingMultipleDispatchExtended.invoke() in a scenario with Variable Arity methods.
     */
    private static void variableArityTest(){
        VariableArity foo = new VariableArity();

        System.err.println("\n> Giving: Integer, Float");
        foo.poing(Integer.valueOf(1), Float.valueOf(1));
        UsingMultipleDispatchExtended.invoke(new VariableArity(), "poing", Integer.valueOf(1), Float.valueOf(1));

        System.err.println("\n> Giving: Integer, Long");
        foo.poing(Integer.valueOf(1), Long.valueOf(1));
        UsingMultipleDispatchExtended.invoke(foo, "poing", Integer.valueOf(1), Long.valueOf(1));

        System.err.println("\n> Giving: Integer, Long, Float, String");
        foo.poing(Integer.valueOf(1), Long.valueOf(1), Float.valueOf(1), "Poing");
        UsingMultipleDispatchExtended.invoke(foo, "poing", Integer.valueOf(1), Long.valueOf(1), Float.valueOf(1), "Poing");

        System.err.println("\n> Giving: Integer, Long, Float, Double, String");
        foo.poing(Integer.valueOf(1), Long.valueOf(1), Float.valueOf(1), Double.valueOf(1.1), "ola");
        UsingMultipleDispatchExtended.invoke(foo, "poing", Integer.valueOf(1), Long.valueOf(1), Float.valueOf(1), Double.valueOf(1.1), "ola");

        System.err.println("\n> Giving: Integer, Long, Float, Double, Double");
        foo.poing(Integer.valueOf(1), Long.valueOf(1), Float.valueOf(1), Double.valueOf(1.1), Double.valueOf(1.1));
        UsingMultipleDispatchExtended.invoke(foo, "poing", Integer.valueOf(1), Long.valueOf(1), Float.valueOf(1), Double.valueOf(1.1), Double.valueOf(1.1));

        System.err.println("\n> Giving: Long");
        foo.poing(Long.valueOf(1));
        UsingMultipleDispatchExtended.invoke(foo, "poing", Long.valueOf(1));

        System.err.println("\n> Giving: String");
        foo.poing("ola");
        UsingMultipleDispatchExtended.invoke(foo, "poing", "ola");

        System.err.println("\n> Giving: Line, Pencil, Fat, String, Integer");
        foo.poing(new Line(), new Pencil(), new Fat(), "ola", Integer.valueOf(1));
        UsingMultipleDispatchExtended.invoke(foo, "poing", new Line(), new Pencil(), new Fat(), "ola", Integer.valueOf(1));

        System.err.println("\n> Giving: Object[] (In this case, our Multiple Dispatch chooses a different method than Java does, but it's a tradeoff. " +
                "We don't allow primitive arrays in our Multiple Dispatch, they are treated as a collection of arguments)");
        foo.poing(new Object[]{1,2,3});
        UsingMultipleDispatchExtended.invoke(foo, "poing", new Object[] {1,2,3});

        System.err.println("\n> Giving: int, int, int");
        foo.poing(1,2,3);
        UsingMultipleDispatchExtended.invoke(foo, "poing", 1, 2, 3);

        /**
        System.err.println("\n> Giving: Object[] to poingWithArray");
        foo.poingWithArray(new Object[] {1,2,3});
        UsingMultipleDispatchExtended.invoke(foo, "poingWithArray", new Object[] {1,2,3});
         **/
    }
}
