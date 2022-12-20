
/**
 * KVPair class to hold both a key and associated value for
 * generic types
 * 
 * @author Kareem Diab (kareemdiab3)
 * @version 2022.09.05
 *
 * @param <K>
 *            Generic type for key.
 * @param <E>
 *            Generic type for value.
 */
public class KVPair<K extends Comparable<K>, E>
    implements Comparable<KVPair<K, E>> {
    private K theKey;
    private E theVal;

    /**
     * KVPair constructor to initialize both key and value.
     * 
     * @param k
     *            Key
     * @param v
     *            Value
     */
    public KVPair(K k, E v) {
        theKey = k;
        theVal = v;
    }


    /**
     * Compares to another KVPair.
     * 
     * @param it
     *            KVPair to be compared to.
     * 
     * @return Returns 1 if greater than,
     *         -1 if less than, and 0 if equal to.
     */
    public int compareTo(KVPair<K, E> it) {
        return theKey.compareTo(it.key());
    }


    /**
     * Compares key for another KVPair
     * 
     * @param key
     *            Key
     * @return Returns 1 if greater than,
     *         -1 if less than, and 0 if equal to.
     */
    public int compareTo(K key) {
        return theKey.compareTo(key);
    }


    /**
     * Key getter method.
     * 
     * @return Returns key.
     */
    public K key() {
        return theKey;
    }


    /**
     * Value getter method.
     * 
     * @return Returns value.
     */
    public E value() {
        return theVal;
    }


    /**
     * ToString method for KVPair.
     * 
     * @return Returns string representation of KVPair.
     */
    @Override
    public String toString() {
        return theKey.toString() + ", " + theVal.toString();
    }
}
/* *** ODSAendTag: KVPair *** */
