package Framework.appiumFrameworkDesign;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import Framework.appiumFrameworkDesign.android.FormPage;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseTest {
	
	public AndroidDriver driver;
	public AppiumDriverLocalService service;
	public  FormPage formpage;
	
	@BeforeClass
	public void configAppium() throws MalformedURLException {
				
			//start appium server using appium classes (main.js is used to start appium)
				 service = new AppiumServiceBuilder()
												.withAppiumJS(new File("C:\\Users\\himanshu.pawar\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
												.withIPAddress("127.0.0.1").usingPort(4723).build();
				
				service.start();
				
				// connect virtual device from android studio using appium classes
				UiAutomator2Options options= new UiAutomator2Options();
				options.setDeviceName("HimanshuEmulator");
				//for browser using chromedriver
				options.setChromedriverExecutable("C:\\Users\\himanshu.pawar\\OneDrive - Perficient, Inc\\downloads\\chromedriver_win32 (1)\\chromedriver.exe");
				// C:\Users\himanshu.pawar\OneDrive - Perficient, Inc\downloads\chromedriver_win32 (1)
				// open app from local 
				options.setApp("C:\\Users\\himanshu.pawar\\OneDrive - Perficient, Inc\\pc\\user\\himanshu.pawar\\eclipse-workspace\\GeneralStoreEcomm\\src\\test\\java\\resources\\General-Store.apk");
				
				
				// creating driver
				 driver = new AndroidDriver(new URL("http://127.0.0.1:4723"),options);
				 driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				 
				 formpage= new FormPage(driver);
	}
	
	public void longPress(WebElement elmnt) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture", 
				ImmutableMap.of("elementId",((RemoteWebElement)elmnt).getId(),
						"duration",2000));
	}
	
	public void scrollToEndAction() {
		
		boolean canScrollMore;
		do {
			 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
				    "left", 100, "top", 100, "width", 200, "height", 200,
				    "direction", "down",
				    "percent", 3.0
				));
		}while(canScrollMore);
	}
	
	public void swipeAction(WebElement firstElmnt, String direction) {
		//swipe
				((JavascriptExecutor) driver).executeScript("mobile: swipeGesture", ImmutableMap.of(
					    "elementId",((RemoteWebElement)firstElmnt).getId(),
					    "direction", direction,
					    "percent", 0.75
					));
	}
	
	
	public Double getFormattedAmmout(String amt) {
		Double price = Double.parseDouble(amt.substring(1));
		return price;
	}
	
	@AfterClass
	public void tearDown() {
		
			//stopping driver and server
				driver.quit();
				service.stop();
	}
}
