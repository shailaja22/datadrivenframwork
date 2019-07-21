package base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utilities.ExcelReader;
//import utilities.ExcelReader;
import utilities.ExtentManager;
import utilities.TestUtil;

public class TestBase {
	public static WebDriver driver;

	public static Properties Config = new Properties();
	public static Properties OR = new Properties();
	public static FileInputStream fis;
	public static Logger log = Logger.getLogger("devpinoyLogger");
	public static ExcelReader excel=new ExcelReader("src/test/resources/excel/testData.xlsx");
	public static WebDriverWait wait;
	public ExtentReports report=ExtentManager.getInstance();
	public static ExtentTest test;

	
	@BeforeSuite

	public void setUp() {

		if (driver == null) {
			System.out.println("driver is " + driver);
			try {
				fis = new FileInputStream("src/test/resources/properties/config.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				Config.load(fis);
				log.debug("config file loaded");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				fis = new FileInputStream("src/test/resources/properties/OR.properties");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				OR.load(fis);
				log.debug("OR file loaded");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			System.out.println("driver is" + driver);
			if (Config.getProperty("browser").equals("firefox")) {
				driver = new FirefoxDriver();
				System.out.println("driver is" + driver);

			}

			else if (Config.getProperty("browser").equals("CHROME")) {

				System.setProperty("webdriver.chrome.driver", "/Applications/chromedriver");

				driver = new ChromeDriver();
				log.debug("CHROME DRIVER LAUNCHED");
			}
			// System.out.println("driver is" +driver);

			// System.out.println(Config.getProperty("testsiteurl"));
			driver.get(Config.getProperty("testsiteurl"));
			log.debug("NAVIGATED TO" + Config.getProperty("testsiteurl"));
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicit.wait")),
					TimeUnit.SECONDS);
			wait=new WebDriverWait(driver,5);

		}

	}
	public void click(String locator)
	{
		if(locator.endsWith("_CSS")) {
		driver.findElement(By.cssSelector(OR.getProperty(locator))).click();
		}else if(locator.endsWith("_XPATH"))
		{
			driver.findElement(By.xpath(OR.getProperty(locator))).click();
			
		}else if(locator.endsWith("_ID"))
		{
			driver.findElement(By.id(OR.getProperty(locator))).click();
		}
		test.log(LogStatus.INFO, "clicking on : "+locator);
		
	}
	public void type(String locator,String value)
	{
		if(locator.endsWith("_CSS")) {

		driver.findElement(By.cssSelector(OR.getProperty(locator))).sendKeys(value);
		}else if(locator.endsWith("_XPATH")) {
			driver.findElement(By.xpath(OR.getProperty(locator))).sendKeys(value);
			
			
		}else if(locator.endsWith("_ID")) {
			driver.findElement(By.id(OR.getProperty(locator))).sendKeys(value);

			
		}
		test.log(LogStatus.INFO, "clicking on : "+locator +"entered value as"+value);
		
	}
	static WebElement dropdown;
	public void select(String locator,String value)
	{
		if(locator.endsWith("_CSS")) {
			System.out.println("inside dropdown method"+locator);

			dropdown=driver.findElement(By.cssSelector(OR.getProperty(locator)));
			
			}else if(locator.endsWith("_XPATH")) {
				dropdown=driver.findElement(By.xpath(OR.getProperty(locator)));
				
				
			}else if(locator.endsWith("_ID")) {
				dropdown=driver.findElement(By.id(OR.getProperty(locator)));
			}
		System.out.println("inside dropdown method value is"+value);

				Select select=new Select(dropdown);
				select.selectByVisibleText(value);

				System.out.println("inside dropdown method value is"+value);
	
			test.log(LogStatus.INFO, "Selecting from dropdown : "+locator +" value as"+value);
			
	}

	public boolean isElementPresent(By by) {
		try

		{
			driver.findElement(by);
			log.debug("add customer button clicked");
			return true;
			
		} catch (NoSuchElementException e) {

			return false;

		}
	}
	public static void verifyEquals(String expected,String actual) throws  IOException
	{
		try {
			Assert.assertEquals(actual,expected);
			
		}catch(Throwable t)
		{
			TestUtil.captureScreenshot();
			//REPORTNG
			Reporter.log("<br>" +"verification failure" +t.getMessage()+"<br>");
			Reporter.log("<br>");
			Reporter.log("<img src= "+utilities.TestUtil.screenshotName+"  height=200 width=200></img>");
			//Extentreport
			TestBase.test.log(LogStatus.FAIL, "verification failed with exception" +t.getMessage());
			TestBase.test.log(LogStatus.FAIL, test.addScreenCapture(utilities.TestUtil.screenshotName));
			
		}
	}



@AfterSuite

	public void tearDown() {
	if (driver != null) {
			driver.quit();
			log.debug("Test execution completed");
	}
		;
	}
}
