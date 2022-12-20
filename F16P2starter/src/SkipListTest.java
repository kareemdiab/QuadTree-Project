
import java.util.Iterator;
import student.TestCase;
import student.TestableRandom;

/**
 * Test class for SkipList.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.09.02
 *
 */
public class SkipListTest extends TestCase {

    private SkipList<String, Point> skipper;
    private KVPair<String, Point> apple;
    private KVPair<String, Point> zebra;
    private KVPair<String, Point> cat;
    private KVPair<String, Point> something;

    /**
     * Set up method for tests.
     */
    public void setUp() {
        skipper = new SkipList<String, Point>();
        apple = new KVPair<String, Point>("Apple", new Point("Apple", 1, 2));
        zebra = new KVPair<String, Point>("Zebra", new Point("Zebra", 1, 2));
        cat = new KVPair<String, Point>("Cat", new Point("Cat", 1, 2));
        something = new KVPair<String, Point>("Something", new Point(
            "Something", 1, 2));

    }


    /**
     * Tests insert method for values greater and less than current elements in
     * skiplist.
     */
    public void testInsert() {

        skipper.insert(apple);
        assertEquals(skipper.search("Apple"), apple);

        assertTrue(skipper.insert(zebra));
        assertEquals(skipper.search("Zebra"), zebra);

        assertTrue(skipper.insert(cat));

        assertEquals(skipper.search("Cat"), cat);

        assertTrue(skipper.insert(something));
        assertEquals(skipper.search("Something"), something);
        KVPair<String, Point> first = new KVPair<String, Point>("Aa", new Point(
            "Aa", 5, 5));
        skipper.insert(first);
        assertEquals(skipper.getSize(), 5);
        KVPair<String, Point> second = new KVPair<String, Point>("Ab",
            new Point("Ab", 5, 5));
        skipper.insert(second);
        assertEquals(skipper.getSize(), 6);

    }


    /**
     * Tests the random level method in the skip list with different true false
     * combinations.
     */
    public void testRandomLevel() {
        TestableRandom.setNextBooleans(true, false);
        assertEquals(skipper.randomLevel(), 1);
        TestableRandom.setNextBooleans(false);
        assertEquals(skipper.randomLevel(), 0);

    }


    /**
     * Tests remove method for elements at the front, back and middlel of the
     * skiplist./
     */
    public void testRemove() {
        Iterator<Point> iter = skipper.iterator();
        skipper.remove("nothing");
        assertFalse(iter.hasNext());
        skipper.insert(apple);
        skipper.insert(cat);
        skipper.insert(something);
        skipper.insert(zebra);

        skipper.remove("Cat");

        while (iter.hasNext()) {
            System.out.println(iter.next());
        }

        // assertEquals(skipper.search("Zebra"), zebra);
        assertEquals(skipper.search("Cat"), null);
        skipper.remove("Zebra");
        assertEquals(skipper.search("Zebra"), null);
        skipper.remove("Apple");
        assertEquals(skipper.search("Apple"), null);
        assertEquals(skipper.search("Something"), something);

    }


    /**
     * Tests dump method to ensure everything is being printed out fine.
     */
    public void testDump() {
        skipper.insert(apple);
        skipper.insert(cat);
        skipper.insert(something);
        skipper.insert(zebra);
        assertEquals(skipper.dump(), 4);
    }


    /**
     * Tests skiplist when all nodes are removed.
     */
    public void testRemoveAll() {
        skipper.insert(apple);
        skipper.remove("Apple");

    }


    /**
     * Tests remove method more.
     */
    public void testMoreRemove() {

        Point one = new Point("P1", 4, 4);
        KVPair<String, Point> first = new KVPair<String, Point>("P1", one);
        Point p1 = new Point("p1", 4, 4);
        KVPair<String, Point> sec = new KVPair<String, Point>("p1", p1);
        Point p2 = new Point("p2", 300, 15);
        KVPair<String, Point> third = new KVPair<String, Point>("p1", p2);
        Point p4 = new Point("p4", 700, 4);
        KVPair<String, Point> fourth = new KVPair<String, Point>("p4", p4);
        Point p5 = new Point("p5", 700, 4);
        KVPair<String, Point> fifth = new KVPair<String, Point>("p5", p5);
        Point p6 = new Point("p7", 5, 5);
        KVPair<String, Point> sixth = new KVPair<String, Point>("p7", p6);
        Point p62 = new Point("p6", 6, 6);
        KVPair<String, Point> seventh = new KVPair<String, Point>("p6", p62);
        Point pointy = new Point("pointy", 4, 4);
        KVPair<String, Point> point = new KVPair<String, Point>("pointy",
            pointy);
        skipper.insert(first);
        skipper.insert(sec);
        skipper.insert(third);
        skipper.insert(fourth);
        skipper.insert(fifth);
        skipper.insert(sixth);
        skipper.insert(seventh);
        skipper.insert(point);
        //!!skipper.removeKV(point);

        skipper.dump();

    }
    /**
     * Tests remove method more.
     */
    public void testRemoveThree() {

        Point one = new Point("p1", 4, 4);
        KVPair<String, Point> first = new KVPair<String, Point>("p1", one);
        Point p1 = new Point("p1", 7, 7);
        KVPair<String, Point> sec = new KVPair<String, Point>("p1", p1);
        Point p2 = new Point("p2", 300, 15);
        KVPair<String, Point> third = new KVPair<String, Point>("p2", p2);

  
         
        skipper.insert(first);
        skipper.insert(sec);
        skipper.insert(third);
        System.out.println("FIRST DUMP: ");
        skipper.dump();
        skipper.removeKV(sec);

        System.out.println("SECOND DUMP: ");

        skipper.dump();

    }

}
