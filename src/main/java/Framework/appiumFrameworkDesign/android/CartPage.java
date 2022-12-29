package Framework.appiumFrameworkDesign.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import utils.AndroidActions;

public class CartPage extends AndroidActions{
	
	 AndroidDriver driver;
		//constructor
		public CartPage(AndroidDriver driver) {
			super(driver);
			this.driver=driver;
			PageFactory.initElements(driver, this);
		}
		
	@FindBy(id="com.androidsample.generalstore:id/productPrice")
	private List<WebElement> productList;
	
	@FindBy(id="com.androidsample.generalstore:id/totalAmountLbl")
	private WebElement totalAmount;
	
	@FindBy(id="com.androidsample.generalstore:id/termsButton")
	private WebElement terms;
	
	@FindBy(id="android:id/button1")
	private WebElement accept;
	
	@FindBy(className="android.widget.CheckBox")
	private WebElement checkBox;
	
	@FindBy(id="com.androidsample.generalstore:id/btnProceed")
	private WebElement proceed;
	
	
	public List<WebElement> getProductList(){
		
		return productList;
		
	}
	public double getProductSum() {
		int count = productList.size();
		double totalSum =0;
		for (int i=0;i<count;i++) {
			String amtString= productList.get(i).getText();
			Double price = Double.parseDouble(amtString.substring(1));
			totalSum = totalSum+price;
				
		}
		return totalSum;
	}
	
	public double getTotalAmountDisplayed() {
		return getFormattedAmount(totalAmount.getText());
	}
	
	public void acceptTermsCondition() {
		longPress(terms);
	}
	
	
	
	public void submitOrder() {
		checkBox.click();
		proceed.click();
	}
}
