package ist.meic.pava.domainExample.testSuite;

public class Screen extends Device {

    // TESTING DOUBLE DISPATCH

    public String draw(Shape s) {
        return "Screen.draw(Shape)";
    }
    public String draw(Line l) {
        return "Screen.draw(Line)";
    }
    public String draw(Circle c) {
        return "Screen.draw(Circle)";
    }
    // TESTING TRIPLE DISPATCH

    public String draw(Shape s, Brush b) {
        return "Screen.draw(Shape, Brush)";
    }
    public String draw(Shape s, Pencil p) {
        return "Screen.draw(Shape, Pencil)";
    }
    public String draw(Shape s, Crayon c) {
        return "Screen.draw(Shape, Crayon)";
    }

    public String draw(Line l, Brush b) {
        return "Screen.draw(Line, Brush)";
    }
    public String draw(Line l, Pencil p) {
        return "Screen.draw(Line, Pencil)";
    }
    public String draw(Line l, Crayon c) {
        return "Screen.draw(Line, Crayon)";
    }

    public String draw(Circle c, Brush b) {
        return "Screen.draw(Circle, Brush)";
    }
    public String draw(Circle c, Pencil p) {
        return "Screen.draw(Circle, Pencil)";
    }
    public String draw(Circle c1, Crayon c2) {
        return "Screen.draw(Circle, Crayon)";
    }

    // TESTING QUADRUPLE DISPATCH

    public String draw(Shape s, Brush b, StrokeWidth sw) {
        return "Screen.draw(Shape, Brush, StrokeWidth)";
    }
    public String draw(Shape s, Brush b, Thin t) {
        return "Screen.draw(Shape, Brush, Thin)";
    }
    public String draw(Shape s, Brush b, Fat f) {
        return "Screen.draw(Shape, Brush, Fat)";
    }

    public String draw(Shape s, Pencil p, StrokeWidth sw) {
        return "Screen.draw(Shape, Pencil, StrokeWidth)";
    }
    public String draw(Shape s, Pencil p, Thin t) {
        return "Screen.draw(Shape, Pencil, Thin)";
    }
    public String draw(Shape s, Pencil p, Fat f) {
        return "Screen.draw(Shape, Pencil, Fat)";
    }

    public String draw(Shape s, Crayon c, StrokeWidth sw) {
        return "Screen.draw(Shape, Crayon, StrokeWidth)";
    }
    public String draw(Shape s, Crayon c, Thin t) {
        return "Screen.draw(Shape, Crayon, Thin)";
    }
    public String draw(Shape s, Crayon c, Fat f) {
        return "Screen.draw(Shape, Crayon, Fat)";
    }


    public String draw(Line l, Brush b, StrokeWidth sw) {
        return "Screen.draw(Line, Brush, StrokeWidth)";
    }
    public String draw(Line l, Brush b, Thin t) {
        return "Screen.draw(Line, Brush, Thin)";
    }
    public String draw(Line l, Brush b, Fat f) {
        return "Screen.draw(Line, Brush, Fat)";
    }

    public String draw(Line l, Pencil p, StrokeWidth sw) {
        return "Screen.draw(Line, Pencil, StrokeWidth)";
    }
    public String draw(Line l, Pencil p, Thin t) {
        return "Screen.draw(Line, Pencil, Thin)";
    }
    public String draw(Line l, Pencil p, Fat f) {
        return "Screen.draw(Line, Pencil, Fat)";
    }

    public String draw(Line l, Crayon c, StrokeWidth sw) {
        return "Screen.draw(Line, Crayon, StrokeWidth)";
    }
    public String draw(Line l, Crayon c, Thin t) {
        return "Screen.draw(Line, Crayon, Thin)";
    }
    public String draw(Line l, Crayon c, Fat f) {
        return "Screen.draw(Line, Crayon, Fat)";
    }

    public String draw(Circle c, Brush b, StrokeWidth sw) {
        return "Screen.draw(Circle, Brush, StrokeWidth)";
    }
    public String draw(Circle c, Brush b, Thin t) {
        return "Screen.draw(Circle, Brush, Thin)";
    }
    public String draw(Circle c, Brush b, Fat f) {
        return "Screen.draw(Circle, Brush, Fat)";
    }

    public String draw(Circle c, Pencil p, StrokeWidth sw) {
        return "Screen.draw(Circle, Pencil, StrokeWidth)";
    }
    public String draw(Circle c, Pencil p, Thin t) {
        return "Screen.draw(Circle, Pencil, Thin)";
    }
    public String draw(Circle c, Pencil p, Fat f) {
        return "Screen.draw(Circle, Pencil, Fat)";
    }

    public String draw(Circle c1, Crayon c2, StrokeWidth sw) {
        return "Screen.draw(Circle, Crayon, StrokeWidth)";
    }
    public String draw(Circle c1, Crayon c2, Thin t) {
        return "Screen.draw(Circle, Crayon, Thin)";
    }
    public String draw(Circle c1, Crayon c2, Fat f) {
        return "Screen.draw(Circle, Crayon, Fat)";
    }

}