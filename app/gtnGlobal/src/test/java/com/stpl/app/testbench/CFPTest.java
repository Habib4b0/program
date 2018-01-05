package com.stpl.app.testbench;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CheckBoxElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.OptionGroupElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class CFPTest extends TestBenchTestCase{	
	private String cfpName="cfp234";
	private String editcfpName="Editcfp234";

	@Before
	public void setUp() {
		
		setDriver(new FirefoxDriver());			
		getDriver().get("https://galderma-test.sysbiz.org/");
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		
		driver.manage().window().maximize();	
		
		findElement(By.xpath(".//span[contains(text(),'Global')]")).click();
		
		findElement(By.xpath(".//span[contains(text(),'Company Family Plan')]")).click();		
	}

	// delete Code for company master
	
	 

	 //cfp search test with all values
	@Test
	public void cfpTest1() {					
			$(TextFieldElement.class).get(0).sendKeys("*");
			$(TextFieldElement.class).get(1).sendKeys("*");
			$(ComboBoxElement.class).get(1).selectByText("Active");			
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search Completed",$(NotificationElement.class).first().getText());		
	}

	// cfp search test with no values
	
	 @Test
	public void cfpTest2() {		
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertTrue("Notification Window is present",$(WindowElement.class).exists());		
		$(WindowElement.class).first().$(ButtonElement.class).caption("Ok")
		.first().click();
	}

	// CFP Add test

	 @Test
	public void cfpTest3() throws Exception {

		$(ButtonElement.class).caption("ADD").first().click();

		// Tab 1
		$(TextFieldElement.class).get(3).sendKeys("cfp234");
		$(TextFieldElement.class).get(4).sendKeys("cfp234");
		$(TextFieldElement.class).get(5).sendKeys("cfp234");
		$(ComboBoxElement.class).get(0).selectByText("Active");		
		findElement(By.id("CFPStartDate")).findElement(By.tagName("input"))	.sendKeys("02/06/2015");		
		findElement(By.id("CFPEndDate")).findElement(By.tagName("input")).sendKeys("02/06/2018");
		$(ComboBoxElement.class).get(1).selectByText("Admin");
		$(ComboBoxElement.class).get(2).selectByText("Acute");
		$(ComboBoxElement.class).get(3).selectByText("Clinic");

		// Tab 2
		$(TabSheetElement.class).first().openTab(1);
		$(ComboBoxElement.class).get(0).selectByText("Company No");
		$(TextFieldElement.class).get(3).sendKeys("*");
		$(ButtonElement.class).caption("Search").first().click();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		findElement(By.id("btnMove")).findElement(By.xpath(".//span[contains(text(),'>')]"))
		.click();

		// Tab 3
		$(TabSheetElement.class).first().openTab(2);	
		$(OptionGroupElement.class).get(0).setValue("Enable");	
		findElement(By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']")).click();
		$(ComboBoxElement.class).get(0).selectByText("CFP Start Date");		
		findElement(By.id("MassDate")).findElement(By.tagName("input")).sendKeys("02/06/2015");
		$(CheckBoxElement.class).get(0).click();
				
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("CFP End Date");		
		findElement(By.id("MassDate")).findElement(By.tagName("input")).sendKeys("02/06/2018");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("CFP Status");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		$(ButtonElement.class).caption("Populate").first().click();
		String cfpNo=$(TextFieldElement.class).get(3).getText();
		String cfpNames=$(TextFieldElement.class).get(5).getText();
		$(ButtonElement.class).caption("SAVE").first().click();		
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		Assert.assertEquals(cfpNo+", "+ cfpNames+" has been successfully Saved"
				, $(NotificationElement.class).first().getText());

	}
	
	
	 @Test
	public void cfpTest4() {
		
		$(ButtonElement.class).caption("ADD").first().click();
		$(ButtonElement.class).caption("SAVE").first().click();		
		Assert.assertTrue($(LabelElement.class).id("ErrorMessage").isDisplayed());
	}
	
	
	// Edit
	
	 @Test
	 public void cfpTest5() {
		 $(TextFieldElement.class).get(0).sendKeys(cfpName);
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
			$(NotificationElement.class).first().close();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
	        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();	
			$(ButtonElement.class).caption("EDIT").first().click();
			
			//Tab1
			
			$(TextFieldElement.class).get(3).clear();
			$(TextFieldElement.class).get(3).sendKeys(editcfpName);
			$(ComboBoxElement.class).get(2).selectByText("Acute");
			$(ComboBoxElement.class).get(3).selectByText("Clinic");
			$(TabSheetElement.class).first().openTab(2);			
			findElement(By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']")).click();
			$(ButtonElement.class).caption("UPDATE").first().click();

	 }
	 
	 @Test
	 public void cfpTest6() {
		 $(TextFieldElement.class).get(0).sendKeys("*");
			$(ButtonElement.class).caption("SEARCH").first().click();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();	
			$(ButtonElement.class).caption("EDIT").first().click();
			$(TextFieldElement.class).get(3).clear();
			Assert.assertTrue($(ButtonElement.class).caption("UPDATE").exists());
			$(ButtonElement.class).caption("UPDATE").first().click();		
			Assert.assertTrue($(LabelElement.class).id("ErrorMessage").isDisplayed());
			
	 }
	 	// Delete
	 
	 	 @Test
		public void cfpTest7() {			
				
				$(TextFieldElement.class).get(0).sendKeys("*");
				
				$(ButtonElement.class).caption("SEARCH").first().click();
				Assert.assertEquals("Search Completed",$(NotificationElement.class).first().getText());
				$(NotificationElement.class).first().close();
				findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
            	.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
				String cfpNo=$(TextFieldElement.class).get(3).getText();
				String cfpNames=$(TextFieldElement.class).get(5).getText();
				$(ButtonElement.class).caption("EDIT").first().click();				
				$(ButtonElement.class).caption("DELETE").first().click();
				if (!$(WindowElement.class).exists()) {
					Assert.fail($(NotificationElement.class).first().getText());
				}
				$(WindowElement.class).first().$(ButtonElement.class).first().click();
				String expected = cfpNo+","+cfpNames +"has been successfully deleted";

				Assert.assertEquals(expected, $(NotificationElement.class).first()
						.getText());
		}
//	
	 // For View Mode

		@Test
		public void cfpTest8() {

			$(TextFieldElement.class).get(0).sendKeys("*");
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search is not successfully completed","Search Completed", $(NotificationElement.class).first().getText());
			$(NotificationElement.class).first().close();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
			Assert.assertTrue("View button is not available", $(ButtonElement.class).caption("VIEW").exists());
			$(ButtonElement.class).caption("VIEW").first().click();
			Assert.assertTrue("Edit screen is not opened", $(TabSheetElement.class).exists());
			$(TabSheetElement.class).first().openTab(0);
			$(TabSheetElement.class).first().openTab(1);
			$(TabSheetElement.class).first().openTab(2);		
			$(ButtonElement.class).caption("BACK").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		}
	
	@After
	public void close(){
	 getDriver().quit();
	}
}
