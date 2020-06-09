package adapter;

/**
 * An ordered collection (also known as a sequence).
 */
public interface HList extends HCollection {

    /**
     * Inserts the specified element at the specified position in this list. Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). 
     * @param index  index at which the specified element is to be inserted.
     * @param element  element to be inserted.
     * @throws UnsupportedOperationException if the add method is not supported by this list. 
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list. 
     * @throws NullPointerException if the specified element is null and this list does not support null elements. 
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list. 
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public void add(int index, Object element);

    /**
     * Inserts the specified element at the specified position in this list.
     * @param o element to be appended to this list.
     * @return true (as per the general contract of the Collection.add method).
     * @throws UnsupportedOperationException if the add method is not supported by this list.
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list. 
     * @throws NullPointerException if the specified element is null and this list does not support null elements. 
     * @throws IllegalArgumentException if some aspect of this element prevents it from being added to this list.
     */
    public boolean add(Object o);

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.
     * @param c collection whose elements are to be added to this list. 
     * @return true if this list changed as a result of the call.
     * @throws UnsupportedOperationException if the addAll method is not supported by this list. 
     * @throws ClassCastException if the class of an element in the specified collection prevents it from being added to this list. 
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not support null elements, or if the specified collection is null. 
     * @throws IllegalArgumentException if some aspect of an element in the specified collection prevents it from being added to this list.
     */
    public boolean addAll(HCollection c);

    /**
     * Appends all of the elements in the specified collection to the end of this list, in the order that they are returned by the specified collection's iterator.
     * @param index index at which to insert first element from the specified collection.
     * @param c elements to be inserted into this list. 
     * @return true if this list changed as a result of the call. 
     * @throws UnsupportedOperationException if the addAll method is not supported by this list. 
     * @throws ClassCastException if the class of an element in the specified collection prevents it from being added to this list.
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not support null elements, or if the specified collection is null. 
     * @throws IllegalArgumentException if some aspect of an element in the specified collection prevents it from being added to this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public boolean addAll(int index, HCollection c);

    /**
     * Removes all of the elements from this list.
     * @throws UnsupportedOperationException if the clear method is not supported by this list.
     */
    public void clear();

    /**
     * Returns true if this list contains the specified element.
     * @param o element whose presence in this list is to be tested.
     * @return true if this list contains the specified element.
     * @throws ClassCastException if the type of the specified element is incompatible with this list (optional). 
     * @throws NullPointerException if the specified element is null and this list does not support null elements (optional).
     */
    public boolean contains(Object o);

    /**
     * Returns true if this list contains the specified element.
     * @param c collection to be checked for containment in this list.
     * @return true if this list contains all of the elements of the specified collection.
     * @throws ClassCastException if the types of one or more elements in the specified collection are incompatible with this list (optional).
     * @throws NullPointerException if the specified collection contains one or more null elements and this list does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean containsAll(HCollection c);

    /**
     * Compares the specified object with this list for equality.
     * @param o the object to be compared for equality with this list.
     * @return true if the specified object is equal to this list.
     */
    public boolean equals(Object o);

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of element to return. 
     * @return the element at the specified position in this list. 
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Object get(int index);

    /**
     * Returns the hash code value for this list.
     * @return the hash code value for this list.
     */
    public int hashCode();

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for. 
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws ClassCastException if the type of the specified element is incompatible with this list (optional). 
     * @throws NullPointerException if the specified element is null and this list does not support null elements (optional).
     */
    public int indexOf(Object o);

    /**
     * Returns true if this list contains no elements.
     * @return true if this list contains no elements.
     */
    public boolean isEmpty();

    /**
     * Returns an iterator over the elements in this list in proper sequence.
     * @return an iterator over the elements in this list in proper sequence.
     */
    public HIterator iterator();

    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for. 
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws ClassCastException if the type of the specified element is incompatible with this list (optional). 
     * @throws NullPointerException if the specified element is null and this list does not support null elements (optional).
     */
    public int lastIndexOf(Object o);

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @param index  index of first element to be returned from the list iterator (by a call to the next method). 
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public HListIterator listIterator(int index);

