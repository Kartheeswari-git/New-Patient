package HospitalPortalFlow;

import java.awt.Robot;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class doc_dwnld {
	WebDriver driver;
	public static WebDriverWait wait;
	public static JavascriptExecutor js;
	Robot robot;
	String methodname;
	public static ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest test;
	String monthString;
	
	
}
