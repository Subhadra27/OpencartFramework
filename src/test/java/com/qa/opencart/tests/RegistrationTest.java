package com.qa.opencart.tests;

import static org.testng.Assert.assertTrue;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegistrationTest extends BaseTest {
	@BeforeClass
	public void regSetUp() {
		regPage = loginPage.navigateToRegisterPage();
	}

	public String getRandomEmailId() {
		return "testAutomation" + System.currentTimeMillis() + "@opencart.com";
		// return "testAutomation"+UUID.randomUUID()+"opencart.com";
	}

	
	  @DataProvider public Object[][] getUserRegData() { return new Object[][] {
	  {"Subha","sahu","9810213141","test@123","no"},
	  {"Dipti","sahoo","6810213141","test@123","yes"},
	  {"Reyansh","sahu","7810213141","test@123","no"}
	  
	  };
	  }
	 

//	@DataProvider
//	public Object[][] getUserRegExcelData() {
//		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_DATASHEET_NAME);
//		return regData;
//			
//		}
//	

	@Test(dataProvider = "getUserRegData")
	public void userRegistration(String firstName, String lastName, String telephoneNumber, String password,
			String subscribe) {

		boolean isRegsterDone = regPage.userRegister(firstName, lastName, getRandomEmailId(), telephoneNumber, password,
				subscribe);
		assertTrue(isRegsterDone);

	}
//	@Test(dataProvider = "getUserRegExcelData")
//	public void userRegistration(String firstName,String lastName, String telephoneNumber,String password,String subscribe) {
//		
//		boolean isRegsterDone = regPage.userRegister(firstName,lastName,getRandomEmailId(),telephoneNumber,password,subscribe);
//		assertTrue(isRegsterDone);
//		
//		
//	}

}
