
/**
 * Quad tree data structure designed for more spatial searches.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 *
 */
public class QuadTree {
    private int size;
    private Node root;

    private int minX;
    private int minY;
    private int maxX;
    private int maxY;
    private int level;

    private Point removedPoint;

    /**
     * QuadTree constructor.
     * 
     * @param x
     *            minimum x coordinate of tree.
     * @param y
     *            minimum y coordinate of tree.
     * @param width
     *            Width of tree.
     * @param height
     *            Height of tree.
     */
    public QuadTree(int x, int y, int width, int height) {
        this.size = 0;

        this.minX = x;
        this.minY = y;
        this.maxX = x + width;
        this.maxY = y + height;
        this.root = new FlyNode(0, 0, 1024, 1024);

        this.level = 0;
        removedPoint = null;

    }


    /**
     * Inserts node into tree.
     * 
     * @param point
     *            Point to inser.
     * @return returns root.
     */
    public Node insert(Point point) {
        size++;

        root = root.insert(point, minX, minY, maxX, maxY);

        return root;

    }


    /**
     * Print out elements of quadtree in preorder traversal.
     */
    public void dump() {

        System.out.println("QuadTree dump: ");
        System.out.println(root.toString());
        System.out.println(countNodes() + " quadtree nodes printed");

    }


    /**
     * Removes point from quad tree.
     * 
     * @param x
     *            X coordinate of point
     * @param y
     *            Y coordinate of point.
     * @return root.
     */
    public Node remove(int x, int y) {
        root = root.remove(x, y, minX, minY, maxX, maxY);
        return root;

    }


    /**
     * Returns array of all duplicates.
     * 
     * @return number of duplicates.
     */
    public int duplicates() {
        return root.duplicates();
    }


    /**
     * Searches for point in tree.
     * 
     * @param x X value of point.
     * @param y Y value of point.
     * @return String of point.
     */
    public String search(int x, int y) {
        return root.search(x, y);
    }


    /**
     * Quad Tree's size getter.
     * 
     * @return Return int for size;
     */
    public int getSize() {
        return size;
    }


    /**
     * Counts nodes in tree.
     * 
     * @return number of nodes.
     */
    public int countNodes() {
        return root.countNodes();
    }


    /**
     * Searches specified region for points.
     * 
     * @param x
     *            x coordinate of region.
     * @param y
     *            y coordinate of region.
     * @param w
     *            width of region.
     * @param h
     *            height of region.
     * @return number of points in region.
     */
    public int regionSearch(int x, int y, int w, int h) {
        return root.regionSearch(x, y, w, h);
    }


    /**
     * Adds all points in tree to provided skiplist.
     * 
     * @param skip
     *            SkipList.
     */
    public void addToSkip(SkipList<String, Point> skip) {
        root.addToSkip(skip);
    }

}
