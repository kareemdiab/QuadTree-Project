
/**
 * Class to represent internal nodes in the quad tree.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 */
public class IntNode implements Node {

    private int minimumX;
    private int minimumY;
    private int maximumX;
    private int maximumY;
    private int width;
    private int height;

    private Node[] children;
    /**
     * FlyNode to be used instead of null values.
     */
    public static final FlyNode THE_FLY = new FlyNode();
    // Notice there is just one 'THE_FLY' for entire IntNode class
    // Since all Flys look identical and have no data,
    // they can actually all just point to the same FlyNode

    /**
     * Number of children in the internal node.
     */
    static final int NUM_CHILDREN = 4;

    /**
     * Set each child to THE_FLY.
     * 
     * @param lowerX
     *            minimum X value of quadrant.
     * @param lowerY
     *            minimum Y value of quadrant.
     * @param upperX
     *            maximum X value of quadrant.
     * @param upperY
     *            maximum Y value of quadrant.
     */
    public IntNode(int lowerX, int lowerY, int upperX, int upperY) {
        this.children = new Node[NUM_CHILDREN];
        this.minimumX = lowerX;
        this.minimumY = lowerY;
        this.maximumX = upperX;
        this.maximumY = upperY;
        this.width = maximumX - minimumX;
        this.height = maximumY - minimumY;

        children[0] = new FlyNode(minimumX, minimumY, (width / 2) + minimumX,
            (height / 2) + minimumY);
        children[1] = new FlyNode((width / 2) + minimumX, minimumY, maximumX,
            (height / 2) + minimumY);
        children[2] = new FlyNode(minimumX, (height / 2) + minimumY, (width / 2)
            + minimumX, maximumY);
        children[3] = new FlyNode((width / 2) + minimumX, (height / 2)
            + minimumY, maximumX, maximumY);

        // Not a single null pointer here, good!

        // this.insert(leaf.getPoint());

    }


    /**
     * Inserts point into proper leaf Node.
     * 
     * @param newPoint
     *            Point to be inserted with.
     * @param minX
     *            minimum x coordinate of quadrant to be inserted into.
     * @param minY
     *            minimum y coordinate of quadrant to be inserted into.
     * @param maxX
     *            maximum x coordinate of quadrant to be inserted into.
     * @param maxY
     *            maximum y coordinate of quadrant to be inserted into.
     * @return Returns this internal node.
     */
    public Node insert(Point newPoint, int minX, int minY, int maxX, int maxY) {
        int pos = newPoint.getQuadrant(minX, minY, maxX, maxY);
        // int width = maxX - minX;
        // int height = maxY - minY;

        if (pos == 0) {
            // children[pos] = new LeafNode(newPoint, minX, minY, (maxX - minX)
            // / 2, (maxY - minY) / 2);
            children[pos] = children[pos].insert(newPoint, minX, minY, (width
                / 2) + minX, (height / 2) + minY);

        }
        else if (pos == 1) {
            // children[pos] = new LeafNode(newPoint, (maxX - minX) / 2, minY,
            // maxX, (maxY - minY) / 2);
            children[pos] = children[pos].insert(newPoint, (width / 2) + minX,
                minY, maxX, (height / 2) + minY);

        }
        else if (pos == 2) {
            // children[pos] = new LeafNode(newPoint, minX, (maxY - minY) / 2,
            // (maxX - minX) / 2, maxY);
            children[pos] = children[pos].insert(newPoint, minX, (height / 2)
                + minY, (width / 2) + minX, maxY);

        }
        else if (pos == 3) {
            // children[pos] = new LeafNode(newPoint, (maxX - minX) / 2, (maxY -
            // minY) / 2, maxX, maxY);
            children[pos] = children[pos].insert(newPoint, (width / 2) + minX,
                (height / 2) + minY, maxX, maxY);

        }
        return this;
    }


