package rough;

import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Textproperties {

	@Test
	public void ensureTestIsWorking() throws IOException {
	    System.out.println(System.getProperty("user.dir"));
		Properties Config=new Properties();
	    Properties OR=new Properties();
	    

		FileInputStream fis=new FileInputStream("src/test/resources/properties/config.properties");
		Config.load(fis);
		fis=new FileInputStream("src/test/resources/properties/OR.properties");

		OR.load(fis);
		
		
		System.out.println("print: "+ Config.getProperty("browser"));
		System.out.println(Config.getProperty("testsiteurl"));
		System.out.println(OR.getProperty("bmlbtn"));
		
	}

}
