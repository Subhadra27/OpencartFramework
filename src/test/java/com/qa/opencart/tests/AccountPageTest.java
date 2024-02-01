package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;

public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void accSetUp() {
		acntPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	@Test
	public void acctPageTitleTest() {
		assertEquals(acntPage.getAccountPageTitle(),AppConstants.ACCOUNTPAGE_TITLE);
	}
	@Test
	public void acctPageUrlTest() {
		assertTrue(acntPage.getAccountPageUrl().contains(AppConstants.ACCOUNTPAGE_URL_FRACTION));
	}
	@Test
	public void isLogoutLinkExistTest() {
		assertTrue(acntPage.isLogoutBtnExit());
	}
	@Test
	public void isSearchFieldTextTest() {
		assertTrue(acntPage.isSearchTextFieldExist());
	}
	@Test
	public void accountHeadersTest() {
		List<String> actAccountHeaders = acntPage.getAccountHeaders();
		assertEquals(actAccountHeaders,AppConstants.ACCOUNT_HEADERS);
		
	}
	@Test
	public void accountHeadersCountTest() {
		List<String> actAccountHeaders = acntPage.getAccountHeaders();
		assertEquals(actAccountHeaders.size(),AppConstants.ACCOUNTPAGE_HEADERS_COUNT);
		
	}
	
	@Test
	public void searchProductTest() {
		searchResultPage = acntPage.searchProduct("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String accProductHeaderName = productInfoPage.getProductHeaderName();
		assertEquals(accProductHeaderName,"MacBook Pro");
	}
	
	

}
