package adapter.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Test Suite which groups all the test cases for the adapter package.
 */

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestSetAdapter.class, TestListAdapter.class, TestListAdapterSublist.class, TestMapAdapter.class, TestMapAdapterEntrySet.class, TestMapAdapterKeySet.class, TestMapAdapterValues.class
})

/**
 * @safe.testsuiteexecutionrecords Questa suite è basata su JUnit 4.13, per poter eseguire i test e' necessario aggiungere i percorsi a hamcrest-core-1.3.jar e junit-4.13.jar al classpath
 * @safe.executionvariables Bisogna indicare nel classpath le posizioni di hamcrest-core-1.3.jar e junit-4.13.jar
 * @safe.testsuitedesign Each method is individually tested with multiple asserts and in different conditions to stimulate possibile failure scenarios.
 */
public class AdapterTestSuite {

}
