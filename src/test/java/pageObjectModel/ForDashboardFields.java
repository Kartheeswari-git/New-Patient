package pageObjectModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForDashboardFields {

	public static WebDriver driver;
	//Login
	public static WebElement uName(WebDriver driver) {
		System.out.println("Test");
		return driver.findElement(By.xpath("//*[@id=\"username\"]"));
	}
	public static WebElement pWord(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"password\"]"));
	}
	public static WebElement login(WebDriver driver) {
		return driver.findElement(By.xpath("*//input[@value='Login']"));
	}	
	public static WebElement accept(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(text(),'Continue')]"));
	}
	//Status1
	public static WebElement dbs1(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[1]/div/div/div/div/div/div/div[1]/div[1]/a/div[2]/div/div/span"));
	}	

	public static WebElement status1(WebDriver driver) {
		return driver.findElement(By.xpath("//a[contains(text(),'PENDING WITH BAGIC')]"));
	}	
	public static List<WebElement> row_count(WebDriver driver) {
		return driver.findElements(By.xpath("//*[@id=\"table\"]/tbody/tr"));
	}

	//Status2
	public static WebElement dbs2(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[1]/div/div/div/div/div/div/div[1]/div[2]/a/div[2]/div/div/span"));
	}	

	public static WebElement status2(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(text(), 'PENDING WITH HOSPITAL')]"));
	}	
	//Status3
	public static WebElement dbs3(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[1]/div/div/div/div/div/div/div[1]/div[3]/a/div[2]/div/div/span"));
	}	

	public static WebElement status3(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(text(), 'CASHLESS APPROVED')]"));
	}	
	//Status4
	public static WebElement dbs4(WebDriver driver) {
		return driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div[1]/div/div/div/div/div/div/div[1]/div[4]/a/div[2]/div/div/span"));
	}	

	public static WebElement status4(WebDriver driver) {
		return driver.findElement(By.xpath("//*[contains(text(),'CASHLESS REJECTED')]"));
	}	
		
}
