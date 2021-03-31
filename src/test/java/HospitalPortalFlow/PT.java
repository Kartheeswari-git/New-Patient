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
import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import pageObjectModel.ForDashboardFields;
import pageObjectModel.NewPatientFields;


public class PT  {

	public WebDriver driver;
	public WebDriverWait wait;
	public JavascriptExecutor js;
	public Robot robot;
	String methodname;
	public ExtentReports extent;
	public ExtentHtmlReporter htmlReporter;
	public ExtentTest test;
	String[][] data = null;
	String monthString;

	@DataProvider(name = "datapass")
	public String[][] mdp() throws BiffException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		data = launch_Browser();
		return data;
	}

	public String[][] launch_Browser() throws InterruptedException, BiffException, IOException {

		// Read Excel data
		File file = new File("C:\\Users\\Ajith\\Documents\\HP9.xls");
		FileInputStream excel = new FileInputStream(file);
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(3);
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

	@BeforeSuite
	public void launch() {

		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64 (1)\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.get("https://hportal.bagicuat.bajajallianz.com/#/");
		wait = new WebDriverWait(driver, 9000);
		extent = new ExtentReports();


		// LOGIN
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));

		ForDashboardFields.uName(driver).sendKeys("apollohospital@hat.co.in");
		wait.until(ExpectedConditions.visibilityOf(ForDashboardFields.pWord(driver)));
		ForDashboardFields.pWord(driver).sendKeys("jan@2021");
		wait.until(ExpectedConditions.elementToBeClickable(ForDashboardFields.login(driver)));
		ForDashboardFields.login(driver).click();

	}

	@Test(dataProvider = "datapass", priority = 1)
	
		public void fill_data(String Policy_type, String policy_Num, String Corporate_Name, String Employee_code,
				String Health_Num, String DOA, String DOD, String IPD_Patient_Number, String Total_Estimated_Bill,
				String Provisional_Diagnosis, String Patient_Contact_Number, String Attendant_Contact_Number,
				String Treating_Doctor_Name, String Room_Type, String Room_Type_Description, String Patient_Email,
				String Path, String DOC_v2, String path2, String DOC_v3, String path3, String DOC_v4, String path4,
				String DOC_v5, String path5, String DOC_v6, String path6, String DOC_v7, String path7)
				throws InterruptedException, AWTException, IOException {
			
			wait = new WebDriverWait(driver, 9000);
			extent = new ExtentReports();
			test = extent.createTest("New Patient Creation");

			robot = new Robot();

			// New PAtient Creation
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.new_patient_tab(driver)));
			JavascriptExecutor jse = (JavascriptExecutor) driver;
			jse.executeScript("arguments[0].click()", NewPatientFields.new_patient_tab(driver));
			Thread.sleep(2000);

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
				NewPatientFields.search_value(driver).click();
			}
			// Fill date in Claim Details

		//	wait.until(ExpectedConditions.visibilityOf(NewPatientFields.DOA(driver)));
		//	NewPatientFields.DOA(driver).click();
			
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
	//		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[4]/div[2]/button[" + monthv +"]/span[2]"))));
		//	driver.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[4]/div[2]/button[" + monthv + "]/span[2]")).click();
			Thread.sleep(3000);

	//Click date
