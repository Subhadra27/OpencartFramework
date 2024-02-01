package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {

	WebDriver driver;
	Properties prop;
	OptionsManager opm;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser");
		System.out.println("Browser name is "+ browserName);
		opm = new OptionsManager(prop);
		switch (browserName.toLowerCase().trim()) {
		case "chrome":
			driver = new ChromeDriver(opm.getChromeOptions());
			tlDriver.set(driver);//pass the Chromedriver object,tlDriver.set(new ChromeDriver(opm.getChromeOptions());
			break;
		case "firefox":
			driver = new FirefoxDriver(opm.getFirefoxOptions());
			tlDriver.set(driver);
			break;
		case "edge":
			driver = new EdgeDriver(opm.getEdgeOptions());
			tlDriver.set(driver);
			break;
		case "safari":
			driver = new SafariDriver();
			tlDriver.set(driver);
			break;

		default:
			System.out.println("Enter a valid driver" + browserName);
			throw new FrameworkException("No browser found");

		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));

		return getDriver();

	}
	
	public static WebDriver getDriver() {
		return tlDriver.get();
		
	}


	public Properties initProp() {
		// mvn clean install -Denv="qa"
		// mvn clean install
		
		FileInputStream ip = null;		
		prop = new Properties();
		
		String envName = System.getProperty("env");
		System.out.println("The environment name is "+ envName);
		
		try {
			if(envName == null) {
				System.out.println("Your environment is null,hence running the test cases on qa env..");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
				
			}else {
				switch(envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;					
				
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;					
				
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.prod.properties");
					break;					
				
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;	
					
				default:
					System.out.println("Please pass the right environment name" + envName);
					throw new FrameworkException("Wrong env name " + envName);
				}
			}
		}
			
			//ip=new FileInputStream("./src/test/resources/config/config.properties");
		catch(FileNotFoundException e)
	{
		e.printStackTrace();
	}
	try{
		prop.load(ip);
		
	}catch (IOException e) {
		e.printStackTrace();
	}

	return prop;


	}
	
	/*Take screenshot
	 * 
	 */
	
	public static String getScreenshot(String methodName) {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		String path = System.getProperty("user.dir")+"/screenshots/"+methodName+"_"+System.currentTimeMillis()+".png";
		
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return path;
	}
	
}


