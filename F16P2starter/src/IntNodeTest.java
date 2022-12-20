
import student.TestCase;

/**
 * Test class for IntNode
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 *
 */
public class IntNodeTest extends TestCase {

    private IntNode node;
    private LeafNode leaf;
    private Point northWest;
    private Point northEast;
    private Point southWest;
    private Point southEast;

    /**
     * Sets up test class.
     */
    public void setUp() {
        leaf = new LeafNode(new Point("", 10, 10), 0, 0, 100, 100);
        node = new IntNode(0, 0, 100, 100);
        northWest = new Point("NW", 10, 10);
        northEast = new Point("NE", 60, 10);
        southWest = new Point("SW", 10, 60);
        southEast = new Point("SE", 60, 60);
    }


    /**
     * Tests getPosition correctly assigns leaf to child array.
     */
    public void testInsert() {
        node.insert(northWest, 0, 0, 100, 100);
        assertEquals(node.getChildren()[0].getPoint().toString(), northWest
            .toString());
        node.insert(northEast, 0, 0, 100, 100);
        assertEquals(node.getChildren()[1].getPoint().toString(), northEast
            .toString());

        node.insert(southWest, 0, 0, 100, 100);
        assertEquals(node.getChildren()[2].getPoint().toString(), southWest
            .toString());
        node.insert(southEast, 0, 0, 100, 100);
        assertEquals(node.getChildren()[3].getPoint().toString(), southEast
            .toString());
    }





    /**
     * Tests remove method and ensures that branches merge when they are
     * supposed to.
     */
    public void testRemove() {
        node.insert(northWest, 0, 0, 100, 100);

        node.insert(southWest, 0, 0, 100, 100);
        node.insert(northEast, 0, 0, 100, 100);
        node.insert(southEast, 0, 0, 100, 100);

        node.remove(10, 60, 0, 0, 100, 100);

        System.out.println(node.merge().toString());

    }

    /**
     * Test merge method.
     */
    public void testMerge() {
        node.insert(northWest, 0, 0, 100, 100);
        node.insert(northWest, 0, 0, 100, 100);

        node.insert(northEast, 0, 0, 100, 100);
        node.insert(southEast, 0, 0, 100, 100);
        node.remove(10, 60, 0, 0, 100, 100);
        System.out.println(node.toString());

    }


    /**
     * Tests merge for when there are 3 empty leaf nodes in an internal node.
     */
    public void testmerge() {
        node.insert(northWest, 0, 0, 100, 100);
        node.insert(northWest, 0, 0, 100, 100);

        node.insert(southWest, 0, 0, 100, 100);

        node.insert(southEast, 0, 0, 100, 100);
        node.insert(northEast, 0, 0, 100, 100);

        System.out.println(node.toString());

    }


    /**
     * Tests merge for when there are less than 4 points split across multiple
     * leaf nodes.
     */
    public void testMerge2() {
        node.insert(northWest, 0, 0, 100, 100);
        node.insert(northEast, 0, 0, 100, 100);
        node.insert(southWest, 0, 0, 100, 100);
        node.insert(southEast, 0, 0, 100, 100);
        System.out.println(node.remove(10, 60, 0, 0, 100, 100));
        // assertTrue(node.merge());

    }

    /**
     * Test merge method.
     */
    public void testMerge3() {
        Point one = new Point("one", 0, 0);
        Point two = new Point("two", 0, 0);
        Point three = new Point("three", 1000, 1000);
        Point four = new Point("four", 1000, 1000);
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        myTree.insert(one);
        myTree.insert(two);
        myTree.insert(three);
        myTree.insert(four);
        myTree.dump();
        myTree.remove(1000, 1000);
        myTree.dump();
    }

    /**
     * Test spacing method.
     */
    public void testSpacing() {
        IntNode node1 = new IntNode(0, 0, 1024, 1024);
        IntNode node2 = new IntNode(0, 0, 512, 512);
        IntNode node3 = new IntNode(0, 0, 256, 256);
        System.out.println(node1.getSpacing());
        System.out.println(node2.getSpacing());
        System.out.println(node3.getSpacing());
    }

}
