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
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
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

public class Cashless_Approved extends Common{

	public WebDriver driver;
	public WebDriverWait wait; 
	public Robot robot, robot1;
	String[][] data = null;
	int i,e1,e2;
	String[] inputArray2, inputArrayd2;
	String[] inputArray, inputArrayd1;
	public ExtentTest Interim, Final, OpusC, MaximusC, MaximusF, OpusF;
	public ExtentTest Fail_MaximusC, Skip_OpusC, Fail_MaximusF, Skip_OpusF;
	public ExtentTest Skip_MaximusC, Fail_OpusC, Skip_MaximusF, Fail_OpusF;
	public ExtentTest Pass_MaximusC, Pass_OpusC, Pass_MaximusF, Pass_OpusF;
	String Claim_num,MaxOpusC, Interim_Final1;
	@DataProvider(name = "datapass")
	public String[][] mdp() throws BiffException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		data = launch_Browser();
		return data;
	}
	
	
	public String[][] launch_Browser() throws InterruptedException, BiffException, IOException {

		// Read Excel data
		File file = new File("C:\\Users\\Ajith\\Documents\\Interim3.xls");
		FileInputStream excel = new FileInputStream(file);
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(1);
		int rowcount = sheet.getRows();
		int colcount = sheet.getColumns();
		System.out.println(rowcount);
		System.out.println(colcount);
		String testdata[][] = new String[rowcount - 1][colcount];
		for ( i = 1; i < rowcount; i++)
			for (int j = 0; j < colcount; j++) {
				testdata[i - 1][j] = sheet.getCell(j, i).getContents();

				System.out.println(testdata[i - 1][j]);

			}
		return testdata;
	}
	
	
	@BeforeSuite
	public void extentrep() {
		//extent = new ExtentReports();

		htmlReporter = new ExtentHtmlReporter("./Report/Cashless Approved.html");
		htmlReporter.config().setEncoding("utf-8");
		htmlReporter.config().setDocumentTitle("Hospital Portal");
		htmlReporter.config().setReportName("Cashless Approved");
		htmlReporter.config().setTheme(Theme.DARK);
		extent = new ExtentReports();

		extent.attachReporter(htmlReporter);

		extent.setSystemInfo("Organization", "FA Softwares");
		extent.setSystemInfo("Browser", "FireFox");
		extent.setSystemInfo("QA", "Kartheeswari");
		
		Interim = extent.createTest("<b>Interim Bill</b>");
		Final = extent.createTest("<b>Final Bill</b>");

//		MaximusC=test1.createNode("<b>Maximus </b>");
//		MaximusF=test2.createNode("<b>Maximus </b>");
//
//		OpusC=test1.createNode("<b> Opus </b>");
//		OpusF=test2.createNode("<b> Opus </b>");

	
		}
	
@BeforeTest
public void launch() throws InterruptedException, AWTException {
	System.setProperty("webdriver.gecko.driver",
			"C:\\Users\\Ajith\\Downloads\\geckodriver-v0.29.0-win64 (1)\\geckodriver.exe");
	FirefoxOptions options = new FirefoxOptions();
	   options.addPreference("media.navigator.permission.disabled", true);

	driver = new FirefoxDriver(options);
	driver.get("https://hportal.bagicuat.bajajallianz.com/#/");
	wait = new WebDriverWait(driver, Duration.ofSeconds(120, 1));


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
Thread.sleep(2000);

}

@Test (dataProvider="datapass")
public void reply(String MaxOpus, String Claim_no, String Interim_Final, String Enhancement_Amount, String Doc_img_1, String Interim_doc, String Doc_img_2, String Inv_doc, String Doc_img_3, String Summary_doc, String Doc_img_4, String Interim2_doc, String Total_Bill_Amount,
		String Selection_Doctyp1, String Doc_Doctyp1, String Doc_Doctyp2, String Selection_Doctyp3, String Doc_Doctyp3) throws InterruptedException, AWTException {
	robot = new Robot();
	MaxOpusC=MaxOpus;
	Claim_num=Claim_no;
	Interim_Final1=Interim_Final;
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@placeholder,'Universal Search')]")));
	NewPatientFields.uni_search(driver).clear();
	NewPatientFields.uni_search(driver).sendKeys(Claim_no);
