/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
/**
 *
 * @author Karthikeyan.Subraman
 */
import org.junit.Ignore;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@Ignore
public class GtnWsCfpAddCotrollerIT {

	public GtnWsCfpAddCotrollerIT() {
		// constructor
	}

	@BeforeClass
	public static void setUpClass() {
		return;
	}

	@AfterClass
	public static void tearDownClass() {
		return;
	}

	@Before
	public void setUp() {
		return;
	}

	@After
	public void tearDown() {
		return;
	}

	/**
	 * Test of getSessionFactory method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testGetSessionFactory() {
		System.out.println("getSessionFactory");
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		SessionFactory expResult = null;
		SessionFactory result = instance.getSessionFactory();
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of setSessionFactory method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testSetSessionFactory() {
		System.out.println("setSessionFactory");
		SessionFactory sessionFactory = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		instance.setSessionFactory(sessionFactory);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companyAdditionSearch method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompanyAdditionSearch() {
		System.out.println("companyAdditionSearch");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.cfpSearch(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companyAdditionMoveRight method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompanyAdditionMoveRight() {
		System.out.println("companyAdditionMoveRight");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.cfoCompanyAdditionMoveRight(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companyAdditionMoveLeft method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompanyAdditionMoveLeft() {
		System.out.println("companyAdditionMoveLeft");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.cfpCompanyAdditionMoveLeft(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companyAdditionMoveAllLeft method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompanyAdditionMoveAllLeft() {
		System.out.println("companyAdditionMoveAllLeft");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.companyAdditionMoveAllLeft(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companyAdditionMoveAllRight method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompanyAdditionMoveAllRight() {
		System.out.println("companyAdditionMoveAllRight");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.companyAdditionMoveAllRight(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companyAdditionRightTableSearch method, of class
	 * GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompanyAdditionRightTableSearch() {
		System.out.println("companyAdditionRightTableSearch");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.companyAdditionRightTableSearch(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companiesResultTableData method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompaniesResultTableData() {
		System.out.println("companiesResultTableData");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.cfpCompaniesResultTableData(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of companiesColumnUpdate method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testCompaniesColumnUpdate() {
		System.out.println("companiesColumnUpdate");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.companiesColumnUpdate(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of fetchCfpInformation method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testFetchCfpInformation() throws Exception {
		System.out.println("fetchCfpInformation");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.fetchCfpInformation(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCfpCompaniesTabDelete method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testGetCfpCompaniesTabDelete() throws Exception {
		System.out.println("getCfpCompaniesTabDelete");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.getCfpCompaniesTabDelete(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

	/**
	 * Test of getCfpDelete method, of class GtnWsCfpAddCotroller.
	 */
	@Test
	public void testGetCfpDelete() throws Exception {
		System.out.println("getCfpDelete");
		GtnUIFrameworkWebserviceRequest gtnWsRequest = null;
		GtnWsCfpAddCotroller instance = new GtnWsCfpAddCotroller();
		GtnUIFrameworkWebserviceResponse expResult = null;
		GtnUIFrameworkWebserviceResponse result = instance.getCfpDelete(gtnWsRequest);
		assertEquals(expResult, result);
		// TODO review the generated test code and remove the default call to
		// fail.
		fail("The test case is a prototype.");
	}

}
