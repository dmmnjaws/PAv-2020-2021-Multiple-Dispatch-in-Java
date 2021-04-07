package ist.meic.pava.domainExample.testSuite;

public class Device {

    // TESTING DOUBLE DISPATCH

    public String draw(Shape s) {
        return "Device.draw(Shape)";
    }
    public String draw(Line l) {
        return "Device.draw(Line)";
    }
    public String draw(Circle c) {
        return "Device.draw(Circle)";
    }
    // TESTING TRIPLE DISPATCH

    public String draw(Shape s, Brush b) {
        return "Device.draw(Shape, Brush)";
    }
    public String draw(Shape s, Pencil p) {
        return "Device.draw(Shape, Pencil)";
    }
    public String draw(Shape s, Crayon c) {
        return "Device.draw(Shape, Crayon)";
    }

    public String draw(Line l, Brush b) {
        return "Device.draw(Line, Brush)";
    }
    public String draw(Line l, Pencil p) {
        return "Device.draw(Line, Pencil)";
    }
    public String draw(Line l, Crayon c) {
        return "Device.draw(Line, Crayon)";
    }

    public String draw(Circle c, Brush b) {
        return "Device.draw(Circle, Brush)";
    }
    public String draw(Circle c, Pencil p) {
        return "Device.draw(Circle, Pencil)";
    }
    public String draw(Circle c1, Crayon c2) {
        return "Device.draw(Circle, Crayon)";
    }

    // TESTING QUADRUPLE DISPATCH

    public String draw(Shape s, Brush b, StrokeWidth sw) {
        return "Device.draw(Shape, Brush, StrokeWidth)";
    }
    public String draw(Shape s, Brush b, Thin t) {
        return "Device.draw(Shape, Brush, Thin)";
    }
    public String draw(Shape s, Brush b, Fat f) {
        return "Device.draw(Shape, Brush, Fat)";
    }

    public String draw(Shape s, Pencil p, StrokeWidth sw) {
        return "Device.draw(Shape, Pencil, StrokeWidth)";
    }
    public String draw(Shape s, Pencil p, Thin t) {
        return "Device.draw(Shape, Pencil, Thin)";
    }
    public String draw(Shape s, Pencil p, Fat f) {
        return "Device.draw(Shape, Pencil, Fat)";
    }

    public String draw(Shape s, Crayon c, StrokeWidth sw) {
        return "Device.draw(Shape, Crayon, StrokeWidth)";
    }
    public String draw(Shape s, Crayon c, Thin t) {
        return "Device.draw(Shape, Crayon, Thin)";
    }
    public String draw(Shape s, Crayon c, Fat f) {
        return "Device.draw(Shape, Crayon, Fat)";
    }


    public String draw(Line l, Brush b, StrokeWidth sw) {
        return "Device.draw(Line, Brush, StrokeWidth)";
    }
    public String draw(Line l, Brush b, Thin t) {
        return "Device.draw(Line, Brush, Thin)";
    }
    public String draw(Line l, Brush b, Fat f) {
        return "Device.draw(Line, Brush, Fat)";
    }

    public String draw(Line l, Pencil p, StrokeWidth sw) {
        return "Device.draw(Line, Pencil, StrokeWidth)";
    }
    public String draw(Line l, Pencil p, Thin t) {
        return "Device.draw(Line, Pencil, Thin)";
    }
    public String draw(Line l, Pencil p, Fat f) {
        return "Device.draw(Line, Pencil, Fat)";
    }

    public String draw(Line l, Crayon c, StrokeWidth sw) {
        return "Device.draw(Line, Crayon, StrokeWidth)";
    }
    public String draw(Line l, Crayon c, Thin t) {
        return "Device.draw(Line, Crayon, Thin)";
    }
    public String draw(Line l, Crayon c, Fat f) {
        return "Device.draw(Line, Crayon, Fat)";
    }

    public String draw(Circle c, Brush b, StrokeWidth sw) {
        return "Device.draw(Circle, Brush, StrokeWidth)";
    }
    public String draw(Circle c, Brush b, Thin t) {
        return "Device.draw(Circle, Brush, Thin)";
    }
    public String draw(Circle c, Brush b, Fat f) {
        return "Device.draw(Circle, Brush, Fat)";
    }

    public String draw(Circle c, Pencil p, StrokeWidth sw) {
        return "Device.draw(Circle, Pencil, StrokeWidth)";
    }
    public String draw(Circle c, Pencil p, Thin t) {
        return "Device.draw(Circle, Pencil, Thin)";
    }
    public String draw(Circle c, Pencil p, Fat f) {
        return "Device.draw(Circle, Pencil, Fat)";
    }

    public String draw(Circle c1, Crayon c2, StrokeWidth sw) {
        return "Device.draw(Circle, Crayon, StrokeWidth)";
    }
    public String draw(Circle c1, Crayon c2, Thin t) {
        return "Device.draw(Circle, Crayon, Thin)";
    }
    public String draw(Circle c1, Crayon c2, Fat f) {
        return "Device.draw(Circle, Crayon, Fat)";
    }

    // special non-variable arity cases:

    public String poing(Number n, Float l){
        return "Device.poing(Number, Float)";
    }

    public String poing(Integer i, Long l){
        return "Device.poing(Integer, Long)";
    }

    public String poing(Integer i, Line l){
        return "Device.poing(Integer, Line)";
    }

    public String poing(Integer i, Brush b){
        return "Device.poing(Integer, Brush)";
    }

    public String poing(Line l, Fat f){
        return "Device.poing(Line, Fat)";
    }

    // methods for variable arity testing:

    public String poing(Number n1, Number n2){
        return "Device.poing(Number, number)";
    }

    public String poing(Integer i, Long l, Float f, String s){
        return "Device.poing(Integer, Long, Float, String)";
    }

    public String poing(Integer i, Long l, Float f, Object... o){
        return "Device.poing(Integer, Long, Float, Object...)";
    }

    public String poing(Integer i, Long l, Float f, Number... o){
        return "Device.poing(Integer, Long, Float, Number...)";
    }

    public String poing(Integer i, Long l, Float f, Double d, String s, Integer i2, Object... options){
        return "Device.poing(Integer, Long, Float, Double, String, Integer, Object...)";
    }

    public String poing(Number n, Object... options){
        return "Device.poing(Number, Object...)";
    }

    public String poing(Integer i, Object... options){
        return "Device.poing(Integer, Object...)";
    }

    public String poing(Integer i, Number n, Object... options){
        return "Device.poing(Integer, Number, Object...)";
    }

    public String poing(Integer i, Long l, Object... options){
        return "Device.poing(Integer, Long, Object...)";
    }

    public String poing(Integer i, Number... options){
        return "Device.poing(Integer, Number...)";
    }

    public String poing(Object... options){
        return "Device.poing(Object...)";
    }

    public String poing(Object o, Brush b, StrokeWidth sw, Object... options){
        return "Device.poing(Object, Brush, StrokeWidth, Object...)";
    }


}
