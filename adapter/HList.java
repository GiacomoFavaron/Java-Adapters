package adapter;

/**
 * An ordered collection (also known as a sequence).
 */
public interface HList extends HCollection {

    /**
     * Inserts the specified element at the specified position in this list (optional operation). Shifts the element currently at that position (if any) and any subsequent elements to the right (adds one to their indices). 
     * @param index  index at which the specified element is to be inserted.
     * @param element  element to be inserted.
     * @throws NullPointerException
     */
    public void add(int index, Object element);

    /**
     * Inserts all of the elements in the specified collection into this list at the specified position (optional operation).
     * @param index  index at which to insert first element from the specified collection.
     * @param c  elements to be inserted into this list. 
     * @return true if this list changed as a result of the call.
     * @throws NullPointerException
     */
    public boolean addAll(int index, HCollection c);

    /**
     * Returns the element at the specified position in this list.
     * @param index  index of element to return. 
     * @return the element at the specified position in this list. 
     * @throws NullPointerException
     */
    public Object get(int index);

    /**
     * Returns the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for. 
     * @return the index in this list of the first occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException
     */
    public int indexOf(Object o);
    
    /**
     * Returns the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @param o  element to search for. 
     * @return the index in this list of the last occurrence of the specified element, or -1 if this list does not contain this element.
     * @throws NullPointerException
     */
    public int lastIndexOf(Object o);

    /**
     * Returns a list iterator of the elements in this list (in proper sequence).
     * @return a list iterator of the elements in this list (in proper sequence).
     */
    public HListIterator listIterator();

    /**
     * Returns a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @param index  index of first element to be returned from the list iterator (by a call to the next method). 
     * @return a list iterator of the elements in this list (in proper sequence), starting at the specified position in this list.
     * @throws NullPointerException
     */
    public HListIterator listIterator(int index);

    /**
     * Removes the element at the specified position in this list (optional operation).
     * @param index  the index of the element to removed. 
     * @return the element previously at the specified position.
     * @throws IndexOutOfBoundsException
     */
    public Object remove(int index);

    /**
     * Replaces the element at the specified position in this list with the specified element (optional operation).
     * @param index Replaces the element at the specified position in this list with the specified element (optional operation).
     * @param element Replaces the element at the specified position in this list with the specified element (optional operation).
     * @return the element previously at the specified position.
     * @throws NullPointerException
     */
    public Object set(int index, Object element);

    /**
     * Returns a view of the portion of this list between the specified fromIndex, inclusive, and toIndex, exclusive.
     * @param fromIndex  low endpoint (inclusive) of the subList.
     * @param toIndex  low endpoint (inclusive) of the subList.
     * @return a view of the specified range within this list.
     * @throws NullPointerException
     * @throws IndexOutOfBoundsException
     */
    public HList subList(int fromIndex, int toIndex);
    
}