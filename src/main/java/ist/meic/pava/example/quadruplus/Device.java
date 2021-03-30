package ist.meic.pava.example.quadruplus;

public class Device {
    public void draw(Shape s, Brush b) {
        System.err.println("draw what where and with what?");
    }
    public void draw(Line l, Brush b) {
        System.err.println("draw a line where and with what?");
    }
    public void draw(Circle c, Brush b) {
        System.err.println("draw a circle where and with what?");
    }
    public void draw(Shape s, Brush b, StrokeWidth w){
        System.err.println("draw what, where, with what, and with what stroke width?");
    }
}
