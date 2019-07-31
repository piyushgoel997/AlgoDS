package CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by piyus on 30-03-2017.
 */
public class FastCollinearPoints {
    private Point[] points;
    private int noOfSegments;
    private ArrayList<ArrayList<Set>> sets;
    private ArrayList<LineSegment> ls;

    private class Set {
        Point point;
        double slope;
        public Set(Point point, double slope) {
            this.point = point;
            this.slope = slope;
        }
    }


    public FastCollinearPoints(Point[] points) {
        Point[] xPoints = new Point[points.length];
        for (int j = 0; j < points.length; j++) {
            xPoints[j] = points[j];
        }
        this.points = xPoints;
        Arrays.sort(xPoints);
        for (int i = 0; i < xPoints.length - 1; i++) {
            if (xPoints[i] == xPoints[i + 1]) {
                throw new IllegalArgumentException();
            }
        }
        noOfSegments = 0;
        makeSets();
        getSegments();
    }

    public int numberOfSegments() {
        return noOfSegments;
    }

    public LineSegment[] segments() {
        return ls.toArray(new LineSegment[ls.size()]);
    }

    private void makeSets() {
        sets = new ArrayList<>();
        for (int i = 0; i < points.length - 3; i++) {
            ArrayList<Set> curr = new ArrayList<>();
            Point[] temp = new Point[points.length - i - 1];
            for (int j = 0; j < temp.length; j++) {
                temp[j] = points[i + j + 1];
            }
            Arrays.sort(temp,points[i].slopeOrder());
            for (int j = 0; j < temp.length; j++) {
                curr.add(new Set(temp[j], points[i].slopeTo(temp[j])));
            }
            sets.add(curr);
        }
    }

    private void getSegments() {
        ls = new ArrayList<>();
        for (int i = 0; i < sets.size(); i++) {
            ArrayList<Set> curr = sets.get(i);
            for (int j = 0; j < curr.size(); j++) {
                int temp = 0;
                ArrayList<Point> currPoints = new ArrayList<>();
                currPoints.add(curr.get(j).point);
                while (j < curr.size() - 1 && curr.get(j).slope == curr.get(j + 1).slope) {
                    temp++;
                    j++;
                    currPoints.add(curr.get(j).point);
                }
                currPoints.add(points[i]);
                if (temp >= 2) {
                    noOfSegments++;
                    Point[] arr = currPoints.toArray(new Point[currPoints.size()]);
                    Arrays.sort(arr);
                    ls.add(new LineSegment(arr[0], arr[arr.length - 1]));
                }
            }
        }
    }
}
