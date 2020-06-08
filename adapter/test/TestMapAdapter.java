package adapter.test;

import adapter.*;
import adapter.HMap.HEntry;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

/**
 * Test case class for MapAdapter
 */
public class TestMapAdapter {

    private HMap map = null;

    /**
     * Bootstrap
     */

    @Before
    public void start() {
        map = new MapAdapter();
    }

    /**
     * TestClear
     */

    @Test
    public void testClear() {
        for(int i = 0; i < 5; i++) {
            map.put(i, new Object());
        }
        map.clear();
        assertEquals(0, map.size());
    }

    /**
     * TestContainsKey
     */

    @Test(expected = NullPointerException.class)
    public void testContainsKeyWithNull() {
        map.containsKey(null);
    }

    @Test
    public void testContainsKeyWithObjContained() {
        map.put(1, new Object());
        assertTrue(map.containsKey(1));
    }

    @Test
    public void testContainsKeyWithObjNotContained() {
        assertFalse(map.containsKey(1));
    }

    /**
     * TestContainsValue
     */

    @Test(expected = NullPointerException.class)
    public void testContainsValueWithNull() {
        map.containsValue(null);
    }

    @Test
    public void testContainsValueWithObjContained() {
        Object o = new Object();
        map.put(1, o);
        assertTrue(map.containsValue(o));
    }

    @Test
    public void testContainsValueWithObjNotContained() {
        Object o = new Object();
        assertFalse(map.containsValue(o));
    }

    /**
     * TestEntrySet
     */

    @Test
    public void testEntrySet() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HSet s = map.entrySet();
        HIterator iter = s.iterator();
        while(iter.hasNext()) {
            HEntry e = (HEntry)iter.next();
            Object key = e.getKey();
            assertTrue(map.get(key).equals(e.getValue()));
        }
    }

    @Test
    public void testEntrySetFail() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HSet s = map.entrySet();
        HIterator iter = s.iterator();
        while(iter.hasNext()) {
            HEntry e = (HEntry)iter.next();
            Object key = e.getKey();
            assertFalse(map.get(key).equals(new Object()));
        }
    }

    /**
     * TestEquals
     */

    @Test(expected = NullPointerException.class)
    public void testEqualsWithNull() {
        map.containsValue(null);
    }

    @Test
    public void testEqualsWithEqualsMapping() {
        HMap m = new MapAdapter();
        assertTrue(map.equals(m));
    }

    @Test
    public void testEqualsWithDifferentMapping() {
        HMap m = new MapAdapter();
        m.put(0, new Object());
        assertFalse(map.equals(m));
    }

    /**
     * TestGet
     */

    @Test(expected = NullPointerException.class)
    public void testGetWithNull() {
        map.get(null);
    }

    @Test
    public void testGetWithKeyContained() {
        Object o = new Object();
        map.put(0, o);
        Object cmp = map.get(0);
        assertTrue(cmp.equals(o));
    }

    @Test
    public void testGetWithKeyNotContained() {
        Object cmp = map.get(0);
        assertTrue(cmp == null);   
    }

    /**
     * TestHashCode
     */

    @Test
    public void testHashCode() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++) {
			Object o = new Object();
            m.put(i, o);
            map.put(i, o);
        }
		assertEquals(map, m);
		assertTrue(map.hashCode() == m.hashCode());
    }

    @Test
    public void testHashCodeFail() {
        assertFalse(map.hashCode() == -1);
    }

    /**
     * TestIsEmpty
     */

    @Test
    public void testIsEmpty() {
        assertTrue(map.isEmpty());
    }

    @Test
    public void testIsEmptyFalse() {
        Object o = new Object();
        map.put(0, o);
        assertFalse(map.isEmpty());
    }

    /**
     * TestKeySet
     */

    @Test
    public void testKeySet() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HSet s = map.keySet();
        HIterator iter = s.iterator();
        while(iter.hasNext()) {
            assertTrue(map.containsKey(iter.next()));
        }
    }

    /**
     * TestPut
     */

    @Test(expected = NullPointerException.class)
    public void testPutWithNull() {
        map.put(null, null);
    }

    @Test
    public void testPut() {
        map.put(0, 1);
        assertTrue(map.get(0).equals(1));
    }

    @Test
    public void testPutFail() {
        map.put(0, 1);
        assertFalse(map.get(0).equals(0));
    }

    /**
     * TestPutAll
     */

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNull() {
        map.putAll(null);
    }

    @Test
    public void testPutAll() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++)
            m.put(i, i);
        map.putAll(m);
        for(int i = 0; i < 5; i++)
            assertTrue(map.get(i).equals(i));
    }

    @Test
    public void testPutAllFail() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++)
            m.put(i, i);
        map.putAll(m);
        for(int i = 0; i < 5; i++)
            assertFalse(map.get(i).equals(new Object()));
    }

    /**
     * TestRemove
     */

    @Test(expected = NullPointerException.class)
    public void testRemoveWithNull() {
        map.remove(null);
    }

    @Test
    public void testRemoveWithObjContained() {
        Object o = new Object();
        map.put(0, o);
        map.remove(0);
        assertFalse(map.containsKey(0));
    }

    @Test
    public void testRemoveWithObjNotContained() {
        assertTrue(map.remove(0) == null);
    }

    /**
     * TestSize
     */

    @Test
    public void testSize() {
        assertTrue(map.size() == 0);
    }

    @Test
    public void testSizeIncremented() {
        map.put(0, new Object());
        assertTrue(map.size() == 1);
    }

    @Test
    public void testSizeFail() {
        assertFalse(map.size() == -1);
    }

    /**
     * TestValues
     */

    @Test
    public void testValues() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HCollection c = map.values();
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            assertTrue(map.containsValue(iter.next()));
        }
    }

    @Test
    public void testValuesFail() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HCollection c = map.values();
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            iter.next();
            assertFalse(map.containsValue(new Object()));
        }
    }

}