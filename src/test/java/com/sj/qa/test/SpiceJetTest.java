package com.sj.qa.test;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SpiceJetTest {
	
	@Test
	public void Test() {
		System.setProperty("webdriver.chrome.driver", "C:\\work\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		driver.get("https://book.spicejet.com/");
		driver.findElement(By.xpath("//input[contains(@id,'originStation1_CTXT')]")).click();
		driver.findElement(By.xpath("//a[@value='BLR']")).click();
		driver.findElement(By.xpath("//div[contains(@id,'destinationStation1_CTNR')] //a[@value='MAA']")).click();
		
		String month = driver.findElement(By.cssSelector("div.ui-datepicker-title")).getText();
		while(!month.contains("July")) {
			System.out.println(month);
			driver.findElement(By.xpath("//span[contains(text(),'Next')]")).click();
			month = driver.findElement(By.cssSelector("div.ui-datepicker-title")).getText();
		}
		
		List<WebElement> dates = driver.findElements(By.xpath("//a[contains(@class,'ui-state-default')]"));
		
		for(int i=0;i<dates.size();i++) {
			if(dates.get(i).getText().equalsIgnoreCase("25")) {
				dates.get(i).click();
				break;
			}
		}
		
		driver.findElement(By.id("divpaxinfo")).click();
		Select adult = new Select(driver.findElement(By.xpath("//select[contains(@id,'DropDownListPassengerType_ADT')]")));
		adult.selectByValue("4");
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		System.out.println(driver.getTitle());
		driver.close();
		
		

				
	}
	

}
