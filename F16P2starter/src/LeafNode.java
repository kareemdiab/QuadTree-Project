
/**
 * LeafNode class to implement node and represent outer nodes.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 *
 */

public class LeafNode implements Node {
    private Point point;
    private Point[] pointBucket;

    private int size;

    private int minimumX;
    private int minimumY;
    private int maximumX;
    private int maximumY;

    private int width;
    private int height;

    /**
     * Constructor for leafNode, store newPoint value in point.
     * 
     * @param newPoint
     *            Point to be stored
     * @param lowerX
     *            minimum X value of quadrant.
     * @param lowerY
     *            minimum Y value of quadrant.
     * @param upperX
     *            maximum X value of quadrant.
     * @param upperY
     *            maximum Y value of quadrant.
     */
    public LeafNode(
        Point newPoint,
        int lowerX,
        int lowerY,
        int upperX,
        int upperY) {
        point = newPoint;

        this.minimumX = lowerX;
        this.minimumY = lowerY;
        this.maximumX = upperX;
        this.maximumY = upperY;

        this.size = 0;
        this.width = maximumX - minimumX;
        this.height = maximumY - minimumY;
        pointBucket = new Point[4];

    }


    /**
     * Inserts point into the node, creates internal node.
     * 
     * @param newPoint
     *            point to be inserted.
     * @param minX
     *            minimum X value of quadrant.
     * @param minY
     *            minimum Y value of quadrant.
     * @param maxX
     *            maximum X value of quadrant.
     * @param maxY
     *            maximum Y value of quadrant.
     * @return Returns the resulting Node
     */
    public Node insert(Point newPoint, int minX, int minY, int maxX, int maxY) {
        if (newPoint == null || !newPoint.validPoint(minX, minY, maxX, maxY)) {
            return this;
        }
        if (size == pointBucket.length) {
            expandCapacity();
        }
        pointBucket[size] = newPoint;
        size++;
        if (split(pointBucket)) {
            if (width == 1 || height == 1) {
                return this;
            }
            IntNode iNode = new IntNode(minX, minY, maxX, maxY);

            for (int i = 0; i < size; i++) {
                iNode.insert(pointBucket[i], minX, minY, maxX, maxY);
            }

            return iNode;
        }

        return this;
        // growing up!
    }


    /**
     * adds to array of points to help with merge method.
     * 
     * @param points
     *            to be added to.
     * @return array of points.
     */
    public Point[] add(Point[] points) {
        points = expandArr(points);
        for (int i = 0; i < pointBucket.length; i++) {
            if (pointBucket[i] != null) {
                int j = 0;
                while (points[j] != null) {
                    j++;
                }
                points[j] = pointBucket[i];

            }

        }
        return points;
    }


    /**
     * Expands array holding all the points for the merge method.
     * 
     * @param points Array to be expanded
     * @return returns new array of points.
     */
    private Point[] expandArr(Point[] points) {
        Point[] temp = new Point[points.length + size];
        for (int i = 0; i < points.length; i++) {
            temp[i] = points[i];
        }
        return temp;
    }


    /**
     * Returns whether or not all point values in the point array are the same.
     * 
     * @param points
     *            array of points to be split or not.
     * @return True if there is at least one unique point.
     */
    public boolean split(Point[] points) {
        if (size > 3) {
            if (unique(points)) {
                return true;
            }
        }
        return false;
    }


    /**
     * Removes specified point from specified quadrant.
     * 
     * @param x
     *            X coordinate of point
     * @param y
     *            Y coordinate of point
     * @param minX
     *            minimum X value of quadrant.
     * @param minY
     *            minimum Y value of quadrant.
     * @param maxX
     *            maximum X value of quadrant.
     * @param maxY
     *            maximum Y value of quadrant.
     * @return returns either leaf, fly or internal node.
     */
    public Node remove(int x, int y, int minX, int minY, int maxX, int maxY) {
        for (int i = 0; i < pointBucket.length; i++) {

            if (pointBucket[i] != null && pointBucket[i].dimensionCheck(x, y)) {

                System.out.println("Point removed: " + pointBucket[i]
                    .toString());

                pointBucket[i] = null;
                size--;
                // resetArr();
                if (size == 0) {
                    return new FlyNode(minX, minY, maxX, maxY);
                }
                return this;

            }
        }
        return this;

    }


    /**
     * Resets pointBucket array after removal.
     */
    public void resetArr() {
        Point[] temp = new Point[size + 1];
        int i = 0;
        while (pointBucket[i] == null) {
            i++;
        }
        for (int j = 0; j < size; j++) {
            temp[j] = pointBucket[i];
            i++;
        }
        pointBucket = temp;
    }


    /**
     * Searches for point in specified region.
     * 
     * @param x
     *            minimum x for region.
     * @param y
     *            minimum y for region.
     * @param w
     *            width of region.
     * @param h
     *            height of region.
     * @return number of points in the region.
     */
    public int regionSearch(int x, int y, int w, int h) {

        for (int i = size - 1; i >= 0; i--) {

            if (pointBucket[i] != null && pointBucket[i].intersecting(x, y, x
                + w, y + h)) {

                System.out.println("Point found: " + pointBucket[i].toString());
            }
        }
        return 1;
    }


