package ist.meic.pava.domainExample.quadruple;

public class Device {

    // TESTING DOUBLE DISPATCH

    public void draw(Shape s) {
        System.err.println("draw what, where?");
    }
    public void draw(Line l) {
        System.err.println("draw a line, where?");
    }
    public void draw(Circle c) {
        System.err.println("draw a circle, where?");
    }

    // TESTING TRIPLE DISPATCH

    public void draw(Shape s, Brush b) {
        System.err.println("draw what, where, with what?");
    }
    public void draw(Shape s, Pencil p) {
        System.err.println("draw what, where, with a pencil?");
    }
    public void draw(Shape s, Crayon c) {
        System.err.println("draw what, where, with a crayon?");
    }

    public void draw(Line l, Brush b) {
        System.err.println("draw a line, where, with what?");
    }
    public void draw(Line l, Pencil p) {
        System.err.println("draw a line, where, with a pencil?");
    }
    public void draw(Line l, Crayon c) {
        System.err.println("draw a line, where, with a crayon?");
    }

    public void draw(Circle c, Brush b) {
        System.err.println("draw a circle, where, with what?");
    }
    public void draw(Circle c, Pencil p) {
        System.err.println("draw a circle, where, with a pencil?");
    }
    public void draw(Circle c1, Crayon c2) {
        System.err.println("draw a circle, where, with a crayon?");
    }

    // TESTING QUADRUPLE DISPATCH

    public void draw(Shape s, Brush b, StrokeWidth sw) {
        System.err.println("draw what, where, with what, with what stroke?");
    }
    public void draw(Shape s, Brush b, Thin t) {
        System.err.println("draw what, where, with what, with thin stroke?");
    }
    public void draw(Shape s, Brush b, Fat f) {
        System.err.println("draw what, where, with what, with fat stroke?");
    }

    public void draw(Shape s, Pencil p, StrokeWidth sw) {
        System.err.println("draw what, where, with a pencil, with what stroke?");
    }
    public void draw(Shape s, Pencil p, Thin t) {
        System.err.println("draw what, where, with a pencil, with thin stroke?");
    }
    public void draw(Shape s, Pencil p, Fat f) {
        System.err.println("draw what, where, with a pencil, with fat stroke?");
    }

    public void draw(Shape s, Crayon c, StrokeWidth sw) {
        System.err.println("draw what, where, with a crayon, with what stroke?");
    }
    public void draw(Shape s, Crayon c, Thin t) {
        System.err.println("draw what, where, with a crayon, with thin stroke?");
    }
    public void draw(Shape s, Crayon c, Fat f) {
        System.err.println("draw what, where, with a crayon, with fat stroke?");
    }

    public void draw(Line l, Brush b, StrokeWidth sw) {
        System.err.println("draw a line, where, with what, with what stroke?");
    }
    public void draw(Line l, Brush b, Thin t) {
        System.err.println("draw a line, where, with what, with thin stroke?");
    }
    public void draw(Line l, Brush b, Fat f) {
        System.err.println("draw a line, where, with what, with fat stroke?");
    }

    public void draw(Line l, Pencil p, StrokeWidth sw) {
        System.err.println("draw a line, where, with a pencil, with what stroke?");
    }
    public void draw(Line l, Pencil p, Thin t) {
        System.err.println("draw a line, where, with a pencil, with thin stroke?");
    }
    public void draw(Line l, Pencil p, Fat f) {
        System.err.println("draw a line, where, with a pencil, with fat stroke?");
    }

    public void draw(Line l, Crayon c, StrokeWidth sw) {
        System.err.println("draw a line, where, with a crayon, with what stroke?");
    }
        public void draw(Line l, Crayon c, Thin t) {
        System.err.println("draw a line, where, with a crayon, with thin stroke?");
    }
    public void draw(Line l, Crayon c, Fat f) {
        System.err.println("draw a line, where, with a crayon, with fat stroke?");
    }

    public void draw(Circle c, Brush b, StrokeWidth sw) {
        System.err.println("draw a circle, where, with what, with what stroke?");
    }
    public void draw(Circle c, Brush b, Thin t) {
        System.err.println("draw a circle, where, with what, with thin stroke?");
    }
    public void draw(Circle c, Brush b, Fat f) {
        System.err.println("draw a circle, where, with what, with fat stroke?");
    }

    public void draw(Circle c, Pencil p, StrokeWidth sw) {
        System.err.println("draw a circle, where, with a pencil, with what stroke?");
    }
    public void draw(Circle c, Pencil p, Thin t) {
        System.err.println("draw a circle, where, with a pencil, with thin stroke?");
    }
    public void draw(Circle c, Pencil p, Fat f) {
        System.err.println("draw a circle, where, with a pencil, with fat stroke?");
    }

    public void draw(Circle c1, Crayon c2, StrokeWidth sw) {
        System.err.println("draw a circle, where, with a crayon, with what stroke?");
    }
    public void draw(Circle c1, Crayon c2, Thin t) {
        System.err.println("draw a circle, where, with a crayon, with thin stroke?");
    }
    public void draw(Circle c1, Crayon c2, Fat f) {
        System.err.println("draw a circle, where, with a crayon, with fat stroke?");
    }
}
