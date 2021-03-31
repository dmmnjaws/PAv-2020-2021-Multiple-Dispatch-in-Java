package ist.meic.pava.example.triple;

public class Printer extends Device {
    public void draw(Line l, Brush b) {
        System.err.println("draw a line on printer with what?");
    }
    public void draw(Line l, Pencil p) {
        System.err.println("drawing a line on printer with pencil!");
    }
    public void draw(Line l, Crayon c) {
        System.err.println("drawing a line on printer with crayon!");
    }
    public void draw(Circle c, Brush b) {
        System.err.println("drawing a circle on printer with what?");
    }
    public void draw(Circle c, Pencil p) {
        System.err.println("drawing a circle on printer with pencil!");
    }
    public void draw(Circle c, Crayon y) {
        System.err.println("drawing a line on printer with crayon!");
    }
}
