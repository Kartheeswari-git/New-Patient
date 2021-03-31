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
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
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

public class Query extends Common{
	
	

	public WebDriver driver;
	public WebDriverWait wait; 
	public Robot robot, robot1;
	String[][] data = null;
	int i,q;
	public ExtentTest test, OpusQ, MaximusQ;
	public ExtentTest Fail_MaximusQ, Skip_OpusQ;
	public ExtentTest Skip_MaximusQ, Fail_OpusQ;
	public ExtentTest Pass_MaximusQ, Pass_OpusQ;
	String Claim_num,MaxOpusq;

	@DataProvider(name = "datapass")
	public String[][] mdp() throws BiffException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		data = launch_Browser();
		return data;
	}
	
	
	public String[][] launch_Browser() throws InterruptedException, BiffException, IOException {

		// Read Excel data
		File file = new File("C:\\Users\\Ajith\\Documents\\Q5.xls");
		FileInputStream excel = new FileInputStream(file);
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(2);
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

		htmlReporter = new ExtentHtmlReporter("./Report/Query.html");
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

//	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.SUBTRACT);
//	driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL,Keys.SUBTRACT);

	ForDashboardFields.uName(driver).sendKeys("apollohospital@hat.co.in");
	wait.until(ExpectedConditions.visibilityOf(ForDashboardFields.pWord(driver)));
	ForDashboardFields.pWord(driver).sendKeys("jan@2021");
	wait.until(ExpectedConditions.elementToBeClickable(ForDashboardFields.login(driver)));
	ForDashboardFields.login(driver).click();
	//driver.manage().window().setPosition(new Point(0,0));
	//driver.manage().window().setSize(new Dimension(1024,768));
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
	test = extent.createTest("<b>Query</b>");

	MaximusQ=test.createNode("<b>Maximus Query</b>");

	OpusQ=test.createNode("<b> Opus Query</b>");

}

@Test (dataProvider="datapass")
public void reply(String MaxOpusQ,String Claim_no,
		String Q_Com1, String Doc_img_1, String Doc_Path_1, String Action_1,
		String Q_Com2, String Doc_img_2, String Doc_Path_2, String Action_2,
		String Q_Com3, String Doc_img_3, String Doc_Path_3, String Action_3,
		String Q_Com4, String Doc_img_4, String Doc_Path_4, String Action_4,
		String Q_Com5, String Doc_img_5, String Doc_Path_5, String Action_5,
		String Q_Com6, String Doc_img_6, String Doc_Path_6, String Action_6,
		String Q_Com7, String Doc_img_7, String Doc_Path_7, String Action_7
		) throws InterruptedException, AWTException {
	robot = new Robot();
	Claim_num=Claim_no;
	MaxOpusq=MaxOpusQ;
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[contains(@placeholder,'Universal Search')]")));
	NewPatientFields.uni_search(driver).clear();
	NewPatientFields.uni_search(driver).sendKeys(Claim_no);
	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.uni_search_icon(driver)));


	NewPatientFields.uni_search_icon(driver).click();
	Thread.sleep(2000);

	WebElement first=driver.findElement(By.xpath("//*[contains(text(),'"+Claim_no+"')]"));
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(text(),'"+Claim_no+"')]")));

	first.click();
	Thread.sleep(2000);

	wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.querylink(driver)));
	NewPatientFields.querylink(driver).click();	

List<WebElement> num_queries=driver.findElements(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr"));
int row_no=num_queries.size();
System.out.println("queries"+num_queries.size());
if (row_no == 1) {
	WebElement Query_text=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr/td[2]/form/span/span/textarea"));
	wait.until(ExpectedConditions.visibilityOf(Query_text));
	Query_text.sendKeys(Q_Com1);

	switch (Doc_img_1) {
	case "Document":{
		WebElement Query_doc=driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(Query_doc));
		Query_doc.click();
		StringSelection strSelection = new StringSelection(Doc_Path_1);
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
		
		} break;
	case "Image":{
		
		WebElement img=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr/td[3]/form/span/span/div/div/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(img));
		img.click();
		Thread.sleep(3000);
	
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
	
		wait.until(ExpectedConditions.visibilityOf(NewPatientFields.ok_img(driver)));
		NewPatientFields.ok_img(driver).click();
	}}
	WebElement upload=driver.findElement(By.xpath("//button[contains(text(),'Upload')]"));
	wait.until(ExpectedConditions.elementToBeClickable(upload));
	JavascriptExecutor jch = ((JavascriptExecutor) driver);

	jch.executeScript("arguments[0].click()", upload);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
	
	if(!Action_1.equals("Yes")) {
	WebElement doc_not_needed=driver.findElement(By.xpath("//*[@id=\"queryAction0\"]"));
	doc_not_needed.click(); 
	}
}

