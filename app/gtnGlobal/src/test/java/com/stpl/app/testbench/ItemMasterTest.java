package com.stpl.app.testbench;

import com.vaadin.testbench.TestBenchTestCase;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.By;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class ItemMasterTest extends TestBenchTestCase {

	private String imName = "TestIM12022015";
	private String imEDITName = "TestIM12022015";

	@Before
	public void setUp() {
		setDriver(new FirefoxDriver());
		driver.get("https://galderma-bpigtn.sysbiz.org/");
	
        driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		

		// Use this if menu is not Expanded.
	   findElement(By.xpath(".//span[contains(text(),'Global')]")).click();

		findElement(By.xpath(".//span[contains(text(),'Item Master')]"))
				.click();

	}

	// SEARCH operation
	@Test
	public void testIM1() {
		$(TextFieldElement.class).get(0).sendKeys("*");
		$(TextFieldElement.class).get(1).sendKeys("*");

		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class)
				.first().getText());
	}



// add operation
	@Test
	public void testIM3() throws Exception {
		// Tab 1
		$(ButtonElement.class).caption("ADD").first().click();
	
		$(TextFieldElement.class).get(4).sendKeys(imName);
		$(TextFieldElement.class).get(5).sendKeys(imName);
		$(TextFieldElement.class).get(6).sendKeys(imName);
		$(ComboBoxElement.class).get(0).selectByText("Active");
		findElement(By.id("itemStartDate")).findElement(By.tagName("input"))
				.sendKeys("01/06/2015");
		findElement(By.id("itemEndDate")).findElement(By.tagName("input"))
				.sendKeys("02/06/2015");

		$(ComboBoxElement.class).get(1).selectByText("Non-Drug");
		$(ComboBoxElement.class).get(3).selectByText("ADAPALENE");
		$(ComboBoxElement.class).get(4).selectByText("DISPLAY BRAND-114");
		$(ComboBoxElement.class).get(6).selectByText("Bath Oil");

		$(ComboBoxElement.class).get(7).selectByText("Strength-1");
		$(TextFieldElement.class).get(9).sendKeys("999933356");
		$(TextFieldElement.class).get(10).sendKeys("67676767");
		$(TextFieldElement.class).get(12).sendKeys("677");
		
		$(TabSheetElement.class).first().openTab(1);

		// third Tab
		$(TabSheetElement.class).first().openTab(2);
		$(ComboBoxElement.class).get(0).selectByText("CSS Product ID");
		$(TextFieldElement.class).get(4).sendKeys("Item12307");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		findElement(By.id("IdentifierSD")).findElement(By.tagName("input"))
				.sendKeys("06/06/2015");
		findElement(By.id("IdentifierED")).findElement(By.tagName("input"))
				.sendKeys("10/06/2015");
		$(ButtonElement.class).caption("Attach").first().click();
		// Fourth Tab
		$(TabSheetElement.class).first().openTab(3);
		$(ComboBoxElement.class).get(0).selectByText("AMP");
		$(TextFieldElement.class).get(4).sendKeys("555");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		$(ComboBoxElement.class).get(2).selectByText("3");
		findElement(By.id("PricingSD")).findElement(By.tagName("input"))
				.sendKeys("06/06/2015");
		findElement(By.id("PricingED")).findElement(By.tagName("input"))
				.sendKeys("10/06/2015");
		$(ButtonElement.class).caption("Attach").first().click();
		String imNo=$(TextFieldElement.class).get(4).getText();
		String imNames=$(TextFieldElement.class).get(6).getText();
		$(ButtonElement.class).caption("SAVE").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		Assert.assertEquals(imNo+","+imNames +" has been successfully saved"
			, $(NotificationElement.class).first().getText());

	}

	@Test
	public void testIM4() throws Exception {
		
		$(ButtonElement.class).caption("ADD").first().click();
		$(TabSheetElement.class).first().openTab(0);
		$(ButtonElement.class).caption("SAVE").first().click();
		
		Assert.assertTrue("IM saved without mandatory field",
				$(LabelElement.class).id("ErrorMessage").isDisplayed());

	}

	// IM EDIT test
	@Test
	public void testIM5() throws InterruptedException {
		$(TextFieldElement.class).get(1).sendKeys(imName);

		$(ButtonElement.class).caption("SEARCH").first().click();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(4).clear();
		$(TextFieldElement.class).get(4).sendKeys(imEDITName);
		$(TextFieldElement.class).get(5).clear();
		$(TextFieldElement.class).get(5).sendKeys(imEDITName);
		$(TextFieldElement.class).get(6).clear();
		$(TextFieldElement.class).get(6).sendKeys(imEDITName);
		String imNo=$(TextFieldElement.class).get(4).getText();
		String imNames=$(TextFieldElement.class).get(6).getText();
		$(TabSheetElement.class).first().openTab(2);
		$(TabSheetElement.class).first().openTab(3);
		$(ButtonElement.class).caption("UPDATE").first().click();
	}

	@Test
	public void testIM6() {
		$(ButtonElement.class).caption("EDIT").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Ok")
		.first().click();
	}

	// View

	@Test
	public void testIM7() {

		$(TextFieldElement.class).get(1).sendKeys(imName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("SEARCH is not successfully completed",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		Assert.assertTrue("View button is not available",
				$(ButtonElement.class).caption("VIEW").exists());
		$(ButtonElement.class).caption("VIEW").first().click();
		Assert.assertTrue("View screen is not opened", $(TabSheetElement.class)
				.exists());
		$(TabSheetElement.class).first().openTab(0);
		$(TabSheetElement.class).first().openTab(2);
		$(TabSheetElement.class).first().openTab(3);

		$(ButtonElement.class).caption("BACK").first().click();
	}

	// Delete

	@Test
	public void testIM8() {


		$(TextFieldElement.class).get(1).sendKeys("TestIM12022015");
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class)
				.first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		Assert.assertTrue($(TabSheetElement.class).exists());
		$(TabSheetElement.class).first().openTab(0);
		String textName=$(TextFieldElement.class).get(0).getText();
		String imNo=$(TextFieldElement.class).get(4).getText();
		String imNames=$(TextFieldElement.class).get(6).getText();
		$(ButtonElement.class).caption("DELETE").exists();
		$(ButtonElement.class).caption("DELETE").first().click();
		if (!$(WindowElement.class).exists()) {
			Assert.fail($(NotificationElement.class).first().getText());
		}
		$(WindowElement.class).first().$(ButtonElement.class).first().click();
		String expected = imNo+","+imNames +" has been successfully deleted";
		Assert.assertEquals(expected, $(NotificationElement.class).first()
				.getText());
	}

	@After
	public void end() {
	driver.quit();
	}

}
