package com.stpl.app.testbench;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.vaadin.testbench.TestBenchTestCase;
import com.vaadin.testbench.elements.ButtonElement;
import com.vaadin.testbench.elements.CheckBoxElement;
import com.vaadin.testbench.elements.ComboBoxElement;
import com.vaadin.testbench.elements.LabelElement;
import com.vaadin.testbench.elements.NotificationElement;
import com.vaadin.testbench.elements.OptionGroupElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TableElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class IFPTest extends TestBenchTestCase{	

	@Before
	public void setUp() throws Exception {		
		setDriver(new FirefoxDriver());
			
		getDriver().get("https://galderma-test.sysbiz.org/");
		
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();	
		driver.manage().window().maximize();	
	   findElement(By.xpath(".//span[contains(text(),'Global')]")).click();		
		findElement(By.xpath(".//span[contains(text(),'Item Family Plan')]")).click();
	}

	// delete Code for IFP
	@Test
	public void ifpTest1() {
		
			$(TextFieldElement.class).get(0).sendKeys("Test777");
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
			$(NotificationElement.class).first().close();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
     		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();		
			String ifpno = findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		     		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]//td[2]")).getText();
			String 	ifpname =findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		     		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]//td[3]")).getText();
			$(ButtonElement.class).caption("EDIT").first().click();	
			Assert.assertTrue($(TabSheetElement.class).exists());
			$(ButtonElement.class).caption("Delete").first().click();
			if (!$(WindowElement.class).exists()) {
				Assert.fail($(NotificationElement.class).first().getText());
			}
			$(WindowElement.class).first().$(ButtonElement.class).first().click();			
			String expected = ifpno+", "+ifpname +" has been successfully deleted";

			Assert.assertEquals(expected, $(NotificationElement.class).first()
					.getText());
	}

	// IFP search test with all values
	@Test
	public void ifpTest2() {		
			$(TextFieldElement.class).get(0).sendKeys("*");
			$(TextFieldElement.class).get(1).sendKeys("*");
			$(TextFieldElement.class).get(2).sendKeys("*");
			$(ComboBoxElement.class).get(0).selectByText("Active");
			$(ComboBoxElement.class).get(1).selectByText("Package");
			$(TextFieldElement.class).get(3).sendKeys("*");
			$(TextFieldElement.class).get(4).sendKeys("*");
			$(TextFieldElement.class).get(5).sendKeys("*");
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search Completed",$(NotificationElement.class).first().getText());
			$(NotificationElement.class).first().close();	
	}

	// IFP search test with no values
	@Test
	public void ifpTest3() {

		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertTrue("Please enter search criteria",$(WindowElement.class).exists());

	}

	// CFP Add test

	@Test
	public void ifpTest4() throws Exception {
		
		$(ButtonElement.class).caption("ADD").first().click();

		// Tab 1
		$(TextFieldElement.class).get(3).sendKeys("ifpTst13022015");
		String ifpId = $(TextFieldElement.class).get(3).getValue();
		$(TextFieldElement.class).get(4).sendKeys("111");
		$(TextFieldElement.class).get(5).sendKeys("ifpTest11");
		String ifpname = $(TextFieldElement.class).get(5).getValue();
		$(ComboBoxElement.class).get(0).selectByText("Active");		
		findElement(By.id("IFPStartDate")).findElement(By.tagName("input")).sendKeys("02/06/2015");		
		findElement(By.id("IFPEndDate")).findElement(By.tagName("input")).sendKeys("05/06/2018");
		$(ComboBoxElement.class).get(1).selectByText("Child");
		
		// Tab 2
		$(TabSheetElement.class).first().openTab(1);

		$(ComboBoxElement.class).get(0).selectByText("Item No");
		$(TextFieldElement.class).get(3).sendKeys("*");
		$(ButtonElement.class).caption("Search").first().click();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
		.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		findElement(By.id("btnMove")).findElement(By.xpath(".//span[contains(text(),'>')]"))
		.click();

		// Tab 3
		$(TabSheetElement.class).first().openTab(2);	
		$(OptionGroupElement.class).get(0).setValue("Enable");
		Thread.sleep(3000);
	
		findElement(By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']")).click();
		$(ComboBoxElement.class).get(0).selectByText("IFP Start Date");
		findElement(By.id("MassDate")).findElement(By.tagName("input"))
		.sendKeys("02/06/2015");
		$(CheckBoxElement.class).get(0).click();
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("IFP End Date");
		findElement(By.id("MassDate")).findElement(By.tagName("input")).clear();
		findElement(By.id("MassDate")).findElement(By.tagName("input"))
		.sendKeys("02/06/2018");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ComboBoxElement.class).get(0).selectByText("IFP Status");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		$(ButtonElement.class).caption("Populate").first().click();
		$(ButtonElement.class).caption("Save").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		Assert.assertEquals(ifpId+", "+ ifpname +" has been successfully saved"
				, $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();

	}
	
	// CFP Add negative test

		@Test
		public void ifpTest5() {
			
			$(ButtonElement.class).caption("ADD").first().click();
			$(ButtonElement.class).caption("Save").first().click();	
			Assert.assertTrue($(LabelElement.class).id("ErrorMessage").isDisplayed());
		}

	//  IFP Edit test
	
	 @Test
	 public void ifpTest6() {
		 $(TextFieldElement.class).get(0).sendKeys("ifpTst0910");
			$(ButtonElement.class).caption("SEARCH").first().click();
			$(NotificationElement.class).first().close();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
			.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click(); 
			$(ButtonElement.class).caption("EDIT").first().click();			
			
			//Tab1			
			$(TextFieldElement.class).get(3).clear();
			$(TextFieldElement.class).get(3).sendKeys("editifpTst0910");
			$(ComboBoxElement.class).get(0).selectByText("Active");
			String ifpNo = $(TextFieldElement.class).get(3).getValue();
			String ifpName = $(TextFieldElement.class).get(5).getValue();
			$(TabSheetElement.class).first().openTab(2);			
			findElement(By.xpath("//div[@class='v-table-caption-container v-table-caption-container-align-left']")).click();
			$(ButtonElement.class).caption("Update").first().click();
			$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
			.first().click();
			Assert.assertEquals(ifpNo+", "+ifpName +" has been successfully Saved"
				, $(NotificationElement.class).first().getText());

			
	 }
	 @Test
	 public void ifpTest7() {
		 	$(TextFieldElement.class).get(0).sendKeys("editifpTst0910");
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());	
			$(NotificationElement.class).first().close();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
			.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click(); 	
			$(ButtonElement.class).caption("EDIT").first().click();		
			$(TextFieldElement.class).get(3).clear();		
			$(ButtonElement.class).caption("Update").first().click();			
			Assert.assertTrue($(LabelElement.class).id("ErrorMessage").isDisplayed());
			
	 } 
	// For View Mode

		@Test
		public void ifpTest8() {

			$(TextFieldElement.class).get(0).sendKeys("*");
			$(ButtonElement.class).caption("SEARCH").first().click();
			Assert.assertEquals("Search is not successfully completed","Search Completed", $(NotificationElement.class).first().getText());
			$(NotificationElement.class).first().close();
			findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
			.findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
			Assert.assertTrue("view button is not available", $(ButtonElement.class).caption("VIEW").exists());
			$(ButtonElement.class).caption("VIEW").first().click();
			Assert.assertTrue("Edit screen is not opened", $(TabSheetElement.class).exists());
			$(TabSheetElement.class).first().openTab(0);
			$(TabSheetElement.class).first().openTab(1);
			$(TabSheetElement.class).first().openTab(2);		
			$(ButtonElement.class).caption("Back").first().click();
			$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
     		.first().click();
		}

	
	@After
	public void closeMethod() {
		getDriver().quit();
	}
}
