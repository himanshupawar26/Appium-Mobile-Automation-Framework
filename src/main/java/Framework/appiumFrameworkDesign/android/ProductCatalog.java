package Framework.appiumFrameworkDesign.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import utils.AndroidActions;

public class ProductCatalog extends AndroidActions{
	
	 AndroidDriver driver;
	//constructor
	public ProductCatalog(AndroidDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//android.widget.TextView[@text='ADD TO CART']")
	private List<WebElement> addToCart;
	
	@FindBy(id="com.androidsample.generalstore:id/appbar_btn_cart")
	private WebElement cart;
	
	public void addItemsToCartByIndex(int index) {
		addToCart.get(index).click();
	}
	public CartPage goToCart() throws InterruptedException {
		cart.click();
		Thread.sleep(4000);
		return new CartPage(driver);
	}
	
}
