package test;

/**
 * An immutable point in floating-point pixel space.
 * AF (x,y)= a point with the double x as the  x-coordinate,double y as the  y-coordinate
 * Representation invariant:
 * Safety from rep exposure:
 * All fields are private final;
 */
public class Point {

    private final double x;
    private final double y;

    /**
     * Construct a point at the given coordinates.
     * @param x x-coordinate
     * @param y y-coordinate
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return x-coordinate of the point
     */
    public double x() {
        return x;
    }

    /**
     * @return y-coordinate of the point
     */
    public double y() {
        return y;
    }
}
