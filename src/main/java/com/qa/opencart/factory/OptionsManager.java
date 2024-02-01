package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

	private Properties prop;
	private ChromeOptions co;
	private FirefoxOptions fo;
	private EdgeOptions eo;

	public OptionsManager(Properties prop) {
		this.prop = prop;
		}

	public ChromeOptions getChromeOptions() {
		co = new ChromeOptions();
		if (prop.getProperty("headless").trim().equals("true")) {
			co.addArguments("--headless");
		}
		if (prop.getProperty("incognito").trim().equals("true")) {
			co.addArguments("--incognito");
		}
		return co;		
	}

	public FirefoxOptions getFirefoxOptions() {
		fo = new FirefoxOptions();
		if (prop.getProperty("headless").trim().equals(true)) {
			fo.addArguments("--headless");
		}
		if (prop.getProperty("incognito").trim().equals(true)) {
			fo.addArguments("--incognito");
		}
		return fo;
	}

	public EdgeOptions getEdgeOptions() {
		eo = new EdgeOptions();
		if (prop.getProperty("headless").trim().equals(true)) {
			eo.addArguments("--headless");
		}
		if (prop.getProperty("incognito").trim().equals(true)) {
			eo.addArguments("--inprivate");
		}		
		return eo;
	}
}
