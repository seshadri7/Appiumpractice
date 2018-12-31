package com.appiumdemo.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import com.appiumdemo.base.Setup;

import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;


public class Case1 extends Setup {
	
	@Test(description="Drag and drop")
	public static void num1() throws InterruptedException {
		
	driver.findElement(By.id("com.mobeta.android.demodslv:id/activity_title")).click();
	
	WebDriverWait wait = new WebDriverWait(driver, 5);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.id("com.mobeta.android.demodslv:id/text")));

	Point src=driver.findElement(By.xpath("//android.widget.TextView[@text='Brad Mehldau']/preceding-sibling::android.widget.ImageView")).getLocation();
	
	Dimension des=driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'Herbi')]/preceding-sibling::android.widget.ImageView")).getSize();
	
	int x = src.getX()+des.getWidth()/2;
	int y= src.getY()+des.getHeight()/2;
	
	TouchAction ac = new TouchAction(driver);
	
	ac.press(PointOption.point(x, y)).waitAction(WaitOptions.waitOptions(Duration.ofMillis(3000))).moveTo(PointOption.point(x, y+300)).release().perform();
	
	
		
	}

}
