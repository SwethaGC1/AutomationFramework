package vtiger.GenericUtilities;

import org.testng.ITestResult;
/**
 * this class provides implementation for iretryAnalyser interface of testNG
 * @author Swetha G C
 *
 */
public class RetryAnalyserImplementation {
int count=0;
int retrycount=3;

/**
 * this method will retry for 3 times
 * @param result
 * @return
 */
public boolean retry(ITestResult result)
{
	while(count<retrycount)
	{
		count++;
		return true;
	}
	return false;
}
}
