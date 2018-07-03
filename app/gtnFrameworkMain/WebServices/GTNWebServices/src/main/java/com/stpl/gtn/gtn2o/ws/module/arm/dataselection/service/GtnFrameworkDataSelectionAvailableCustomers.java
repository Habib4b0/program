/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton")
public class GtnFrameworkDataSelectionAvailableCustomers {

    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    @Autowired
    private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
    @Autowired
    private GtnFrameworkQueryGeneratorService queryGeneratorService;

    public String getCustomerLevelQuery(GtnARMHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
        List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService
                .getHierarchyBuilder(inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
        HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
                .getLastLinkedLevel(hierarchyDefinitionList);
        HierarchyLevelDefinitionBean selectedHierarchyLevelDto = HierarchyLevelDefinitionBean
                .getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
        GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
                "LOAD_AVAILABLE_TABLE", hierarchyDefinitionList);
        String finalQuery = queryBean.generateQuery();
        if (!inputBean.getDeductionValues().isEmpty()) {
            String createDeductionTableQuery = gtnWsSqlService.getQuery("createTempDeduction");
            String joinDeductionTableQuery = gtnWsSqlService.getQuery("joinTempDeduction");
            finalQuery = finalQuery.replace("WHERE", joinDeductionTableQuery + " WHERE ");
            finalQuery = createDeductionTableQuery + finalQuery;
            finalQuery = finalQuery.replace("@RS_CON_IDS", inputBean.getDeductionValues());
        }
        List<Object> inputList = new ArrayList<>();
        inputList.add(inputBean.getRelationShipBuilderSid());
        inputList.add(String.valueOf(lastLevelDto.getLevelNo()));
        inputList.add(inputBean.getRelationVersionNo());
        inputList.add(inputBean.getRelationVersionNo());
        inputList.add(String.valueOf(selectedHierarchyLevelDto.getLevelNo()));
        return gtnWsSqlService.getReplacedQuery(inputList, finalQuery);
    }

}
