package adapter.test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

public class TestListAdapterSublist {

	private HList l = null;

	// Set up

	@Before
	public  void setUp() {
		ListAdapter list = new ListAdapter();
		list.add(Integer.valueOf(5));
		list.add(Integer.valueOf(10));
        HList sublist = list.subList(1, 1);
        l = sublist;
	}

	// Tests

	// Add(index, o)
	@Test
	public void testAddWithParamsFirstPosition() {
		Object o = new Object();
		l.add(0, o);
		assertEquals(o, l.get(0));
	}

	@Test
	public void testAddWithParamsLastPosition() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(l.size(), o);
		assertEquals(o, l.get(l.size()-1));
	}

	@Test (expected = NullPointerException.class)
	public void testAddWithParamsWithNullElement() {
		l.add(0, null);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBoundsNegative() {
		l.add(-1, new Object());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.add(6, new Object());
	}

	// Add
	@Test
	public void TestAdd() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertTrue(l.add(o));
		assertEquals(o, l.get(l.size()-1));
		assertTrue(l.contains(o));
	}

	@Test (expected = NullPointerException.class)
	public void testAddWithNullElement() {
		l.add(null);
	}

	// addAll
	@Test
	public void TestAddAll() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		c.add(o1);
		c.add(o2);
		assertTrue(l.addAll(c));
		assertEquals(o1, l.get(0));
		assertEquals(o2, l.get(1));
	}

	@Test
	public void TestAddAllEmptyCollection() {
		HCollection c = new CollectionAdapter();
		assertFalse(l.addAll(c));
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllWithNullCollection() {
		l.addAll(null);
	}

	// addAll(index, c)
	@Test
	public void TestAddAllWithParamsFirstPosition() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		l.add(o1);
		l.add(o2);
		c.add(o3);
		c.add(o4);
		assertTrue(l.addAll(0, c));
		assertEquals(o3, l.get(0));
		assertEquals(o4, l.get(1));
	}

	@Test
	public void TestAddAllWithParamsLastPosition() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		l.add(o1);
		l.add(o2);
		c.add(o3);
		c.add(o4);
		assertTrue(l.addAll(2, c));
		assertEquals(o3, l.get(2));
		assertEquals(o4, l.get(3));
	}

	@Test(expected = NullPointerException.class)
	public void testAddAllWithParamsWithNullElement() {
		l.addAll(0, null);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithParamsIndexOutOfBoundsNegative() {
		l.addAll(-1, new CollectionAdapter());
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithParamsIndexOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.add(6, new CollectionAdapter());
	}

	// clear
	@Test
	public void testClear() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		l.clear();
		assertEquals(0, l.size());
		assertFalse(l.contains(o));
	}

	// contains
	@Test
	public void testContainsFirstPos() {
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		assertTrue(l.contains(o));
	}

	@Test
	public void testContainsLastPos() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		assertTrue(l.contains(o));
	}

	@Test
	public void testContainsObjectNotContained() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertFalse(l.contains(o));
	}

	@Test (expected = NullPointerException.class)
	public void testContainsWithNullObject() {
		l.contains(null);
	}

	/**
     * TestContainsAll
     */

    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        l.addAll(c);
        assertTrue(l.containsAll(c));
    }

    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        assertFalse(l.containsAll(c));
	}
	
	@Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        l.containsAll(null);
	}
	
	/**
     * TestEquals
     */

    @Test
    public void testEqualsTrue() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherList.add(o);
            l.add(o);
        }
        assertEquals(l, otherList);
    }

    @Test
    public void testEqualsFalse() {
		HList otherList = new ListAdapter();
		otherList.add(new Object());
        assertFalse(l.equals(otherList));
	}

	@Test
    public void testEqualsEmptyList() {
		HList otherList = new ListAdapter();
        assertEquals(l, otherList);
	}

	@Test
	public void testEqualsEqualToItself() {
		assertTrue(l.equals(l));
	}
	
	@Test
	public void testEqualsWithNull() {
		assertFalse(l.equals(null));
	}

	/**
	 * Test get
	 */

	@Test
    public void testGet() {
		Object o = new Object();
		l.add(o);
        assertEquals(o, l.get(0));
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBoundsNegative() {
		l.add(new Object());
        l.get(-1);
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.get(6);
	}

	/**
     * Test hashCode
     */

	@Test
    public void testHashCodeTrue() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherList.add(o);
            l.add(o);
        }
		assertEquals(l, otherList);
		assertTrue(l.hashCode() == otherList.hashCode());
	}
	
	@Test
    public void testHashCodeFalse() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
        }
		assertFalse(l.equals(otherList));
		assertFalse(l.hashCode() == otherList.hashCode());
	}
	
	/**
     * Test indexOf
     */

	@Test
    public void testIndexOfObjectContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		l.add(o);
		assertEquals(2, l.indexOf(o));
	}

	@Test
    public void testIndexOfObjectNotContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertEquals(-1, l.indexOf(o));
	}

	@Test(expected = NullPointerException.class)
	public void testIndexOfWithNull() {
		l.indexOf(null);
	}

	/**
     * TestIsEmpty
     */

    @Test
    public void testIsEmptyTrue() {
        assertTrue(l.isEmpty());
	}
	
	@Test
    public void testIsEmptyFalse() {
		l.add(new Object());
        assertFalse(l.isEmpty());
	}
	
	/**
     * TestIterator
     */

    @Test
    public void testIteratorNextAndHasNext() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HIterator it = l.iterator();
        HList otherList = new ListAdapter();
        while(it.hasNext()) {
            otherList.add(it.next());
        }
        assertEquals(l, otherList);
	}

	@Test(expected = NoSuchElementException.class)
    public void testIteratorNextNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HIterator it = l.iterator();
        for(int i = 0; i < 4; i++) {
            it.next();
		}
	}
	
	@Test
    public void testIteratorRemove() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HIterator it = l.iterator();
		it.next();
        it.remove();
        assertEquals(4, l.size());
	}
	
	/**
     * Test lastIndexOf
     */

	@Test
    public void testLastIndexOfObjectContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		l.add(o);
		assertEquals(5, l.lastIndexOf(o));
	}

	@Test
    public void testLastIndexOfObjectNotContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertEquals(-1, l.lastIndexOf(o));
	}

	@Test(expected = NullPointerException.class)
	public void testLastIndexOfWithNull() {
		l.lastIndexOf(null);
	}

	/**
     * Test listIterator
     */

    @Test
    public void testListIteratorNextAndHasNext() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        HList otherList = new ListAdapter();
        while(lit.hasNext()) {
            otherList.add(lit.next());
        }
        assertEquals(l, otherList);
	}

	@Test(expected = NoSuchElementException.class)
    public void testListIteratorNextNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        for(int i = 0; i < 4; i++) {
            lit.next();
		}
	}

	@Test
    public void testListIteratorPreviousAndHasPrevious() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
        HListIterator lit = l.listIterator();
        HList otherList = new ListAdapter();
        while(lit.hasNext()) {
           lit.next();
		}
		while(lit.hasPrevious()) {
			otherList.add(lit.previous());
		}
		assertEquals(6, otherList.size());
		assertEquals(o, otherList.get(0));
	}

	@Test(expected = NoSuchElementException.class)
    public void testListIteratorPreviousNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        lit.previous();
	}

	// Add
	// modifiche alla lista mentre c'e' un'iterazione in corso?
	@Test
	public void testListIteratorAdd() {
		Object o1 = new Object();
		l.add(o1);
		HListIterator lit = l.listIterator();
		assertEquals(o1, lit.next());
		lit.previous(); // restore
		for(int i = 0; i < 3; i++) {
            lit.add(new Object());
		}
		Object o2 = new Object();
		lit.add(o2);
		assertEquals(5, l.size());
		assertEquals(o2, lit.previous());
		lit.next(); // restore
		assertEquals(o1, lit.next()); // chiamata a next unaffected dagli add dell'iteratore
	}

	@Test (expected = NullPointerException.class)
	public void testListIteratorAddWithNullElement() {
		HListIterator lit = l.listIterator();
		lit.add(null);
	}

	// nextIndex
	@Test
    public void testListIteratorNextIndexStart() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        assertEquals(0, lit.nextIndex());
	}

	@Test
    public void testListIteratorNextIndexMiddle() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.next();
        assertEquals(2, lit.nextIndex());
	}

	@Test
    public void testListIteratorNextIndexEnd() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext()) lit.next();
        assertEquals(l.size(), lit.nextIndex());
	}

	// previousIndex
	@Test
    public void testListIteratorPreviousIndexStart() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        assertEquals(-1, lit.previousIndex());
	}

	@Test
    public void testListIteratorPreviousIndexMiddle() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.next();
        assertEquals(1, lit.previousIndex());
	}

	@Test
    public void testListIteratorPreviousIndexEnd() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext()) lit.next();
        assertEquals(l.size()-1, lit.previousIndex());
	}
	
	// remove
	@Test
    public void testListIteratorRemove() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
        lit.remove();
        assertEquals(4, l.size());
	}

	@Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNextOrPrevious() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
        lit.remove();
	}
	
	@Test(expected = IllegalStateException.class)
    public void testRemoveAfterAdd() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.add(new Object());
        lit.remove();
	}

	// set

	@Test
    public void testListIteratorSetAfterNextFirstPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(0));
	}

	@Test
    public void testListIteratorSetAfterNextLastPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext())
			lit.next();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(l.size()-1));
	}

	@Test
    public void testListIteratorSetAfterPreviousFirstPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.previous();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(0));
	}

	@Test
    public void testListIteratorSetAfterPreviousLastPos() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext())
			lit.next();
		lit.previous();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(l.size()-1));
	}

	@Test(expected = IllegalStateException.class)
    public void testSetWithoutNextOrPrevious() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
        lit.set(new Object());
	}
	
	@Test(expected = IllegalStateException.class)
    public void testSetAfterAdd() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.add(new Object());
        lit.set(new Object());
	}

	@Test(expected = IllegalStateException.class)
    public void testSetAfterRemove() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		lit.remove();
        lit.set(new Object());
	}

	/**
     * Test listIterator(index)
     */

    @Test
    public void testListIteratorIndex() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit1 = l.listIterator();
		HListIterator lit2 = l.listIterator(2);
		for(int i = 0; i < 2; i++) {
            lit1.next();
		}
        assertEquals(lit1.next(), lit2.next());
	}

	/**
     * Test remove(index)
     */

    @Test
    public void testRemoveIndex() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        Object o = new Object();
        l.add(3, o);
        l.remove(3);
        assertFalse(l.contains(o));
    }
	
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsNegative() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.remove(-1);
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.remove(5);
    }

	/**
     * Test remove(o)
     */

    @Test
    public void testRemoveTrue() {
        Object o = new Object();
        l.add(o);
        assertTrue(l.remove(o));
        assertFalse(l.contains(o));
    }

    @Test
    public void testRemoveFalse() {
        Object o = new Object();
        l.add(o);
        assertFalse(l.remove(new Object()));
        assertTrue(l.contains(o));
	}
	
	@Test(expected = NullPointerException.class)
    public void testRemoveNullOBject() {
        l.remove(null);
	}
	
	/**
     * TestRemoveAll
     */

    @Test
    public void testRemoveAllCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        l.addAll(c);
		assertTrue(l.removeAll(c));
		HIterator cit = c.iterator();
		while(cit.hasNext()) {
			assertFalse(l.contains(cit.next()));
		}
    }

    @Test
    public void testRemoveAllCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		l.add(new Object());
		assertFalse(l.removeAll(c));
		assertEquals(1, l.size());
	}

	@Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		Object o = new Object();
		c.add(o);
		l.add(o);
		assertTrue(l.removeAll(c));
		assertEquals(0, l.size());
	}
	
	@Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        l.removeAll(null);
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
            l.add(o);
		}
		c.add(new Object());
		assertFalse(l.retainAll(c));
		assertEquals(5, l.size());
    }

    @Test
    public void testRetainAllSomeElementsRetained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
			if(i % 2 == 0)
        		c.add(o);
            l.add(o);
		}
		assertTrue(l.retainAll(c));
		assertEquals(3, l.size());
	}

	@Test
    public void testRetainAllNoElementsRetained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		c.add(new Object());
		assertTrue(l.retainAll(c));
		assertEquals(0, l.size());
	}
	
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        l.retainAll(null);
	}
	
	/**
	 * Test set
	*/

	@Test
    public void testSet() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		Object o = new Object();
		l.set(1, o);
		assertEquals(o, l.get(1));
	}

	@Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBoundsNegative() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.set(-1, new Object());
	}
	
	@Test(expected = IndexOutOfBoundsException.class)
    public void testSetOutOfBoundsGreaterOrEqualToSize() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.set(5, new Object());
	}
	
	@Test(expected = NullPointerException.class)
    public void testSetWithNull() {
        l.set(0, null);
	}

	 /**
     * TestSize
     */

    @Test
    public void testSizeEmpty() {
        assertEquals(0, l.size());
    }

    @Test
    public void TestSize() {
		for(int i = 0; i < 4; i++) {
			l.add(new Object());
		}
        assertEquals(4, l.size());
	}
	
	/**
     * Test Sublist(fromIndex, toIndex)
     */

	// Classe test + vedere se i cambiamenti si ripercuotono su lista madre

	@Test
    public void TestSubListChangesPropagation() {
		for(int i = 0; i < 10; i++) {
			l.add(new Object());
		}
		HList subList = l.subList(2, 7);
		Object o = new Object();
		subList.add(o);
		assertEquals(6, subList.size());
		assertEquals(11, l.size());
		assertEquals(o, subList.get(5));
		assertEquals(o, l.get(7));
	}

	@Test
    public void TestSubListChangesPropagationClear() {
		for(int i = 0; i < 9; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(o);
		l.subList(5, 10).clear();
		assertEquals(5, l.size());
		assertFalse(l.contains(o));
	}

    /**
     * TestToArray
     */

    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            l.add(i);
        }
        Object[] setArray = l.toArray();
        for(int i = 0; i < l.size(); i++) {
            assertEquals(l.get(i), setArray[i]);
        }
    }

    @Test
    public void testToArrayWithParameterSizeSmaller() {
        for(int i = 0; i < 10; i++) {
            l.add(i);
        }
        Object[] param = new Object[5];
        Object[] setArray = l.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < setArray.length; i++) {
            assertEquals(l.get(i), setArray[i]);
        }
    }

    @Test
    public void testToArrayWithParameterSizeLonger() {
        for(int i = 0; i < 5; i++) {
            l.add(i);
        }
        Object[] param = new Object[10];
        Object[] setArray = l.toArray(param);
        assertEquals(10, setArray.length);
        for(int i = 0; i < l.size(); i++) {
            assertEquals(l.get(i), setArray[i]);
        }
        for(int i = l.size(); i < param.length; i++) {
            assertEquals(setArray[i], null);
        }
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        l.toArray(null);
    }

}