if (row_no > 1) {
	WebElement Query_text=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[2]/form/span/span/textarea"));
	wait.until(ExpectedConditions.visibilityOf(Query_text));
	Query_text.sendKeys(Q_Com1);

	switch (Doc_img_1) {
	case "Document":{
		WebElement Query_doc1=driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[2]"));
		wait.until(ExpectedConditions.elementToBeClickable(Query_doc1));
		Query_doc1.click();
		StringSelection strSelection = new StringSelection(Doc_Path_1);
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
		Thread.sleep(2000);

		

		} break;
	case "Image":{
		WebElement img1=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[3]/form/span/span/div/div/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(img1));
		img1.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
	}}
	WebElement upload1=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[1]/td[4]/form/span/span/div/button"));
	wait.until(ExpectedConditions.elementToBeClickable(upload1));
	JavascriptExecutor jch = ((JavascriptExecutor) driver);

	jch.executeScript("arguments[0].click()", upload1);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
		
	
	if(!Action_1.equals("Yes")) {
	WebElement doc_not_needed=driver.findElement(By.xpath("//*[@id=\"queryAction0\"]"));
	doc_not_needed.click(); 
	
}}
if (row_no > 2) {
	WebElement Query_text2=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[2]/td[2]/form/span/span/textarea"));
	wait.until(ExpectedConditions.visibilityOf(Query_text2));
	Query_text2.sendKeys(Q_Com2);

	switch (Doc_img_2) {
	case "Document":{
		WebElement Query_doc2=driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[3]"));
		wait.until(ExpectedConditions.elementToBeClickable(Query_doc2));
		Query_doc2.click();
		StringSelection strSelection = new StringSelection(Doc_Path_2);
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
Thread.sleep(2000);
	}
		
		break;
	case "Image":{
		WebElement img2=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[2]/td[3]/form/span/span/div/div/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(img2));
		img2.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
	} }
	WebElement upload2=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[2]/td[4]/form/span/span/div/button"));
	wait.until(ExpectedConditions.elementToBeClickable(upload2));
	JavascriptExecutor jch = ((JavascriptExecutor) driver);

	jch.executeScript("arguments[0].click()", upload2);
	wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
	NewPatientFields.ok(driver).click();
		
	
	if(!Action_2.equals("Yes")) {
	WebElement doc_not_needed=driver.findElement(By.xpath("//*[@id=\"queryAction1\"]"));
	doc_not_needed.click(); 
	
}}
if (row_no > 3) {
	WebElement Query_text3=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[3]/td[2]/form/span/span/textarea"));
	wait.until(ExpectedConditions.visibilityOf(Query_text3));
	Query_text3.sendKeys(Q_Com2);

	switch (Doc_img_3) {
	case "Document":{
		WebElement Query_doc3=driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[4]"));
		wait.until(ExpectedConditions.elementToBeClickable(Query_doc3));
		Query_doc3.click();
		StringSelection strSelection = new StringSelection(Doc_Path_3);
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
Thread.sleep(2000);
	}

		break;
	case "Image":{
		
		WebElement img3=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[3]/td[3]/form/span/span/div/div/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(img3));
		img3.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
	}}
	WebElement upload3=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[3]/td[4]/form/span/span/div/button"));
	wait.until(ExpectedConditions.elementToBeClickable(upload3));
	JavascriptExecutor jch = ((JavascriptExecutor) driver);

	jch.executeScript("arguments[0].click()", upload3);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
		
	if(!Action_3.equals("Yes")) {
	WebElement doc_not_needed=driver.findElement(By.xpath("//*[@id=\"queryAction2\"]"));
	doc_not_needed.click(); 
	}}

