package adapter;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Adapter class from CLDC 1.1 Hashtable to JSE 1.4.2 Set (interface HSet).
This class implements an Object Adapter, therefore it stores a Hastable instance which is used by the Set's methods. This implementation does not allow null elements.
 */

public class SetAdapter implements HSet {

    private Hashtable hashtable = new Hashtable();

    /**
     * {@inheritDoc}
     * <p>This implementations checks if the object is already contained in the set using contains(Object), and if it isn't it adds the object to the set calling the hashtable's put(Object, Object) method.
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean add(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        if(contains(o)) {
            return false;
        }
        hashtable.put(o, o);
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the collection and adds its elements to the set using add(Object).
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean addAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(add(o)) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's clear() method.
     */
    public void clear() {
        hashtable.clear();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation call's the hashtable's containsKey(Object) method.
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean contains(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return hashtable.containsKey(o);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the collection and checks if the elements are already in the set using contains(Object).
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean containsAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(!contains(o)) {
                return false;
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation first checks if the specified object is this set, if so it returns true. Then, it checks if the specified object is a set whose size is identical to the size of this set, if not, it it returns false. If so, it returns containsAll((Collection) o).
     */
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (!(o instanceof HSet)) {
            return false;
        }
        HSet s = (HSet) o;
        if (s.size() != size()) {
            return false;
        }
        try {
            return containsAll(s);
        }
        catch (ClassCastException cce)   {
            return false;
        }
        catch (NullPointerException npe) {
            return false;
        }
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the set, adding up each element's haschode and returning the result.
     */
    public int hashCode(){
        int hashCode = 0;
        HIterator it = iterator();
        while (it.hasNext()) {
            hashCode += it.next().hashCode();
        }
        return hashCode;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's isEmpty() method.
     */
    public boolean isEmpty(){
        return hashtable.isEmpty();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation uses the enumeration returned by the hashtable's keys() method to iterate over the set. If the set is modified in any way other than through the iterator's remove() method while an iteration is in progress, the changes won't be reflected in the iterator but the latter will not be invalidated. The iterator, in fact, keeps an enumeration of the keys present in the set when the iterator is instantiated. Therefore even if structural changes are made to the map, the iterator will continue to operate on the same enumeration of keys.
     */
    public HIterator iterator(){
        return new SetIterator();
    }

    private class SetIterator implements HIterator {
        private Enumeration keys = hashtable.keys();
        private Object lastRetKey = null;

        public boolean hasNext() {
            return keys.hasMoreElements();
        }

        public Object next() {
            lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
            return lastRetKey;
        }

        public void remove() {
            // Se next non e' mai stato chiamato o remove e' gia' stato
            // chiamato dopo l'ultima chiamata a next.
            if(lastRetKey == null) {
                throw new IllegalStateException();
            }
            SetAdapter.this.remove(lastRetKey);
            lastRetKey = null;  // Reset to null after removing
        }
    }

    /**
     * {@inheritDoc}
     * <p>This implementations checks if the object is already contained in the set using contains(Object), and if it isn't it removes the object from the set calling the hashtable's remove(Object) method.
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean remove(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        if(!contains(o)) {
            return false;
        }
        hashtable.remove(o);
        return true;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the collection and removes its elements from the set using remove(Object).
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean removeAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator cit = c.iterator();
        while(cit.hasNext()) {
            if(remove(cit.next())) {
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the set and removes from the set the elements which are not contained in the collection (using the iterator's remove() method).
     * @throws NullPointerException {@inheritDoc}
     */
    public boolean retainAll(HCollection c){
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(!c.contains(o)) {
                it.remove();
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's size() method.
     */
    public int size() {
        return hashtable.size();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the set and adds the elements returned by next() to the array. The length of the array is equal to the size of the set.
     */
    public Object[] toArray(){
        Object[] v = new Object[size()];
        HIterator it = iterator();
        for(int i = 0; it.hasNext(); i++) {
            v[i] = it.next();
        }
        return v;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the set and adds the elements returned by next() to the array. The length of the array is equal to the one of the array passed as the parameter. If it's length is greater than the set's size the elements whose index is greater or equal to the set's size are set to null.
     * @throws NullPointerException {@inheritDoc}
     */
    public Object[] toArray(Object[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        Object[] v;
        HIterator it = iterator();
        if(a.length >= size()) {
            v = new Object[a.length];
            int i = 0;
            while(it.hasNext()) {
                v[i++] = it.next();
            }
        }
        else {
            v = new Object[size()];
            for(int i = 0; i < size() && it.hasNext(); i++) {
                v[i] = it.next();
            }
        }
        return v;
    }

}