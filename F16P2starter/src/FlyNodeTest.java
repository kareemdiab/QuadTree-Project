import student.TestCase;

/**
 * Test class for flynode. 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.05
 *
 */
public class FlyNodeTest extends TestCase {
    
    private FlyNode fly;
    /**
     * Sets up.
     */
    public void setUp() {
        fly = new FlyNode(0, 0, 1024, 1024);
        
    }
    /**
     * Tests the fly node. 
     */
    public void testFly() {
        assertTrue(fly.merge().isFly());
        System.out.println(fly.toString());
        assertEquals(fly.toString().compareTo("Node at 0, 0, 1024: Empty"), 0);
        assertFalse(fly.isLeaf());
        
 
    }
}
