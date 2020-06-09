package adapter;

/**
 * The root interface in the collection hierarchy.
 */
public interface HCollection {

    /**
     * Ensures that this collection contains the specified element.
     * @param o element whose presence in this collection is to be ensured. 
     * @return true if this collection changed as a result of the call
     * @throws UnsupportedOperationException some aspect of this element prevents it from being added to this collection.some aspect of this element prevents it from being added to this collection.
     * @throws ClassCastException class of the specified element prevents it from being added to this collection. 
     * @throws NullPointerException if the specified element is null and this collection does not support null elements. 
     * @throws IllegalArgumentException some aspect of this element prevents it from being added to this collection.
     */
    public boolean add(Object o);

    /**
     * Adds all of the elements in the specified collection to this collection.
     * @param c elements to be inserted into this collection. 
     * @return elements to be inserted into this collection.
     * @throws UnsupportedOperationException if this collection does not support the addAll method.
     * @throws ClassCastException if the class of an element of the specified collection prevents it from being added to this collection. 
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not support null elements, or if the specified collection is null.
     * @throws IllegalArgumentException some aspect of an element of the specified collection prevents it from being added to this collection.
     */
    public boolean addAll(HCollection c);

    /**
     * Removes all of the elements from this collection.
     * @throws UnsupportedOperationException if the clear method is not supported by this collection.
     */
    public void clear();

    /**
     * Returns true if this collection contains the specified element.
     * @param o element whose presence in this collection is to be tested. 
     * @return true if this collection contains the specified element 
     * @throws UnsupportedOperationException if the clear method is not supported by this collection.
     * @throws NullPointerException if the specified element is null and this collection does not support null elements (optional).
     */
    public boolean contains(Object o);

    /**
     * Returns true if this collection contains all of the elements in the specified collection.
     * @param c collection to be checked for containment in this collection. 
     * @return true if this collection contains all of the elements in the specified collection
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this collection (optional). 
     * @throws NullPointerException if the specified collection contains one or more null elements and this collection does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this collection for equality.
     * @param o  Object to be compared for equality with this collection. 
     * @return Object to be compared for equality with this collection.
     */
    public boolean equals(Object o);

    /**
     * Returns the hash code value for this collection.
     * @return the hash code value for this collection
     */
    public int hashCode();

    /**
     * Returns true if this collection contains no elements.
     * @return true if this collection contains no elements
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in this collection.
     * @return an array containing all of the elements in this collection
     */
    public HIterator iterator();

    /**
     * Removes a single instance of the specified element from this collection, if it is present.
     * @param o element to be removed from this collection, if present. 
     * @return true if this collection changed as a result of the call
     * @throws ClassCastException if the type of the specified element is incompatible with this collection (optional).
     * @throws NullPointerException if the specified element is null and this collection does not support null elements (optional). 
     * @throws UnsupportedOperationException remove is not supported by this collection.
     */
    public boolean remove(Object o);

    /**
     * Removes all this collection's elements that are also contained in the specified collection.
     * @param c elements to be removed from this collection. 
     * @return true if this collection changed as a result of the call
     * @throws UnsupportedOperationException if the removeAll method is not supported by this collection.
     * @throws ClassCastException if the types of one or more elements in this collection are incompatible with the specified collection (optional).
     * @throws NullPointerException if this collection contains one or more null elements and the specified collection does not support null elements (optional).
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this collection that are contained in the specified collection.
     * @param c elements to be retained in this collection. 
     * @return true if this collection changed as a result of the call 
     * @throws UnsupportedOperationException if the retainAll method is not supported by this Collection. 
     * @throws ClassCastException if the types of one or more elements in this collection are incompatible with the specified collection (optional). 
     * @throws NullPointerException if this collection contains one or more null elements and the specified collection does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean retainAll(HCollection c);

    /**
     * Returns the number of elements in this collection.
     * @return the number of elements in this collection
     */
    public int size();

    /**
     * Returns an array containing all of the elements in this collection.
     * @return an array containing all of the elements in this collection
     * @throws ArrayStoreException the runtime type of the specified array is not a supertype of the runtime type of every element in this collection. 
     * @throws NullPointerException if the specified array is null.
     */
    public Object[] toArray();

    /**
     * Returns an array containing all of the elements in this collection; the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this collection are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return an array containing the elements of this collection
     * @throws ArrayStoreException the runtime type of the specified array is not a supertype of the runtime type of every element in this collection. 
     * @throws NullPointerException if the specified array is null.
     */
    public Object[] toArray(Object[] a);
    
}