package com.qa.opencart.pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtl;

public class SearchResultPage {
	
	private WebDriver driver;
	private ElementUtl eleUtil;
	public SearchResultPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtl(this.driver);		
	}
	
	public ProductInfoPage selectProduct(String productName) {
		eleUtil.waitForVisibilityOfEelement(By.linkText(productName), AppConstants.SHORT_DEFAULT_WAIT).click();
		return new ProductInfoPage(driver);
	}
	
	

}
