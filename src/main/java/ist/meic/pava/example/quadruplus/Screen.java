package ist.meic.pava.example.quadruplus;

public class Screen extends Device {

    // TESTING DOUPLE DISPATCH

    public void draw(Shape s) {
        System.err.println("draw what, in screen?");
    }
    public void draw(Line l) {
        System.err.println("drawing a line, in screen!");
    }
    public void draw(Circle c) {
        System.err.println("drawing a circle, in screen!");
    }

    // TESTING TRIPLE DISPATCH

    public void draw(Shape s, Brush b) {
        System.err.println("draw what, in screen, with what?");
    }
    public void draw(Shape s, Pencil p) {
        System.err.println("draw what, in screen, with a pencil?");
    }
    public void draw(Shape s, Crayon c) {
        System.err.println("draw what, in screen, with a crayon?");
    }

    public void draw(Line l, Brush b) {
        System.err.println("draw a line, in screen, with what?");
    }
    public void draw(Line l, Pencil p) {
        System.err.println("drawing a line, in screen, with a pencil!");
    }
    public void draw(Line l, Crayon c) {
        System.err.println("drawing a line, in screen, with a crayon!");
    }

    public void draw(Circle c, Brush b) {
        System.err.println("draw a circle, in screen, with what?");
    }
    public void draw(Circle c, Pencil p) {
        System.err.println("drawing a circle, in screen, with a pencil!");
    }
    public void draw(Circle c1, Crayon c2) {
        System.err.println("drawing a circle, in screen, with a crayon!");
    }

    // TESTING QUADRUPLE DISPATCH

    public void draw(Shape s, Brush b, StrokeWidth sw) {
        System.err.println("draw what, in screen, with what, with what stroke?");
    }
    public void draw(Shape s, Brush b, Thin t) {
        System.err.println("draw what, in screen, with what, with thin stroke?");
    }
    public void draw(Shape s, Brush b, Fat f) {
        System.err.println("draw what, in screen, with what, with fat stroke?");
    }

    public void draw(Shape s, Pencil p, StrokeWidth sw) {
        System.err.println("draw what, in screen, with a pencil, with what stroke?");
    }
    public void draw(Shape s, Pencil p, Thin t) {
        System.err.println("draw what, in screen, with a pencil, with thin stroke?");
    }
    public void draw(Shape s, Pencil p, Fat f) {
        System.err.println("draw what, in screen, with a pencil, with fat stroke?");
    }

    public void draw(Shape s, Crayon c, StrokeWidth sw) {
        System.err.println("draw what, in screen, with a crayon, with what stroke?");
    }
    public void draw(Shape s, Crayon c, Thin t) {
        System.err.println("draw what, in screen, with a crayon, with thin stroke?");
    }
    public void draw(Shape s, Crayon c, Fat f) {
        System.err.println("draw what, in screen, with a crayon, with fat stroke?");
    }

    public void draw(Line l, Brush b, StrokeWidth sw) {
        System.err.println("draw a line, in screen, with what, with what stroke?");
    }
    public void draw(Line l, Brush b, Thin t) {
        System.err.println("draw a line, in screen, with what, with thin stroke?");
    }
    public void draw(Line l, Brush b, Fat f) {
        System.err.println("draw a line, in screen, with what, with fat stroke?");
    }

    public void draw(Line l, Pencil p, StrokeWidth sw) {
        System.err.println("draw a line, in screen, with a pencil, with what stroke?");
    }
    public void draw(Line l, Pencil p, Thin t) {
        System.err.println("drawing a line, in screen, with a pencil, with thin stroke!");
    }
    public void draw(Line l, Pencil p, Fat f) {
        System.err.println("drawing a line, in screen, with a pencil, with fat stroke!");
    }

    public void draw(Line l, Crayon c, StrokeWidth sw) {
        System.err.println("draw a line, in screen, with a crayon, with what stroke?");
    }
    public void draw(Line l, Crayon c, Thin t) {
        System.err.println("drawing a line, in screen, with a crayon, with thin stroke!");
    }
    public void draw(Line l, Crayon c, Fat f) {
        System.err.println("drawing a line, in screen, with a crayon, with fat stroke!");
    }

    public void draw(Circle c, Brush b, StrokeWidth sw) {
        System.err.println("draw a circle, in screen, with what, with what stroke?");
    }
    public void draw(Circle c, Brush b, Thin t) {
        System.err.println("draw a circle, in screen, with what, with thin stroke?");
    }
    public void draw(Circle c, Brush b, Fat f) {
        System.err.println("draw a circle, in screen, with what, with fat stroke?");
    }

    public void draw(Circle c, Pencil p, StrokeWidth sw) {
        System.err.println("draw a circle, in screen, with a pencil, with what stroke?");
    }
    public void draw(Circle c, Pencil p, Thin t) {
        System.err.println("drawing a circle, in screen, with a pencil, with thin stroke!");
    }
    public void draw(Circle c, Pencil p, Fat f) {
        System.err.println("drawing a circle, in screen, with a pencil, with fat stroke!");
    }

    public void draw(Circle c1, Crayon c2, StrokeWidth sw) {
        System.err.println("draw what, in screen, with a crayon, with what stroke?");
    }
    public void draw(Circle c1, Crayon c2, Thin t) {
        System.err.println("drawing a circle, in screen, with a crayon, with thin stroke!");
    }
    public void draw(Circle c1, Crayon c2, Fat f) {
        System.err.println("drawing a circle, in screen, with a crayon, with fat stroke!");
    }

}