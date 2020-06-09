package adapter;

import java.util.NoSuchElementException;

/**
 * An iterator over a collection.
 */
public interface HIterator {

    /**
     * Returns true if the iteration has more elements.
     * @return true if the iterator has more elements.
     */
    public boolean hasNext();

    /**
     * Returns the next element in the iteration.
     * @return the next element in the iteration.
     * @throws NoSuchElementException iteration has no more elements.
     */
    public Object next();

    /**
     * Removes from the underlying collection the last element returned by the iterator.
     * @throws IllegalStateException if the next method has not yet been called, or the remove method has already been called after the last call to the next method.
     */
    public void remove();
    
}