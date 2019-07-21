package utilities;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import base.TestBase;

public class TestUtil extends TestBase{
	public static String screenshotPath;
	public static String screenshotName;
	public static void captureScreenshot() throws IOException{
		File scrFile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		System.out.println("scrfile is" +scrFile);
		Date d=new Date();
		screenshotName= d.toString().replace(":", "_").replace(" ", "_")+".jpg";
		System.out.println("screen shot name is" +screenshotName);
		//FileUtils.copyFile(scrFile, new File("/target/surefire-reports/html/" +screenshotName ));
		
		FileUtils.copyFile(scrFile, new File("target/surefire-reports/html/"+screenshotName));
		System.out.println("screenshotName");
	System.out.println("executed");
	
		
	}
	
	public static boolean isTestRunnable(String testName,ExcelReader excel) {
		String sheetName="test_suite";
		int rows=excel.getRowCount("sheetName");
		for(int rNum=2;rNum<=rows;rNum++)
		{
			String testCase=excel.getCellData("sheetName"," TCID", rNum);
			if(testCase.equalsIgnoreCase(testName)) {
				String runmode=excel.getCellData(sheetName, "Runmode", rNum);
				if(runmode.equalsIgnoreCase("Y"))
	
					return true;
				else
					return false;
					
		
				
			}
		}
		return false;
	}
	
	
}