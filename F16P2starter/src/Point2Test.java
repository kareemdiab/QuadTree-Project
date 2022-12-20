
import java.io.IOException;
import student.TestCase;



/**
 * This class tests the Point2 project
 *
 * @author CS Staff
 * @version 2022.10.07
 */
public class Point2Test extends TestCase {
    /**
     * Set up method. 
     */
    public void setUp() {
    }

    /**
     * Tests bad file. 
     * @throws IOException for no file.
     */
    public void testBadFile() throws IOException {
        String[] args = new String[1];
        args[0] = "NO_input_file_exists.txt";
        Point2.main(args);
        String out = systemOut().getHistory();
        assertFuzzyEquals("File does not exist: NO_input_file_exists.txt", out);
    }

}
