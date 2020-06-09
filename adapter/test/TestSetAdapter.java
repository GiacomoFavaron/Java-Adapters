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
 */
public class TestSetAdapter {

    private HSet s = null;

    /**
     * Set up
     */

    @Before
    public void start() {
        s = new SetAdapter();
    }

    /**
     * TestAdd
     */

    @Test
    public void testAddWithObjNotContained() {
        Object o = new Object();
        assertTrue(s.add(o));
        assertEquals(1, s.size());
        assertTrue(s.contains(o));
    }


    @Test
    public void testAddWithObjContained() {
        Object o = new Object();
        assertTrue(s.add(o));
        assertFalse(s.add(o));
        assertEquals(1, s.size());
        assertTrue(s.contains(o));
    }

    @Test(expected = NullPointerException.class)
    public void testAddWithNull() {
        s.add(null);
    }

    /**
     * TestAddAll
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
    }

    @Test
    public void testAddAllCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
            c.add(o);
            s.add(o);
        }
        assertFalse(s.addAll(c));
    }

    @Test
	public void TestAddAllEmptyCollection() {
		HCollection c = new CollectionAdapter();
		assertFalse(s.addAll(c));
	}

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNull() {
        s.addAll(null);
    }

    /**
     * TestClear
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
     * TestContains
     */

    @Test
    public void testContainsTrue() {
        Object o = new Object();
        s.add(o);
        assertTrue(s.contains(o));
    }

    @Test
    public void testContainsFalse() {
        Object o = new Object();
        s.add(o);
        Object o2 = new Object();
        assertFalse(s.contains(o2));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        s.contains(null);
    }

    /**
     * TestContainsAll
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

    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        assertFalse(s.containsAll(c));
    }

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

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        s.containsAll(null);
    }

    /**
     * TestEquals
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

    @Test
    public void testEqualsFalse() {
        HSet otherSet = new SetAdapter();
        otherSet.add(new Object());
        assertFalse(s.equals(otherSet));
    }

    /**
     * Test hashCode
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
     * TestIsEmpty
     */

    @Test
    public void TestIsEmptyTrue() {
        assertTrue(s.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
		s.add(new Object());
        assertFalse(s.isEmpty());
	}

    /**
     * TestIterator
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
     * TestRemove
     */

    @Test
    public void testRemoveTrue() {
        Object o = new Object();
        s.add(o);
        assertTrue(s.remove(o));
        assertEquals(false, s.contains(o));
    }

    @Test
    public void testRemoveFalse() {
        Object o = new Object();
        s.add(o);
        assertFalse(s.remove(new Object()));
        assertTrue(s.contains(o));
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        s.remove(null);
    }

    /**
     * TestRemoveAll
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
    
    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        s.removeAll(null);
    }

    /**
     * TestRetainAll
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
	
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        s.retainAll(null);
	}

    /**
     * TestSize
     */

    @Test
    public void testSizeEmpty() {
        assertEquals(0, s.size());
    }

    @Test
    public void testSize() {
        for(int i = 0; i < 4; i++) {
			s.add(new Object());
		}
        assertEquals(4, s.size());
    }

    /**
     * TestToArray
     */

    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            s.add(i);
        }
        Object[] setArray = s.toArray();
        for(int i = 0; i < s.size(); i++) {
            assertTrue(s.contains(setArray[i]));
        }
    }

    @Test
    public void testToArrayWithParameterSizeSmaller() {
        for(int i = 0; i < 10; i++) {
            s.add(i);
        }
        Object[] param = new Object[5];
        Object[] setArray = s.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(s.contains(setArray[i]));
        }
    }

    @Test
    public void testToArrayWithParameterSizeLonger() {
        for(int i = 0; i < 5; i++) {
            s.add(i);
        }
        Object[] param = new Object[10];
        Object[] setArray = s.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < s.size(); i++) {
            assertTrue(s.contains(setArray[i]));
        }
        for(int i = s.size(); i < param.length; i++) {
            assertEquals(setArray[i], null);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        s.toArray(null);
    }

}