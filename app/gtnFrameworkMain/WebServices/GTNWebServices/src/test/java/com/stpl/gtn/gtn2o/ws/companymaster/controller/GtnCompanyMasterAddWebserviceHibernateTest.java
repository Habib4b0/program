package com.stpl.gtn.gtn2o.ws.companymaster.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.RandomStringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 *
 * @author Abishek.Ram
 */

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.module.companymaster.controller.GtnCompanyMasterAddWebserviceHibernate;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GTNRestController-servlet.xml" })
public class GtnCompanyMasterAddWebserviceHibernateTest {

	public GtnCompanyMasterAddWebserviceHibernateTest() {
	}

	@Autowired
	private GtnCompanyMasterAddWebserviceHibernate cmAddService;

	@BeforeClass
	public static void setUpClass() {
	}

	@AfterClass
	public static void tearDownClass() {
	}

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	/**
	 * Test of getSessionFactory method, of class
	 * GtnCompanyMasterAddWebserviceHibernate.
	 */
	/**
	 * Test of saveCompanyMaster method, of class
	 * GtnCompanyMasterAddWebserviceHibernate.
	 */
	@Test
	public void testSaveCompanyMaster() {
		System.out.println("saveCompanyMaster");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnCMasterBean cmRequest = new GtnCMasterBean();
		cmRequest = setCompanyMasterInfo(cmRequest);
		cmRequest = setCompanyUdc(cmRequest);
		cmRequest = setCompanyIdentifierInfo(cmRequest);
		cmRequest = setCompanyTradeClassInfo(cmRequest);
		cmRequest = setCompanyParentInfo(cmRequest);
		cmRequest = setCompanyMasterNotesTab(cmRequest);
		GtnWsGeneralRequest wsRequest = new GtnWsGeneralRequest();
		wsRequest.setUserId("1");
		gtnWsRequest.setGtnCMasterRequest(new GtnCMasterRequest());
		gtnWsRequest.setGtnWsGeneralRequest(wsRequest);
		gtnWsRequest.getGtnCMasterRequest().setGtnCMasterBean(cmRequest);
		cmAddService.saveCompanyMaster(gtnWsRequest);
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
		for (int i = 0; i < 2; i++) {
			GtnCMasterIdentifierInfoBean companyIdentifierInfoRequest = new GtnCMasterIdentifierInfoBean();
			companyIdentifierInfoRequest.setCompanyQualifierSid(20);
			companyIdentifierInfoRequest.setCompanyIdentifierValue("TP Identifier-1000" + i);
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

		}
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
		companyInfoRequest.setSource("GTN");
		companyInfoRequest.setCreatedBy(1);
		companyInfoRequest.setCreatedDate(currentDate);
		companyInfoRequest.setModifiedBy(1);
		companyInfoRequest.setModifiedDate(currentDate);
		cmRequest.setGtnCMasterInformationBean(companyInfoRequest);
		return cmRequest;
	}

}
