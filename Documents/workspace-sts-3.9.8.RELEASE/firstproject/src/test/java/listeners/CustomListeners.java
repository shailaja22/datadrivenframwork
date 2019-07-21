package listeners;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.SkipException;

import com.relevantcodes.extentreports.LogStatus;

import base.TestBase;
import utilities.TestUtil;

public class CustomListeners extends TestBase implements ITestListener{

	public void onTestStart(ITestResult result) {
		test=report.startTest(result.getName().toUpperCase());
		//checking run modes
		
		
	}

	public void onTestSuccess(ITestResult result) {
		TestBase.test.log(LogStatus.PASS, result.getName().toUpperCase()+"PASS");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestFailure(ITestResult result) {
		System.setProperty("org.uncommons.reportng.escape-output", "false");
		try {
			
			utilities.TestUtil.captureScreenshot();
			//System.out.println("screenshot captured");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		TestBase.test.log(LogStatus.FAIL, result.getName().toUpperCase()+"failed with exception: "+result.getThrowable());
		TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(utilities.TestUtil.screenshotName));
		
		
		Reporter.log("capturing Screenshot");
		Reporter.log("<a target=\"_blank \" href="+utilities.TestUtil.screenshotName+">Screenshot </a>");
		Reporter.log("<br>");
		Reporter.log("<img src= "+utilities.TestUtil.screenshotName+"  height=200 width=200></img>");
		report.endTest(test);
		report.flush();
		
	}

	public void onTestSkipped(ITestResult result) {
		test.log(LogStatus.SKIP, result.getName().toUpperCase()+"skipped the test as runmode is NO");
		report.endTest(test);
		report.flush();
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub
		
	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub
		
	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		
	}
	
	

}
