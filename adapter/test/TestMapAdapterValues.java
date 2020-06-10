package adapter.test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * Test case class for MapAdapter values, tests all functionalities of the Collection of values as a HCollection.
 */
public class TestMapAdapterValues {

    private HCollection c = null;
    private Object v1 = null;
    private Object v2 = null;
    private Object value1 = null;
    private Object value2 = null;

    /**
     * Setup (for all tests): initializes the HSet s returned by the method entrySet and containing 2 values. It also saves 4 values, 2 contained in s (v1, v2), 2 not contained in s (value1, value2).
     */

    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        c = map.values();
        HIterator it = c.iterator();
        v1 = it.next();
        v2 = it.next();
        MapAdapter map2 = new MapAdapter();
        map2.put(Integer.valueOf(10), Integer.valueOf(11));
        map2.put(Integer.valueOf(11), Integer.valueOf(12));
        HCollection s2 = map2.values();
        HIterator it2 = s2.iterator();
        value1 = it2.next();
        value2 = it2.next();
    }

    /**
     * Test add
     * @safe.precondition Setup
     * @safe.postcondition UnsupportedOperationException thrown
     * @safe.testcases Test that when calling add UnsupportedOperationException is thrown
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        c.add(new Object());
    }

     /**
     * Test addAll
     * @safe.precondition Setup
     * @safe.postcondition UnsupportedOperationException thrown
     * @safe.testcases Test that when calling addAll UnsupportedOperationException is thrown
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        c.add(new CollectionAdapter());
    }

    /**
     * Test clear
     * @safe.precondition setup
     * @safe.postcondition collection is empty, size is 0
     * @safe.testcases Tests that after calling clear size is 0
     */
    @Test
    public void testClear() {
        c.clear();
        assertEquals(0, c.size());
    }

    /**
     * Test contains value contained
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that contains(v1) returns true.
     */
    @Test
    public void testContainsTrue() {
        assertTrue(c.contains(v1));
    }

    /**
     * Test contains value not contained
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that contains(value1) returns false.
     */
    @Test
    public void testContainsFalse() {
        assertFalse(c.contains(value1));
    }

    /**
     * Test contains with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that contains(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        c.contains(null);
    }

    /**
     * Test containsAll with collection contained
     * @safe.precondition setup, collection coll initialized, v1 and v2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(coll) returns true
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(v2);
        assertTrue(c.containsAll(coll));
    }

    /**
     * Test containsAll with collection not contained
     * @safe.precondition setup, collection coll initialized, value1 and value2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(coll) returns false
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(value2);
        assertFalse(c.containsAll(coll));
    }

    /**
     * Test containsAll with collection partially contained
     * @safe.precondition setup, collection coll initialized, v1 and value2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(coll) returns false
     */
    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(value1);
        assertFalse(c.containsAll(coll));
    }

    /**
     * Test containsAll with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that containsAll(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        c.containsAll(null);
    }

    /**
     * Test equals with equal set
     * @safe.precondition setup, calls values to create otherCollection from map with the same values
     * @safe.postcondition None
     * @safe.testcases Test that c.equals(otherCollection) returns true.
     */
    @Test
    public void testEqualsTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HCollection otherCollection = map.values();
        assertEquals(c, otherCollection);
    }

    /**
     * Test equals with different sets
     * @safe.precondition setup, otherSet initialized, one object added to otherSet
     * @safe.postcondition None
     * @safe.testcases Test that c.equals(otherSet) returns false.
     */
    @Test
    public void testEqualsFalse() {
        HSet otherSet = new SetAdapter();
        otherSet.add(new Object());
        assertFalse(c.equals(otherSet));
    }

    /**
     * Test hashcode equal sets
     * @safe.precondition setup, calls values to create otherCollection from map with the same values
     * @safe.postcondition None
     * @safe.testcases Test that if sets are equal also hashcodes are equal 
     */
	@Test
    public void testHashCodeTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HCollection otherCollection = map.values();
        assertEquals(c, otherCollection);
		assertTrue(c.hashCode() == otherCollection.hashCode());
	}
    
    /**
     * Test hashcode different sets
     * @safe.precondition set s initialized, otherSet initialized, objects added to s
     * @safe.postcondition None
     * @safe.testcases Test that if sets are not equal also hashcodes are not equal 
     */
	@Test
    public void testHashCodeFalse() {
        HSet otherSet = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            otherSet.add(new Object());
        }
		assertFalse(c.equals(otherSet));
		assertFalse(c.hashCode() == otherSet.hashCode());
	}

   /**
     * Test isEmpty with empty set
     * @safe.precondition setup, call to clear
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns true
     */
    @Test
    public void TestIsEmptyTrue() {
        c.clear();
        assertTrue(c.isEmpty());
    }

     /**
     * Test isEmpty with non empty set
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns false
     */
    @Test
    public void testIsEmptyFalse() {
        assertFalse(c.isEmpty());
	}

    /**
     * Test iterator next and hasNext
     * @safe.precondition setup, iterator initialized, otherSet initialized
     * @safe.postcondition set s copied into otherSet
     * @safe.testcases Test that after iterating over the set s with the iterator's methods next and hasNext and copying the elements returned by next into otherSet, otherSet is equal to s.
     */
    @Test
    public void testIteratorNextAndHasNext() {
        HIterator it = c.iterator();
        HCollection otherCollection = new CollectionAdapter();
        while(it.hasNext()) {
            otherCollection.add(it.next());
        }
        assertEquals(c, otherCollection);
	}

    /**
     * Test iterator NoSuchElementException
     * @safe.precondition setup, iterator initialized
     * @safe.postcondition NoSuchElementException thrown
     * @safe.testcases Test that calling next 3 causes NoSuchElementException to be thrown
     */
	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        HIterator it = c.iterator();
        for(int i = 0; i < 3; i++) {
            it.next();
		}
	}
    
    /**
     * Test iterator remove
     * @safe.precondition setup
     * @safe.postcondition One element removed from the set
     * @safe.testcases Test that calling it.next() and it.remove() causes the size of the set to decrease by one
	 */
	@Test
    public void testIteratorRemove() {
		HIterator it = c.iterator();
		it.next();
        it.remove();
        assertEquals(1, c.size());
	}
    
	/**
     * Test remove when object is contained
     * @safe.precondition setup
     * @safe.postcondition value v1 has been removed from the set
     * @safe.testcases Test that calling remove(v1) removes that value from the set and returns true (contains(v1) returns false)
     */
    @Test
    public void testRemoveTrue() {
        assertTrue(c.remove(v1));
        assertEquals(false, c.contains(v1));
    }

	/**
     * Test remove when object isn't contained
     * @safe.precondition setup
     * @safe.postcondition s hasn't been modified
     * @safe.testcases Test that calling remove(value1) returns false
     */
    @Test
    public void testRemoveFalse() {
        assertFalse(c.remove(value1));
    }

    /**
     * Test remove with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that remove(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        c.remove(null);
    }

	/**
     * Test removeAll with collection contained in the set
     * @safe.precondition setup, collection coll initialized, v1 added to coll
     * @safe.postcondition all elements in the collection have been removed from the set
     * @safe.testcases Test that calling removeAll(coll) removes the elements of coll from the collection (iterating over the collection and calling contains on the elements of coll returns false)
     */
    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
		assertTrue(c.removeAll(coll));
		HIterator cit = coll.iterator();
		while(cit.hasNext()) {
			assertFalse(c.contains(cit.next()));
		}
    }

	/**
     * Test removeAll with coll not contained in the set
     * @safe.precondition setup, value1 and value2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that calling removeAll(coll) returns false and the size of the collection is two
     */
    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(value2);
		assertFalse(c.removeAll(coll));
        assertEquals(2, c.size());
    }

    /**
     * Test removeAll with coll partially contained in the set
     * @safe.precondition setup, value1 and v2 added to coll
     * @safe.postcondition v2 has been removed from the set
     * @safe.testcases Test that calling removeAll(coll) returns true and removes v2 from the collection (size of the collection becomes 1)
     */
    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(v2);
		assertTrue(c.removeAll(coll));
		assertEquals(1, c.size());
    }

    /**
     * Test removeAll with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that removeAll(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        c.removeAll(null);
    }

	/**
     * Test retainAll in a condition when all elements are retained
     * @safe.precondition setup, collection coll initailized, v1 and v2 added to coll
     * @safe.postcondition all objects are still in the set
     * @safe.testcases Test that calling retainAll(coll) retains all the elements of coll in the set (retainAll returns false, since the set hasn't been modified and the size of the set is still 2)
     */
    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(v2);
		assertFalse(c.retainAll(coll));
		assertEquals(2, c.size());
    }

	/**
     * Test retainAll in a condition when some elements are retained
     * @safe.precondition setup, collection coll initailized, v1 added to coll
     * @safe.postcondition One element retained
     * @safe.testcases Test that calling retainAll(coll) retains the one value in coll and removes the other one (retainAll returns true, since the set has been modified and the size of the set is now 1)
     */
    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
		assertTrue(c.retainAll(coll));
		assertEquals(1, c.size());
	}

    /**
     * Test retainAll in a condition when no elements are retained
     * @safe.precondition setup, collection coll initailized, value1 added to coll
     * @safe.postcondition The set is empty
     * @safe.testcases Test that calling retainAll(coll) retains no elements (retainAll returns true, since the set has been modified and the size of the set is now 0)
     */
	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection coll = new CollectionAdapter();
		coll.add(value1);
		assertTrue(c.retainAll(coll));
		assertEquals(0, c.size());
	}

    /**
     * Test retainAll with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that retainAll(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        c.retainAll(null);
	}

   	 /**
     * Test size with an empty set 
     * @safe.precondition setup, call to clear
     * @safe.postcondition None
     * @safe.testcases Test that calling size with an empty set returns 0
     */
    @Test
    public void testSizeEmpty() {
        c.clear();
        assertEquals(0, c.size());
    }

     /**
     * Test size
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that calling size retrns 2
     */
    @Test
    public void testSize() {
        assertEquals(2, c.size());
    }

    /**
     * Test toArray 
     * @safe.precondition setup
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray returns an array with the same elements as the set in the same order returned by the set iterator.
     */
    @Test
    public void testToArray() {
        Object[] setArray = c.toArray();
        HIterator it = c.iterator();
		int j = 0;
        while(it.hasNext()) {
			assertEquals(it.next(), setArray[j]);
			j++;
        }
    }

    /**
     * Test toArray(Object[]) with an array of length smaller than the size of the set
     * @safe.precondition setup, Object array param of length 1 initialized
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray(param) returns an array with the same elements as the set in the same order returned by the set iterator and of length the size of the set.
     */
    @Test
    public void testToArrayWithParameterSizeSmaller() {
        Object[] param = new Object[1];
        Object[] setArray = c.toArray(param);
        assertEquals(2, setArray.length);
        HIterator it = c.iterator();
		int j = 0;
        while(it.hasNext()) {
			assertEquals(it.next(), setArray[j]);
			j++;
        }
    }

    /**
     * Test toArray(Object[]) with an array of length greater than the size of the set
     * @safe.precondition setup, Object array param of length 10 initialized
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray(param) returns an array with the same elements as the set in the same order returned by the set iterator and of length the length of param. The elements of the array after the index s.size()-1 are set to null.
     */
    @Test
    public void testToArrayWithParameterSizeLonger() {
        Object[] param = new Object[10];
        Object[] setArray = c.toArray(param);
        assertEquals(10, setArray.length);
        HIterator it = c.iterator();
		int j = 0;
        while(it.hasNext()) {
			assertEquals(it.next(), setArray[j]);
			j++;
        }
        for(int i = c.size(); i < param.length; i++) {
            assertEquals(setArray[i], null);
        }
    }

	/**
     * Test toArray with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling toArray(null) throws NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        c.toArray(null);
    }

}