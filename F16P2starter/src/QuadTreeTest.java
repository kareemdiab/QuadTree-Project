
import student.TestCase;

/**
 * Test class for QuadTree data structure.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.04
 *
 */
public class QuadTreeTest extends TestCase {

    private QuadTree tree;
    private FlyNode fly;
    private Point northWest;
    private Point northEast;
    private Point southWest;
    private Point southEast;

    /**
     * Sets up test methods.
     */
    public void setUp() {
        tree = new QuadTree(0, 0, 1024, 1024);

        northWest = new Point("NW", 0, 0);
        northEast = new Point("NE", 60, 10);
        southWest = new Point("SW", 10, 60);
        southEast = new Point("SE", 60, 60);

    }


    /**
     * Tests insert method.
     */
    public void testInsert() {
        tree.insert(northWest);
        tree.insert(northEast);
        tree.insert(southWest);
        tree.insert(southEast);
        assertEquals(tree.getSize(), 4);
        Point one = new Point("one", 10, 12);
        tree.insert(one);
        Point two = new Point("two", 12, 10);
        tree.insert(two);
        Point three = new Point("three", 12, 12);

        tree.insert(three);
        tree.dump();

    }


    /**
     * More insert tests.
     */
    public void testInsertTwo() {
        Point one = new Point(" ", 1, 1);
        Point three = new Point(" ", 1, 3);
        Point two = new Point(" ", 1, 2);
        tree.insert(one);
        tree.insert(one);
        tree.insert(one);
        tree.insert(three);

    }


    /**
     * Tests dump metho.d
     */
    public void testDump() {
        Point one = new Point("P1", 4, 4);
        Point two = new Point("p1", 4, 4);
        Point three = new Point("p1", 4, 4);
        Point four = new Point("p2", 300, 15);
        Point five = new Point("p4", 700, 4);
        Point six = new Point("p5", 700, 4);
        Point seven = new Point("pointy", 200, 900);
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        myTree.insert(one);
        myTree.insert(two);
        myTree.insert(three);
        myTree.insert(four);
        myTree.insert(five);
        myTree.insert(six);
        myTree.insert(seven);

        myTree.dump();

    }


    /**
     * Second test for dump.
     */
    public void testDump2() {
        Point one = new Point("p1", 4, 4);
        Point two = new Point("p1", 4, 4);
        Point three = new Point("p1", 4, 4);
        Point four = new Point("p2", 300, 15);
        Point five = new Point("p4", 700, 4);
        Point six = new Point("p5", 700, 4);
        Point seven = new Point("pointy", 200, 900);

        tree.insert(one);
        tree.insert(two);
        tree.insert(three);
        tree.insert(four);
        tree.insert(five);
        tree.insert(six);
        tree.insert(seven);
        tree.dump();
    }


    /**
     * Tests remove method.
     */
    public void testRemove() {
        Point one = new Point("p1", 4, 4);
        Point two = new Point("p1", 4, 4);
        Point three = new Point("p1", 4, 4);
        Point four = new Point("p2", 300, 15);
        Point five = new Point("p4", 700, 4);
        Point six = new Point("p5", 700, 4);
        Point seven = new Point("pointy", 200, 900);
        Point eight = new Point("eight", 800, 800);

        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        myTree.insert(one);
        myTree.insert(two);
        myTree.insert(three);
        myTree.insert(four);
        myTree.insert(five);
        myTree.insert(six);
        myTree.insert(seven);
        myTree.insert(eight);

        // tree.remove(1552, 2);
        // tree.remove(8, 8);
        // tree.remove(300, 15);
        myTree.remove(300, 15);
        myTree.remove(200, 900);
        myTree.remove(800, 800);

        myTree.dump();
    }


    /**
     * Ensures that the nodes are counted correctly.
     */
    public void testCountNodes() {
        Point one = new Point("P1", 4, 4);
        Point two = new Point("p1", 4, 4);
        Point three = new Point("p1", 4, 4);
        Point four = new Point("p2", 300, 15);
        Point five = new Point("p4", 700, 4);
        Point six = new Point("p5", 700, 4);
        Point seven = new Point("pointy", 200, 900);

        tree.insert(one);
        tree.insert(two);
        tree.insert(three);
        tree.insert(four);
        tree.insert(five);
        tree.insert(six);
        tree.insert(seven);
        assertEquals(tree.countNodes(), 9);

    }


    /**
     * Tests tree when all points are removed.
     */
    public void testRemoveAll() {
        Point one = new Point("P1", 4, 4);
        tree.insert(one);
        tree.remove(4, 4);
        tree.dump();

    }


    /**
     * Tests region search on empy tree.
     */
    public void testRegionSearch() {
        QuadTree emptyTree = new QuadTree(0, 0, 1024, 1024);
        assertEquals(emptyTree.regionSearch(0, 0, 1020, 1020), 1);
    }


