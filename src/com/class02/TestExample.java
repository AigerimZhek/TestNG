package com.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilis.CommonMethods;

public class TestExample extends CommonMethods{
	
	@BeforeClass
	public void setUp() {
		setUpDriver("chrome", "http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");
	}
	
	@Test(priority=0)
	public void loginScreenTitle() {
		String logTitle = driver.getTitle();
		String expTitle = "Web Orders Login";
		if(logTitle.equals(expTitle)) {
			System.out.println("Right title");
		}else {
			System.out.println("Wrong title");
		}
	}
	
	@Test(priority=1)
	public void login() {
		sendText(driver.findElement(By.xpath("//input[contains(@id,'username')]")),"Tester");
		sendText(driver.findElement(By.cssSelector("input[id*='password']")),"test");
				driver.findElement(By.xpath("//input[contains(@id,'login_button')]")).click();
	}

	@Test(enabled=true,priority=2)
	public void webOrders() {
		WebElement webOrderTitle = driver.findElement(By.xpath("//h1[text()='Web Orders']"));
		if(webOrderTitle.isDisplayed()) {
			System.out.println("Title is displayed");
		}else {
			System.out.println("Title is not displayed");
		}
	}
	
	@AfterClass
	public void close() {
		driver.close();
	}
}
