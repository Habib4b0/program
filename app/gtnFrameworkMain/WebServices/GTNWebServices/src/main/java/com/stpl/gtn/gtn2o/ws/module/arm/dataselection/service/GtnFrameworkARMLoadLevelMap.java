/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkJoinClauseBean;
import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkEntityMasterBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.bean.GtnFrameworkSelectColumnRelationBean;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkJoinType;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.module.forecastconfiguration.controller.GtnWsForecastConfigurationController;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Sathya.Seelan
 */
@Service
@Scope(value = "singleton")
public class GtnFrameworkARMLoadLevelMap {

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

    public GtnFrameworkARMLoadLevelMap() {
        super();
    }

    public String getArmQueryForLevelValueMap(GtnARMHierarchyInputBean armInputBean)
            throws GtnFrameworkGeneralException {
        try {
            List<HierarchyLevelDefinitionBean> armHierarchyLevelDefinitionList = relationUpdateService.getHierarchyBuilder(
                    armInputBean.getHierarchyDefinitionSid(), armInputBean.getHierarchyVersionNo());
            StringBuilder query = new StringBuilder();
            List<GtnFrameworkQueryGeneratorBean> armQueryBeanList = new ArrayList<>();
            for (HierarchyLevelDefinitionBean armLeveldto : armHierarchyLevelDefinitionList) {
                if (armLeveldto.isUserDefined()) {
                    List<Object> armInput = new ArrayList<>();
                    armInput.add(armInputBean.getRelationShipBuilderSid());
                    armInput.add(armInputBean.getRelationVersionNo());

                    query.append(gtnWsSqlService.getQuery(armInput, "RelationShipUserDefinedLoading"));
                    query.append(" union ");
                    continue;
                }
                GtnFrameworkQueryGeneratorBean armQueryBean = new GtnFrameworkQueryGeneratorBean();
                GtnFrameworkSelectColumnRelationBean keyBean = gtnFrameworkEntityMasterBean
                        .getKeyRelationBeanUsingTableIdAndColumnName(armLeveldto.getTableName(), armLeveldto.getFieldName());
                armQueryBean.addSelectClauseBean(RELATIONSHIP_BUILD_HIERARCHY_NO, null, Boolean.TRUE, null);
                armQueryBean.addSelectClauseBean(keyBean.getJoinColumnTable() + "." + keyBean.getDescriptionClauseColumn(),
                        null, Boolean.TRUE, null);
                armQueryBean.setFromTableNameWithAlies(RELATIONSHIP_LEVEL_DEFN, RELATIONSHIP_LEVEL_DEFN);
                GtnFrameworkJoinClauseBean tableJoin = armQueryBean.addJoinClauseBean(keyBean.getJoinColumnTable(),
                        keyBean.getJoinColumnTable(), GtnFrameworkJoinType.JOIN);
                tableJoin.addConditionBean(keyBean.getJoinColumnTable() + "." + keyBean.getMasterSidColumn(),
                        "RELATIONSHIP_LEVEL_DEFINITION.RELATIONSHIP_LEVEL_VALUES", GtnFrameworkOperatorType.EQUAL_TO);
                armQueryBean.addWhereClauseBean(RELATIONSHIP_LEVEL_RELATIONSHIP_BUILDER_SID, null,
                        GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER,
                        armInputBean.getRelationShipBuilderSid());
                armQueryBean.addWhereClauseBean("RELATIONSHIP_LEVEL_DEFINITION.LEVEL_NO", null,
                        GtnFrameworkOperatorType.EQUAL_TO, GtnFrameworkDataType.INTEGER,
                        armLeveldto.getLevelNo());
                armQueryBean.addWhereClauseBean(RELATIONSHIP_BUILD_VERSION, null, GtnFrameworkOperatorType.EQUAL_TO,
                        GtnFrameworkDataType.INTEGER, armInputBean.getRelationVersionNo());
                armQueryBeanList.add(armQueryBean);

            }

            for (GtnFrameworkQueryGeneratorBean armQueryBean : armQueryBeanList) {
                query.append(armQueryBean.generateQuery());
                query.append(" union ");
            }
            query.replace(query.lastIndexOf("union"), query.length() - 1, "");
            return query.toString();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return "";
    }

}
