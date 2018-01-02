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
	private GtnFrameworkAutomaticRelationUpdateService automaticService;

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
	private GtnFrameworkSelectQueryGeneratorService selectService;
	@Autowired
	@Qualifier("CustProdWhere")
	private GtnFrameworkWhereQueryGeneratorService whereService;
	@Autowired
	@Qualifier("CustProdJoin")
	private GtnFrameworkJoinQueryGeneratorService joinService;

	public GtnFrameworkCustProdAutoUpdateServiceImpl() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public boolean checkAutomaticRelation(int relationshipBuilderSid) throws GtnFrameworkGeneralException {
		String customerQuery = gtnWsSqlService.getQuery("automaticRelationCheck");
		List<Integer> productResultData = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(customerQuery,
				new Object[] { relationshipBuilderSid }, new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		return (int) productResultData.get(0) == 1;
	}

	@Override
	public boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) throws InterruptedException {

		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		final ExecutorService customerExecutorService = Executors
				.newFixedThreadPool(hierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
		AtomicBoolean atomicBoolean = new AtomicBoolean(false);

		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
			if (hierarchyLevelDefinitionList.get(i).isUserDefined())
				continue;
			GtnFramworkCheckForAutoUpdateRunnable customerRunnableTarget = applicationContext
					.getBean(GtnFramworkCheckForAutoUpdateRunnable.class);
			customerRunnableTarget.setAtomicBoolean(atomicBoolean);
			customerRunnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
			customerRunnableTarget.setIndex(i);
			customerRunnableTarget.setRelationBean(relationBean);
			customerExecutorService.submit(customerRunnableTarget);
		}
		customerExecutorService.shutdown();
		customerExecutorService.awaitTermination(5, TimeUnit.MINUTES);
		return atomicBoolean.get();
	}

	@Override
	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean, String userId) throws GtnFrameworkGeneralException {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		int customertUpdatedVersionNo = automaticService.insertRelationTillFirstLevelAndGetVersionNo(firstLinkedLevelNo,
				relationBean, userId);
		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
			HierarchyLevelDefinitionBean customerHierarchyLevelBean = hierarchyLevelDefinitionList.get(i);
			if (customerHierarchyLevelBean.isUserDefined()) {
				checkAndInserUserDefinedLevels(relationBean, customerHierarchyLevelBean);
				continue;
			}
			GtnFrameworkHierarchyQueryBean customerHierarchyQuery = fileService.getQueryFromFile(
					customerHierarchyLevelBean.getHierarchyDefinitionSid(),
					customerHierarchyLevelBean.getHierarchyLevelDefinitionSid(),
					customerHierarchyLevelBean.getVersionNo());
			HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
					.getPreviousLinkedLevel(hierarchyLevelDefinitionList, customerHierarchyLevelBean);
			GtnFrameworkQueryGeneratorBean querygeneratorBean = customerHierarchyQuery.getQuery();
			GtnFrameworkQueryGeneraterServiceImpl customerQueryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
					selectService, joinService, whereService);
			customerQueryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
					customertUpdatedVersionNo, userId, i);
			List<String> whereQueryList = automaticService.getRelationQueries(relationBean.getRelationshipBuilderSid(),
					customertUpdatedVersionNo,
					hierarchyLevelDefinitionList.subList(0, i).toArray(new HierarchyLevelDefinitionBean[i]));
			List<Object> inputsList = new ArrayList<>();
			inputsList.add(relationBean.getRelationshipBuilderSid());
			inputsList.add(previousHierarchyLevelBean.getLevelNo());
			inputsList.add(customertUpdatedVersionNo);
			inputsList.add(customerHierarchyLevelBean.getLevelNo());
			inputsList.add(customertUpdatedVersionNo - 1);
			inputsList.addAll(whereQueryList);
			hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
			String finalQuery = gtnWsSqlService.getReplacedQuery(inputsList, querygeneratorBean.generateQuery());
			List<String> insertQueryInput = new ArrayList<>();
			insertQueryInput.add(finalQuery);
			String finalInsertQuery = gtnWsSqlService.getQuery(insertQueryInput,
					"relationShipSubQueryToInsertAutomaticData");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
		}

	}

	private void checkAndInserUserDefinedLevels(GtnWsRelationshipBuilderBean relationBean,
			HierarchyLevelDefinitionBean customerHierarchyLevelBean) throws GtnFrameworkGeneralException {
		List<Object> inputList = new ArrayList<>();

		inputList.add(customerHierarchyLevelBean.getHierarchyLevelDefinitionSid());
		inputList.add(customerHierarchyLevelBean.getDefaultVlaue());
		inputList.add(customerHierarchyLevelBean.getLevelNo());
		inputList.add(customerHierarchyLevelBean.getLevelName());
		inputList.add(customerHierarchyLevelBean.getDefaultVlaue());
		inputList.add(customerHierarchyLevelBean.getDefaultVlaue());
		inputList.add(customerHierarchyLevelBean.getLevelNo() - 1);
		inputList.add(relationBean.getVersionNo() + 1);
		inputList.add(relationBean.getRelationshipBuilderSid());

		inputList.add(customerHierarchyLevelBean.getLevelNo());
		inputList.add(customerHierarchyLevelBean.getLevelName());
		inputList.add(customerHierarchyLevelBean.getLevelNo() - 1);
		inputList.add(customerHierarchyLevelBean.getLevelNo());
		inputList.add(relationBean.getVersionNo());
		inputList.add(relationBean.getVersionNo() + 1);
		inputList.add(relationBean.getRelationshipBuilderSid());

		String finalInsertQuery = gtnWsSqlService.getQuery(inputList, "RelationInsertForIntermediate userDefined");
		gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}

    @Override
    public void createDeductionRelation(GtnWsRelationshipBuilderBean relationBean) throws GtnFrameworkGeneralException {
        // Overriden method
    }

}