    /**
     * Tests search method.
     */
    public void testSearch() {
        Point one = new Point("p1", 300, 15);
        tree.insert(one);
        assertEquals(tree.search(300, 15).compareTo("p1"), 0);

        Point two = new Point("p1", 4, 4);
        Point three = new Point("p1", 4, 4);
        Point four = new Point("p2", 300, 15);
        Point five = new Point("p4", 700, 4);
        Point six = new Point("p5", 700, 4);
        Point seven = new Point("pointy", 200, 900);
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        tree.regionSearch(0, 0, 1024, 1024);
        myTree.insert(one);
        myTree.insert(two);
        myTree.insert(three);
        myTree.insert(four);
        myTree.insert(five);
        myTree.insert(six);
        tree.regionSearch(0, 0, 1024, 1024);
        myTree.insert(seven);
        assertEquals(myTree.search(10, 20).compareTo(""), 0);
        assertEquals(myTree.search(4, 4).compareTo("p1"), 0);

    }


    /**
     * Tests inserts and removes on tree.
     */
    public void testTree() {
        System.out.println("TEST: ");
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        Point p1 = new Point("p1", 4, 4);
        Point p2 = new Point("p2", 300, 15);
        Point p4 = new Point("p4", 700, 4);
        Point p5 = new Point("p5", 700, 4);
        Point pointy = new Point("pointy", 200, 900);
        tree.regionSearch(0, 0, 1024, 1024);
        myTree.insert(p1);
        myTree.insert(p1);
        myTree.insert(p1);
        tree.regionSearch(0, 0, 1024, 1024);
        myTree.insert(p2);
        myTree.insert(p4);
        myTree.insert(p5);
        myTree.insert(pointy);
        tree.regionSearch(0, 0, 1024, 1024);
        myTree.dump();
    }


    /**
     * More tests on insert.
     */
    public void testInsertThree() {
        tree.regionSearch(0, 0, 1024, 1024);
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        Point zero = new Point("zero", 0, 0);
        Point max = new Point("max", 1024, 1024);
        Point middle = new Point("middle", 512, 512);

        myTree.insert(zero);
        myTree.insert(max);
        myTree.insert(middle);
        myTree.insert(zero);
        tree.regionSearch(0, 0, 1024, 1024);
        myTree.dump();
    }


    /**
     * Tests empty tree.
     */
    public void testEmpty() {
        tree.regionSearch(0, 0, 1024, 1024);
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        Point zero = new Point("zero", 0, 0);
        myTree.insert(zero);
        myTree.dump();
        myTree.remove(0, 0);
        myTree.dump();
    }


    /**
     * Test case for when nodes that are close together are inserted.
     */
    public void testCloseNodes() {
        Point one = new Point("P1", 25, 25);
        Point two = new Point("P2", 26, 26);
        tree.insert(one);
        tree.insert(one);
        tree.insert(one);
        tree.insert(one);
        tree.insert(two);
        tree.dump();
        tree.remove(26, 26);
        tree.dump();
        tree.insert(one);
        tree.insert(one);
        tree.regionSearch(0, 0, 1024, 1024);
        Point middle = new Point("P1", 512, 512);
        tree.insert(middle);
        Point outer = new Point("outer", 1000, 1000);
        tree.regionSearch(0, 0, 1024, 1024);
        tree.insert(outer);
        tree.insert(outer);
        tree.insert(outer);
        tree.insert(outer);
        tree.insert(outer);
        tree.insert(outer);
        Point other = new Point("other", 1001, 1001);
        tree.insert(other);
        Point outside = new Point("out", 513, 513);
        tree.insert(outside);
        tree.remove(26, 26);

        tree.insert(other);

        tree.dump();

    }


    /**
     * More insert and remove tests.
     */
    public void testOther() {
        tree.regionSearch(0, 0, 1024, 1024);
        Point nw = new Point("nw", 200, 200);
        Point ne = new Point("ne", 600, 200);
        Point sw = new Point("sw", 200, 600);
        Point se = new Point("se", 600, 600);
        Point one = new Point("one", 602, 203);
        tree.insert(nw);
        tree.insert(ne);
        tree.insert(sw);
        tree.insert(se);
        tree.insert(one);
        tree.insert(one);
        tree.insert(one);

        tree.remove(600, 400);
        tree.remove(602, 203);

        tree.dump();

    }


    /**
     * More tests..
     */
    public void testMore() {
        tree.regionSearch(0, 0, 1024, 1024);
        Point upperLeft = new Point("topLeft", 0, 0);
        Point upperRight = new Point("topRight", 1024, 0);
        Point lowerLeft = new Point("lowerLeft", 0, 1024);
        Point lowerRight = new Point("lowerRight", 1024, 1024);
        tree.insert(upperLeft);
        tree.insert(upperRight);
        tree.insert(lowerLeft);
        tree.insert(lowerRight);
        tree.dump();

    }


