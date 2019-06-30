package com.class02;

import org.openqa.selenium.By;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import utilis.CommonMethods;

public class PriorityExample extends CommonMethods{
	
	@BeforeClass
	public void setUp() {
		setUpDriver("chrome", "https://www.saucedemo.com/");
		boolean swagLabs = driver.findElement(By.xpath("//title[text()='Swag Labs']")).isDisplayed();
		
		if(swagLabs=true) {
			System.out.println("right title");
		}else {
			System.out.println("wrong title");
		}
	}
	
	@Test(priority=0)
	public void login() {
		sendText(driver.findElement(By.cssSelector("input#user-name")),"standard_user");
		sendText(driver.findElement(By.cssSelector("input#password")),"secret_sauce");
		driver.findElement(By.cssSelector("input.btn_action")).click();
	}

	@Test(priority=1)
	public void mainPage() {
		String actual = driver.findElement(By.xpath("//div[@class='product_label']")).getText();
		String expected = "Products";
		if(expected.equals(actual)) {
			System.out.println("Expected matches actual text");
		}else {
			System.out.println("Expected does not match actual text");
		}
	}
	
	@AfterClass
	public void closePage() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}
}
