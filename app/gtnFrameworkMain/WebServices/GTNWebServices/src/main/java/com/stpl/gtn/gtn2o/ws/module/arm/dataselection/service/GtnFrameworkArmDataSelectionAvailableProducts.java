/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.arm.dataselection.service;

import com.stpl.gtn.gtn2o.bean.GtnFrameworkQueryGeneratorBean;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.hierarchyroutebuilder.service.GtnFrameworkQueryGeneratorService;
import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.querygenerator.GtnFrameworkOperatorType;
import com.stpl.gtn.gtn2o.ws.arm.dataselection.bean.GtnARMHierarchyInputBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecast.bean.GtnFrameworkRelationshipLevelDefintionBean;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticRelationUpdateService;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.bean.HierarchyLevelDefinitionBean;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "singleton")
public class GtnFrameworkArmDataSelectionAvailableProducts {

    @Autowired
    private GtnFrameworkAutomaticRelationUpdateService relationUpdateService;
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    @Autowired
    private GtnFrameworkQueryGeneratorService queryGeneratorService;
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    public GtnFrameworkArmDataSelectionAvailableProducts() {
        super();
    }

    public String getProductLevelQuery(GtnARMHierarchyInputBean inputBean) throws GtnFrameworkGeneralException {
        List<HierarchyLevelDefinitionBean> hierarchyDefinitionList = relationUpdateService
                .getHierarchyBuilder(inputBean.getHierarchyDefinitionSid(), inputBean.getHierarchyVersionNo());
        HierarchyLevelDefinitionBean selectedHierarchyBean = HierarchyLevelDefinitionBean
                .getBeanByLevelNo(inputBean.getLevelNo(), hierarchyDefinitionList);
        if (selectedHierarchyBean.isUserDefined()) {
            return buildQueryForUserDefinedLevel(inputBean);
        }
        return getQueryForLinkedLevelProduct(inputBean, hierarchyDefinitionList, selectedHierarchyBean);
    }

    private String buildQueryForUserDefinedLevel(GtnARMHierarchyInputBean inputBean) {
        List<Object> input = new ArrayList<>();
        input.add(inputBean.getRelationShipBuilderSid());
        input.add(inputBean.getLevelNo());
        input.add(inputBean.getRelationVersionNo());
        return gtnWsSqlService.getQuery(input, "SelectValuesFromUserDefinedHierarchy");
    }

    @SuppressWarnings("rawtypes")
    private String getQueryForLinkedLevelProduct(GtnARMHierarchyInputBean inputBean,
            List<HierarchyLevelDefinitionBean> hierarchyDefinitionList,
            HierarchyLevelDefinitionBean selectedHierarchyBean) throws GtnFrameworkGeneralException {

        List<HierarchyLevelDefinitionBean> customerHierarchyLevelList = relationUpdateService.getHierarchyBuilder(
                inputBean.getSelectedCustomerHierarcySid(), inputBean.getSelectedCustomerHierarchyVersionNo());
        HierarchyLevelDefinitionBean lastLevelDto = HierarchyLevelDefinitionBean
                .getLastLinkedLevel(hierarchyDefinitionList);
        String situationName = "LOAD_AVAILABLE_TABLE_PRODUCT";
        if (inputBean.isIsNdc()) {
            situationName = "LOAD_AVAILABLE_TABLE_FOR_NDC";
        }
        GtnFrameworkQueryGeneratorBean queryBean = queryGeneratorService.getQuerybySituationNameAndLevel(lastLevelDto,
                situationName, hierarchyDefinitionList);
        List<Set> sidList = getCustomerConractSid(inputBean.getSelectedCustomerList(), customerHierarchyLevelList,
                inputBean.getSelectedCustomerRelationShipBuilderVersionNo());
        getWhereQueryForProductLevel(sidList, queryBean);
        if (inputBean.getBusinessUnit() != 0) {
            queryBean.addWhereClauseBean("ITEM_MASTER.ORGANIZATION_KEY", null, GtnFrameworkOperatorType.EQUAL_TO,
                    GtnFrameworkDataType.STRING, String.valueOf(inputBean.getBusinessUnit()));
        }
        if (inputBean.getGlCompany() != 0) {
            queryBean.addWhereClauseBean("COMPANY_MASTER.COMPANY_MASTER_SID", null, GtnFrameworkOperatorType.EQUAL_TO,
                    GtnFrameworkDataType.STRING, String.valueOf(inputBean.getGlCompany()));
        }
        String query = queryBean.generateQuery().replace("WHERE", gtnWsSqlService.getQuery("joinTempProductDeduction") + " WHERE ");
        query = getDeductionJoin(inputBean, query);
        List<Object> inputList = new ArrayList<>();
        inputList.add(inputBean.getRelationShipBuilderSid());
        inputList.add(String.valueOf(lastLevelDto.getLevelNo()));
        inputList.add(inputBean.getRelationVersionNo());
        inputList.add(inputBean.getRelationVersionNo());
        inputList.add(String.valueOf(selectedHierarchyBean.getLevelNo()));
        return gtnWsSqlService.getReplacedQuery(inputList, query);
    }

