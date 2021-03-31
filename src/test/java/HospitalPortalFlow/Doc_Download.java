package HospitalPortalFlow;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

import pageObjectModel.ForDashboardFields;
import pageObjectModel.NewPatientFields;

public class Doc_Download {

	public WebDriver driver;
	public WebDriverWait wait;
public String claimno="20210311000004";
public Robot robot;
int j,k;

@BeforeClass
public void launch() {

	System.setProperty("webdriver.gecko.driver",
			"C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64 (1)\\geckodriver.exe");
	driver = new FirefoxDriver();
	driver.get("https://hportal.bagicuat.bajajallianz.com/#/");
	wait = new WebDriverWait(driver, 9000);


	// LOGIN
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

	ForDashboardFields.uName(driver).sendKeys("apollohospital@hat.co.in");
	wait.until(ExpectedConditions.visibilityOf(ForDashboardFields.pWord(driver)));
	ForDashboardFields.pWord(driver).sendKeys("jan@2021");
	wait.until(ExpectedConditions.elementToBeClickable(ForDashboardFields.login(driver)));
	ForDashboardFields.login(driver).click();
}

	@Test
	public void dwnload() throws AWTException, InterruptedException {
		wait = new WebDriverWait(driver, 9000);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@placeholder,'Universal Search')]")));
	NewPatientFields.uni_search(driver).sendKeys(claimno);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.uni_search_icon(driver)));
NewPatientFields.uni_search_icon(driver).click();
	WebElement first=driver.findElement(By.xpath("//*[contains(text(),'"+claimno+"')]"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+claimno+"')]")));
	first.click();
	wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Doc_nav(driver)));
	NewPatientFields.Doc_nav(driver).click();	
	List<WebElement> cmn_dwnld=NewPatientFields.cmn_dwnld(driver);
	cmn_dwnld.size();
	for(int i=0;i<=cmn_dwnld.size();i++) {
		j=i;
		k=j+1;
		WebElement dwnld1=driver.findElement(By.xpath("(//a[contains(@class,'fa fa-download')])["+k+"]"));
		dwnld1.click();
	
	robot = new Robot();
	robot.keyPress(KeyEvent.VK_TAB);
	robot.delay(90);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.delay(90);
	robot.keyPress(KeyEvent.VK_TAB);
	robot.delay(90);
	robot.keyPress(KeyEvent.VK_ENTER);
	robot.keyRelease(KeyEvent.VK_ENTER);
	robot.delay(490);
	Thread.sleep(2000);
	}
	}
}
