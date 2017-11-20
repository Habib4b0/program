package com.stpl.gtn.gtn2o.ws.service;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.controller.GtnWsGeneralController;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
public class GtnGeneralServiceTest {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnGeneralServiceTest.class);

	public void testGetHelperTableResult() {
		System.out.println("getResultsForCompany");
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("GtnGeneralService");
		System.out.println("getResultsForCompany---1");
		GtnUIFrameworkWebserviceRequest gtnWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		gtnWebserviceRequest.getGtnWsGeneralRequest().setComboBoxType("CompanyIdentifier");
		System.out.println("getResultsForCompany---2");
		GtnUIFrameworkWebserviceResponse result = instance.getComboBoxResultSet(gtnWebserviceRequest);

		assertNotNull(result);

	}

	@Test
	public void testExecuteUpdateQuery() {

		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gTNGeneralServiceController");

		Date asd = new Date();

		String str = "Test022";
		Object[] savedata = { str, str, str, 295, 184, asd, "address_1", "address_2", "City", 352, "12345", "20150731",
				"BUSs", asd, asd };
		GtnFrameworkDataType[] saveCMHeader = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.DATE, GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING, GtnFrameworkDataType.DATE,
				GtnFrameworkDataType.DATE };
		String cmSaveQuery = ""
				+ "INSERT INTO COMPANY_MASTER (COMPANY_ID, COMPANY_NO, COMPANY_NAME, COMPANY_TYPE, COMPANY_STATUS, COMPANY_CATEGORY, COMPANY_GROUP, "
				+ "COMPANY_START_DATE, COMPANY_END_DATE, ORGANIZATION_KEY, LIVES, FINANCIAL_SYSTEM, ADDRESS1, ADDRESS2, CITY, \"STATE\", ZIP_CODE, COUNTRY, REGION_CODE, LAST_UPDATED_DATE, INTERNAL_NOTES, INBOUND_STATUS, RECORD_LOCK_STATUS, BATCH_ID, \"SOURCE\", CREATED_BY, CREATED_DATE, MODIFIED_BY, MODIFIED_DATE)"
				+ "VALUES (?, ?, ?, ?, ?, NULL, NULL, ?, NULL, NULL, NULL, NULL, ?, ?, ?, ?, ?, NULL, NULL, NULL, NULL, 'A', 0, ?, ?, 1, ?, 1, ?);";
		try {
			int id = instance.executeUpdateQuery(cmSaveQuery, savedata, saveCMHeader);
			System.out.println(id);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}

	@Test
	public void getComboBoxResultSetCompanyIdentifier() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType("CompanyIdentifier");
		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		int i = gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size();
		System.out.println(i);

		for (int j = 0; j < gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size(); j++) {
			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().get(j));
		}
	}

	@Test
	public void getComboBoxResultSetForCompanyMaster() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP);
		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		int i = gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size();
		System.out.println(i);

		for (int j = 0; j < gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size(); j++) {
			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().get(j));
		}
	}

	@Test
	public void getComboBoxResultSetForBusinessUnit() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(GtnFrameworkForecastConstantCommon.BUSINESS_UNIT_GLCOMP);

		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		int i = gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size();
		System.out.println(i);

		for (int j = 0; j < gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size(); j++) {
			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().get(j));
		}
	}

	@Test
	public void getComboBoxResultSetForCompanyMasterWithWhereClause() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setComboBoxType("CompanyMasterWithWhereClause");
		List<Object> paramListBulider = new ArrayList<>();
		paramListBulider.add(519472);
		generalWSRequest.setComboBoxWhereclauseParamList(paramListBulider);

		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		int i = gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size();
		System.out.println(i);

		for (int j = 0; j < gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size(); j++) {
			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().get(j));
		}
	}

	@Test
	public void getComboBoxResultSetForProductRelationWithWhereClause() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setComboBoxType("ProductRelation");

		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		int i = gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size();
		System.out.println("Total no. of values :" + i);

		for (int j = 0; j < gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size(); j++) {

			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList().get(j));
			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().get(j));
		}

	}

	@Test
	public void getComboBoxResultSetForProductLevelWithWhereClause() {

		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();

		generalWSRequest.setComboBoxType("ProductLevel");
		List<Object> paramListBulider = new ArrayList<>();
		paramListBulider.add(76);
		generalWSRequest.setComboBoxWhereclauseParamList(paramListBulider);

		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		System.out.println(gtnResponse);

	}

	@Test
	public void getComboBoxResultSetForTimePeriod() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(GtnFrameworkForecastConstantCommon.TIME_PERIOD_FROM_DATE);
		gtnWsRequest.setGtnWsGeneralRequest(generalWSRequest);
		GtnWsGeneralController instance = (GtnWsGeneralController) ServiceContextUtil.instance()
				.getBean("gtnGeneralServiceController");
		GtnUIFrameworkWebserviceResponse gtnResponse = instance.getComboBoxResultSet(gtnWsRequest);
		int i = gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size();
		System.out.println(i);

		for (int j = 0; j < gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().size(); j++) {
			System.out.println(gtnResponse.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList().get(j));
		}
	}

}
