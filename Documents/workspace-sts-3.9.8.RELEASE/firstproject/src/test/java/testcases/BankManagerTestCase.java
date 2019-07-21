package testcases;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import base.TestBase;

public class BankManagerTestCase extends TestBase{
	
	@Test
	public void bankManagerTestCase() throws InterruptedException
	
	{
		log.debug("Inside testcase class");
		//driver.findElement(By.cssSelector(OR.getProperty("bmlBtn"))).click();
		//driver.findElement(By.cssSelector(OR.getProperty("addcustomerBtn"))).click();
		click("bmlBtn_CSS");
		
		Thread.sleep(3000);
		//click("addcustomerBtn_CSS");
		//Thread.sleep(3000);
		log.debug("Login successfully executed");
		Reporter.log("Login successfully executed");
	
		//Assert.assertTrue("Login not successful",isElementPresent(By.cssSelector(OR.getProperty("addcustomerBtn"))));
		
	}

}
