
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Main for CS3114 Quadtree/SkipList Point project
 * Usage: java Point2 <command-file>
 *
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.10.07
 */
public class Point2 {
    /**
     * 
     * @param args
     *            file name.
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        if (args.length != 1) {
            System.out.println("Usage: java Point2 <command-file>");
            return;
        }
        String filename = args[0].trim();
        File commandFile = new File(filename);
        if (!commandFile.exists()) {
            System.out.println("File does not exist: " + filename);
            return;
        }
        else {

            DataBase dB = new DataBase(args[0], new SkipList<String, Point>(),
                new QuadTree(0, 0, 1024, 1024));

        }

    }
}
