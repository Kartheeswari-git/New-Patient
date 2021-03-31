package HospitalPortalFlow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import pageObjectModel.ForDashboardFields;
import pageObjectModel.NewPatientFields;

public class Common {
	public Robot robot;
	public static WebDriver driver;
	ExtentTest node;
	public ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest test;
	

	public void zoomout() throws AWTException {
	robot.keyPress(KeyEvent.VK_CONTROL);
	robot.keyPress(KeyEvent.VK_MINUS);
	robot.keyRelease(KeyEvent.VK_MINUS);
	robot.keyRelease(KeyEvent.VK_CONTROL);
}
	
	public void docupload() {
		
		robot.delay(450);

		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_V);
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.delay(90);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(90);
		robot.keyPress(KeyEvent.VK_TAB);
		robot.delay(90);
		robot.keyPress(KeyEvent.VK_ENTER);
		robot.keyRelease(KeyEvent.VK_ENTER);
	}
	
	
	
/*	@BeforeSuite
	public void Extent() {
		htmlReporter = new ExtentHtmlReporter("./Report/Smoke Testing Output.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);
		test = extent.createTest("Hospital Portal");

		node=test.createNode("New Patient Creation");
		extent.setSystemInfo("Organization", "FA Softwares");
		extent.setSystemInfo("Browser", "FireFox");
		extent.setSystemInfo("QA", "Kartheeswari");
	}*/
	
	public static String takeScreenshot(WebDriver driver, String Screenshotname) throws IOException {
		String datename=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
TakesScreenshot ts=(TakesScreenshot) driver;
File source=ts.getScreenshotAs(OutputType.FILE);
String dest=System.getProperty("user.dir")+"/Failedscreenshot/"+Screenshotname+datename+".png";
File finaldest=new File(dest);
FileUtils.copyFile(source, finaldest);
return dest;
	}
	
/*	@AfterSuite
	public void close() {
		extent.flush();
		System.out.println("AfterSuite");
		driver.manage().deleteAllCookies();

		driver.close();
	} */
}