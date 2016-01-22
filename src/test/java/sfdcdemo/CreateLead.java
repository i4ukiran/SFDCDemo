package sfdcdemo;

import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

public class CreateLead {

	// private WebDriver driver;

	public static SetEnvVariables selenium = new SetEnvVariables();

	private long systime = System.currentTimeMillis() / 1000000;

	String randomAlpha = this.Leads(6);
	String firstName = randomAlpha + systime;
	String lastName = randomAlpha + systime;
	String companyName = randomAlpha + systime;

	@Test(dependsOnMethods = { "leadCreation" })
	public void testLead() {

		selenium.driver.findElement(By.linkText("Leads")).click();
		selenium.driver.findElement(By.xpath("//tbody/tr[2]/th/a")).click();
		existsElement(companyName);
	}

	private boolean existsElement(String id) {
		try {
			selenium.driver.findElement(By.id("lea3_ileinner"));
		} catch (NoSuchElementException e) {
			return false;
		}
		return true;
		
	}

	@Test
	public void leadCreation() throws Exception {

		WebDriverWait wait = new WebDriverWait(selenium.driver, 5000);
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Leads")));

		selenium.driver.findElement(By.linkText("Leads")).click();
		selenium.driver.findElement(By.name("new")).click();
		selenium.driver.findElement(By.id("name_firstlea2")).clear();
		selenium.driver.findElement(By.id("name_firstlea2")).sendKeys(firstName);
		selenium.driver.findElement(By.id("name_lastlea2")).clear();
		selenium.driver.findElement(By.id("name_lastlea2")).sendKeys(lastName);
		selenium.driver.findElement(By.id("lea3")).clear();
		selenium.driver.findElement(By.id("lea3")).sendKeys(companyName);
		selenium.driver.findElement(By.cssSelector("#bottomButtonRow > input[name=\"save\"]")).click();

	}

	@BeforeClass
	public void beforeTest() throws Exception {

		selenium.getProperties();
		selenium.Driver();
		selenium.login();

	}

	@AfterClass
	public void afterTest() {
		selenium.driver.quit();
	}

	public enum Mode {
		ALPHA, ALPHANUMERIC, NUMERIC
	}

	public String Leads(int length) {

		StringBuffer buffer = new StringBuffer();
		String characters = "";

		characters = "abcdefghijklmnopqrstuvwxyz";

		int charactersLength = characters.length();

		for (int i = 0; i < length; i++) {
			double index = Math.random() * charactersLength;
			buffer.append(characters.charAt((int) index));
		}
		return buffer.toString();
	}

}
