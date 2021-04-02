//
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import pageObjectModel.*;


public class new_Patient_Creation extends Common implements ITestListener {

	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Robot robot, robot1;
	String methodname, RefNum;
	public ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest test, Opus, Maximus, InValid;

	String[][] data = null;
	String monthString;
	int element;
	public ExtentTest node;
	String PolicyType,PolicyNum,HealthNum,ValidInvalid,MaxOpus;
	
	

	@DataProvider(name = "datapass")
	public String[][] mdp() throws BiffException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		data = launch_Browser();
		return data;
	}

	@BeforeSuite
	public void Extent() {
htmlReporter = new ExtentHtmlReporter("./Report/New Patient Creation.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Hospital Portal");
		htmlReporter.config().setReportName("Automation Test Result");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Organization", "FA Softwares");
		extent.setSystemInfo("Browser", "FireFox");
		extent.setSystemInfo("QA", "Kartheeswari");
	}
	
	public String[][] launch_Browser() throws InterruptedException, BiffException, IOException {

		// Read Excel data
		File file = new File("C:\\Users\\Ajith\\Documents\\HP25.xls");
		FileInputStream excel = new FileInputStream(file);
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(6);
		int rowcount = sheet.getRows();
		int colcount = sheet.getColumns();
		System.out.println(rowcount);
		System.out.println(colcount);
		String testdata[][] = new String[rowcount - 1][colcount];
		for (int i = 1; i < rowcount; i++)
			for (int j = 0; j < colcount; j++) {
				testdata[i - 1][j] = sheet.getCell(j, i).getContents();

				System.out.println(testdata[i - 1][j]);

			}
		return testdata;
	}

	@BeforeTest
	public void launch() throws AWTException {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64 (1)\\geckodriver.exe");
		FirefoxOptions options = new FirefoxOptions();
		   options.addPreference("media.navigator.permission.disabled", true);

		driver = new FirefoxDriver(options);
		driver.get("https://hportal.bagicuat.bajajallianz.com/#/");
		 wait = new WebDriverWait(driver, 9000);

		// LOGIN
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
		ForDashboardFields.uName(driver).sendKeys("apollohospital@hat.co.in");
		wait.until(ExpectedConditions.visibilityOf(ForDashboardFields.pWord(driver)));
		ForDashboardFields.pWord(driver).sendKeys("jan@2021");
		wait.until(ExpectedConditions.elementToBeClickable(ForDashboardFields.login(driver)));
		ForDashboardFields.login(driver).click();
		robot1 = new Robot();

		robot1.keyPress(KeyEvent.VK_CONTROL);
		robot1.keyPress(KeyEvent.VK_MINUS);
		robot1.keyRelease(KeyEvent.VK_MINUS);
		robot1.keyRelease(KeyEvent.VK_CONTROL);
		robot1.keyPress(KeyEvent.VK_CONTROL);
		robot1.keyPress(KeyEvent.VK_MINUS);
		robot1.keyRelease(KeyEvent.VK_MINUS);
		robot1.keyRelease(KeyEvent.VK_CONTROL);
		test = extent.createTest("<b>New Patient Creation</b>");
		Maximus=test.createNode("<b>Maximus Records Creation</b>");

		Opus=test.createNode("<b> Opus Records Creation</b>");
		InValid=test.createNode("<b> Invalid Data Testing</b>");
System.out.println("Done");
	}

	@Test(dataProvider = "datapass", priority = 1)
	
		public void fill_data(String Max_Opus, String Valid_Invalid,String Policy_type, String policy_Num, String Corporate_Name, String Employee_code,
				String Health_Num, String DOA, String DOD, String IPD_Patient_Number, String Total_Estimated_Bill,
				String Provisional_Diagnosis, String Patient_Contact_Number, String Attendant_Contact_Number,
				String Treating_Doctor_Name, String Room_Type, String Room_Type_Description, String Patient_Email,
				String Path, String DOC_DD, String Doc_Path)
				throws InterruptedException, AWTException, IOException {
			

			robot = new Robot();

			// New PAtient Creation
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.new_patient_tab(driver)));
			NewPatientFields.new_patient_tab(driver).click();
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", NewPatientFields.new_patient_tab(driver));
			Thread.sleep(2000);

			PolicyType=Policy_type;
			PolicyNum=policy_Num;
			HealthNum=Health_Num;
			MaxOpus=Max_Opus;
			ValidInvalid=Valid_Invalid;
			
			
			if (Valid_Invalid.contentEquals("Invalid1")) {
				switch (Policy_type) {
				case "Individual": {
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Advanced_search(driver)));
					NewPatientFields.Advanced_search(driver).click();
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.individual(driver)));

					NewPatientFields.individual(driver).click();
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.policy_num(driver)));

					NewPatientFields.policy_num(driver).sendKeys(policy_Num);
					JavascriptExecutor jsv_search = ((JavascriptExecutor) driver);
					jsv_search.executeScript("arguments[0].scrollIntoView(true);", NewPatientFields.policy_num(driver));
					
				}
					break;
				case "Group": {
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Advanced_search(driver)));
					NewPatientFields.Advanced_search(driver).click();
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.grp(driver)));

					NewPatientFields.grp(driver).click();

					if (policy_Num != null) {
						wait.until(ExpectedConditions.visibilityOf(NewPatientFields.policy_num(driver)));
						NewPatientFields.policy_num(driver).sendKeys(policy_Num);
					}

					if (Corporate_Name != null) {
						wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Corp_name(driver)));
						NewPatientFields.Corp_name(driver).sendKeys(Corporate_Name);
					}

					if (Employee_code != null) {
						wait.until(ExpectedConditions.visibilityOf(NewPatientFields.emp_code(driver)));
						NewPatientFields.emp_code(driver).sendKeys(Employee_code);
						JavascriptExecutor jsi_search = ((JavascriptExecutor) driver);

						jsi_search.executeScript("scroll(0, -250);");
						Thread.sleep(2000);
						
					}

				}
					break;
				}
				if (Health_Num != null && !Health_Num.isEmpty()) {
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.HealthCare_ID(driver)));

					NewPatientFields.HealthCare_ID(driver).sendKeys(Health_Num);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.search_button(driver)));
					
				}
				driver.findElement(By.xpath("//*[@id=\"headingOne\"]/h2/button")).click();
			String searchHealthCard=driver.findElement(By.xpath("//*[@id=\"swal2-content\"]")).getText();
			Assert.assertEquals("Please Search valid Health card no", searchHealthCard);
			
			System.out.println("Pass"+searchHealthCard);
			}
			
							
				if (Valid_Invalid.contentEquals("Invalid2")) {
			
				if (Health_Num != null && !Health_Num.isEmpty()) {
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.HealthCare_ID(driver)));

					NewPatientFields.HealthCare_ID(driver).sendKeys(Health_Num);				
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Advanced_search(driver)));
					NewPatientFields.Advanced_search(driver).click();
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.individual(driver)));

					NewPatientFields.individual(driver).click();
					WebElement policy_search1 = driver.findElement(By.xpath(
							"//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/button[1]"));
					JavascriptExecutor js_searchivs = ((JavascriptExecutor) driver);

					js_searchivs.executeScript("arguments[0].click()", policy_search1);
				}
				String searchHealthCard2=driver.findElement(By.xpath("//*[@id=\"swal2-content\"]")).getText();
				Assert.assertEquals("Please Enter Policy No", searchHealthCard2);
				
				System.out.println("Pass"+searchHealthCard2);	}		
			
				if (Valid_Invalid.contentEquals("Invalid3")) {
					
					if (Health_Num != null && !Health_Num.isEmpty()) {
						wait.until(ExpectedConditions.visibilityOf(NewPatientFields.HealthCare_ID(driver)));

						NewPatientFields.HealthCare_ID(driver).sendKeys(Health_Num);
						wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Advanced_search(driver)));
						NewPatientFields.Advanced_search(driver).click();
						wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.grp(driver)));

						NewPatientFields.grp(driver).click();
						WebElement grp_search1 = driver.findElement(By.xpath(
								"//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[5]/button[1]"));
						JavascriptExecutor po_search = ((JavascriptExecutor) driver);

						po_search.executeScript("arguments[0].click()", grp_search1);					}
					String searchHealthCard3=driver.findElement(By.xpath("//*[@id=\"swal2-content\"]")).getText();
					Assert.assertEquals("Please Enter any Search criteria", searchHealthCard3);
					
					System.out.println("Pass"+searchHealthCard3);	}	
	if (Valid_Invalid.contentEquals("Invalid4")) {
					
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.HealthCare_ID(driver)));

		NewPatientFields.HealthCare_ID(driver).sendKeys(Health_Num);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.search_button(driver)));
		NewPatientFields.search_button(driver).click();
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.search_value(driver)));
		JavascriptExecutor f_search = ((JavascriptExecutor) driver);

		f_search.executeScript("arguments[0].click()", NewPatientFields.search_value(driver));
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.IPD_Patient_num(driver)));
		NewPatientFields.IPD_Patient_num(driver).sendKeys(IPD_Patient_Number);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Total_Estimated_Bill(driver)));
		NewPatientFields.Total_Estimated_Bill(driver).sendKeys(Total_Estimated_Bill);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Provisional_Diagnosis(driver)));
		NewPatientFields.Provisional_Diagnosis(driver).sendKeys(Provisional_Diagnosis);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Patient_Contact_Number(driver)));
		NewPatientFields.Patient_Contact_Number(driver).sendKeys(Patient_Contact_Number);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Attendant_Contact_Number(driver)));
		NewPatientFields.Attendant_Contact_Number(driver).sendKeys(Attendant_Contact_Number);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Name_of_the_Treating_Doctor(driver)));
		NewPatientFields.Name_of_the_Treating_Doctor(driver).sendKeys(Treating_Doctor_Name);
		Thread.sleep(1000);
		WebElement docpanel=driver.findElement(By.xpath("//*[@id=\"headingTwo\"]/h2/button"));
		wait.until(ExpectedConditions.elementToBeClickable(docpanel));
		f_search.executeScript("scroll(0, 250);");

		docpanel.click();
		
		WebElement docavail=driver.findElement(By.partialLinkText("//*[@id=\"drop-area\"]"));
		Assert.assertFalse(docavail.isDisplayed());
		System.out.println("Pass 4");
	}	
	if (Valid_Invalid.contentEquals("valid")) 
	{
			switch (Policy_type) {
			case "Individual": {
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Advanced_search(driver)));
				NewPatientFields.Advanced_search(driver).click();
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.individual(driver)));

				NewPatientFields.individual(driver).click();
				wait.until(ExpectedConditions.visibilityOf(NewPatientFields.policy_num(driver)));

				NewPatientFields.policy_num(driver).sendKeys(policy_Num);
				JavascriptExecutor js_search = ((JavascriptExecutor) driver);
				js_search.executeScript("arguments[0].scrollIntoView(true);", NewPatientFields.policy_num(driver));
				WebElement policy_search = driver.findElement(By.xpath(
						"//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[2]/button[1]"));
				js_search.executeScript("arguments[0].click()", policy_search);
				js_search.executeScript("scroll(0, -250);");
				Thread.sleep(2000);
				NewPatientFields.search_value(driver).click();
			}
				break;
			case "Group": {
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Advanced_search(driver)));
				NewPatientFields.Advanced_search(driver).click();
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.grp(driver)));

				NewPatientFields.grp(driver).click();

				if (policy_Num != null) {
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.policy_num(driver)));
					NewPatientFields.policy_num(driver).sendKeys(policy_Num);
				}

				if (Corporate_Name != null) {
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Corp_name(driver)));
					NewPatientFields.Corp_name(driver).sendKeys(Corporate_Name);
				}

				if (Employee_code != null) {
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.emp_code(driver)));
					NewPatientFields.emp_code(driver).sendKeys(Employee_code);
					WebElement grp_search = driver.findElement(By.xpath(
							"//body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[1]/div[1]/form[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]/div[2]/div[3]/div[1]/div[5]/button[1]"));
					JavascriptExecutor js1_search = ((JavascriptExecutor) driver);

					js1_search.executeScript("arguments[0].click()", grp_search);
					js1_search.executeScript("scroll(0, -250);");
					Thread.sleep(2000);
					driver.findElement(By.xpath(
							"//*[@id=\"collapseThree\"]/div/div/div/div[2]/div/div[1]/div/div[2]/table/tbody/tr[1]/td[4]/span/span/a"))
							.click();
				}

			}
				break;
			}
			if (Health_Num != null && !Health_Num.isEmpty()) {
				wait.until(ExpectedConditions.visibilityOf(NewPatientFields.HealthCare_ID(driver)));

				NewPatientFields.HealthCare_ID(driver).sendKeys(Health_Num);
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.search_button(driver)));
				NewPatientFields.search_button(driver).click();
				Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.search_value(driver)));
				JavascriptExecutor f_search = ((JavascriptExecutor) driver);

				f_search.executeScript("arguments[0].click()", NewPatientFields.search_value(driver));

				//NewPatientFields.search_value(driver).click();
			}
			// Fill date in Claim Details
			    Thread.sleep(2000);
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.DOA(driver)));
				NewPatientFields.DOA(driver).click();
				
			
			String[] dates = DOA.split(" ");
			String dateString = dates[0];
			String timeString = dates[1];
			String meridian = dates[2];

			String[] datess = dateString.split("/");
			String datesss = datess[0];
			String month = datess[1];
			String year = datess[2];

			String[] hours = timeString.split(":");
			String hh = hours[0];
			String mm = hours[1];

			System.out.println("datesss " + dateString);
			System.out.println("month " + timeString);
			System.out.println("year " + meridian);

			System.out.println("datesss " + datesss);
			System.out.println("month " + month);
			System.out.println("year " + year);
			System.out.println("hh " + hh);
			System.out.println("mm " + mm);
			System.out.println("meridian " + meridian);

			// Click month and then value
			NewPatientFields.month(driver).click();
			int monthv = Integer.parseInt(month);
			int mm1 = Integer.parseInt(mm);
			int mm2 = mm1 + 1;

	//Click month
			wait.until(ExpectedConditions.presenceOfElementLocated(
					By.xpath(("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[4]/div[2]/button[" + monthv
							+ "]/span[2]"))));
			driver.findElement(By.xpath(
					"//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[4]/div[2]/button[" + monthv + "]/span[2]"))
					.click();

	//Click date
			List<WebElement> active = driver.findElements(By
					.xpath("//button[contains(@class,'datepicker-day flex align-center justify-content-center enable')]"));
			System.out.println("Size" + active.size());
			for (int i = 0; i < active.size(); i++) {
				System.out.println("Active Elements are:" + active.get(i).getText());
				if ((active.get(i).getText()).equals(datesss)) {
					active.get(i).click();

				}
			}

			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div")));

			Actions action = new Actions(driver);
			WebElement hourside = driver
					.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div"));
			action.moveToElement(hourside);
			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
					"//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div/button[" + hh + "]/span[2]")));
			driver.findElement(By
					.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div/button[" + hh + "]/span[2]"))
					.click();
	//Click minutes
			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
					By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div")));

			WebElement minside = driver
					.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div"));
			action.moveToElement(minside);
			WebElement minvalue = driver.findElement(By.xpath(
					"//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div/button[" + mm2 + "]/span[2]"));
			wait.until(ExpectedConditions.visibilityOf(minvalue));
			JavascriptExecutor jch = ((JavascriptExecutor) driver);

			jch.executeScript("arguments[0].click()", minvalue);

			
	//Click meridian
wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
		By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div")));
			WebElement meridianside = driver
					.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div"));
			action.moveToElement(meridianside);

			if (meridian.equalsIgnoreCase("AM")) {
				
				WebElement AM=driver.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div/button[1]"));
				wait.until(ExpectedConditions.elementToBeClickable(
						AM));
				JavascriptExecutor jch1 = ((JavascriptExecutor) driver);

				jch1.executeScript("arguments[0].click()", AM);
			} else if (meridian.equalsIgnoreCase("PM")) {
				
				WebElement PM=driver.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div/button[2]"));
				wait.until(ExpectedConditions.elementToBeClickable(PM));

				JavascriptExecutor jch2 = ((JavascriptExecutor) driver);

				jch2.executeScript("arguments[0].click()", PM);		
				}

	//end of date selection
			wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[2]/button")));
			WebElement tick=driver.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[2]/button"));
			JavascriptExecutor jch2 = ((JavascriptExecutor) driver);

			jch2.executeScript("arguments[0].click()", tick);	//DOD
JavascriptExecutor js9 = ((JavascriptExecutor) driver);

js9.executeScript("arguments[0].scrollIntoView(true);", NewPatientFields.DOD(driver));

			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.DOD(driver)));
			NewPatientFields.DOD(driver).click();
			Thread.sleep(2000);
			String[] dates1 = DOD.split("/");
			String datess1 = dates1[0];
			String month1 = dates1[1];
			String year1 = dates1[2];

			System.out.println("DOD_datesss " + datess1);
			System.out.println("DOD_month " + month1);
			System.out.println("DOD_year " + year1);

			WebElement month_DOD = driver.findElement(By.xpath(
					"/html/body/div/div/div[2]/div[1]/div/div/div/div/div/form/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div[4]/div/div[2]/div/div/div/div/div[1]/div[2]/span[1]/button/span[2]"));
			wait.until(ExpectedConditions.elementToBeClickable(month_DOD));
			month_DOD.click();
	// NewPatientFields.month(driver).click();
			int monthv1 = Integer.parseInt(month1);
			int datess1_dod = Integer.parseInt(datess1);
			System.out.println(+datess1_dod);

	//Click month
			WebElement splitted_month = driver
					.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[4]/div[2]/button["
							+ monthv1 + "]/span[2]"));
			wait.until(ExpectedConditions.elementToBeClickable(splitted_month));

			splitted_month.click();

	//Click date
	//
	////presence in DOM
	//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ID")));
	//
	////scrolling
	//WebElement element = driver.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[3]/span/div/button["+datess1_dod+"]/span[2]"));  
	//
	////clickable
	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[3]/span/div/button["+datess1_dod+"]/span[2]")));
			List<WebElement> active2 = driver.findElements(By
					.xpath("//button[contains(@class,'datepicker-day flex align-center justify-content-center enable')]"));
			System.out.println("Size" + active2.size());
			for (int i = active2.size() - 1; i >= 0; i--) {
				String DOA_v=active2.get(i).getText();
				System.out.println("Active Elements are:" + DOA_v);
				if (DOA_v.equals(datess1)) {
					{
					  try{
						//	JavascriptExecutor jdate = ((JavascriptExecutor) driver);
						  Thread.sleep(1000);
						// WebElement Date= driver.findElement(By.xpath("(//*[contains(text(),'"+DOA_v+"')])[4]"));
						 active2.get(i).click();
						 break;							 
					  }
					catch(StaleElementReferenceException e)
					{ 
						e.printStackTrace();
						}
					 
					  catch(ElementClickInterceptedException eI)
						{ 
							eI.printStackTrace();
					}
				}
			}}
			Thread.sleep(1000);
			
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.IPD_Patient_num(driver)));
			NewPatientFields.IPD_Patient_num(driver).sendKeys(IPD_Patient_Number);
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Total_Estimated_Bill(driver)));
			NewPatientFields.Total_Estimated_Bill(driver).sendKeys(Total_Estimated_Bill);
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Provisional_Diagnosis(driver)));
			NewPatientFields.Provisional_Diagnosis(driver).sendKeys(Provisional_Diagnosis);
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Patient_Contact_Number(driver)));
			NewPatientFields.Patient_Contact_Number(driver).sendKeys(Patient_Contact_Number);
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Attendant_Contact_Number(driver)));
			NewPatientFields.Attendant_Contact_Number(driver).sendKeys(Attendant_Contact_Number);
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Name_of_the_Treating_Doctor(driver)));
			NewPatientFields.Name_of_the_Treating_Doctor(driver).sendKeys(Treating_Doctor_Name);
			Thread.sleep(1000);
