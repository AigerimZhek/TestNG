package com.class02;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import utilis.CommonMethods;

public class Task extends CommonMethods{
	
	@BeforeMethod
	public void gettitle() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/");
		String title = driver.getTitle();
		System.out.println(title);
		String expected = "OrangeHRM";
		if(expected.equals(title)) {
			System.out.println("right title");
		}
	}
	@Test
	public void login() {
		sendText(driver.findElement(By.cssSelector("input#txtUsername")),"Admin");
		sendText(driver.findElement(By.cssSelector("input#txtPassword")),"admin123");
		driver.findElement(By.cssSelector("input.button")).click();
	}
	
	@AfterMethod
	public void close() throws InterruptedException {
		Thread.sleep(3000);
		driver.close();
	}

}
