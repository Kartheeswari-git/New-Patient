package pageObjectModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CreationPage {

	public static WebDriver driver;
	
	public static WebElement health_ID(WebDriver driver) {
		return driver.findElement(By.id(null));
	}
}
