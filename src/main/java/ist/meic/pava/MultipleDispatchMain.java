package ist.meic.pava;

import ist.meic.pava.MultipleDispatch.UsingDoubleDispatch;
import ist.meic.pava.example.basic.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MultipleDispatchMain {

    public static void main(String[] args) {
        SpringApplication.run(MultipleDispatchMain.class, args);

        Device[] devices = new Device[] { new Screen(), new Printer() };
        Shape[] shapes = new Shape[] { new Line(), new Circle() };
        for (Device device : devices) {
            for (Shape shape : shapes) {
                //device.draw(shape);
                UsingDoubleDispatch.invoke(device, "draw", shape);
            }
        }
    }

}
