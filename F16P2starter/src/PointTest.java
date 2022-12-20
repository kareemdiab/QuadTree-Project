
import student.TestCase;

/**
 * Test class for methods in Point.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.09.05
 *
 */
public class PointTest extends TestCase {
    private Point rec;
    private Point one;

    /**
     * Sets up test methods.
     */
    public void setUp() {
        rec = new Point("rec", 5, 5);
        one = new Point("one", 0, 0);

    }


    /**
     * Tests quadrant getter method
     */
    public void testGetQuadrants() {

        Point northWest = new Point("NW", 10, 10);
        Point northEast = new Point("NE", 600, 10);
        Point southWest = new Point("SW", 10, 600);
        Point southEast = new Point("SE", 600, 600);
        assertEquals(northWest.getQuadrant(0, 0, 1024, 1024), 0);
        assertEquals(northEast.getQuadrant(0, 0, 1024, 1024), 1);
        assertEquals(southWest.getQuadrant(0, 0, 1024, 1024), 2);
        assertEquals(southEast.getQuadrant(0, 0, 1024, 1024), 3);

    }


    /**
     * Tests toString method for Point.
     */
    public void testToString() {
        System.out.println(rec.toString());
        assertEquals(rec.toString().compareTo("(rec, 5, 5)"), 0);
        Point noName = new Point(null, 1, 1);
        assertEquals(noName.toString().compareTo("(1, 1)"), 0);
    }


    /**
     * Tests coordinateCheck method.
     */
    public void testDimensionCheck() {
        assertTrue(rec.dimensionCheck(5, 5));
        assertFalse(rec.dimensionCheck(5, 4));
        assertFalse(rec.dimensionCheck(4, 5));

    }


    /**
     * Tests areSame method for Points that are the same and Points
     * that are different.
     */
    public void testAreSame() {
        assertFalse(rec.areSame(one));
        Point same = new Point("rec", 5, 5);
        assertTrue(rec.areSame(same));
        assertFalse(rec.areSame(new Point("rec", 5, 4)));
        assertFalse(rec.areSame(new Point("rec", 4, 5)));

        Point different = new Point("diff", 5, 5);
        assertTrue(same.areSame(different));
    }


    /**
     * Tests validPoint method with both valid and invalid Points.
     */
    public void testvalidPoint() {
        assertTrue(rec.validPoint(0, 0, 1024, 1024));

        Point bad1 = new Point("bad1", -1, 0);
        Point bad2 = new Point("bad2", 0, -1);
        Point bad3 = new Point("bad3", 0, 1025);
        Point bad4 = new Point("bad4", 1025, 0);

        assertFalse(bad1.validPoint(0, 0, 1024, 1024));
        assertFalse(bad2.validPoint(0, 0, 1024, 1024));
        assertFalse(bad3.validPoint(0, 0, 1024, 1024));
        assertFalse(bad4.validPoint(0, 0, 1024, 1024));

    }

}
