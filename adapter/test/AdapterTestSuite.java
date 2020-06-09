package adapter.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//JUnit Suite Test
@RunWith(Suite.class)
@Suite.SuiteClasses({
    TestSetAdapter.class, TestListAdapter.class, TestListAdapterSublist.class, TestMapAdapter.class, TestMapAdapterEntrySet.class, TestMapAdapterKeySet.class, TestMapAdapterValues.class
})

public class AdapterTestSuite {

}
