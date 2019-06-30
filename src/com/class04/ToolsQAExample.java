package com.class04;

import org.openqa.selenium.By;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import utilis.CommonMethods;

public class ToolsQAExample extends CommonMethods {

	@BeforeMethod(alwaysRun = true)
	public void setUp() {
		setUpDriver("chrome", "https://www.toolsqa.com/automation-practice-form/");
	}

	@DataProvider()
	public Object[][] setUpData() {
		Object[][] data = new Object[4][3];

		data[0][0] = "first1";
		data[0][1] = "last1";
		data[0][2] = "00-00-0001";

		data[1][0] = "first2";
		data[1][1] = "last2";
		data[1][2] = "00-00-0002";

		data[2][0] = "first3";
		data[2][1] = "last3";
		data[2][2] = "00-00-0003";

		data[3][0] = "first4";
		data[3][1] = "last4";
		data[3][2] = "00-00-0004";

		return data;
	}

	@Test(dataProvider= "setUpData")
	public void getData(String first, String last, String data) {
		driver.findElement(By.cssSelector("input[name='firstname']")).sendKeys(first);
		driver.findElement(By.cssSelector("input[name='lastname']")).sendKeys(last);
		driver.findElement(By.cssSelector("input[id='datepicker']")).sendKeys(data);
	}
	
	@AfterMethod(alwaysRun=true)
	public void tearDown() {
		driver.close();
	}

}
