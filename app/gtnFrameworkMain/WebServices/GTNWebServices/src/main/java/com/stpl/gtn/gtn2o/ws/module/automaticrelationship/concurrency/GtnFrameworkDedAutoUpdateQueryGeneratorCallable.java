package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkHierarchyQueryBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkJoinQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkSelectQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.service.GtnFrameworkWhereQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.querygenerator.serviceimpl.GtnFrameworkQueryGeneraterServiceImpl;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnFrameworkFileReadWriteService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFrameworkDedAutoUpdateQueryGeneratorCallable implements Callable<String> {

	private GtnWsRelationshipBuilderBean relationBean;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;
	private int index;
	private List<Integer> itemMastersidList;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;


	@Autowired
	@Qualifier("DeductionSelect")
	private GtnFrameworkSelectQueryGeneratorService selectService;
	@Autowired
	@Qualifier("DeductionWhere")
	private GtnFrameworkWhereQueryGeneratorService whereService;
	@Autowired
	@Qualifier("DeductionJoin")
	private GtnFrameworkJoinQueryGeneratorService joinService;

	private int customertUpdatedVersionNo;

	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyLevelDefinitionList() {
		return hierarchyLevelDefinitionList;
	}

	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = hierarchyLevelDefinitionList;
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

	public List<Integer> getItemMastersidList() {
		return itemMastersidList;
	}

	public void setItemMastersidList(List<Integer> itemMastersidList) {
		this.itemMastersidList = itemMastersidList;
	}

	@Override
	public String call() throws Exception {
		GtnFrameworkFileReadWriteService fileService = new GtnFrameworkFileReadWriteService();
		HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(index);
		GtnFrameworkHierarchyQueryBean hierarchyQuery = fileService.getQueryFromFile(
				hierarchyLevelBean.getHierarchyDefinitionSid(), hierarchyLevelBean.getHierarchyLevelDefinitionSid(),
				hierarchyLevelBean.getVersionNo());
		GtnFrameworkQueryGeneratorBean querygeneratorBean = hierarchyQuery.getQuery();

		GtnFrameworkQueryGeneraterServiceImpl queryGenerator = new GtnFrameworkQueryGeneraterServiceImpl(
				selectService, joinService, whereService);
		queryGenerator.generateQuery(hierarchyLevelDefinitionList, relationBean, querygeneratorBean,
				customertUpdatedVersionNo, index);
		List<String> firstInput = new ArrayList<>();
		firstInput.add(getListToString(itemMastersidList));
		String insertQuery = gtnWsSqlService.getReplacedQuery(firstInput, querygeneratorBean.generateQuery());
		List<String> insertQueryInput = new ArrayList<>();
		insertQueryInput.add(insertQuery);
		return gtnWsSqlService.getQuery(insertQueryInput,
				"relationShipSubQueryToInsertAutomaticData");
	}

	private String getListToString(Collection<?> masterSids) {
		StringBuilder result = new StringBuilder();
		if (masterSids != null && !masterSids.isEmpty()) {
			for (Object hirarechyNo : masterSids) {
				result.append("'" + hirarechyNo + "' ,");
			}
			result.deleteCharAt(result.length() - 1);
			return result.toString();
		}
		return "''";
	}

}
