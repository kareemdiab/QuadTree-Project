
import student.TestCase;

/**
 * Test class for DataBase.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.05
 *
 */
public class DataBaseTest extends TestCase {

    private SkipList<String, Point> skip;
    private QuadTree tree;

    private DataBase dataBase;

    /**
     * Sets up test class.
     */
    public void setUp() {

        skip = new SkipList<String, Point>();
        tree = new QuadTree(0, 0, 1024, 1024);
        dataBase = new DataBase(skip, tree);
        dataBase.insertPoint("p1", 4, 4);
        dataBase.insertPoint("p1", 4, 4);
        dataBase.insertPoint("P1", 4, 4);
        dataBase.insertPoint("p2", 300, 15);
        dataBase.insertPoint("p4", 700, 4);
        dataBase.insertPoint("p5", 700, 4);
        dataBase.insertPoint("pointy", 4, 4);

    }


    /**
     * Tests that dump works properly for both skiplist and quadtree.
     */
    public void testDump() {

        assertEquals(dataBase.dump(), 7);
    }


    /**
     * Ensures insert() rejects bad points and accepts good ones.
     */
    public void testInsert() {
        dataBase.regionSearch(0, 0, 0, 0);
        dataBase.regionSearch(0, 0, -1, 10);
        dataBase.regionSearch(-1, 0, 0, 10);
        dataBase.regionSearch(0, -1, -1, 10);
        dataBase.regionSearch(0, 0, 1050, 100);
        dataBase.regionSearch(0, 0, 0, 1050);
        dataBase.regionSearch(0, 0, 0, -1);
        dataBase.insertPoint(" ", -1, 0);
        dataBase.insertPoint(" ", 0, -1);
        dataBase.insertPoint(" ", 1025, 0);
        dataBase.insertPoint(" ", 0, 1025);
        assertEquals(tree.countNodes(), 9);
 
        dataBase.insertPoint("p1", 1024, 4);
        assertEquals(tree.countNodes(), 9);
  

        tree.dump();
        dataBase.insertPoint("bad1", 1134, 12);
        dataBase.insertPoint("bad2", 3, 3221);
        dataBase.insertPoint("bad3", -3, 2);
        assertEquals(tree.countNodes(), 9);
    }


    /**
     * Ensures nodes are properly removed in both skiplist and quadtree for both
     * removeByCoord and removeByName.
     */
    public void testRemove() {
        // Remove by coordinates

        dataBase.removeByCoord(300, 15);
        tree.dump();
        assertEquals(tree.countNodes(), 9);
        //assertEquals(skip.dump(), 6);
        tree.dump();
        // Reinsert
        dataBase.insertPoint("p2", 300, 15);
        assertEquals(tree.countNodes(), 9);
        

        // Remove by name
        dataBase.removeByName("p2");
        assertEquals(tree.countNodes(), 9);
        //assertEquals(skip.dump(), 6);

        dataBase.insertPoint("p2", 300, 15);
        dataBase.removeByCoord(300, 15);

    }


    /**
     * Tests remove method on duplicates.
     */
    public void testRemoveDuplicates() {
        dataBase.insertPoint("duplicate", 1, 1);
        dataBase.insertPoint("duplicate", 1, 1);
        dataBase.insertPoint("duplicate", 1, 1);
        assertEquals(skip.getSize(), 10);
       
        dataBase.removeByCoord(1, 1);
        //assertEquals(skip.getSize(), 9);
        dataBase.insertPoint("duplicate", 1, 1);
        dataBase.removeByName("duplicate");
        //assertEquals(skip.getSize(), 9);

    }


    /**
     * Tests removals for invalid coordinates.
     */
    public void testInvalidRemovals() {
        assertFalse(dataBase.removeByCoord(-2, 3));
    }


    /**
     * Tests regionSearch with valid and invalid rectangles.
     */
    public void testRegionSearch() {
        assertEquals(dataBase.regionSearch(1, 1, 500, 0), 0);
        assertEquals(dataBase.regionSearch(1, 1, 0, 50), 0);
        assertEquals(dataBase.regionSearch(1, 1, 1024, 1024), 9);

        assertEquals(dataBase.regionSearch(1, 1, 200, 1024), 5);

        QuadTree emptyTree = new QuadTree(0, 0, 1024, 1024);
        DataBase db = new DataBase(new SkipList<String, Point>(), emptyTree);
        assertEquals(db.regionSearch(0, 0, 1020, 1020), 1);

    }


    /**
     * Tests duplicates() when there are none and when there are some.
     */
    public void testDuplicates() {
        assertEquals(dataBase.duplicates(), 2);
        dataBase.removeByCoord(700, 4);
        assertEquals(dataBase.duplicates(), 1);
    }


    /**
     * Tests search method for both the quadtree and the skiplist.
     */
    public void testSearch() {
        assertEquals(dataBase.search("p1"), 2);
        assertEquals(dataBase.search("pointy"), 1);
        assertEquals(dataBase.search("nothing"), 0);
        QuadTree emptyTree = new QuadTree(0, 0, 1024, 1024);
        DataBase db = new DataBase(new SkipList<String, Point>(), emptyTree);
        assertEquals(db.search("nothinn"), 0);

    }
    /**
     * Tests remove methods on skiplist.
     */
    public void testRemoveSkipList() {
        dataBase.insertPoint("p6", 5, 5);
        dataBase.insertPoint("p6", 6, 6);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.removeByCoord(6, 6);
        
        dataBase.removeByName("p6");
        //dataBase.removeByCoord(4, 4);
        dataBase.dump();
    }
    /**
     * Tests remove all nodes.
     */
    public void testRemoveAll() {
        SkipList<String, Point> skipper = new SkipList<String, Point>();
        tree = new QuadTree(0, 0, 1024, 1024);
        dataBase = new DataBase(skipper, tree);
  
      
        dataBase.insertPoint("P1", 4, 4);
        dataBase.insertPoint("p1", 4, 4);
        dataBase.insertPoint("p2", 300, 15);
        dataBase.insertPoint("p4", 700, 4);
        dataBase.insertPoint("p5", 700, 4);
        dataBase.insertPoint("p7", 5, 5);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.insertPoint("p6", 6, 6);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.insertPoint("p6", 5, 5);
        dataBase.removeByCoord(6, 6);
        
        System.out.println("SKIPLIST: ");
        dataBase.dump();
    }
    
    /**
     * Tests more cases.
     */
    public void testExtra() {
        assertFalse(dataBase.removeByName("not there"));
        assertFalse(dataBase.removeByCoord(-1, 0));
        assertFalse(dataBase.removeByCoord(0, -1));
        assertFalse(dataBase.removeByCoord(1025, 0));
        assertFalse(dataBase.removeByCoord(0, 1025));
        assertFalse(dataBase.removeByCoord(3, 9));
        assertEquals(dataBase.search("not there"), 0);
        
    }

}
