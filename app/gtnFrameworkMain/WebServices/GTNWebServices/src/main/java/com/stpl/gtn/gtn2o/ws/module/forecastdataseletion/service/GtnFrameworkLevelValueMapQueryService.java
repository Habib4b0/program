package com.stpl.gtn.gtn2o.ws.module.forecastdataseletion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSingleColumnRelationBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnForecastHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

@Service
@Scope(value = "singleton")
public class GtnFrameworkLevelValueMapQueryService {
	@Autowired
	private GtnFrameworkEntityMasterBean gtnFrameworkEntityMasterBean;
	@Autowired
	private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	private static final String RELATIONSHIP_BUILD_VERSION = "RELATIONSHIP_LEVEL_DEFINITION.VERSION_NO";
	private static final String RELATIONSHIP_BUILD_HIERARCHY_NO = "RELATIONSHIP_LEVEL_DEFINITION.HIERARCHY_NO";
	private static final String RELATIONSHIP_LEVEL_DEFN = "RELATIONSHIP_LEVEL_DEFINITION";
	private static final String RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID = "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_BUILDER_SID";
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsForecastConfigurationController.class);
	public GtnFrameworkLevelValueMapQueryService() {
		super();
	}

	public String getQueryForLevelValueMap(GtnForecastHierarchyInputBean inputBean)
			throws GtnFrameworkGeneralException {
		try {
		List<HierarchyLevelDefinitionBean> hierarchyLevelDefinitionList = relationUpdateService.getHierarchyBuilder(
					inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
		StringBuilder finalQuery = new StringBuilder();
		List<GtnFrameworkQueryGeneratorBean> queryBeanList = new ArrayList<>();
		for (HierarchyLevelDefinitionBean leveldto : hierarchyLevelDefinitionList) {
			if (leveldto.isUserDefined()) {
				List<Object> input = new ArrayList<>();
				input.add(inputBean.getRelationShipBuilderSid());
				input.add(inputBean.getRelationVersionNo());

				finalQuery.append(gtnWsSqlService.getQuery(input, "RelationShipUserDefinedLoading"));
				finalQuery.append(" union ");
				continue;
			}
			GtnFrameworkQueryGeneratorBean queryBean = new GtnFrameworkQueryGeneratorBean();
			GtnFrameworkSingleColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
					.getKeyRelationBeanUsingTableIdAndColumnName(leveldto.getTableName(), leveldto.getFieldName());
			queryBean.addSelectClauseBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, Boolean.TRUE, null);
			queryBean.addSelectClauseBean(keyBean.getJoinColumnTable() + "." + keyBean.getDescriptionClauseColumn(),
					null, Boolean.TRUE, null);
			queryBean.setFromTableNameWithAlies(RELATIONSHIP_LEVEL_DEFN, RELATIONSHIP_LEVEL_DEFN);
			GtnFrameworkJoinClauseBean tableJoin = queryBean.addJoinClauseBean(keyBean.getJoinColumnTable(),
					keyBean.getJoinColumnTable(), GtnFrameworkJoinType.JOIN);
			tableJoin.addConditionBean(keyBean.getJoinColumnTable() + "." + keyBean.getMasterSidColumn(),
					"RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES", GtnFrameworkOperatorType.EQUAL_TO);
			queryBean.addWhereClauseBean(RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID, null,
						GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER,
					inputBean.getRelationShipBuilderSid());
			queryBean.addWhereClauseBean("RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO", null,
					GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER,
					leveldto.getLevelNo());
			queryBean.addWhereClauseBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO,
					GtnFrameworkDataType.INTEGER, inputBean.getRelationVersionNo());
			queryBeanList.add(queryBean);

		}

		for (GtnFrameworkQueryGeneratorBean queryBean : queryBeanList) {
			finalQuery.append(queryBean.generateQuery());
			finalQuery.append(" union ");
		}
		finalQuery.replace(finalQuery.lastIndexOf("union"), finalQuery.length() - 1, "");
		return finalQuery.toString();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return "";
	}


}
