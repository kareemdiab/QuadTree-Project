
/**
 * Class to hold coordinates and dimensions of Points and
 * perform functions on them.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.09.01
 * 
 *
 */
public class Point {

    private int x;
    private int y;

    private String name;

    /**
     * Point constructor
     * 
     * @param name
     *            Name of Point.
     * @param xCord
     *            X coordinate
     * @param yCord
     *            Y coordinate
     */
    public Point(String name, int xCord, int yCord) {
        this.x = xCord;
        this.y = yCord;

        this.name = name;
    }


    /**
     * ToString method for Point.
     * 
     * @return String representation of Point.
     */
    @Override
    public String toString() {
        String str = x + ", " + y + ")";
        if (name != null) {
            str = "(" + name + ", " + str;
        }
        else {
            str = "(" + str;
        }
        return str;
    }


    /**
     * String method specifically for skiplist.
     * 
     * @return Returns string of point.
     */
    public String skipString() {
        String str = x + ", " + y + ")";
        if (name != null) {
            str = "(" + name + ", " + name + ", " + str;
        }
        else {
            str = "(" + str;
        }
        return str;
    }
    /**
     * Eqauals method for points. 
     * @param obj Point being compared to. 
     * @return true if points are equal
     */
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (obj.getClass() == this.getClass()) {
            Point other = new Point(" ", 0, 0);
            other = (Point)obj;
            if (this.areSame(other)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Name getter.
     * 
     * @return Returns name of Point.
     */
    public String getName() {
        return name;

    }


    /**
     * Checks if Point shares specified x and y coordinates.
     * 
     * @param xCord
     *            X coordinate.
     * @param yCord
     *            Y coordinate.
     * 
     * 
     * @return Returns true if the coordinates match.
     */
    public boolean dimensionCheck(int xCord, int yCord) {
        return ((x == xCord) && (y == yCord));
    }


    /**
     * Checks the Point dimensions to ensure that Point is valid.
     * 
     * @param minX
     *            minimum X of current quadrant.
     * @param minY
     *            min Y of current quadrant.
     * @param maxX
     *            max X of current quadrant.
     * @param maxY
     *            of current Quadrant.
     * @return Returns true if the Point is valid.
     */
    public boolean validPoint(int minX, int minY, int maxX, int maxY) {
        return x < maxX && x >= minX && y < maxY && y >= minY;
    }


    /**
     * Finds quadrant the point is in.
     * 
     * @param minX
     *            minimum X of current quadrant.
     * @param minY
     *            min Y of current quadrant.
     * @param maxX
     *            max X of current quadrant.
     * @param maxY
     *            of current Quadrant.
     * @return int value 1-4 indicating column.
     */
    public int getQuadrant(int minX, int minY, int maxX, int maxY) {

        if (x < (((maxX - minX) / 2) + minX) && y < (((maxY - minY) / 2)
            + minY)) {
            return 0;
        }
        else if (x >= (((maxX - minX) / 2) + minX) && y < (((maxY - minY) / 2)
            + minY)) {
            return 1;
        }
        else if (x < (((maxX - minX) / 2) + minX) && y >= (((maxY - minY) / 2)
            + minY)) {
            return 2;
        }
        else {
            return 3;
        }
    }


    /**
     * Gets quadrant but also checks if point is valid.
     * 
     * @param minX
     *            minimum X of current quadrant.
     * @param minY
     *            min Y of current quadrant.
     * @param maxX
     *            max X of current quadrant.
     * @param maxY
     *            of current Quadrant.
     * 
     * @return the quadrant the point is in or -1 if it is not in any.
     */
    public int getQuadrantAndCheck(int minX, int minY, int maxX, int maxY) {
        if (!validPoint(minX, minY, maxX, maxY)) {
            return -1;
        }
        return getQuadrant(minX, minY, maxX, maxY);
    }


    /**
     * Determines whether point is intersecting rectangle.
     * 
     * @param minX
     *            minimum x of rectangle.
     * @param minY
     *            minimum y coordinate of rectangle.
     * @param maxX
     *            maximum x
     * @param maxY
     *            maximum y
     * @return true if intersecting.
     */
    public boolean intersecting(int minX, int minY, int maxX, int maxY) {

        return x >= minX && x <= maxX && y >= minY && y <= maxY;
    }


    /**
     * X coordinate getter.
     * 
     * @return Returns X coordinate.
     */
    public int getX() {
        return this.x;
    }


    /**
     * Y coordinate getter.
     * 
     * @return Returns Y coordinate.
     */
    public int getY() {
        return this.y;
    }


    /**
     * Checks if two Points are exactly the same.
     * 
     * @param rec
     *            Point to check with.
     * @return Returns true if they are the exact same.
     */
    public boolean areSame(Point rec) {

        return dimensionCheck(rec.getX(), rec.getY());

    }

}