    /**
     * Searches for point with x and y coordinates.
     * 
     * @param x
     *            X coordinate to be sarched for.
     * @param y
     *            Y coordinate to be searched for.
     * @return String returns search result as string.
     */
    public String search(int x, int y) {
        for (int i = 0; i < size; i++) {
            if (pointBucket[i] != null && pointBucket[i].dimensionCheck(x, y)) {
                return pointBucket[i].getName();
            }
        }
        return "";
    }


    /**
     * Gets all duplicates and displays them.
     * 
     * @return number of duplicates.
     */
    public int duplicates() {
        Point[] duplicates = new Point[size];

        for (int i = 0; i < size; i++) {
            for (int j = i; j < size; j++) {
                if (pointBucket[i] != null && pointBucket[i].areSame(
                    pointBucket[j]) && i != j && original(duplicates,
                        pointBucket[j])) {
                    duplicates[i] = pointBucket[j];
                }
            }
        }
        return displayDuplicates(duplicates);

    }


    /**
     * Double checks that duplicate has not already been listed.
     * 
     * @param points array to check duplicates with.
     * @param p duplicate to check.
     * 
     * @return True
     */
    private boolean original(Point[] points, Point p) {
        for (int i = 0; i < points.length; i++) {

            if (points[i] != null) {

                if (points[i].areSame(p)) {

                    return false;
                }

            }
        }
        return true;
    }


    /**
     * Checks whether specified point is at least unique to one other point in
     * the array.
     * 
     * @param points array to check if unique.
     * @return true if unique.
     */
    private boolean unique(Point[] points) {
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < points.length; j++) {
                if (points[i] != null && points[j] != null) {

                    if (!points[i].areSame(points[j]) && i != j) {

                        return true;
                    }

                }

            }

        }
        return false;
    }


    /**
     * Prints out duplicates to the console.
     * @param duplicates array with duplicates.
     * @return number of duplicates.
     */
    private int displayDuplicates(Point[] duplicates) {
        int count = 0;
        for (int i = 0; i < duplicates.length; i++) {
            if (duplicates[i] != null) {
                count++;
                System.out.println("(" + duplicates[i].getX() + ", "
                    + duplicates[i].getY() + ")");
            }

        }
        return count;
    }


    /**
     * Expands capacity of arrays when they fill up.
     * 
     */
    public void expandCapacity() {

        if (size == pointBucket.length) {
            Point[] newArr = new Point[size + 5];
            for (int i = 0; i < pointBucket.length; i++) {
                newArr[i] = pointBucket[i];
            }

            pointBucket = newArr;
        }

    }


    /**
     * Prints out toString for all points in leaf.
     * 
     * @return Returns string representation of all the points in the leaf.
     */
    @Override
    public String toString() {
        resetArr();
        if (size == 0) {
            FlyNode fly = new FlyNode(minimumX, minimumY, maximumX, maximumY);
            return fly.toString();
        }
        String result = getSpacing() + "Node at " + minimumX + ", " + minimumY
            + ", " + width + ":";

        for (int i = 0; i < pointBucket.length; i++) {
            if (pointBucket[i] != null) {
                result += "\n" + getSpacing() + pointBucket[i].toString();

            }

        }

        return result;
    }


    /**
     * Adds all points in tree to provided skiplist.
     * 
     * @param skip
     *            SkipList.
     */
    public void addToSkip(SkipList<String, Point> skip) {
        for (int i = 0; i < pointBucket.length; i++) {
            if (pointBucket[i] != null) {
                KVPair<String, Point> kv = new KVPair<String, Point>(
                    pointBucket[i].getName(), pointBucket[i]);
                skip.insert(kv);
            }
        }
    }


    /**
     * Determines spacing for output depending on depth of node.
     * 
     * @return The amount of spacing required.
     */
    public String getSpacing() {
        String spacing = "";
        int depth = 0;
        if (width == 0) {
            return "";
        }
        int currentWidth = width;
        while (currentWidth < 1024) {
            currentWidth *= 2;
            depth++;
        }

        for (int i = 0; i < depth; i++) {
            spacing += "  ";
        }
        return spacing;

    }


    /**
     * Sets the size of leaf node.
     * 
     * @param n
     *            new size.
     */
    public void setSize(int n) {
        this.size = n;
    }


    /**
     * Return number of points in leaf node.
     * 
     * @return size
     */
    public int countPoints() {
        return size;
    }


    /**
     * Return points in leaf node.
     * 
     * @return pointBucket
     */
    public Point[] getPoints() {
        return pointBucket;
    }


    /**
     * Point getter.
     * 
     * @return Returns point value
     */
    public Point getPoint() {
        return point;
    }


    /**
     * Counts nodes in tree.
     * 
     * @return number of nodes.
     */
    public int countNodes() {
        return 1;
    }


    /**
     * Checks if node is leaf.
     * 
     * @return true if leaf.
     */
    public boolean isLeaf() {
        return true;
    }


    /**
     * Returns array of children.
     * 
     * @return array.
     */
    public Node[] getChildren() {
        return null;
    }


    /**
     * Checks if node is internal.
     * 
     * @return true if internal.
     */
    public boolean isInternal() {
        return false;
    }


    /**
     * Checks if node is fly node.
     * 
     * @return true if fly.
     */
    public boolean isFly() {
        return false;
    }

}
