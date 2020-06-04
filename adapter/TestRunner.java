import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner
{
	public static void main(String[] args)
	{
		//Result result = JUnitCore.runClasses(TestJunit.class);
		//let's make it parametrizable
		Result result = null;
		if(args.length > 0)
		{
			for(int i=0;i<args.length;i++)
			{
				Class clazz = null;
				try
				{
					clazz = Class.forName(args[i]);
				}
				catch(Exception e)
				{
					e.printStackTrace();
					continue;
				}
				result = JUnitCore.runClasses(clazz);

				//what is "result"?
				System.out.println(result);

				for (Failure failure : result.getFailures()) 
				{
					System.out.println(failure.toString());
				}
				System.out.println("Test successful == " + result.wasSuccessful());
				System.out.println("___________________\n\n");
			}
		}
		else
		{
			result = JUnitCore.runClasses(adapter.TestListAdapter.class);

			//what is "result"?
			System.out.println(result);

			for (Failure failure : result.getFailures()) 
			{
				System.out.println(failure.toString());
			}
			System.out.println(result.getRunCount());
			System.out.println("Test successful == " + result.wasSuccessful());
		}
	}
}
