package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.serviceimpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency.GtnFrameworkDeductionRelationServiceRunnable;
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

@Component("ProdService")
@Scope(value = "singleton")
public class GtnFrameworkProductAutoUpdateServiceImpl implements GtnFrameworkAutoupdateService {
	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	@Qualifier("CustProdSelect")
	private GtnFrameworkSelectQueryGeneratorService custSelectService;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService automaticService;



	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;



	@Autowired
	@Qualifier("CustProdWhere")
	private GtnFrameworkWhereQueryGeneratorService whereService;
	@Autowired
	@Qualifier("CustProdJoin")
	private GtnFrameworkJoinQueryGeneratorService joinService;
	@Autowired
	private GtnFrameworkDeductionRelationServiceRunnable deductionRelationService;

	private static final GtnWSLogger logger = GtnWSLogger
			.getGTNLogger(GtnFrameworkProductAutoUpdateServiceImpl.class);

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
			List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) throws InterruptedException {

		try {
			StringBuilder finalQuery = new StringBuilder();
			int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
			final ExecutorService productExecutorService = Executors
					.newFixedThreadPool(hierarchyLevelDefinitionList.size() - firstLinkedLevelNo);
			List<Future<String>> productFutureList = new ArrayList<>();
			for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
				if (hierarchyLevelDefinitionList.get(i).isUserDefined())
					continue;
				GtnFramworkCheckForAutoUpdateRunnable productRunnableTarget = applicationContext
						.getBean(GtnFramworkCheckForAutoUpdateRunnable.class);
				productRunnableTarget.setHierarchyLevelDefinitionList(hierarchyLevelDefinitionList);
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
		return Boolean.FALSE;
	}

	@Override
	public void doAutomaticUpdate(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList,
			GtnWsRelationshipBuilderBean relationBean) throws GtnFrameworkGeneralException {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		int firstLinkedLevelNo = HierarchyLevelDefinitionBean.getFirstLinkedLevel(hierarchyLevelDefinitionList);
		int updatedVersionNo = automaticService.insertRelationTillFirstLevelAndGetVersionNo(firstLinkedLevelNo,
				relationBean);
		StringBuilder finalQuery = new StringBuilder();
		for (int i = firstLinkedLevelNo; i < hierarchyLevelDefinitionList.size(); i++) {
			HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(i);
			if (hierarchyLevelBean.isUserDefined()) {
				finalQuery.append(checkAndInserUserDefinedLevels(relationBean, hierarchyLevelBean));
				continue;
			}
			GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
					hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
					hierarchyLevelBean.getVersionNo());
			HierarchyLevelDefinitionBean previousHierarchyLevelBean = i > 0 ? hierarchyLevelDefinitionList.get(i - 1)
					: null;
			GtnFrameworkQueryGeneratorBean querygeneratorBean = hierarchyQuery.getQuery();
			GtnFrameworkQueryGeneraterServiceImpl queryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
					custSelectService, joinService, whereService);
			queryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
					updatedVersionNo, i);
			List<Object> inputs = new ArrayList<>();
			inputs.add(relationBean.getRelationshipBuilderSid());
			inputs.add(previousHierarchyLevelBean == null ? "" : previousHierarchyLevelBean.getLevelNo());
			inputs.add(updatedVersionNo);
			inputs.add(hierarchyLevelBean.getLevelNo());
			inputs.add(updatedVersionNo - 1);
			inputs.add(hierarchyLevelBean.getLevelNo());
			inputs.add(updatedVersionNo - 1);
			hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
			String query = gtnWsSqlService.getReplacedQuery(inputs, querygeneratorBean.generateQuery());
			List<String> insertQueryInput = new ArrayList<>();
			insertQueryInput.add(query);
			String finalInsertQuery = gtnWsSqlService.getQuery(insertQueryInput,
					"relationShipSubQueryToInsertAutomaticData");
			finalQuery.append(finalInsertQuery);
		}
		try {
			List<Object> input = new ArrayList<>();
			input.add(relationBean.getRelationshipBuilderSid());
			input.add(updatedVersionNo + "," + (updatedVersionNo - 1));
			input.add(finalQuery.toString());
			String finalInsertQuery = gtnWsSqlService.getQuery(input, "Temp Insert Relationship");
			gtnSqlQueryEngine.executeInsertOrUpdateQuery(finalInsertQuery);
			deductionRelationService.saveRelationship(relationBean);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}



	private String checkAndInserUserDefinedLevels(GtnWsRelationshipBuilderBean relationBean,
			HierarchyLevelDefinitionBean customerHierarchyLevelBean) {
		List<Object> input = new ArrayList<>();

		input.add(customerHierarchyLevelBean.getHierarchyLevelDefinitionSid());
		input.add(customerHierarchyLevelBean.getDefaultVlaue());
		input.add(customerHierarchyLevelBean.getLevelNo());
		input.add(customerHierarchyLevelBean.getLevelName());
		input.add(customerHierarchyLevelBean.getDefaultVlaue());
		input.add(customerHierarchyLevelBean.getDefaultVlaue());
		input.add(customerHierarchyLevelBean.getLevelNo() - 1);
		input.add(relationBean.getVersionNo() + 1);
		input.add(relationBean.getRelationshipBuilderSid());

		input.add(customerHierarchyLevelBean.getLevelNo());
		input.add(customerHierarchyLevelBean.getLevelName());
		input.add(customerHierarchyLevelBean.getLevelNo() - 1);
		input.add(customerHierarchyLevelBean.getLevelNo());
		input.add(relationBean.getVersionNo());
		input.add(relationBean.getVersionNo() + 1);
		input.add(relationBean.getRelationshipBuilderSid());

		return gtnWsSqlService.getQuery(input, "RelationInsertForIntermediate userDefined");
	}

	@Override
	public GtnFrameworkAutomaticRelationUpdateService getService() {
		return automaticService;
	}


}
