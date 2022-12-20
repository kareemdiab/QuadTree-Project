
/**
 * The FlyNode class to replace null values with empty nodes instead.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 *
 */
public class FlyNode implements Node {
    // No data, just some methods...

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int width;

    /**
     * FlyNode constructor. Nothing needed!
     */
    public FlyNode() {

    }


    /**
     * FlyNode constructor purely for toString method.
     * 
     * @param minX min x value of node.
     * @param minY min y value of node.
     * @param maxX max x value of node.
     * @param maxY max y value of node.
     */
    public FlyNode(int minX, int minY, int maxX, int maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
        this.width = maxX - minX;

    }


    /**
     * Creates new LeafNode with the point.
     * 
     * @param newPoint
     *            point to create leaf node with.
     * @param lowerX
     *            minimum X value of quadrant.
     * @param lowerY
     *            minimum Y value of quadrant.
     * @param upperX
     *            maximum X value of quadrant.
     * @param upperY
     *            maximum Y value of quadrant.
     * @return Returns the leafNode.
     */
    public Node insert(
        Point newPoint,
        int lowerX,
        int lowerY,
        int upperX,
        int upperY) {
        LeafNode node = new LeafNode(newPoint, lowerX, lowerY, upperX, upperY);
        return node.insert(newPoint, lowerX, lowerY, upperX, upperY); // growing
                                                                      // up!
    }


    /**
     * For merge method.
     * 
     * @param points
     *            array of points to return.
     * @return returns array of points.
     */
    public Point[] add(Point[] points) {
        return points;
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
        return "";
    }


    /**
     * Removes specified point from specified quadrant.
     * 
     * @param x
     *            X coordinate of point
     * @param y
     *            Y coordinate of point
     * @param lowerX
     *            minimum X value of quadrant.
     * @param lowerY
     *            minimum Y value of quadrant.
     * @param upperX
     *            maximum X value of quadrant.
     * @param upperY
     *            maximum Y value of quadrant.
     * @return returns either leaf, fly or internal node.
     */
    public Node remove(
        int x,
        int y,
        int lowerX,
        int lowerY,
        int upperX,
        int upperY) {
        return this;
    }


    /**
     * Merges in the case that the updated tree shouldn't have decomposed.
     * 
     * @return returns current node.
     */
    public Node merge() {
        return this;
    }


    /**
     * Returns string representation.
     * 
     * @return string.
     */
    public String toString() {
        return getSpacing() + "Node at " + minX + ", " + minY + ", " + width
            + ": Empty";

    }


    /**
     * Adds all points in tree to provided skiplist.
     * 
     * @param skip
     *            SkipList.
     */
    public void addToSkip(SkipList<String, Point> skip) {
        // nothing
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
     * Checks whether node is leaf or not.
     * 
     * @return true if leaf.
     */
    public boolean isLeaf() {
        return false;
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
     * Returns point stored in node.
     * 
     * @return point in node.
     */
    public Point getPoint() {
        return new Point("nothing", 0, 0);
    }


    /**
     * Gets the array of points in the node.
     * 
     * @return array of points.
     */
    public Point[] getPoints() {
        return new Point[0];
    }


    /**
     * Checks if node is flynode.
     * 
     * @return true if flynode.
     */
    public boolean isFly() {
        return true;
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
     * Counts nodes in tree.
     * 
     * @return number of nodes.
     */
    public int countNodes() {

        return 1;
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
        return 1;
    }


    /**
     * Returns number of duplicates.
     * 
     * @return number of duplicates.
     */
    public int duplicates() {
        return 0;
    }

}
