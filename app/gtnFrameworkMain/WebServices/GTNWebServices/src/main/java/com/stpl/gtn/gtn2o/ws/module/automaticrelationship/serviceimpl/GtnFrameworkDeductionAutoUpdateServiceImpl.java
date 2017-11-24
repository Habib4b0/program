package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.GtnFrameworkQueryGeneraterServiceImpl;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.deduction.GtnFrameworkDeductionJoinServiceImpl;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Component("Deduction")
@Scope(value = "singleton")
public class GtnFrameworkDeductionAutoUpdateServiceImpl implements GtnFrameworkAutoupdateService {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService automaticService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
	@Autowired
	private GtnFrameworkDeductionJoinServiceImpl joinService;

	@Autowired
	@Qualifier("DeductionSelect")
	GtnFrameworkSelectQueryGeneratorService selectService;
	@Autowired
	@Qualifier("DeductionWhere")
	GtnFrameworkWhereQueryGeneratorService whereService;

	@Override
	public boolean checkAutomaticRelation(int relationshipBuilderSid) throws GtnFrameworkGeneralException {
		return Boolean.TRUE;
	}

	@Override
	public boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		return true;
	}

	@Override
	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, String userId) {
		try {
			GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
			int updatedVersionNo = relationBean.getVersionNo();
			for (int i = 0; i < hierarchyLevelDefinitionList.size(); i++) {
				HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(i);
				GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
						hierarchyLevelBean.getHierarchyDefinitionSid(),
						hierarchyLevelBean.getHierarchyLevelDefinitionSid(), hierarchyLevelBean.getVersionNo());
				GtnFrameworkQueryGeneratorBean querygeneratorBean = hierarchyQuery.getQuery();
				GtnFrameworkQueryGeneraterServiceImpl queryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
						selectService, joinService, whereService);
				queryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
						updatedVersionNo, userId, i);
				List<String> insertQueryInput = new ArrayList<>();
				insertQueryInput.add(querygeneratorBean.generateQuery());
				String finalInsertQuery = gtnWsSqlService.getQuery(insertQueryInput,
						"relationShipSubQueryToInsertAutomaticData");
				gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}

}