//driver.findElement(By.xpath("//*[@id=\"collapseOne\"]/div/div/div[1]/div[2]/div/div[5]/div/div/input")).sendKeys(Room_Type);
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Room_Type(driver)));

			JavascriptExecutor js = ((JavascriptExecutor) driver);
			js.executeScript("arguments[0].scrollIntoView(true);", NewPatientFields.Room_Type(driver));

			Select room = new Select(NewPatientFields.Room_Type(driver));
			room.selectByVisibleText(Room_Type);
			if (!Room_Type_Description.isEmpty()) {
				wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Room_Type_others(driver)));
				NewPatientFields.Room_Type_others(driver).sendKeys(Room_Type_Description);
			}
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.email(driver)));

			NewPatientFields.email(driver).sendKeys(Patient_Email);
Thread.sleep(2000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Next(driver)));
			NewPatientFields.Next(driver).click();
			
			String DD21 = "";
			String DD22 = "";
			String DD23 = "";
			String DD24 = "";

			String DD25 = "";
			String DD26 = "";
			try {
			String input = DOC_DD;
			if (input !=null && input !="") {
				String[] inputArrayd2 = input.split(",");
				element=inputArrayd2.length;

				if (inputArrayd2 != null && inputArrayd2.length > 0) {
					for (int i = 0; i < inputArrayd2.length; i++) {
						switch (i) {
						case 0:
							DD21 = inputArrayd2[i];
							break;
						case 1:
							DD22 = inputArrayd2[i];
							break;
						case 2:
							DD23 = inputArrayd2[i];
							break;
						case 3:
							DD24 = inputArrayd2[i];
							break;
						case 4:
							DD25 = inputArrayd2[i];
							break;
						case 5:
							DD26 = inputArrayd2[i];
							break;
						
						
						}
					}
				}
			}

			System.out.println("first " + DD21);
			System.out.println("two " + DD22);
			System.out.println("three " + DD23);
			System.out.println("four " + DD24);
			System.out.println("first " + DD25);
			System.out.println("two " + DD26);

			} catch (Exception e) {
			e.printStackTrace();
			}

			String DD2_Doc1 = "";
			String DD2_Doc2 = "";
			String DD2_Doc3 = "";
			String DD2_Doc4 = "";

			String DD2_Doc5 = "";
			String DD2_Doc6 = "";
			
			try {
			String input = Doc_Path;

			if (input !=null && input !="") {
				String[] inputArray2 = input.split(",");
				if (inputArray2 != null && inputArray2.length > 0) {
					for (int i = 0; i < inputArray2.length; i++) {
						switch (i) {
						case 0:
							DD2_Doc1 = inputArray2[i];
							break;
						case 1:
							DD2_Doc2 = inputArray2[i];
							break;
						case 2:
							DD2_Doc3 = inputArray2[i];
							break;
						case 3:
							DD2_Doc4 = inputArray2[i];
							break;
						case 4:
							DD2_Doc5 = inputArray2[i];
							break;
						case 5:
							DD2_Doc6 = inputArray2[i];
							break;
						
						}
					}
				}
			}

			System.out.println("first " + DD2_Doc1);
			System.out.println("two " + DD2_Doc2);
			System.out.println("three " + DD2_Doc3);
			System.out.println("four " + DD2_Doc4);
			System.out.println("first " + DD2_Doc5);
			System.out.println("two " + DD2_Doc6);
			

			} catch (Exception e) {

			e.printStackTrace();
			}

	//Document
			WebElement doc = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[1]"));
			Select docvalue = new Select(doc);
			docvalue.selectByVisibleText("Preauth Form");
