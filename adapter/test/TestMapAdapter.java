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
    public void TestContainsKeyWithNull() {
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
    public void TestContainsValueWithNull() {
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

    /**
     * TestEquals
     */

    @Test(expected = NullPointerException.class)
    public void TestEqualsWithNull() {
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
    public void TestGetWithNull() {
        map.get(null);
    }

    @Test
    public void TestGetWithKeyContained() {
        Object o = new Object();
        map.put(0, o);
        Object cmp = map.get(0);
        assertTrue(cmp.equals(o));
    }

    @Test
    public void TestGetWithKeyNotContained() {
        Object cmp = map.get(0);
        assertTrue(cmp == null);   
    }

    /**
     * TestHashCode
     */

    /**
     * TestIsEmpty
     */

    @Test
    public void TestIsEmpty() {
        assertTrue(map.isEmpty());
    }

    @Test
    public void TestIsNotEmpty() {
        Object o = new Object();
        map.put(0, o);
        assertFalse(map.isEmpty());
    }

    /**
     * TestKeySet
     */

    /**
     * TestPut
     */

    /**
     * TestPutAll
     */

    @Test(expected = NullPointerException.class)
    public void testPutAllWithNull() {
        map.putAll(null);
    }

    @Test
    public void testPutAllWithHMap() {
        HMap m = new MapAdapter();
        for(int i = 0; i < 5; i++)
            m.put(i, i);
        map.putAll(m);
        for(int i = 0; i < 5; i++)
            assertTrue(map.get(i).equals(i));
    }

    /**
     * TestRemove
     */

    @Test(expected = NullPointerException.class)
    public void TestRemoveWithNull() {
        map.remove(null);
    }

    @Test
    public void TestRemoveWithObjContained() {
        Object o = new Object();
        map.put(0, o);
        map.remove(0);
        assertFalse(map.containsKey(0));
    }

    @Test
    public void TestRemoveWithObjNotContained() {
        assertTrue(map.remove(0) == null);
    }

    /**
     * TestSize
     */

    @Test
    public void TestSize() {
        assertTrue(map.size() == 0);
    }

    @Test
    public void TestSizeIncremented() {
        map.put(0, new Object());
        assertTrue(map.size() == 1);
    }

    /**
     * TestValues
     */

    @Test
    public void TestValuesTrue() {
        for(int i = 0; i < 5; i++)
            map.put(i, i);
        HCollection c = map.values();
        HIterator iter = c.iterator();
        while(iter.hasNext()) {
            HEntry e = (HEntry)iter.next();
            System.out.println(e.getValue());
            assertTrue(map.get(e.getKey()).equals(e.getValue()));
        }
    }
}