    /**
     * ToString method for internal nodes.
     * 
     * @return Returns string representation of node.
     */
    @Override
    public String toString() {

        String result = getSpacing() + "Node at " + minimumX + ", " + minimumY
            + ", " + (maximumX - minimumX) + ": Internal";

        for (int i = 0; i < 4; i++) {

            result += "\n" + children[i].toString();

        }

        return result;
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
     * Removes point specified by x and y coordinates, returns the point.
     * 
     * @param x
     *            X value of point.
     * @param y
     *            Y value of point
     * @param minX
     *            minimum x of quadrant.
     * @param minY
     *            minimum y of quadrant.
     * @param maxX
     *            maximum x of quadrant.
     * @param maxY
     *            max y of quadrant.
     * @return Returns current node or merged leaf node.
     */
    public Node remove(int x, int y, int minX, int minY, int maxX, int maxY) {
        // int width = maxX - minX;
        // int height = maxY - minY;
        Point temp = new Point(" ", x, y);
        int pos = temp.getQuadrantAndCheck(minX, minY, maxX, maxY);

        if (pos == 0) {
            children[pos] = children[pos].remove(x, y, minX, minY, (width / 2)
                + minX, (height / 2) + minY);

            return merge();
        }
        else if (pos == 1) {
            children[pos] = children[pos].remove(x, y, (width / 2) + minX, minY,
                maxX, (height / 2) + minY);
            return merge();
        }
        else if (pos == 2) {
            children[pos] = children[pos].remove(x, y, minX, (height / 2)
                + minY, (width / 2) + minX, maxY);
            return merge();
        }
        else if (pos == 3) {
            children[pos] = children[pos].remove(x, y, (width / 2) + minX,
                (height / 2) + minY, maxX, maxY);
            return merge();
        }

        return this;

    }


    /**
     * Determines whether a merge should occur or not.
     * 
     * @return returns node result of merge.
     */
    public Node merge() {

        int size = 0;

        LeafNode lNode = new LeafNode(new Point("", 0, 0), minimumX, minimumY,
            maximumX, maximumY);
        Point[] points = new Point[1];
        for (int i = 0; i < 4; i++) {
            if (children[i].isInternal()) {
                return this;
            }
        }

        points = add(points);

        while (points[size] != null) {
            size++;
        }

        lNode.setSize(size);
        // if (!lNode.split(points)) {
        if (size < 4 || allSame(points, size)) {
            lNode.setSize(0);
            for (int i = 0; i < size; i++) {

                lNode.insert(points[i], minimumX, minimumY, maximumX, maximumY);

            }

            return lNode;
        }
        else {

            
            return this;
        }

    }


    /**
     * Returns true if all the points are the same.
     * 
     * @param points
     *            Points array to be checked.
     * @param size number of points in array
     * @return True if all of them are the same.
     */
    public boolean allSame(Point[] points, int size) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {

                if (points[i] != points[j]) {
                    return false;
                }

            }
        }
        return true;
    }


    /**
     * Adds points to array as helper method for merge.
     * 
     * @param points
     *            to add to.
     * @return array of points.
     */
    public Point[] add(Point[] points) {
        for (int i = 0; i < 4; i++) {

            points = children[i].add(points);

        }
        return points;
    }


    /**
     * Counts nodes in tree.
     * 
     * @return number of nodes.
     */
    public int countNodes() {
        int count = 0;
        for (int i = 0; i < 4; i++) {
            count += children[i].countNodes();

        }
        return 1 + count;
    }


    /**
     * Region search for IntNode.
     * 
     * @param x
     *            minimum x value of region.
     * @param y
     *            min y of region.
     * @param w
     *            width of region.
     * @param h
     *            height of region.
     * @return number of points in region.
     */
    public int regionSearch(int x, int y, int w, int h) {
        int count = 1;
        if (overlap(x, y, x + w, y + h, minimumX, minimumY, (width / 2)
            + minimumX, (height / 2) + minimumY)) {
            count += children[0].regionSearch(x, y, w, h);

        }
        if (overlap(x, y, x + w, y + h, (width / 2) + minimumX, minimumY,
            maximumX, (height / 2) + minimumY)) {
            count += children[1].regionSearch(x, y, w, h);
        }
        if (overlap(x, y, x + w, y + h, minimumX, (height / 2) + minimumY,
            (width / 2) + minimumX, maximumY)) {
            count += children[2].regionSearch(x, y, w, h);
        }
        if (overlap(x, y, x + w, y + h, (width / 2) + minimumX, (height / 2)
            + minimumY, maximumX, maximumY)) {
            count += children[3].regionSearch(x, y, w, h);
        }
        return count;

    }


    /**
     * Determines if there is overlap between two rectangles.
     * 
     * @param minX1
     *            min x of rec 1.
     * @param minY1
     *            min y of rec 1.
     * @param maxX1
     *            max x of rec 1.
     * @param maxY1
     *            max y of rec 1.
     * @param minX2
     *            min x of rec 2.
     * @param minY2
     *            min y of rec 2.
     * @param maxX2
     *            max x of rec 2.
     * @param maxY2
     *            max y of rec 2.
     * @return true if there is overlap.
     */
    public boolean overlap(
        int minX1,
        int minY1,
        int maxX1,
        int maxY1,
        int minX2,
        int minY2,
        int maxX2,
        int maxY2) {
        return maxX1 > minX2 && maxY1 > minY2 && maxX2 > minX1 && maxY2 > minY1;

    }


    /**
     * Searches for specified point.
     * 
     * @param x
     *            of point
     * @param y
     *            of point
     * @return string of point.
     */
    public String search(int x, int y) {
        String result = "";
        Point temp = new Point("", x, y);
        int pos = temp.getQuadrantAndCheck(minimumX, minimumY, maximumX,
            maximumY);
        if (pos == 0) {
            result += children[pos].search(x, y);
        }
        else if (pos == 1) {
            result += children[pos].search(x, y);
        }
        else if (pos == 2) {
            result += children[pos].search(x, y);
        }
        else if (pos == 3) {
            result += children[pos].search(x, y);
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
        for (int i = 0; i < 4; i++) {
            children[i].addToSkip(skip);
        }

    }


    /**
     * Gets duplicates.
     * 
     * @return number of duplicates.
     */
    public int duplicates() {

        return children[0].duplicates() + children[1].duplicates() + children[2]
            .duplicates() + children[3].duplicates();

    }


    /**
     * Counts points in node.
     * 
     * @return number of points.
     */
    public int countPoints() {
        return 0;
    }


    /**
     * Checks if node is leaf.
     * 
     * @return true if leaf.
     */
    public boolean isLeaf() {
        return false;
    }


    /**
     * Gets children array of node.
     * 
     * @return children array.
     */
    public Node[] getChildren() {
        return children;
    }


    /**
     * Checks if node is internal.
     * 
     * @return true if internal.
     */
    public boolean isInternal() {
        return true;
    }


    /**
     * Gets point in node.
     * 
     * @return point.
     */
    public Point getPoint() {
        return new Point("nothing", 0, 0);
    }


    /**
     * Gets array of points in node.
     * 
     * @return array of points.
     */
    public Point[] getPoints() {
        return new Point[0];
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
