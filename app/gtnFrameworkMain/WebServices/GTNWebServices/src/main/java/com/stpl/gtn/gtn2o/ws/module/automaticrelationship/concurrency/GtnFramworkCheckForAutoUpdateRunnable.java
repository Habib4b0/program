package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.concurrency.GtnFramworkCustProdCheckForAutoUpdateQueryGenerator;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFramworkCheckForAutoUpdateRunnable implements Callable<String> {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;
	@Autowired
	private GtnFramworkCustProdCheckForAutoUpdateQueryGenerator gtnCustProdQueryGenerator;

	private GtnWsRelationshipBuilderBean relationBean;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;
	private int index;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFramworkCheckForAutoUpdateRunnable.class);

	public GtnFramworkCheckForAutoUpdateRunnable() {
		super();
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}
	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}

	public void setGtnSqlQueryEngine(GtnFrameworkSqlQueryEngine gtnSqlQueryEngine) {
		this.gtnSqlQueryEngine = gtnSqlQueryEngine;
	}

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	public void setGtnFrameworkEntityMasterBean(GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean) {
		this.gtnFrameworkEntityMasterBean = gtnFrameworkEntityMasterBean;
	}
	public GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean() {
		return gtnFrameworkEntityMasterBean;
	}

	public void setGtnHierarchyServiceBuilder(GtnFrameworkHierarchyService gtnHierarchyServiceBuilder) {
		this.gtnHierarchyServiceBuilder = gtnHierarchyServiceBuilder;
	}


	public GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder() {
		return gtnHierarchyServiceBuilder;
	}


	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyLevelDefinitionList() {
		return Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}

	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}


	@Override
	public String call() throws Exception {
		try {
			HierarchyLevelDefinitionBean currnetHierarchyLevelBean = hierarchyLevelDefinitionList.get(index);

			HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
					.getPreviousLinkedLevel(hierarchyLevelDefinitionList, currnetHierarchyLevelBean);
			List<Object> inputs = new ArrayList<>();
			inputs.add(relationBean.getRelationshipBuilderSid());
			inputs.add(previousHierarchyLevelBean.getLevelNo());
			inputs.add(relationBean.getVersionNo());
			inputs.add(currnetHierarchyLevelBean.getLevelNo());
			inputs.add(relationBean.getVersionNo());
			List<Object> inputsForFinalQuery = null;
			GtnFrameworkQueryGeneratorBean hierarchyQuery = gtnCustProdQueryGenerator
					.getFinalCheckQuery(currnetHierarchyLevelBean,
							previousHierarchyLevelBean, hierarchyLevelDefinitionList);
			String query = gtnWsSqlService.getReplacedQuery(inputs, hierarchyQuery.generateQuery());
			inputsForFinalQuery = new ArrayList<>();
			inputsForFinalQuery.add(relationBean.getRelationshipBuilderSid());
			inputsForFinalQuery.add(currnetHierarchyLevelBean.getLevelNo());
			inputsForFinalQuery.add(relationBean.getVersionNo());
			inputsForFinalQuery.add(query);
			return gtnWsSqlService.getQuery(inputsForFinalQuery, "checkForUpdateInAutomaticRelation");

		} catch (Exception e) {
			LOGGER.error(" Error " + e.getMessage());
		}
		return "";
	}
}
