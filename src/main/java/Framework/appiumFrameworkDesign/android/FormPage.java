package Framework.appiumFrameworkDesign.android;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class FormPage extends AndroidActions{
	
	 AndroidDriver driver;
	//constructor
	public FormPage(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//locators
	
	@FindBy(id="com.androidsample.generalstore:id/nameField")
	private WebElement nameField;
	// driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("shivani");
	
	@FindBy(id="com.androidsample.generalstore:id/radioFemale")
	private WebElement femaleOption;
	
	@FindBy(id="com.androidsample.generalstore:id/radioMale")
	private WebElement maleOption;
	
	@FindBy(id="android:id/text1")
	private WebElement countrySelection;
	
	@FindBy(id="com.androidsample.generalstore:id/btnLetsShop")
	private WebElement shopBtn;
	
	//actions
	
	public void setNameField(String name) {
		nameField.sendKeys(name);
		driver.hideKeyboard();
	}
	
	public void setGender(String gender) {
		if(gender.contains("female"))
			femaleOption.click();
		else
			maleOption.click();
		
	}
	
	public void setCountry(String countryName) {
		countrySelection.click();
		scrollToText(countryName);
		driver.findElement(By.xpath("//android.widget.TextView[@text='"+ countryName +"']")).click();
	}
	
	public ProductCatalog submitAction() {
		shopBtn.click();
		return new ProductCatalog(driver);
	}
	
	public void setActivity() {
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
	}
	
}
