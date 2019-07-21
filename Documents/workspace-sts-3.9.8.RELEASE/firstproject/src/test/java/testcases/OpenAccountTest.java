package testcases;

import java.util.Hashtable;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.TestBase;
import utilities.TestUtil;

public class OpenAccountTest extends TestBase {
	
	/*@BeforeMethod
	public void setup() throws InterruptedException  {
		log.debug("Inside testcase class");
	//driver.findElement(By.cssSelector(OR.getProperty("addcustomerBtn"))).click();
		//click("bmlBtn_CSS");
		//Thread.sleep(3000);
		//click("addcustomerBtn_CSS");
		
	log.debug("Login successfully executed");

	}*/
	@Test(dataProvider="getData")
	public void openAccountTest(Hashtable<String,String> data) throws InterruptedException {
		if(!TestUtil.isTestRunnable("openAccountTest", excel))
		{
			throw new SkipException("Skkipping the test"+"openAccountTest".toUpperCase()+"as the Runmode is NO");
		}
		
		click("openaccount_CSS");
		System.out.println("objArray: "+ data.get("customer"));
		System.out.println("objArray: "+ data.get("currency"));
		//Hashtable<String,String> hashTable = (Hashtable<String,String>)objArray[0];
		
		String customer = data.get("customer");
		String currency = data.get("currency");
		System.out.println("customer name is" +customer);
		//click("openaccount_CSS");
		select("customer_CSS",data.get(customer));
		select("currency_CSS",data.get(currency));
		click("process_CSS");
		Thread.sleep(2000);
		Alert alert=wait.until(ExpectedConditions.alertIsPresent());
		alert.accept();
		
		
		
					
	

		
		

	}

	@DataProvider
	public Object[][] getData(){
			

			String sheetName="OpenAccountTest";
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
