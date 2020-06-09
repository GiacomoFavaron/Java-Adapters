package adapter;

/**
 * An object that maps keys to values.
 */
public interface HMap {

    /**
     * Removes all mappings from this map (optional operation).
     * @throws UnsupportedOperationException clear is not supported by this map.
     */
    public void clear();

    /**
     * Returns true if this map contains a mapping for the specified key.
     * @param key key whose presence in this map is to be tested. 
     * @return true if this map contains a mapping for the specified key.
     * @throws ClassCastException if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException if the key is null and this map does not not permit null values (optional).
     */
    public boolean containsKey(Object key);

    /**
     * Returns true if this map maps one or more keys to the specified value. 
     * @param value value whose presence in this map is to be tested. 
     * @return true if this map maps one or more keys to the specified value.
     * @throws ClassCastException if the value is of an inappropriate type for this map (optional).
     * @throws NullPointerException if the value is null and this map does not not permit null values (optional).
     */
    public boolean containsValue(Object value);

    /**
     * Returns a set view of the mappings contained in this map. 
     * @return  a set view of the mappings contained in this map.
     */
    public HSet entrySet();

    /**
     * Compares the specified object with this map for equality. Returns true if the given object is also a map and the two Maps represent the same mappings. 
     * @param o object to be compared for equality with this map.
     * @return true if the specified object is equal to this map.
     */
    public boolean equals(Object o);

    /**
     * Returns the value to which this map maps the specified key. 
     * Returns null if the map contains no mapping for this key. 
     * @param key key whose associated value is to be returned.
     * @return the value to which this map maps the specified key, or null if the map contains no mapping for this key. 
     * @throws ClassCastException if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException key is null and this map does not not permit null keys (optional).
     */
    public Object get(Object key);

    /**
     * Returns the hash code value for this map. 
     * @return the hash code value for this map.
     */
    public int hashCode();

    /**
     * Returns true if this map contains no key-value mappings. 
     * @return true if this map contains no key-value mappings.
     */ 
    public boolean isEmpty();

    /**
     * Returns a set view of the keys contained in this map. 
     * @return a set view of the keys contained in this map.
     */
    public HSet keySet();

    /**
     * Associates the specified value with the specified key in this map (optional operation).
     * @param key key with which the specified value is to be associated.
     * @param value value to be associated with the specified key. 
     * @return previous value associated with specified key, or null if there was no mapping for key. A null return can also indicate that the map previously associated null with the specified key, if the implementation supports null values. 
     * @throws UnsupportedOperationException if the put operation is not supported by this map. 
     * @throws ClassCastException if the class of the specified key or value prevents it from being stored in this map. 
     * @throws IllegalArgumentException if some aspect of this key or value prevents it from being stored in this map. 
     * @throws NullPointerException this map does not permit null keys or values, and the specified key or value is null.
     */
    public Object put(Object key, Object value);

    /**
     * Copies all of the mappings from the specified map to this map (optional operation). The effect of this call is equivalent to that of calling put(k, v) on this map once for each mapping from key k to value v in the specified map. The behavior of this operation is unspecified if the specified map is modified while the operation is in progress. 
     * @param t Mappings to be stored in this map. 
     * @throws UnsupportedOperationException if the putAll method is not supported by this map. 
     * @throws ClassCastException if the class of a key or value in the specified map prevents it from being stored in this map. 
     * @throws IllegalArgumentException some aspect of a key or value in the specified map prevents it from being stored in this map. 
     * @throws NullPointerException the specified map is null, or if this map does not permit null keys or values, and the specified map contains null keys or values.
     */
    public void putAll(HMap t);

    /**
     * Removes the mapping for this key from this map if it is present (optional operation).
     * @param key key whose mapping is to be removed from the map. 
     * @return previous value associated with specified key, or null if there was no mapping for key. 
     * @throws ClassCastException if the key is of an inappropriate type for this map (optional).
     * @throws NullPointerException if the key is null and this map does not not permit null keys (optional). 
     * @throws UnsupportedOperationException if the remove method is not supported by this map.
     */
    public Object remove(Object key);

    /**
     * Returns the number of key-value mappings in this map. If the map contains more than Integer.MAX_VALUE elements, returns Integer.MAX_VALUE. 
     * @return  the number of key-value mappings in this map.
     */
    public int size();

    /**
     * Returns a collection view of the values contained in this map. The collection is backed by the map, so changes to the map are reflected in the collection, and vice-versa. If the map is modified while an iteration over the collection is in progress, the results of the iteration are undefined. The collection supports element removal, which removes the corresponding mapping from the map, via the Iterator.remove, Collection.remove, removeAll, retainAll and clear operations. It does not support the add or addAll operations. 
     * @return  a collection view of the values contained in this map.
     */
    public HCollection values();

    /**
     * A map entry (key-value pair).
     */
    public interface HEntry {

        /**
         * Compares the specified object with this entry for equality.
         * @param o  object to be compared for equality with this map entry.
         * @return true if the specified object is equal to this map entry.
         */
        public boolean equals(Object o);

        /**
         * Returns the key corresponding to this entry.
         * @return the key corresponding to this entry.
         */
        public Object getKey();

        /**
         * Returns the value corresponding to this entry. If the mapping has been removed from the backing map (by the iterator's remove operation), the results of this call are undefined. 
         * @return  the value corresponding to this entry.
         */  
        public Object getValue();

        /**
         * Returns the value corresponding to this entry.
         * @return  the hash code value for this map entry.
         */        
        public int hashCode();

        /**
         * Replaces the value corresponding to this entry with the specified value (optional operation).
         * @param value  new value to be stored in this entry.
         * @return old value corresponding to the entry. 
         * @throws UnsupportedOperationException if the put operation is not supported by the backing map. 
         * @throws ClassCastException if the class of the specified value prevents it from being stored in the backing map. 
         * @throws IllegalArgumentException if some aspect of this value prevents it from being stored in the backing map. 
         * @throws NullPointerException the backing map does not permit null values, and the specified value is null.
         */
        public Object setValue(Object value);

    }
}