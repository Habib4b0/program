/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.controller;

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
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GTNRestController-servlet.xml" })

public class ComponentServiceTest {
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

	@Test
	public void retriveFromFile() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		System.out.println(gtnWsRequest);

	}
}
