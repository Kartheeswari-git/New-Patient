package HospitalPortalFlow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import pageObjectModel.ForDashboardFields;
import pageObjectModel.NewPatientFields;

public class myTest extends Common{
	
	

	public WebDriver driver;
	public WebDriverWait wait; 
	public Robot robot;
	String[][] data = null;
	int i,q;
	File folder;
	FirefoxOptions options;
	public ExtentTest test, OpusQ, MaximusQ;
	public ExtentTest Fail_MaximusQ, Skip_OpusQ;
	public ExtentTest Skip_MaximusQ, Fail_OpusQ;
	public ExtentTest Pass_MaximusQ, Pass_OpusQ;
	String Claim_num,MaxOpusq;

	/*
	 * @BeforeSuite public void extentrep() { //extent = new ExtentReports();
	 * 
	 * htmlReporter = new ExtentHtmlReporter("./Report/Query4.html");
	 * htmlReporter.config().setEncoding("utf-8");
	 * htmlReporter.config().setDocumentTitle("Hospital Portal");
	 * htmlReporter.config().setReportName("Automation Test Result");
	 * htmlReporter.config().setTheme(Theme.DARK); extent = new ExtentReports();
	 * 
	 * extent.attachReporter(htmlReporter);
	 * 
	 * extent.setSystemInfo("Organization", "FA Softwares");
	 * extent.setSystemInfo("Browser", "FireFox"); extent.setSystemInfo("QA",
	 * "Kartheeswari"); }
	 */
	@Test
public void launch() throws InterruptedException {
//	   folder=new File(UUID.randomUUID().toString());
//		folder.mkdir();
//		
//		System.setProperty("webdriver.gecko.driver",
//				"C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64 (1)\\geckodriver.exe");
//		   options = new FirefoxOptions();
//	   Map<String, Object> prefs=new HashMap<String, Object>();
//		prefs.put("profile.default_content_settings.popups",0);
//		prefs.put("download.default_directory", folder.getAbsolutePath());
//		options.setExperimentalOption("prefs",prefs);
//		DesiredCapabilities cap=DesiredCapabilities.firefox();
//		cap.setCapability(FirefoxOptions..FIREFOX_OPTIONS,options);
		
		FirefoxProfile profile = new FirefoxProfile();
		 //Set Location to store files after downloading.
		 profile.setPreference("browser.download.dir", "D:\\WebDriverDownloads");
		 profile.setPreference("browser.download.folderList", 2);
		 
		 //Set Preference to not show file download confirmation dialogue using MIME types Of different file extension types.
		 profile.setPreference("browser.helperApps.neverAsk.saveToDisk", 
		     "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;"); 
		 
		 profile.setPreference( "browser.download.manager.showWhenStarting", false );
		 profile.setPreference( "pdfjs.disabled", true );
		 FirefoxOptions option=new FirefoxOptions();
		 option.setProfile(profile);
			System.setProperty("webdriver.gecko.driver","C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64 (1)\\geckodriver.exe");
			

		 //Pass FProfile parameter In webdriver to use preferences to download file.
					driver = new FirefoxDriver(options);
		 
		 // Open APP to download application
		 driver.get("https://toolsqa.com/automation-practice-form/");
		 
		 // Click to download 
		 driver.findElement(By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[1]/span/div/div[2]/div[2]/svg")).click();
		 
		 //Halting the execution for 5 secs to donwload the file completely
		 Thread.sleep(5000);
	test = extent.createTest("<b>Query</b>");

	MaximusQ=test.createNode("<b>Maximus Query</b>");

	OpusQ=test.createNode("<b> Opus Query</b>");

}


}