Thread.sleep(2000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.uni_search_icon(driver)));


	NewPatientFields.uni_search_icon(driver).click();

	Thread.sleep(2000);
	WebElement first=driver.findElement(By.xpath("//*[contains(text(),'"+Claim_no+"')]"));
	wait.until(ExpectedConditions.elementToBeClickable(first));

	first.click();
	 
	if(Interim_Final.contentEquals("Interim")) {
	wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Query & Interim Bil")));
	NewPatientFields.querylink(driver).click();	
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim(driver)));
	JavascriptExecutor js_search = ((JavascriptExecutor) driver);
	js_search.executeScript("arguments[0].scrollIntoView(true);",NewPatientFields.Interim(driver));
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim(driver)));

	NewPatientFields.Interim(driver).click();	
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@name,'enhancementAmount')]")));
	NewPatientFields.Enhancement_amount(driver).sendKeys(Enhancement_Amount);
	switch (Doc_img_1) {
	case "Document":{

	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_doc1(driver)));
	NewPatientFields.Interim_doc1(driver).click();
	StringSelection strSelection = new StringSelection(Interim_doc);
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
	robot.keyRelease(KeyEvent.VK_ENTER);	} break;
case "image":{
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_img1(driver)));
	NewPatientFields.Interim_img1(driver).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
		}
	}
	Thread.sleep(2000);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Upload')])[2]")));
	NewPatientFields.Interim_upload1(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
//-------------------------------------------------------------------------------------
	Select doct1=new Select(NewPatientFields.doc_typ1(driver));
	doct1.selectByVisibleText("Investigation reports");
	
	
	switch (Doc_img_2) {
	case "Document":{

	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_doc2(driver)));
	NewPatientFields.Interim_doc2(driver).click();
	StringSelection strSelection = new StringSelection(Inv_doc);
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
	robot.keyRelease(KeyEvent.VK_ENTER);	}break;
case "image":{
	
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_img2(driver)));
	NewPatientFields.Interim_img2(driver).click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
		}
	}
	Thread.sleep(2000);

	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Upload')])[4]")));
	NewPatientFields.Interim_upload2(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
	WebElement plus=driver.findElement(By.xpath("//*[@id=\"query\"]/div[3]/form/div/div/div[2]/div[2]/div/div/div[4]/button"));
	wait.until(ExpectedConditions.elementToBeClickable(plus));

	plus.click();
	
	//-------------------------------------------------------------------------------------
	Select doct2=new Select(NewPatientFields.doc_typ2(driver));
	doct2.selectByVisibleText("Case Summary");
	JavascriptExecutor js = ((JavascriptExecutor) driver);
	js.executeScript("arguments[0].scrollIntoView(true);", doct2);
	
	switch (Doc_img_3) {
		case "Document":{

		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_doc3(driver)));
		NewPatientFields.Interim_doc3(driver).click();
		StringSelection strSelection = new StringSelection(Summary_doc);
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
		}break;
	case "image":{
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_img3(driver)));
		NewPatientFields.Interim_img3(driver).click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
			NewPatientFields.img_capture(driver).click();			
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();		
	}
		}
	Thread.sleep(2000);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[contains(text(),'Upload')])[6]")));
		NewPatientFields.Interim_upload3(driver).click();
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
		WebElement plus1=driver.findElement(By.xpath("(//*[@id=\"query\"]/div[3]/form/div/div/div[2]/div[2]/div/div/div[4]/button)[1]"));
	
		wait.until(ExpectedConditions.elementToBeClickable(plus1));

		plus1.click();
		
//-------------------------------------------------------------------------------------
		Select doct3=new Select(NewPatientFields.doc_typ3(driver));
		doct3.selectByVisibleText("Interim bill 2");
		
		
		switch (Doc_img_4) {
			case "Document":{

			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_doc4(driver)));
			NewPatientFields.Interim_doc4(driver).click();
			StringSelection strSelection = new StringSelection(Interim2_doc);
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
			}break;
		case "image":{
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_img4(driver)));
			NewPatientFields.Interim_img4(driver).click();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
				NewPatientFields.img_capture(driver).click();			
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
				NewPatientFields.ok(driver).click();		
		}
			}
		Thread.sleep(3000);

			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.Interim_upload4(driver)));
			NewPatientFields.Interim_upload4(driver).click();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();	
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.submit(driver)));
			NewPatientFields.submit(driver).click();
	
	}
