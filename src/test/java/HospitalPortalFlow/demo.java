package HospitalPortalFlow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableCell;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class demo {

	public static WebDriver driver;
	public static WebDriverWait wait;
	String caseid="INV-CASHLESS-21000081";
	JavascriptExecutor js = (JavascriptExecutor) driver;
	public static ExtentReports extent;
	ExtentHtmlReporter spark;
	ExtentTest test;
	String[][] data=null;
	Label label;
	static int j;
	static int i;
	static List<WebElement> tp;
	String touchpoints;
	static String concat;
	
	
	@DataProvider(name = "datapass")
	public String[][] mdp() throws BiffException, IOException, InterruptedException {
		// TODO Auto-generated method stub
		data = launch_Browser();
		return data;
	}

	public String[][] launch_Browser() throws InterruptedException, BiffException, IOException {

//Alert Accept	SIT
//	 Alert alert=driver.switchTo().alert(); 
//	 alert.accept();
//	 System.out.println("accepted"); 
//	 Thread.sleep(8000);

		// Read Excel data
		File file = new File("C:\\Users\\Ajith\\Documents\\vl6.xls");
		FileInputStream excel = new FileInputStream(file);
		Workbook workbook = Workbook.getWorkbook(excel);
		Sheet sheet = workbook.getSheet(0);
		int rowcount = sheet.getRows();
		int colcount = sheet.getColumns();
		System.out.println(rowcount);
		System.out.println(colcount);
		String testdata[][] = new String[rowcount - 1][colcount];
		for (i = 1; i < rowcount; i++)
			for (j = 0; j < colcount; j++) {
				testdata[i - 1][j] = sheet.getCell(j, i).getContents();

				System.out.println(testdata[i - 1][j]);

			}
		return testdata;
	}
	
@BeforeSuite()
public void getexcel() throws IOException, BiffException, InterruptedException {
	System.setProperty("webdriver.chrome.driver", "C:\\Users\\Ajith\\Downloads\\chromedriver_win32 (1)\\chromedriver.exe");
	driver=new ChromeDriver();

wait = new WebDriverWait(driver,20);
}

@Test(dataProvider = "datapass")
public static void main(String Trigger_Values) throws WriteException, BiffException {

	driver.get("https://devilmportal.bagicsit2.bajajallianz.com/#/");
	driver.manage().window().maximize();
	concat=Trigger_Values;
	System.out.println(""+Trigger_Values);
		checkexistingxcel();

}


public static void checkexistingxcel() throws WriteException, BiffException {
	File file=new File("C:\\Users\\Ajith\\Documents\\vl6.xls");
	if(file.exists()) {
		CreateExcel();
	}
}
public static void CreateExcel() throws WriteException, BiffException {
	try {
		
		Workbook cwb=Workbook.getWorkbook(new File("C:\\Users\\Ajith\\Documents\\vl6.xls"));
		WritableWorkbook writewb=Workbook.createWorkbook(new File("C:\\Users\\Ajith\\Documents\\vl6.xls"), cwb);
		WritableSheet copysheet=writewb.getSheet(1);
		WritableCell cell;
		Label label=new Label(i,j,concat);
		cell=(WritableCell) label;
	copysheet.addCell(cell);
		writewb.write();
		writewb.close();
		cwb.close();
		System.out.println("written");
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

}
