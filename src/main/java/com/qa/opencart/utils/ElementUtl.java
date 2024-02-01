package com.qa.opencart.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.opencart.exceptions.FrameworkException;

import io.qameta.allure.Step;

import java.time.Duration;
import java.util.*;

public class ElementUtl {
	
	private WebDriver driver;
	
	public ElementUtl(WebDriver driver) {
		this.driver = driver;
	}	
	
	public By getByLocator(String locatorType, String locatorValue) {
		By by = null;
		
		switch (locatorType.toLowerCase().trim()) {
		case "id":
			by = By.id(locatorValue);			
			break;
		case "name":
			by = By.name(locatorValue);			
			break;
		case "xpath":
			by = By.xpath(locatorValue);			
			break;
		case "css":
			by = By.cssSelector(locatorValue);			
			break;
		case "linktext":
			by = By.linkText(locatorValue);			
			break;
		case "partiallink":
			by = By.partialLinkText(locatorValue);			
			break;
		case "tag":
			by = By.tagName(locatorValue);			
			break;
		case "class":
			by = By.className(locatorValue);			
			break;

		default:
			System.out.println("Wrong locator type is passed ..." + locatorType );
			throw new FrameworkException("WRONG LOCATOR TYPE");
		}
		return by;
	}
	
	@Step("Entering value {1} to the element{0}")
	public void doSendKeys(By locator, String input) {
		getElement(locator).sendKeys(input);		
	}
	public void doSendKeys(String locatorType,String locatorValue, String input) {
		getElement(locatorType,locatorValue).sendKeys(input);		
	}
	@Step("Click the element{0}")
	public void doClick(By locator) {
		driver.findElements(locator);
		getElement(locator).click();	
	}
	public void doClick(String locatorType,String locatorValue) {
		getElement(locatorType,locatorValue).click();	
	}
	
	public String doElementGetText(By locator) {
		return getElement(locator).getText();		
	}
	public String doElementGetText(String locatorType,String locatorValue) {
		return getElement(locatorType,locatorValue).getText();		
	}
	public WebElement getElement(By locator) {
		return driver.findElement(locator);	
	}
	public WebElement getElement(String locatorType,String locatorValue) {
		return driver.findElement(getByLocator(locatorType,locatorValue));	
	}
	
	public String getElementAttribute(By locator,String attributeName) {
		return getElement(locator).getAttribute(attributeName);
	}
	//WAF to get the list of webelements
	public List<WebElement> getListWebElements(By locator) {
		List<WebElement> listWebeles = driver.findElements(locator);
		return listWebeles;
	}
	
	//WAF to get the count of similar elements
	public int countOfWebElements(By locator) {
		return getListWebElements(locator).size();
		
	}
	
	//WAF to capture text of all the links of a page and return List<String>
	public List<String> getTextLinks(By locator) {
		List<WebElement> eleTextLinks = getListWebElements(locator);
		List<String> textLinksList = new ArrayList<String>();
		for(WebElement ele : eleTextLinks) {
			String textLink = ele.getText();
			if(textLink.length() != 0) {
				textLinksList.add(textLink);		
			}		
		}
		return textLinksList;		
	}
	//WAF to capture specific attributes of a page and return List<String>
	public List<String> getElementAttrributeList(By locator,String attrName) {
		List<WebElement> eleList = getListWebElements(locator);
		List<String> eleAttrList = new ArrayList<String>();
		for(WebElement ele : eleList) {
			String eleAttr = ele.getAttribute(attrName);
			eleAttrList.add(eleAttr);		
	}
		return eleAttrList;
}
	
	@Step("Waiting for title of page")
	public String waitForTitleIs(String title , int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(timeOut));
		try {
			if(wait.until(ExpectedConditions.titleIs(title))){
				return driver.getTitle();		
			}
		}catch(TimeoutException e) {
			System.out.println(title + "title value is not prsent..");
			e.printStackTrace();
		}
		return null;
		
	}
	@Step("waiting for element {0} within {1} timout")
	public WebElement waitForVisibilityOfEelement(By locator , int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));	
	}
	public String waitForURLContains(String urlFraction, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));

		try {
			if (wait.until(ExpectedConditions.urlContains(urlFraction))) {
				return driver.getCurrentUrl();
			}
		} catch (TimeoutException e) {
			System.out.println(urlFraction + " url value is not present....");
			e.printStackTrace();
		}
		return null;

	}
	
	public List<WebElement> waitForVisibilityOfElements(By locator, int timeOut) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
		return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));

	}
	
	
}	