//			List<WebElement> active = driver.findElements(By
//					.xpath("//button[contains(@class,'datepicker-day flex align-center justify-content-center enable')]"));
//			System.out.println("Size" + active.size());
//			for (int i = 0; i < active.size(); i++) {
//				System.out.println("Active Elements are:" + active.get(i).getText());
//				if ((active.get(i).getText()).equals(datesss)) {
//					active.get(i).click();
//
//				}
//			}
//
//			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//					By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div")));
//
//			Actions action = new Actions(driver);
//			WebElement hourside = driver
//					.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div"));
//			action.moveToElement(hourside);
//			wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//					"//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div/button[" + hh + "]/span[2]")));
//			driver.findElement(By
//					.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[1]/div/button[" + hh + "]/span[2]"))
//					.click();
//			JavascriptExecutor js4_search = ((JavascriptExecutor) driver);
//			js4_search.executeScript("arguments[0].click()", NewPatientFields.DOA(driver));
//	//Click minutes
//			wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
//					By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div")));
//
//			WebElement minside = driver
//					.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div"));
//			action.moveToElement(minside);
//			WebElement minvalue = driver.findElement(By.xpath(
//					"//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[2]/div/button[" + mm2 + "]/span[2]"));
//			wait.until(ExpectedConditions.elementToBeClickable(minvalue));
//			minvalue.click();
//
//	//Click meridian
//			WebElement meridianside = driver
//					.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div"));
//			action.moveToElement(meridianside);
//			if (meridian.equalsIgnoreCase("AM")) {
//				wait.until(ExpectedConditions.elementToBeClickable(
//						By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div/button[1]/span[2]")));
//				WebElement AM=driver.findElement(
//						By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div/button[1]/span[2]"));
//AM.click();
//			} else if (meridian.equalsIgnoreCase("PM")) {
//				wait.until(ExpectedConditions.elementToBeClickable(By.xpath(
//						"//*[@id=\\\"undefined-wrapper\\\"]/div[2]/div/div[1]/div[2]/div[3]/div/button[2]/span[2]")));
//				WebElement PM=driver.findElement(
//						By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[1]/div[2]/div[3]/div/button[2]/span[2]"));
//PM.click();
//			}

	//end of date selection
//			wait.until(ExpectedConditions
//					.elementToBeClickable(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[2]/button")));
//			WebElement tick=driver.findElement(By.xpath("//*[@id=\"undefined-wrapper\"]/div[2]/div/div[2]/button"));
//			js4_search.executeScript("arguments[0].click()", tick);

	//DOD

//			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.DOD(driver)));
//			NewPatientFields.DOD(driver).click();
//			Thread.sleep(2000);
//			String[] dates1 = DOD.split("/");
//			String datess1 = dates1[0];
//			String month1 = dates1[1];
//			String year1 = dates1[2];
//
//			System.out.println("DOD_datesss " + datess1);
//			System.out.println("DOD_month " + month1);
//			System.out.println("DOD_year " + year1);
//
//			WebElement month_DOD = driver.findElement(By.xpath(
//					"/html/body/div/div/div[2]/div[1]/div/div/div/div/div/form/div/div[2]/div[2]/div/div/div[1]/div[1]/div/div[4]/div/div[2]/div/div/div/div/div[1]/div[2]/span[1]/button/span[2]"));
//			wait.until(ExpectedConditions.elementToBeClickable(month_DOD));
//			month_DOD.click();
//	// NewPatientFields.month(driver).click();
//			int monthv1 = Integer.parseInt(month1);
//			int datess1_dod = Integer.parseInt(datess1);
//			System.out.println(+datess1_dod);
//
//	//Click month
//			WebElement splitted_month = driver
//					.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[4]/div[2]/button["
//							+ monthv1 + "]/span[2]"));
//			wait.until(ExpectedConditions.elementToBeClickable(splitted_month));
//
//			splitted_month.click();
//
//	//Click date
//	//
//	////presence in DOM
//	//wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ID")));
//	//
//	////scrolling
//	//WebElement element = driver.findElement(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[3]/span/div/button["+datess1_dod+"]/span[2]"));  
//	//
//	////clickable
//	//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"undefined-picker-container-DatePicker\"]/div/div[3]/span/div/button["+datess1_dod+"]/span[2]")));
//			List<WebElement> active2 = driver.findElements(By
//					.xpath("//button[contains(@class,'datepicker-day flex align-center justify-content-center enable')]"));
//			System.out.println("Size" + active2.size());
//			for (int i = active2.size() - 1; i >= 0; i--) {
//				System.out.println("Active Elements are:" + active2.get(i).getText());
//				if ((active2.get(i).getText()).equals(datess1)) {
//					active2.get(i).click();
//				}
//			}

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

			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.Next(driver)));
			NewPatientFields.Next(driver).click();
			;

	//Document
			WebElement doc = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[1]"));
			Select docvalue = new Select(doc);
			docvalue.selectByVisibleText("Preauth Form");

			WebElement file1 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[1]"));
			wait.until(ExpectedConditions.visibilityOf(file1));

			file1.click();
	//	
//		Screen s=new Screen();
	//	
