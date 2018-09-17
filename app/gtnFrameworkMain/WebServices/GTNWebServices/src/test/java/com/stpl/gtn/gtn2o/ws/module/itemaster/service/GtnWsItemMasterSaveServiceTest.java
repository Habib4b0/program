package com.stpl.gtn.gtn2o.ws.module.itemaster.service;

import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemIdentifier;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemMaster;
import com.stpl.gtn.gtn2o.ws.entity.itemmaster.ItemQualifier;
import com.stpl.gtn.gtn2o.ws.entity.priceshedule.ItemPricingQualifier;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemIdentifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemPricingQualifierBean;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsItemQualifierBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.itemmaster.GtnWsItemMasterRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
 * The class <code>GtnWsItemMasterSaveServiceTest</code> contains tests for the
 * class <code>{@link GtnWsItemMasterSaveService}</code>.
 *
 *
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/test/resources/AutomaticContext.xml"})
public class GtnWsItemMasterSaveServiceTest {

    @Mock
    @Autowired
    private org.hibernate.SessionFactory sessionFactory;

    @InjectMocks
    @Autowired
    GtnWsItemMasterSaveService fixture;

    /**
     * Run the GtnWsItemMasterSaveService() constructor test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGtnWsItemMasterSaveService_1()
            throws Exception {

        GtnWsItemMasterSaveService result = new GtnWsItemMasterSaveService();

        // add additional test code here
        assertNotNull(result);
        assertEquals(null, result.getSessionFactory());
    }

    /**
     * Run the void checkAndUpdateAllrelationShip() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testCheckAndUpdateAllrelationShip_1()
            throws Exception {
        GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();

        fixture.checkAndUpdateAllrelationShip();

        // add additional test code here
    }

    /**
     * Run the BigDecimal getBigDecimalValue(Double) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetBigDecimalValue_1()
            throws Exception {
        GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();
        Double value = Double.valueOf(1.0);

        BigDecimal result = fixture.getBigDecimalValue(value);

        // add additional test code here
        assertNotNull(result);
        assertEquals("1", result.toString());
        assertEquals(1, result.intValue());
        assertEquals(1L, result.longValue());
        assertEquals(1.0f, result.floatValue(), 1.0f);
        assertEquals(1.0, result.doubleValue(), 1.0);
        assertEquals(1, result.signum());
        assertEquals(0, result.scale());
        assertEquals((byte) 1, result.byteValueExact());
        assertEquals(1, result.intValueExact());
        assertEquals(1L, result.longValueExact());
        assertEquals((short) 1, result.shortValueExact());
        assertEquals(1, result.precision());
        assertEquals("1", result.toEngineeringString());
        assertEquals("1", result.toPlainString());
        assertEquals((byte) 1, result.byteValue());
        assertEquals((short) 1, result.shortValue());
    }

    /**
     * Run the BigDecimal getBigDecimalValue(Double) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetBigDecimalValue_2()
            throws Exception {
        GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();
        Double value = null;

        BigDecimal result = fixture.getBigDecimalValue(value);

        // add additional test code here
        assertEquals(null, result);
    }

    /**
     * Run the BigDecimal getBigDecimalValue(Integer) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetBigDecimalValue_3()
            throws Exception {
        GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();
        Integer value = new Integer(1);

        BigDecimal result = fixture.getBigDecimalValue(value);

        // add additional test code here
        assertNotNull(result);
        assertEquals("1", result.toString());
        assertEquals(1, result.intValue());
        assertEquals(1L, result.longValue());
        assertEquals(1.0f, result.floatValue(), 1.0f);
        assertEquals(1.0, result.doubleValue(), 1.0);
        assertEquals(1, result.signum());
        assertEquals(0, result.scale());
        assertEquals((byte) 1, result.byteValueExact());
        assertEquals(1, result.intValueExact());
        assertEquals(1L, result.longValueExact());
        assertEquals((short) 1, result.shortValueExact());
        assertEquals(1, result.precision());
        assertEquals("1", result.toEngineeringString());
        assertEquals("1", result.toPlainString());
        assertEquals((byte) 1, result.byteValue());
        assertEquals((short) 1, result.shortValue());
    }

    /**
     * Run the BigDecimal getBigDecimalValue(Integer) method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetBigDecimalValue_4()
            throws Exception {
        GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();
        Integer value = null;

        BigDecimal result = fixture.getBigDecimalValue(value);

        // add additional test code here
        assertNotNull(result);
        assertEquals("0", result.toString());
        assertEquals(0, result.intValue());
        assertEquals(0L, result.longValue());
        assertEquals(0.0f, result.floatValue(), 1.0f);
        assertEquals(0.0, result.doubleValue(), 1.0);
        assertEquals(0, result.signum());
        assertEquals(0, result.scale());
        assertEquals((byte) 0, result.byteValueExact());
        assertEquals(0, result.intValueExact());
        assertEquals(0L, result.longValueExact());
        assertEquals((short) 0, result.shortValueExact());
        assertEquals(1, result.precision());
        assertEquals("0", result.toEngineeringString());
        assertEquals("0", result.toPlainString());
        assertEquals((byte) 0, result.byteValue());
        assertEquals((short) 0, result.shortValue());
    }

    /**
     * Run the SessionFactory getSessionFactory() method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testGetSessionFactory_1()
            throws Exception {
        GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();

        SessionFactory result = fixture.getSessionFactory();

        // add additional test code here
        assertEquals(null, result);
    }

    /**
     * Run the Integer
     * saveItemMaster(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveItemMaster_1()
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
            doReturn(session).when(sessionFactory).openSession();
            doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
            doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

            List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList = new ArrayList<>();
            GtnWsItemIdentifierBean idenBean = new GtnWsItemIdentifierBean();
            gtnWsItemIdentifierBeanList.add(idenBean);
            gtnWsItemMasterBean.setGtnWsItemIdentifierBeanList(gtnWsItemIdentifierBeanList);

            ItemQualifier iq = Mockito.mock(ItemQualifier.class);
            doReturn(iq).when(session).load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());

            Integer result = fixture.saveItemMaster(gtnWsRequest, response);
        } catch (Exception e) {

        }
    }

    /**
     * Run the int
     * saveItemMasterIndetifierQualifier(GtnUIFrameworkWebserviceRequest) method
     * test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testSaveItemMasterIndetifierQualifier_1()
            throws Exception {
        try {
            GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
            gtnWsItemMasterRequest.setUserId("10");

            GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
            GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
            gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
            gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

            gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);

            Session session = Mockito.mock(Session.class);
            ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
            doReturn(session).when(sessionFactory).openSession();
            doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
            doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

            GtnWsItemQualifierBean qualifierBean = new GtnWsItemQualifierBean();
            gtnWsItemMasterRequest.setGtnWsItemQualifierBean(qualifierBean);

            List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList = new ArrayList<>();
            GtnWsItemIdentifierBean idenBean = new GtnWsItemIdentifierBean();
            idenBean.setItemIdentifierSid(1);
            gtnWsItemIdentifierBeanList.add(idenBean);
            gtnWsItemMasterBean.setGtnWsItemIdentifierBeanList(gtnWsItemIdentifierBeanList);

            ItemQualifier iq = Mockito.mock(ItemQualifier.class);
            doReturn(iq).when(session).load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());

            int result = fixture.saveItemMasterIndetifierQualifier(gtnWsRequest);

            assertEquals(0, result);
        } catch (Exception e) {

        }
    }

	

	/**
	 * Run the int saveItemMasterPricingQualifier(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * 
	 */
	@Test
	public void testSaveItemMasterPricingQualifier_1()
		throws Exception {
            try{
	         GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
            GtnWsItemMasterRequest gtnWsItemMasterRequest = new GtnWsItemMasterRequest();
            gtnWsItemMasterRequest.setUserId("10");

            GtnWsItemMasterBean gtnWsItemMasterBean = new GtnWsItemMasterBean();
            GtnWsItemMasterInfoBean gtnWsItemMasterInfoBean = new GtnWsItemMasterInfoBean();
            gtnWsItemMasterBean.setGtnWsItemMasterInfoBean(gtnWsItemMasterInfoBean);
            gtnWsItemMasterRequest.setGtnWsItemMasterBean(gtnWsItemMasterBean);

            gtnWsRequest.setGtnWsItemMasterRequest(gtnWsItemMasterRequest);

            Session session = Mockito.mock(Session.class);
            ItemMaster itemMasterEditData = Mockito.mock(ItemMaster.class);
            doReturn(session).when(sessionFactory).openSession();
            doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
            doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

            GtnWsItemPricingQualifierBean qualifierBean = new GtnWsItemPricingQualifierBean();
            gtnWsItemMasterRequest.setGtnWsItemPricingQualifierBean(qualifierBean);

            List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList = new ArrayList<>();
            GtnWsItemIdentifierBean idenBean = new GtnWsItemIdentifierBean();
            idenBean.setItemIdentifierSid(1);
            gtnWsItemIdentifierBeanList.add(idenBean);
            gtnWsItemMasterBean.setGtnWsItemIdentifierBeanList(gtnWsItemIdentifierBeanList);

            ItemQualifier iq = Mockito.mock(ItemQualifier.class);
            doReturn(iq).when(session).load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());
		int result = fixture.saveItemMasterPricingQualifier(gtnWsRequest);
                  } catch (Exception e) {

        }

	}
