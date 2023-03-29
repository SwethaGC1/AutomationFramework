package vtiger.GenericUtilities;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

/**
 * This class contains implementation for ITestListners interface of testNG
 * @author swetha GC
 *
 */
public class ListenersImplementation implements ITestListener{
	ExtentReports reports;
	ExtentTest test;
	
	public void onTestStart(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		System.out.println(methodname+"---Execution started");
		test=reports.createTest(methodname);
	}
	
	public void onTestSuccess(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		//System.out.println(methodname+"----PASS");
		test.log(Status.PASS, methodname+"----PASS");
		
	}
	
	public void onTestFailure(ITestResult result) {
		javaUtility jUtil=new javaUtility();
		WebDriverUtility wUtil=new WebDriverUtility();
		
		String methodname=result.getMethod().getMethodName();
		//System.out.println(methodname+"----Fail");
		test.log(Status.FAIL, methodname+"----Fail");
		test.log(Status.INFO,result.getThrowable());//it will give what is the error
		
		String screenshotname=methodname+"-"+jUtil.getSystemDateInFormat();
		try {
			String path=wUtil.takeScreenShot(BaseClass.sdriver, screenshotname);
			test.addScreenCaptureFromPath(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void onTestSkipped(ITestResult result) {
		String methodname=result.getMethod().getMethodName();
		//System.out.println(methodname+"----SKIP");
		test.log(Status.SKIP, methodname+"----SKIP");
		test.log(Status.INFO,result.getThrowable());
	}
	
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onTestFailedWithTimeout(ITestResult result) {
		
	}
	public void onStart(ITestContext result) {
		
		System.out.println("Suite execution started");
		ExtentSparkReporter htmlreport=new ExtentSparkReporter(".\\ExtentReports\\Report-"+new javaUtility().getSystemDateInFormat()+".html");
		htmlreport.config().setDocumentTitle("Vtiger Exection Report");
		htmlreport.config().setTheme(Theme.DARK);
		htmlreport.config().setReportName("VTIGER EXECUTION REPORT");
		
		reports=new ExtentReports();
		reports.attachReporter(htmlreport);
		reports.setSystemInfo("Base URL","http://localhost:8888");
		reports.setSystemInfo("Base Browser","Firefox");
		reports.setSystemInfo("Reporter name","Swetha");
	}
	
	public void onFinish(ITestContext result) {
		
		System.out.println("Suite execution finished");
		reports.flush();
	}

	
	


}