    /**
     * Removes the element at the specified position in this list.
     * @param index  the index of the element to removed. 
     * @return the element previously at the specified position.
     * @throws ClassCastException if the type of the specified element is incompatible with this list (optional). 
     * @throws NullPointerException if the specified element is null and this list does not support null elements (optional).
     */
    public Object remove(int index);

    /**
     * Removes the first occurrence in this list of the specified element.
     * @param o element to be removed from this list, if present. 
     * @return true if this list contained the specified element. 
     * @throws ClassCastException if the type of the specified element is incompatible with this list (optional). 
     * @throws NullPointerException if the specified element is null and this list does not support null elements (optional). 
     * @throws UnsupportedOperationException if the remove method is not supported by this list. 
     */
    public boolean remove(Object o);

    /**
     * Removes from this list all the elements that are contained in the specified collection.
     * @param c collection that defines which elements will be removed from this list.
     * @return true if this list changed as a result of the call. 
     * @throws UnsupportedOperationException if the removeAll method is not supported by this list. 
     * @throws ClassCastException if the types of one or more elements in this list are incompatible with the specified collection (optional).
     * @throws NullPointerException if this list contains one or more null elements and the specified collection does not support null elements (optional).
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean removeAll(HCollection c);

    /**
     * Retains only the elements in this list that are contained in the specified collection.
     * @param c collection that defines which elements this set will retain.
     * @return true if this list changed as a result of the call. 
     * @throws UnsupportedOperationException if the retainAll method is not supported by this list. 
     * @throws ClassCastException if the types of one or more elements in this list are incompatible with the specified collection (optional). 
     * @throws NullPointerException if this list contains one or more null elements and the specified collection does not support null elements (optional). 
     * @throws NullPointerException if the specified collection is null.
     */
    public boolean retainAll(HCollection c);

    /**
     * Replaces the element at the specified position in this list with the specified element.
     * @param index Replaces the element at the specified position in this list with the specified element.
     * @param element Replaces the element at the specified position in this list with the specified element.
     * @return the element previously at the specified position.
     * @throws UnsupportedOperationException if the set method is not supported by this list. 
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list. 
     * @throws NullPointerException if the specified element is null and this list does not support null elements. 
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list. 
     * @throws IndexOutOfBoundsException if the index is out of range.
     */
    public Object set(int index, Object element);

    /**
     * Returns the number of elements in this list.
     * @return the number of elements in this list.
     * @throws UnsupportedOperationException if the set method is not supported by this list. 
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list. 
     * @throws NullPointerException if the specified element is null and this list does not support null elements. 
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list. 
     * @throws IllegalArgumentException if some aspect of the specified element prevents it from being added to this list. 
     */
    public int size();

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex  low endpoint (inclusive) of the subList.
     * @param toIndex  low endpoint (inclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws IndexOutOfBoundsException for an illegal endpoint index value.
     */
    public HList subList(int fromIndex, int toIndex);

    /**
     * Returns an array containing all of the elements in this list in proper sequence.
     * @return array containing all of the elements in this list in proper sequence.
     */
    public Object[] toArray();

    /**
     * Returns an array containing all of the elements in this list in proper sequence; the runtime type of the returned array is that of the specified array.
     * @param a the array into which the elements of this list are to be stored, if it is big enough; otherwise, a new array of the same runtime type is allocated for this purpose. 
     * @return an array containing the elements of this list.
     * @throws UnsupportedOperationException if the add method is not supported by this list. 
     * @throws ClassCastException if the class of the specified element prevents it from being added to this list. 
     * @throws NullPointerException if the specified array is null.
     * @throws IllegalArgumentException if some aspect of this element prevents it from being added to this list.
     */
    public Object[] toArray(Object[] a);
    
}