//
//	/**
//	 * Run the void saveNotesTabDetails(GtnWsItemMasterBean) method test.
//	 *
//	 * @throws Exception
//	 *
//	 * 
//	 */
//	@Test
//	public void testSaveNotesTabDetails_1()
//		throws Exception {
//		GtnWsItemMasterSaveService fixture = new GtnWsItemMasterSaveService();
//		GtnWsItemMasterBean ruleInfoBean = new GtnWsItemMasterBean();
//
//		fixture.saveNotesTabDetails(ruleInfoBean);
//
//		// add additional test code here
//		// An unexpected exception was thrown in user code while executing this test:
//		//    java.lang.NullPointerException
//		//       at com.stpl.gtn.gtn2o.ws.module.itemaster.service.GtnWsItemMasterSaveService.saveNotesTabDetails(GtnWsItemMasterSaveService.java:207)
//	}
//
//	
//

    /**
     * Run the void
     * updateItemMaster(GtnUIFrameworkWebserviceRequest,GtnUIFrameworkWebserviceResponse)
     * method test.
     *
     * @throws Exception
     *
     *
     */
    @Test
    public void testUpdateItemMaster_1()
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
            doReturn(session).when(sessionFactory).openSession();
            doReturn(itemMasterEditData).when(session).get(ItemMaster.class, gtnWsItemMasterInfoBean.getItemMasterSid());
            doReturn(1).when(session).save(Mockito.any(ItemIdentifier.class));

            List<GtnWsItemIdentifierBean> gtnWsItemIdentifierBeanList = new ArrayList<>();
            GtnWsItemIdentifierBean idenBean = new GtnWsItemIdentifierBean();
            idenBean.setItemIdentifierSid(1);
            gtnWsItemIdentifierBeanList.add(idenBean);
            gtnWsItemMasterBean.setGtnWsItemIdentifierBeanList(gtnWsItemIdentifierBeanList);

            ItemQualifier iq = Mockito.mock(ItemQualifier.class);
            doReturn(iq).when(session).load(ItemQualifier.class, idenBean.getItemQualifierMasterSid());

            fixture.updateItemMaster(gtnWsRequest, response);
        } catch (Exception e) {

        }

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
