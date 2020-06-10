package adapter.test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

/**
 * Test case class for SetAdapter
 * @safe.summary This classes tests all the methods of the SetAdapter class
 */
public class TestSetAdapter {

    private HSet s = null;

    /**
     * Setup method, initializes the SetAdapter
     */

    @Before
    public void start() {
        s = new SetAdapter();
    }

    /**
     * Test add object not contained in the set
     * @safe.precondition Set initialized
     * @safe.postcondition Element added to the map
     * @safe.testcases Test that when adding an object the method returns true, the size has increased and contains returns true
     */
    @Test
    public void testAddWithObjectNotAlreadyContained() {
        Object o = new Object();
        assertTrue(s.add(o));
        assertEquals(1, s.size());
        assertTrue(s.contains(o));
    }

    /**
     * Test add object already contained in the set
     * @safe.precondition Set initialized
     * @safe.postcondition Element added to the map
     * @safe.testcases Test that when adding an object altready contained in the map the method returns false, the size hasn't increased and contains returns true
     */
    @Test
    public void testAddWithObjectAlreadyContained() {
        Object o = new Object();
        s.add(o);
        assertFalse(s.add(o));
        assertEquals(1, s.size());
        assertTrue(s.contains(o));
    }

    /**
     * Test add with null
     * @safe.precondition Set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that when adding a null object the method throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testAddWithNull() {
        s.add(null);
    }

    /**
     * Test addAll Collection not contained
     * @safe.precondition Set initialized, collection initialized with 5 objects
     * @safe.postcondition All objects in the collection added to the set
     * @safe.testcases Test that addAll returns true and that the objects have been added to the set.
     */
    @Test
    public void testAddAllCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        assertTrue(s.addAll(c));
        assertEquals(5, s.size());
    }

    /**
     * Test addAll collection partially contained
     * @safe.precondition Set initialized, collection initialized with 5 objects, 3 of which are also added to the set
     * @safe.postcondition Some objects of the collection added to the set, size is 5
     * @safe.testcases Test that addAll returns true and that the objects not already contained have been added to the set (size equal to 5)
     */
    @Test
    public void testAddAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
            c.add(o);
            if(i % 2 == 0)
                s.add(o);
        }
        assertTrue(s.addAll(c));
        assertEquals(5, s.size());
    }

    /**
     * Test addAll collection already contained
     * @safe.precondition Set and collection initialized with 5 objects
     * @safe.postcondition No objects of the collection added to the set, size is 5
     * @safe.testcases Test that addAll returns false (set not modified) and that size is equal to 5
     */
    @Test
    public void testAddAllCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
            c.add(o);
            s.add(o);
        }
        assertFalse(s.addAll(c));
        assertEquals(5, s.size());
    }

    @Test
	public void TestAddAllEmptyCollection() {
		HCollection c = new CollectionAdapter();
		assertFalse(s.addAll(c));
	}

    /**
     * Test addAll with null
     * @safe.precondition Set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that when calling addAll(null) the method throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testAddAllWithNull() {
        s.addAll(null);
    }

    /**
     * Test clear
     * @safe.precondition set initialized, 5 Objects and Object o added, size greater than 0
     * @safe.postcondition set is empty, size is 0
     * @safe.testcases Tests that after calling clear size is 0 and Object o is no longer contained in the set
     */
    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            s.add(new Object());
        }
        Object o = new Object();
        s.add(o);
        s.clear();
        assertEquals(0, s.size());
        assertFalse(s.contains(o));
    }

    /**
     * Test contains object contained
     * @safe.precondition set initialized, Object o added, some objects added
     * @safe.postcondition None
     * @safe.testcases Test that contains(o) returns true.
     */
    @Test
    public void testContainsTrue() {
        Object o = new Object();
        s.add(o);
        for(int i = 0; i < 5; i++) {
			s.add(new Object());
		}
        assertTrue(s.contains(o));
    }

    /**
     * Test contains Object not contained
     * @safe.precondition set initialized, some objects added
     * @safe.postcondition None
     * @safe.testcases Test that contains(o) returns false.
     */
    @Test
    public void testContainsFalse() {
		for(int i = 0; i < 5; i++) {
			s.add(new Object());
		}
		Object o = new Object();
		assertFalse(s.contains(o));
    }

	/**
	* Test contains with null
	* @safe.precondition set initialized
	* @safe.postcondition NullPointerException thrown
	* @safe.testcases Test that when calling contains(null) NullPointerException is thrown.
	*/
    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        s.contains(null);
    }

    /**
     * Test containsAll with collection contained
     * @safe.precondition set initialized, collection c initialized, collection added to the set
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns true
     */
    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
            c.add(o);
            s.add(o);
        }
        assertTrue(s.containsAll(c));
    }

    /**
     * Test containsAll with collection not contained
     * @safe.precondition set initialized, collection c initialized with 5 objects
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns false
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        assertFalse(s.containsAll(c));
    }

    /**
     * Test containsAll with collection partially contained
     * @safe.precondition set initialized, collection c initialized, collection partially added to set
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns false
     */
    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
			if(i % 2 == 0)
        		s.add(o);
            c.add(o);
        }
        assertFalse(s.containsAll(c));
    }

    /**
     * Test containsAll with null
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that when calling containsAll(null) NullPointerException is thrown
     */
    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        s.containsAll(null);
    }

    /**
     * Test equals with equal set
     * @safe.precondition set s initialized, otherSet initialized, same objects added to both sets
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(otherSet) returns true.
     */
    @Test
    public void testEqualsTrue() {
        HSet otherSet = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
            otherSet.add(o);
            s.add(o);
        }
        assertTrue(s.equals(otherSet));
    }

    /**
     * Test equals with different sets
     * @safe.precondition set s initialized, otherSet initialized, one object added to otherSet
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
     * Test equals with empty sets
     * @safe.precondition set s initialized, otherSet initialized, both empty
     * @safe.postcondition None
     * @safe.testcases Test that s.equals(otherSet) returns true.
     */
	@Test
    public void testEqualsEmptySet() {
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
		assertTrue(s.equals(s));
	}

    /**
     * Test equals with null
     * @safe.precondition set s initialized
     * @safe.postcondition none
     * @safe.testcases Test that calling s.equals(null) returns false
     */
	@Test
	public void testEqualsWithNull() {
		assertFalse(s.equals(null));
    }
    
    /**
     * Test hashcode equal sets
     * @safe.precondition set s initialized, otherSet initialized, same objects added
     * @safe.postcondition None
     * @safe.testcases Test that if sets are equal also hashcodes are equal 
     */
	@Test
    public void testHashCodeTrue() {
        HSet otherSet = new SetAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherSet.add(o);
            s.add(o);
        }
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
            s.add(new Object());
        }
		assertFalse(s.equals(otherSet));
		assertFalse(s.hashCode() == otherSet.hashCode());
	}

    /**
     * Test isEmpty with empty set
     * @safe.precondition set initialized empty
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns true
     */
    @Test
    public void TestIsEmptyTrue() {
        assertTrue(s.isEmpty());
    }

    /**
     * Test isEmpty with non empty set
     * @safe.precondition set initialized, one element added
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns false
     */
    @Test
    public void testIsEmptyFalse() {
		s.add(new Object());
        assertFalse(s.isEmpty());
	}

    /**
     * Test iterator next and hasNext
     * @safe.precondition set s initialized, 5 objects added to the set, iterator initialized, otherSet initialized
     * @safe.postcondition set s copied into otherSet
     * @safe.testcases Test that after iterating over the set s with the iterator's methods next and hasNext and copying the elements returned by next into otherSet, otherSet is equal to s.
     */
    @Test
    public void testIteratorNextAndHasNext() {
        for(int i = 0; i < 5; i++) {
            s.add(new Object());
		}
        HIterator it = s.iterator();
        HSet otherSet = new SetAdapter();
        while(it.hasNext()) {
            otherSet.add(it.next());
        }
        assertEquals(s, otherSet);
	}

    /**
     * Test iterator NoSuchElementException
     * @safe.precondition set initialized, 3 objects added to the set, iterator initialized
     * @safe.postcondition NoSuchElementException thrown
     * @safe.testcases Test that calling next 4 causes NoSuchElementException to be thrown
     */
	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            s.add(new Object());
		}
        HIterator it = s.iterator();
        for(int i = 0; i < 4; i++) {
            it.next();
		}
	}

    /**
     * Test iterator remove
     * @safe.precondition set s initialized, 5 objects added to the set, iterator initialized
     * @safe.postcondition One element removed from the set
     * @safe.testcases Test that calling it.next() and it.remove() causes the size of the set to decrease by one
	 */
	@Test
    public void testIteratorRemove() {
        for(int i = 0; i < 5; i++) {
            s.add(new Object());
		}
		HIterator it = s.iterator();
		it.next();
        it.remove();
        assertEquals(4, s.size());
	}
    
	/**
     * Test remove when object is contained
     * @safe.precondition set initialized, Object o added to the set
     * @safe.postcondition Object o has been removed from the set
     * @safe.testcases Test that calling remove(o) removes that element from the set and returns true (contains(o) returns false)
     */
    @Test
    public void testRemoveTrue() {
        Object o = new Object();
        s.add(o);
        assertTrue(s.remove(o));
        assertFalse(s.contains(o));
    }

	/**
     * Test remove when object isn't contained
     * @safe.precondition set initialized, Object o added to the set
     * @safe.postcondition Object o is still in the set
     * @safe.testcases Test that calling remove(new Object()) returns false and contains(o) returns true
     */
    @Test
    public void testRemoveFalse() {
        Object o = new Object();
        s.add(o);
        assertFalse(s.remove(new Object()));
        assertTrue(s.contains(o));
    }

	/**
     * Test remove with null
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling remove(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        s.remove(null);
    }

	/**
     * Test removeAll with collection contained in the set
     * @safe.precondition set initialized, collection c initailized, 5 objects added both to the collection and the set
     * @safe.postcondition All elements in the collection have been removed from the set
     * @safe.testcases Test that calling removeAll(c) removes the elements of the collection from the set (iterating over the set and calling contains on the elements of the collection returns false)
     */
    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        s.addAll(c);
		assertTrue(s.removeAll(c));
		HIterator cit = c.iterator();
		while(cit.hasNext()) {
			assertFalse(s.contains(cit.next()));
		}
    }

	/**
     * Test removeAll with collection not contained in the set
     * @safe.precondition set initialized, collection c initailized, 3 objects added to the collection, one different object added to the set
     * @safe.postcondition None
     * @safe.testcases Test that calling removeAll(c) returns false and the size of the set is one
     */
    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		s.add(new Object());
		assertFalse(s.removeAll(c));
        assertEquals(1, s.size());
    }

	/**
     * Test removeAll with collection partially contained in the set
     * @safe.precondition set initialized, collection c initailized, 3 objects added to the collection, object o added both to the collection and the set
     * @safe.postcondition Object o has been removed from the set
     * @safe.testcases Test that calling removeAll(c) returns true and removes o from the set (size of the set becomes 0)
     */
    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		Object o = new Object();
		c.add(o);
		s.add(o);
		assertTrue(s.removeAll(c));
		assertEquals(0, s.size());
    }

	/**
     * Test removeAll with null
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling removeAll(null) throws NullPointerException
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        s.removeAll(null);
    }

	/**
     * Test retainAll in a condition when all elements are retained
     * @safe.precondition set initialized, collection c initailized, 5 objects added both to the collection and the set
     * @safe.postcondition all objects are still in the set
     * @safe.testcases Test that calling retainAll(c) retains all the elements of the collection in the set (retainAll returns false, since the set hasn't been modified and the size of the set is still 5)
     */
    @Test
    public void testRetainAllAllElementsRetained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            c.add(o);
            s.add(o);
		}
		c.add(new Object());
		assertFalse(s.retainAll(c));
		assertEquals(5, s.size());
    }

	/**
     * Test retainAll in a condition when some elements are retained
     * @safe.precondition set initialized, collection c initailized, 5 objects added to the set, 3 of these objects added to the collection
     * @safe.postcondition The 3 objects in the collection are still in the set while the other 2 have been removed
     * @safe.testcases Test that calling retainAll(c) retains the 3 elements of the collection and removes the other 2 (retainAll returns true, since the set has been modified and the size of the set is now 3)
     */
    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
			if(i % 2 == 0)
        		c.add(o);
            s.add(o);
		}
		assertTrue(s.retainAll(c));
		assertEquals(3, s.size());
	}

    /**
     * Test retainAll in a condition when no elements are retained
     * @safe.precondition set initialized, collection c initailized, 5 objects added to the set
     * @safe.postcondition The set is empty
     * @safe.testcases Test that calling retainAll(c) retains no elements (retainAll returns true, since the set has been modified and the size of the set is now 0)
     */
	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            s.add(new Object());
		}
		c.add(new Object());
		assertTrue(s.retainAll(c));
		assertEquals(0, s.size());
	}
    
	/**
     * Test retainAll with null
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling retainAll(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        s.retainAll(null);
	}

   	 /**
     * Test size with an empty set 
     * @safe.precondition set initialized
     * @safe.postcondition None
     * @safe.testcases Test that calling size with an empty set returns 0
     */
    @Test
    public void testSizeEmpty() {
        assertEquals(0, s.size());
    }

     /**
     * Test size
     * @safe.precondition set initialized, 4 objects to the set
     * @safe.postcondition None
     * @safe.testcases Test that calling size retrns 4
     */
    @Test
    public void testSize() {
        for(int i = 0; i < 4; i++) {
			s.add(new Object());
		}
        assertEquals(4, s.size());
    }

    /**
     * Test toArray 
     * @safe.precondition set initialized, 5 objects added to the set
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray returns an array with the same elements as the set in the same order returned by the set iterator.
     */
    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            s.add(i);
        }
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
     * @safe.precondition set initialized, 10 objects added to the set, Object array param of length 5 initialized
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray(param) returns an array with the same elements as the set in the same order returned by the set iterator and of length the size of the set.
     */
    @Test
    public void testToArrayWithParameterSizeSmaller() {
        for(int i = 0; i < 10; i++) {
            s.add(i);
        }
        Object[] param = new Object[5];
        Object[] setArray = s.toArray(param);
        assertEquals(10, setArray.length);
        HIterator it = s.iterator();
		int j = 0;
        while(it.hasNext()) {
			assertEquals(it.next(), setArray[j]);
			j++;
        }
    }

	/**
     * Test toArray(Object[]) with an array of length greater than the size of the set
     * @safe.precondition set initialized, 5 objects added to the set, Object array param of length 10 initialized
     * @safe.postcondition setArray contains the array view of the set
     * @safe.testcases Test that calling toArray(param) returns an array with the same elements as the set in the same order returned by the set iterator and of length the length of param. The elements of the array after the index s.size()-1 are set to null.
     */
    @Test
    public void testToArrayWithParameterSizeLonger() {
        for(int i = 0; i < 5; i++) {
            s.add(i);
        }
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
     * @safe.precondition set initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling toArray(null) throws NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        s.toArray(null);
    }

}