//===================================++++++++++++++++++Next piece of code
	if(Interim_Final.contentEquals("Final")) {
		Thread.sleep(2000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.final_bill(driver)));
		NewPatientFields.final_bill(driver).click();
		
			String DD1 = "";
			String DD2 = "";
			String DD3 = "";
			String DD4 = "";
			try {
			String input = Selection_Doctyp1;
			System.out.println("values"+e1);
			if (input !=null && input !="") {
				String[] inputArray = input.split(",");
				e1=inputArray.length;

				if (inputArray != null && inputArray.length > 0) {
					for (int i = 0; i < inputArray.length; i++) {
						switch (i) {
						case 0:
							DD1 = inputArray[i];
							break;
						case 1:
							DD2 = inputArray[i];
							break;
						case 2:
							DD3 = inputArray[i];
							break;
						case 3:
							DD4 = inputArray[i];
							break;
						}
					}
				}
			}

		

		} catch (Exception e) {
			e.printStackTrace();
		}
		
			String DD1_Doc1 = "";
			String DD1_Doc2 = "";
			String DD1_Doc3 = "";
			String DD1_Doc4 = "";
			try {
			String input = Doc_Doctyp1;

			if (input !=null && input !="") {
				inputArrayd1 = input.split(",");
				
				if (inputArrayd1 != null && inputArrayd1.length > 0) {
					for (int i = 0; i < inputArrayd1.length; i++) {
						switch (i) {
						case 0:
							DD1_Doc1 = inputArrayd1[i];
							break;
						case 1:
							DD1_Doc2 = inputArrayd1[i];
							break;
						case 2:
							DD1_Doc3 = inputArrayd1[i];
							break;
						case 3:
							DD1_Doc4 = inputArrayd1[i];
							break;
						}
					}
				}
			}

		

		} catch (Exception e) {
			
			e.printStackTrace();
		}
			
			
//-----------------------------
		Thread.sleep(2000);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.total_bill(driver)));
		NewPatientFields.total_bill(driver).sendKeys(Total_Bill_Amount);
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc_DD1(driver)));
		Select first_dd=new Select(NewPatientFields.first_doc_DD1(driver));
		first_dd.selectByVisibleText(DD1);
