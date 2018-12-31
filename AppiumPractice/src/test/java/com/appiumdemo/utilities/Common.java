package com.appiumdemo.utilities;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.appiumdemo.base.Setup;

public class Common extends Setup{
	
	
	public static void takescreenshot(String screenshotname) throws IOException {
		
		String imageloaction = System.getProperty("user.dir")+"\\src\\test\\resources\\com\\appiumdemo\\screenshots\\";
		String filename = imageloaction+screenshotname+".png";
		
		File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(srcfile, new File(filename), true);
		
	
		
		
	}
	
	

}
