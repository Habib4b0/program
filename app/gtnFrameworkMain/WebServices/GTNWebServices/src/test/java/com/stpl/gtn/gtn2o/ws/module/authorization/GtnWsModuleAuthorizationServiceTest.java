package com.stpl.gtn.gtn2o.ws.module.authorization;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleAuthorizationBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsModuleComponentBean;
import com.stpl.gtn.gtn2o.ws.authorization.bean.GtnWsTablePropertyBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.authorization.service.GtnWsModuleAuthorizationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.authorization.GtnWsModuleAuthorizationGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.authorization.GtnWsModuleAuthorizationGeneralResponse;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:GtnSecurityContext-servlet.xml" })
public class GtnWsModuleAuthorizationServiceTest {
	public GtnWsModuleAuthorizationServiceTest() {
		super();
	}

	@Autowired
	private GtnWsModuleAuthorizationService moduleSecurityService;

	@Test
	public void saveSecurityDetails() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
		List<GtnWsModuleAuthorizationBean> gtnSecurityBeanList = new ArrayList<>();
		GtnWsModuleAuthorizationBean gtnSecurityBean = new GtnWsModuleAuthorizationBean();
		GtnWsGeneralRequest gtnSecurityGeneralRequest = new GtnWsGeneralRequest();
		gtnSecurityBean.setGtnRoleMasterSid(1);
		gtnSecurityBean.setGtnModuleMasterSid(1);
		gtnSecurityBean.setGtnComponentSid(14);
		gtnSecurityBean.setIsEditable(true);
		gtnSecurityBean.setIsVisible(false);
		gtnSecurityBean.setIsTableProperty(true);
		gtnSecurityGeneralRequest.setUserId("13330");
		gtnSecurityBeanList.add(gtnSecurityBean);
		moduleSecurityGeneralRequest.setGtnWsModuleSecuritySaveBeanList(gtnSecurityBeanList);
		gtnWsRequest.setGtnWsModuleAuthorizationGeneralRequest(moduleSecurityGeneralRequest);
		gtnWsRequest.setGtnWsGeneralRequest(gtnSecurityGeneralRequest);
		try {
			moduleSecurityService.saveAuthorizationDetails(gtnWsRequest);
		} catch (GtnFrameworkGeneralException e) {
			Assert.fail("Exception while inserting records.");
		}

	}

	@Test
	public void getSecurityDetails() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		List<GtnWebServiceSearchCriteria> searchCriteria = new ArrayList<>();
		searchCriteria.add(new GtnWebServiceSearchCriteria());
		searchCriteria.add(new GtnWebServiceSearchCriteria());
		searchCriteria.get(0).setFilterValue1("1");
		searchCriteria.get(1).setFilterValue1("6");
		gtnWsRequest.setGtnWsSearchRequest(new GtnWsSearchRequest());
		gtnWsRequest.getGtnWsSearchRequest().setGtnWebServiceSearchCriteriaList(searchCriteria);
		gtnWsRequest.getGtnWsSearchRequest().setSearchQueryName("getGtnModuleComponentRoleDetailsQuery");
		try {
			List<Object[]> resultList = moduleSecurityService.getComponentAuthorizationDetails(gtnWsRequest);
			Assert.assertNotNull(resultList);
		} catch (GtnFrameworkGeneralException e) {
			Assert.fail("Exception while inserting records.");
		}

	}

	@Test
	public void getModuleWiseSecurityDetails() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralResponse moduleWiseSecurityResponse = new GtnWsModuleAuthorizationGeneralResponse();
		try {
			GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
			gtnWsRequest.setGtnWsModuleAuthorizationGeneralRequest(moduleSecurityGeneralRequest);
			GtnWsModuleAuthorizationBean modulewiseSecurityBean = new GtnWsModuleAuthorizationBean();
			modulewiseSecurityBean.setModuleName("Company Master");
			moduleSecurityGeneralRequest.setGtnWsModuleSecurityBean(modulewiseSecurityBean);
			moduleSecurityService.getModuleWiseAuthorizationDetails(gtnWsRequest, moduleWiseSecurityResponse);
			GtnWsModuleAuthorizationBean gtnWsModuleSecurityBean = moduleWiseSecurityResponse
					.getModuleWiseTablePropertyList(1);
			Assert.assertNotNull(gtnWsModuleSecurityBean);
		} catch (GtnFrameworkGeneralException e) {
			Assert.fail("Exception while inserting records.");
		}

	}

	@Test
	public void saveComponentDetails() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest moduleSecurityGeneralRequest = new GtnWsModuleAuthorizationGeneralRequest();
		List<GtnWsModuleComponentBean> gtnModuleComponentBeanList = new ArrayList<>();
		List<GtnWsTablePropertyBean> gtnWsTablePropertyBeanList = new ArrayList<>();
		GtnWsModuleComponentBean gtnModuleComponentBean = new GtnWsModuleComponentBean();
		gtnModuleComponentBean.setComponentId("TestCompanyMasterTextComponnet");
		gtnModuleComponentBean.setComponentDesc("Company Master Id");
		gtnModuleComponentBean.setComponentType("Text Field");
		gtnModuleComponentBean.setScreenName("Search Screen");
		gtnModuleComponentBean.setModuleName("Company Master");
		gtnModuleComponentBeanList.add(gtnModuleComponentBean);
		gtnModuleComponentBean = new GtnWsModuleComponentBean();
		gtnModuleComponentBean.setComponentId("TestCompanyMasterTableComponnet");
		gtnModuleComponentBean.setComponentDesc("Result Table Id");
		gtnModuleComponentBean.setComponentType("Table");
		gtnModuleComponentBean.setScreenName("Search Screen");
		gtnModuleComponentBean.setModuleName("Company Master");
		gtnModuleComponentBean.setTablePropertyList(gtnWsTablePropertyBeanList);
		gtnModuleComponentBeanList.add(gtnModuleComponentBean);

		GtnWsTablePropertyBean gtnTablePropertyBean = new GtnWsTablePropertyBean();
		gtnTablePropertyBean.setModuleName("Company Master");
		gtnTablePropertyBean.setSingleHeaderVisibleColumns("id,name");
		gtnTablePropertyBean.setSingleHeaderVisibleHeaders("Id ,Name");
		gtnTablePropertyBean.setDoubleHeaderVisibleColumns(null);
		gtnTablePropertyBean.setDoubleHeaderVisibleHeaders(null);

		GtnWsTablePropertyBean gtnTablePropertyBean1 = new GtnWsTablePropertyBean();
		gtnTablePropertyBean1.setModuleName("Company Master");
		gtnTablePropertyBean1.setSingleHeaderVisibleColumns("name");
		gtnTablePropertyBean1.setSingleHeaderVisibleHeaders("Name");
		gtnTablePropertyBean1.setDoubleHeaderVisibleColumns(null);
		gtnTablePropertyBean1.setDoubleHeaderVisibleHeaders(null);

		gtnWsTablePropertyBeanList.add(gtnTablePropertyBean1);
		gtnWsTablePropertyBeanList.add(gtnTablePropertyBean);

		moduleSecurityGeneralRequest.setGtnWsModuleComponentBeanList(gtnModuleComponentBeanList);
		gtnWsRequest.setGtnWsModuleAuthorizationGeneralRequest(moduleSecurityGeneralRequest);
		GtnWsGeneralRequest gtnSecurityGeneralRequest = new GtnWsGeneralRequest();
		gtnSecurityGeneralRequest.setUserId("10948");
		gtnWsRequest.setGtnWsGeneralRequest(gtnSecurityGeneralRequest);
		try {
			moduleSecurityService.saveComponentDetails(gtnWsRequest);
		} catch (GtnFrameworkGeneralException e) {
			Assert.fail("Exception while saveComponentDetails records.");
		}
	}

	@Test
	public void updateModuleFlagInModuleMaster() {
		GtnUIFrameworkWebserviceRequest gtnWsRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsModuleAuthorizationGeneralRequest moduleSubmoduleUpdateGenRequest = new GtnWsModuleAuthorizationGeneralRequest();
		GtnWsModuleAuthorizationBean updateModuleMasterBean = new GtnWsModuleAuthorizationBean();
		updateModuleMasterBean.setModuleSubModuleSid(1);
		updateModuleMasterBean.setUpdateModuleFlag(false);
		List<GtnWsModuleAuthorizationBean> beanList = new ArrayList<>();
		beanList.add(updateModuleMasterBean);
		moduleSubmoduleUpdateGenRequest.setGtnWsModuleSecuritySaveBeanList(beanList);
		gtnWsRequest.setGtnWsModuleAuthorizationGeneralRequest(moduleSubmoduleUpdateGenRequest);
		try {
			moduleSecurityService.updateModuleFlagInModuleMaster(gtnWsRequest);
		} catch (GtnFrameworkGeneralException e) {
			Assert.fail("Exception while updateModuleFlagInModuleMaster records.");
			e.printStackTrace();
		}
	}

}
