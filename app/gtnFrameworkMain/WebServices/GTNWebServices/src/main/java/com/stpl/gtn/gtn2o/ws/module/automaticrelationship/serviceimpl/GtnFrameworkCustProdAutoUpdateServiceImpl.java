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
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFramworkCheckForAutoUpdateRunnable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFramworkDeductionCheckForAutoUpdateRunnable;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutoupdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
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
	private ApplicationContext applicationContext;

	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFramworkDeductionCheckForAutoUpdateRunnable.class);

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
		try {
			StringBuilder finalQuery = new StringBuilder();
			int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
			final ExecutorService customerExecutorService = Executors
					.newFixedThreadPool(hierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
			List<Future<String>> futureList = new ArrayList<>();
			for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
				if (hierarchyLevelDefinitionList.get(i).isUserDefined())
					continue;
				GtnFramworkCheckForAutoUpdateRunnable customerRunnableTarget = applicationContext
						.getBean(GtnFramworkCheckForAutoUpdateRunnable.class);
				customerRunnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
				customerRunnableTarget.setIndex(i);
				customerRunnableTarget.setRelationBean(relationBean);
				futureList.add(customerExecutorService.submit(customerRunnableTarget));
			}
			customerExecutorService.shutdown();
			for (Future<String> future : futureList) {
				finalQuery.append(future.get());
			}
			List<Object> input = new ArrayList<>();
			input.add(relationBean.getRelationshipBuilderSid());
			input.add(relationBean.getVersionNo() + "," + (relationBean.getVersionNo() - 1));
			input.add(finalQuery.toString());
			String query = gtnWsSqlService.getQuery(input, "Check Relation to Update");
			List<?> result = gtnSqlQueryEngine.executeSelectQuery(query);
			return Integer.parseInt(result.get(0).toString()) > 0;
		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
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
			List<Future<String>> futureProductList = new ArrayList<>();
			for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
				GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable customerRunnableTarget = applicationContext
						.getBean(GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable.class);
				customerRunnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
				customerRunnableTarget.setIndex(i);
				customerRunnableTarget.setRelationBean(relationBean);
				customerRunnableTarget.setCustomertUpdatedVersionNo(customertUpdatedVersionNo);
				futureProductList.add(customerExecutorService.submit(customerRunnableTarget));
			}
			customerExecutorService.shutdown();
			for (Future<String> future : futureProductList) {
				finalQuery.append(future.get());
			}
			List<Object> customerInput = new ArrayList<>();
			customerInput.add(relationBean.getRelationshipBuilderSid());
			customerInput.add(customertUpdatedVersionNo + "," + (customertUpdatedVersionNo - 1));
			customerInput.add(finalQuery.toString());
			String finalInsertQuery = gtnWsSqlService.getQuery(customerInput, "Temp Insert Relationship");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
			automaticService.deleteUnwantedUserDefinedLevels(relationBean.getRelationshipBuilderSid(),
					customertUpdatedVersionNo);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}

}
