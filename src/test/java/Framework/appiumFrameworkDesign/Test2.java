package Framework.appiumFrameworkDesign;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.appium.java_client.android.Activity;

public class Test2 extends BaseTest{
	
	@Test
	public void test2() {
		System.out.println("test2 executed");
		
	}
	
	@Test
	public void test3() {
		System.out.println("test3 executed");
	}
	
	@BeforeMethod
	public void preSetup() {
		Activity activity = new Activity("com.androidsample.generalstore", "com.androidsample.generalstore.MainActivity");
		driver.startActivity(activity);
		
	}
}
