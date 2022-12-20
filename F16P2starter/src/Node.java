
/**
 * Interface for flynodes, leafnodes, and internal nodes.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.05
 */
public interface Node {
    // Note the different return type!!! See below...

    /**
     * Creates new LeafNode with the point.
     * 
     * @param newPoint
     *            point to create leaf node with.
     * @param minX
     *            minimum X value of quadrant.
     * @param minY
     *            minimum Y value of quadrant.
     * @param maxX
     *            maximum X value of quadrant.
     * @param maxY
     *            maximum Y value of quadrant.
     * @return Returns the leafNode.
     */
    public Node insert(Point newPoint, int minX, int minY, int maxX, int maxY);


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
    public Node remove(int x, int y, int minX, int minY, int maxX, int maxY);


    /**
     * Searches for point with x and y coordinates.
     * 
     * @param x
     *            X coordinate to be sarched for.
     * @param y
     *            Y coordinate to be searched for.
     * @return String returns search result as string.
     */
    public String search(int x, int y);


    /**
     * Checks whether node is leaf or not.
     * 
     * @return true if leaf.
     */
    public boolean isLeaf();


    /**
     * Returns array of children.
     * 
     * @return array.
     */
    public Node[] getChildren();


    /**
     * Checks if node is internal.
     * 
     * @return true if internal.
     */
    public boolean isInternal();


    /**
     * Returns point stored in node.
     * 
     * @return point in node.
     */
    public Point getPoint();


    /**
     * Gets the array of points in the node.
     * 
     * @return array of points.
     */
    public Point[] getPoints();


    /**
     * Checks if node is flynode.
     * 
     * @return true if flynode.
     */
    public boolean isFly();


    /**
     * Counts points in node.
     * 
     * @return number of points.
     */
    public int countPoints();


    /**
     * Counts nodes in tree.
     * 
     * @return number of nodes.
     */
    public int countNodes();


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
    public int regionSearch(int x, int y, int w, int h);


    /**
     * Returns number of duplicates.
     * 
     * @return number of duplicates.
     */
    public int duplicates();


    /**
     * adds to array of points to help with merge method.
     * 
     * @param points
     *            to be added to.
     * @return array of points.
     */
    public Point[] add(Point[] points);


    /**
     * Adds all points in tree to provided skiplist.
     * 
     * @param skip
     *            SkipList.
     */
    public void addToSkip(SkipList<String, Point> skip);

}
