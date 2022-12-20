
import student.TestCase;

/**
 * Test class for leaf nodes.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 *
 */
public class LeafNodeTest extends TestCase {

    private LeafNode leaf;
    private Point first;

    /**
     * Sets up test class.
     */
    public void setUp() {
        first = new Point("first", 10, 10);
        leaf = new LeafNode(first, 0, 0, 100, 100);
    }

    /**
     * Tests insert method.
     */
    public void testInsert() {
        leaf.insert(first, 0, 0, 100, 100);
        // assertEquals(leaf.toString(), first.toString());
        assertEquals(leaf.getPoints()[0].toString(), first.toString());
        Point second = new Point("second", 10, 10);
        Point third = new Point("third", 10, 10);
        Point fourth = new Point("fourth", 10, 10);
        Point fifth = new Point("fifth", 10, 10);

        leaf.insert(second, 0, 0, 100, 100);
        leaf.insert(third, 0, 0, 100, 100);
        leaf.insert(fourth, 0, 0, 100, 100);
        leaf.insert(fifth, 0, 0, 100, 100);
        assertEquals(leaf.getPoints().length, 9);
    }


    /**
     * Tests duplicates and makes sure they don't add more than once.
     */
    public void testDuplicates() {
        Point second = new Point("second", 10, 10);

        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(second, 0, 0, 1024, 1024);
        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(second, 0, 0, 1024, 1024);
        leaf.insert(second, 0, 0, 1024, 1024);
        leaf.insert(first, 0, 0, 1024, 1024);
        assertEquals(leaf.duplicates(), 1);

    }

    /**
     * Tests split method.
     */
    public void testSplit() {
        Point second = new Point("second", 0, 5);
        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(second, 0, 0, 1024, 1024);
        assertFalse(leaf.split(leaf.getPoints()));
        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(first, 0, 0, 1024, 1024);
        assertTrue(leaf.split(leaf.getPoints()));

    }

    /**
     * Tests split method some more.
     */
    public void testSplitTwo() {
        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(first, 0, 0, 1024, 1024);
        leaf.insert(first, 0, 0, 1024, 1024);
        Point second = new Point(" ", 11, 10);
        Point third = new Point(" ", 10, 11);

        assertTrue(leaf.insert(second, 0, 0, 1024, 1024).isInternal());
        leaf.remove(11, 10, 0, 0, 1024, 1024);
        assertTrue(leaf.insert(third, 0, 0, 1024, 1024).isInternal());

    }

    /**
     * Test add method.
     */
    public void testAdd() {
        Point one = new Point("one", 0, 5);
        Point three = new Point("three", 0, 4);

        LeafNode newLeaf = new LeafNode(new Point("empty", 0, 0), 0, 0, 1024,
            1024);
        newLeaf.insert(one, 0, 0, 1024, 1024);
        newLeaf.insert(one, 0, 0, 1024, 1024);
        newLeaf.insert(three, 0, 0, 1024, 1024);
        newLeaf.insert(three, 0, 0, 1024, 1024);
        newLeaf.remove(0, 5, 0, 0, 1024, 1024);
        newLeaf.remove(0, 4, 0, 0, 1024, 1024);
        assertNull(newLeaf.getPoints()[0]);
        assertNotNull(newLeaf.getPoints()[1]);
        assertNull(newLeaf.getPoints()[2]);

        Point[] points = new Point[4];

        Point[] bucket = new Point[4];
        bucket = newLeaf.add(points);

        assertEquals(bucket[0].toString(), one.toString());
        assertEquals(bucket[1].toString(), three.toString());

    }

    /**
     * More test methods for split.
     */
    public void testSplitThree() {
        LeafNode newLeaf = new LeafNode(new Point("empty", 0, 0), 0, 0, 1024,
            1024);
        Point[] bucket = new Point[10];
        Point one = new Point("p1", 5, 5);
        Point two = new Point("p2", 5, 6);
        bucket[0] = two;
        bucket[1] = two;
        bucket[2] = two;
        bucket[3] = one;
        bucket[6] = two;
        newLeaf.setSize(5);
        assertTrue(newLeaf.split(bucket));
        
    }
    public void testRemoveAlot() {
        
        
    }
}
