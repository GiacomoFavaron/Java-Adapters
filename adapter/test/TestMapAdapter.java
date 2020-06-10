package adapter.test;

import adapter.*;
import adapter.HMap.HEntry;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test case class for MapAdapter
 * @safe.summary This classes tests all the methods of the MapAdapter class
 */
public class TestMapAdapter {

    private HMap m = null;

    /**
     * Setup method, initializes the MapAdapter
     */

    @Before
    public void setUp() {
        m = new MapAdapter();
    }

    /**
     * Test clear
     * @safe.precondition set initialized, 5 elements added, size greater than 0
     * @safe.postcondition set is empty, size is 0
     * @safe.testcases Tests that after calling clear size is 0
     */
    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
        m.clear();
        assertEquals(0, m.size());
    }

    /**
     * Test containsKey key contained
     * @safe.precondition set initialized, element with key 1 added to the map
     * @safe.postcondition None
     * @safe.testcases Test that containsKey(1) returns true.
     */
    @Test
    public void testContainsKeyTrue() {
        m.put(1, new Object());
        assertTrue(m.containsKey(1));
    }

    /**
     * Test containsKey key not contained
     * @safe.precondition set initialized, 5 elements with keys from 0 to 4 added to the map
     * @safe.postcondition None
     * @safe.testcases Test that containsKey(5) returns false.
     */
    @Test
    public void testContainsKeyFalse() {
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
        assertFalse(m.containsKey(5));
    }

	/**
	* Test containsKey with null
	* @safe.precondition set initialized
	* @safe.postcondition NullPointerException thrown
	* @safe.testcases Test that when calling containsKey(null) NullPointerException is thrown.
	*/
    @Test(expected = NullPointerException.class)
    public void testContainsKeyWithNull() {
        m.containsKey(null);
    }

    /**
     * Test containsValue with value contained
     * @safe.precondition set initialized, Object o created, element with value o added to the map
     * @safe.postcondition None
     * @safe.testcases Test that containsValue(o) returns true.
     */
    @Test
    public void testContainsValueTrue() {
        Object o = new Object();
        m.put(1, o);
        assertTrue(m.containsValue(o));
    }

    /**
     * Test containsValue with value not contained
     * @safe.precondition set initialized, Object o created
     * @safe.postcondition None
     * @safe.testcases Test that containsValue(o) returns false.
     */
    @Test
    public void testContainsValueFalse() {
        Object o = new Object();
        assertFalse(m.containsValue(o));
    }

	/**
	* Test containsValue with null
	* @safe.precondition set initialized
	* @safe.postcondition NullPointerException thrown
	* @safe.testcases Test that when calling containsValue(null) NullPointerException is thrown.
	*/
    @Test(expected = NullPointerException.class)
    public void testContainsValueWithNull() {
        m.containsValue(null);
    }

	/**
	* Test entrySet
	* @safe.precondition set initialized, call m.entrySet and put the returned set in variable s
	* @safe.postcondition s contains the set view of the mappings of the map
	* @safe.testcases Iterate over the set and check that all the elements of the set are contained in the map and their values are equal
	*/
    @Test
    public void testEntrySet() {
        for(int i = 0; i < 5; i++)
            m.put(i, new Object());
        HSet s = m.entrySet();
        HIterator it = s.iterator();
        while(it.hasNext()) {
            HEntry e = (HEntry) it.next();
            Object key = e.getKey();
            assertEquals(m.get(key), e.getValue());
        }
    }

	/**
	* Test entrySet propagation to backing map
	* @safe.precondition set initialized, two elements added to the map, call m.entrySet and put the returned set in variable s
	* @safe.postcondition The map's size is 1
	* @safe.testcases Test that removing an element from the map's entrySet affects the entryset's backing map (the size decreases)
	*/
    @Test
    public void testEntrySetPropagation() {
        m.put(Integer.valueOf(1), Integer.valueOf(2));
        m.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet set = m.entrySet();
        HIterator it = set.iterator();
        it.next();
        it.remove();
        assertEquals(1, m.size());
    }

    /**
     * Test equals with equal map
     * @safe.precondition set s initialized, otherSet initialized, same objects added to both maps
     * @safe.postcondition None
     * @safe.testcases Test that m.equals(otherSet) returns true.
     */
    @Test
    public void testEqualsTrue() {
        HMap otherMap = new MapAdapter();
        m.put(Integer.valueOf(1), Integer.valueOf(2));
        otherMap.put(Integer.valueOf(1), Integer.valueOf(2));
        assertTrue(m.equals(otherMap));
    }

    /**
     * Test equals with different sets
     * @safe.precondition set s initialized, otherSet initialized, one object added to otherSet
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(otherSet) returns false.
     */
    @Test
    public void testEqualsFalse() {
        HMap otherMap = new MapAdapter();
        otherMap.put(Integer.valueOf(1), new Object());
        assertFalse(m.equals(otherMap));
    }

    /**
     * Test equals with empty sets
     * @safe.precondition set s initialized, otherSet initialized, both empty
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(otherSet) returns true.
     */
	@Test
    public void testEqualsEmptyList() {
		HSet otherSet = new SetAdapter();
        assertTrue(s.equals(otherSet));
	}

    /**
     * Test equals with itself
     * @safe.precondition set s initialized
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(s) returns true.
     */
	@Test
	public void testEqualsEqualToItself() {
		assertTrue(s.equals(m));
	}

    /**
     * Test equals with null
     * @safe.precondition set s initialized
     * @safe.postcondition none
     * @safe.testcases Test that calling s.equals(null) returns false
     */
    @Test(expected = NullPointerException.class)
    public void testEqualsWithNull() {
        m.equals(null);
    }

    /**
     * Test get is key contained
     * @safe.precondition set s initialized, element of key 3 added
     * @safe.postcondition none
     * @safe.testcases Test that get(3) returns o
     */
    @Test
    public void testGetWithKeyContained() {
        Object o = new Object();
        m.put(Integer.valueOf(3), o);
        assertEquals(m.get(0), o);
    }

    /**
     * Test get if key not contained
     * @safe.precondition set s initialized
     * @safe.postcondition none
     * @safe.testcases Test that get(3) returns null
     */
    @Test
    public void testGetWithKeyNotContained() {
        assertEquals(null, m.get(3)); 
    }

    /**
     * Test get with null
     * @safe.precondition set s initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that get(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testGetWithNull() {
        m.get(null);
    }

    /**
     * Test hashcode equal sets
     * @safe.precondition set s initialized, otherSet initialized, same elements added
     * @safe.postcondition None
     * @safe.testcases Test that if sets are equal also hashcodes are equal 
     */
    @Test
    public void testHashCodeTrue() {
        HMap otherMap = new MapAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherMap.put(Integer.valueOf(i), o);
            m.put(Integer.valueOf(i), o);
        }
		assertEquals(m, otherMap);
		assertTrue(m.hashCode() == otherMap.hashCode());
    }

    /**
     * Test hashcode different sets
     * @safe.precondition set s initialized, otherSet initialized, elements added to s
     * @safe.postcondition None
     * @safe.testcases Test that if sets are not equal also hashcodes are not equal 
     */
    @Test
    public void testHashCodeFalse() {
        HMap otherMap = new MapAdapter();
        for(int i = 0; i < 5; i++) {
            m.put(Integer.valueOf(i), new Object());
        }
		assertFalse(m.equals(otherMap));
		assertFalse(m.hashCode() == otherMap.hashCode());
	}

    /**
     * Test isEmpty with empty set
     * @safe.precondition set initialized empty
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns true
     */
    @Test
    public void testIsEmptyTrue() {
        assertTrue(m.isEmpty());
    }

    /**
     * Test isEmpty with non empty set
     * @safe.precondition set initialized, one element added
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns false
     */
    @Test
    public void testIsEmptyFalse() {
        m.put(new Object(), new Object());
        assertFalse(m.isEmpty());
    }

	/**
	* Test keySet
	* @safe.precondition set initialized, call m.keySet and put the returned set in variable s
	* @safe.postcondition s contains the set view of the keys of the map
	* @safe.testcases Iterate over the set and check that all the elements of the set are contained in the map
	*/
    @Test
    public void testKeySet() {
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
        HSet s = m.keySet();
        HIterator it = s.iterator();
        while(it.hasNext()) {
            assertTrue(m.containsKey(it.next()));
        }
    }

    /**
	* Test keySet propagation to backing map
	* @safe.precondition set initialized, two elements added to the map, call m.keySet and put the returned set in variable s
	* @safe.postcondition The map's size is 1
	* @safe.testcases Test that removing an element from the map's keySet affects the kerySet's backing map (the size decreases)
	*/
    @Test
    public void testKeySetPropagation() {
        m.put(Integer.valueOf(1), Integer.valueOf(2));
        m.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet set = m.keySet();
        HIterator it = set.iterator();
        it.next();
        it.remove();
        assertEquals(1, m.size());
    }

    /**
     * Test put with new key
     * @safe.precondition Set initialized
     * @safe.postcondition key/value pair added to the map
     * @safe.testcases Test when using put to add a new key/value pair the size increases and the map then contains the mapping.
     */
    @Test
    public void testPutNewKey() {
        Object o = new Object();
        m.put(Integer.valueOf(1), o);
        assertEquals(1, m.size());
        assertTrue(m.containsKey(Integer.valueOf(1)));
    }

    /**
     * Test put with key already present
     * @safe.precondition Set initialized, put an element in the map
     * @safe.postcondition value of the mapping replaced
     * @safe.testcases Test when using put with a key already present in the map the value of the existing map is replaced with the value passed to put.
     */
    @Test
    public void testPutReplace() {
        Object o1 = new Object();
        m.put(Integer.valueOf(1), o1);
        assertEquals(m.get(Integer.valueOf(1)), o1);
        Object o2 = new Object();
        m.put(Integer.valueOf(1), o2);
        assertEquals(m.get(Integer.valueOf(1)), o2);
    }

	/**
     * Test put with null key
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling put(null, Object) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNullKey() {
        m.put(null, new Object());
    }

	/**
     * Test put with null value
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling put(Object, null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNullValue() {
        m.put(new Object(), null);
    }
    
	/**
     * Test put with null key and value
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling put(null, null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyAndValue() {
        m.put(null, null);
    }

    /**
     * Test putAll
     * @safe.precondition Set m initialized, otherMap initialized, 3 elements added to otherMap
     * @safe.postcondition key/value pair added to the map m
     * @safe.testcases Test when using putAll all the key/values are added or replaced in the map
     */
    @Test
    public void testPutAll() {
        HMap otherMap = new MapAdapter();
        for(int i = 0; i < 3; i++) {
            otherMap.put(Integer.valueOf(i), new Object());
        }
        m.put(Integer.valueOf(2), new Object());
        m.putAll(otherMap);
        for(int i = 0; i < 3; i++) {
            assertTrue(m.containsKey(i));
        }
    }

	/**
     * Test putAll with null
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling putAll(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testPutAllWithNull() {
        m.putAll(null);
    }

	/**
     * Test remove when object is contained
     * @safe.precondition set initialized, element added to the map
     * @safe.postcondition Element has been removed from the set
     * @safe.testcases Test that calling remove with the key of the element inserted removes that element from the set (contains returns false)
     */
    @Test
    public void testRemoveContained() {
        Object o = new Object();
        m.put(Integer.valueOf(1), o);
        m.remove(Integer.valueOf(1));
        assertFalse(m.containsKey(0));
    }

	/**
     * Test remove when object is not contained
     * @safe.precondition set initialized
     * @safe.postcondition None
     * @safe.testcases Test that calling remove with a key not present in the map returns null
     */
    @Test
    public void testRemoveNotContained() {
        assertEquals(m.remove(new Object()), null);
    }

	/**
     * Test putAll with null
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling remove(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        m.remove(null);
    }

  	 /**
     * Test size with an empty set 
     * @safe.precondition set initialized
     * @safe.postcondition None
     * @safe.testcases Test that calling size with an empty set returns 0
     */
    @Test
    public void testSizeEmpty() {
        assertEquals(0, m.size());
    }

     /**
     * Test size
     * @safe.precondition set initialized, 5 elements added to the set
     * @safe.postcondition None
     * @safe.testcases Test that calling size retrns 5
     */
    @Test
    public void testSize() {
        for(int i = 0; i < 5; i++) {
			m.put(new Object(), new Object());
        }
        assertEquals(5, m.size());
    }

	/**
	* Test values
	* @safe.precondition set initialized, call m.values and put the returned collection in variable c
	* @safe.postcondition c contains the collection view of the values of the map
	* @safe.testcases Iterate over the collection and check that all the elements of the collection are contained in the map
	*/
    @Test
    public void testValues() {
        for(int i = 0; i < 5; i++) {
            m.put(Integer.valueOf(i), new Object());
        }
        HCollection c = m.values();
        HIterator cit = c.iterator();
        while(cit.hasNext()) {
            assertTrue(m.containsValue(cit.next()));
        }
    }

    /**
	* Test values propagation to backing map
	* @safe.precondition set initialized, two elements added to the map, call m.values and put the returned collection in variable c
	* @safe.postcondition The map's size is 1
	* @safe.testcases Test that removing an element from the map's values collection affects the backing map (the size decreases)
	*/
    @Test
    public void testValuesPropagation() {
        m.put(Integer.valueOf(1), Integer.valueOf(2));
        m.put(Integer.valueOf(4), Integer.valueOf(5));
        HCollection c = m.values();
        HIterator it = c.iterator();
        it.next();
        it.remove();
        assertEquals(1, m.size());
    }

}