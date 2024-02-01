package com.qa.opencart.base;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import com.qa.opencart.factory.DriverFactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.pages.RegistrationPage;
import com.qa.opencart.pages.SearchResultPage;


public class BaseTest {
	
	protected WebDriver driver;
	protected Properties prop;
	DriverFactory df;
	
	protected LoginPage loginPage;
	protected AccountsPage acntPage;
	protected SearchResultPage searchResultPage;
	protected SearchResultPage searchResultPage1;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage regPage;
	protected SoftAssert softAssert;
	
	
	@Parameters("browser")// This browser is same name provided in parameter in xml file
	@BeforeTest
	public void setUp(String browserName) {
		df = new DriverFactory();
		prop = df.initProp();
		
		//Quick check for browserName which is coming from xml is not equal to null 
		//then update config.properties value with xml value
		//prop.setProperty("config browser", xml browser name which is provided as input in above method)
		//we maintain this if case because the initBrowser() takes input the browser name from config.properties
		if(browserName != null) {
			prop.setProperty("browser", browserName);
		}
		driver = df.initDriver(prop);
		 loginPage = new LoginPage(driver);
		 
		 softAssert = new SoftAssert();
		
	}
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
