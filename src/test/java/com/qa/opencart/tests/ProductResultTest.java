package com.qa.opencart.tests;

import static org.testng.Assert.assertEquals;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.pages.ProductInfoPage;
import com.qa.opencart.utils.ExcelUtil;

public class ProductResultTest extends BaseTest {
	
	@BeforeClass
	public void productSetUp() {
		acntPage = loginPage.doLogin(prop.getProperty("username"),prop.getProperty("password"));
	}
	
	  @DataProvider public Object[][] getSearchData() { return new Object[][] {
	  {"MacBook","MacBook Pro",4}, {"MacBook","MacBook Air",4}, {"iMac","iMac",3},
	  {"Samsung","Samsung SyncMaster 941BW",1} };
	  }
	 
	
//	@DataProvider
//	public Object[][] getSearchExcelData() {
//		Object regData[][] = ExcelUtil.getTestData(AppConstants.PRODUCT_DATASHEET_NAME);
//		return regData;
//	}
	@Test(dataProvider ="getSearchData")
	public void productImagesTest(String searchKey,String productName,int imageCount) {
		searchResultPage = acntPage.searchProduct(searchKey);
		//searchResultPage1 = acntPage.searchProduct("MacBook");
		productInfoPage = searchResultPage.selectProduct(productName);
		assertEquals(productInfoPage.productImagesCount(),imageCount);
	}
//	@Test(dataProvider ="getSearchExcelData")
//	public void productImagesTest(String searchKey,String productName,String imageCount) {
//		searchResultPage = acntPage.searchProduct(searchKey);
//		//searchResultPage1 = acntPage.searchProduct("MacBook");
//		productInfoPage = searchResultPage.selectProduct(productName);
//		assertEquals(String.valueOf(productInfoPage.productImagesCount()),imageCount);
//	}
	@Test
	public void productDetailsTest() {
		searchResultPage = acntPage.searchProduct("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		Map<String,String>productDetails =productInfoPage.getProductDeatils();
		
		
		softAssert.assertEquals(productDetails.get("Brand"),"Apple");
		softAssert.assertEquals(productDetails.get("Product Code"),"Product 18");
		softAssert.assertEquals(productDetails.get("Reward Points"),"800");
		softAssert.assertEquals(productDetails.get("Availability"),"In Stock");
		softAssert.assertEquals(productDetails.get("price"),"$2,000.00");
		softAssert.assertEquals(productDetails.get("tax"),"$2,000.00");
		softAssert.assertAll();
	}
	

}
