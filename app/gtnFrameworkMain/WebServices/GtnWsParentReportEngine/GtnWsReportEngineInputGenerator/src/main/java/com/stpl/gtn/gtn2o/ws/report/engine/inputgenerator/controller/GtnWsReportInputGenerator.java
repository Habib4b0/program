package com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn20.ws.report.engine.hibernate.GtnWsCustomSqlClass;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service.GtnWsQueryService;
import com.stpl.gtn.gtn2o.ws.report.engine.inputgenerator.service.GtnWsTreeService;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

public class GtnWsReportInputGenerator {

	public static final GtnWsCustomSqlClass SQL_INSTANCE = GtnWsCustomSqlClass.getInstance();
	public static final GtnWsQueryService QUERY = new GtnWsQueryService();
	public static final GtnWsTreeService TREE_SERVICE = GtnWsTreeService.getInstance();

	public GtnWsReportEngineTreeNode callBuildTree(int caseNo) {
		List<Object[]> deductionList = QUERY.getDeductionQueryList();
		List<Object[]> ccpResult = SQL_INSTANCE.executeQuery(QUERY.getCPPListWithRsQuery());
		// List<Object[]> ccpResult = QUERY.getCPPListWithRS();
		List<Object[]> selectedCust = QUERY.getSelectedCust();
		List<Object[]> selectedProd = QUERY.getSelectedProd();
		Map<String, Object[]> custHierarchy = convertAsMap(SQL_INSTANCE.executeQuery(QUERY.getCustomerMap()));
		GtnWsReportEngineTreeNode customerRootNode = TREE_SERVICE.buildTree(selectedCust, custHierarchy, "C");
		// displayNodeValues(customerRootNode);
		Map<String, Object[]> prodHierarchy = convertAsMap(SQL_INSTANCE.executeQuery(QUERY.getProductMap()));
		GtnWsReportEngineTreeNode productrootNode = TREE_SERVICE.buildTree(selectedProd, prodHierarchy, "P");
		// displayNodeValues(productrootNode);
		List<Object[]> customLevelDeatils = QUERY.getCustomViewWithDiscountLevel(caseNo);

		List<String> variableList = new ArrayList<>();
		variableList.add("Test1");
		variableList.add("Test2");

		GtnWsReportEngineTreeNode root = new GtnWsReportEngineTreeNode();

		for (Object[] objects : customLevelDeatils) {
			if ("A".equals(objects[1])) {
				TREE_SERVICE.buildAllVariableTree(root, Integer.parseInt(objects[3].toString()), variableList);
			}
			if ("D".equals(objects[1])) {
				TREE_SERVICE.buildDeductionTree(root, Integer.parseInt(objects[3].toString()), deductionList, ccpResult,
						Integer.parseInt(objects[0].toString()));
			}

			if ("C".equals(objects[1]) || "P".equals(objects[1])) {
				GtnWsReportEngineTreeNode inputTree = "C".equals(objects[1]) ? customerRootNode : productrootNode;
				List<GtnWsReportEngineTreeNode> allLevelNodeList = new ArrayList<>();
				TREE_SERVICE.getAllNodesFromTree(inputTree, allLevelNodeList, Integer.parseInt(objects[0].toString()));
				TREE_SERVICE.buildCustomTree(root, Integer.parseInt(objects[3].toString()), allLevelNodeList,
						ccpResult);
			}

		}

		displayNodeValues(root);
		return root;
	}

	private Map<String, Object[]> convertAsMap(List<Object[]> resultList) {
		Map<String, Object[]> inputParam = new HashMap<>();
		for (int j = resultList.size() - 1; j >= 0; j--) {
			Object[] object = resultList.get(j);
			inputParam.put(String.valueOf(object[0]), object);
		}
		return inputParam;
	}

	public void displayNodeValues(GtnWsReportEngineTreeNode ccpNode) {
		for (GtnWsReportEngineTreeNode gtnWsTreeNode : ccpNode.getChildren()) {
			System.out.println(gtnWsTreeNode.toString());
			if (gtnWsTreeNode.getChildren() != null) {
				displayNodeValues(gtnWsTreeNode);
			}
		}
	}

	public static void shutdown() {
		GtnWsCustomSqlClass.shutdown();
	}
}
