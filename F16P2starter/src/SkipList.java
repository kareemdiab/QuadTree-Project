
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;
import student.TestableRandom;

/**
 * Skiplist class, similar to linked list but also stores pointers to
 * elements further down in list for quicker access.
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.09.05
 *
 * @param <K>
 *            Generic type for key.
 * @param <E>
 *            Generic type for value.
 */
public class SkipList<K extends Comparable<K>, E> {
// Make this a private data member of the SkipList object
    private Random rnd; // Random number generator
    private SkipNode<K, E> head;
    private int deepestLevel;
    private int size = 0;

    /**
     * SkipList constructor. Initializes head to null, level to -1, and
     * size to 0.
     */
    public SkipList() {
        rnd = new TestableRandom();
        head = new SkipNode<K, E>(null, 0);
        deepestLevel = -1;

    }


    /**
     * Size getter method
     * 
     * @return Returns size;
     */
    public int getSize() {
        return size;
    }


    /**
     * Uses a random geometric distribution to pick a new level.
     * 
     * @return Integer for new level.
     */
    public int randomLevel() {
        int lev = 0;
        while (rnd.nextBoolean()) {
            lev++;
        }
        return lev;
    }


    /**
     * Inserts generic KVPair into list.
     * 
     * @param it
     *            KVPair to be inserted.
     * @return True when insert is successful.
     */
    public boolean insert(KVPair<K, E> it) {
        int newLevel = randomLevel();

        if (deepestLevel < newLevel) {

            adjustHead(newLevel);

        }
        @SuppressWarnings("unchecked") // Generic array allocation
        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipList.SkipNode.class, deepestLevel + 1);
        Comparable<K> k = it.key();
        SkipNode<K, E> curr = head; // Start at header node
        for (int i = deepestLevel; i >= 0; i--) { // Find insert position
            SkipNode<K, E> ahead = curr.forward[i];
            while ((ahead != null) && (k.compareTo((K)(curr.forward[i])
                .key()) > 0)) {
                curr = ahead;
                ahead = ahead.forward[i];
            }

            update[i] = curr; // Track end at level i
        }
        curr = new SkipNode<K, E>(it, newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            curr.forward[i] = update[i].forward[i]; // Who x points to
            update[i].forward[i] = curr; // Who y points to

        }

