package com.class04;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utilis.CommonMethods;

public class HomeWork extends CommonMethods {
	/*
	 * Identify Priority of Test Cases
	 * 
	 * http://secure.smartbearsoftware.com/samples/TestComplete11/WebOrders/Login.
	 * aspx?ReturnUrl=%2fsamples%2fTestComplete11%2fWebOrders%2fDefault.aspx
	 * 
	 * TC 1: WebOrder Verify Successful Login ( ) Step 1: Open browser and navigate
	 * to WebOrder Step 2: Enter valid username, enter valid password and click on
	 * the login button Step 3: Verify user successfully logged in
	 * 
	 * TC 2: WebOrder Creating and verifying Users( ) Step 1: Create Two users and
	 * fill out all the required fields Step 2: Verify that user one name appears
	 * within the table Step 3: Edit user one and update user one’s State, assert
	 * the new updated State is displayed and save the changes. Step 4: Verify that
	 * user two name appears within the table Step 5: Edit user two and update user
	 * two’s City and assert the new updated City is displayed and save the changes.
	 * 
	 * TC 3: WebOrder verifying Users( ) Step 1 : Assert Both User one and User Two
	 * are displayed
	 * 
	 * Note: Create BeforeClass and AfterClass annotations to open browser and
	 * logout from the application. The creation of users two should depend on the
	 * creation of users one.The validation both users should depend on the creation
	 * of both users. Create xml file and share a screenshot of the test.
	 * 
	 */

	@Parameters({ "browser", "url" })
	@BeforeClass(alwaysRun = true)
	public void setUp(String browser, String url) {
		setUpDriver(browser, url);
	}

	@Parameters({ "user", "pass" })
	@Test(priority = 1)
	public void login(String user, String pass) {
		sendText(driver.findElement(By.xpath("//input[contains(@id,'username')]")), user);
		sendText(driver.findElement(By.cssSelector("input[id*='password']")), pass);
		driver.findElement(By.xpath("//input[contains(@id,'login_button')]")).click();

		boolean disp = driver.findElement(By.xpath("//h1[text()='Web Orders']")).isDisplayed();
		Assert.assertTrue(disp);
	}

	@Parameters({ "name", "address", "city", "state", "zip", "cardNum", "expDate", "newState" })
	@Test(priority = 2)
	public void createOrder(String name, String address, String city, String state, String zip, String cardNum,
			String expDate, String newState) throws InterruptedException {
		driver.findElement(By.linkText("Order")).click();

		WebElement element = driver.findElement(By.cssSelector("select[id$='Product']"));
		selectValueFromDD(element, 1);

		element = driver.findElement(By.cssSelector("input[id$='Quantity']"));
		sendText(element, "2");

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

		element = driver.findElement(By.cssSelector("input[value^='Master']"));
		RadioButton(element);

		element = driver.findElement(By.cssSelector("input[id$='Box6']"));
		sendText(element, cardNum);

		element = driver.findElement(By.cssSelector("input[id$='Box1']"));
		sendText(element, expDate);

		Thread.sleep(2000);

		driver.findElement(By.linkText("Process")).click();

		driver.findElement(By.linkText("View all orders")).click();

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));

		for (int i = 1; i <= rows.size(); i++) {

			String rowText = driver
					.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[" + i + "]")).getText();
			if (rowText.contains(name)) {
				driver.findElement(By.xpath("//table[contains(@id,'orderGrid')]/tbody/tr[" + i + "]/td[13]")).click();
				break;
			}
		}

		element = driver.findElement(By.cssSelector("input[id$='Box4']"));
		sendText(element, newState);

		driver.findElement(By.linkText("Update")).click();

		driver.findElement(By.linkText("View all orders")).click();

		for (int i = 1; i <= rows.size(); i++) {
			String rowText = driver
					.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[" + i + "]")).getText();
			if (rowText.contains(name) && rowText.contains(newState)) {
				System.out.println("The street name was updated");
				break;
			}

		}

	}

	@Parameters("name")
	@Test(priority = 3)
	public void verifyingUser(String name) {

		List<WebElement> rows = driver.findElements(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr"));

		for (int i = 1; i <= rows.size(); i++) {

			String rowText = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[" + i + "]")).getText();
			if(rowText.contains(name)) {
				System.out.println("User has been verified");
				break;
			}
		}
		takeScreenShot("SmartBear/HW");
	}
	
	@AfterClass(alwaysRun=true)
	public void tearDown() {
		driver.quit();
	}
}
