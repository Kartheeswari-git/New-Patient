package HospitalPortalFlow;

import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;


public class xtent extends Common{
	WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	Robot robot;
	String methodname;
	public static ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest test;
	String[][] data = null;
	String monthString="fngn", Testv="tr";
	Markup m;


	@BeforeMethod
	public void launch() {


		htmlReporter = new ExtentHtmlReporter("./DemoReport/Smoke Testing Output.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Automation Reports");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Organization", "FA Softwares");
		extent.setSystemInfo("Browser", "FireFox");
		extent.setSystemInfo("QA", "Kartheeswari");
		String[][] data = {
			    { "Health#", "Claim/Ref#", "Pass" }};
		m = MarkupHelper.createTable(data);
		test=extent.createTest("Pass");
		test.pass(m);

		// LOGIN
	}
	
	@Test
	public void launching() {
			
		
	System.out.println("main");}
	
	@AfterMethod
	public void testing()
	{
		for(int i=0; i<=2;i++) {

		String[][] data = {
			    
			    { monthString+Testv, "Content.2.1", "Content.3.1" },
			    { "Content.1.2", "Content.2.2", "Content.3.2" },
			    { "Content.1.3", "Content.2.3", "Content.3.3" },
			    { "Content.1.4", "Content.2.4", "Content.3.4" }
			};
			m = MarkupHelper.createTable(data);
System.out.println("hgh");

test.pass(m);
test.assignCategory("tag");
test.assignCategory("tag-1","tags","tags", "tag-2");
test.log(Status.INFO, "<b>bold</b>");


// usage
extent.createTest("Test").assignCategory("tag-1").pass("details");


	}}
	@AfterMethod
	public void screen(ITestResult result) throws IOException {
		if(result.getStatus()==ITestResult.FAILURE) {
		//	test.log(Status.FAIL,"Test is Failed"+result.getName());
		//	test.log(Status.FAIL,"Test is Failed"+result.getThrowable());
			String screenshotPAth=takeScreenshot(driver, result.getName());
			test.log(Status.FAIL, "Test is Failed", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPAth).build());
			driver.navigate().refresh();

		}
		if(result.getStatus()==ITestResult.SKIP) {
			test.log(Status.SKIP,"Test is Skipped"+result.getName());
			test.log(Status.SKIP,"Test is Skipped"+result.getThrowable());
			String screenshotPAth=takeScreenshot(driver, result.getName());
			test.log(Status.SKIP, "Test is Skipped", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPAth).build());
		}
		if(result.getStatus()==ITestResult.SUCCESS) {
		//	test.log(Status.PASS,"Test is Skipped"+result.getName());
		//	test.log(Status.PASS,"Test is Skipped"+result.getThrowable());
			String screenshotPAth=takeScreenshot(driver, result.getName());
			test.log(Status.PASS, "Test is Skipped", MediaEntityBuilder.createScreenCaptureFromBase64String(screenshotPAth).build());
		}
		} 

		@AfterSuite
	public void close() {
		extent.flush();

		System.out.println("AfterSuite");
		driver.manage().deleteAllCookies();

		driver.close();
	}
}
