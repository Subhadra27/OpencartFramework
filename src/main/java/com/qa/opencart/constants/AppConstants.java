package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {
	public static final String LOGINPAGE_TITLE = "Account Login";
	public static final String LOGINPAGE_URL_FRACTION ="account/login";
	
	
	public static final String ACCOUNTPAGE_TITLE = "My Account";
	public static final String ACCOUNTPAGE_URL_FRACTION ="account/account";
	public static final int ACCOUNTPAGE_HEADERS_COUNT = 4;
	public static final List<String> ACCOUNT_HEADERS = Arrays.asList("My Account","My Orders","My Affiliate Account","Newsletter");
	
	public static final int SHORT_DEFAULT_WAIT = 5;
	public static final int MEDIUM_DEFAULT_WAIT = 10;
	public static final int LONG_DEFAULT_WAIT = 20;
	public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";
	public static final String REGISTER_DATASHEET_NAME = "register";
	public static final String PRODUCT_DATASHEET_NAME = "product";

}
