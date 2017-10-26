package com.stpl.app.testbench;

import org.junit.After;
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
import com.vaadin.testbench.elements.PopupDateFieldElement;
import com.vaadin.testbench.elements.TabSheetElement;
import com.vaadin.testbench.elements.TableElement;
import com.vaadin.testbench.elements.TextFieldElement;
import com.vaadin.testbench.elements.WindowElement;

public class CompanyMasterTest extends TestBenchTestCase {

	String companyName="TestCompany12022015d";
	String editCompanyName="EditTestCompany12022015";
	
	// For Search

	@Test
	public void testCompanyMaster1() {

		$(TextFieldElement.class).get(0).sendKeys("");
		$(TextFieldElement.class).get(1).sendKeys("NoCompany");
		$(ButtonElement.class).caption("SEARCH").first().click();		
		Assert.assertEquals("No results found", $(NotificationElement.class).first().getText());

	}

	@Test
	public void testCompanyMaster2() {
		
		$(TextFieldElement.class).get(0).sendKeys("100109");
		$(TextFieldElement.class).get(1).sendKeys("TPNO2344");
		$(TextFieldElement.class).get(2).sendKeys("TPNM2344");
		$(ComboBoxElement.class).get(0).selectByText("Active");
		$(ComboBoxElement.class).get(1).selectByText("Wholesaler Hq");
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());

	}

	
	@Test
	public void testCompanyMaster3() {

		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertTrue("Please enter search criteria",$(WindowElement.class).exists());

	}


	
	 @Test
	public void testCompanyMaster5() {
			
		$(ButtonElement.class).caption("ADD").first().click();

		// Tab 1
		$(TextFieldElement.class).get(3).sendKeys(companyName);		
		$(TextFieldElement.class).get(4).sendKeys(companyName);
		$(TextFieldElement.class).get(5).sendKeys(companyName);
		$(ComboBoxElement.class).get(0).selectByText("Active");		
		findElement(By.id("CompanyStartDate")).findElement(By.tagName("input"))
				.sendKeys("02/06/2015");		
		findElement(By.id("CompanyEndDate")).findElement(By.tagName("input"))
				.sendKeys("04/06/2015");
		$(ComboBoxElement.class).get(3).selectByText("Adap Programs");


		// Tab 3
		$(TabSheetElement.class).first().openTab(2);
		$(ComboBoxElement.class).get(0).selectByText("BANK_820");
		$(TextFieldElement.class).get(3).sendKeys("CompIdeng");
		$(ComboBoxElement.class).get(1).selectByText("Divested");
		findElement(By.id("identifierStartDate")).findElement(
				By.tagName("input")).sendKeys("04/06/2015");
		findElement(By.id("identifierEndDate"))
				.findElement(By.tagName("input")).sendKeys("05/06/2015");
		$(ButtonElement.class).caption("ATTACH").first().click();

		// Tab 4
		$(TabSheetElement.class).first().openTab(3);
		$(ComboBoxElement.class).get(0).selectByText("Broker");
		findElement(By.id("tradeClassSDate")).findElement(
				By.tagName("input")).sendKeys("05/05/2015");
		findElement(By.id("tradeClassEDate"))
				.findElement(By.tagName("input")).sendKeys("06/06/2016");
		$(ButtonElement.class).caption("ATTACH").first().click();

		// Tab 5
		$(TabSheetElement.class).first().openTab(4);
		$(TextFieldElement.class).get(3).click();
		$(WindowElement.class).first().$(TextFieldElement.class).first()
				.sendKeys("*");
		$(WindowElement.class).first().$(ButtonElement.class).caption("Search")
				.first().click();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Select")
				.first().click();
		findElement(By.id("ParentStartDate")).findElement(By.tagName("input"))
				.sendKeys("03/03/2015");
		findElement(By.id("ParentEndDate")).findElement(By.tagName("input"))
				.sendKeys("03/03/2016");
		$(ButtonElement.class).caption("ATTACH").first().click();

		// Save a Company
		String companyNo =$(TextFieldElement.class).get(3).getText();
		String companyNames =$(TextFieldElement.class).get(5).getText();
		$(ButtonElement.class).caption("Save").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		Assert.assertTrue("Company save failed", $(NotificationElement.class)
				.exists());
		Assert.assertEquals(companyNo+","+companyNames +" has been successfully saved"
			, $(NotificationElement.class).first().getText());
	}



	// For Edit

	@Test
	public void testCompanyMaster7() {
		$(TextFieldElement.class).get(0).sendKeys(companyName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("No results found",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		//$(TableElement.class).first().getRow(0).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		Assert.assertTrue("Edit screen is not opened", $(TabSheetElement.class)
				.exists());
	}

	@Test
	public void testCompanyMaster8() {		
		$(ButtonElement.class).caption("EDIT").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Ok")
		.first().click();
	}

	@Test
	public void testCompanyMaster9() {		
		$(ButtonElement.class).caption("RESET").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
	.first().click();
	}

	@Test
	public void testCompanyMaster10() {
		$(TextFieldElement.class).get(0).sendKeys(companyName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search is not successfully completed",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		Assert.assertTrue("Edit screen is not opened", $(TabSheetElement.class)
				.exists());

		// Tab 1
		$(TabSheetElement.class).first().openTab(0);
		$(ComboBoxElement.class).get(0).selectByText("Inactive");	
		$(ButtonElement.class).caption("Update").first().click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes")
		.first().click();
		Assert.assertEquals(companyName+","+companyName +" has been successfully saved"
			, $(NotificationElement.class).first().getText());
	}

	@Test
	public void testCompanyMaste11() {
		$(TextFieldElement.class).get(0).sendKeys(companyName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search is not successfully completed",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		Assert.assertTrue("Edit screen is not opened", $(TabSheetElement.class)
				.exists());

		// Tab 1
		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(ButtonElement.class).caption("Back").first().click();
		Assert.assertTrue("Back button is not working", $(WindowElement.class).exists());
		$(WindowElement.class).first().$(ButtonElement.class).caption("Yes").first().click();
	}

	@Test
	public void testCompanyMaster12() {

		// Search Screen

		$(TextFieldElement.class).get(0).sendKeys(companyName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search is not successfully completed",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[1]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		// For First Tab

		$(TabSheetElement.class).first().openTab(0);
		$(TextFieldElement.class).get(3).clear();
		$(TextFieldElement.class).get(3).setValue(editCompanyName);
		$(TextFieldElement.class).get(4).clear();
		$(TextFieldElement.class).get(4).setValue(editCompanyName);
		$(TextFieldElement.class).get(5).clear();
		$(TextFieldElement.class).get(5).setValue(editCompanyName);
		$(ComboBoxElement.class).get(0).selectByText("Active");		
		findElement(By.id("CompanyStartDate")).findElement(By.tagName("input"))
				.clear();
		findElement(By.id("CompanyStartDate")).findElement(By.tagName("input"))
				.sendKeys("01/01/2015");
		findElement(By.id("CompanyEndDate")).findElement(By.tagName("input"))
				.clear();
		findElement(By.id("CompanyEndDate")).findElement(By.tagName("input"))
				.sendKeys("01/01/2016");
		$(ComboBoxElement.class).get(3).selectByText("Alternate Health Site");

		// Not working for popup date field
		// $(PopupDateFieldElement.class).first().clear();
		// $(PopupDateFieldElement.class).first().sendKeys("02/06/2015");

		// Second Tab

		$(TabSheetElement.class).first().openTab(1);
		$(TextFieldElement.class).get(3).setValue("Address Line 1");
		$(TextFieldElement.class).get(3).setValue("Address Line 2");
		$(TextFieldElement.class).get(4).setValue("City");
		$(TextFieldElement.class).get(5).setValue("154876");
		$(ComboBoxElement.class).get(0).selectByText("USA");

		// Third Tab

		$(TabSheetElement.class).first().openTab(2);
		$(ComboBoxElement.class).get(0).click();
		$(ComboBoxElement.class).get(0).selectByText("Braodlane Internal ID");
		$(TextFieldElement.class).get(3).sendKeys("CompIdenff");
		$(ComboBoxElement.class).get(1).selectByText("Active");
		
		
		findElement(By.id("identifierStartDate")).findElement(
				By.tagName("input")).sendKeys("01/01/2015");
		findElement(By.id("identifierEndDate"))
				.findElement(By.tagName("input")).sendKeys("01/01/2016");
		$(ButtonElement.class).caption("ATTACH").first().click();

		// Fourth Tab

		$(TabSheetElement.class).first().openTab(3);
		$(ComboBoxElement.class).get(0).selectByText("Ahc");
		findElement(By.id("tradeClassSDate")).findElement(
				By.tagName("input")).sendKeys("15/05/2015");
		findElement(By.id("tradeClassEDate"))
				.findElement(By.tagName("input")).sendKeys("16/06/2016");
		$(ButtonElement.class).caption("ATTACH").first().click();

		// Fifth Tab

		$(TabSheetElement.class).first().openTab(4);
		$(TextFieldElement.class).get(3).click();
		$(WindowElement.class).first().$(TextFieldElement.class).first()
				.sendKeys("*");
		$(WindowElement.class).first().$(ButtonElement.class).caption("Search")
				.first().click();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		$(WindowElement.class).first().$(ButtonElement.class).caption("Select")
				.first().click();
		findElement(By.id("ParentStartDate")).findElement(By.tagName("input"))
				.clear();
		findElement(By.id("ParentStartDate")).findElement(By.tagName("input"))
				.sendKeys("13/03/2015");
		findElement(By.id("ParentEndDate")).findElement(By.tagName("input"))
				.clear();
		findElement(By.id("ParentEndDate")).findElement(By.tagName("input"))
				.sendKeys("13/03/2016");
		$(ButtonElement.class).caption("ATTACH").first().click();
		String companyNo =$(TextFieldElement.class).get(3).getText();
		String companyNames =$(TextFieldElement.class).get(5).getText();
		$(ButtonElement.class).caption("Update").first().click();

	}

	// For View Mode

	@Test
	public void testCompanyMaster13() {

		$(TextFieldElement.class).get(0).sendKeys("*");
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search is not successfully completed",
				"Search Completed", $(NotificationElement.class).first()
						.getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		Assert.assertTrue("View button is not available", $(ButtonElement.class).caption("VIEW").exists());
		$(ButtonElement.class).caption("VIEW").first().click();
		Assert.assertTrue("Edit screen is not opened", $(TabSheetElement.class).exists());
		$(TabSheetElement.class).first().openTab(0);
		$(TabSheetElement.class).first().openTab(1);
		$(TabSheetElement.class).first().openTab(2);
		$(TabSheetElement.class).first().openTab(3);
		$(TabSheetElement.class).first().openTab(4);
		$(ButtonElement.class).caption("Back").first().click();
	}

	// For Delete

	@Test
	public void testCompanyMaster14() {

		$(TextFieldElement.class).get(0).sendKeys(companyName);
		$(ButtonElement.class).caption("SEARCH").first().click();
		Assert.assertEquals("Search Completed", $(NotificationElement.class).first().getText());
		$(NotificationElement.class).first().close();
		findElement(By.xpath("//div[@class='v-scrollable v-table-body-wrapper v-table-body']"))
        .findElement(By.tagName("tbody")).findElement(By.xpath(".//tr[3]")).click();
		$(ButtonElement.class).caption("EDIT").first().click();
		String companyNo =$(TextFieldElement.class).get(3).getText();
		String companyNames =$(TextFieldElement.class).get(5).getText();
		$(ButtonElement.class).caption("Delete").first().click();
		if (!$(WindowElement.class).exists()) {
			Assert.fail($(NotificationElement.class).first().getText());
		}
		$(WindowElement.class).first().$(ButtonElement.class).first().click();
		String expected = companyNo+","+companyNames +"has been successfully deleted";

		Assert.assertEquals(expected, $(NotificationElement.class).first()
				.getText());
	}

	@Before
	public void setUp() throws InterruptedException {
		
		setDriver(new FirefoxDriver());		
		getDriver().get("https://galderma-bpigtn.sysbiz.org/");		
		driver.manage().window().maximize();
		findElement(By.id("_58_login")).sendKeys("admin");
		findElement(By.id("_58_password")).sendKeys("admin");
		findElement(By.className("btn-primary")).click();		
		
		// Use this if menu is not Expanded.
		 findElement(By.xpath(".//span[contains(text(),'Global')]")).click();
		
		findElement(By.xpath(".//span[contains(text(),'Company Master')]")).click();
	}

	@After
	public void closeBrowser() {
		driver.quit();
	}


}
