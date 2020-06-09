package adapter.test;

import adapter.*;
import adapter.HMap.HEntry;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

/**
 * Test case class for MapAdapter
 */
public class TestMapAdapter {

    private HMap m = null;

    /**
     * Bootstrap
     */

    @Before
    public void start() {
        m = new MapAdapter();
    }

    /**
     * TestClear
     */

    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
        m.clear();
        assertEquals(0, m.size());
    }

    /**
     * TestContainsKey
     */

    @Test
    public void testContainsKeyWithObjContained() {
        m.put(1, new Object());
        assertTrue(m.containsKey(1));
    }

    @Test
    public void testContainsKeyWithObjNotContained() {
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
        assertFalse(m.containsKey(5));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsKeyWithNull() {
        m.containsKey(null);
    }

    /**
     * TestContainsValue
     */

    @Test
    public void testContainsValueWithObjContained() {
        Object o = new Object();
        m.put(1, o);
        assertTrue(m.containsValue(o));
    }

    @Test
    public void testContainsValueWithObjNotContained() {
        Object o = new Object();
        assertFalse(m.containsValue(o));
    }

    @Test(expected = NullPointerException.class)
    public void testContainsValueWithNull() {
        m.containsValue(null);
    }

    /**
     * TestEntrySet
     */

    @Test
    public void testEntrySet() {
        for(int i = 0; i < 5; i++)
            m.put(i, new Object());
        HSet s = m.entrySet();
        HIterator it = s.iterator();
        while(it.hasNext()) {
            HEntry e = (HEntry) it.next();
            Object key = e.getKey();
            assertEquals(m.get(key), e.getValue());
        }
    }

    @Test
    public void testEntrySetPropagation() {
        m.put(Integer.valueOf(1), Integer.valueOf(2));
        m.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet set = m.entrySet();
        HIterator it = set.iterator();
        it.next();
        it.remove();
        assertEquals(1, m.size());
    }

    /**
     * TestEquals
     */

    @Test
    public void testEqualsWithEqualsMapping() {
        HMap otherMap = new MapAdapter();
        assertTrue(otherMap.equals(m));
    }

    @Test
    public void testEqualsFalse() {
        HMap otherMap = new MapAdapter();
        m.put(0, new Object());
        assertFalse(otherMap.equals(m));
    }

    @Test(expected = NullPointerException.class)
    public void testEqualsWithNull() {
        m.containsValue(null);
    }

    /**
     * TestGet
     */

    @Test
    public void testGet() {
        Object o = new Object();
        m.put(0, o);
        Object cmp = m.get(0);
        assertTrue(cmp.equals(o));
    }

    @Test
    public void testGetWithKeyNotContained() {
        Object cmp = m.get(0);
        assertTrue(cmp == null);   
    }

    @Test(expected = NullPointerException.class)
    public void testGetWithNull() {
        m.get(null);
    }

    /**
     * TestHashCode
     */

    @Test
    public void testHashCodeTrue() {
        HMap otherMap = new MapAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            otherMap.put(i, o);
            m.put(i, o);
        }
		assertEquals(m, otherMap);
		assertTrue(m.hashCode() == otherMap.hashCode());
    }

    @Test
    public void testHashCodeFalse() {
        HMap otherMap = new MapAdapter();
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
		assertFalse(m.equals(otherMap));
		assertFalse(m.hashCode() == otherMap.hashCode());
	}

    /**
     * TestIsEmpty
     */

    @Test
    public void testIsEmptyTrue() {
        assertTrue(m.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        Object o = new Object();
        m.put(0, o);
        assertFalse(m.isEmpty());
    }

    /**
     * TestKeySet
     */

    @Test
    public void testKeySet() {
        for(int i = 0; i < 5; i++) {
            m.put(i, new Object());
        }
        HSet s = m.keySet();
        HIterator it = s.iterator();
        while(it.hasNext()) {
            assertTrue(m.containsKey(it.next()));
        }
    }

    @Test
    public void testKeySetPropagation() {
        m.put(Integer.valueOf(1), Integer.valueOf(2));
        m.put(Integer.valueOf(4), Integer.valueOf(5));
        HSet set = m.keySet();
        HIterator it = set.iterator();
        it.next();
        it.remove();
        assertEquals(1, m.size());
    }

    /**
     * TestPut
     */

    @Test
    public void testPutNewKey() {
        Object o = new Object();
        m.put(7, o);
        assertTrue(m.get(7).equals(o));
    }

    @Test
    public void testPutReplace() {
        Object o1 = new Object();
        m.put(7, o1);
        assertTrue(m.get(7).equals(o1));
        Object o2 = new Object();
        m.put(7, o2);
        assertTrue(m.get(7).equals(o2));
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullKey() {
        m.put(null, new Object());
    }

    @Test(expected = NullPointerException.class)
    public void testPutWithNullValue() {
        m.put(new Object(), null);
    }
    
    @Test(expected = NullPointerException.class)
    public void testPutWithNullKeyAndValue() {
        m.put(null, null);
    }

    /**
     * TestPutAll
     */

    @Test
    public void testPutAll() {
        HMap otherMap = new MapAdapter();
        for(int i = 0; i < 5; i++) {
            otherMap.put(i, new Object());
        }
        m.putAll(otherMap);
        for(int i = 0; i < 5; i++) {
            assertTrue(m.containsKey(i));
        }
        assertTrue(m.size() == 5);
    }

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNull() {
        m.putAll(null);
    }

    /**
     * TestRemove
     */

    @Test
    public void testRemoveWithObjContained() {
        Object o = new Object();
        m.put(0, o);
        m.remove(0);
        assertFalse(m.containsKey(0));
    }

    @Test
    public void testRemoveWithObjNotContained() {
        assertEquals(m.remove(0), null);
    }

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        m.remove(null);
    }

    /**
     * TestSize
     */

    @Test
    public void testSizeEmpty() {
        assertEquals(0, m.size());
    }

    @Test
    public void testSize() {
        for(int i = 0; i < 4; i++) {
			m.put(i, new Object());
        }
        m.put(2, new Object());
        assertEquals(4, m.size());
    }

    /**
     * TestValues
     */

    @Test
    public void testValues() {
        for(int i = 0; i < 5; i++)
            m.put(i, new Object());
        HCollection c = m.values();
        HIterator cit = c.iterator();
        while(cit.hasNext()) {
            assertTrue(m.containsValue(cit.next()));
        }
    }

}