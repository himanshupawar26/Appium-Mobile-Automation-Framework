package Framework.appiumFrameworkDesign;

import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.appiumFrameworkDesign.android.CartPage;
import Framework.appiumFrameworkDesign.android.FormPage;
import Framework.appiumFrameworkDesign.android.ProductCatalog;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class EcommTest_Hybrid extends BaseTest{
	
	@Test(dataProvider="getData")
	public void frameworkTest(String name, String gender, String country) throws InterruptedException {
		
		
		
		
		
		//formpage class object created at base test
		formpage.setNameField(name);
		formpage.setGender(gender);
		
		//dropdown and scroll till text
		//select option
		formpage.setCountry(country);
		
		//letshop click -> create class object product page
		ProductCatalog pc = formpage.submitAction();
		
		
		//add two products 
		pc.addItemsToCartByIndex(0);
		pc.addItemsToCartByIndex(0);
		
		//click checkout page
		CartPage cp = pc.goToCart();
		
		
		
		//wait till a pageload using WAITS
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.attributeContains(driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")),"text", "Cart"));
				
		//extracting product prices and adding
		double totalSum = cp.getProductSum();
		double dispSum = cp.getTotalAmountDisplayed();
		
		Assert.assertEquals(totalSum, dispSum);
		
		//long press on terms and condition
		//after long press pop-up appears
		cp.acceptTermsCondition();
		
		 //click chechkbox
		 //proceed to website to complete order
		 cp.submitOrder(); 
		 	 	 
	}
	
	@BeforeMethod
	public void preSetup() {
		formpage.setActivity();
	}
	
	@DataProvider
	public Object[][] getData() {
		
		return new Object[][] { {"Shivani","female","Argentina"}, {"himanshu","male","India"} };
		
	}
	
	
	
}
