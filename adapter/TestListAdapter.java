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

	@Test
	public void TestAddWithParamsFirstPosition() {
		Object o = new Object();
		l.add(0, o);
		assertEquals(o, l.get(0));
	}

	@Test (expected = NullPointerException.class)
	public void testAddWithParamsWithNullElement() {
		//System.out.println("Testing add(int index, Object element) when adding a null element: ");
		l.add(0, null);
	}

	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBounds() {
		l.add(-1, new Object());
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



	@Test
	public void testGet() {

	}

}