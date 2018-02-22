package com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.controller;

import com.stpl.gtn.gtn20.ws.report.engine.hibernate.GtnWsCustomSqlClass;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service.GtnWsQueryService;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsTreeNode;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GtnWsReportInputGenerator {

    public static final GtnWsCustomSqlClass SQL_INSTANCE = GtnWsCustomSqlClass.getInstance();
    public static final GtnWsQueryService QUERY = new GtnWsQueryService();
    public static final GtnWsTreeService TREE_SERVICE = GtnWsTreeService.getInstance();

    public GtnWsTreeNode callBuildTree(int caseNo) {
        List<Object[]> deductionList = QUERY.getDeductionQueryList();
//        List<Object[]> ccpResult = SQL_INSTANCE.executeQuery(QUERY.getCPPListWithRsQuery());
        List<Object[]> ccpResult = QUERY.getCPPListWithRS();
        List<Object[]> selectedCust = QUERY.getSelectedCust();
        List<Object[]> selectedProd = QUERY.getSelectedProd();
        Map<String, Object[]> custHierarchy = convertAsMap(SQL_INSTANCE.executeQuery(QUERY.getCustomerMap()));
        GtnWsTreeNode customerRootNode = TREE_SERVICE.buildTree(selectedCust, custHierarchy);
        Map<String, Object[]> prodHierarchy = convertAsMap(SQL_INSTANCE.executeQuery(QUERY.getProductMap()));
        GtnWsTreeNode productrootNode = TREE_SERVICE.buildTree(selectedProd, prodHierarchy);
        GtnWsTreeNode ccpNode = buildStructure(caseNo, customerRootNode, productrootNode, ccpResult, deductionList);
        return ccpNode;
    }

    private Map<String, Object[]> convertAsMap(List<Object[]> resultList) {
        Map<String, Object[]> inputParam = new HashMap<>();
        for (int j = resultList.size() - 1; j >= 0; j--) {
            Object[] object = resultList.get(j);
            inputParam.put(String.valueOf(object[0]), object);
        }
        return inputParam;
    }

    private void displayNodeValues(GtnWsTreeNode ccpNode) {
        for (GtnWsTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
            System.out.println(gtnWsTreeNode.toString());
            if (gtnWsTreeNode.getChildren() != null) {
                displayNodeValues(gtnWsTreeNode);
            }
        }
    }

    private GtnWsTreeNode buildStructure(int caseNo, GtnWsTreeNode customerRootNode, GtnWsTreeNode productrootNode, List<Object[]> ccpResult, List<Object[]> deductionList) {
        GtnWsTreeNode ccpNode = TREE_SERVICE.buildCCPTree(customerRootNode, productrootNode, ccpResult, QUERY.getCustomViewWithDiscountLevel(caseNo), deductionList);
        displayNodeValues(ccpNode);
        return ccpNode;
    }

    public static void shutdown() {
        SQL_INSTANCE.shutdown();
    }
}
