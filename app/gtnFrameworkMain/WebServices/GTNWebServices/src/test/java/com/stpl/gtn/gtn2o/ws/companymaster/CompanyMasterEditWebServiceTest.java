package com.stpl.gtn.gtn2o.ws.companymaster;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.module.companymaster.controller.GtnWsCMasterEdit;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.ServiceContextUtil;

@Ignore
public class CompanyMasterEditWebServiceTest {

	@Test
	public void testGetCompanyMaster() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		GtnWsCMasterEdit instance = (GtnWsCMasterEdit) ServiceContextUtil.instance().getBean("cmEditService");
		GtnCMasterBean cmBean = new GtnCMasterBean();
		GtnCMasterInformationBean companyInformationBean = new GtnCMasterInformationBean();
		companyInformationBean.setCompanyMasterSystemId(519638);
		cmBean.setGtnCMasterInformationBean(companyInformationBean);
		gtnCMasterRequest.setGtnCMasterBean(cmBean);
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		GtnUIFrameworkWebserviceResponse response = instance.getCompanyMasterDetails(gtnWsRequest);
		System.out.println("Response from WS::::::::::::" + response.getGtnCompanyMasterResponse());
		System.out.println("Response from WS::::::::::::" + response.getGtnCompanyMasterResponse().getGtnCMasterBean());
		System.out.println("Response from WS::::::::::::"
				+ response.getGtnCompanyMasterResponse().getGtnCMasterBean().getGtnCMasterInformationBean());
		System.out.println("Response from WS::::::::::::" + response.getGtnCompanyMasterResponse().getGtnCMasterBean()
				.getGtnCMasterInformationBean().getAddress1());

	}

	@Test
	public void testUpdateCompanyMaster() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsCMasterEdit instance = (GtnWsCMasterEdit) ServiceContextUtil.instance().getBean("cmEditService");
		GtnCMasterBean cmRequest = new GtnCMasterBean();
		GtnCMasterRequest gtnCMasterRequest = new GtnCMasterRequest();
		cmRequest = setCompanyMasterInfo(cmRequest);
		cmRequest = setCompanyUdc(cmRequest);
		cmRequest = setCompanyIdentifierInfo(cmRequest);
		cmRequest = setCompanyTradeClassInfo(cmRequest);
		cmRequest = setCompanyParentInfo(cmRequest);
		cmRequest = setCompanyMasterNotesTab(cmRequest);
		cmRequest.setUpdateFlag(true);
		gtnCMasterRequest.setGtnCMasterBean(cmRequest);
		gtnWsRequest.setGtnCMasterRequest(gtnCMasterRequest);
		instance.updateCompanyMasterDetails(gtnWsRequest);
		Object[] arr = { "Test024IiW", "Test024IiW", "Test024IiW", 295, 184, null, null, "Tue Dec 20 13:04:12 IST 2016",
				null, null, null, null, "address_1", "address_2", "City", "352", "12345", null, null, null, null, "A",
				0, "20150731", "Cars", 1, "Tue Dec 20 13:04:12 IST 2016", 1, "Tue Dec 20 13:04:12 IST 2016", null,
				"COMPANY_MASTER", 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, null, 20, "TP Identifier-1000", 184,
				"Tue Dec 20 13:04:12 IST 2016", null, null, "A", 0, 1594, "CARS", 1, "Tue Dec 20 13:04:12 IST 2016", 1,
				"Tue Dec 20 13:04:12 IST 2016", null, "Tue Dec 20 13:04:12 IST 2016", "Tue Dec 20 13:04:12 IST 2016",
				400, null, null, "Tue Dec 20 13:04:12 IST 2016", "A", 0, 1598, "CARS", 1,
				"Tue Dec 20 13:04:12 IST 2016", 1, "Tue Dec 20 13:04:12 IST 2016", null, 190908,
				"Tue Dec 20 13:04:12 IST 2016", null, null, null, "Tue Dec 20 13:04:12 IST 2016,A,0,1596", "CARS", 1,
				"Tue Dec 20 13:04:12 IST 2016", 1, "Tue Dec 20 13:04:12 IST 2016", null, "COMPANY_MASTER",
				System.getProperty("gtn.app.data.path")+"/Attachments/Docs/Test.txt", "Tue Dec 20 13:04:12 IST 2016", 13330, null };
		System.out.println(arr);
	}

	private GtnCMasterBean setCompanyMasterNotesTab(GtnCMasterBean cmRequest) {
		List<NotesTabBean> cmNotesTabRequestList = new ArrayList<>();
		Date currentDate = new Date();
		NotesTabBean companyNotesTabRequest = new NotesTabBean();
		companyNotesTabRequest.setMasterTableName("COMPANY_MASTER");
		companyNotesTabRequest.setFilePath(System.getProperty("gtn.app.data.path")+"/Attachments/Docs/Test.txt");
		companyNotesTabRequest.setCreatedDate(currentDate);
		companyNotesTabRequest.setCreatedBy(13330);
		cmNotesTabRequestList.add(companyNotesTabRequest);
		cmRequest.setGtnCMasterCompanyNotesTabBeanList(cmNotesTabRequestList);
		return cmRequest;
	}

	private GtnCMasterBean setCompanyParentInfo(GtnCMasterBean cmRequest) {
		Date currentDate = new Date();
		List<GtnCMasterCompanyParentBean> cmParentRequest = new ArrayList<>();
		GtnCMasterCompanyParentBean companyParentRequest = new GtnCMasterCompanyParentBean();
		companyParentRequest.setParentCompanyMasterSystemId(190908);
		companyParentRequest.setCompanyParentStartDate(currentDate);
		companyParentRequest.setCompanyParentEndDate(null);
		companyParentRequest.setPriorParentCmpyMasterSystemId(null);
		companyParentRequest.setPriorParentStartDate(null);
		companyParentRequest.setLastUpdatedDate(null);
		companyParentRequest.setInboundStatus("A");
		companyParentRequest.setRecordLockStatus(0);
		companyParentRequest.setBatchId("1596");
		companyParentRequest.setSource("CARS");
		companyParentRequest.setCreatedBy(1);
		companyParentRequest.setCreatedDate(currentDate);
		companyParentRequest.setModifiedBy(1);
		companyParentRequest.setModifiedDate(null);
		cmParentRequest.add(companyParentRequest);
		cmRequest.setGtnCMasterCompanyParentBeanList(cmParentRequest);
		return cmRequest;
	}

	private GtnCMasterBean setCompanyTradeClassInfo(GtnCMasterBean cmRequest) {
		Date currentDate = new Date();
		List<GtnCMasterCompanyTradeClassBean> cmTradeClassRequest = new ArrayList<>();
		GtnCMasterCompanyTradeClassBean companyTradeClassRequest = new GtnCMasterCompanyTradeClassBean();
		companyTradeClassRequest.setCompanyTradeClassStartDate(currentDate);
		companyTradeClassRequest.setCompanyTradeClassEndDate(currentDate);
		companyTradeClassRequest.setCompanyTradeClassSid(400);
		companyTradeClassRequest.setCompanyPriorTradeClass(null);
		companyTradeClassRequest.setCompanyPriorTradeClassStartDate(null);
		companyTradeClassRequest.setLastUpdatedDate(null);
		companyTradeClassRequest.setInboundStatus("A");
		companyTradeClassRequest.setRecordLockStatus(0);
		companyTradeClassRequest.setBatchId("1598");
		companyTradeClassRequest.setSource("CARS");
		companyTradeClassRequest.setCreatedBy(1);
		companyTradeClassRequest.setCreatedDate(currentDate);
		companyTradeClassRequest.setModifiedBy(1);
		companyTradeClassRequest.setModifiedDate(null);
		cmTradeClassRequest.add(companyTradeClassRequest);
		cmRequest.setGtnCMasterCompanyTradeClassBeanList(cmTradeClassRequest);
		return cmRequest;
	}

	private GtnCMasterBean setCompanyIdentifierInfo(GtnCMasterBean cmRequest) {
		Date currentDate = new Date();
		List<GtnCMasterIdentifierInfoBean> cmIdentifierInfoRequest = new ArrayList<>();
		GtnCMasterIdentifierInfoBean companyIdentifierInfoRequest = new GtnCMasterIdentifierInfoBean();
		companyIdentifierInfoRequest.setCompanyQualifierSid(20);
		companyIdentifierInfoRequest.setCompanyIdentifierValue("TP Identifier-1000");
		companyIdentifierInfoRequest.setIdentifierStatus(184);
		companyIdentifierInfoRequest.setCompanyIdentifierStartDate(currentDate);
		companyIdentifierInfoRequest.setCompanyIdentifierEndDate(null);
		companyIdentifierInfoRequest.setEntityCode(null);
		companyIdentifierInfoRequest.setInboundStatus("A");
		companyIdentifierInfoRequest.setRecordLockStatus(0);
		companyIdentifierInfoRequest.setBatchId("1594");
		companyIdentifierInfoRequest.setSource("CARS");
		companyIdentifierInfoRequest.setCreatedBy(1);
		companyIdentifierInfoRequest.setCreatedDate(currentDate);
		companyIdentifierInfoRequest.setModifiedBy(1);
		companyIdentifierInfoRequest.setModifiedDate(currentDate);
		cmIdentifierInfoRequest.add(companyIdentifierInfoRequest);
		cmRequest.setGtnCMasterIdentifierInfoBeanList(cmIdentifierInfoRequest);
		return cmRequest;
	}

	private GtnCMasterBean setCompanyUdc(GtnCMasterBean cmRequest) {
		GtnCMasterCompanyUdcInfoBean cmUdcInfoRequest = new GtnCMasterCompanyUdcInfoBean();
		cmUdcInfoRequest.setMasterType("COMPANY_MASTER");
		cmUdcInfoRequest.setUdc1(0);
		cmUdcInfoRequest.setUdc2(0);
		cmUdcInfoRequest.setUdc3(0);
		cmUdcInfoRequest.setUdc4(0);
		cmUdcInfoRequest.setUdc5(0);
		cmUdcInfoRequest.setUdc6(0);
		cmUdcInfoRequest.setUdc7(0);
		cmUdcInfoRequest.setUdc8(0);
		cmUdcInfoRequest.setUdc9(0);
		cmUdcInfoRequest.setUdc10(0);
		cmUdcInfoRequest.setUdc11(0);
		cmUdcInfoRequest.setUdc12(0);
		cmRequest.setGtnCMasterCompanyUdcInfoBean(cmUdcInfoRequest);
		return cmRequest;
	}

	private GtnCMasterBean setCompanyMasterInfo(GtnCMasterBean cmRequest) {
		Date currentDate = new Date();
		String randasom = RandomStringUtils.randomAlphanumeric(3);
		GtnCMasterInformationBean companyInfoRequest = new GtnCMasterInformationBean();
		companyInfoRequest.setCompanyId("Test024" + randasom);
		companyInfoRequest.setCompanyNo("Test024" + randasom);
		companyInfoRequest.setCompanyName("Test024" + randasom);
		companyInfoRequest.setCompanyType(295);
		companyInfoRequest.setCompanyStatus(184);
		companyInfoRequest.setCompanyCategory(0);
		companyInfoRequest.setCompanyGroup(0);
		companyInfoRequest.setCompanyStartDate(currentDate);
		companyInfoRequest.setCompanyEndDate(null);
		companyInfoRequest.setOrganizationKey(0);
		companyInfoRequest.setLives(0);
		companyInfoRequest.setFinancialSystem(null);
		companyInfoRequest.setAddress1("address_1");
		companyInfoRequest.setAddress2("address_2");
		companyInfoRequest.setCity("City");
		companyInfoRequest.setState(352);
		companyInfoRequest.setZipCode("12345");
		companyInfoRequest.setCountry(0);
		companyInfoRequest.setRegionCode(null);
		companyInfoRequest.setLastUpdatedDate(null);
		companyInfoRequest.setInternalNotes(null);
		companyInfoRequest.setInboundStatus("A");
		companyInfoRequest.setRecordLockStatus(0);
		companyInfoRequest.setBatchId("20150731");
		companyInfoRequest.setSource("Cars");
		companyInfoRequest.setCreatedBy(1);
		companyInfoRequest.setCreatedDate(currentDate);
		companyInfoRequest.setModifiedBy(1);
		companyInfoRequest.setModifiedDate(currentDate);
		companyInfoRequest.setCompanyMasterSystemId(519639);

		cmRequest.setGtnCMasterInformationBean(companyInfoRequest);
		return cmRequest;
	}

}