    /**
     * More tests..
     */
    public void testAgain() {
        tree.regionSearch(0, 0, 1024, 1024);
        Point one = new Point("p1", 127, 127);
        Point two = new Point("p2", 257, 257);
        Point three = new Point("p3", 256, 256);
        Point four = new Point("p4", 1, 385);
        Point five = new Point("p5", 125, 445);
        Point six = new Point("p6", 5, 300);
        Point seven = new Point("p7", 1, 500);
        Point eight = new Point("p8", 100, 500);
        Point nine = new Point("p9", 2, 395);
        QuadTree myTree = new QuadTree(0, 0, 1024, 1024);
        myTree.insert(one);
        myTree.insert(one);
        myTree.insert(one);
        myTree.insert(one);
        myTree.insert(two);
        myTree.insert(three);
        myTree.insert(four);
        myTree.insert(seven);
        myTree.insert(eight);
        myTree.insert(five);
        myTree.insert(six);
        myTree.insert(nine);
        // myTree.insert(six);
        // myTree.dump();
        myTree.remove(1, 385);
        myTree.remove(2, 395);

        // myTree.remove(127, 127);
        // myTree.remove(256, 256);
        // myTree.remove(257, 257);
        myTree.dump();
    }
    

    /**
     * Tests tree some more. 
     */
    public void testTree3() {
        Point p1 = new Point("p1", 5, 401);
        Point p2 = new Point("p2", 4, 402);
        Point p3 = new Point("p3", 3, 403);
        Point p4 = new Point("p4", 2, 403);
        Point p5 = new Point("p5", 120, 400);
        Point p6 = new Point("p6", 1, 1);
        Point p7 = new Point("p7", 300, 1);
        Point p8 = new Point("p8", 240, 260);

        tree.insert(p1);
        tree.insert(p2);
        tree.insert(p3);
        tree.insert(p4);
        tree.insert(p5);
        tree.insert(p6);
        tree.insert(p7);
        tree.insert(p8);
        
        tree.dump();
        tree.remove(120, 400);
        tree.remove(2, 403);
        
        tree.dump();
       
    }
    
    /**
     * Tests tree again. 
     */
    public void testTree2() {
        tree.regionSearch(0, 0, 1024, 1024);
        Point p1 = new Point("p1", 10, 10);
        Point p2 = new Point("p2", 10, 10);
        Point p3 = new Point("p3", 10, 10);
        Point p4 = new Point("p4", 500, 500);
        Point p5 = new Point("p5", 550, 500);
        Point p6 = new Point("p6", 550, 500);
        Point p7 = new Point("p7", 550, 500);
        Point p8 = new Point("p8", 800, 500);
        Point p9 = new Point("p9", 530, 383);
        Point p10 = new Point("p10", 550, 550);
        Point p11 = new Point("p11", 550, 550);
        Point p12 = new Point("p12", 10, 430);
        Point p13 = new Point("p13", 10, 430);
        Point p14 = new Point("p14", 10, 430);
        Point p15 = new Point("p15", 250, 500);
        Point p16 = new Point("p16", 127, 511);
        Point p17 = new Point("p17", 60, 402);
        Point p18 = new Point("p18", 60, 404);
        Point p19 = new Point("p19", 60, 400);
        Point p20 = new Point("p20", 5, 385);
        Point p21 = new Point("p21", 10, 386);
        
        tree.insert(p1);
 
        tree.insert(p2);
  
        tree.insert(p3);

        tree.insert(p4);
        //tree.dump();
        tree.insert(p5);
        tree.insert(p6);
        tree.insert(p7);
        tree.insert(p8);

        tree.dump();
        tree.remove(500, 500);
        tree.insert(p9);
        tree.insert(p10);
        tree.insert(p11);
        tree.remove(550, 550);
        // doesnt exist: 
        tree.remove(-1, 10);
        tree.remove(550, 550);
        // doesnt exist: 
        tree.remove(1030, 10);
        tree.remove(800, 500);
        // doesnt exist: 
        tree.remove(256, 550);
        tree.remove(550, 500);
        tree.insert(p12);
        tree.insert(p13);
        tree.insert(p14);
        tree.insert(p15);
        tree.insert(p21);
        //tree.insert(p16);
//        tree.insert(p17);
//        tree.insert(p18);
//        tree.insert(p19);
//        tree.insert(p20);
        //tree.remove(60, 400);
        //tree.remove(60, 400);
        tree.remove(10, 386);

        tree.dump();
    }
    
    /**
     * Test quad tree some more. 
     */
    public void testTree5() {
        Point one = new Point("p1", 0, 384);
        Point two = new Point("p2", 64, 384);
        Point three = new Point("p3", 0, 448);
        Point four = new Point("p4", 64, 448);
        Point five = new Point("p5", 128, 384);
        tree.insert(one);
        tree.insert(two);
        tree.insert(three);
        tree.insert(four);
        tree.insert(five);
        tree.remove(64, 448);
        tree.dump();
        
        
    }
    

}
