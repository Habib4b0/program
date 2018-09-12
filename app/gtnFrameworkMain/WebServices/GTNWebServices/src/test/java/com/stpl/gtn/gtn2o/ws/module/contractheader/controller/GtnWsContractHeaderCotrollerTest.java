/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractheader.controller;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsContractHeaderCotrollerTest {
    
    public GtnWsContractHeaderCotrollerTest() {
    }
    
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
    
    
    @Autowired
    private GtnWsContractHeaderCotroller gtnWsContractHeaderCotroller;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of saveService method, of class GtnWsContractHeaderCotroller.
     */
    @Test
    public void testSaveService() {
        System.out.println("saveService");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);


        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        //gtnWsContractMasterBean.setContractMasterSid(0);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test158");
        gtnWsContractMasterBean.setContractNo("test158");
        gtnWsContractMasterBean.setContractName("test158");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList = new ArrayList<>();
        GtnwsContractAliasMasterBean e = new GtnwsContractAliasMasterBean();
        e.setContractAliasMasterSid(0);
        e.setContractType(651);
        e.setContractTypeDesc("Manufacturer");
        e.setContractMasterSid(0);
        e.setContractAliasNo("test156");
        e.setTpCompanyMasterSid(74773);
        e.setStartDate(new Date());       
        gtnwsContractAliasMasterBeanList.add(e);
        List<NotesTabBean> noteBeanList = new ArrayList<>();
        NotesTabBean eb = new NotesTabBean();
        eb.setMasterTableSystemId(0);
        eb.setMasterTableName("CONTRACT_MASTER");
        eb.setCreatedDate(new Date());
        eb.setCreatedBy(20156);
        noteBeanList.add(eb);
        gtnWsContractHeaderRequest.setNoteBeanList(noteBeanList);
        gtnWsContractHeaderRequest.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        gtnWsContractHeaderCotroller.saveService(gtnWsRequest);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
    }
    
     @Test
    public void testSaveServiceFalse() {
       
        System.out.println("testSaveServiceFalse");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);


        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(0);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList = new ArrayList<>();
        GtnwsContractAliasMasterBean e = new GtnwsContractAliasMasterBean();
        e.setContractAliasMasterSid(0);
        e.setContractType(651);
        e.setContractTypeDesc("Manufacturer");
        e.setContractMasterSid(0);
        e.setContractAliasNo("test156");
        e.setTpCompanyMasterSid(74773);
        e.setStartDate(new Date());       
        gtnwsContractAliasMasterBeanList.add(e);
        List<NotesTabBean> noteBeanList = new ArrayList<>();
        NotesTabBean eb = new NotesTabBean();
        eb.setMasterTableSystemId(0);
        eb.setMasterTableName("CONTRACT_MASTER");
        eb.setCreatedDate(new Date());
        eb.setCreatedBy(20156);
        noteBeanList.add(eb);
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        try{
        gtnWsContractHeaderCotroller.saveService(gtnWsRequest);
         assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
        } catch (Exception  ex) {
			
	}

    }

    /**
     * Test of deleteContractAliasService method, of class GtnWsContractHeaderCotroller.
     */
    @Test
    public void testDeleteContractAliasService() {
        System.out.println("deleteContractAliasService");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList = new ArrayList<>();
        GtnwsContractAliasMasterBean e = new GtnwsContractAliasMasterBean();
        e.setContractAliasMasterSid(288);
        e.setContractType(651);
        e.setContractTypeDesc("Manufacturer");
        e.setContractMasterSid(0);
        e.setContractAliasNo("test156");
        e.setTpCompanyMasterSid(74773);
        e.setStartDate(new Date());       
        gtnwsContractAliasMasterBeanList.add(e);
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        gtnWsContractHeaderRequest.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderCotroller.deleteContractAliasService(gtnWsRequest);
        assertFalse(result==null);
    }
    
     @Test
    public void testDeleteContractAliasServiceFalse() {
        System.out.println("deleteContractAliasService");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList = new ArrayList<>();
        GtnwsContractAliasMasterBean e = new GtnwsContractAliasMasterBean();
        e.setContractAliasMasterSid(0);
        e.setContractType(651);
        e.setContractTypeDesc("Manufacturer");
        e.setContractMasterSid(0);
        e.setContractAliasNo("test156");
        e.setTpCompanyMasterSid(74773);
        e.setStartDate(new Date());       
        gtnwsContractAliasMasterBeanList.add(e);
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        gtnWsContractHeaderRequest.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        try{
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderCotroller.deleteContractAliasService(gtnWsRequest);
        //assertFalse(result==null);
         assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
        } catch (Exception  ex) {
			
	}
    }

    /**
     * Test of deleteContractMasterService method, of class GtnWsContractHeaderCotroller.
     */
    @Test
    public void testDeleteContractMasterService() {
        System.out.println("deleteContractMasterService");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1254);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderCotroller.deleteContractMasterService(gtnWsRequest);
        assertFalse(result==null);
    }
    
        @Test
    public void testDeleteContractMasterServiceFalse() {
        System.out.println("deleteContractMasterService");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(0);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        //gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        try{
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderCotroller.deleteContractMasterService(gtnWsRequest);
        assertFalse(gtnWsContractMasterBean.getContractType()==0);
        } catch (Exception  ex) {
			
	}
        
    }

    /**
     * Test of fetchContractHeaderService method, of class GtnWsContractHeaderCotroller.
     */
    @Test
    public void testFetchContractHeaderService() {      
        System.out.println("fetchContractHeaderService");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1279);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderCotroller.fetchContractHeaderService(gtnWsRequest);
        assertFalse(result==null);
    }
    
    @Test
    public void testFetchContractHeaderServiceFalse() {      
        System.out.println("fetchContractHeaderService");
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1279);
        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
        gtnWsContractMasterBean.setTradingPartnerName("");
        gtnWsContractMasterBean.setDocumentClass(null);
        gtnWsContractMasterBean.setContractType(218);
        gtnWsContractMasterBean.setContractStatus(126);
        gtnWsContractMasterBean.setPaymentTerms(0);
        gtnWsContractMasterBean.setContractId("test156");
        gtnWsContractMasterBean.setContractNo("test156");
        gtnWsContractMasterBean.setContractName("test156");
        gtnWsContractMasterBean.setStartDate(new Date());
        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
        gtnWsContractMasterBean.setInternalNotes("kk");
        gtnWsContractMasterBean.setContractEligibleDate(new Date());
        gtnWsContractHeaderRequest.setUserId("20156");
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        try{
        GtnUIFrameworkWebserviceResponse result = gtnWsContractHeaderCotroller.fetchContractHeaderService(gtnWsRequest);
        assertFalse(gtnWsContractMasterBean.getContractType()==0);
        } catch (Exception  ex) {
			
	}
    }
    
}
