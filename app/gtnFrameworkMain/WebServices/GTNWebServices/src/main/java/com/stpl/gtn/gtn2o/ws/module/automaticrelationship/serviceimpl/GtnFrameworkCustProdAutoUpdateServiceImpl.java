package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFramworkCheckForAutoUpdateRunnable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkJoinQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.GtnFrameworkQueryGeneraterServiceImpl;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Component("CustService")
@Scope(value = "singleton")
public class GtnFrameworkCustProdAutoUpdateServiceImpl implements GtnFrameworkAutoupdateService {

	@Autowired
	GtnFrameworkAutomaticRelationUpdateService automaticService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	@Qualifier("CustProdSelect")
	GtnFrameworkSelectQueryGeneratorService selectService;
	@Autowired
	@Qualifier("CustProdWhere")
	GtnFrameworkWhereQueryGeneratorService whereService;
	@Autowired
	@Qualifier("CustProdJoin")
	private GtnFrameworkJoinQueryGeneratorService joinService;

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkAutomaticRelation(int relationshipBuilderSid) throws GtnFrameworkGeneralException {
		String query = gtnWsSqlService.getQuery("automaticRelationCheck");
		List<Integer> resultData = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(query,
				new Object[] { relationshipBuilderSid }, new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		return (int) resultData.get(0) == 1;
	}

	@Override
	public boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) throws InterruptedException {

		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		final ExecutorService executorService = Executors
				.newFixedThreadPool(hierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);

		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
			GtnFramworkCheckForAutoUpdateRunnable runnableTarget = applicationContext
					.getBean(GtnFramworkCheckForAutoUpdateRunnable.class);
			runnableTarget.setAtomicBoolean(atomicBoolean);
			runnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
			runnableTarget.setIndex(i);
			runnableTarget.setRelationBean(relationBean);
			executorService.submit(runnableTarget);
		}
		executorService.shutdown();
		executorService.awaitTermination(5, TimeUnit.MINUTES);
		return atomicBoolean.get();
	}

	@Override
	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, String userId) throws GtnFrameworkGeneralException {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		int updatedVersionNo = automaticService.insertRelationTillFirstLevelAndGetVersionNo(firstLinkedLevelNo,
				relationBean, userId);
		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
			HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(i);
			GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
					hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
					hierarchyLevelBean.getVersionNo());
			HierarchyLevelDefinitionBean previousHierarchyLevelBean = i > 0 ? hierarchyLevelDefinitionList.get(i - 1)
					: null;
			GtnFrameworkQueryGeneratorBean querygeneratorBean = hierarchyQuery.getQuery();
			GtnFrameworkQueryGeneraterServiceImpl queryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
					selectService, joinService, whereService);
			queryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
					updatedVersionNo, userId, i);
			List<String> whereQueryList = automaticService.getRelationQueries(relationBean.getRelationshipBuilderSid(),
					updatedVersionNo,
					hierarchyLevelDefinitionList.subList(0, i).toArray(new HierarchyLevelDefinitionBean[i]));
			List<Object> inputs = new ArrayList<>();
			inputs.add(relationBean.getRelationshipBuilderSid());
			inputs.add(previousHierarchyLevelBean == null ? "" : previousHierarchyLevelBean.getLevelNo());
			inputs.add(updatedVersionNo);
			inputs.addAll(whereQueryList);
			hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
			String finalQuery = gtnWsSqlService.getReplacedQuery(inputs, querygeneratorBean.generateQuery());
			List<String> insertQueryInput = new ArrayList<>();
			insertQueryInput.add(finalQuery);
			String finalInsertQuery = gtnWsSqlService.getQuery(insertQueryInput,
					"relationShipSubQueryToInsertAutomaticData");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
		}

	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}

}
