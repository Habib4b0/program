package com.stpl.gtn.gtn2o.ws.module.itemaster.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doReturn;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.HelperTable;
import com.stpl.gtn.gtn2o.ws.entity.companymaster.CompanyMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.BrandMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemQualifier;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 * The class <code>GtnWsItemMasterAddServiceTest</code> contains tests for the
 * class <code>{@link GtnWsItemMasterAddService}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsItemMasterAddServiceTest {

    @Mock
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    @InjectMocks
    @Autowired
    GtnWsItemMasterAddService fixture;

    /**
     * Run the GtnWsItemMasterAddService() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnWsItemMasterAddService_1()
            throws Exception {

        GtnWsItemMasterAddService result = new GtnWsItemMasterAddService();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getGtnWsSqlService());
        assertEquals(null, result.getSysSessionFactory());
    }

    /**
     * Run the boolean checkIfItemExist(List<Long>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckIfItemExist_1()
            throws Exception {

        List<Long> results = new LinkedList();

        boolean result = fixture.checkIfItemExist(results);

        assertFalse(result);
    }

    /**
     * Run the boolean checkIfItemExist(List<Long>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckIfItemExist_2()
            throws Exception {

        List<Long> results = null;

        boolean result = fixture.checkIfItemExist(results);

        assertFalse(result);
    }

    /**
     * Run the boolean checkIfItemExist(List<Long>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckIfItemExist_3()
            throws Exception {

        List<Long> results = new LinkedList();

        boolean result = fixture.checkIfItemExist(results);

        assertFalse(result);
    }

    /**
     * Run the boolean checkIfItemExist(List<Long>) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckIfItemExist_4()
            throws Exception {
        List<Long> results = new LinkedList();
        results.add(1L);
        boolean result = fixture.checkIfItemExist(results);

        assertTrue(result);
    }

    /**
     * Run the Double getDoublevalue(BigDecimal) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetDoublevalue_1()
            throws Exception {

        BigDecimal value = new BigDecimal(1.0);

        Double result = fixture.getDoublevalue(value);

        assertTrue(value.doubleValue() == result);
    }

    /**
     * Run the Double getDoublevalue(BigDecimal) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetDoublevalue_2()
            throws Exception {
        BigDecimal value = null;

        Double result = fixture.getDoublevalue(value);
        assertTrue(result == null);
    }

    /**
     * Run the GtnWsSqlService getGtnWsSqlService() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetGtnWsSqlService_1()
            throws Exception {

        GtnWsSqlService result = fixture.getGtnWsSqlService();

        assertNotNull(result);
    }

    /**
     * Run the Integer getHelpervalue(Object) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHelpervalue_1()
            throws Exception {

        HelperTable value = new HelperTable();
        value.setHelperTableSid(10);

        Integer result = fixture.getHelpervalue(value);
        assertTrue(result == 10);
    }

    /**
     * Run the Integer getHelpervalue(Object) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetHelpervalue_2()
            throws Exception {
        Object value = null;

        Integer result = fixture.getHelpervalue(value);
        assertTrue(result == 0);
    }


    /**
     * Run the SessionFactory getSysSessionFactory() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSysSessionFactory_1()
            throws Exception {
        SessionFactory result = fixture.getSysSessionFactory();
        assertNotNull(result);
    }

    /**
     * Run the void setGtnWsSqlService(GtnWsSqlService) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetGtnWsSqlService_1()
            throws Exception {
        GtnWsItemMasterAddService instance = new GtnWsItemMasterAddService();
        GtnWsSqlService gtnWsSqlService = Mockito.mock(GtnWsSqlService.class);

        instance.setGtnWsSqlService(gtnWsSqlService);
        assertEquals(gtnWsSqlService, instance.getGtnWsSqlService());
    }

    /**
     * Run the void setSysSessionFactory(SessionFactory) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSetSysSessionFactory_1()
            throws Exception {
        GtnWsItemMasterAddService instance = new GtnWsItemMasterAddService();
        SessionFactory factory = Mockito.mock(SessionFactory.class);

        instance.setSysSessionFactory(factory);
        assertEquals(factory, instance.getSysSessionFactory());

    }

    /**
     * Run the void
     * deleteItemMaster(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDeleteItemMaster_1()
            throws Exception {
        try {
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
            GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
            GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
            GtnWsItemMasterInfoBean bean = new GtnWsItemMasterInfoBean();
            gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
            gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);
            gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(bean);
            bean.setItemMasterSid(Integer.MAX_VALUE);
            
             GtnWsItemMasterAddService in = Mockito.spy(fixture);
            SessionFactory sess = Mockito.mock(SessionFactory.class);
            Session session = Mockito.mock(Session.class);
            doReturn(session).when(sess).openSession();
            doNothing().when(session).delete(Mockito.any());
            doReturn(sess).when(in).getSessionFactory();
            ItemMaster itemMaster =Mockito.mock(ItemMaster.class);
            doReturn(itemMaster).when(session).get(ItemMaster.class,Integer.MAX_VALUE);
            
            in.deleteItemMaster(gtnWsRequest, gtnResponse);
        } catch (Exception e) {

        }
    }

    /**
     * Run the void deleteItemQualifier(GtnUIFrameworkWebserviceRequest) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDeleteItemQualifier_1()
            throws Exception {
        try {
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
            GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
            GtnWsItemQualifierBean bean = new GtnWsItemQualifierBean();
            gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
            gtnWsItemMasterRequest.setGtnWsItemQualifierBean(bean);
            bean.setItemQualifierSid(Integer.MAX_VALUE);

            GtnWsItemMasterAddService in = Mockito.spy(fixture);
            SessionFactory sess = Mockito.mock(SessionFactory.class);
            Session session = Mockito.mock(Session.class);
            doReturn(session).when(sess).openSession();
            doNothing().when(session).delete(Mockito.any());
            doReturn(sess).when(in).getSessionFactory();
            
                     ItemQualifier itemMaster =Mockito.mock(ItemQualifier.class);
            doReturn(itemMaster).when(session).get(ItemQualifier.class,Integer.MAX_VALUE);

            in.deleteItemQualifier(gtnWsRequest);
        } catch (Exception e) {

        }

    }

    /**
     * Run the void deletePricingQualifier(GtnUIFrameworkWebserviceRequest)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDeletePricingQualifier_1()
            throws Exception {
        try {
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
            GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
            GtnWsItemPricingQualifierBean bean = new GtnWsItemPricingQualifierBean();
            gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
            gtnWsItemMasterRequest.setGtnWsItemPricingQualifierBean(bean);
            bean.setItemPricingQualifierSid(Integer.MAX_VALUE);

            GtnWsItemMasterAddService in = Mockito.spy(fixture);
            SessionFactory sess = Mockito.mock(SessionFactory.class);
            Session session = Mockito.mock(Session.class);
            doReturn(session).when(sess).openSession();
            doNothing().when(session).delete(Mockito.any());
            doReturn(sess).when(in).getSessionFactory();

            in.deletePricingQualifier(gtnWsRequest);
        } catch (Exception e) {

        }
    }
@Test
public void fetchBrandMasterInfoTest() throws GtnFrameworkGeneralException {
    GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
    GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
    GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
    GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
    GtnWsBrandMasterBean bean = new GtnWsBrandMasterBean();
    gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
    gtnWsItemMasterRequest.setGtnWsBrandMasterBean	(bean);
    bean.setBrandMasterSid(Integer.MAX_VALUE);

    GtnWsItemMasterAddService in = Mockito.spy(fixture);
    SessionFactory sess = Mockito.mock(SessionFactory.class);
    Session session = Mockito.mock(Session.class);
    Transaction tr = Mockito.mock(Transaction.class);
    doReturn(session).when(sess).openSession();
    doReturn(tr).when(session).beginTransaction();
    doNothing().when(session).delete(Mockito.any());
    doReturn(sess).when(in).getSessionFactory();
    
    BrandMaster itemMaster =Mockito.mock(BrandMaster.class);
    doReturn(itemMaster).when(session).get(BrandMaster.class,Integer.MAX_VALUE);
    
    in.fetchBrandMasterInfo(gtnWsRequest, gtnResponse);
}
@Test
public void fetchCompanyMasterInfoTest() throws GtnFrameworkGeneralException {
    GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
    GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
    GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
    GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
    GtnWsItemMasterInfoBean bean = new GtnWsItemMasterInfoBean();
    gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
    gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);
    gtnWsItemMasterBean.setGtnWsItemMasterInfoBean	(bean);
    bean.setCompanyMasterSid(Integer.MAX_VALUE);

    GtnWsItemMasterAddService in = Mockito.spy(fixture);
    SessionFactory sess = Mockito.mock(SessionFactory.class);
    Session session = Mockito.mock(Session.class);
    Transaction tr = Mockito.mock(Transaction.class);
    doReturn(session).when(sess).openSession();
    doReturn(tr).when(session).beginTransaction();
    doNothing().when(session).delete(Mockito.any());
    doReturn(sess).when(in).getSessionFactory();
    
    CompanyMaster itemMaster =Mockito.mock(CompanyMaster.class);
    doReturn(itemMaster).when(session).get(CompanyMaster.class,Integer.MAX_VALUE);
    
    in.fetchCompanyInfo(gtnWsRequest, gtnResponse);
}
    /**
     * Run the void dropPricingTempTable(GtnUIFrameworkWebserviceRequest) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testDropPricingTempTable_1()
            throws Exception {
        try {
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsGeneralRequest gtnWsGeneralRequest = new GtnWsGeneralRequest();
            gtnWsRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
            fixture.dropPricingTempTable(gtnWsRequest);
        } catch (Exception e) {

        }
    }

    /**
     * Run the void
     * fetchItemMaster(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testFetchItemMaster_1()
            throws Exception {
        try {
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
            GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
            gtnWsItemMasterRequest.setUserId("10");

            GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
            GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
            gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
            gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

            gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);

            Session session = Mockito.mock(Session.class);
            ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
            BrandMaster brandMaster = Mockito.mock(BrandMaster.class);
            CompanyMaster companyMaster = Mockito.mock(CompanyMaster.class);
            doReturn(brandMaster).when(itemMasterEditData).getBrandMaster();
            doReturn(companyMaster).when(itemMasterEditData).getCompanyMaster();

            doReturn(itemMasterEditData).when(session).get(ItemMaster.class, 0);
            doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

            List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList = new ArrayList<>();
            GtnWsItemIdentifierBean idenBean = new GtnWsItemIdentifierBean();
            gtnWsItemIdentifierBeanList.add(idenBean);
            gtnWsItemMasterBean.setGtnWsItemIdentifierBeanList(gtnWsItemIdentifierBeanList);

            ItemQualifier iq = Mockito.mock(ItemQualifier.class);
            doReturn(iq).when(session).load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());
            SessionFactory factory = Mockito.mock(SessionFactory.class);
            doReturn(factory).when(gtnSqlQueryEngine).getSessionFactory();
            doReturn(session).when(factory).openSession();

            fixture.fetchItemMaster(gtnWsRequest, response);
        } catch (Exception e) {

        }
    }
    
    @Test 
    public void getPricingColumnMapTest() {
    	fixture.getPricingColumnMap();
    	
    }
    
    @Test
    public void insertInPricingTembTableTest() {
    	
    }

//	
//	/**
//	 * Run the void loadPricingTempData(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testLoadPricingTempData_1()
//		throws Exception {
//		
//		fixture.setSysSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		fixture.setGtnWsSqlService((GtnWsSqlService) null);
//		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
//		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
//
//		fixture.loadPricingTempData(gtnWsRequest, gtnResponse);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
//	}
//
//
	@Test
	public void testUpdateData_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		 GtnWsItemMasterRequest gtnWsItemMasterRequest=new  GtnWsItemMasterRequest();
		 GtnWsItemMasterBean gtnWsItemMasterBean=new GtnWsItemMasterBean();
		 GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean=new  GtnWsItemMasterInfoBean();
		 gtnWsItemMasterInfoBean.setPopulateField("modifiedDate");
		 gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
		 gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
		 gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);
			GtnWsGeneralRequest re=new GtnWsGeneralRequest();
			gtnWsRequest.setGtnWsGeneralRequest(re);
			 gtnWsItemMasterInfoBean.setPopulateIdentityId("1");
		fixture.updateData(gtnWsRequest);
		 gtnWsItemMasterInfoBean.setPopulateField("itemPrice");
		
			fixture.updateData(gtnWsRequest);
		 gtnWsItemMasterInfoBean.setPopulateField("modifiedBy");
		 gtnWsItemMasterInfoBean.setPopulateValue("1");
			fixture.updateData(gtnWsRequest);

	}
	/**
	 * Run the void validatePricingData(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testValidatePricingData_1()
		throws Exception {
		
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest re=new GtnWsGeneralRequest();
		gtnWsRequest.setGtnWsGeneralRequest(re);
		GtnUIFrameworkWebserviceResponse gtnResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsItemMasterAddService fix= Mockito.spy(fixture);
        doReturn(1).when(fix).getCountBasedOnCountQuery(Mockito.contains("ITEM_PRICE"));
        fix.validatePricingData(gtnWsRequest, gtnResponse);
        doReturn(1).when(fix).getCountBasedOnCountQuery(Mockito.contains("PRICING_CODE_STATUS"));
        doReturn(0).when(fix).getCountBasedOnCountQuery(Mockito.contains("ITEM_PRICE"));
        fix.validatePricingData(gtnWsRequest, gtnResponse);
        doReturn(0).when(fix).getCountBasedOnCountQuery(Mockito.contains("PRICING_CODE_STATUS"));
        doReturn(0).when(fix).getCountBasedOnCountQuery(Mockito.contains("ITEM_PRICE"));
        doReturn(1).when(fix).getCountBasedOnCountQuery(Mockito.contains("ITEM_UOM"));
        fix.validatePricingData(gtnWsRequest, gtnResponse);
        doReturn(0).when(fix).getCountBasedOnCountQuery(Mockito.contains("PRICING_CODE_STATUS"));
        doReturn(0).when(fix).getCountBasedOnCountQuery(Mockito.contains("ITEM_PRICE"));
        doReturn(0).when(fix).getCountBasedOnCountQuery(Mockito.contains("ITEM_UOM"));
        doReturn(1).when(fix).getCountBasedOnCountQuery(Mockito.contains("START_DATE"));
        fix.validatePricingData(gtnWsRequest, gtnResponse);

	}
    /**
     * Perform pre-test initialization.
     *
     * @throws Exception if the initialization fails for some reason
     *
     *
     */
    @Before
    public void setUp()
            throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    /**
     * Perform post-test clean-up.
     *
     * @throws Exception if the clean-up fails for some reason
     *
     *
     */
    @After
    public void tearDown()
            throws Exception {
        // Add additional tear down code here
    }

}
