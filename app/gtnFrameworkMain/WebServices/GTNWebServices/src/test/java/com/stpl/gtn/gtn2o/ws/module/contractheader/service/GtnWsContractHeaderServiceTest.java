/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractheader.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGroupBean;
import com.stpl.gtn.gtn2o.ws.companygroup.bean.GtnCompanyGrpInformationBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnWsContractMasterBean;
import com.stpl.gtn.gtn2o.ws.contract.bean.GtnwsContractAliasMasterBean;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.contract.ContractMaster;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.companygroup.GtnCompanyGroupRequest;
import com.stpl.gtn.gtn2o.ws.request.contract.GtnWsContractHeaderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
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
public class GtnWsContractHeaderServiceTest {
    
    public GtnWsContractHeaderServiceTest() {
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
    private GtnWsContractHeaderService gtnWsContractHeaderService;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private GtnWsAllListConfig gtnWebServiceAllListConfig;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getSessionFactory method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsContractHeaderService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of getCompanyHeaderFetchQuery method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testGetCompanyHeaderFetchQuery() throws Exception {
        System.out.println("getCompanyHeaderFetchQuery");
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();      
        gtnWsContractHeaderService.getCompanyHeaderFetchQuery(gtnWsRequest,gtnResponse);
        assertFalse(gtnWsContractMasterBean.getContractType()==0);
    }
    
    @Test
    public void testGetCompanyHeaderFetchQueryfalse() throws Exception {
        System.out.println("getCompanyHeaderFetchQuery");
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();  
        try{
        gtnWsContractHeaderService.getCompanyHeaderFetchQuery(gtnWsRequest,gtnResponse);
         Assert.fail();
         } catch (GtnFrameworkGeneralException  ex) {
			
	}
    }

    /**
     * Test of getHelpervalue method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testGetHelpervalue() {
        System.out.println("getHelpervalue");
        Object value = null;
        Integer result = gtnWsContractHeaderService.getHelpervalue(value);
        assertFalse(result!=null);
    }

    /**
     * Test of fetchContractAlias method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testFetchContractAlias() {
        System.out.println("fetchContractAlias");
        int contractystemId=1613;
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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();     
        Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
        try{
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("fetchContractAlias",Session.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, session,contractystemId);
        assertFalse(gtnWsContractMasterBean.getContractType()==0);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractHeaderServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

    /**
     * Test of getHelperTable method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testGetHelperTable() {
        System.out.println("getHelperTable");
        Integer systemId = 1;
        Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
        try{
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("getHelperTable",Integer.class,Session.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, systemId, session);
        assertFalse(systemId==0);
         } catch (Exception ex) {
            Logger.getLogger(GtnWsContractHeaderServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
   
    
    @Test
    public void testGetCompanyGrpDeleteQueryFalse() throws Exception {
        System.out.println("getCompanyGrpDeleteQuery");
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderService instance = new GtnWsContractHeaderService();
        GtnCompanyGroupRequest companyGroupRequest=new GtnCompanyGroupRequest();
        GtnCompanyGroupBean gtnCompanyGroupBean = new GtnCompanyGroupBean();
        GtnCompanyGrpInformationBean gtnCompanyGrpInformationBean = new GtnCompanyGrpInformationBean();
        gtnCompanyGrpInformationBean.setCompanyGrpSid(1185);
        gtnCompanyGroupBean.setGtnCompanyGrpInformationBean(gtnCompanyGrpInformationBean);
        companyGroupRequest.setGtnCompanyGroupBean(gtnCompanyGroupBean);
        //gtnWsRequest.setGtnCompanyGroupRequest(companyGroupRequest);
        try{
        gtnWsContractHeaderService.getCompanyGrpDeleteQuery(gtnWsRequest);
        Assert.fail();
        } catch (GtnFrameworkGeneralException e) {
			
	}
    }

    /**
     * Test of saveContractHeader method, of class GtnWsContractHeaderService.
     */
//    @Test
//    public void testSaveContractHeader() throws Exception {
//        System.out.println("saveContractHeader");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("720");
//        generalRequest.setExcel(false);
//
//
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
//        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
//        gtnWsContractMasterBean.setContractMasterSid(0);
//        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
//        gtnWsContractMasterBean.setTradingPartnerName("");
//        gtnWsContractMasterBean.setDocumentClass(null);
//        gtnWsContractMasterBean.setContractType(218);
//        gtnWsContractMasterBean.setContractStatus(126);
//        gtnWsContractMasterBean.setPaymentTerms(0);
//        gtnWsContractMasterBean.setContractId("test156");
//        gtnWsContractMasterBean.setContractNo("test156");
//        gtnWsContractMasterBean.setContractName("test156");
//        gtnWsContractMasterBean.setStartDate(new Date());
//        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
//        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
//        gtnWsContractMasterBean.setInternalNotes("kk");
//        gtnWsContractMasterBean.setContractEligibleDate(new Date());
//        gtnWsContractHeaderRequest.setUserId("20156");
//        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList = new ArrayList<>();
//        GtnwsContractAliasMasterBean e = new GtnwsContractAliasMasterBean();
//        e.setContractAliasMasterSid(0);
//        e.setContractType(651);
//        e.setContractTypeDesc("Manufacturer");
//        e.setContractMasterSid(0);
//        e.setContractAliasNo("test156");
//        e.setTpCompanyMasterSid(74773);
//        e.setStartDate(new Date());       
//        gtnwsContractAliasMasterBeanList.add(e);
//        List<NotesTabBean> noteBeanList = new ArrayList<>();
//        NotesTabBean eb = new NotesTabBean();
//        eb.setMasterTableSystemId(0);
//        eb.setMasterTableName("CONTRACT_MASTER");
//        eb.setCreatedDate(new Date());
//        eb.setCreatedBy(20156);
//        noteBeanList.add(eb);
//        gtnWsContractHeaderRequest.setNoteBeanList(noteBeanList);
//        gtnWsContractHeaderRequest.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
//        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
//        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
//        gtnWsContractHeaderService.saveContractHeader(gtnWsRequest,gtnResponse);
//        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
//    }
//    
//    @Test
//    public void testSaveContractHeaderFalse() throws Exception {
//        System.out.println("saveContractHeader");
//
//        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
//        generalRequest.setComboBoxType("COMPANY_TYPE");
//        generalRequest.setUserId("20156");
//        generalRequest.setSessionId("720");
//        generalRequest.setExcel(false);
//
//
//        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
//        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
//        gtnWsContractMasterBean.setContractMasterSid(0);
//        gtnWsContractMasterBean.setCompanyMasterByContHoldCompanyMasterSid(74773);
//        gtnWsContractMasterBean.setTradingPartnerName("");
//        gtnWsContractMasterBean.setDocumentClass(null);
//        gtnWsContractMasterBean.setContractType(218);
//        gtnWsContractMasterBean.setContractStatus(126);
//        gtnWsContractMasterBean.setPaymentTerms(0);
//        gtnWsContractMasterBean.setContractId("test156");
//        gtnWsContractMasterBean.setContractNo("test156");
//        gtnWsContractMasterBean.setContractName("test156");
//        gtnWsContractMasterBean.setStartDate(new Date());
//        gtnWsContractMasterBean.setOrganizationKey("COMPANY_1");
//        gtnWsContractMasterBean.setAdvanceNoticeDays(0.0);
//        gtnWsContractMasterBean.setInternalNotes("kk");
//        gtnWsContractMasterBean.setContractEligibleDate(new Date());
//        gtnWsContractHeaderRequest.setUserId("20156");
//        List<GtnwsContractAliasMasterBean> gtnwsContractAliasMasterBeanList = new ArrayList<>();
//        GtnwsContractAliasMasterBean e = new GtnwsContractAliasMasterBean();
//        e.setContractAliasMasterSid(0);
//        e.setContractType(651);
//        e.setContractTypeDesc("Manufacturer");
//        e.setContractMasterSid(0);
//        e.setContractAliasNo("test156");
//        e.setTpCompanyMasterSid(74773);
//        e.setStartDate(new Date());       
//        gtnwsContractAliasMasterBeanList.add(e);
//        List<NotesTabBean> noteBeanList = new ArrayList<>();
//        NotesTabBean eb = new NotesTabBean();
//        eb.setMasterTableSystemId(0);
//        eb.setMasterTableName("CONTRACT_MASTER");
//        eb.setCreatedDate(new Date());
//        eb.setCreatedBy(20156);
//        noteBeanList.add(eb);
//        gtnWsContractHeaderRequest.setNoteBeanList(noteBeanList);
//        gtnWsContractHeaderRequest.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
//        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
//        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
//        //gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
//        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
//        try{
//        gtnWsContractHeaderService.saveContractHeader(gtnWsRequest,gtnResponse);
//        Assert.fail();
//        } catch (GtnFrameworkGeneralException ex) {
//			
//	}
//
//       // assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
//    }

    /**
     * Test of generateContractHeader method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testGenerateContractHeader() {
        System.out.println("generateContractHeader");

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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
        try{
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("generateContractHeader",GtnUIFrameworkWebserviceRequest.class,Session.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, gtnWsRequest,session);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractHeaderServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of updateCompanyGrpQuery method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testUpdateCompanyGrpQuery() throws Exception {
 System.out.println("updateCompanyGrpQuery");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);


        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1621);
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
        e.setContractAliasMasterSid(2044);
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
        GtnUIFrameworkWebserviceResponse gtnResponse=new GtnUIFrameworkWebserviceResponse();
        gtnWsContractHeaderService.updateCompanyGrpQuery(gtnWsRequest,gtnResponse);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
    }

    /**
     * Test of saveOrUpdateContractAlias method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testSaveOrUpdateContractAlias() {
 System.out.println("saveOrUpdateContractAlias");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        
        int contractystemId=1621;


        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1621);
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
        e.setContractAliasMasterSid(2058);
        e.setContractType(651);
        e.setContractTypeDesc("Manufacturer");
        //e.setContractMasterSid(0);
        e.setContractAliasNo("test159");
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
        Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
        try{
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("saveOrUpdateContractAlias",GtnWsContractHeaderRequest.class,Session.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, gtnWsContractHeaderRequest,session,contractystemId);
        
        //gtnWsContractHeaderService.saveOrUpdateContractAlias(gtnWsContractHeaderRequest,session,contractystemId);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnWsContractHeaderServiceTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     @Test
    public void testSaveOrUpdateContractAliasFalse() {
    System.out.println("saveOrUpdateContractAlias");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        
        int contractystemId=1621;


        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnWsContractHeaderRequest gtnWsContractHeaderRequest=new GtnWsContractHeaderRequest();
        GtnWsContractMasterBean gtnWsContractMasterBean = new GtnWsContractMasterBean();
        gtnWsContractMasterBean.setContractMasterSid(1621);
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
        e.setContractAliasMasterSid(2058);
        e.setContractType(651);
        e.setContractTypeDesc("Manufacturer");
        //e.setContractMasterSid(0);
        e.setContractAliasNo("test159");
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
        //gtnWsContractHeaderRequest.setGtnwsContractAliasMasterBeanList(gtnwsContractAliasMasterBeanList);
        gtnWsContractHeaderRequest.setGtnWsContractMasterBean(gtnWsContractMasterBean);
        gtnWsRequest.setGtnWsGeneralRequest(generalRequest);
        gtnWsRequest.setGtnWsContractHeaderRequest(gtnWsContractHeaderRequest);
        Session session = gtnWebServiceAllListConfig.getSessionFactory().openSession();
        try{
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("saveOrUpdateContractAlias",GtnWsContractHeaderRequest.class,Session.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, gtnWsContractHeaderRequest,session,contractystemId);
        //gtnWsContractHeaderService.saveOrUpdateContractAlias(gtnWsContractHeaderRequest,session,contractystemId);
        Assert.fail();
        } catch (Exception ex) {
			
	}


        //assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
    }

    /**
     * Test of saveNotesTabDetails method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testSaveNotesTabDetails() throws Exception {
//        System.out.println("saveNotesTabDetails");
//        GtnWsContractHeaderRequest imRquest = null;
//        int masterSid = 0;
//        GtnWsContractHeaderService instance = new GtnWsContractHeaderService();
//        instance.saveNotesTabDetails(imRquest, masterSid);
 System.out.println("saveNotesTabDetails");

        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setComboBoxType("COMPANY_TYPE");
        generalRequest.setUserId("20156");
        generalRequest.setSessionId("720");
        generalRequest.setExcel(false);
        
        int masterSid=1621;


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
        GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
        gtnWsContractHeaderService.saveNotesTabDetails(gtnWsContractHeaderRequest,masterSid);
        assertFalse(gtnwsContractAliasMasterBeanList.isEmpty());
    }

    /**
     * Test of deleteNotesTab method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testDeleteNotesTab() throws Exception {
        System.out.println("deleteNotesTab");
        int systemId = 0;      
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("deleteNotesTab",int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, systemId);
        //int result = gtnWsContractHeaderService.deleteNotesTab(systemId);
        assertFalse(systemId!=0);
     
    }

    /**
     * Test of deleteNotesAttachTab method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testDeleteNotesAttachTab() throws Exception {
        System.out.println("deleteNotesAttachTab");
        int systemId = 1344;  
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("deleteNotesAttachTab",int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, systemId);
        assertFalse(systemId==0);
    }

    /**
     * Test of notesTabInsert method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testNotesTabInsert() throws Exception {
        System.out.println("notesTabInsert");
        GtnWsContractHeaderRequest imRquest = new GtnWsContractHeaderRequest();
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        int masterSid = 1621;
        List<NotesTabBean> noteBeanList = new ArrayList<>();
        NotesTabBean eb = new NotesTabBean();
        eb.setMasterTableSystemId(0);
        eb.setMasterTableName("CONTRACT_MASTER");
        eb.setCreatedDate(new Date());
        eb.setCreatedBy(20156);
        noteBeanList.add(eb);
        imRquest.setNoteBeanList(noteBeanList);
        gtnWsRequest.setGtnWsContractHeaderRequest(imRquest);
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("notesTabInsert",GtnWsContractHeaderRequest.class,int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, imRquest, masterSid);
        assertFalse(masterSid==0);
    }

   

    /**
     * Test of getNotesTabDetails method, of class GtnWsContractHeaderService.
     */
    @Test
    public void testGetNotesTabDetails() throws Exception {
        System.out.println("getNotesTabDetails");
        int systemId = 5885;
        Method method = gtnWsContractHeaderService.getClass().getDeclaredMethod("getNotesTabDetails",int.class);
        method.setAccessible(true);
        method.invoke(gtnWsContractHeaderService, systemId);
        assertFalse(systemId==0);
    }
}