//[For Type1]		
		if (!DD1.isEmpty() && !DD1_Doc1.contentEquals("image")) {
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc1(driver)));
		NewPatientFields.first_doc1(driver).click();
		StringSelection strSelection = new StringSelection(DD1_Doc1);
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
		robot.keyRelease(KeyEvent.VK_ENTER);	}	
		if ((!DD1.isEmpty()) && DD1_Doc1.contentEquals("image") ) {
			WebElement img1=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image00'])[1]"));
			wait.until(ExpectedConditions.visibilityOf(img1));
			img1.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
			NewPatientFields.img_capture(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
				}
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
		NewPatientFields.first_doc1_upload(driver).click();
	    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
		//set condition
		if (e1>1) {
		WebElement plus1=driver.findElement(By.xpath("//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));

		plus1.click(); 
		

//----------------------------
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc_DD2(driver)));
		Select first_dd2=new Select(NewPatientFields.first_doc_DD2(driver));
		first_dd2.selectByVisibleText(DD2);

		if (!DD2.isEmpty() && !DD1_Doc2.contentEquals("image")) {
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc2(driver)));
			NewPatientFields.first_doc2(driver).click();
			StringSelection strSelection = new StringSelection(DD1_Doc2);
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
			if ((!DD2.isEmpty()) && DD1_Doc2.contentEquals("image")) {
				WebElement img2=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image10'])[1]"));
				wait.until(ExpectedConditions.visibilityOf(img2));
				img2.click();
				Thread.sleep(3000);
				wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
				NewPatientFields.img_capture(driver).click();
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
				NewPatientFields.ok(driver).click();
						}
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType110']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
			NewPatientFields.first_doc2_upload(driver).click();
		    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
		}
			if (e1>2) {
				WebElement plus2=driver.findElement(By.xpath("//div/select[@name='documentType110']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType110']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
				plus2.click();
			
			//----------------------------
			wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc_DD3(driver)));
			Select first_dd3=new Select(NewPatientFields.first_doc_DD3(driver));
			first_dd3.selectByVisibleText(DD3);

			if (!DD3.isEmpty() && !DD1_Doc3.contentEquals("image")) {
				wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc3(driver)));
				NewPatientFields.first_doc3(driver).click();
				StringSelection strSelection = new StringSelection(DD1_Doc3);
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
				if ((!DD3.isEmpty()) && DD1_Doc3.contentEquals("image")) {
					WebElement img3=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image20'])[1]"));
					wait.until(ExpectedConditions.visibilityOf(img3));
					img3.click();
					Thread.sleep(3000);
					wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
					NewPatientFields.img_capture(driver).click();
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
								}
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType120']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
				NewPatientFields.first_doc3_upload(driver).click();
			    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
				NewPatientFields.ok(driver).click();
			}
				if (e1>3) {
					WebElement plus3=driver.findElement(By.xpath("//div/select[@name='documentType120']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType120']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
					plus3.click();
				
				//----------------------------
				wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc_DD4(driver)));
				Select first_dd4=new Select(NewPatientFields.first_doc_DD4(driver));
				first_dd4.selectByVisibleText(DD4);

				if (!DD4.isEmpty() && !DD1_Doc4.contentEquals("image")) {
					wait.until(ExpectedConditions.visibilityOf(NewPatientFields.first_doc4(driver)));
					NewPatientFields.first_doc4(driver).click();
					StringSelection strSelection = new StringSelection(DD1_Doc4);
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
					if ((!DD4.isEmpty()) && DD1_Doc4.contentEquals("image")) {
						WebElement img4=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image30'])[1]"));
						wait.until(ExpectedConditions.visibilityOf(img4));
						img4.click();
						Thread.sleep(3000);
						wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
						NewPatientFields.img_capture(driver).click();
						wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
						NewPatientFields.ok(driver).click();
										}
					wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType130']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
					NewPatientFields.first_doc4_upload(driver).click();
				    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
					NewPatientFields.ok(driver).click();
					
					}
//[For Type2 - Single unit]
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.sec_doc1(driver)));
		NewPatientFields.sec_doc1(driver).click();
		if(!Doc_Doctyp2.isEmpty()&& !Doc_Doctyp2.contentEquals("image")){

		StringSelection strSelection = new StringSelection(Doc_Doctyp2);
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
		robot.keyRelease(KeyEvent.VK_ENTER);}
		if((!Doc_Doctyp2.isEmpty()) && Doc_Doctyp2.contentEquals("image")){
			WebElement imgS=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image01'])[1]"));
			wait.until(ExpectedConditions.visibilityOf(imgS));
			imgS.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
			NewPatientFields.img_capture(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
		}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType101']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.sec_doc1_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();

//-------[For Type 3]
//3rd doc type			
String DD21 = "";
String DD22 = "";
String DD23 = "";
String DD24 = "";

String DD25 = "";
String DD26 = "";
String DD27 = "";
String DD28 = "";
try {
String input = Selection_Doctyp3;
if (input !=null && input !="") {
	String[] inputArrayd2 = input.split(",");
	e2=inputArrayd2.length;

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
			case 6:
				DD27 = inputArrayd2[i];
				break;
			case 7:
				DD28 = inputArrayd2[i];
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
System.out.println("three " + DD27);
System.out.println("four " + DD28);

} catch (Exception e) {
e.printStackTrace();
}

String DD2_Doc1 = "";
String DD2_Doc2 = "";
String DD2_Doc3 = "";
String DD2_Doc4 = "";

String DD2_Doc5 = "";
String DD2_Doc6 = "";
String DD2_Doc7 = "";
String DD2_Doc8 = "";
try {
String input = Doc_Doctyp3;

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
			case 6:
				DD2_Doc7 = inputArray2[i];
				break;
			case 7:
				DD2_Doc8 = inputArray2[i];
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
System.out.println("three " + DD2_Doc7);
System.out.println("four " + DD2_Doc8);

} catch (Exception e) {

e.printStackTrace();
}

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD1(driver)));
Select sec_dd1=new Select(NewPatientFields.D3_doc_DD1(driver));
sec_dd1.selectByVisibleText(DD21);
JavascriptExecutor js4 = ((JavascriptExecutor) driver);
js4.executeScript("arguments[0].scrollIntoView(true);", sec_dd1);
//[For Type3]		
if ((!DD21.isEmpty()) && !DD2_Doc1.contentEquals("image")) {
wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc1(driver)));
NewPatientFields.D3_doc1(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc1);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD21.isEmpty()) && DD2_Doc1.contentEquals("image")) {
	WebElement img21=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image02'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img21));
	img21.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType102']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc1_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();

if (e2>1) {
	WebElement plusd1=driver.findElement(By.xpath("//div/select[@name='documentType102']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType102']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd1.click();


wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD2(driver)));
Select sec_dd2=new Select(NewPatientFields.D3_doc_DD2(driver));
sec_dd2.selectByVisibleText(DD22);
if ((!DD22.isEmpty()) && !DD2_Doc2.contentEquals("image")) {
wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc2(driver)));
NewPatientFields.D3_doc2(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc2);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
robot.keyRelease(KeyEvent.VK_ENTER); }
if ((!DD22.isEmpty()) && DD2_Doc2.contentEquals("image") ) {
	WebElement img22=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image12'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img22));
	img22.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType112']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc2_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
if (e2>2) {
	WebElement plusd2=driver.findElement(By.xpath("//div/select[@name='documentType112']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType112']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd2.click();


wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD3(driver)));
Select sec_dd3=new Select(NewPatientFields.D3_doc_DD3(driver));
sec_dd3.selectByVisibleText(DD23);
if ((!DD23.isEmpty()) && !DD2_Doc3.contentEquals("image")) {
wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc3(driver)));
NewPatientFields.D3_doc3(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc3);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD23.isEmpty()) && DD2_Doc3.contentEquals("image")) {
	WebElement img23=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image22'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img23));
	img23.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType122']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc3_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
if (e2>3) {
	WebElement plusd3=driver.findElement(By.xpath("//div/select[@name='documentType122']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType122']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd3.click();

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD4(driver)));
Select sec_dd4=new Select(NewPatientFields.D3_doc_DD4(driver));
sec_dd4.selectByVisibleText(DD24);
//[For Type3]		
if ((!DD24.isEmpty()) && !DD2_Doc4.contentEquals("image")) {

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc4(driver)));
NewPatientFields.D3_doc4(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc4);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD24.isEmpty()) && DD2_Doc4.contentEquals("image")) {
	WebElement img24=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image32'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img24));
	img24.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType132']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc4_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
if (e2>4) {
	WebElement plusd4=driver.findElement(By.xpath("//div/select[@name='documentType132']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType132']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd4.click();

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD5(driver)));
Select sec_dd5=new Select(NewPatientFields.D3_doc_DD5(driver));
sec_dd5.selectByVisibleText(DD25);
if ((!DD25.isEmpty()) && !DD2_Doc5.contentEquals("image")) {

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc5(driver)));
NewPatientFields.D3_doc5(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc5);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD25.isEmpty()) && DD2_Doc5.contentEquals("image")) {
	WebElement img25=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image42'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img25));
	img25.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType142']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc5_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
if (e2>5) {
	WebElement plusd5=driver.findElement(By.xpath("//div/select[@name='documentType142']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType142']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd5.click();


wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD6(driver)));
Select sec_dd6=new Select(NewPatientFields.D3_doc_DD6(driver));
sec_dd6.selectByVisibleText(DD26);
if ((!DD26.isEmpty()) && !DD2_Doc6.contentEquals("image")) {

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc6(driver)));
NewPatientFields.D3_doc6(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc6);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD26.isEmpty()) && DD2_Doc6.contentEquals("image")) {
	WebElement img26=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image52'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img26));
	img26.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType152']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc6_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
if (e2>6) {
	WebElement plusd6=driver.findElement(By.xpath("//div/select[@name='documentType152']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType152']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd6.click();

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD7(driver)));
Select sec_dd7=new Select(NewPatientFields.D3_doc_DD7(driver));
sec_dd7.selectByVisibleText(DD27);
if ((!DD27.isEmpty()) && !DD2_Doc7.contentEquals("image")) {

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc7(driver)));
NewPatientFields.D3_doc7(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc7);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD27.isEmpty()) && DD2_Doc7.contentEquals("image")) {
	WebElement img27=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image62'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img27));
	img27.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType162']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc7_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
if (e2>7) {
	WebElement plusd7=driver.findElement(By.xpath("//div/select[@name='documentType162']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/select[@name='documentType162']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-sm btn-primary']")));
	plusd7.click();
wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc_DD8(driver)));
Select sec_dd8=new Select(NewPatientFields.D3_doc_DD8(driver));
sec_dd8.selectByVisibleText(DD28);
if ((!DD28.isEmpty()) && !DD2_Doc8.contentEquals("image")) {

wait.until(ExpectedConditions.visibilityOf(NewPatientFields.D3_doc8(driver)));
NewPatientFields.D3_doc8(driver).click();
StringSelection strSelectiond31 = new StringSelection(DD2_Doc8);
Clipboard clipboardd31 = Toolkit.getDefaultToolkit().getSystemClipboard();
clipboardd31.setContents(strSelectiond31, null);
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
if ((!DD28.isEmpty()) && DD2_Doc8.contentEquals("image")) {
	WebElement img28=driver.findElement(By.xpath("(//div/select[@name='documentType100']/following::div/div[@id='drop-area']/following::div/div[@name='image72'])[1]"));
	wait.until(ExpectedConditions.visibilityOf(img28));
	img28.click();
	Thread.sleep(3000);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
	NewPatientFields.img_capture(driver).click();
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div/select[@name='documentType172']/following::div/div[@id='drop-area']/following::div/button[@class='btn btn-info custom-btn upload-button mb-1'])[1]")));
NewPatientFields.D3_doc8_upload(driver).click();
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
NewPatientFields.ok(driver).click();
}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[contains(text(),'Submit')])[2]")));
driver.findElement(By.xpath("(//button[contains(text(),'Submit')])[2]")).click();
}

		}