if (!Path.contentEquals("image")) {
			WebElement file1 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[1]"));
			wait.until(ExpectedConditions.visibilityOf(file1));

			file1.click();
	
			StringSelection strSelection = new StringSelection(Path);
			Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard.setContents(strSelection, null);
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
if (Path.contentEquals("image")) {
	WebElement img = driver
			.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image00'])[1]"));					
	wait.until(ExpectedConditions.elementToBeClickable(img));	
	img.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
	
}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

			// Document2
			WebElement doc2 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[2]"));
			Select docvalue2 = new Select(doc2);
			docvalue2.selectByVisibleText(DD21);
			if(!DD2_Doc1.contentEquals("image")) {
			WebElement file2 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[2]"));
			wait.until(ExpectedConditions.visibilityOf(file2));
			file2.click();

			StringSelection strSelection2 = new StringSelection(DD2_Doc1);
			Clipboard clipboard2 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard2.setContents(strSelection2, null);
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
			if(DD2_Doc1.contentEquals("image")) 
				{
				WebElement img1 = driver
						.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image01'])[1]"));					
					img1.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
					NewPatientFields.img_capture(driver).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
				}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

			// Doc3
			if(element>1) {

			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.add_button(driver)));
			NewPatientFields.add_button(driver).click();
			

			WebElement doc3 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[3]"));
			Select docvalue3 = new Select(doc3);
			docvalue3.selectByVisibleText(DD22);
			if(!DD2_Doc2.contentEquals("image")) 
			{
			WebElement file3 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[3]"));
			wait.until(ExpectedConditions.visibilityOf(file3));

			file3.click();

			StringSelection strSelection3 = new StringSelection(DD2_Doc2);
			Clipboard clipboard3 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard3.setContents(strSelection3, null);
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
			if(DD2_Doc2.contentEquals("image")) 
			{
			WebElement img2 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image11'])[1]"));					
				img2.click();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
				NewPatientFields.img_capture(driver).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
				NewPatientFields.ok(driver).click();
			}
	//for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
			}
	//Doc4
			if(element>2) {
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.add_button(driver)));
			NewPatientFields.add_button(driver).click();


			Thread.sleep(3000);
			WebElement doc4 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[4]"));
			Select docvalue4 = new Select(doc4);
			docvalue4.selectByVisibleText(DD23);
			if(!DD2_Doc3.contentEquals("image")) {

			WebElement file4 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[4]"));
			wait.until(ExpectedConditions.visibilityOf(file4));

			file4.click();

			StringSelection strSelection4 = new StringSelection(DD2_Doc3);
			Clipboard clipboard4 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard4.setContents(strSelection4, null);
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
			robot.keyRelease(KeyEvent.VK_ENTER);}
			// for upload
			if (DD2_Doc3.contentEquals("image")) {
				WebElement img3 = driver
						.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image21'])[1]"));					
					img3.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
					NewPatientFields.img_capture(driver).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
				
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
	}
	//Doc 5			
			if(element>3) {
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.add_button(driver)));
			NewPatientFields.add_button(driver).click(); 


			Thread.sleep(2000);
			WebElement doc5 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[5]"));
			Select docvalue5 = new Select(doc5);
			docvalue5.selectByVisibleText(DD24);
			if(!DD2_Doc4.contentEquals("image")) {

			WebElement file5 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[5]"));
			wait.until(ExpectedConditions.visibilityOf(file5));

			file5.click();

			StringSelection strSelection5 = new StringSelection(DD2_Doc4);
			Clipboard clipboard5 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard5.setContents(strSelection5, null);
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
	//for upload
			if (DD2_Doc4.contentEquals("image")) {
				WebElement img4 = driver
						.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image31'])[1]"));					
					img4.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
					NewPatientFields.img_capture(driver).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
				
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
	}
			// Doc6
			if(element>4) {
				wait.until(ExpectedConditions.visibilityOf(NewPatientFields.add_button(driver)));
			NewPatientFields.add_button(driver).click();
			Thread.sleep(3000);

			WebElement doc6 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[6]"));
			JavascriptExecutor js4 = ((JavascriptExecutor) driver);
			js4.executeScript("arguments[0].scrollIntoView(true);", doc6);
			Select docvalue6 = new Select(doc6);
			docvalue6.selectByVisibleText(DD25);
			if(!DD2_Doc5.contentEquals("image")) {

			WebElement file6 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[6]"));
			wait.until(ExpectedConditions.visibilityOf(file6));

			file6.click();

			StringSelection strSelection6 = new StringSelection(DD2_Doc5);
			Clipboard clipboard6 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard6.setContents(strSelection6, null);
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
			if (DD2_Doc5.contentEquals("image")) {
				WebElement img5 = driver
						.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image41'])[1]"));					
					img5.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
					NewPatientFields.img_capture(driver).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
				
			}	//for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
	}
	//Doc7
			if(element>5) {
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.add_button(driver)));
			NewPatientFields.add_button(driver).click();
			

			Thread.sleep(2000);
			WebElement doc7 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[7]"));

			Select docvalue7 = new Select(doc7);
			docvalue7.selectByVisibleText(DD26);
			if(!DD2_Doc6.contentEquals("image")) {

			WebElement file7 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[7]"));
			wait.until(ExpectedConditions.visibilityOf(file7));

			file7.click();

			StringSelection strSelection7 = new StringSelection(DD2_Doc6);
			Clipboard clipboard7 = Toolkit.getDefaultToolkit().getSystemClipboard();
			clipboard7.setContents(strSelection7, null);
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
			//for upload
			if (DD2_Doc6.contentEquals("image")) {
				WebElement img6 = driver
						.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@name='image51'])[1]"));					
					img6.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
					NewPatientFields.img_capture(driver).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
				
			}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
			}
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.submit(driver)));
			NewPatientFields.submit(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			WebElement ref_no = driver.findElement(By.xpath("//div[contains(@class,'swal2-content')]"));
			RefNum=ref_no.getText();
			System.out.println(Health_Num +""+ ref_no.getText());
			// driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
			driver.navigate().refresh();
	}

}



	@AfterMethod
	public void screen(ITestResult result) throws IOException {
	if(result.getStatus()==ITestResult.FAILURE) {
	//	test.log(Status.FAIL,"Test is Failed"+result.getName());
	//	test.log(Status.FAIL,"Test is Failed"+result.getThrowable());
		String screenshotPAth=takeScreenshot(driver, result.getName());
	//	test.log(Status.FAIL, "Test is Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
		if((MaxOpus.contentEquals("Maximus"))&&(ValidInvalid.contentEquals("Valid"))) {
		if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
			{
			Maximus.fail(""+PolicyNum+" is Failed").addScreenCaptureFromPath(screenshotPAth);
		//	Fail_Maximus.assignCategory("Individual","Maximus");
			}
		else if ((PolicyType.contentEquals("Group")))
		{
			Maximus.fail(""+PolicyNum+" is Failed").addScreenCaptureFromPath(screenshotPAth);
	//	Fail_Maximus.assignCategory("Group","Maximus");
		}

		else
			{
			Maximus.fail(""+HealthNum+"is Failed").addScreenCaptureFromPath(screenshotPAth);
			//	Fail_Maximus.assignCategory("Health","Maximus");
			}}
		if((MaxOpus.contentEquals("Opus"))&&(ValidInvalid.contentEquals("Valid"))) {
			if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
				{
				Opus.fail(""+PolicyNum+" is Failed").addScreenCaptureFromPath(screenshotPAth);
			//	Fail_Opus.assignCategory("Individual","Opus");
				}
			else if ((PolicyType.contentEquals("Group")))
			{
				Opus.fail(""+PolicyNum+" is Failed").addScreenCaptureFromPath(screenshotPAth);
			//Fail_Maximus.assignCategory("Group","Opus");
			}
				else
				{
					Opus.fail(""+HealthNum+" is Failed").addScreenCaptureFromPath(screenshotPAth);
				//	Fail_Opus.assignCategory("Individual","Opus");
				}}
		if(ValidInvalid.contains("Invalid")) {
			if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
			{
			InValid.fail(""+PolicyNum+" is invalid").addScreenCaptureFromPath(screenshotPAth);
		//	InValid.assignCategory("InValid");
			}
			else
			{
				InValid.fail(""+HealthNum+" is invalid").addScreenCaptureFromPath(screenshotPAth);
		//		InValid.assignCategory("InValid");
			}}
		driver.navigate().refresh();	

	}
/*	if(result.getStatus()==ITestResult.SKIP) {
	//	test.log(Status.SKIP,"Test is Skipped"+result.getName());
	//	test.log(Status.SKIP,"Test is Skipped"+result.getThrowable());
		String screenshotPAth_skip=takeScreenshot(driver, result.getName());
	//	test.log(Status.SKIP, "Test is Skipped", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
		if((MaxOpus.contentEquals("Maximus"))&&(ValidInvalid.contentEquals("Valid"))) {

		if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group"))) {
			Skip_Maximus.skip(""+PolicyNum+"is Skipped").addScreenCaptureFromPath(screenshotPAth_skip);
			}
		else
		{
			Skip_Maximus.info(""+HealthNum+"is Skipped").addScreenCaptureFromPath(screenshotPAth_skip);
		}}
		if((MaxOpus.contentEquals("Opus"))&&(ValidInvalid.contentEquals("Valid"))) {
			if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
				{
				Skip_Opus.skip(""+PolicyNum+"is Failed").addScreenCaptureFromPath(screenshotPAth_skip);
			}
				else
				{
					Skip_Opus.skip(""+HealthNum+"is Failed").addScreenCaptureFromPath(screenshotPAth_skip);
				}
		}
		if(ValidInvalid.contentEquals("Valid")) {
			InValid.skip("").addScreenCaptureFromPath(screenshotPAth_skip);
		}
		driver.navigate().refresh();	
	} */
	if(result.getStatus()==ITestResult.SUCCESS) {
	//	test.log(Status.PASS,"Test is Passed"+result.getName());
	//	test.log(Status.PASS,"Test is Passed"+result.getThrowable());
		String screenshotPAth_pass=takeScreenshot(driver, result.getName());
	//	test.log(Status.PASS, "Test is Passed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
		if((MaxOpus.contentEquals("Maximus"))&&(ValidInvalid.contentEquals("Valid"))) {

		if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
		{
			Maximus.pass(""+PolicyNum+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
	}
		else
		{
			Maximus.info(""+HealthNum+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
		}}
		if((MaxOpus.contentEquals("Opus"))&&(ValidInvalid.contentEquals("Valid"))) {
			if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
				{
				Opus.pass(""+PolicyNum+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
			}
				else
				{
					Opus.pass(""+HealthNum+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
				}}
		if(ValidInvalid.contains("Invalid")) {
			if ((PolicyType.contentEquals("Individual"))||(PolicyType.contentEquals("Group")))
			{
			InValid.pass(""+PolicyNum+" is invalid").addScreenCaptureFromPath(screenshotPAth_pass);
		//	InValid.assignCategory("InValid");
			}
			else
			{
				InValid.pass(""+HealthNum+" is invalid").addScreenCaptureFromPath(screenshotPAth_pass);
		//		InValid.assignCategory("InValid");
			}}
	driver.navigate().refresh();	

	}
	 
	
	}
	@AfterSuite
	public void close() {
		extent.flush();
		System.out.println("AfterSuite");
		driver.manage().deleteAllCookies();

		driver.close();
	}

	
	public static String takeScreenshot(WebDriver driver, String Screenshotname) throws IOException {
		String datename=new SimpleDateFormat("yyyymmddhhmmss").format(new Date());
TakesScreenshot ts=(TakesScreenshot) driver;
File source=ts.getScreenshotAs(OutputType.FILE);
String dest=System.getProperty("user.dir")+"/Failedscreenshot/"+Screenshotname+datename+".png";
File finaldest=new File(dest);
FileUtils.copyFile(source, finaldest);
return dest;
	}
}