        size++; // Increment dictionary size
        return true;
    }


    /**
     * Helper method to adjust head when newLevel becomes greater than the
     * current
     * level.
     * 
     * @param newLevel
     *            The Randomly generated level.
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, E> temp = new SkipNode<K, E>(null, newLevel);

        for (int i = 0; i <= deepestLevel; i++) {
            temp.forward[i] = head.forward[i];
        }

        for (int i = deepestLevel + 1; i < newLevel; i++) {
            temp.forward[i] = null;
        }
        deepestLevel = newLevel;
        head = temp;
    }


    /**
     * Remove specified entry from skip list based off of key.
     * 
     * @param kv
     *            KVpair to be removed.
     */
    @SuppressWarnings("unchecked")
    public void removeKV(KVPair<K, E> kv) {

        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipList.SkipNode.class, deepestLevel + 1);

        SkipNode<K, E> curr = head;
        for (int i = deepestLevel; i >= 0; i--) { // Find insert position
            SkipNode<K, E> ahead = curr.forward[i];
            while ((ahead != null) && (kv.key().compareTo(ahead.key()) > 0 || !kv.value().equals(ahead.element()))) {
                curr = ahead;
                ahead = ahead.forward[i];

            }

            update[i] = curr; // Track end at level i
        }

        curr = curr.forward[0];

        if (curr != null && kv.key().compareTo(curr.key()) == 0) {
            for (int i = 0; i <= curr.getLevel(); i++) {
                update[i].forward[i] = curr.forward[i];
            }
            size--;

        }


    }


    /**
     * Remove specified entry from skip list based off of key.
     * 
     * @param key
     *            Key for element to be removed.
     */
    @SuppressWarnings("unchecked")
    public void remove(Comparable<K> key) {

        SkipNode<K, E>[] update = (SkipNode<K, E>[])Array.newInstance(
            SkipList.SkipNode.class, deepestLevel + 1);

        SkipNode<K, E> curr = head;
        for (int i = deepestLevel; i >= 0; i--) { // Find insert position
            SkipNode<K, E> ahead = curr.forward[i];
            while ((ahead != null) && (key.compareTo(ahead.key()) > 0)) {
                curr = ahead;
                ahead = ahead.forward[i];

            }

            update[i] = curr; // Track end at level i
        }

        curr = curr.forward[0];

        if (curr != null && key.compareTo(curr.key()) == 0) {
            for (int i = 0; i <= curr.getLevel(); i++) {
                update[i].forward[i] = curr.forward[i];
            }
            size--;

        }

    }


    /**
     * Returns first matching KVPair if one exists. Otherwise, returns null.
     * 
     * @param key
     *            Key to be searched for.
     * @return Returns matching KVPair.
     */
    public KVPair<K, E> search(Comparable<K> key) {

        SkipNode<K, E> x = head; // Dummy header node
        for (int i = deepestLevel; i >= 0; i--) { // For each level...

            while ((x.forward[i] != null) && (key.compareTo((K)x.forward[i]
                .key()) > 0)) { // go forward

                x = x.forward[i]; // Go one last step
            }
        }
        x = x.forward[0]; // Move to actual record, if it exists
        if ((x != null) && (key.compareTo((K)x.key()) == 0)) {

            return x.rec;
        }
        else {

            return null;
        }

    }


    /**
     * Sets size of skiplist.
     * 
     * @param size
     *            Size to be set to.
     */
    public void setSize(int size) {
        this.size = size;
    }


    /**
     * Prints out dump of skiplist.
     * 
     * @return size of skiplist.
     */
    public int dump() {
        System.out.println("SkipList dump: ");
        Iterator<Point> iter = new Skiperator<String, Point>();

        int count = 0;

        System.out.println(iter.toString());

        while (iter.hasNext()) {
            iter.next();
            System.out.println(iter.toString());

            count++;
        }

        System.out.println("SkipList size is: " + size);
        return count;

    }

    /**
     * Node for each element in the SkipList. Each stores array of
     * pointers with random depth to point to elements further down in
     * skiplist.
     * 
     * @author Kareem Diab (kareemdiab3)
     * @version 2022.09.05
     *
     *
     * @param <K>
     *            Generic key type.
     * @param <E>
     *            Generic Value type.
     */
    class SkipNode<K extends Comparable<K>, E> {
        private KVPair<K, E> rec;
        private SkipNode<K, E>[] forward;
        private int level;

        /**
         * Element getter.
         * 
         * @return Returns element.
         */
        public E element() {
            return rec.value();
        }


        /**
         * Key getter.
         * 
         * @return Returns key.
         */
        public K key() {
            return rec.key();
        }


        /**
         * Level getter.
         * 
         * @return Returns level.
         */
        public int getLevel() {
            return level;
        }


        /**
         * Overrides toString method to be specific to the SkipNode.
         * 
         * @return String representation.
         */
        @Override
        public String toString() {
            String recVal = "(null)";
            if (rec != null) {

                recVal = rec.value().toString();
            }
            return "Node has depth " + forward.length + ", Value " + recVal;
        }


        /**
         * Prints out all elements currently in the skip list.
         * 
         * @return Returns
         */

        /**
         * Skipnode constructor.
         * 
         * @param kv
         *            KVPair for the node.
         * @param level
         *            How many elements its array of pointers will have.
         */
        @SuppressWarnings("unchecked")
        public SkipNode(KVPair<K, E> kv, int level) {
            this.level = level;
            this.rec = kv;
            this.forward = new SkipNode[level + 1];
            for (int i = 0; i < level; i++)
                forward[i] = null;
        }

    }

    /**
     * Iterator method creates Iterator object
     *
     * @return new Iterator object
     */
    public Skiperator<K, E> iterator() {
        return new Skiperator<K, E>();
    }

    /**
     * Iterator class made for the SkipList.
     * 
     * @author Kareem Diab (kareemdiab3)
     * @version 2022.09.05
     *
     * @param <K>
     *            Generic type for key.
     * @param <E>
     *            Generic type for value.
     */
    class Skiperator<K extends Comparable<K>, E> implements Iterator<E> {

        private SkipNode<K, E> first;
        private boolean calledNext;

        /**
         * Creates a new Skiperator.
         */
        @SuppressWarnings("unchecked")
        public Skiperator() {
            first = (SkipNode<K, E>)head;
            calledNext = false;
        }


        /**
         * Checks if there are more elements in the list
         *
         * @return true if there are more elements in the list
         */
        @Override
        public boolean hasNext() {
            return first.forward[0] != null;
        }


        /**
         * Gets the next value in the list
         *
         * @return the next value
         * @throws NoSuchElementException
         *             if there are no nodes left in the list
         */
        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException("No more nodes in skip list!");
            }

            first = first.forward[0];
            E value = first.element();
            calledNext = true;
            return value;
        }


        /**
         * ToString method for the current node in skip list.
         * 
         * @return String representation of node.
         */
        @Override
        public String toString() {

            return first.toString();
        }

    }

}
