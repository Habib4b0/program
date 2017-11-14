package com.stpl.gtn.gtn2o.ws.module.periodconfiguration;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.util.List;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.periodconfiguration.controller.GtnWsPeriodConfigurationController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.periodconfig.GtnWsPeriodConfigurationRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/GTNRestController-servlet.xml" })

public class GtnWsPeriodConfigurationTest {

	@Autowired
	GtnWsPeriodConfigurationController periodController;

	@Autowired
	GtnWsGeneralController genearlControler;

	// @Test
	public void testPagedTableLoadConfig() {
		RequestBuilder builder = new RequestBuilder();
		GtnUIFrameworkWebserviceResponse response = periodController
				.periodConfigLoad(builder.getSearchCountRequest());
		assertTrue(response.getGtnWsGeneralResponse().isSucess());
		response = periodController
				.periodConfigLoad(builder.getSearchDataRequest(0, response.getGtnSerachResponse().getCount()));
		assertTrue(response.getGtnWsGeneralResponse().isSucess());
	}

	@Test
	public void testSavePeriodConfig() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsPeriodConfigurationRequest gtnPeriodrequest = new GtnWsPeriodConfigurationRequest();
		RequestBuilder builder = new RequestBuilder();
		GtnUIFrameworkWebserviceResponse moduleComobox = genearlControler
				.getComboBoxResultSet(builder.getComboboxData("Module"));
		gtnPeriodrequest.setModule(
				Integer.valueOf(moduleComobox.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList().get(0)));
		GtnUIFrameworkWebserviceResponse busineesProcess = genearlControler
				.getComboBoxResultSet(builder.getComboboxData("BUSINESS_PROCESS"));
		gtnPeriodrequest.setBusinessProcess(Integer.valueOf(getRandomDataFromList(
				busineesProcess.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList())));
		GtnUIFrameworkWebserviceResponse company = genearlControler
				.getComboBoxResultSet(builder.getComboboxData("company"));
		gtnPeriodrequest.setCompany(Integer.valueOf(
				getRandomDataFromList(company.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList())));
		GtnUIFrameworkWebserviceResponse businessUnit = genearlControler
				.getComboBoxResultSet(builder.getComboboxData("Business_Unit"));
		gtnPeriodrequest.setBusinessUnit(Integer.valueOf(
				getRandomDataFromList(businessUnit.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList())));
		gtnPeriodrequest.setPeriodView("Single");

		GtnUIFrameworkWebserviceResponse mode = genearlControler.getComboBoxResultSet(builder.getComboboxData("Mode"));

		gtnPeriodrequest.setModeFrom(getComboxId(mode.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList(),
				mode.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList(), "Auto"));

		GtnUIFrameworkWebserviceResponse frequency = genearlControler
				.getComboBoxResultSet(builder.getComboboxData("Frequency"));

		gtnPeriodrequest
				.setFrequencyFrom(getComboxId(frequency.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList(),
						frequency.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList(), "MONTHLY"));
		gtnPeriodrequest.setPeriodFromTextBox("-2");
		gtnPeriodrequest.setDateFrom("07/01/2017");

		gtnPeriodrequest
				.setDefaultModeFrom(getComboxId(mode.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList(),
						mode.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList(), "Auto"));

		gtnPeriodrequest.setDefaultFrequencyFrom(
				getComboxId(frequency.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList(),
						frequency.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList(), "MONTHLY"));

		gtnPeriodrequest.setDefaultPeriodFromTextBox("-1");

		gtnPeriodrequest.setDefaultDateFrom("08/01/2017");
		request.setGtnWsPeriodConfigurationRequest(gtnPeriodrequest);

		gtnPeriodrequest.setUserId(Integer.valueOf(
				getRandomDataFromList(genearlControler.getUserIdNameBoxResultSet(new GtnUIFrameworkWebserviceRequest())
						.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList())));
		try {
			periodController.savePeriodConfig(request);
		} catch (GtnFrameworkGeneralException | ParseException e) {
			fail("Period Configuration cannot be saved");
		}
	}

	private int getComboxId(List<String> codeList, List<String> captionList, String targetCaption) {
		for (int i = 0; i < captionList.size(); i++) {
			if (captionList.get(i).equalsIgnoreCase(targetCaption)) {
				return Integer.valueOf(codeList.get(i));
			}
		}
		throw new IllegalArgumentException();
	}

	private String getRandomDataFromList(List<String> codeList) {
		if (codeList != null && !codeList.isEmpty()) {
			Random randomGenerator = new Random();
			int index = randomGenerator.nextInt(codeList.size());
			return codeList.get(index);

		} else {
			throw new IllegalArgumentException("Expecting a data in List");
		}
	}
}