@AfterMethod
public void screen(ITestResult result) throws IOException {
if(result.getStatus()==ITestResult.FAILURE) {
//	test.log(Status.FAIL,"Test is Failed"+result.getName());
//	test.log(Status.FAIL,"Test is Failed"+result.getThrowable());
	String screenshotPAth=takeScreenshot(driver, result.getName());
//	test.log(Status.FAIL, "Test is Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
	if(Interim_Final1.contentEquals("Interim")) {
			Interim.fail(""+Claim_num+" is Failed").addScreenCaptureFromPath(screenshotPAth);
		//	Fail_Opus.assignCategory("Individual","Opus");
			}	
	if(Interim_Final1.contentEquals("Final")) {
			Final.fail(""+Claim_num+" is Failed").addScreenCaptureFromPath(screenshotPAth);
			//	Fail_Opus.assignCategory("Individual","Opus");

		}
	driver.navigate().refresh();	

}

if(result.getStatus()==ITestResult.SUCCESS) {
//	test.log(Status.PASS,"Test is Passed"+result.getName());
//	test.log(Status.PASS,"Test is Passed"+result.getThrowable());
	String screenshotPAth_pass=takeScreenshot(driver, result.getName());
//	test.log(Status.PASS, "Test is Passed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
		if(Interim_Final1.contentEquals("Interim")) {
		Interim.pass(""+Claim_num+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);			
		}
		if(Interim_Final1.contentEquals("Final")) {

			Final.pass(""+Claim_num+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
			
		}
driver.navigate().refresh();
}
}
@AfterSuite
public void close() {
	
//	extent.flush();
	System.out.println("AfterSuite");
	driver.manage().deleteAllCookies();

	driver.close();
}


	
}

