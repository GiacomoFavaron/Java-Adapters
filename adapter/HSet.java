package adapter;

/**
 * A collection that contains no duplicate elements.
 */
public interface HSet extends HCollection {

    /**
     * Adds the specified element to this set if it is not already present (optional operation).
     * @param o element to be added to this set.
     * @return true if this set did not already contain the specified element.
     * @throws UnsupportedOperationException if the add method is not supported by this set. 
     * @throws ClassCastException if the class of the specified element prevents it from being added to this set. 
     * @throws NullPointerException if the specified element is null and this set does not support null elements. 
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this set.
     */
    public boolean add(Object o);

    /**
     * Adds all of the elements in the specified collection to this set if they're not already present (optional operation).
     * @param c  collection whose elements are to be added to this set.
     * @return true if this set changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll method is not supported by this set. 
     * @throws ClassCastException if the class of some element of the specified collection prevents it from being added to this set. 
     * @throws NullPointerException if the specified collection contains one or more null elements and this set does not support null elements, or if the specified collection is null.
     * @throws IllegalArgumentException if some aspect of some element of the specified collection prevents it from being added to this set.
     */
    public boolean addAll(HCollection c);

    /**
     * Removes all of the elements from this set (optional operation).
     * @throws UnsupportedOperationException if the clear method is not supported by this set.
     */
    public void clear();

    /**
     * Returns true if this set contains the specified element.
     * @param o element whose presence in this set is to be tested.
     * @return true if this set contains the specified element.
     * @throws ClassCastException if the type of the specified element is incompatible with this set (optional). 
     * @throws NullPointerException if the specified element is null and this set does not support null elements (optional).
     */
    public boolean contains(Object o);

    /**
     * Returns true if this set contains all of the elements of the specified collection.
     * @param c collection to be checked for containment in this set.
     * @return true if this set contains all of the elements of the specified collection.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this set (optional). 
     * @throws NullPointerException if the specified collection contains one or more null elements and this set does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this set for equality.
     * @param o Object to be compared for equality with this set.
     * @return true if the specified Object is equal to this set.
     */
    public boolean equals(Object o);

    /**
     * Returns the hash code value for this set.
     * @return the hash code value for this set.
     */
    public int hashCode();

    /**
     * Returns true if this set contains no elements.
     * @return true if this set contains no elements.
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in this set.
     * @return an iterator over the elements in this set.
     */
    public HIterator iterator();

    /**
     * Removes the specified element from this set if it is present (optional operation).
     * @param o object to be removed from this set, if present.
     * @return true if the set contained the specified element.
     * @throws ClassCastException if the type of the specified element is incompatible with this set (optional).
     * @throws NullPointerException if the specified element is null and this set does not support null elements (optional).
     * @throws UnsupportedOperationException if the remove method is not supported by this set.
     */
    public boolean remove(Object o);

    /**
     * Removes from this set all of its elements that are contained in the specified collection (optional operation).
     * @param c collection that defines which elements will be removed from this set.
     * @return true if this set changed as a result of the call.
     * @throws UnsupportedOperationException if the removeAll method is not supported by this Collection. 
     * @throws ClassCastException if the types of one or more elements in this set are incompatible with the specified collection (optional). 
     * @throws NullPointerException if this set contains a null element and the specified collection does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this set that are contained in the specified collection (optional operation).
     * @param c collection that defines which elements this set will retain.
     * @return true if this collection changed as a result of the call.
     * @throws UnsupportedOperationException if the retainAll method is not supported by this Collection. 
     * @throws ClassCastException if the types of one or more elements in this set are incompatible with the specified collection (optional). 
     * @throws NullPointerException if this set contains a null element and the specified collection does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this set (its cardinality).
     * @return true if this set contains the specified element.
     */
    public int size();

    /**
     * Returns an array containing all of the elements in this set.
     * @return an array containing all of the elements in this set.
     * @throws NullPointerException if the specified array is null.
     */
    public Object[] toArray();

    /**
     * Returns an array containing all of the elements in this set; the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this set are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return an array containing the elements of this set.
     * @throws ArrayStoreException the runtime type of a is not a supertype of the runtime type of every element in this set. 
     * @throws NullPointerException if the specified array is null.
     */
    public Object[] toArray(Object[] a);
}