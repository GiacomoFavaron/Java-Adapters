package adapter;

import java.util.NoSuchElementException;
import java.util.Vector;

/**
 * Adapter class from CLDC 1.1 Vector to JSE 1.4.2 List (interface HList).
 * This implementation does not allow null elements.
 */

public class ListAdapter implements HList {

    private Vector v = new Vector();

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void add(int index, Object element) {
        if(element == null) {
            throw new NullPointerException();
        }
        try {
            v.insertElementAt(element, index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean add(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        v.addElement(o);
        return true;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean addAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(o); // Contiene controllo null
            flag = true;
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean addAll(int index, HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        if(index < 0 || index > size()) {
            throw new IndexOutOfBoundsException();
        }
        boolean flag = false;
        HIterator it = c.iterator();
        while(it.hasNext()) {
            Object o = it.next();
            add(index, o); // Contiene controllo null
            flag = true;
            index++;
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void clear() {
        v.removeAllElements();
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean contains(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.contains(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        HIterator it = c.iterator();
        while(it.hasNext()) {
            if(!contains(it.next()))
                return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if(o == this) {
            return true;
        }
        if (!(o instanceof HList)) {
            return false;
        }

        HListIterator it1 = listIterator();
        HListIterator it2 = ((HList) o).listIterator();
        while (it1.hasNext() && it2.hasNext()) {
            Object o1 = it1.next();
            Object o2 = it2.next();
            if (!(o1==null ? o2==null : o1.equals(o2)))
                return false;
        }
        return !(it1.hasNext() || it2.hasNext());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object get(int index) {
        try {
            return v.elementAt(index);
        }
        catch(ArrayIndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        int hashCode = 1;
        HIterator it = iterator();
        while (it.hasNext()) {
            Object obj = it.next();
            hashCode = 31*hashCode + (obj==null ? 0 : obj.hashCode());
        }
        return hashCode;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int indexOf(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.indexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isEmpty() {
        return v.isEmpty();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HIterator iterator() {
        return new Iterator();
    }

    private class Iterator implements HIterator {

        protected int cursor = 0;
        protected int lastRet = -1;

        public boolean hasNext() {
            return cursor != size();
        }

        public Object next() {
            if(!hasNext()) {
                throw new NoSuchElementException();
            }
            Object o = get(cursor);
            lastRet = cursor;
            cursor++;
            return o;
        }

        public void remove() {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ListAdapter.this.remove(lastRet);
            if (lastRet < cursor)
                cursor--;
            lastRet = -1;
        }
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public int lastIndexOf(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.lastIndexOf(o);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HListIterator listIterator() {
        return new ListIterator(0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public HListIterator listIterator(int index) {
        return new ListIterator(index);
    }

    private class ListIterator extends Iterator implements HListIterator {
        
        ListIterator(int index) {
            cursor = index;
        }

        public void add(Object o) {
            ListAdapter.this.add(cursor, o);
            cursor++;
            lastRet = -1;
        }

        public boolean hasPrevious() {
            return cursor != 0;
        }

        public Object previous() {
            if(!hasPrevious()) {
                throw new NoSuchElementException();
            }
            cursor--;
            Object o = get(cursor);
            lastRet = cursor;
            return o;
        }

        public int nextIndex() {
            return cursor;
        }

        public int previousIndex() {
            return cursor-1;
        }

        public void set(Object o) {
            if (lastRet < 0) {
                throw new IllegalStateException();
            }
            ListAdapter.this.set(lastRet, o); //Da testare indici limite
        }

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object remove(int index) {
        Object o = get(index);  // Contiene controllo bounds
        v.removeElementAt(index);
        return o;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean remove(Object o) {
        if(o == null) {
            throw new NullPointerException();
        }
        return v.removeElement(o);
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean removeAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator cit = c.iterator();
        while(cit.hasNext()) {
            if(remove(cit.next())) { // Contiene controllo null
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean retainAll(HCollection c) {
        if(c == null) {
            throw new NullPointerException();
        }
        boolean flag = false;
        HIterator it = iterator();
        while(it.hasNext()) {
            Object o = it.next();
            if(!c.contains(o)) {
                it.remove(); // Contiene controllo null
                flag = true;
            }
        }
        return flag;
    }

    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Object set(int index, Object element) {
        if(element == null) {
            throw new NullPointerException();
        }
        Object o = get(index); // Throws IndexOutOfBoundsException
        v.setElementAt(element, index);
        return o;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return v.size();    //Integer.maxValue??
    }

    /**
     * {@inheritDoc}
     */
    public HList subList(int fromIndex, int toIndex) {
        return new SubList(this, fromIndex, toIndex);
    }

    private class SubList extends ListAdapter {
        private int offset = -1;
        private int size = -1;
        private ListAdapter l = null;
        
        public SubList(ListAdapter list, int fromIndex, int toIndex) {
            if (fromIndex < 0 || toIndex > list.size() || fromIndex > toIndex) {
                throw new IndexOutOfBoundsException();
            }
            l = list;
            offset = fromIndex;
            size = toIndex - fromIndex;
        }
    
        private void boundCheck(int index) {
            if(index < 0 || index >= size) {
                throw new IndexOutOfBoundsException();
            }
        }
    
        private void boundCheckForAdd(int index) {
            if(index < 0 || index > size) {
                throw new IndexOutOfBoundsException();
            }
        }
    
        public void add(int index, Object element) {
            boundCheckForAdd(index);
            l.add(offset + index, element);
            size++;
        }
    
        public boolean add(Object o) {
            l.add(offset + size, o);
            size++;
            return true;
        }
    
        public boolean addAll(HCollection c) {
            return addAll(size, c);
        }
    
        public boolean addAll(int index, HCollection c) {
            boundCheckForAdd(index);
            int cSize = c.size();
            if(cSize == 0) {
                return false;
            }
            l.addAll(offset + index, c);
            size += cSize;
            return true;
        }
    
        public void clear() {
            HIterator it = iterator();
            while(it.hasNext()) {
                it.next();
                it.remove();
            }
        }

        public boolean contains(Object o) {
            return indexOf(o) != -1;
        }
    
        public Object get(int index) {
            boundCheck(index);
            return l.get(offset + index);
        }
    
        public int indexOf(Object o) {
            int index = l.indexOf(o);
            if(index < offset || index >= (offset + size)) {
                return -1;
            }
            return index - offset;
        }
    
        public boolean isEmpty() {
            return size == 0;
        }
    
        public HIterator iterator() {
            return new SubListIterator(0);
        }
    
        public int lastIndexOf(Object o) {
            int index = l.lastIndexOf(o);
            if(index < offset || index >= (offset + size)) {
                return -1;
            }
            return index - offset;
        }
    
        public HListIterator listIterator() {
            return new SubListIterator(0);
        }
    
        public HListIterator listIterator(int index) {
            return new SubListIterator(index);
        }
    
        private class SubListIterator implements HListIterator {
            private HListIterator it = null;
    
            SubListIterator(int index) {
                it = ListAdapter.this.listIterator(index+offset);
            }
    
            public boolean hasNext() {
                return nextIndex() < size;
            }
    
            public Object next() {
                if(hasNext()) { // hasNext() di subList, che controlla indici
                    return it.next(); // next usa l'hasNext() di ListAdapter
                }
                else {
                    throw new NoSuchElementException();
                }
            }
    
            public boolean hasPrevious() {
                return previousIndex() >= 0;
            }
    
            public Object previous() {
                if(hasPrevious()) {
                    return it.previous();
                }
                else {
                    throw new NoSuchElementException();
                }
            }
    
            public int nextIndex() {
                return it.nextIndex() - offset;
            }
    
            public int previousIndex() {
                return it.previousIndex() - offset;
            }
    
            public void remove() {
                it.remove();
                size--;
            }
    
            public void set(Object o) {
                it.set(o);
            }
    
            public void add(Object o) {
                it.add(o);
                size++;
            }
        }
    
        public Object remove(int index) {
            boundCheck(index);
            Object o = l.remove(offset + index);
            size--;
            return o;
        }
    
        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            HIterator it = iterator();
            while(it.hasNext()) {
                if(it.next().equals(o)) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }
    
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
    
        public boolean retainAll(HCollection c) {
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
    
        public Object set(int index, Object element) {
            if(element == null) {
                throw new NullPointerException();
            }
            boundCheck(index);
            return l.set(offset + index, element);
        }
    
        public int size() {
            return size;
        }
    }
    
    /**
     * {@inheritDoc}
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
     * @throws NullPointerException {@inheritDoc}
     */
    public Object[] toArray(Object[] a) {
        if(a == null) {
            throw new NullPointerException();
        }
        Object[] v = new Object[a.length];
        HIterator it = iterator();
        if(a.length >= size()) {
            for(int i = 0; i < a.length && it.hasNext(); i++) {
                v[i] = it.next();
            }
            return v;
        }
        v = new Object[size()];
        for(int i = 0; i < size(); i++) {
            if(it.hasNext())
                v[i] = it.next();
            else
                v[i] = null;
        }
        return v;
    }

}