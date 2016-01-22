package sfdcdemo;

import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import java.io.InputStream;
import java.util.Properties;

public class Demo {
	// private WebDriver driver;

	public static SetEnvVariables selenium = new SetEnvVariables();
	// WebDriver driver = env.driver;

	String environment = System.getProperty("environment");
	String browser = System.getProperty("browser");
	// String environment = "qa";
	// String browser = "*firefox";
	String url, username, password;

	@Test
	public void testLogin() throws Exception {

		String title = selenium.driver.getTitle();
		Assert.assertTrue(title.contains("Login | Salesforce"));

		selenium.driver.findElement(By.id("username")).click();
		selenium.driver.findElement(By.id("username")).clear();
		selenium.driver.findElement(By.id("username")).sendKeys(username);
		selenium.driver.findElement(By.id("password")).clear();
		selenium.driver.findElement(By.id("password")).sendKeys(password);
		selenium.driver.findElement(By.id("Login")).click();

		Thread.sleep(5000);
		selenium.driver.findElement(By.linkText("Leads")).click();

	}

	@BeforeClass
	public void beforeTest() throws Exception {

		selenium.getProperties();
		selenium.setUpDriver(browser, url);

	}

	@AfterClass
	public void afterTest() {
		selenium.driver.quit();
	}
}