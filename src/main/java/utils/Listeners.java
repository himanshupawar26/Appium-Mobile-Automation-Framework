package utils;

import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import io.appium.java_client.AppiumDriver;

public class Listeners extends AppiumUtils implements ITestListener {
		
	AppiumDriver driver;
	
	 public Listeners(AppiumDriver driver) {
		super(driver);
		
		this.driver=driver;
	}

	ExtentReports extent = ExtentReporterNG.getReporterObject();
	 ExtentTest test;
	 
		@Override
		public void onTestStart(ITestResult result) {
		     test = extent.createTest(result.getMethod().getMethodName());
			
		  }

		 @Override
		 public void onTestSuccess(ITestResult result) {
		    test.log(Status.PASS,"test passed");
		    
			 
		  }

		 @Override 
		 public void onTestFailure(ITestResult result) {
		    test.fail(result.getThrowable());
		    try {
				test.addScreenCaptureFromPath(getScreenshotPath(result.getMethod().getMethodName(), driver),result.getMethod().getMethodName());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 
		  }

		 @Override
		 public void onTestSkipped(ITestResult result) {
		    
			 
		  }

		 @Override
		 public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		    // not implemented
		  }

		 @Override
		 public void onTestFailedWithTimeout(ITestResult result) {
		    onTestFailure(result);
		  }

		 @Override
		 public void onStart(ITestContext context) {
		    // not implemented
		  }

		 @Override
		  public void onFinish(ITestContext context) {
		    
			 extent.flush();
		  }

}
