package com.stpl.gtn.gtn2o.ws.automaticrelation;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkDeductionAutoUpdateServiceImplTest {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService service;

	@Autowired
	@Qualifier("Deduction")
	GtnFrameworkAutoupdateService automaticService;

	@Test
	public void checkForAutoUpdate() {
		try {
			GtnWsRelationshipBuilderBean relationBean = service.getRelationtionshipBuilder(743);
		List<HierarchyLevelDefinitionBean> hierarchyDefinitionList;
		System.setProperty("com.stpl.gtnframework.base.path", "D:/SriThAr/Allergan/Server");
			hierarchyDefinitionList = service.getHierarchyBuilder(relationBean.getHierarchyDefinitionSid(),
					relationBean.getHierarchyVersion());
			automaticService.checkForAutoUpdate(relationBean, hierarchyDefinitionList);
		} catch (Exception e) {
			
			e.printStackTrace();
		}

	}
}
