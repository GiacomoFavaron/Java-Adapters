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
     * Test contains key contained
     * @safe.precondition setup
     * @safe.postcondition None
     * @safe.testcases Test that contains(k1) returns true.
     */
    @Test
    public void testContainsTrue() {
        assertTrue(c.contains(v1));
    }

    @Test
    public void testContainsFalse() {
        assertFalse(c.contains(value1));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        c.contains(null);
    }

    /**
     * TestContainsAll
     */

    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(v2);
        assertTrue(c.containsAll(coll));
    }

    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(value2);
        assertFalse(c.containsAll(coll));
    }

    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(value1);
        assertFalse(c.containsAll(coll));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        c.containsAll(null);
    }

    /**
     * TestEquals
     */

    @Test
    public void testEqualsTrue() {
        MapAdapter map = new MapAdapter();
        map.put(Integer.valueOf(1), Integer.valueOf(2));
        map.put(Integer.valueOf(4), Integer.valueOf(5));
        HCollection otherCollection = map.values();
        assertEquals(c, otherCollection);
    }

    @Test
    public void testEqualsFalse() {
        HSet otherSet = new SetAdapter();
        otherSet.add(new Object());
        assertFalse(c.equals(otherSet));
    }

    /**
     * Test hashCode
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
     * TestIsEmpty
     */

    @Test
    public void TestIsEmptyTrue() {
        c.clear();
        assertTrue(c.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        assertFalse(c.isEmpty());
	}

    /**
     * TestIterator
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

	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        HIterator it = c.iterator();
        for(int i = 0; i < 3; i++) {
            it.next();
		}
	}
	
	@Test
    public void testIteratorRemove() {
		HIterator it = c.iterator();
		it.next();
        it.remove();
        assertEquals(1, c.size());
	}
    
    /**
     * TestRemove
     */

    @Test
    public void testRemoveTrue() {
        assertTrue(c.remove(v1));
        assertEquals(false, c.contains(v1));
    }

    @Test
    public void testRemoveFalse() {
        assertFalse(c.remove(value1));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        c.remove(null);
    }

    /**
     * TestRemoveAll
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

    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(value2);
		assertFalse(c.removeAll(coll));
        assertEquals(2, c.size());
    }

    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection coll = new CollectionAdapter();
        coll.add(value1);
        coll.add(v2);
		assertTrue(c.removeAll(coll));
		assertEquals(1, c.size());
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        c.removeAll(null);
    }

    /**
     * TestRetainAll
     */

    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
        coll.add(v2);
		assertFalse(c.retainAll(coll));
		assertEquals(2, c.size());
    }

    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection coll = new CollectionAdapter();
        coll.add(v1);
		assertTrue(c.retainAll(coll));
		assertEquals(1, c.size());
	}

	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection coll = new CollectionAdapter();
		coll.add(value1);
		assertTrue(c.retainAll(coll));
		assertEquals(0, c.size());
	}

	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        c.retainAll(null);
	}

    /**
     * TestSize
     */

    @Test
    public void testSizeEmpty() {
        c.clear();
        assertEquals(0, c.size());
    }

    @Test
    public void testSize() {
        assertEquals(2, c.size());
    }

    /**
     * TestToArray
     */

    @Test
    public void testToArray() {
        Object[] setArray = c.toArray();
        for(int i = 0; i < c.size(); i++) {
            assertTrue(c.contains(setArray[i]));
        }
    }

    @Test
    public void testToArrayWithParameterSizeSmaller() {
        Object[] param = new Object[1];
        Object[] setArray = c.toArray(param);
        assertEquals(2, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(c.contains(setArray[i]));
        }
    }

    @Test
    public void testToArrayWithParameterSizeLonger() {
        Object[] param = new Object[10];
        Object[] setArray = c.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < c.size(); i++) {
            assertTrue(c.contains(setArray[i]));
        }
        for(int i = c.size(); i < param.length; i++) {
            assertEquals(setArray[i], null);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        c.toArray(null);
    }

}