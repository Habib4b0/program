package com.stpl.gtn.gtn2o.ws.automaticrelation;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkCustProdAutoUpdateServiceImplTest {
	@Autowired
	GtnFrameworkAutomaticRelationUpdateService service;

	@Autowired
	@Qualifier("CustService")
	GtnFrameworkAutoupdateService automaticService;


	private static final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkCustProdAutoUpdateServiceImplTest.class);

	@Test
	public void checkForAutoUpdate() throws InterruptedException {
		GtnWsRelationshipBuilderBean relationBean = service.getRelationtionshipBuilder(769);
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList;
		try {
			System.setProperty("gtn.app.data.path", "D:/SriThAr/Allergan/Server");
			hierarchyDefinitionList = service.getHierarchyBuilder(relationBean.getHierarchyDefinitionSid(),
					relationBean.getHierarchyVersion());
			automaticService.checkForAutoUpdate(relationBean, hierarchyDefinitionList);
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}

	}

	@Test
	public void doAutomaticUpdate() throws GtnFrameworkGeneralException {
		try {
		System.setProperty("com.stpl.gtnframework.base.path", "D:/SriThAr/Allergan/Server");
			GtnWsRelationshipBuilderBean relationBean = service.getRelationtionshipBuilder(768);
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = service
				.getHierarchyBuilder(relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
			automaticService.doAutomaticUpdate(hierarchyLevelDefinitionList, relationBean);
		}
		catch(Exception e)
		{
			logger.error(e.getMessage());
		}
	}

	@Test
	public void checkAndUpdateAutomaticRelationship() throws GtnFrameworkGeneralException {
		try {
			System.setProperty("com.stpl.gtnframework.base.path", "D:/SriThAr/Allergan/Server");
			service.checkAndUpdateAutomaticRelationship(769);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}


}
