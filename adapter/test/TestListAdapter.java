package adapter.test;

import adapter.*;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import java.util.NoSuchElementException;

/**
 * Test case TestListAdapter
 */

public class TestListAdapter {

	private ListAdapter l = null;

    /**
     * Metodo di setup
     */

	@Before
	public  void setUp() {
		l = new ListAdapter();
	}

	// Tests

    /**
     * Test add(int, object) in first position
     * @safe.precondition List initialized
     * @safe.postcondition Element inserted
     * @safe.testcases Adds an element to the list and checks that the size has increased and the element is present in the first position
     */
	@Test
	public void testAddWithParamsFirstPosition() {
		Object o = new Object();
		l.add(0, o);
		assertEquals(1, l.size());
		assertEquals(o, l.get(0));
	}

	/**
     * Test add(int, object) in last position
     * @safe.precondition List initialized
     * @safe.postcondition Element inserted
     * @safe.testcases Adds an element to the list and checks that the size has increased and the element is present in the last position.
     */
	@Test
	public void testAddWithParamsLastPosition() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		l.add(l.size(), o);
		assertEquals(1, l.size());
		assertEquals(o, l.get(l.size()-1));
	}

    /**
     * Test add(int, object) in with null element
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Adds an element to the list and checks that NullPointerException is thrown.
     */
	@Test (expected = NullPointerException.class)
	public void testAddWithParamsWithNullElement() {
		l.add(0, null);
	}
    /**
     * Test add(int, object) with negative index
     * @safe.precondition List initialized
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Adds an element to the list with a negative index and checks that IndexOutOfBoundsException is thrown.
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBoundsNegative() {
		l.add(-1, new Object());
	}
    /**
     * Test add(int, object) with index greater than size
     * @safe.precondition List initialized
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Adds an element to the list with an index greater than size and checks that IndexOutOfBoundsException is thrown.
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddWithParamsIndexOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.add(6, new Object());
	}

    /**
     * Test add
     * @safe.precondition List initialized
     * @safe.postcondition Element added to the list
     * @safe.testcases Test that when adding an object the method returns true, the size has increased and the object is present in the last position.
     */
	@Test
	public void TestAdd() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertTrue(l.add(o));
		assertEquals(1, l.size());
		assertEquals(o, l.get(l.size()-1));
		
	}
    /**
     * Test add
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that when trying to add a null object the method throws NullPointerException.
     */
	@Test (expected = NullPointerException.class)
	public void testAddWithNullElement() {
		l.add(null);
	}

    /**
     * Test addAll
     * @safe.precondition List initialized, collection initialized with objects
     * @safe.postcondition All objects in the collection added to the list
     * @safe.testcases Test that addAll returns true and that the objects have been added to the list.
     */
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

    /**
     * Test addAll empty collection
     * @safe.precondition List initialized, collection initialized empty
     * @safe.postcondition No objects added to the list
     * @safe.testcases Test that addAll returns false and that the size hasn't changed.
     */
	@Test
	public void TestAddAllEmptyCollection() {
		HCollection c = new CollectionAdapter();
		assertFalse(l.addAll(c));
		assertEquals(0, l.size());
	}

	/**
     * Test addAll with null
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that NullPointerException is thrown.
     */
	@Test(expected = NullPointerException.class)
	public void testAddAllWithNullCollection() {
		l.addAll(null);
	}

    /**
     * Test addAll(int, c) 
     * @safe.precondition List initialized, collection initialized with objects
     * @safe.postcondition All objects in the collection added to the list
     * @safe.testcases Test that addAll returns true, the size has increased and that the objects have been added to the list in the right position.
     */
	@Test
	public void TestAddAllWithParams() {
		HCollection c = new CollectionAdapter();
		Object o1 = new Object();
		Object o2 = new Object();
		Object o3 = new Object();
		Object o4 = new Object();
		l.add(o1);
		l.add(o2);
		c.add(o3);
		c.add(o4);
		assertTrue(l.addAll(1, c));
		assertEquals(4, l.size());
		assertEquals(o3, l.get(1));
		assertEquals(o4, l.get(2));
	}

    /**
     * Test addAll(int, object) with null
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that NullPointerException is thrown.
     */
	@Test(expected = NullPointerException.class)
	public void testAddAllWithParamsWithNullElement() {
		l.addAll(0, null);
	}
    /**
     * Test addAll(int, object) with negative index
     * @safe.precondition List initialized
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Test addAll(int, object) with a negative index and checks that IndexOutOfBoundsException is thrown.
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithParamsIndexOutOfBoundsNegative() {
		l.addAll(-1, new CollectionAdapter());
	}
    /**
     * Test addAll(int, object) with index greater than size
     * @safe.precondition List initialized
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Test addAll(int, object) with an index greater than size and checks that IndexOutOfBoundsException is thrown.
     */
	@Test (expected = IndexOutOfBoundsException.class)
	public void testAddAllWithParamsIndexOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.add(6, new CollectionAdapter());
	}

    /**
     * Test clear
     * @safe.precondition List initialized, elements added, size greater than 0
     * @safe.postcondition List is empty, size is 0
     * @safe.testcases Tests that after calling clear size is 0 and an element isn't contained anymore
     */
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
    /**
     * Test contains object contained
     * @safe.precondition List initialized, Object o added, some objects added
     * @safe.postcondition None
     * @safe.testcases Test that contains(o) returns true.
     */
	@Test
	public void testContainsTrue() {
		Object o = new Object();
		l.add(o);
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		assertTrue(l.contains(o));
	}

    /**
     * Test contains Object not contained
     * @safe.precondition List initialized, some objects added
     * @safe.postcondition None
     * @safe.testcases Test that contains(o) returns false.
     */
	@Test
	public void testContainsFalse() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertFalse(l.contains(o));
	}

	/**
	* Test contains null
	* @safe.precondition List initialized
	* @safe.postcondition NullPointerException thrown
	* @safe.testcases Test that NullPointerException is thrown.
	*/
	@Test (expected = NullPointerException.class)
	public void testContainsWithNullObject() {
		l.contains(null);
	}

    /**
     * Test containsAll with collection contained
     * @safe.precondition List initialized, collection initialized, collection added to list
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns true
     */
    @Test
    public void testContainsAllTrue() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        l.addAll(c);
        assertTrue(l.containsAll(c));
    }

    /**
     * Test containsAll with collection not contained
     * @safe.precondition List initialized, collection initialized
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns false
     */
    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(new Object());
        }
        assertFalse(l.containsAll(c));
    }
	
    /**
     * Test containsAll with collection partially contained
     * @safe.precondition List initialized, collection initialized, collection partially added to list
     * @safe.postcondition None
     * @safe.testcases Test that containsAll(c) returns false
     */
    @Test
    public void testContainsAllWithHCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            Object o = new Object();
			if(i % 2 == 0)
        		l.add(o);
            c.add(o);
        }
        assertFalse(l.containsAll(c));
    }

    /**
     * Test containsAll with null
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that NullPointerException is thrown
     */
	@Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        l.containsAll(null);
	}
	
    /**
     * Test equals with equal list
     * @safe.precondition List l initialized, otherList initialized, same objects added to both lists
     * @safe.postcondition None
     * @safe.testcases Test that l.equals(otherList) returns true.
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
	
    /**
     * Test equals with different list
     * @safe.precondition List l initialized, otherList initialized, one object added to otherList
     * @safe.postcondition None
     * @safe.testcases Test that l.equals(otherList) returns false.
     */
    @Test
    public void testEqualsFalse() {
		HList otherList = new ListAdapter();
		otherList.add(new Object());
        assertFalse(l.equals(otherList));
	}

    /**
     * Test equals with empty lists
     * @safe.precondition List l initialized, otherList initialized, both empty
     * @safe.postcondition None
     * @safe.testcases Test that l.equals(otherList) returns true.
     */
	@Test
    public void testEqualsEmptyList() {
		HList otherList = new ListAdapter();
        assertTrue(l.equals(otherList));
	}

    /**
     * Test equals with itself
     * @safe.precondition List l initialized
     * @safe.postcondition None
     * @safe.testcases Test that l.equals(l) returns true.
     */
	@Test
	public void testEqualsEqualToItself() {
		assertTrue(l.equals(l));
	}

    /**
     * Test equals with null
     * @safe.precondition List l initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling l.equals(null) NullPointerException is thrown
     */
	@Test
	public void testEqualsWithNull() {
		assertFalse(l.equals(null));
	}

    /**
     * Test Get
     * @safe.precondition List initialized, object o added to list in first position
     * @safe.postcondition None
     * @safe.testcases Test that get(0) returns the object o
     */
	@Test
    public void testGet() {
		Object o = new Object();
		l.add(o);
        assertEquals(o, l.get(0));
	}
	
    /**
     * Test Get negative index
     * @safe.precondition List initialized, object added to list in first position
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Test that get(-1) throws IndexOutOfBoundsException
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testGetOutOfBoundsNegative() {
		l.add(new Object());
        l.get(-1);
	}

    /**
     * Test Get index greater or equal to size
     * @safe.precondition List initialized, 5 object added to list
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Test that get(5) throws IndexOutOfBoundsException
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetOutOfBoundsGreaterOrEqualToize() {
		for(int i = 0; i < 5; i++) {
			l.add(new Object());
		}
		l.get(5);
	}

    /**
     * Test hashcode equal lists
     * @safe.precondition List l initialized, otherList initialized, same objects added
     * @safe.postcondition None
     * @safe.testcases Test that if lists are equal also hashcodes are equal 
     */
	@Test
    public void testHashCodeTrue() {
        HList otherList = new ListAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherList.add(o);
            l.add(o);
        }
		assertTrue(l.equals(otherList));
		assertTrue(l.hashCode() == otherList.hashCode());
	}

    /**
     * Test hashcode different lists
     * @safe.precondition List l initialized, otherList initialized, objects added to l
     * @safe.postcondition None
     * @safe.testcases Test that if lists are not equal also hashcodes are not equal 
     */
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
     * Test indexOf element contained
     * @safe.precondition list initialized, some objects and object o added to the list
     * @safe.postcondition None
     * @safe.testcases Test that indexOf(o) returns the right index
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

    /**
     * Test indexOf element not contained
     * @safe.precondition list initialized, some objects added to the list
     * @safe.postcondition None
     * @safe.testcases Test that indexOf returns -1 since the element is not contained in the list
     */
	@Test
    public void testIndexOfObjectNotContained() {
        for(int i = 0; i < 2; i++) {
			l.add(new Object());
		}
		Object o = new Object();
		assertEquals(-1, l.indexOf(o));
	}
    /**
     * Test indexOf with null
     * @safe.precondition list initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that indexOf(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
	public void testIndexOfWithNull() {
		l.indexOf(null);
	}

    /**
     * Test isEmpty with empty list
     * @safe.precondition List initialized empty
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns true
     */
    @Test
    public void testIsEmptyTrue() {
        assertTrue(l.isEmpty());
	}
	
    /**
     * Test isEmpty with non empty list
     * @safe.precondition List initialized, one element added
     * @safe.postcondition None
     * @safe.testcases Test that isEmpty returns false
     */
	@Test
    public void testIsEmptyFalse() {
		l.add(new Object());
        assertFalse(l.isEmpty());
	}
	
    /**
     * Test iterator next and hasNext
     * @safe.precondition List l initialized, 5 objects added to the list, iterator initialized, otherList initialized
     * @safe.postcondition List l copied into otherList
     * @safe.testcases Test that after iterating over the list l with the iterator's methods next and hasNext and copying the elements returned by next into otherList otherList is equal to l.
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

    /**
     * Test iterator NoSuchElementException
     * @safe.precondition List initialized, 3 objects added to the list, iterator initialized
     * @safe.postcondition NoSuchElementException thrown
     * @safe.testcases Test that calling next 4 causes NoSuchElementException to be thrown
     */
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

    /**
     * Test iterator remove
     * @safe.precondition List l initialized, 5 objects added to the list, iterator initialized
     * @safe.postcondition One element removed from the list
     * @safe.testcases Test that calling it.next() and it.remove() causes the size of the list to decrease by one
	 */
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
     * Test lastIndexOf element contained
     * @safe.precondition list initialized, some objects and object o added to the list twice
     * @safe.postcondition None
     * @safe.testcases Test that lastIndexOf(o) returns the right index
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

    /**
     * Test lastIndexOf element not contained
     * @safe.precondition list initialized, some objects added to the list
     * @safe.postcondition None
     * @safe.testcases Test that lastIndexOf returns -1 since the element is not contained in the list
     */
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
     * Test listIterator next and hasNext
     * @safe.precondition List l initialized, 5 objects added to the list, listIterator initialized, otherList initialized
     * @safe.postcondition List l copied into otherList
     * @safe.testcases Test that after iterating over the list l with the listIterator's methods next and hasNext and copying the elements returned by next into otherList, otherList is equal to l. 
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

    /**
     * Test listIterator next NoSuchElementException
     * @safe.precondition List initialized, 3 objects added to the list, iterator initialized
     * @safe.postcondition NoSuchElementException thrown
     * @safe.testcases Test that calling next 4 causes NoSuchElementException to be thrown
     */
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

    /**
     * Test listIterator previous and hasPrevious
     * @safe.precondition List l initialized, 5 objects added to the list, object o added to the list l in last position, l listIterator initialized, otherList initialized
     * @safe.postcondition List l copied into otherList backwards
     * @safe.testcases The test calls next until hasNext is false, then it adds the elements returned by previous to otherList until hasPrevious is false. OtherList should therefore now contain the same elements of l but in reverse order. This is tested by checking that the two lists have the same size and the object o is present in otherList in the first position.
     */
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

	/**
     * Test listIterator previous NoSuchElementException
     * @safe.precondition List initialized, 3 objects added to the list, iterator initialized
     * @safe.postcondition NoSuchElementException thrown
     * @safe.testcases Test that calling previous causes NoSuchElementException to be thrown
     */
	@Test(expected = NoSuchElementException.class)
    public void testListIteratorPreviousNoMoreElements() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        lit.previous();
	}

	/**
     * Test listIterator add
     * @safe.precondition List initialized, one object added
     * @safe.postcondition The list iterator has added 4 elements to the list
     * @safe.testcases Test that after calling the add method of the list iterator the elements are added in the right position, the size has been increased and that calls to next are unaffected by the add operation.
     */
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

	/**
     * Test listIterator add with null
     * @safe.precondition List initialized, iterator initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that callin the listIterator's add method with null causes NullPointerException to be thrown.
     */
	@Test (expected = NullPointerException.class)
	public void testListIteratorAddWithNullElement() {
		HListIterator lit = l.listIterator();
		lit.add(null);
	}

	/**
     * Test listIterator nextIndex start
     * @safe.precondition List initialized, listIterator initialized
     * @safe.postcondition None
     * @safe.testcases Test that nextIndex returns 0
     */
	@Test
    public void testListIteratorNextIndexStart() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        HListIterator lit = l.listIterator();
        assertEquals(0, lit.nextIndex());
	}

	/**
     * Test listIterator nextIndex end
     * @safe.precondition List initialized, listIterator initialized, 5 objects added, iterator moved to the end of the list
     * @safe.postcondition None
     * @safe.testcases Test that nextIndex returns the list size (5)
     */
	@Test
    public void testListIteratorNextIndexEnd() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext()) lit.next();
        assertEquals(l.size(), lit.nextIndex());
	}

	/**
     * Test listIterator previousIndex start
     * @safe.precondition List initialized, listIterator initialized
     * @safe.postcondition None
     * @safe.testcases Test that perviousIndex returns -1
     */
	@Test
    public void testListIteratorPreviousIndexStart() {
        HListIterator lit = l.listIterator();
        assertEquals(-1, lit.previousIndex());
	}

	/**
     * Test listIterator previousIndex end
     * @safe.precondition List initialized, listIterator initialized, 5 objects added, iterator moved to the end of the list
     * @safe.postcondition None
     * @safe.testcases Test that perviousIndex returns the lsit size minus one
     */
	@Test
    public void testListIteratorPreviousIndexEnd() {
        for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		while(lit.hasNext()) lit.next();
        assertEquals(l.size()-1, lit.previousIndex());
	}
	
	/**
     * Test listIterator remove
     * @safe.precondition List initialized, listIterator initialized, 5 objects added
     * @safe.postcondition Element removed from the list
     * @safe.testcases Test that calling remove after a call to next removes that element (the size has been decreased)
     */
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

	/**
     * Test listIterator remove without next or previous
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition IllegalStateException thrown
     * @safe.testcases Test that calling remove without first calling next or previous throws IllegalStateException.
     */
	@Test(expected = IllegalStateException.class)
    public void testRemoveWithoutNextOrPrevious() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
        lit.remove();
	}
	
	/**
     * Test listIterator remove after add
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition IllegalStateException thrown
     * @safe.testcases Test that calling remove after calling add throws IllegalStateException.
     */
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

	/**
     * Test listIterator set after next
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition Element has been set
     * @safe.testcases Test that calling set after next sets the element to the value passed to the set method
     */
	@Test
    public void testListIteratorSetAfterNext() {
        for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
		lit.next();
		Object o = new Object();
        lit.set(o);
        assertEquals(o, l.get(0));
	}

	/**
     * Test listIterator set after previous
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition Element has been set
     * @safe.testcases Test that calling set after previous sets the element to the value passed to the set method
     */
	@Test
    public void testListIteratorSetAfterPrevious() {
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

	/**
     * Test listIterator set withoud next or previous
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition IllegalStateException thrown
     * @safe.testcases Test that calling set without first calling next or previous throws IllegalStateException
     */
	@Test(expected = IllegalStateException.class)
    public void testSetWithoutNextOrPrevious() {
		for(int i = 0; i < 3; i++) {
            l.add(new Object());
		}
		HListIterator lit = l.listIterator();
        lit.set(new Object());
	}

	/**
     * Test listIterator set after add
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition IllegalStateException thrown
     * @safe.testcases Test that calling set after calling the add method of the listIterator throws IllegalStateException
     */
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

	/**
     * Test listIterator set after remove
     * @safe.precondition List initialized, listIterator initialized, 3 objects added
     * @safe.postcondition IllegalStateException thrown
     * @safe.testcases Test that calling set after calling the remove method of the listIterator throws IllegalStateException
     */
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
     * Test that listIterator(int) is consistent with listIterator()
     * @safe.precondition List initialized, 5 objects added to the list, listIterator with no parameters initialized, listIterator with parameter int 2 initialized
     * @safe.postcondition The two iterators are in the same position
     * @safe.testcases Test that after calling next twice on the first iterator, the subsequent calls to next for the two iterators return the same element.
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
     * Test remove(int)
     * @safe.precondition List initialized, 5 objects added to the list, object o added at index 3
     * @safe.postcondition object o has been removed from the list
     * @safe.testcases Test that calling remove(3) removes object o from the list (contains(o) returns false)
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

	/**
     * Test remove(int) with negative index
     * @safe.precondition List initialized, 5 objects added to the list
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Test that calling remove(-1) throws IndexOutOfBoundsException
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsNegative() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.remove(-1);
	}
	
	/**
     * Test remove(int) with index greater than size
     * @safe.precondition List initialized, 5 objects added to the list
     * @safe.postcondition IndexOutOfBoundsException thrown
     * @safe.testcases Test that calling remove(5) throws IndexOutOfBoundsException
     */
	@Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveOutOfBoundsGreaterThanSize() {
		for(int i = 0; i < 5; i++) {
            l.add(new Object());
		}
        l.remove(5);
    }

	/**
     * Test remove(Object) when object is contained
     * @safe.precondition List initialized, Object o added to the list
     * @safe.postcondition Object o has been removed from the list
     * @safe.testcases Test that calling remove(o) removes that element from the list and returns true (contains(o) returns false)
     */
    @Test
    public void testRemoveTrue() {
        Object o = new Object();
        l.add(o);
        assertTrue(l.remove(o));
        assertFalse(l.contains(o));
    }

	/**
     * Test remove(Object) when object isn't contained
     * @safe.precondition List initialized, Object o added to the list
     * @safe.postcondition Object o is still in the list
     * @safe.testcases Test that calling remove(new Object()) returns false and contains(o) returns true
     */
    @Test
    public void testRemoveFalse() {
        Object o = new Object();
        l.add(o);
        assertFalse(l.remove(new Object()));
        assertTrue(l.contains(o));
	}

	/**
     * Test remove(Object) with null
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling remove(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRemoveNullOBject() {
        l.remove(null);
	}
	
	/**
     * Test removeAll with collection contained in the list
     * @safe.precondition List initialized, collection c initailized, 5 objects added both to the collection and the list
     * @safe.postcondition 5 objects have been removed from the list
     * @safe.testcases Test that calling removeAll(c) removes the elements of the collection from the list (iterating over the list and calling contains on the elements of the collection return false)
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

	/**
     * Test removeAll with collection not contained in the list
     * @safe.precondition List initialized, collection c initailized, 3 objects added to the collection, one different object added to the list
     * @safe.postcondition None
     * @safe.testcases Test that calling removeAll(c) returns false and the size of the list is one
     */
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

	/**
     * Test removeAll with collection partially contained in the list
     * @safe.precondition List initialized, collection c initailized, 3 objects added to the collection, object o added both to the collection and the list
     * @safe.postcondition Object o has been removed from the list
     * @safe.testcases Test that calling removeAll(c) returns true and removes o from the list (size of the list becomes 0)
     */
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

	/**
     * Test removeAll with null
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling removeAll(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        l.removeAll(null);
    }

	/**
     * Test retainAll in a condition when all elements are retained
     * @safe.precondition List initialized, collection c initailized, 5 objects added both to the collection and the list
     * @safe.postcondition all objects are still in the list
     * @safe.testcases Test that calling retainAll(c) retains all the elements of the collection in the list (retainAll returns false, since the list hasn't been modified and the size of the list is still 5)
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

	/**
     * Test retainAll in a condition when some elements are retained
     * @safe.precondition List initialized, collection c initailized, 5 objects added to the list, 3 of these objects added to the collection
     * @safe.postcondition The 3 objects in the collection are still in the list while the other 2 have been removed
     * @safe.testcases Test that calling retainAll(c) retains the 3 elements of the collection and removes the other 2 (retainAll returns true, since the list has been modified and the size of the list is now 3)
     */
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

	/**
     * Test retainAll in a condition when no elements are retained
     * @safe.precondition List initialized, collection c initailized, 5 objects added to the list
     * @safe.postcondition The list is empty
     * @safe.testcases Test that calling retainAll(c) retains no elements (retainAll returns true, since the list has been modified and the size of the list is now 0)
     */
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

	/**
     * Test retainAll with null
     * @safe.precondition List initialized
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that calling retainAll(null) throws NullPointerException
     */
	@Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        l.retainAll(null);
	}
	
	/**
     * Test listIterator 
     * @safe.precondition 
     * @safe.postcondition 
     * @safe.testcases Test that 
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
	
	/**
     * Test  
     * @safe.precondition 
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that 
     */
	@Test(expected = NullPointerException.class)
    public void testSetWithNull() {
        l.set(0, null);
	}

	 /**
     * Test listIterator 
     * @safe.precondition 
     * @safe.postcondition 
     * @safe.testcases Test that 
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
     * Test Sublist changes propagation to the backing list 
     * @safe.precondition 
     * @safe.postcondition 
     * @safe.testcases Test that 
     */
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
     * Test listIterator 
     * @safe.precondition 
     * @safe.postcondition 
     * @safe.testcases Test that 
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

	/**
     * Test  
     * @safe.precondition 
     * @safe.postcondition NullPointerException thrown
     * @safe.testcases Test that 
     */
    @Test(expected = NullPointerException.class)
    public void testToArrayWithNull() {
        l.toArray(null);
    }

}