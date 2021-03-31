package HospitalPortalFlow;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pageObjectModel.*;

public class Dashboard {

	WebDriver driver;
	 String status1;
	
@BeforeSuite()
public void launch_Browser() throws InterruptedException {

	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ajith\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");

	//System.setProperty("webdriver.gecko.driver", "C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
	driver=new ChromeDriver();

	//driver=new FirefoxDriver();
	driver.get("\r\n"
			+ "https://auth.bagicsit2.bajajallianz.com/auth/realms/Bagic/protocol/openid-connect/auth?client_id=bagic-ui-hospitalportal&redirect_uri=https%3A%2F%2Fdevhportal.bagicsit2.bajajallianz.com%2F%23%2Fcreate-new&state=81480107-e172-4904-a425-b7895c80fb96&response_mode=fragment&response_type=code&scope=openid&nonce=cea7b274-2e00-4990-bdf3-adc3a784c8ad");
	
	Thread.sleep(2000);
	ForDashboardFields.uName(driver).sendKeys("lilavati@hat.co.in");
	Thread.sleep(2000);

	ForDashboardFields.pWord(driver).sendKeys("May@2020");
	Thread.sleep(2000);
	
	ForDashboardFields.login(driver).click();
	Thread.sleep(2000);
	/*
	 * ForDashboardFields.accept(driver).click(); Thread.sleep(4000);
	 */
Alert alert=driver.switchTo().alert();
alert.accept();
System.out.println("accepted");
Thread.sleep(4000);
}

@Test()
public void Dashboard() throws InterruptedException{
	// status:1 check
	
	
	  status1=ForDashboardFields.status1(driver).getText();
		Thread.sleep(2000);
 
	String status1_count=ForDashboardFields.dbs1(driver).getText();
	Thread.sleep(2000);
	ForDashboardFields.status1(driver).click();
	Thread.sleep(2000);
	int Pending_with_BAGIC=Integer.parseInt(status1_count);
	System.out.println(+Pending_with_BAGIC);
	int Pending_with_BAGIC_Actual=ForDashboardFields.row_count(driver).size();
	System.out.println(+Pending_with_BAGIC_Actual);

	if(Pending_with_BAGIC==Pending_with_BAGIC_Actual) 
	{
		System.out.println(" "+status1+ " is equal ");
	}
	// status:2 check
		String status2=ForDashboardFields.status2(driver).getText();
		Thread.sleep(2000);
		ForDashboardFields.status2(driver).click();
		Thread.sleep(2000);
		String status2_count=ForDashboardFields.dbs2(driver).getText();
		Thread.sleep(2000);

		int Pending_with_Hospital=Integer.parseInt(status2_count);
		Thread.sleep(2000);

		int Pending_with_Hospital_Actual=ForDashboardFields.row_count(driver).size();
		Thread.sleep(2000);

		System.out.println(+Pending_with_Hospital_Actual);
		if(Pending_with_Hospital==Pending_with_Hospital_Actual) {
			System.out.println(" "+status2+ "is" +Pending_with_Hospital_Actual);
		}
		// status:3 check
		String status3=ForDashboardFields.status3(driver).getText();
		Thread.sleep(2000);
		ForDashboardFields.status3(driver).click();
		Thread.sleep(2000);
		String status3_count=ForDashboardFields.dbs3(driver).getText();
		Thread.sleep(2000);

		int Cashless_Approved=Integer.parseInt(status3_count);
		Thread.sleep(2000);

		int Cashless_Approved_Actual=ForDashboardFields.row_count(driver).size();
		Thread.sleep(2000);

		if(Cashless_Approved==Cashless_Approved_Actual) {
			System.out.println(" "+status3+ "is" +Cashless_Approved_Actual);
		}
		// status:4 check
		String status4=ForDashboardFields.status4(driver).getText();
		Thread.sleep(2000);
		ForDashboardFields.status4(driver).click();
		Thread.sleep(2000);
		String status4_count=ForDashboardFields.dbs4(driver).getText();
		Thread.sleep(2000);

		int Cashless_Rejected=Integer.parseInt(status4_count);
		Thread.sleep(2000);

		int Cashless_Rejected_Actual=ForDashboardFields.row_count(driver).size();
		Thread.sleep(2000);
System.out.println(Cashless_Rejected_Actual);
		if(Cashless_Rejected==Cashless_Rejected_Actual) {
			System.out.println(" "+status4+ "is" +Cashless_Rejected_Actual);
		}
}

@AfterSuite()
public void close() {
	driver.close();
}
}
