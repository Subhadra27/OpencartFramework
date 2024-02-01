package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

@Epic("Epic # 100 :- Login Epic")
@Story("Story # 101->OpenCart Login story")
@Feature("Feature # 123 ->Login Feature")
public class LoginPageTest extends BaseTest{	
	@Description("Login page title test..")
	@Severity(SeverityLevel.MINOR)
	@Test(description = "Title test case" ,priority = 1)
	public void loginPageTitleTest() {
		String actTitle = loginPage.getLoginPageTitle();
		assertEquals(actTitle,AppConstants.LOGINPAGE_TITLE);	
	}
	@Test(priority = 2)
	public void loginPageUrlTest() {
		String actUrl = loginPage.getLoginPageUrl();
		assertTrue(actUrl.contains(AppConstants.LOGINPAGE_URL_FRACTION))	;
	}
	@Test(priority = 3)
	public void isLogoPresentTest() {
		boolean isLogoPrsnt = loginPage.isLogoExist();
		assertTrue(isLogoPrsnt);
	}
	@Description("Verify the password")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void isForgotPwdLinkPrsntTest() {
		boolean isPwdLink = loginPage.isForgotPwdLinkExist();
		assertTrue(isPwdLink);
	}
	@Test(priority = 5)
	public void loginTest() {
		acntPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
		assertTrue(acntPage.isLogoutBtnExit());
		
	}
	
}
