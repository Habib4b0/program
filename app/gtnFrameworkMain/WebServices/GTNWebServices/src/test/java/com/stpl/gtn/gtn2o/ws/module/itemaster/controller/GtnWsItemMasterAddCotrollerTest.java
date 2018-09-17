package com.stpl.gtn.gtn2o.ws.module.itemaster.controller;

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
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

/**
 * The class <code>GtnWsItemMasterAddCotrollerTest</code> contains tests for the class <code>{@link GtnWsItemMasterAddCotroller}</code>.
 *
 * @generatedBy CodePro at 9/7/18 2:54 PM
 * @author Karthik.Raja
 * @version $Revision: 1.0 $
 */
@Ignore 
public class GtnWsItemMasterAddCotrollerTest {
	/**
	 * Run the GtnWsItemMasterAddCotroller() constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testGtnWsItemMasterAddCotroller_1()
		throws Exception {

		GtnWsItemMasterAddCotroller result = new GtnWsItemMasterAddCotroller();

		// add additional test code here
		assertNotNull(result);
		assertEquals(null, result.getSessionFactory());
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse createTempTable(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testCreateTempTable_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.createTempTable(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse createTempTable(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testCreateTempTable_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.createTempTable(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deleteItemMaster(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeleteItemMaster_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deleteItemMaster(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deleteItemMaster(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeleteItemMaster_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deleteItemMaster(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deleteItemQualifier(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeleteItemQualifier_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deleteItemQualifier(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deleteItemQualifier(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeleteItemQualifier_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deleteItemQualifier(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deletePricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeletePricingData_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deletePricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deletePricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeletePricingData_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deletePricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deletePricingQualifier(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeletePricingQualifier_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deletePricingQualifier(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse deletePricingQualifier(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDeletePricingQualifier_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.deletePricingQualifier(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse dropTempTable(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDropTempTable_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.dropTempTable(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse dropTempTable(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testDropTempTable_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.dropTempTable(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse fetchBrandMasterInfo(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testFetchBrandMasterInfo_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.fetchBrandMasterInfo(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse fetchBrandMasterInfo(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testFetchBrandMasterInfo_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.fetchBrandMasterInfo(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse fetchCompanyInfo(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testFetchCompanyInfo_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.fetchCompanyInfo(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse fetchCompanyInfo(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testFetchCompanyInfo_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.fetchCompanyInfo(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse fetchItemMasterInfo(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testFetchItemMasterInfo_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.fetchItemMasterInfo(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse fetchItemMasterInfo(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testFetchItemMasterInfo_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.fetchItemMasterInfo(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the SessionFactory getSessionFactory() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testGetSessionFactory_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));

		SessionFactory result = fixture.getSessionFactory();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse insertPricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testInsertPricingData_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.insertPricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse insertPricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testInsertPricingData_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.insertPricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse loadItemPricing(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testLoadItemPricing_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.loadItemPricing(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse loadItemPricing(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testLoadItemPricing_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.loadItemPricing(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse loadItemPricing(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testLoadItemPricing_3()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.loadItemPricing(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse loadItemPricing(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testLoadItemPricing_4()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.loadItemPricing(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the void setSessionFactory(SessionFactory) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testSetSessionFactory_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		SessionFactory sessionFactory = new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver())))));

		fixture.setSessionFactory(sessionFactory);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse updatePricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testUpdatePricingData_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.updatePricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse updatePricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testUpdatePricingData_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.updatePricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse updatePricingData(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testUpdatePricingData_3()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.updatePricingData(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse validatePricingDate(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testValidatePricingDate_1()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.validatePricingDate(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Run the GtnUIFrameworkWebserviceResponse validatePricingDate(GtnUIFrameworkWebserviceRequest) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Test
	public void testValidatePricingDate_2()
		throws Exception {
		GtnWsItemMasterAddCotroller fixture = new GtnWsItemMasterAddCotroller();
		fixture.setSessionFactory(new SessionFactoryDelegatingImpl(new SessionFactoryImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()), new SessionFactoryOptionsImpl(new SessionFactoryBuilderImpl(new InFlightMetadataCollectorImpl(new org.hibernate.boot.internal.MetadataBuilderImpl.MetadataBuildingOptionsImpl((StandardServiceRegistry) null), new TypeResolver()))))));
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();

		GtnUIFrameworkWebserviceResponse result = fixture.validatePricingDate(gtnWsRequest);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at org.hibernate.boot.internal.MetadataBuilderImpl$MetadataBuildingOptionsImpl.<init>(MetadataBuilderImpl.java:579)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 9/7/18 2:54 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GtnWsItemMasterAddCotrollerTest.class);
	}
}