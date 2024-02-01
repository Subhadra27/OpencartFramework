package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtl;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementUtl eleUtil;
	private Map<String , String> hmap = new HashMap<String,String>();
	
	private By productHeader =  By.cssSelector("div#content h1");
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetadata = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[2]/li");		
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtl(this.driver);
		
	}
	
	public String getProductHeaderName() {
		String productHedaerValue = eleUtil.doElementGetText(productHeader);
		System.out.println("The product header name is " +productHedaerValue );
		return productHedaerValue;
		
	}
	
	public int productImagesCount() {
		int productImgCount = eleUtil.countOfWebElements(productImages);
		System.out.println("product" +getProductHeaderName() + "image count: " + productImgCount );
		return productImgCount;
	}
	
	public void getproductMetadata() {		
		
		List<WebElement> metadataList = eleUtil.waitForVisibilityOfElements(productMetadata, AppConstants.MEDIUM_DEFAULT_WAIT);
		for(WebElement ele: metadataList) {
			String metadata = ele.getText();
			
			String metaKey = metadata.split(":")[0].trim();
			String metaValue = metadata.split(":")[1].trim();
			
			hmap.put(metaKey, metaValue);			
		}
	}
	
	public void getproductPriceMetadata() {		
			
			List<WebElement> metaPriceList = eleUtil.waitForVisibilityOfElements(productPriceData, AppConstants.MEDIUM_DEFAULT_WAIT);
			String productPrice = metaPriceList.get(0).getText();
			String productTax = metaPriceList.get(1).getText().split(":")[1].trim();
			hmap.put("price", productPrice);
			hmap.put("tax", productTax);		
		}
	
	public Map<String,String> getProductDeatils() {
		hmap.put("ProductName", getProductHeaderName());
		getproductMetadata();
		getproductPriceMetadata();	
		System.out.println(hmap);
		return hmap;
		
	}

}
