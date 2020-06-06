package adapter;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestListAdapter {

	private ListAdapter l = null;

	// Set up

	@Before
	public  void setUp() {
		l = new ListAdapter();
	}

	// Tests

	// Add(index, o)
	@Test
	public void TestAddWithParamsFirstPosition() {
		Object o = new Object();
		l.add(0, o);
		assertEquals(o, l.get(0));
	}

	@Test
	public void TestAddWithParamsLastPosition() {
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

	// @Test(expected = NullPointerException.class)
	// public void TestAddAllNullElements() {
	// 	HCollection c = new CollectionAdapter();
	// 	c.add(new Object());
	// 	c.add(null);
	// 	l.addAll(c)
	// }
	// IL C.ADD(NULL) LANCIA GIA' NULLPOINTER PERCHE' LA COLLECTION STESSA NON AMMETTE ELEMENTI NULL, NON RIESCO A TESTARE CHE LA LANCI ADDALL.

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

	@Test (expected = NullPointerException.class)
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
        assertTrue(l.equals(otherList));
    }

    @Test
    public void testEqualsFalse() {
		HList otherList = new ListAdapter();
		otherList.add(new Object());
		l.add(new Object());
        assertFalse(l.equals(otherList));
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
    public void testhashCodeTrue() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherList.add(o);
            l.add(o);
        }
		assertTrue(l.equals(otherList));
		assertTrue(l.hashCode() == otherList.hashCode());
	}
	
	@Test
    public void testhashCodeFalse() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            l.add(o);
        }
		assertFalse(l.equals(otherList));
		assertFalse(l.hashCode() == otherList.hashCode());
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
    public void testIterator() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HIterator it = l.iterator();
        HList otherList = new ListAdapter();
        while(it.hasNext()) {
            otherList.add(it.next());
        }
        assertTrue(l.equals(otherList));
    }

}