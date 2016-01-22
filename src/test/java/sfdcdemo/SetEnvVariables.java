package sfdcdemo;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;


import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class SetEnvVariables {

	public WebDriver driver;
	String environment = System.getProperty("environment");
	String browser = System.getProperty("browser");
	// String environment = "qa";
	// String browser = "*firefox";
	String url, username, password;

	public void Driver() throws Exception {

		setUpDriver(browser, url);
	}

	public void setUpDriver(String browser, String url) throws Exception {

		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		capabilities.setCapability("cleanSession", true);
		if (browser.equals("*iexplore")) {
			/*
			 * capabilities.setCapability(CapabilityType.
			 * ENABLE_PERSISTENT_HOVERING , true);
			 * capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS ,
			 * true);
			 */
			System.setProperty("webdriver.ie.driver", getClass().getResource("/IEDriverServer.exe")
					.toString().replace("%20", " ").replace("file:", ""));
			driver = new InternetExplorerDriver(capabilities);
		} else if (browser.equals("*chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--test-type");
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			System.setProperty("webdriver.chrome.driver", getClass().getResource("/chromedriver.exe")
					.toString().replace("%20", " ").replace("file:", ""));
			driver = new ChromeDriver(capabilities);
			System.out.println(DesiredCapabilities.chrome());
		} else if (browser.equals("*firefox")) {
			driver = new FirefoxDriver(capabilities);
		} else if (browser.equals("*safari")) {
			capabilities.setCapability(CapabilityType.HAS_NATIVE_EVENTS, true);
			System.setProperty("webdriver.safari.driver", getClass().getResource("/safaridriver.exe")
					.toString().replace("%20", " ").replace("file:", ""));
			driver = new ChromeDriver(capabilities);
			System.out.println(DesiredCapabilities.chrome());

		} else {
			System.out.println("Please selenium.select one of these browser:\niexplore\nfirefox\nchrome");
			return;
		}
		driver.get(url);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		// Setting the default PageLoad Time out to 2 Mins
		driver.manage().timeouts().pageLoadTimeout(120, TimeUnit.SECONDS);
		// Setting the default Script Time out(the amount of time to wait for an
		// asynchronous script to finish execution before throwing an error) to
		// 2 Mins
		driver.manage().timeouts().setScriptTimeout(120, TimeUnit.SECONDS);

		driver.manage().window().maximize();
	}

	public void getProperties() throws Exception {

		InputStream propertyIs = getClass().getResourceAsStream("/ConfigSetup.properties");
		Properties prop = new Properties();
		prop.load(propertyIs);

		if (environment.equalsIgnoreCase("qa")) {

			url = prop.getProperty("QAURL");
			username = prop.getProperty("QAUSERNAME");
			password = prop.getProperty("QAPASSWORD");
		} else {
			url = prop.getProperty("QAURL");
			username = prop.getProperty("QAUSERNAME");
			password = prop.getProperty("QAPASSWORD");
		}

	}

	public void login() {

		driver.findElement(By.id("username")).click();
		driver.findElement(By.id("username")).clear();
		driver.findElement(By.id("username")).sendKeys(username);
		driver.findElement(By.id("password")).clear();
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.id("Login")).click();

	}

}
