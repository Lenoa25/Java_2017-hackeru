package graphicshape;

/**
 * Created by hackeru on 2/7/2017.
 */
public class Segment {
    private Point p1, p2;
    private double length;
    private boolean isLengthCalculated;

    public Segment(Point p1, Point p2) {
        setP1(p1);
        setP2(p2);
    }

    public Point getP1() {
        return new Point(p1);
    }

    public void setP1(Point p1) {
        isLengthCalculated = false;
        this.p1 = new Point(p1);
    }

    public Point getP2() {
        return p2;
    }

    public void setP2(Point p2) {
        isLengthCalculated = false;
        this.p2 = p2;
    }

    public void calculateLength() {
        int deltaY = p1.getY() - p2.getY();
        int deltax = p1.getX() - p2.getX();
        length = Math.sqrt(deltax * deltax + deltaY * deltaY);
        isLengthCalculated = true;
    }

    public double slope() {

        double deltaY = p1.getY() - p2.getY();
        double deltax = p1.getX() - p2.getX();
        if (deltax == 0)
            return Double.MAX_VALUE;
        return deltaY / deltax;
    }

    public double getLength() {
        if (!isLengthCalculated)
            calculateLength();
        return length;
    }

    /**
     * the line that going through the two points p1,p1
     * that determine this segment can be represented
     * as Ax +By + C =0
     *
     * @return returns the A of the equation.
     */
    public double A() {
        return -1 * slope();
    }

    public double B() {
        return 1;
    }

    public double C() {
        return slope() * p1.getX() - p1.getY();
    }

    public double distanceToPoint(Point point) {
        double A = A();
        double numerator = A * point.getX() + B() * point.getY() + C();
        if (numerator < 0)
            numerator *= -1;
        double denominator = Math.sqrt(A * A + B() * B());
        //B()*B()=1
        return numerator / denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (obj instanceof Segment) {
            Segment s = (Segment) obj;
            return (this.p1.equals(s.p1) && this.p2.equals(s.p2)) || (this.p1.equals(s.p2) && this.p2.equals(s.p1));
        }
        return false;
    }
}


