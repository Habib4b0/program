package com.stpl.app.testbench;

import org.junit.After;

import com.vaadin.testbench.elements.CheckBoxElement;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.OptionGroupElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;
import org.jboss.logging.Logger;

public class PriceScheduleTest extends TestBenchTestCase {
    private static final Logger LOGGER = Logger.getLogger(PriceScheduleTest.class);
	private String psName = "Test1PS8345";
	private String psEditName = "Test1PS44897";

	@Before
	public void setUp() throws InterruptedException {

		setDriver(new FirefoxDriver());
		driver.get("https://galderma-test.sysbiz.org/");
		//driver.get("http://localhost:8081/c/portal/login?p_l_id=17401");
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		

		// Use this if menu is not Expanded.
		findElement(By.xpath(".//span[contains(text(),'Global')]")).click();

		findElement(
				By.xpath(".//span[contains(text(),'Price Schedule Master')]"))
				.click();
	}

	// Search

	@Test
	public void testPriceSchedule1() {

		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ComboBoxElement.class).get(0).selectByText("Active");
		$(ComboBoxElement.class).get(1).selectByText("Service Fees");
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class)
				.first().getText());

	}

	@Test
	public void testPriceSchedule2() {

		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertFalse("Notification Window is present",
				$(WindowElement.class).exists());
		$(WindowElement.class).first().$(ButtonElement.class).first().click();

	}

	// Add

	@Test
	public void testPriceSchedule3() throws Exception {
		
		$(ButtonElement.class).caption("ADD").first().click();

		// Tab 1
		$(TextFieldElement.class).get(3).sendKeys(psName);
		String psNo = $(TextFieldElement.class).get(3).getValue();
		$(TextFieldElement.class).get(4).sendKeys(psName);
		$(TextFieldElement.class).get(5).sendKeys(psName);
		String psNames = $(TextFieldElement.class).get(5).getValue();
		$(ComboBoxElement.class).get(0).selectByText("Active");
		findElement(By.id("PriceScheduleStartDate")).findElement(
				By.tagName("input")).sendKeys("02/06/2015");

		// Tab 2
		$(TabSheetElement.class).first().openTab(1);
		$(ComboBoxElement.class).get(0).selectByText("IFP No");
		$(TextFieldElement.class).get(3).sendKeys("I*");
		$(ButtonElement.class).caption("Search").first().click();
		try {
		Thread.sleep(15000);
	} catch (InterruptedException e) {
		// TODO Auto-generated catch block
		LOGGER.error(e);
	}
	findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
    .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
	findElement(By.id("addBtn")).findElement(By.xpath(".//span[contains(text(),'>')]"))
	.click();

		// Tab 3
		$(TabSheetElement.class).first().openTab(2);
		$(OptionGroupElement.class).get(0).setValue("Enable");

		findElement(
				By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']"))
				.click();
		$(CheckBoxElement.class).get(0).click();
		$(ComboBoxElement.class).get(0).selectByText("Status");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("CP Start Date");
		findElement(By.id("MassDate")).findElement(By.tagName("input"))
				.sendKeys("08/08/2015");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("Price Type");
		$(ComboBoxElement.class).get(1).click();
		$(ComboBoxElement.class).get(1).selectByText("AMP");
		 Thread.sleep(5000);
		$(ButtonElement.class).caption("Populate").first().click();
		$(ButtonElement.class).caption("Save").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes").first().click();		
		Assert.assertEquals(psNo+","+psNames +" has been successfully saved"
				, $(NotificationElement.class).first().getText());
	}

	@Test
	public void testPriceSchedule4() throws Exception {
		
		$(ButtonElement.class).caption("ADD").first().click();
		$(TabSheetElement.class).first().openTab(0);
		$(ButtonElement.class).caption("Save").first().click();
		Assert.assertTrue("PS saved without mandatory field",
				$(LabelElement.class).id("ErrorMessage").isDisplayed());

	}

	// IM edit test

	@Test
	public void testPriceSchedule5() throws InterruptedException {

		$(TextFieldElement.class).get(0).sendKeys(psName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		
		Assert.assertEquals("Search is not successfully completed",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		$(NotificationElement.class).first().close();
		Thread.sleep(2000);
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
	    .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();

		// Tab1
		Assert.assertTrue("Edit is not Working", $(TabSheetElement.class)
				.exists());
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(TextFieldElement.class).get(3).sendKeys(psEditName);
		$(ComboBoxElement.class).get(0).selectByText("Obsolete");
		String psNo = $(TextFieldElement.class).get(3).getValue();
		String psNames = $(TextFieldElement.class).get(5).getValue();

		// Tab 2
		$(TabSheetElement.class).first().openTab(1);
		$(ComboBoxElement.class).get(0).selectByText("IFP No");
		$(TextFieldElement.class).get(3).sendKeys("I*");
		$(ButtonElement.class).caption("Search").first().click();
		Thread.sleep(15000);
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
       .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[4]")).click();
		findElement(By.id("addBtn")).findElement(By.xpath(".//span[contains(text(),'>')]"))
		.click();
		$(TabSheetElement.class).first().openTab(2);
		$(OptionGroupElement.class).get(0).setValue("Enable");

		findElement(
				By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']"))
				.click();
		$(ComboBoxElement.class).get(0).selectByText("CP Start Date");
		findElement(By.id("MassDate")).findElement(By.tagName("input"))
				.sendKeys("08/08/2015");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("Status");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("Price Type");
		$(ComboBoxElement.class).get(1).click();
		$(ComboBoxElement.class).get(1).selectByText("AMP");
		 Thread.sleep(5000);
		$(ButtonElement.class).caption("Populate").first().click();
		$(ButtonElement.class).caption("Update").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
	    .first().click();
		Assert.assertEquals(psNo+","+psNames +" has been successfully saved"
			, $(NotificationElement.class).first().getText());
	}

	@Test
	public void testPriceSchedule6() {
		$(ButtonElement.class).caption("EDIT").first().click();
		Assert.assertFalse("Edit screen is opened without selecting a item",
				$(TabSheetElement.class).exists());
		$(WindowElement.class).first().$(ButtonElement.class).caption("Ok")
		.first().click();
	}

	// View

	@Test
	public void testPriceSchedule7() {

		$(TextFieldElement.class).get(0).sendKeys(psEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search is not successfully completed","Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			
			
		}
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
      .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		Assert.assertTrue("View button is not available",
				$(ButtonElement.class).caption("VIEW").exists());
		$(ButtonElement.class).caption("VIEW").first().click();
		Assert.assertTrue("View screen is not opened", $(TabSheetElement.class)
				.exists());
		$(TabSheetElement.class).first().openTab(0);
		$(TabSheetElement.class).first().openTab(1);
		$(TabSheetElement.class).first().openTab(2);
		$(ButtonElement.class).caption("Back").first().click();
	}

	// Delete

	@Test
	public void testPriceSchedule8() {

		$(TextFieldElement.class).get(0).sendKeys(psEditName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		
		Assert.assertEquals("Search Completed", $(NotificationElement.class)
				.first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
      .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		String psNo = $(TextFieldElement.class).get(3).getValue();
    	String psNames = $(TextFieldElement.class).get(5).getValue();
		$(ButtonElement.class).caption("Delete").first().click();
		if (!$(WindowElement.class).exists()) {
			Assert.fail($(NotificationElement.class).first().getText());
		}
		$(WindowElement.class).first().$(ButtonElement.class).first().click();
		String expected = psNo+","+psNames +" has been successfully deleted";

		Assert.assertEquals(expected, $(NotificationElement.class).first()
				.getText());
	}

	@After
	public void end() {
		//driver.quit();
	}

}
