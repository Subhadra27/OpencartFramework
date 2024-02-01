package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtl;

public class AccountsPage {
	
	private WebDriver driver;
	private ElementUtl eleUtl;
	
	private By logOutLink = By.linkText("Logout");
	private By serachText = By.xpath("//input[@name ='search']");
	private By accountHeaders = By.xpath("//div[@id='content']/h2");
	private By searchBtn = By.cssSelector("div#search>span>button");
	
	
	
	public AccountsPage(WebDriver driver) {
		this.driver = driver;	
		eleUtl = new ElementUtl(driver);
	}
	
	public String getAccountPageTitle()  {
		return eleUtl.waitForTitleIs(AppConstants.ACCOUNTPAGE_TITLE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	public String getAccountPageUrl()  {
		return eleUtl.waitForURLContains(AppConstants.ACCOUNTPAGE_URL_FRACTION, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	public boolean isLogoutBtnExit() {
		return eleUtl.waitForVisibilityOfEelement(logOutLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public void logout() {
		if(isLogoutBtnExit()) {
			eleUtl.doClick(logOutLink);
		}
	}
	public boolean isSearchTextFieldExist() {
		return eleUtl.waitForVisibilityOfEelement(serachText, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}
	
	public List<String> getAccountHeaders() {
		
		List<String> headerText = new ArrayList<String>();
		
		List<WebElement> headerElements = eleUtl.waitForVisibilityOfElements(accountHeaders, AppConstants.SHORT_DEFAULT_WAIT);
		for(WebElement ele:headerElements) {
			headerText.add(ele.getText());
		}
		
		return headerText;
		
	}
	
	public SearchResultPage searchProduct(String productName) {
		eleUtl.waitForVisibilityOfEelement(serachText, AppConstants.MEDIUM_DEFAULT_WAIT).clear();
		eleUtl.waitForVisibilityOfEelement(serachText, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(productName);
		eleUtl.doClick(searchBtn);
		return new SearchResultPage(driver);
		
		
	}
}
