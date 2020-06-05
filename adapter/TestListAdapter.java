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
		assertEquals(1, l.size());
	}

	@Test
	public void TestAddWithParamsLastPosition() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(l.size(), o);
		assertEquals(o, l.get(l.size()-1));
		assertEquals(6, l.size());
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
		assertEquals(6, l.size());
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
		assertEquals(2, l.size());
		assertEquals(o1, l.get(0));
		assertEquals(o2, l.get(1));
	}

	@Test
	public void TestAddAllEmptyCollection() {
		HCollection c = new CollectionAdapter();
		assertEquals(l.addAll(c), false);
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

}