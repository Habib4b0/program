package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFrameworkDedAutoUpdateQueryGeneratorCallable implements Callable<String> {

	private GtnWsRelationshipBuilderBean relationBean;
	private List<Integer> itemMastersidList;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;
	private int index;

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	private int customertUpdatedVersionNo;


	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public void setCustomertUpdatedVersionNo(int customertUpdatedVersionNo) {
		this.customertUpdatedVersionNo = customertUpdatedVersionNo;
	}

	public void setIndex(int index) {
		this.index = index;
	}
	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = new ArrayList<>(hierarchyLevelDefinitionList);
	}

	public void setItemMastersidList(List<Integer> itemMastersidList) {
		this.itemMastersidList = new ArrayList<>(itemMastersidList);
	}

	public GtnFrameworkDedAutoUpdateQueryGeneratorCallable() {
		super();
	}

	@Override
	public String call() throws Exception {
		HierarchyLevelDefinitionBean hierarchyLevelBean = hierarchyLevelDefinitionList.get(index);
		GtnFrameworkQueryGeneratorBean querygeneratorBean = queryGeneratorService.getQuerybySituationNameAndLevel(
				hierarchyLevelBean, "AUTO_UPDATE_DEDUCTION", hierarchyLevelDefinitionList);
		List<Object> firstInput = new ArrayList<>();
		firstInput.add(relationBean.getRelationshipBuilderSid());
		firstInput.add(hierarchyLevelBean.getHierarchyLevelDefinitionSid());
		firstInput.add(hierarchyLevelBean.getLevelNo());
		firstInput.add("'" + hierarchyLevelBean.getLevelName() + "'");
		firstInput.add(relationBean.getRelationshipBuilderSid());
		firstInput.add(relationBean.getCreatedBy());
		firstInput.add(relationBean.getModifiedBy());
		firstInput.add(customertUpdatedVersionNo);
		firstInput.add(relationBean.getRelationshipBuilderSid());
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
				result.append("'" );
                                result.append(hirarechyNo);
                                result.append("' ,");
			}
			result.deleteCharAt(result.length() - 1);
			return result.toString();
		}
		return "''";
	}

}
