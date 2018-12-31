package com.appiumdemo.base;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.appiumdemo.utilities.Common;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class Setup {

// driver implementation for Android
	
	public static AndroidDriver driver;

// Appiumserver
	
	/*public static AppiumDriverLocalService service = AppiumDriverLocalService.buildService(
			new AppiumServiceBuilder().usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"))
			.withAppiumJS(new File("C:\\Users\\sesh\\AppData\\Local\\Programs\\Appium\\resources\\app\\node_modules\\appium\\build\\lib\\main.js"))
			.withLogFile(new File(System.getProperty("user.dir")+"\\src\\test\\resources\\com\\appiumdemo\\logs\\appium.logs")).withArgument(GeneralServerFlag.LOCAL_TIMEZONE));
*/
// capabilities for mobiledevice	
	
	@BeforeSuite
	public static void applauch() throws IOException {
		
		/*if(service.isRunning()) {
			System.out.println("Server is running");
		}else {
		service.start();}*/
		
		DesiredCapabilities cap = new DesiredCapabilities();
		
		cap.setCapability(MobileCapabilityType.BROWSER_NAME, "");
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "OnePlus5");
		cap.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
		cap.setCapability(MobileCapabilityType.PLATFORM_VERSION, "8.1.0");
		cap.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
		cap.setCapability("appPackage", "com.mobeta.android.demodslv");
		cap.setCapability("appActivity", "com.mobeta.android.demodslv.Launcher");
		//cap.setCapability(MobileCapabilityType.APPLICATION_NAME, " ");
		
		driver = new AndroidDriver(new URL("http://0.0.0.0:4723/wd/hub"),cap);
		driver.manage().timeouts().implicitlyWait(10L, TimeUnit.SECONDS);
		
		
		Common.takescreenshot("sc2");
		System.out.println("screenshot captured");
		
		
	}
	
	
	public static void teardown() {
	//	service.stop();
	}
	
}
