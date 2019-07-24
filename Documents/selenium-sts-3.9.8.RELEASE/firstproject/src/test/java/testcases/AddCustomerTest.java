package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;

public class AddCustomerTest extends TestBase {
	//changes i made
	@BeforeMethod
	public void setup() throws InterruptedException  {
		log.debug("Inside testcase class");
	//driver.findElement(By.cssSelector(OR.getProperty("addcustomerBtn"))).click();
		//click("bmlBtn_CSS");
		//Thread.sleep(3000);
		//click("addcustomerBtn_CSS");
		
	log.debug("Login successfully executed");

	}

	@Test(dataProvider="getData")
	public void addCustomerTest(Hashtable<String,String> data) throws InterruptedException {
	//soft assertion.
	//verifyEquals("abc","XYZ");//to check title
	//Thread.sleep(3000);
	
    
	//click("addcustomerBtn_CSS");
	if(!data.get("runMode").equalsIgnoreCase("Y")) {
		throw new SkipException("Skkipped the testcase as runmode is NO");
		
	}
	click("addcustomerBtn_CSS");
	
	
	// driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
	type("firstname_CSS",data.get("firstName"));
	//driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
	type("lastname_XPATH",data.get("lastName"));
	//driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
	type("postCode_CSS",data.get("postCode"));
	
	Thread.sleep(3000);
	
	//driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
	click("addbtn_CSS");
	Thread.sleep(3000);
	Alert alert =wait.until(ExpectedConditions.alertIsPresent());
Assert.assertTrue( alert.getText().contains(data.get("alertText")));
System.out.println("message is"+data.get("alertText"));

alert.accept();
//click("openaccount_CSS");
//Assert.fail("customer not added successfully");
//Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("openaccount_CSS")) ),"THERE IS NO OPEN ACCOUNT BUTTON");


}
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	/*public void addCustomerTest(Object[] objArray) throws InterruptedException {
		//soft assertion.
		//verifyEquals("abc","XYZ");//to check title
		//Thread.sleep(3000);
		
        //click("bmlBtn_CSS");
        //Thread.sleep(3000);
		click("addcustomerBtn_CSS");
		
		//click("addcustomerBtn_CSS");
		//Thread.sleep(3000);
		System.out.println("objArray: "+ objArray.length);
		System.out.println("objArray: "+ objArray[0]);
		Hashtable<String,String> hashTable = (Hashtable<String,String>)objArray[0];
		
		
		String firstName = hashTable.get("firstName");
		String lastName = hashTable.get("lastName");
		String postCode = hashTable.get("postCode");
		String alertText=hashTable.get("alertText");
		System.out.println("firstName: "+ firstName); 
		System.out.println("lastName: "+ lastName); 
		System.out.println("postCode: "+ postCode); 
		//Assert.fail("customer not added successfully");*/
		
		
		
		//click("bmlBtn_CSS");
		//Thread.sleep(3000);
		/*click("addcustomerBtn_CSS");
		
		
		// driver.findElement(By.cssSelector(OR.getProperty("firstname"))).sendKeys(firstName);
		type("firstname_CSS",data.get("firstName"));
		//driver.findElement(By.cssSelector(OR.getProperty("lastname"))).sendKeys(lastName);
		type("lastname_XPATH",data.get("lastName"));
		//driver.findElement(By.cssSelector(OR.getProperty("postCode"))).sendKeys(postCode);
		type("postCode_CSS",data.get("postCode"));
		Thread.sleep(3000);
		
		//driver.findElement(By.cssSelector(OR.getProperty("addbtn"))).click();
		click("addbtn_CSS");
		Thread.sleep(3000);
		Alert alert =wait.until(ExpectedConditions.alertIsPresent());
	Assert.assertTrue( alert.getText().contains(data.get("alertText")));
	System.out.println("message is"+data.get("alertText"));

	alert.accept();
	//click("openaccount_CSS");
	//Assert.fail("customer not added successfully");
	//Assert.assertTrue(isElementPresent(By.cssSelector(OR.getProperty("openaccount_CSS")) ),"THERE IS NO OPEN ACCOUNT BUTTON");
	
	
	}*/
	
	@DataProvider
	public Object[][] getData(){
			

			String sheetName="AddCustomerTest";
			int rows = excel.getRowCount(sheetName);
			int cols = excel.getColumnCount(sheetName);
			

			Object[][] data = new Object[rows - 1][1];
			
			Hashtable<String,String> table = null;

			for (int rowNum = 2; rowNum <= rows; rowNum++) { // 2

				table = new Hashtable<String,String>();
				
				for (int colNum = 0; colNum < cols; colNum++) {

					// data[0][0]
					table.put(excel.getCellData(sheetName, colNum, 1), excel.getCellData(sheetName, colNum, rowNum));
					data[rowNum - 2][0] = table;
				}

			}

			return data;

}
	

	
}
