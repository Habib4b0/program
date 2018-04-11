package com.stpl.gtn.gtn2o.ws.module.automaticrelationship.concurrency;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.GtnWsRelationshipBuilderBean;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "prototype")
public class GtnFramworkDeductionCheckForAutoUpdateRunnable implements Callable<String> {

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;

	@Autowired
	private GtnFrameworkQueryGeneratorService queryGeneratorService;

	private int index;
	private static final GtnWSLogger LOGGER = GtnWSLogger
			.getGTNLogger(GtnFramworkDeductionCheckForAutoUpdateRunnable.class);
	private GtnWsRelationshipBuilderBean relationBean;
	private List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList;
	private List<Integer> itemMastersidList;

	public GtnFramworkDeductionCheckForAutoUpdateRunnable() {
		super();
	}

	public void setGtnWsSqlService(GtnWsSqlService gtnWsSqlService) {
		this.gtnWsSqlService = gtnWsSqlService;
	}

	public GtnWsSqlService getGtnWsSqlService() {
		return gtnWsSqlService;
	}


	public List<Integer> getItemMastersidList() {
		return Collections.unmodifiableList(itemMastersidList);
	}

	public void setGtnHierarchyServiceBuilder(GtnFrameworkHierarchyService gtnHierarchyServiceBuilder) {
		this.gtnHierarchyServiceBuilder = gtnHierarchyServiceBuilder;
	}

	public void setItemMastersidList(List<Integer> itemMastersidList) {
		this.itemMastersidList = Collections.unmodifiableList(itemMastersidList);
	}

	public GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder() {
		return gtnHierarchyServiceBuilder;
	}

	public GtnWsRelationshipBuilderBean getRelationBean() {
		return relationBean;
	}

	public void setHierarchyLevelDefinitionList(List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList) {
		this.hierarchyLevelDefinitionList = Collections.unmodifiableList(hierarchyLevelDefinitionList);
	}

	public void setRelationBean(GtnWsRelationshipBuilderBean relationBean) {
		this.relationBean = relationBean;
	}

	public List<HierarchyLevelDefinitionBean> getHierarchyLevelDefinitionList() {
		return Collections.unmodifiableList(hierarchyLevelDefinitionList);
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
			List<Object> input = new ArrayList<>();
			input.add(currnetHierarchyLevelBean.getLevelNo());
			input.add(relationBean.getVersionNo());
			input.add(relationBean.getRelationshipBuilderSid());
			input.add(getListToString(itemMastersidList));
			List<Object> finalInputForQuery = null;
			GtnFrameworkQueryGeneratorBean hierarchyQuery = queryGeneratorService.getQuerybySituationNameAndLevel(
					currnetHierarchyLevelBean, "CHECK_AUTO_UPDATE_DEDUCTION", hierarchyLevelDefinitionList);
			String query = gtnWsSqlService.getReplacedQuery(input, hierarchyQuery.generateQuery());
			finalInputForQuery = new ArrayList<>();
			finalInputForQuery.add(relationBean.getRelationshipBuilderSid());
			finalInputForQuery.add(currnetHierarchyLevelBean.getLevelNo());
			finalInputForQuery.add(relationBean.getVersionNo());
			finalInputForQuery.add(query);
			return gtnWsSqlService.getQuery(finalInputForQuery, "checkForUpdateInAutomaticRelation");
		} catch (Exception e) {
			LOGGER.error(" Error " + e.getMessage());
		}

		return "";
	}

	private String getListToString(Collection<?> masterSids) {
		StringBuilder result = new StringBuilder();
		          if (masterSids != null && !masterSids.isEmpty()) {
                for (Object value : masterSids) {
                    result.append("'");
                    result.append(value);
                    result.append("' ,");
                }
			result.deleteCharAt(result.length() - 1);
			return result.toString();
		}
		return "''";
	}
}
