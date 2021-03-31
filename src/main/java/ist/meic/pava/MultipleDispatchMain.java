package ist.meic.pava;

import ist.meic.pava.MultipleDispatch.UsingMultipleDispatch;
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
                default:
                    defaultDispatchTest();
            }
        } else {
            defaultDispatchTest();
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
                UsingMultipleDispatch.invoke(device, "draw",shape); //with dynamic invocation
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
}