//		Pattern file_input=new Pattern("C:\\Users\\Ajith\\Documents\\For Doc upload\\filename.png");
//		Pattern open_button=new Pattern("C:\\Users\\Ajith\\Documents\\For Doc upload\\open.png");
//		s.wait(file_input,20);
//		s.click(file_input);
//		s.type(file_input,Path);
//		s.wait(open_button,20);
//		s.doubleClick(open_button);

	//Runtime.getRuntime().exec("C:\\Users\\Ajith\\Documents\\HP_fileuploadscript.exe");
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

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

			// Document2
			Thread.sleep(2000);
			WebElement doc2 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[2]"));
			Select docvalue2 = new Select(doc2);
			docvalue2.selectByVisibleText(DOC_v2);
			WebElement file2 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[2]"));
			wait.until(ExpectedConditions.visibilityOf(file2));
			file2.click();

			StringSelection strSelection2 = new StringSelection(path2);
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

			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

			// Doc3
			NewPatientFields.add_button(driver).click();
			Thread.sleep(2000);

			WebElement doc3 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[3]"));
			Select docvalue3 = new Select(doc3);
			docvalue3.selectByVisibleText(DOC_v3);
			WebElement file3 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[3]"));
			wait.until(ExpectedConditions.visibilityOf(file3));

			file3.click();

			StringSelection strSelection3 = new StringSelection(path3);
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
	//for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

	//Doc4
			NewPatientFields.add_button(driver).click();
			Thread.sleep(3000);
			WebElement doc4 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[4]"));
			Select docvalue4 = new Select(doc4);
			docvalue4.selectByVisibleText(DOC_v4);
			WebElement file4 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[4]"));
			wait.until(ExpectedConditions.visibilityOf(file4));

			file4.click();

			StringSelection strSelection4 = new StringSelection(path4);
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
			robot.keyRelease(KeyEvent.VK_ENTER);
			// for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

	//Doc 5			
			NewPatientFields.add_button(driver).click();
			Thread.sleep(2000);
			WebElement doc5 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[5]"));
			Select docvalue5 = new Select(doc5);
			docvalue5.selectByVisibleText(DOC_v5);
			WebElement file5 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[5]"));
			wait.until(ExpectedConditions.visibilityOf(file5));

			file5.click();

			StringSelection strSelection5 = new StringSelection(path5);
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

	//for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

			// Doc6
			NewPatientFields.add_button(driver).click();
			Thread.sleep(3000);

			WebElement doc6 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[6]"));
			JavascriptExecutor js4 = ((JavascriptExecutor) driver);
			js4.executeScript("arguments[0].scrollIntoView(true);", doc6);
			Select docvalue6 = new Select(doc6);
			docvalue6.selectByVisibleText(DOC_v6);
			WebElement file6 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[6]"));
			wait.until(ExpectedConditions.visibilityOf(file6));

			file6.click();

			StringSelection strSelection6 = new StringSelection(path6);
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

			Thread.sleep(5000);
	//for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

	//Doc7
			NewPatientFields.add_button(driver).click();
			Thread.sleep(2000);
			WebElement doc7 = driver.findElement(By.xpath("(//div/select[@id='documentType1'])[7]"));

			Select docvalue7 = new Select(doc7);
			docvalue7.selectByVisibleText(DOC_v7);
			WebElement file7 = driver
					.findElement(By.xpath("(//div/select[@id='documentType1']/following::div/div[@id='drop-area'])[7]"));
			wait.until(ExpectedConditions.visibilityOf(file7));

			file7.click();

			StringSelection strSelection7 = new StringSelection(path7);
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
			Thread.sleep(5000);
	//for upload
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(
					"(//div/select[@id='documentType1']/following::div/div[@id='drop-area']/following::div/div[@class='camera-container-1']/following::div/button[@class='btn btn-info custom-btn mb-1'])[1]")));
			NewPatientFields.upload(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();

			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.submit(driver)));
			NewPatientFields.submit(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			WebElement ref_no = driver.findElement(By.xpath("//div[contains(@class,'swal2-content')]"));
			System.out.println("" + ref_no.getText());
			// driver.findElement(By.xpath("//button[contains(text(),'OK')]")).click();
			driver.navigate().refresh();
			
			
		}


	

	@AfterSuite
	public void close() throws IOException {
		
		extent.flush();

		driver.manage().deleteAllCookies();

	//	driver.close();
		}
		
	
	
	

}
