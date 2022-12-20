
import java.io.FileNotFoundException;
import java.io.File;
import java.util.Iterator;
import java.util.Scanner;

import java.io.File;

/**
 * Reads commands from file with Points. Handles input
 * and output.
 * 
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.09.01
 *
 */
public class DataBase {
    private SkipList<String, Point> skippy;
    private QuadTree tree;

    /**
     * Constructor for DataBase class.
     * 
     * @param fileName
     *            File to be read and processed by DataBase.
     * @param quadTree
     *            The QuadTree to be used for the point data.
     * 
     * 
     * @param skipList
     *            SkipList to store Points.
     * @throws FileNotFoundException
     */
    public DataBase(
        String fileName,
        SkipList<String, Point> skipList,
        QuadTree quadTree)
        throws FileNotFoundException {
        this(skipList, quadTree);

        Scanner txt = new Scanner(new File(fileName));
        readFile(txt);

    }


    /**
     * Second constructor to initialize DataBase without specified file yet.
     * 
     * @param skipList
     *            SkipList to be store Points.
     * @param quadTree
     *            QuadTree for spatial queries.
     */
    public DataBase(SkipList<String, Point> skipList, QuadTree quadTree) {
        Iterator<Point> iter = skipList.iterator();
        skippy = new SkipList<String, Point>();
        tree = quadTree;

        if (iter.hasNext()) {
            skippy = new SkipList<String, Point>();
            while (iter.hasNext()) {
                Point p = iter.next();
                skippy.insert(new KVPair<String, Point>(p.getName(), p));
            }
        }
        else {
            skippy = skipList;
        }

    }


    /**
     * Reads file and calls methods based on commands.
     * 
     * @param txt
     *            Scanner input to be read.
     * @throws FileNotFoundException
     *             In case file is not found.
     */
    public void readFile(Scanner txt) throws FileNotFoundException {

        while (txt.hasNextLine()) {
            if (!txt.hasNext()) {
                break;
            }

            String next = txt.next();

            if (next.compareTo("insert") == 0) {

                String name = txt.next();

                int x = txt.nextInt();
                int y = txt.nextInt();

                insertPoint(name, x, y);

            }
            if (next.compareTo("dump") == 0) {

                dump();
            }
            if (next.compareTo("remove") == 0) {
                if (txt.hasNextInt()) {

                    int x = txt.nextInt();
                    int y = txt.nextInt();

                    removeByCoord(x, y);
                }
                else {
                    removeByName(txt.next());
                }

            }
            if (next.compareTo("regionsearch") == 0) {
                int x = txt.nextInt();
                int y = txt.nextInt();
                int w = txt.nextInt();
                int h = txt.nextInt();
                regionSearch(x, y, w, h);
            }

            if (next.compareTo("duplicates") == 0) {
                duplicates();
            }

            if (next.compareTo("search") == 0) {
                search(txt.next());
            }
        }

    }


    /**
     * Prints out all elements currently in the skip list.
     * 
     * @return Returns
     */
    public int dump() {
        // SkipList dump
        System.out.println("SkipList dump:");
        Iterator<Point> iter = skippy.iterator();
        // String dump = "";
        int count = 0;

        System.out.println(iter.toString());

        while (iter.hasNext()) {
            Point p = iter.next();

            System.out.println(iter.toString().substring(0, 24) + p
                .skipString());
            // dump += iter.toString() + " ";

            count++;
        }

        System.out.println("SkipList size is: " + count);

        // QuadTree dump
        tree.dump();

        return count;

    }


    /**
     * Creates Point and KVpair with name and coordinates and then inserts
     * into the skiplist.
     * 
     * @param name
     *            Name of Point
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     */
    public void insertPoint(String name, int x, int y) {

        Point p = new Point(name, x, y);
        KVPair<String, Point> kv = new KVPair<String, Point>(name, p);

        if (p.validPoint(0, 0, 1024, 1024)) {
            skippy.insert(kv);

            tree.insert(p);
            System.out.println("Point inserted: " + p.toString());
        }
        else {

            System.out.println("Point rejected: " + p.toString());
        }
    }


    /**
     * Removes Point specified by name.
     * 
     * @param name
     *            Name of Point.
     * 
     * @return Returns true if Point is removed.
     */
    public boolean removeByName(String name) {
        KVPair<String, Point> node = skippy.search(name);

        if (node != null) {
            int x = node.value().getX();
            int y = node.value().getY();
            if (node.value().validPoint(0, 0, 1024, 1024)) {
                skippy.remove(name);
                tree.remove(x, y);
                System.out.println("Point removed: " + "(" + name + ", " + x
                    + ", " + y + ")");

                return true;
            }
            else {
                System.out.println("Point rejected: " + "(" + name + ")");
                return false;
            }

        }
        else {
            System.out.println("Point not removed: " + name);
            return false;
        }

    }


    /**
     * Removes Point with specified x y coordinates.
     * 
     * @param x
     *            X coordinate
     * @param y
     *            Y coordinate
     * @return Returns true if Point is removed.
     */
    public boolean removeByCoord(int x, int y) {
        Point point = new Point("", x, y);
        if (x < 0 || y < 0) {
            System.out.println("Point rejected: (" + x + ", " + y + ")");
            return false;

        }
        if (x > 1024 || y > 1024) {
            System.out.println("Point not found: (" + x + ", " + y + ")");
            return false;
        }
        String name = tree.search(x, y);
        if (name.compareTo("") != 0) {
            point = new Point(name, x, y);
            KVPair<String, Point> kv = new KVPair<String, Point>(name, point);

            tree.remove(x, y);
            skippy.remove(kv.key());
            return true;

        }

        System.out.println("Point not found: (" + x + ", " + y + ")");
        return false;

    }




    /**
     * Searches specified region for any intersecting Points and
     * prints them to the console.
     * 
     * @param x
     *            Minimum x value of region to be searched.
     * @param y
     *            Minimum y value of region to be searched.
     * @param w
     *            Width of region.
     * @param h
     *            Width of region.
     * @return Returns number of Points intersecting the region.
     */
    public int regionSearch(int x, int y, int w, int h) {
        if (w <= 0 || h <= 0) {
            System.out.println("Rectangle rejected: (" + x + ", " + y + ", " + w
                + ", " + h + ")");
            return 0;
        }
        else {

            System.out.println("Points intersecting region (" + x + ", " + y
                + ", " + w + ", " + h + "):");
            int count = tree.regionSearch(x, y, w, h);
            System.out.println(count + " quadtree nodes visited");

            return count;
        }

    }


    /**
     * Searches for Point in skip list by specified name and
     * lists all matching Points in the console.
     * 
     * @param name
     *            Name of Point to be searched for.
     * @return Returns amount of Points found with the same name in the
     *         skiplist.
     */
    public int search(String name) {
        int count = 0;

        if (skippy.search(name) != null) {

            Iterator<Point> iter = skippy.iterator();
            while (iter.hasNext()) {
                Point p = iter.next();
                if (p.getName().compareTo(name) == 0) {
                    System.out.println("found " + p.toString());
                    count++;

                }
            }
        }
        else {
            System.out.println("Point not found: " + name);
        }
        return count;

    }


    /**
     * Reports all duplicates.
     * 
     * @return Returns number of duplicates.
     */
    public int duplicates() {

        System.out.println("Duplicate points: ");
        return tree.duplicates();
    }

}
