package adapter.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Runner class for the tests
 */
public class TestRunner
{
	public static void main(String[] args)
	{
		Result result = JUnitCore.runClasses(adapter.test.AdapterTestSuite.class);

		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Number of tests run: " + result.getRunCount());
		System.out.println("Tests successful == " + result.wasSuccessful());

	}
}
