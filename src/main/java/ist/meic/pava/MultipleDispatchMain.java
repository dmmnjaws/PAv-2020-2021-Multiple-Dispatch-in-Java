package ist.meic.pava;

import ist.meic.pava.MultipleDispatch.UsingDoubleDispatch;
import ist.meic.pava.MultipleDispatch.UsingMultipleDispatch;
//import ist.meic.pava.example.quadruplus.*;
import ist.meic.pava.example.triple.*;
//import ist.meic.pava.example.basic.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDispatchMain {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDispatchMain.class, args);


/*
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
*/
        Device[] devices = new Device[] { new Screen(), new Printer() };
        Shape[] shapes = new Shape[] { new Line(), new Circle() };
        Brush[] brushes = new Brush[] { new Pencil(), new Crayon() };
        for (Device device : devices) {
            for (Shape shape : shapes) {
                for (Brush brush : brushes) {
                        UsingMultipleDispatch.invoke(device, "draw", shape, brush);
                }
            }
        }



        /*
        Device[] devices = new Device[] { new Screen(), new Printer() };
        Shape[] shapes = new Shape[] { new Line(), new Circle() };
        for (Device device : devices) {
            for (Shape shape : shapes) {
                //device.draw(shape); //without dynamic invocation
                UsingDoubleDispatch.invoke(device, "draw",shape); //with dynamic invocation
            }
        }
        */

    }

}
