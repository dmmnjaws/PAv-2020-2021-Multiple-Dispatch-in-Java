package ist.meic.pava.domainExample.testSuite;

public class Printer extends Device {

    // TESTING DOUBLE DISPATCH

    public String draw(Shape s) {
        return "Printer.draw(Shape)";
    }
    public String draw(Line l) {
        return "Printer.draw(Line)";
    }
    public String draw(Circle c) {
        return "Printer.draw(Circle)";
    }
    // TESTING TRIPLE DISPATCH

    public String draw(Shape s, Brush b) {
        return "Printer.draw(Shape, Brush)";
    }
    public String draw(Shape s, Pencil p) {
        return "Printer.draw(Shape, Pencil)";
    }
    public String draw(Shape s, Crayon c) {
        return "Printer.draw(Shape, Crayon)";
    }

    public String draw(Line l, Brush b) {
        return "Printer.draw(Line, Brush)";
    }
    public String draw(Line l, Pencil p) {
        return "Printer.draw(Line, Pencil)";
    }
    public String draw(Line l, Crayon c) {
        return "Printer.draw(Line, Crayon)";
    }

    public String draw(Circle c, Brush b) {
        return "Printer.draw(Circle, Brush)";
    }
    public String draw(Circle c, Pencil p) {
        return "Printer.draw(Circle, Pencil)";
    }
    public String draw(Circle c1, Crayon c2) {
        return "Printer.draw(Circle, Crayon)";
    }

    // TESTING QUADRUPLE DISPATCH

    public String draw(Shape s, Brush b, StrokeWidth sw) {
        return "Printer.draw(Shape, Brush, StrokeWidth)";
    }
    public String draw(Shape s, Brush b, Thin t) {
        return "Printer.draw(Shape, Brush, Thin)";
    }
    public String draw(Shape s, Brush b, Fat f) {
        return "Printer.draw(Shape, Brush, Fat)";
    }

    public String draw(Shape s, Pencil p, StrokeWidth sw) {
        return "Printer.draw(Shape, Pencil, StrokeWidth)";
    }
    public String draw(Shape s, Pencil p, Thin t) {
        return "Printer.draw(Shape, Pencil, Thin)";
    }
    public String draw(Shape s, Pencil p, Fat f) {
        return "Printer.draw(Shape, Pencil, Fat)";
    }

    public String draw(Shape s, Crayon c, StrokeWidth sw) {
        return "Printer.draw(Shape, Crayon, StrokeWidth)";
    }
    public String draw(Shape s, Crayon c, Thin t) {
        return "Printer.draw(Shape, Crayon, Thin)";
    }
    public String draw(Shape s, Crayon c, Fat f) {
        return "Printer.draw(Shape, Crayon, Fat)";
    }


    public String draw(Line l, Brush b, StrokeWidth sw) {
        return "Printer.draw(Line, Brush, StrokeWidth)";
    }
    public String draw(Line l, Brush b, Thin t) {
        return "Printer.draw(Line, Brush, Thin)";
    }
    public String draw(Line l, Brush b, Fat f) {
        return "Printer.draw(Line, Brush, Fat)";
    }

    public String draw(Line l, Pencil p, StrokeWidth sw) {
        return "Printer.draw(Line, Pencil, StrokeWidth)";
    }
    public String draw(Line l, Pencil p, Thin t) {
        return "Printer.draw(Line, Pencil, Thin)";
    }
    public String draw(Line l, Pencil p, Fat f) {
        return "Printer.draw(Line, Pencil, Fat)";
    }

    public String draw(Line l, Crayon c, StrokeWidth sw) {
        return "Printer.draw(Line, Crayon, StrokeWidth)";
    }
    public String draw(Line l, Crayon c, Thin t) {
        return "Printer.draw(Line, Crayon, Thin)";
    }
    public String draw(Line l, Crayon c, Fat f) {
        return "Printer.draw(Line, Crayon, Fat)";
    }

    public String draw(Circle c, Brush b, StrokeWidth sw) {
        return "Printer.draw(Circle, Brush, StrokeWidth)";
    }
    public String draw(Circle c, Brush b, Thin t) {
        return "Printer.draw(Circle, Brush, Thin)";
    }
    public String draw(Circle c, Brush b, Fat f) {
        return "Printer.draw(Circle, Brush, Fat)";
    }

    public String draw(Circle c, Pencil p, StrokeWidth sw) {
        return "Printer.draw(Circle, Pencil, StrokeWidth)";
    }
    public String draw(Circle c, Pencil p, Thin t) {
        return "Printer.draw(Circle, Pencil, Thin)";
    }
    public String draw(Circle c, Pencil p, Fat f) {
        return "Printer.draw(Circle, Pencil, Fat)";
    }

    public String draw(Circle c1, Crayon c2, StrokeWidth sw) {
        return "Printer.draw(Circle, Crayon, StrokeWidth)";
    }
    public String draw(Circle c1, Crayon c2, Thin t) {
        return "Printer.draw(Circle, Crayon, Thin)";
    }
    public String draw(Circle c1, Crayon c2, Fat f) {
        return "Printer.draw(Circle, Crayon, Fat)";
    }


}