    private String getDeductionJoin(GtnARMHierarchyInputBean inputBean, String query) {
        if (inputBean.getDeductionValues() != null && !inputBean.getDeductionValues().isEmpty()) {
            String createDeductionTableQuery = gtnWsSqlService.getQuery("createTempDeduction");
            createDeductionTableQuery = createDeductionTableQuery.replace("@RS_CON_IDS", inputBean.getDeductionValues());
            query = createDeductionTableQuery + query;
            return query.replace("@JOINTEMPDEDUCTION", gtnWsSqlService.getQuery("joinTempDeductionProduct"));
        }
        return query.replace("@JOINTEMPDEDUCTION", StringUtils.EMPTY);
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private List<Set> getCustomerConractSid(
            List<GtnFrameworkRelationshipLevelDefintionBean> selectedCustomerContractList,
            List<HierarchyLevelDefinitionBean> customerHierarchyLevelDefinitionList, int customerRelationVersionNo)
            throws GtnFrameworkGeneralException {
        if (customerRelationVersionNo == 0 || customerHierarchyLevelDefinitionList == null
                || customerHierarchyLevelDefinitionList.isEmpty()) {
            return Collections.emptyList();
        }
        HierarchyLevelDefinitionBean lastLinketLevel = HierarchyLevelDefinitionBean
                .getLastLinkedLevel(customerHierarchyLevelDefinitionList);
        GtnFrameworkQueryGeneratorBean customerQueryBean = queryGeneratorService
                .getQuerybySituationNameAndLevel(lastLinketLevel, "CCP_PRODUCT_CUSTOMER",
                        customerHierarchyLevelDefinitionList);
        queryGeneratorService.getWhereQueryForCustomerAndContract(selectedCustomerContractList, customerQueryBean);
        List<Set> finalList = new ArrayList<>();
        Set<Integer> customerSidSet = new HashSet<>();
        Set<Integer> contractSidSet = new HashSet<>();
        List<Object[]> results = (List<Object[]>) gtnSqlQueryEngine
                .executeSelectQuery(customerQueryBean.generateQuery());
        for (Object[] object : results) {
            customerSidSet.add(getIntegerValue(object, 0));
            contractSidSet.add(getIntegerValue(object, 1));
        }
        finalList.add(customerSidSet);
        finalList.add(contractSidSet);
        return finalList;
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    private void getWhereQueryForProductLevel(List<Set> sidListb, GtnFrameworkQueryGeneratorBean queryBean) {
        if (!sidListb.isEmpty()) {
            Set<Integer> customerSidSet = sidListb.get(0);
            Set<Integer> contractSidSet = sidListb.get(1);
            if (!customerSidSet.isEmpty()) {
                queryBean.addWhereClauseBean("CCP_DETAILS.COMPANY_MASTER_SID", null, GtnFrameworkOperatorType.IN,
                        GtnFrameworkDataType.LIST, new ArrayList<>(customerSidSet));
            }
            if (!contractSidSet.isEmpty()) {
                queryBean.addWhereClauseBean("CCP_DETAILS.CONTRACT_MASTER_SID", null, GtnFrameworkOperatorType.IN,
                        GtnFrameworkDataType.LIST, new ArrayList<>(contractSidSet));
            }
        }
    }

    private Integer getIntegerValue(Object[] objects, int index) {
        return Integer.valueOf(objects[index] == null ? "0" : objects[index].toString());
    }

}
