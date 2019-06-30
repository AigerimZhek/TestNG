package com.class05;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilis.CommonMethods;

public class TC1 extends CommonMethods {
	/*
	 * TC 1: Saucedemo Username1(Data Provider - Quantity:, Customer name:,Street:
	 * City:, State: ,Zip:,Card:,Card Nr:, Expire date ) Step 1: Open browser and
	 * navigate to smartbearsoftware Step 2: Enter valid username, password and
	 * click login button Step 3: Verify user successfully logged in Step 4: Click
	 * on Order Step 5: Make an order for two user,enter all the information for
	 * both users(Quantity:, Customer name:,Street: City:, State: ,Zip:,Card:,Card
	 * Nr:, Expire date) Step 6: Take ScreenShot before submitting each user
	 */

	@Parameters({ "browser", "url", "username", "pass" })
	@BeforeMethod
	public void setUp(String browserType, String url, String username, String pass) {

		if (browserType.equalsIgnoreCase("chrome")) {
			setUpDriver(browserType, url);

			sendText(driver.findElement(By.xpath("//input[contains(@id,'username')]")), username);
			sendText(driver.findElement(By.cssSelector("input[id*='password']")), pass);
			driver.findElement(By.xpath("//input[contains(@id,'login_button')]")).click();
		} else if (browserType.equalsIgnoreCase("firefox")) {
			setUpDriver(browserType, url);

			sendText(driver.findElement(By.xpath("//input[contains(@id,'username')]")), username);
			sendText(driver.findElement(By.cssSelector("input[id*='password']")), pass);
			driver.findElement(By.xpath("//input[contains(@id,'login_button')]")).click();
		}

	}

	@DataProvider(name = "bear")
	public Object[][] setUpData() {
		Object[][] data = new Object[2][9];

		data[0][0] = "4";
		data[0][1] = "Steve Cole";
		data[0][2] = "Walney Road";
		data[0][3] = "Chantilly";
		data[0][4] = "VA";
		data[0][5] = "25544";
		data[0][6] = "Visa";
		data[0][7] = "1264215484";
		data[0][8] = "07/22";

		data[1][0] = "7";
		data[1][1] = "Kate False";
		data[1][2] = "Pinky Road";
		data[1][3] = "Oslo";
		data[1][4] = "VA";
		data[1][5] = "25642";
		data[1][6] = "MasterCard";
		data[1][7] = "468446516879";
		data[1][8] = "05/23";

		return data;
	}

	@Test(dataProvider = "bear")
	public void userTest(String quant, String name, String address, String city, String state, String zip, String card,
			String cardNum, String expDate) {

		boolean disp = driver.findElement(By.xpath("//h1[text()='Web Orders']")).isDisplayed();
		Assert.assertTrue(disp);

		driver.findElement(By.linkText("Order")).click();

		WebElement element = driver.findElement(By.cssSelector("input[id$='Quantity']"));
		sendText(element, quant);

		element = driver.findElement(By.cssSelector("input[id$='txtName']"));
		sendText(element, name);

		element = driver.findElement(By.cssSelector("input[id$='Box2']"));
		sendText(element, address);

		element = driver.findElement(By.cssSelector("input[id$='Box3']"));
		sendText(element, city);

		element = driver.findElement(By.cssSelector("input[id$='Box4']"));
		sendText(element, state);

		element = driver.findElement(By.cssSelector("input[id$='Box5']"));
		sendText(element, zip);

		element = driver.findElement(By.cssSelector("input[value='" + card + "']"));
		element.click();

		element = driver.findElement(By.cssSelector("input[id$='Box6']"));
		sendText(element, cardNum);

		element = driver.findElement(By.cssSelector("input[id$='Box1']"));
		sendText(element, expDate);

		if (name.contains("Steve")) {
			takeScreenShot("SmartBear/Steve");
		} else {
			takeScreenShot("SmartBear/Kate");
		}

		driver.findElement(By.linkText("Process")).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.close();
	}
}
