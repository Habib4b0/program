package com.stpl.gtn.gtn2o.ws.module.udc.controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.itemmaster.bean.GtnWsBrandMasterBean;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.udc.GtnWsUdcRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsBrandMasterInfoBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcBean;
import com.stpl.gtn.gtn2o.ws.udc.bean.GtnWsUdcInfoBean;


/**
*
* @author Praveen.Kumar
*/

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsUdcSaveControllerTest {

    @Mock
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@InjectMocks	
	@Autowired
	GtnWsUdcSaveController ins;
	
	@Test
	public void testGtnWsUdcSaveController() {
		
	}

	@Test
	public void testSaveUdc() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsUdcRequest udcWsRequest=new GtnWsUdcRequest();
		GtnWsUdcBean gtnWsUdcBean=new GtnWsUdcBean();
		gtnRequest.setGtnWsUdcRequest(udcWsRequest);
		udcWsRequest.setGtnWsUdcBean(gtnWsUdcBean);
		
		GtnUIFrameworkWebserviceResponse res=ins.saveUdc(gtnRequest);
		assertFalse(res==null);
	}

	@Test
	public void testDeleteUdc_1() {
		GtnUIFrameworkWebserviceRequest gtnRequest=new GtnUIFrameworkWebserviceRequest();
		
		GtnWsUdcRequest gtnWsUdcRequest=new GtnWsUdcRequest();
		GtnWsUdcInfoBean gtnWsUdcInfoBean=new GtnWsUdcInfoBean();
		GtnWsUdcBean gtnWsUdcBean=new GtnWsUdcBean();
		gtnRequest.setGtnWsUdcRequest(gtnWsUdcRequest);
		gtnWsUdcInfoBean.setHelperTableSid(1);
		gtnWsUdcInfoBean.setBrand(true);
		gtnWsUdcBean.setGtnWsUdcInfoBean(gtnWsUdcInfoBean);
		gtnWsUdcRequest.setGtnWsUdcBean(gtnWsUdcBean);
		GtnUIFrameworkWebserviceResponse res=ins.deleteUdc(gtnRequest);
		assertFalse(res==null);
	}

	
	@Test
	public void testDeleteUdc_2() {
		GtnUIFrameworkWebserviceRequest gtnRequest=new GtnUIFrameworkWebserviceRequest();
		
		GtnWsUdcRequest gtnWsUdcRequest=new GtnWsUdcRequest();
		GtnWsUdcInfoBean gtnWsUdcInfoBean=new GtnWsUdcInfoBean();
		GtnWsUdcBean gtnWsUdcBean=new GtnWsUdcBean();
		gtnRequest.setGtnWsUdcRequest(gtnWsUdcRequest);
		gtnWsUdcInfoBean.setHelperTableSid(1);
		gtnWsUdcInfoBean.setBrand(false);
		gtnWsUdcBean.setGtnWsUdcInfoBean(gtnWsUdcInfoBean);
		gtnWsUdcRequest.setGtnWsUdcBean(gtnWsUdcBean);
		GtnUIFrameworkWebserviceResponse res=ins.deleteUdc(gtnRequest);
		assertFalse(res==null);
	}
	@Test
	public void testSaveBrandMaster() throws Exception {
		GtnUIFrameworkWebserviceRequest gtnRequest=new GtnUIFrameworkWebserviceRequest();
		GtnWsUdcRequest gtnWsUdcRequest=new GtnWsUdcRequest();
		GtnWsBrandMasterInfoBean gtnWsBrandMasterInfoBean=new GtnWsBrandMasterInfoBean();
		GtnWsBrandMasterBean gtnWsBrandMasterBean=new GtnWsBrandMasterBean();
		gtnRequest.setGtnWsUdcRequest(gtnWsUdcRequest);
		gtnWsUdcRequest.setGtnWsBrandMasterInfoBean(gtnWsBrandMasterInfoBean);
		gtnWsBrandMasterInfoBean.setGtnWsBrandMasterBean(gtnWsBrandMasterBean);
		GtnUIFrameworkWebserviceResponse res=ins.saveBrandMaster(gtnRequest);
		
		assertFalse(res==null);
	}

}
