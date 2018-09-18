package com.stpl.gtn.gtn2o.ws.module.itemaster.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemPricing;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemQualifier;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsValidationBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.internal.InFlightMetadataCollectorImpl;
import org.hibernate.boot.internal.MetadataBuilderImpl;
import org.hibernate.boot.internal.SessionFactoryBuilderImpl;
import org.hibernate.boot.internal.SessionFactoryOptionsImpl;
import org.hibernate.boot.internal.SessionFactoryOptionsState;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.spi.MetadataBuildingOptions;
import org.hibernate.boot.spi.MetadataImplementor;
import org.hibernate.boot.spi.SessionFactoryOptions;
import org.hibernate.engine.spi.SessionFactoryDelegatingImpl;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.type.TypeResolver;
import org.junit.*;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import static org.mockito.Mockito.doReturn;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * The class <code>GtnWsItemMasterValidationServiceTest</code> contains tests
 * for the class <code>{@link GtnWsItemMasterValidationService}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsItemMasterValidationServiceTest {

    @Mock
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    @InjectMocks
    @Autowired
    GtnWsItemMasterValidationService fixture;
    @Mock
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    /**
     * Run the GtnWsItemMasterValidationService() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnWsItemMasterValidationService_1()
            throws Exception {

        GtnWsItemMasterValidationService result = new GtnWsItemMasterValidationService();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getSessionFactory());
    }

    @Test
    public void testCheckIdentifierExist_1()
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
            GtnWsValidationBean val = new GtnWsValidationBean();

            Session session = Mockito.mock(Session.class);
            ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
            doReturn(session).when(sessionFactory).openSession();
            doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
            doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

            List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList = new ArrayList<>();
            GtnWsItemIdentifierBean idenBean = new GtnWsItemIdentifierBean();
            gtnWsItemIdentifierBeanList.add(idenBean);
            val.setGtnWsItemMasterIdentifierBeanList(gtnWsItemIdentifierBeanList);
            gtnWsItemMasterRequest.setGtnWsValidationBean(val);
            gtnWsItemMasterBean.setGtnWsItemIdentifierBeanList(gtnWsItemIdentifierBeanList);

            ItemQualifier iq = Mockito.mock(ItemQualifier.class);
            doReturn(iq).when(session).load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());

            fixture.checkIdentifierExist(gtnWsRequest, response);
        } catch (Exception e) {

        }

    }

    /**
     * Run the void
     * checkItemIdentifierQualfierValueExist(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckItemIdentifierQualfierValueExist_1()
            throws Exception {
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();

        gtnWsItemMasterRequest.setUserId("10");

        GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
        GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
        gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
        gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

        gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
        GtnWsValidationBean val = new GtnWsValidationBean();

        Session session = Mockito.mock(Session.class);
        ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
        doReturn(session).when(sessionFactory).openSession();
        doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
        doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

        GtnWsItemQualifierBean idenBean = new GtnWsItemQualifierBean();
        val.setGtnWsItemQualifierBean(idenBean);
        gtnWsItemMasterRequest.setGtnWsValidationBean(val);

        fixture.checkItemIdentifierQualfierValueExist(gtnWsRequest, response);

    }

    /**
     * Run the void
     * checkItemIdentifierQualfierValueUsed(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckItemIdentifierQualfierValueUsed_1()
            throws Exception {
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();

        gtnWsItemMasterRequest.setUserId("10");

        GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
        GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
        gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
        gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

        gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
        GtnWsValidationBean val = new GtnWsValidationBean();

        Session session = Mockito.mock(Session.class);
        ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
        doReturn(session).when(sessionFactory).openSession();
        doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
        doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

        GtnWsItemQualifierBean idenBean = new GtnWsItemQualifierBean();
        val.setGtnWsItemQualifierBean(idenBean);
        gtnWsItemMasterRequest.setGtnWsValidationBean(val);

        Criteria itemIdentifierCriteria = Mockito.mock(Criteria.class);
        doReturn(itemIdentifierCriteria).when(session).createCriteria(ItemIdentifier.class, "ID");

        Criteria itemQualifierCriteria = Mockito.mock(Criteria.class);
        doReturn(itemQualifierCriteria).when(itemIdentifierCriteria).createCriteria("itemQualifier", "IQ");

        fixture.checkItemIdentifierQualfierValueUsed(gtnWsRequest, response);

    }

    /**
     * Run the void
     * checkItemMasterIdNameNdc8Exist(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckItemMasterIdNameNdc8Exist_1()
            throws Exception {
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();

        gtnWsItemMasterRequest.setUserId("10");

        GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
        GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
        gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
        gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

        gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
        GtnWsValidationBean val = new GtnWsValidationBean();

        Session session = Mockito.mock(Session.class);
        ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
        doReturn(session).when(sessionFactory).openSession();
        doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
        doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

        GtnWsItemPricingQualifierBean idenBean = new GtnWsItemPricingQualifierBean();
        val.setGtnWsItemPricingQualifierBean(idenBean);
        gtnWsItemMasterRequest.setGtnWsValidationBean(val);

        fixture.checkItemMasterIdNameNdc8Exist(gtnWsRequest, response);
    }
//

    /**
     * Run the void
     * checkItemPricingQualifierValueExist(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckItemPricingQualifierValueExist_1()
            throws Exception {
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();

        gtnWsItemMasterRequest.setUserId("10");

        GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
        GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
        gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
        gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

        gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
        GtnWsValidationBean val = new GtnWsValidationBean();

        Session session = Mockito.mock(Session.class);
        ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
        doReturn(session).when(sessionFactory).openSession();
        doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
        doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

        GtnWsItemPricingQualifierBean idenBean = new GtnWsItemPricingQualifierBean();
        val.setGtnWsItemPricingQualifierBean(idenBean);
        gtnWsItemMasterRequest.setGtnWsValidationBean(val);
        fixture.checkItemPricingQualifierValueExist(gtnWsRequest, response);

    }
//

    /**
     * Run the void
     * checkPricingIdentifierQualifierValueUsed(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckPricingIdentifierQualifierValueUsed_1()
            throws Exception {
        GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
        GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
        GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();

        gtnWsItemMasterRequest.setUserId("10");

        GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
        GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
        gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
        gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

        gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);
        GtnWsValidationBean val = new GtnWsValidationBean();

        Session session = Mockito.mock(Session.class);
        ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
        doReturn(session).when(sessionFactory).openSession();
        doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
        doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

        GtnWsItemPricingQualifierBean idenBean = new GtnWsItemPricingQualifierBean();
        val.setGtnWsItemPricingQualifierBean(idenBean);
        gtnWsItemMasterRequest.setGtnWsValidationBean(val);

        Criteria itemIdentifierCriteria = Mockito.mock(Criteria.class);
        doReturn(itemIdentifierCriteria).when(session).createCriteria(ItemPricing.class, "PD");

        Criteria itemQualifierCriteria = Mockito.mock(Criteria.class);
        doReturn(itemQualifierCriteria).when(itemIdentifierCriteria).createCriteria("itemPricingQualifier", "PQ");

        fixture.checkPricingIdentifierQualifierValueUsed(gtnWsRequest, response);

    }

//
//
//	/**
//	 * Run the SessionFactory getSessionFactory() method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testGetSessionFactory_1()
//		throws Exception {
//		GtnWsItemMasterValidationService fixture = new GtnWsItemMasterValidationService();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//
//		SessionFactory result = fixture.getSessionFactory();
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
//		assertNotNull(result);
//	}
//
//	/**
//	 * Run the void setSessionFactory(SessionFactory) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testSetSessionFactory_1()
//		throws Exception {
//		GtnWsItemMasterValidationService fixture = new GtnWsItemMasterValidationService();
//		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
//		SessionFactory sessionFactory = new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver())))));
//
//		fixture.setSessionFactory(sessionFactory);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
//	}
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
        // add additional set up code here
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
