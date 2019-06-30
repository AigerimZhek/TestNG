package com.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import utilis.CommonMethods;

public class SoftAndHardAssertExample extends CommonMethods{
	
	
	@BeforeClass
	public void setUp() {
		setUpDriver("chrome", "https://opensource-demo.orangehrmlive.com/index.php/auth/login");
	}
	
	@Test(priority= 1)
	public void orangehrmLoginScreen() {
		String title = driver.getTitle();
		String exptitle = "OrangeHRM";
//		SoftAssert soft = new SoftAssert();
//		soft.assertEquals(title, exptitle);
//		System.out.println("soft assert ");
		Assert.assertEquals(title, exptitle);
		//System.out.println("after hard assert ");
	}
	
	@Test(priority =2)
	public void logo() {
		SoftAssert soft2 = new SoftAssert();
		try{WebElement logo = driver.findElement(By.xpath("//img[contains(@src,'logo.pngwwr')]"));
		soft2.assertTrue(logo.isDisplayed());
		
		}
		catch (Exception e) {
			// TODO: handle exception
		}
		
		System.out.println("after soft assert ");
	}
	
	

}
