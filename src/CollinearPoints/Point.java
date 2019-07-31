package CollinearPoints;

import edu.princeton.cs.algs4.StdDraw;

import java.util.Comparator;

/**
 * Created by piyus on 29-03-2017.
 */
public class Point implements Comparable<Point> {

    private int x;
    private int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (this.x == that.x && this.y == that.y) {
            return -(1.0 / 0);
        }
        double slope = ((this.y - that.y) * (1.0)) / (this.x - that.x);
        if (slope == -(1.0 / 0)) {
            return (1.0 / 0);
        }
        if (slope == -0) {
            return 0.0;
        }
        return slope;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    @Override
    public int compareTo(Point that) {
        if (this.y < that.y) {
            return -1;
        }
        if (this.y > that.y) {
            return 1;
        }
        if (this.x < that.x) {
            return -1;
        }
        if (this.x > that.x) {
            return 1;
        }
        return 0;
    }

    public Comparator<Point> slopeOrder() {
        Point point = new Point(this.x, this.y);
        Comparator<Point> comparator = (o1, o2) -> {
            double s1 = point.slopeTo(o1);
            double s2 = point.slopeTo(o2);
            if (s1 > s2) {
                return 1;
            }
            if (s1 < s2) {
                return -1;
            }
            return 0;
        };
        return comparator;
    }


    public static void main(String[] args) {
        Point p, q, r;
        p = new Point(405, 260);
        q = new Point(263, 60);
        r = new Point(11, 231);
        System.out.println(p.slopeTo(q));
        System.out.println(p.slopeTo(r));
        System.out.println(p.slopeOrder().compare(q, r));

    }
}
