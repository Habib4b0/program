package com.stpl.gtn.gtn2o.ws.automaticrelation;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.custprod.GtnFrameworkAutomaticCustProdJoinServiceImpl;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnFrameworkAutomaticCustProdJoinServiceImplTest {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService service;
	@Autowired
	@Qualifier("CustProdJoin")
	GtnFrameworkAutomaticCustProdJoinServiceImpl automaticService;
	private static final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkAutomaticCustProdJoinServiceImplTest.class);

	@Test
	public void getHierarchyNo() {
		try {
			GtnWsRelationshipBuilderBean relationBean = service.getRelationtionshipBuilder(386);
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = service
					.getHierarchyBuilder(relationBean.getHierarchyDefinitionSid(), relationBean.getHierarchyVersion());
				System.out.println(
					automaticService.getHierarchyNo(hierarchyLevelDefinitionList, hierarchyLevelDefinitionList.get(3)));
		} catch (GtnFrameworkGeneralException e) {
			logger.error(e.getMessage());
		}
	}
}
