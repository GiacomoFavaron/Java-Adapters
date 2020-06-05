package adapter;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class TestListAdapter {

	private ListAdapter l = null;

	@Before
	public  void setUp() {
		l = new ListAdapter();
	}

	@Test
	public void TestAddWithParams() {
		Object o = new Object();
		l.add(0, o);
		assertEquals(o, l.get(0));
	}

	@Test (expected = NullPointerException.class)
	public void testAddWithParamsWithNullElement() {
		//System.out.println("Testing add(int index, Object element) when adding a null element: ");
		l.add(0, null);
	}
}