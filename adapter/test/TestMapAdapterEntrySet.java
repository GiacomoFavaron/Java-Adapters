package adapter.test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

import java.util.NoSuchElementException;

/**
 * Test case class for MapAdapter entrySet
 * @safe.summary This class tests all functionalities of the set returned by the method entrySet() of a MapAdapter
 */
public class TestMapAdapterEntrySet {

    private HSet s = null;
    private HMap.HEntry e1 = null;
    private HMap.HEntry e2 = null;
    private HMap.HEntry entry1 = null;
    private HMap.HEntry entry2 = null;

    /**
     * Setup (for all tests): initializes the HSet s returned by the method entrySet and containing 2 entries. It also saves 4 entries, 2 contained in s (e1, e2), 2 not contained in s (entry1, entry2).
     */

    @Before
    public void setUp() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        s = map.entrySet();
        HIterator it = s.iterator();
        e1 = (HMap.HEntry) it.next();
        e2 = (HMap.HEntry) it.next();
        MapAdapter map2 = new MapAdapter();
        map2.put(Integer.valueOf(10), Integer.valueOf(11));
        map2.put(Integer.valueOf(11), Integer.valueOf(12));
        HSet set2 = map2.entrySet();
        HIterator it2 = set2.iterator();
        entry1 = (HMap.HEntry) it2.next();
        entry2 = (HMap.HEntry) it2.next();
    }

    /**
     * Test add
     * @safe.precondition Setup
     * @safe.postcondition UnsupportedOperationException thrown
     * @safe.testcases Test that when calling add UnsupportedOperationException is thrown
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAdd() {
        s.add(new Object());
    }

     /**
     * Test addAll
     * @safe.precondition Setup
     * @safe.postcondition UnsupportedOperationException thrown
     * @safe.testcases Test that when calling addAll UnsupportedOperationException is thrown
     */
    @Test(expected = UnsupportedOperationException.class)
    public void testAddAll() {
        s.add(new CollectionAdapter());
    }

    /**
     * Test clear
     * @safe.precondition setup
     * @safe.postcondition set is empty, size is 0
     * @safe.testcases Tests that after calling clear size is 0
     */
    @Test
    public void testClear() {
        s.clear();
        assertEquals(0, s.size());
    }

    /**
     * Test contains entry contained
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that contains(e1) returns true.
     */
    @Test
    public void testContainsTrue() {
        assertTrue(s.contains(e1));
    }

    /**
     * Test contains entry not contained
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that contains(entry1) returns false.
     */
    @Test
    public void testContainsFalse() {
        assertFalse(s.contains(entry1));
    }

    /**
     * Test contains ClassCastException
     * @safe.precondition setup
     * @safe.postcondition ClassCastException thrown
     * @safe.testcases Test that contains(new Object()) throws ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testContainsClassCast() {
        s.contains(new Object());
    }

    /**
     * Test contains with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that contains(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        s.contains(null);
    }

    /**
     * Test containsAll with collection contained
     * @safe.precondition setup, collection c initialized, e1 and e2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns true
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
        c.add(e2);
        assertTrue(s.containsAll(c));
    }

    /**
     * Test containsAll with collection not contained
     * @safe.precondition setup, collection c initialized, entry1 and entry2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns false
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        c.add(entry1);
        c.add(entry2);
        assertFalse(s.containsAll(c));
    }

    /**
     * Test containsAll with collection partially contained
     * @safe.precondition setup, collection c initialized, e1 and entry2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns false
     */
    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
        c.add(entry1);
        assertFalse(s.containsAll(c));
    }

    /**
     * Test containsAll ClassCastException
     * @safe.precondition setup, new Object() added to a collection c
     * @safe.postcondition ClassCastException thrown
     * @safe.testcases Test that containsAll(c) throws ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testContainsAllClassCast() {
        HCollection c = new CollectionAdapter();
        c.add(new Object());
        s.containsAll(c);
    }

    /**
     * Test containsAll with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that containsAll(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        s.containsAll(null);
    }

    /**
     * Test equals with equal set
     * @safe.precondition setup, create entrySet (otherSet) from map with the same entries
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(otherSet) returns true.
     */
    @Test
    public void testEqualsTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet otherSet = map.entrySet();
        assertTrue(s.equals(otherSet));
    }

    /**
     * Test equals with different sets
     * @safe.precondition setup, otherSet initialized, one object added to otherSet
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(otherSet) returns false.
     */
    @Test
    public void testEqualsFalse() {
        HSet otherSet = new SetAdapter();
        otherSet.add(new Object());
        assertFalse(s.equals(otherSet));
    }

    /**
     * Test equals with itself
     * @safe.precondition set s initialized
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(s) returns true.
     */
	@Test
	public void testEqualsEqualToItself() {
		assertTrue(s.equals(s));
	}

    /**
     * Test equals with null
     * @safe.precondition setup
     * @safe.postcondition none
     * @safe.testcases Test that calling s.equals(null) returns false
     */
	@Test
	public void testEqualsWithNull() {
		assertFalse(s.equals(null));
    }

    /**
     * Test hashcode equal sets
     * @safe.precondition setup, create entrySet (otherSet) from map with the same entries
     * @safe.postcondition None
     * @safe.testcases Test that if sets are equal also hashcodes are equal 
     */
	@Test
    public void testHashCodeTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet otherSet = map.entrySet();
		assertEquals(s, otherSet);
		assertTrue(s.hashCode() == otherSet.hashCode());
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
		assertFalse(s.equals(otherSet));
		assertFalse(s.hashCode() == otherSet.hashCode());
	}

   /**
     * Test isEmpty with empty set
     * @safe.precondition setup, call to clear
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns true
     */
    @Test
    public void TestIsEmptyTrue() {
        s.clear();
        assertTrue(s.isEmpty());
    }

     /**
     * Test isEmpty with non empty set
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns false
     */
    @Test
    public void testIsEmptyFalse() {
        assertFalse(s.isEmpty());
	}

    /**
     * Test iterator next and hasNext
     * @safe.precondition setup, iterator initialized, otherSet initialized
     * @safe.postcondition set s copied into otherSet
     * @safe.testcases Test that after iterating over the set s with the iterator's methods next and hasNext and copying the elements returned by next into otherSet, otherSet is equal to s.
     */
    @Test
    public void testIteratorNextAndHasNext() {
        HIterator it = s.iterator();
        HSet otherSet = new SetAdapter();
        while(it.hasNext()) {
            otherSet.add(it.next());
        }
        assertEquals(s, otherSet);
	}

    /**
     * Test iterator NoSuchElementException
     * @safe.precondition setup, iterator initialized
     * @safe.postcondition NoSuchElementException thrown
     * @safe.testcases Test that calling next 3 causes NoSuchElementException to be thrown
     */
	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        HIterator it = s.iterator();
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
		HIterator it = s.iterator();
		it.next();
        it.remove();
        assertEquals(1, s.size());
	}
    
	/**
     * Test remove when object is contained
     * @safe.precondition setup
     * @safe.postcondition entry e1 has been removed from the set
     * @safe.testcases Test that calling remove(e1) removes that entry from the set and returns true (contains(e1) returns false)
     */
    @Test
    public void testRemoveTrue() {
        assertTrue(s.remove(e1));
        assertEquals(false, s.contains(e1));
    }

	/**
     * Test remove when object isn't contained
     * @safe.precondition setup
     * @safe.postcondition s hasn't been modified
     * @safe.testcases Test that calling remove(entry1) returns false
     */
    @Test
    public void testRemoveFalse() {
        assertFalse(s.remove(entry1));
    }

    /**
     * Test remove ClassCastException
     * @safe.precondition setup
     * @safe.postcondition ClassCastException thrown
     * @safe.testcases Test that remove(c) throws ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveClassCast() {
        s.remove(new Object());
    }

    /**
     * Test remove with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that remove(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        s.remove(null);
    }

	/**
     * Test removeAll with collection contained in the set
     * @safe.precondition setup, collection c initialized, e1 added to c
     * @safe.postcondition all elements in the collection have been removed from the set
     * @safe.testcases Test that calling removeAll(c) removes the elements of the collection from the set (iterating over the set and calling contains on the elements of the collection returns false)
     */
    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
		assertTrue(s.removeAll(c));
		HIterator cit = c.iterator();
		while(cit.hasNext()) {
			assertFalse(s.contains(cit.next()));
		}
    }

	/**
     * Test removeAll with collection not contained in the set
     * @safe.precondition setup, entry1 and entry2 added to collection
     * @safe.postcondition None
     * @safe.testcases Test that calling removeAll(c) returns false and the size of the set is two
     */
    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        c.add(entry1);
        c.add(entry2);
		assertFalse(s.removeAll(c));
        assertEquals(2, s.size());
    }

	/**
     * Test removeAll with collection partially contained in the set
     * @safe.precondition setup, entry1 and e2
     * @safe.postcondition e2 has been removed from the set
     * @safe.testcases Test that calling removeAll(c) returns true and removes e2 from the set (size of the set becomes 1)
     */
    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        c.add(entry1);
        c.add(e2);
		assertTrue(s.removeAll(c));
		assertEquals(1, s.size());
    }

    /**
     * Test removeAll ClassCastException
     * @safe.precondition setup, collection c initialized with new Object()
     * @safe.postcondition ClassCastException thrown
     * @safe.testcases Test that removeAll(c) throws ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testRemoveAllClassCast() {
        HCollection c = new CollectionAdapter();
        c.add(new Object());
        s.removeAll(c);
    }

    /**
     * Test removeAll with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that removeAll(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        s.removeAll(null);
    }

	/**
     * Test retainAll in a condition when all elements are retained
     * @safe.precondition setup, collection c initailized, e1 and e2
     * @safe.postcondition all objects are still in the set
     * @safe.testcases Test that calling retainAll(c) retains all the elements of the collection in the set (retainAll returns false, since the set hasn't been modified and the size of the set is still 2)
     */
    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
        c.add(e2);
		assertFalse(s.retainAll(c));
		assertEquals(2, s.size());
    }

	/**
     * Test retainAll in a condition when some elements are retained
     * @safe.precondition setup, collection c initailized, e1 added to the collection
     * @safe.postcondition One element retained
     * @safe.testcases Test that calling retainAll(c) retains the one entry of the collection and removes the other one (retainAll returns true, since the set has been modified and the size of the set is now 1)
     */
    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection c = new CollectionAdapter();
        c.add(e1);
		assertTrue(s.retainAll(c));
		assertEquals(1, s.size());
	}

    /**
     * Test retainAll in a condition when no elements are retained
     * @safe.precondition setup, collection c initailized, entry1 added to the collection
     * @safe.postcondition The set is empty
     * @safe.testcases Test that calling retainAll(c) retains no elements (retainAll returns true, since the set has been modified and the size of the set is now 0)
     */
	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection c = new CollectionAdapter();
		c.add(entry1);
		assertTrue(s.retainAll(c));
		assertEquals(0, s.size());
	}
 
    /**
     * Test retainAll ClassCastException
     * @safe.precondition setup, collection c initialized with new Object()
     * @safe.postcondition ClassCastException thrown
     * @safe.testcases Test that retainAll(c) throws ClassCastException
     */
    @Test(expected = ClassCastException.class)
    public void testRetainAllClassCast() {
        HCollection c = new CollectionAdapter();
        c.add(new Object());
        s.retainAll(c);
    }

    /**
     * Test retainAll with null
     * @safe.precondition setup
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that retainAll(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        s.retainAll(null);
	}

   	 /**
     * Test size with an empty set 
     * @safe.precondition setup, call to clear
     * @safe.postcondition None
     * @safe.testcases Test that calling size with an empty set returns 0
     */
    @Test
    public void testSizeEmpty() {
        s.clear();
        assertEquals(0, s.size());
    }

     /**
     * Test size
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that calling size retrns 2
     */
    @Test
    public void testSize() {
        assertEquals(2, s.size());
    }

    /**
     * Test toArray 
     * @safe.precondition setup
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray returns an array with the same elements as the set in the same order returned by the set iterator.
     */
    @Test
    public void testToArray() {
        Object[] setArray = s.toArray();
        HIterator it = s.iterator();
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
        Object[] setArray = s.toArray(param);
        assertEquals(2, setArray.length);
        HIterator it = s.iterator();
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
        Object[] setArray = s.toArray(param);
        assertEquals(10, setArray.length);
        HIterator it = s.iterator();
		int j = 0;
        while(it.hasNext()) {
			assertEquals(it.next(), setArray[j]);
			j++;
        }
        for(int i = s.size(); i < param.length; i++) {
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
        s.toArray(null);
    }

}