if (row_no > 4) {
	WebElement Query_text4=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[4]/td[2]/form/span/span/textarea"));
	wait.until(ExpectedConditions.visibilityOf(Query_text4));
	Query_text4.sendKeys(Q_Com4);

	switch (Doc_img_4) {
	case "Document":{
		WebElement Query_doc4=driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[5]"));
		wait.until(ExpectedConditions.elementToBeClickable(Query_doc4));
		Query_doc4.click();
		StringSelection strSelection = new StringSelection(Doc_Path_4);
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
Thread.sleep(2000);

		}break;
	case "Image":{
	
		
		WebElement img4=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[4]/td[3]/form/span/span/div/div/div[2]"));
		wait.until(ExpectedConditions.visibilityOf(img4));
		img4.click();
		Thread.sleep(3000);
		wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
		NewPatientFields.img_capture(driver).click();
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
		NewPatientFields.ok(driver).click();
		
		}}
	WebElement upload4=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[4]/td[4]/form/span/span/div/button"));
	wait.until(ExpectedConditions.elementToBeClickable(upload4));
	JavascriptExecutor jch = ((JavascriptExecutor) driver);

	jch.executeScript("arguments[0].click()", upload4);
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
	
	if(!Action_4.equals("Yes")) {
	WebElement doc_not_needed=driver.findElement(By.xpath("//*[@id=\"queryAction3\"]"));
	doc_not_needed.click(); 
	}}
	if (row_no > 5) {
		WebElement Query_text5=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[5]/td[2]/form/span/span/textarea"));
		wait.until(ExpectedConditions.visibilityOf(Query_text5));
		Query_text5.sendKeys(Q_Com5);

		switch (Doc_img_5) {
		case "Document":{
			WebElement Query_doc5=driver.findElement(By.xpath("(//*[@id=\"drop-area\"])[6]"));
			wait.until(ExpectedConditions.elementToBeClickable(Query_doc5));
			Query_doc5.click();
			StringSelection strSelection = new StringSelection(Doc_Path_5);
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
	Thread.sleep(2000);
	
			}break;
		case "Image":{
		
			
			WebElement img5=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[5]/td[3]/form/span/span/div/div/div[2]"));
			wait.until(ExpectedConditions.visibilityOf(img5));
			img5.click();
			Thread.sleep(3000);
			wait.until(ExpectedConditions.elementToBeClickable(NewPatientFields.img_capture(driver)));
			NewPatientFields.img_capture(driver).click();
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
			NewPatientFields.ok(driver).click();
			}}
		WebElement upload5=driver.findElement(By.xpath("//*[@id=\"query\"]/div[1]/div/div/div[2]/table/tbody/tr[5]/td[4]/form/span/span/div/button"));
		wait.until(ExpectedConditions.elementToBeClickable(upload5));
		JavascriptExecutor jch = ((JavascriptExecutor) driver);

		jch.executeScript("arguments[0].click()", upload5);
				wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[contains(text(),'OK')]")));
				NewPatientFields.ok(driver).click();
		
		if(!Action_5.equals("Yes")) {
		WebElement doc_not_needed=driver.findElement(By.xpath("//*[@id=\"queryAction3\"]"));
		doc_not_needed.click(); 
		}}
wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//button[contains(text(),'Submit')])[1]")));
driver.findElement(By.xpath("(//button[contains(text(),'Submit')])[1]"));
}

@AfterMethod
public void screen(ITestResult result) throws IOException {
if(result.getStatus()==ITestResult.FAILURE) {
//	test.log(Status.FAIL,"Test is Failed"+result.getName());
//	test.log(Status.FAIL,"Test is Failed"+result.getThrowable());
	String screenshotPAth=takeScreenshot(driver, result.getName());
//	test.log(Status.FAIL, "Test is Failed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
	if((MaxOpusq.contentEquals("Maximus"))) {
		
		MaximusQ.fail(""+Claim_num+" is Failed").addScreenCaptureFromPath(screenshotPAth);
	//	Fail_Maximus.assignCategory("Individual","Maximus");
		
	}
	if((MaxOpusq.contentEquals("Opus"))) {
			
			OpusQ.fail(""+Claim_num+" is Failed").addScreenCaptureFromPath(screenshotPAth);
		//	Fail_Opus.assignCategory("Individual","Opus");
			
		
			}
	
	driver.navigate().refresh();	

}

if(result.getStatus()==ITestResult.SUCCESS) {
//	test.log(Status.PASS,"Test is Passed"+result.getName());
//	test.log(Status.PASS,"Test is Passed"+result.getThrowable());
	String screenshotPAth_pass=takeScreenshot(driver, result.getName());
//	test.log(Status.PASS, "Test is Passed", MediaEntityBuilder.createScreenCaptureFromPath(screenshotPAth).build());
	if((MaxOpusq.contentEquals("Maximus"))) {

		MaximusQ.pass(""+Claim_num+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
}
	if((MaxOpusq.contentEquals("Opus"))) {
			
			OpusQ.pass(""+Claim_num+"is Passed").addScreenCaptureFromPath(screenshotPAth_pass);
		}
		
	
		
driver.navigate().refresh();
}}
@AfterSuite
public void close() {
	
//	extent.flush();
	System.out.println("AfterSuite");
	driver.manage().deleteAllCookies();

	driver.close();
}




//public static FirefoxOptions setFireFoxOptions() {
//    FirefoxOptions options = new FirefoxOptions();
//    options.addPreference("media.navigator.permission.disabled", true);
//    return options;
//}



}
