package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkJoinQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.automaticrelationship.querygenerator.serviceimpl.GtnFrameworkQueryGeneraterServiceImpl;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable implements Callable<String> {

	private GtnWsRelationshipBuilderBean relationBean;
	private int index;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkHierarchyService hierarchyService;

	@Autowired
	@Qualifier("CustProdSelect")
	private GtnFrameworkSelectQueryGeneratorService selectService;
	@Autowired
	@Qualifier("CustProdJoin")
	private GtnFrameworkJoinQueryGeneratorService joinService;
	@Autowired
	@Qualifier("CustProdWhere")
	private GtnFrameworkWhereQueryGeneratorService whereService;

	private int customertUpdatedVersionNo;

	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = new ArrayList<>(hierarchyLevelDefinitionList);
	}

	public int getCustomertUpdatedVersionNo() {
		return customertUpdatedVersionNo;
	}

	public void setCustomertUpdatedVersionNo(int customertUpdatedVersionNo) {
		this.customertUpdatedVersionNo = customertUpdatedVersionNo;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public GtnFrameworkCustProdAutoUpdateQueryGeneratorCallable() {
		super();
	}

	@Override
	public String call() throws Exception {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		HierarchyLevelDefinitionBean customerHierarchyLevelBean = hierarchyLevelDefinitionList.get(index);
		if (customerHierarchyLevelBean.isUserDefined()) {
			return checkAndInserUserDefinedLevels(relationBean, customerHierarchyLevelBean);
		}
		GtnFrameworkHierarchyQueryBean customerHierarchyQuery = fileService.getQueryFromFile(
				customerHierarchyLevelBean.getHierarchyDefinitionSid(),
				customerHierarchyLevelBean.getHierarchyLevelDefinitionSid(), customerHierarchyLevelBean.getVersionNo());
		HierarchyLevelDefinitionBean previousHierarchyLevelBean = HierarchyLevelDefinitionBean
				.getPreviousLinkedLevel(hierarchyLevelDefinitionList, customerHierarchyLevelBean);
		GtnFrameworkQueryGeneratorBean querygeneratorBean = customerHierarchyQuery.getQuery();
		GtnFrameworkQueryGeneraterServiceImpl customerQueryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
				selectService, joinService, whereService);
		customerQueryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
				customertUpdatedVersionNo, index);
		List<Object> inputsList = new ArrayList<>();
		inputsList.add(relationBean.getRelationshipBuilderSid());
		inputsList.add(previousHierarchyLevelBean.getLevelNo());
		inputsList.add(customertUpdatedVersionNo);
		inputsList.add(customerHierarchyLevelBean.getLevelNo());
		inputsList.add(customertUpdatedVersionNo > 1 ? customertUpdatedVersionNo - 1 : 1);
		inputsList.add(customerHierarchyLevelBean.getLevelNo());
		inputsList.add(customertUpdatedVersionNo > 1 ? customertUpdatedVersionNo - 1 : 1);
		hierarchyService.getInboundRestrictionQueryForAutoUpdate(querygeneratorBean);
		String query = gtnWsSqlService.getReplacedQuery(inputsList, querygeneratorBean.generateQuery());
		List<String> insertQueryInput = new ArrayList<>();
		insertQueryInput.add(query);
		return gtnWsSqlService.getQuery(insertQueryInput,
				"relationShipSubQueryToInsertAutomaticData");
	}

	private String checkAndInserUserDefinedLevels(GtnWsRelationshipBuilderBean relationBean,
			HierarchyLevelDefinitionBean customerHierarchyLevelBean) {
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

		return gtnWsSqlService.getQuery(inputList, "RelationInsertForIntermediate userDefined");
	}

}
