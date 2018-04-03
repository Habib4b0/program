package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFrameworkDeductionRelationServiceRunnable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFramworkCheckForAutoUpdateRunnable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Component("ProdService")
@Scope(value = "singleton")
public class GtnFrameworkProductAutoUpdateServiceImpl implements GtnFrameworkAutoupdateService {

	@Autowired
	private ApplicationContext applicationContext;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService automaticService;

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	@Autowired
	private GtnFrameworkDeductionRelationServiceRunnable deductionRelationService;

	private static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkProductAutoUpdateServiceImpl.class);

	public GtnFrameworkProductAutoUpdateServiceImpl() {
		super();
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean checkAutomaticRelation(int relationshipBuilderSid) throws GtnFrameworkGeneralException {
		String finalQuery = gtnWsSqlService.getQuery("automaticRelationCheck");
		List<Integer> results = (List<Integer>) gtnSqlQueryEngine.executeSelectQuery(finalQuery,
				new Object[] { relationshipBuilderSid }, new GtnFrameworkDataType[] { GtnFrameworkDataType.INTEGER });
		return (int) results.get(0) == 1;
	}

	@Override
	public boolean checkForAutoUpdate(GtnWsRelationshipBuilderBean relationBean,
			List<HierarchyLevelDefinitionBean> productHierarchyLevelDefinitionList) throws InterruptedException {
		try {
			StringBuilder finalQuery = new StringBuilder();
			int firstLinkedLevelNo = HierarchyLevelDefinitionBean
					.getFirstLinkedLevel(productHierarchyLevelDefinitionList);
			final ExecutorService productExecutorService = Executors
					.newFixedThreadPool(productHierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
			List<Future<String>> productFutureList = new ArrayList<>();
			for (int i = firstLinkedLevelNo; i < productHierarchyLevelDefinitionList.size(); i++) {
				if (productHierarchyLevelDefinitionList.get(i).isUserDefined())
					continue;
				GtnFramworkCheckForAutoUpdateRunnable productRunnableTarget = applicationContext
						.getBean(GtnFramworkCheckForAutoUpdateRunnable.class);
				productRunnableTarget.setHierarchyLevelDefinitionList(productHierarchyLevelDefinitionList);
				productRunnableTarget.setIndex(i);
				productRunnableTarget.setRelationBean(relationBean);
				productFutureList.add(productExecutorService.submit(productRunnableTarget));
			}
			productExecutorService.shutdown();
			for (Future<String> queryFuture : productFutureList) {
				finalQuery.append(queryFuture.get());
			}
			List<Object> inputList = new ArrayList<>();
			inputList.add(relationBean.getRelationshipBuilderSid());
			inputList.add(relationBean.getVersionNo() + "," + (relationBean.getVersionNo() - 1));
			inputList.add(finalQuery.toString());
			String tempQuery = gtnWsSqlService.getQuery(inputList, "Check Relation to Update");
			List<?> result = gtnSqlQueryEngine.executeSelectQuery(tempQuery);
			return Integer.parseInt(result.get(0).toString()) > 0;
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return false;
	}

	@Override
	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean) throws GtnFrameworkGeneralException {
		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		int customertUpdatedVersionNo = automaticService.insertRelationTillFirstLevelAndGetVersionNo(firstLinkedLevelNo,
				relationBean);
		try {
			StringBuilder finalQuery = new StringBuilder();
			final ExecutorService customerExecutorService = Executors
					.newFixedThreadPool(hierarchyLevelDefinitionList.size());
			List<Future<String>> futureList = new ArrayList<>();
			for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
				GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable productRunnableTarget = applicationContext
						.getBean(GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable.class);
				productRunnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
				productRunnableTarget.setIndex(i);
				productRunnableTarget.setRelationBean(relationBean);
				productRunnableTarget.setCustomertUpdatedVersionNo(customertUpdatedVersionNo);
				futureList.add(customerExecutorService.submit(productRunnableTarget));
			}
			customerExecutorService.shutdown();
			for (Future<String> future : futureList) {
				finalQuery.append(future.get());
			}
			List<Object> input = new ArrayList<>();
			input.add(relationBean.getRelationshipBuilderSid());
			input.add(customertUpdatedVersionNo + "," + (customertUpdatedVersionNo - 1));
			input.add(finalQuery.toString());
			String finalInsertQuery = gtnWsSqlService.getQuery(input, "Temp Insert Relationship");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
			automaticService.deleteUnwantedUserDefinedLevels(relationBean.getRelationshipBuilderSid(),
					customertUpdatedVersionNo);
			deductionRelationService.saveRelationship(relationBean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}

}
