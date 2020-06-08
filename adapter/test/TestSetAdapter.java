package adapter.test;

import adapter.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test case class for SetAdapter
 */
public class TestSetAdapter {

    private HSet set = null;

    /**
     * Bootstrap
     */

    @Before
    public void start() {
        set = new SetAdapter();
    }

    /**
     * TestAdd
     */

    @Test(expected = NullPointerException.class)
    public void testAddWithNull() {
        set.add(null);
    }

    @Test()
    public void testAddWithObjNotContained() {
        Object o = new Object();
        assertTrue(set.add(o));
        assertEquals(true, set.contains(o));
    }


    @Test()
    public void testAddWithObjContained() {
        Object o = new Object();
        assertTrue(set.add(o));
        assertFalse(set.add(o));
        assertEquals(true, set.contains(o));
    }

    /**
     * TestAddAll
     */

    @Test(expected = NullPointerException.class)
    public void testAddAllWithNull() {
        set.addAll(null);
    }

    @Test
    public void testAddAllWithHCollection() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertTrue(set.addAll(c));
        for(int i = 0; i < c.size(); i++) {
            assertTrue(set.contains(i));
        }
    }

    /**
     * TestClear
     */

    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        set.clear();
        assertEquals(0, set.size());
    }

    /**
     * TestContains
     */

    @Test(expected = NullPointerException.class)
    public void testContainsWithNull() {
        set.contains(null);
    }

    @Test
    public void testContainsWithObjContained() {
        Object o = new Object();
        set.add(o);
        assertTrue(set.contains(o));
    }

    @Test
    public void testContainsWithObjNotContained() {
        Object o = new Object();
        set.add(o);
        Object o2 = new Object();
        assertFalse(set.contains(o2));
    }

    /**
     * TestContainsAll
     */

    @Test(expected = NullPointerException.class)
    public void testContainsAllWithNull() {
        set.containsAll(null);
    }

    @Test
    public void testContainsAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.addAll(c);
        assertTrue(set.containsAll(c));
    }

    @Test
    public void testContainsAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        assertFalse(set.containsAll(c));
    }

    /**
     * TestEquals
     */

    @Test
    public void testEqualsWithEqualObject() {
        HSet set2 = new SetAdapter();
        for(int i = 0; i < 5; i++) {
            set2.add(i);
            set.add(i);
        }
        assertTrue(set.equals(set2));
    }

    @Test
    public void testEqualsWithNotEqualObject() {
        HSet set2 = new SetAdapter();
        set2.add(new Object());
        assertFalse(set.equals(set2));
    }

    /**
     * TestIsEmpty
     */

    @Test
    public void TestIsEmpty() {
        assertTrue(set.isEmpty());
    }

    /**
     * TestIterator
     */

    @Test
    public void TestIterator() {
        for(int i = 0; i < 3; i++) {
            set.add(i);
        }
        HIterator iter = set.iterator();
        HSet set2 = new SetAdapter();
        while(iter.hasNext()) {
            set2.add(iter.next());
        }
        assertTrue(set.equals(set2));
    }
    
    /**
     * TestRemove
     */

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        set.remove(null);
    }

    @Test
    public void testRemoveWithObjContained() {
        Object o = new Object();
        assertTrue(set.add(o));
        assertTrue(set.remove(o));
        assertEquals(false, set.contains(o));
    }

    @Test
    public void testRemoveWithObjNotContained() {
        Object o = new Object();
        set.add(o);
        set.remove(new Object());
        assertEquals(true, set.contains(o));
    }

    /**
     * TestRemoveAll
     */

    @Test(expected = NullPointerException.class)
    public void testRemoveAllWithNull() {
        set.removeAll(null);
    }

    @Test
    public void testRemoveAllWithHCollectionContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.addAll(c);
        assertTrue(set.removeAll(c));
    }

    @Test
    public void testRemoveAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		set.add(new Object());
		assertFalse(set.removeAll(c));
		assertEquals(1, set.size());
    }

    @Test
    public void testRemoveAllCollectionPartiallyContained() {
        HCollection c = new CollectionAdapter();
        for(int i = 0; i < 3; i++) {
            c.add(new Object());
		}
		Object o = new Object();
		c.add(o);
		set.add(o);
		assertTrue(set.removeAll(c));
		assertEquals(0, set.size());
	}

    /**
     * TestRetainAll
     */

    @Test(expected = NullPointerException.class)
    public void testRetainAllWithNull() {
        set.retainAll(null);
    }

    // @Test
    // public void testRetainAllWithHCollectionContained() {
    //     HCollection c = new CollectionAdapter();
    //     HSet backup = new SetAdapter();
    //     for(int i = 0; i < 5; i++) {
    //         c.add(i);
    //     }
    //     for(int i = 0; i < 3; i++) {
    //         backup.add(i);
    //     }
    //     set.retainAll(c);
    //     assertTrue(set.equals(backup));
    // }

    @Test
    public void testRetainAllWithHCollectionNotContained() {
        HCollection c = new CollectionAdapter();
        HSet backup = set;
        for(int i = 0; i < 5; i++) {
            c.add(i);
        }
        set.retainAll(c);
        assertTrue(set.equals(backup));
    }

    /**
     * TestSize
     */

    @Test
    public void testSize() {
        assertTrue(set.size() == 0);
    }

    @Test
    public void TestSizeIncremented() {
        set.add(new Object());
        assertTrue(set.size() == 1);
    }

    /**
     * TestToArray
     */

    @Test
    public void testToArray() {
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        Object[] setArray = set.toArray();
        for(int i = 0; i < set.size(); i++) {
            assertTrue(set.contains(setArray[i]));
        }
    }

    @Test
    public void testToArrayWithParameter() {
        for(int i = 0; i < 5; i++) {
            set.add(i);
        }
        Object[] param = new Object[3];
        Object[] setArray = set.toArray(param);
        assertEquals(setArray.length, param.length);
        for(int i = 0; i < setArray.length; i++) {
            assertTrue(set.contains(setArray[i]));
        }
    }

    @Test(expected = NullPointerException.class)
    public void testToArrayWithParamWithNull() {
        set.toArray(null);
    }

}