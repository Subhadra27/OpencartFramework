package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtl;

import io.qameta.allure.Step;

public class LoginPage {
	private WebDriver driver;
	private ElementUtl eleUtil;
	
	private By userName = By.id("input-email");
	private By password = By.id("input-password11");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By loginBtn = By.xpath("//input[@type='submit' and @value ='Login']");
	private By logo = By.xpath("//img[@title='naveenopencart']");
	
	private By register = By.linkText("Register");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtl(driver);
	}
	@Step("Getting loggin page title")
	public String getLoginPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.LOGINPAGE_TITLE,AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page title is"+ title);
		return title;		
	}
	@Step("Getting loggin page URL")
	public String getLoginPageUrl() {
		String url = eleUtil.waitForURLContains(AppConstants.LOGINPAGE_URL_FRACTION,AppConstants.SHORT_DEFAULT_WAIT);
		System.out.println("Login page url:" + url);
		return url;		
	}	
	@Step("Verifying password link")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.waitForVisibilityOfEelement(forgotPwdLink, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
		//return driver.findElement(forgotPwdLink).isDisplayed();
	}
	@Step("If logo exists")
	public boolean isLogoExist() {
		return eleUtil.waitForVisibilityOfEelement(logo, AppConstants.SHORT_DEFAULT_WAIT).isDisplayed();
	}	
	@Step("The user name is {0} and password is {1}")
	public AccountsPage doLogin(String username,String pwd) {
		System.out.println("The user name and password are "+ username + pwd);
		eleUtil.waitForVisibilityOfEelement(userName, AppConstants.MEDIUM_DEFAULT_WAIT).sendKeys(username);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		return new AccountsPage(driver);
	}
	
	public RegistrationPage navigateToRegisterPage() {
		eleUtil.doClick(register);
		return new RegistrationPage(driver);
		
	}
}
