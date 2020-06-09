package adapter;

import java.util.Enumeration;
import java.util.Hashtable;


/**
 * Adapter class from CLDC 1.1 Hashtable to JSE 1.4.2 Map (interface HMap). This class implements an Object Adapter, therefore it stores a Hastable instance which is used by the Map's methods. This implementation does not allow null keys and values.
 */


public class MapAdapter implements HMap {

    private Hashtable h = new Hashtable();

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's size() method
     */
    @Override
    public void clear() {
        h.clear();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's containsKey(Object) method
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsKey(Object key) {
        return h.containsKey(key);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's contains(Object) method
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean containsValue(Object value) {
        return h.contains(value);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation returns a set containing the entries of the map and which is backed by the map. If the map is modified in any way other than through the iterator's remove() method while an iteration of the set is in progress, the changes won't be reflected in the iterator but the latter will not be invalidated. The iterator, in fact, keeps an enumeration of the keys present in the map when the iterator is instantiated. Therefore even if structural changes are made to the map, the iterator will continue to operate on the same enumeration of keys.
     */
    @Override
    public HSet entrySet() {
        return new EntrySet();
    }

    private class EntrySet extends SetAdapter {
    
        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public void clear() {
            MapAdapter.this.clear();
        }
    
        @Override
        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            HMap.HEntry e = (HMap.HEntry) o;
            return MapAdapter.this.containsKey(e.getKey());
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public HIterator iterator() {
            return new EntryIterator();
        }

        private class EntryIterator implements HIterator {

            private Enumeration keys = h.keys();
            private Object lastRetKey = null;

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
                return new Entry(lastRetKey, h.get(lastRetKey));
            }

            @Override
            public void remove() {
                // Se next non e' mai stato chiamato o remove e' gia' stato
                // chiamato dopo l'ultima chiamata a next.
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;  // Reset to null after removing
            }
            
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            HMap.HEntry e = (HMap.HEntry) o;
            return MapAdapter.this.remove(e.getKey()) != null;
        }

        @Override
        public int size() {
            return MapAdapter.this.size();
        }
    
    }

    /**
     * {@inheritDoc}
     * <p>This implementation first checks if the specified object is this map, if so it returns true. Then, it checks if the specified object is a map whose size is identical to the size of this map, if not, it it returns false. If so, it returns entrySet().equals(m.entrySet()), where m is the other map.
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof HMap)) {
            return false;
        }
        HMap m = (HMap) o;
        if (m.size() != size()) {
            return false;
        }
        try {
            return entrySet().equals(m.entrySet());
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
     * <p>This implementation calls the hashtable's get(Object o) method.
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Object get(Object key) {
        if(key == null) {
            throw new NullPointerException();
        }
        return h.get(key);

    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the entrySet, adding up each element's haschode and returning the result.
     */
    @Override
    public int hashCode() {
        int sum = 0;
        HSet s = entrySet();
        HIterator iterator = s.iterator();
        while(iterator.hasNext()) {
            HEntry e = (Entry) iterator.next();
            sum += e.hashCode();
        }
        return sum;
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's isEmpty() method.
     */
    @Override
    public boolean isEmpty() {
        return h.isEmpty();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation returns a set containing the keys of the map and which is backed by the map. If the map is modified in any way other than through the iterator's remove() method while an iteration of the set is in progress, the changes won't be reflected in the iterator but the latter will not be invalidated. The iterator, in fact, keeps an enumeration of the keys present in the map when the iterator is instantiated. Therefore even if structural changes are made to the map, the iterator will continue to operate on the same enumeration of keys.
     */
    @Override
    public HSet keySet() {
        return new KeySet();
    }

    private class KeySet extends EntrySet {

        @Override
        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.containsKey(o);
        }

        @Override
        public HIterator iterator() {
            return new KeyIterator();
        }

        private class KeyIterator implements HIterator {

            private Enumeration keys = h.keys();
            private Object lastRetKey = null;

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
                return lastRetKey;
            }

            @Override
            public void remove() {
                // Se next non e' mai stato chiamato o remove e' gia' stato
                // chiamato dopo l'ultima chiamata a next.
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;  // Reset to null after removing
            }
            
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.remove(o) != null;
        }

    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's put(Object, Object) method.
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Object put(Object key, Object value) {
        if(key == null || value == null) {
            throw new NullPointerException();
        }
        return h.put(key, value);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation iterates over the entrySet of the map passed as the parameter and calls the hashtable's put(Object, Object) method for each of the elements of that entrySet.
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public void putAll(HMap m) {
        if(m == null) {
            throw new NullPointerException();
        }
        HSet s = m.entrySet();
        HIterator iterator = s.iterator();
        while(iterator.hasNext()) {
            HEntry e = (Entry) iterator.next();
            put(e.getKey(), e.getValue());
        }
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's remove(Object) method.
     * @throws NullPointerException {@inheritDoc}
     */
    @Override
    public Object remove(Object key) {
        if(key == null) {
            throw new NullPointerException();
        }
        return h.remove(key);
    }

    /**
     * {@inheritDoc}
     * <p>This implementation calls the hashtable's size() method.
     */
    @Override
    public int size() {
        return h.size();
    }

    /**
     * {@inheritDoc}
     * <p>This implementation returns a collection containing the values of the map and which is backed by the map. If the map is modified in any way other than through the iterator's remove() method while an iteration of the set is in progress, the changes won't be reflected in the iterator but the latter will not be invalidated. The iterator, in fact, keeps an enumeration of the keys present in the map when the iterator is instantiated. Therefore even if structural changes are made to the map, the iterator will continue to operate on the same enumeration of keys.
     */
    @Override
    public HCollection values() {
        return new ValueCollection();
    }

    private class ValueCollection extends CollectionAdapter {

        @Override
        public boolean add(Object o) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public boolean addAll(HCollection c) {
            throw new UnsupportedOperationException();
        }
    
        @Override
        public void clear() {
            MapAdapter.this.clear();
        }

        @Override
        public boolean contains(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            return MapAdapter.this.containsValue(o);
        }

        public boolean equals(Object o){
            if (o == this) {
                return true;
            }
            if (!(o instanceof HCollection)) {
                return false;
            }
            HCollection c = (HCollection) o;
            if (c.size() != size()) {
                return false;
            }
            try {
                return containsAll(c);
            }
            catch (ClassCastException cce)   {
                return false;
            }
            catch (NullPointerException npe) {
                return false;
            }
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public HIterator iterator() {
            return new ValueIterator();
        }

        private class ValueIterator implements HIterator {

            private Enumeration keys = h.keys();
            private Object lastRetKey = null;

            @Override
            public boolean hasNext() {
                return keys.hasMoreElements();
            }

            @Override
            public Object next() {
                lastRetKey = keys.nextElement(); // Lancia NoSuchElementException
                return MapAdapter.this.get(lastRetKey);
            }

            @Override
            public void remove() {
                // Se next non e' mai stato chiamato o remove e' gia' stato
                // chiamato dopo l'ultima chiamata a next.
                if(lastRetKey == null) {
                    throw new IllegalStateException();
                }
                MapAdapter.this.remove(lastRetKey);
                lastRetKey = null;  // Reset to null after removing
            }
            
        }

        @Override
        public boolean remove(Object o) {
            if(o == null) {
                throw new NullPointerException();
            }
            boolean flag = false;
            HSet es = MapAdapter.this.entrySet();
            HIterator it = es.iterator();
            while(it.hasNext()) {
                HMap.HEntry e = (HMap.HEntry) it.next();
                if(e.getValue().equals(o)) {
                    if(MapAdapter.this.remove(e.getKey()) != null) {
                        flag = true;
                    }
                } 
            }
            return flag;
        }

        @Override
        public int size() {
            return MapAdapter.this.size();
        }

    }

    /**
     * {@inheritDoc}
     */
    static class Entry implements HMap.HEntry {
        
        private Object key = null;
        private Object value = null;

        Entry(Object key, Object value) {
            if(key == null || value == null) {
                throw new NullPointerException();
            }
            this.key = key;
            this.value = value;
        }

        public Object getKey() {
            return this.key;
        }

        public Object getValue() {
            return this.value;
        }

        public Object setValue(Object value) {
            if(value == null) {
                throw new NullPointerException();
            }
            Object tmp = this.value;
            this.value = value;
            return tmp;
        }

        public boolean equals(Object o) {
            Entry e = (Entry) o;
            if((getKey()==null ?
            e.getKey()==null : getKey().equals(e.getKey()))  &&
           (getValue()==null ?
            e.getValue()==null : getValue().equals(e.getValue()))) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (getKey()==null   ? 0 : getKey().hashCode()) ^
            (getValue()==null ? 0 : getValue().hashCode());
        }

    }

}