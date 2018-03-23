package com.stpl.gtn.gtn2o.hierarchyroutebuilder.module.relationshipbuilder.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkHierarchyService;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;

public class GtnWsRelationshipBuilderHierarchyQueryGenerator {

	private GtnFrameworkHierarchyService gtnHierarchyServiceBuilder;
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;

	public GtnFrameworkHierarchyService getGtnHierarchyServiceBuilder() {
		return gtnHierarchyServiceBuilder;
	}

	public void setGtnHierarchyServiceBuilder(GtnFrameworkHierarchyService gtnHierarchyServiceBuilder) {
		this.gtnHierarchyServiceBuilder = gtnHierarchyServiceBuilder;
	}

	public GtnFrameworkEntityMasterBean getGtnFrameworkEntityMasterBean() {
		return gtnFrameworkEntityMasterBean;
	}

	public void setGtnFrameworkEntityMasterBean(GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean) {
		this.gtnFrameworkEntityMasterBean = gtnFrameworkEntityMasterBean;
	}

	public void getLinkedValueQuery(HierarchyLevelDefinitionBean destinationHierarchyBean,
			List<HierarchyLevelDefinitionBean> hierarchyList, GtnFrameworkQueryGeneratorBean queryBean)
			throws GtnFrameworkGeneralException {
		final Set<String> tableNameList = getDefaultTableNameList(destinationHierarchyBean.getHierarchyCategory());
		Set<String> hierarchyTableList = HierarchyLevelDefinitionBean.getTableNameSet(hierarchyList);
		tableNameList.addAll(hierarchyTableList);
		if (tableNameList.isEmpty()) {
			return;
		}
		final GtnFrameworkSingleColumnRelationBean destinationkeyBean = gtnFrameworkEntityMasterBean
				.getKeyRelationBeanUsingTableIdAndColumnName(destinationHierarchyBean.getTableName(),
						destinationHierarchyBean.getFieldName());
		gtnHierarchyServiceBuilder.getSelectColumnsForRelationShipBuilder(destinationkeyBean, queryBean);
		gtnHierarchyServiceBuilder.getQueryByTableNameAndHierarchyTypeForMultiLevel(new ArrayList<>(tableNameList),
				destinationHierarchyBean.getHierarchyCategory(), queryBean);

		if (!destinationkeyBean.isDescriptionColumnAvailable()) {
			gtnHierarchyServiceBuilder.addTableJoin(destinationkeyBean, queryBean);
		}
		final List<GtnFrameworkSingleColumnRelationBean> keyListBeanList = getKeyListBean(
				destinationHierarchyBean.getLevelNo(), hierarchyList);
		gtnHierarchyServiceBuilder.getWhereQuery(keyListBeanList, queryBean);
	}

	private Set<String> getDefaultTableNameList(String hierarchyType) {
		Set<String> selectedTableNamesList = new HashSet<>();
		if ("Customer Hierarchy".equalsIgnoreCase(hierarchyType)) {
			selectedTableNamesList.add("COMPANY_MASTER");
			selectedTableNamesList.add("CONTRACT_MASTER");
		} else if ("PRODUCT HIERARCHY".equalsIgnoreCase(hierarchyType)) {
			selectedTableNamesList.add("ITEM_MASTER");
		}
		return selectedTableNamesList;
	}

	private List<GtnFrameworkSingleColumnRelationBean> getKeyListBean(int levelNo,
			List<HierarchyLevelDefinitionBean> hierarchyList) {
		final List<GtnFrameworkSingleColumnRelationBean> keyListBeanList = new ArrayList<>();
		for (int i = 0; i < levelNo - 1; i++) {
			final HierarchyLevelDefinitionBean hierarchyBean = hierarchyList.get(i);
			if (!GtnFrameworkWebserviceConstant.USER_DEFINED.equals(hierarchyBean.getLevelValueReference())) {
				final GtnFrameworkSingleColumnRelationBean keyListBean = gtnFrameworkEntityMasterBean
						.getKeyRelationBeanUsingTableIdAndColumnName(hierarchyBean.getTableName(),
								hierarchyBean.getFieldName());
				keyListBeanList.add(keyListBean);
			}
		}
		return keyListBeanList;
	}

}
