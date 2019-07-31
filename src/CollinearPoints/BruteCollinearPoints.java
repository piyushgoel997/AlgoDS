package CollinearPoints;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by piyus on 29-03-2017.
 */
public class BruteCollinearPoints {
    private Point[] points;
    private int noOfSegments;
    private ArrayList<LineSegment> lineSegments;

    public BruteCollinearPoints(Point[] points) {
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
        lineSegments = new ArrayList<>();
        bruteForce();
    }

    public int numberOfSegments() {
        return noOfSegments;
    }

    public LineSegment[] segments() {
        LineSegment[] segments = lineSegments.toArray(new LineSegment[lineSegments.size()]);
        return segments;
    }

    private void bruteForce() {
        for (int i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                for (int k = j + 1; k < points.length; k++) {
                    for (int l = k + 1; l < points.length; l++) {
                        Point[] currPts = new Point[4];
                        currPts[0] = points[i];
                        currPts[1] = points[j];
                        currPts[2] = points[k];
                        currPts[3] = points[l];
                        if (checkLine(currPts)) {
                            noOfSegments++;
                            Arrays.sort(currPts);
                            lineSegments.add(new LineSegment(currPts[0], currPts[3]));
                        }
                    }
                }
            }
        }
    }

    private boolean checkLine(Point[] currPts) {
        double[] slopes = new double[3];
        for (int i = 0; i < 3; i++) {
            slopes[i] = currPts[i].slopeTo(currPts[i + 1]);
        }
        if (slopes[0] == slopes[1] && slopes[1] == slopes[2]) {
            return true;
        }
        return false;
    }
}
