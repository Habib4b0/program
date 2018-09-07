package com.stpl.gtn.gtn2o.ws.report.serviceimpl;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.stpl.gtn.gtn20.ws.report.engine.mongo.service.GtnWsMongoDBConnectionService;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsCustomTreeData;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportCustomViewDataBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean.GtnWsReportEngineTreeNode;

@Service
@Scope(value = "singleton")
public class GtnWsTreeService {

	@Autowired
	private GtnWsMongoDBConnectionService connection;

	public GtnWsTreeService() {
		super();
	}

	public GtnWsReportEngineTreeNode buildTree(List<Object[]> resultList, GtnWsHierarchyType indicator) {
		GtnWsReportEngineTreeNode root = new GtnWsReportEngineTreeNode();
		root.setLevelNumber(0);
		root.setIndicator(indicator);
		root.setHierarchyNo("");
		for (Object[] results : resultList) {
			GtnWsReportEngineTreeNode child = new GtnWsReportEngineTreeNode();
			child.setHierarchyNo(String.valueOf(results[0]));
			child.setLevelValue(String.valueOf(results[1]));
			child.setLevelName(String.valueOf(results[2]));
			child.setLevelNumber(Integer.parseInt(String.valueOf(results[3])));
			child.setIndicator(indicator);
			addChildrenRecursively(root, child);

		}
		return root;
	}

	private void addChildrenRecursively(GtnWsReportEngineTreeNode parentNode, GtnWsReportEngineTreeNode treeNode) {
		if (treeNode.getLevelNumber() == parentNode.getLevelNumber() + 1) {
			if (treeNode.getHierarchyNo().startsWith(parentNode.getHierarchyNo())) {
				parentNode.addChildren(treeNode);
			}
		} else {
			for (GtnWsReportEngineTreeNode childNode : parentNode.getChildren()) {
				addChildrenRecursively(childNode, treeNode);
			}
		}
	}

	public void buildCustomTree(GtnWsReportEngineTreeNode root, GtnWsCustomTreeData customTreeData,
			GtnWsReportEngineTreeNode customerRootNode, GtnWsReportEngineTreeNode productRootNode,
			List<Object[]> deductionList, List<Object[]> ccpResult) {
		if (customTreeData.getCurrentTreeLevelNo() != 0) {

			processCustomTree(root, customTreeData, customerRootNode, productRootNode, deductionList, ccpResult);

			List<GtnWsReportVariablesType> variableList = customTreeData.getVariableList();
			if (variableList != null) {
				buildFixedVariableTree(root, customTreeData.getCurrentTreeLevelNo(), variableList);
			}

		}

		if (customTreeData.getChild() != null) {
			buildCustomTree(root, customTreeData.getChild(), customerRootNode, productRootNode, deductionList,
					ccpResult);
		}
	}

	public void processCustomTree(GtnWsReportEngineTreeNode root, GtnWsCustomTreeData customTreeData,
			GtnWsReportEngineTreeNode customerRootNode, GtnWsReportEngineTreeNode productRootNode,
			List<Object[]> deductionList, List<Object[]> ccpResult) {

		if (GtnWsHierarchyType.VARIABLES.equals(customTreeData.getHierarchyType())) {
			buildAllVariableTree(root, customTreeData.getCurrentTreeLevelNo(), customTreeData.getVariableList(),
					ccpResult);
		}
		if (GtnWsHierarchyType.DEDUCTION.equals(customTreeData.getHierarchyType())) {
			buildDeductionTree(root, customTreeData.getCurrentTreeLevelNo(), deductionList, ccpResult,
					customTreeData.getLevelNo());
		}

		if (GtnWsHierarchyType.CUSTOMER.equals(customTreeData.getHierarchyType())
				|| GtnWsHierarchyType.PRODUCT.equals(customTreeData.getHierarchyType())) {
			GtnWsReportEngineTreeNode inputTree = GtnWsHierarchyType.CUSTOMER.equals(customTreeData.getHierarchyType())
					? customerRootNode
					: productRootNode;
			List<GtnWsReportEngineTreeNode> allLevelNodeList = new ArrayList<>();
			getAllNodesFromTree(inputTree, allLevelNodeList, customTreeData.getLevelNo());
			buildCustomCCPTree(root, customTreeData.getCurrentTreeLevelNo(), allLevelNodeList, ccpResult);
		}
	}

	public void getAllNodesFromTree(GtnWsReportEngineTreeNode inputTree, List<GtnWsReportEngineTreeNode> outputList,
			int levelNo) {
		if (inputTree.getLevelNumber() > levelNo) {
			return;
		}
		for (GtnWsReportEngineTreeNode gtnWsReportEngineTreeNode : inputTree.getChildren()) {
			if (gtnWsReportEngineTreeNode.getLevelNumber() < levelNo) {
				getAllNodesFromTree(gtnWsReportEngineTreeNode, outputList, levelNo);
			}

			if (gtnWsReportEngineTreeNode.getLevelNumber() == levelNo) {
				outputList.add(gtnWsReportEngineTreeNode);
			}

		}
	}

	public void buildCustomCCPTree(GtnWsReportEngineTreeNode root, int levelNo,
			List<GtnWsReportEngineTreeNode> nodeList, List<Object[]> ccpResult) {

		if (root.getChildren() == null && levelNo == 1) {
			root.setChildren(getRootChildren(nodeList, ccpResult, levelNo));
			return;
		}

		for (GtnWsReportEngineTreeNode gtnWsReportEngineTreeNode : root.getChildren()) {
			if (gtnWsReportEngineTreeNode.getLevelNumber() == levelNo - 1) {
				gtnWsReportEngineTreeNode
						.setChildren(getMatchingChildNode(gtnWsReportEngineTreeNode, nodeList, ccpResult, levelNo));
			} else {
				buildCustomCCPTree(gtnWsReportEngineTreeNode, levelNo, nodeList, ccpResult);
			}
		}
	}

	public void buildDeductionTree(GtnWsReportEngineTreeNode root, int levelNo, List<Object[]> discountList,
			List<Object[]> ccpResult, int discountLevelIndex) {
		if (root.getChildren() == null && levelNo == 1) {
			root.setChildren(getMatchingRootDiscountNode(discountList, levelNo, ccpResult, discountLevelIndex));
			return;
		}
		for (GtnWsReportEngineTreeNode gtnWsReportEngineTreeNode : root.getChildren()) {
			if (gtnWsReportEngineTreeNode.getLevelNumber() == levelNo - 1) {
				if (gtnWsReportEngineTreeNode.getRsIds() == null) {
					gtnWsReportEngineTreeNode.setChildren(
							getMatchingRootDiscountNode(discountList, levelNo, ccpResult, discountLevelIndex));
				} else {
					gtnWsReportEngineTreeNode.setChildren(getMatchingChildDiscountNode(gtnWsReportEngineTreeNode,
							discountList, levelNo, discountLevelIndex));
				}

			} else {
				buildDeductionTree(gtnWsReportEngineTreeNode, levelNo, discountList, ccpResult, discountLevelIndex);
			}
		}

	}

	public void buildFixedVariableTree(GtnWsReportEngineTreeNode root, int levelNo,
			List<GtnWsReportVariablesType> variableList) {
		if (root.getChildren() != null) {
			for (GtnWsReportEngineTreeNode gtnWsReportEngineTreeNode : root.getChildren()) {
				if (gtnWsReportEngineTreeNode.getLevelNumber() == levelNo - 1) {
					for (GtnWsReportVariablesType variable : variableList) {
						GtnWsReportEngineTreeNode node = new GtnWsReportEngineTreeNode();
						node.setLevelName(variable.toString());
						node.setLevelValue(variable.toString());
						node.setLevelNumber(levelNo);
						node.setIndicator(GtnWsHierarchyType.VARIABLES);
						gtnWsReportEngineTreeNode.addVariable(node);
					}
				}
			}
		}
	}

	public void buildAllVariableTree(GtnWsReportEngineTreeNode root, int levelNo,
			List<GtnWsReportVariablesType> variableList, List<Object[]> ccpResult) {

		if (root.getChildren() == null && levelNo == 1) {
			Set<Integer> ccpList = new HashSet<>();
			Set<Integer> rsList = new HashSet<>();
			for (Object[] object : ccpResult) {
				ccpList.add(Integer.valueOf(object[0].toString()));
				if (object[3] != null) {
					rsList.add(Integer.valueOf(object[3].toString()));
				}
			}
			for (GtnWsReportVariablesType variable : variableList) {
				GtnWsReportEngineTreeNode node = new GtnWsReportEngineTreeNode();
				node.setLevelName(variable.toString());
				node.setLevelValue(variable.toString());
				node.setLevelNumber(levelNo);
				node.setIndicator(GtnWsHierarchyType.VARIABLES);
				node.setHierarchyNo(levelNo + "~" + variable);
				node.setDiscountAvailable(root.isDiscountAvailable());
				node.addAllCcpIds(ccpList);
				node.addAllRsIds(rsList);
				root.addChildren(node);
			}
			return;
		}

		for (GtnWsReportEngineTreeNode gtnWsReportEngineTreeNode : root.getChildren()) {
			if (gtnWsReportEngineTreeNode.getLevelNumber() == levelNo - 1) {
				for (GtnWsReportVariablesType variable : variableList) {
					GtnWsReportEngineTreeNode node = new GtnWsReportEngineTreeNode();
					node.setLevelName(variable.toString());
					node.setLevelValue(variable.toString());
					node.setLevelNumber(levelNo);
					node.setIndicator(GtnWsHierarchyType.VARIABLES);
					node.setHierarchyNo(gtnWsReportEngineTreeNode.getHierarchyNo() + "~"
							+ gtnWsReportEngineTreeNode.getIndicator().toString() + "~" + variable);
					node.setCcpIds(gtnWsReportEngineTreeNode.getCcpIds());
					node.setRsIds(gtnWsReportEngineTreeNode.getRsIds());
					node.setDiscountAvailable(root.isDiscountAvailable());
					node.setParentHierarchyNo(gtnWsReportEngineTreeNode.getParentHierarchyNo());
					node.setParentIndicator(gtnWsReportEngineTreeNode.getParentIndicator());
					gtnWsReportEngineTreeNode.addChildren(node);
				}
			} else {
				buildAllVariableTree(gtnWsReportEngineTreeNode, levelNo, variableList, ccpResult);
			}
		}
	}

	private List<GtnWsReportEngineTreeNode> getMatchingChildNode(GtnWsReportEngineTreeNode root,
			List<GtnWsReportEngineTreeNode> nodeList, List<Object[]> ccpResult, int currentLevel) {

		if (root.getParentHierarchyNo() != null) {
			return applyParentHierarchyFilter(root, nodeList, ccpResult, currentLevel);
		}
		if (root.getHierarchyNo().contains(".")) {
			return applyHierarchyFilter(root, nodeList, ccpResult, currentLevel);
		}

		return applyLevelOneDeductionFilter(root, nodeList, ccpResult, currentLevel);
	}

	private List<GtnWsReportEngineTreeNode> getMatchingChildDiscountNode(GtnWsReportEngineTreeNode root,
			List<Object[]> discountList, int currentLevel, int discountLevelIndex) {

		Map<Object, Set<Integer>> distinctDiscountNode = new HashMap<>();
		for (int rsId : root.getRsIds()) {
			for (Object[] discount : discountList) {
				if (rsId == Integer.parseInt(discount[0].toString())) {
					Set<Integer> rsIdList = distinctDiscountNode.get(discount[discountLevelIndex]);
					if (rsIdList == null) {
						rsIdList = new HashSet<>();
						distinctDiscountNode.put(discount[discountLevelIndex], rsIdList);
					}
					rsIdList.add(rsId);
				}
			}

		}

		List<GtnWsReportEngineTreeNode> resultList = new ArrayList<>();
		for (Entry<Object, Set<Integer>> discountNode : distinctDiscountNode.entrySet()) {
			GtnWsReportEngineTreeNode discountTreeNode = new GtnWsReportEngineTreeNode();
			discountTreeNode.setHierarchyNo(
					root.getHierarchyNo() + "~" + root.getIndicator().toString() + "~" + discountNode.getKey());
			discountTreeNode.setRsIds(discountNode.getValue());
			discountTreeNode.setLevelValue(discountNode.getKey().toString());
			discountTreeNode.setIndicator(GtnWsHierarchyType.DEDUCTION);
			discountTreeNode.setParentHierarchyNo(root.getParentHierarchyNo());
			discountTreeNode.setParentIndicator(root.getParentIndicator());
			discountTreeNode.setLevelNumber(currentLevel);
			discountTreeNode.setDiscountAvailable(true);
			resultList.add(discountTreeNode);
		}

		return resultList;
	}

	private List<GtnWsReportEngineTreeNode> getMatchingRootDiscountNode(List<Object[]> discountList, int levelNo,
			List<Object[]> ccpResult, int discountLevelIndex) {

		Map<Object, Set<Integer>> distinctDiscountNode = new HashMap<>();
		for (Object[] rsId : ccpResult) {
			if (rsId[3] != null) {
				for (Object[] discount : discountList) {
					if (Integer.parseInt(rsId[3].toString()) == Integer.parseInt(discount[0].toString())) {
						Set<Integer> rsIdList = distinctDiscountNode.get(discount[discountLevelIndex]);
						if (rsIdList == null) {
							rsIdList = new HashSet<>();
							distinctDiscountNode.put(discount[discountLevelIndex], rsIdList);
						}
						rsIdList.add(Integer.valueOf(rsId[3].toString()));
					}
				}

			}

		}

		List<GtnWsReportEngineTreeNode> resultList = new ArrayList<>();
		for (Entry<Object, Set<Integer>> discountNode : distinctDiscountNode.entrySet()) {
			GtnWsReportEngineTreeNode discountTreeNode = new GtnWsReportEngineTreeNode();
			discountTreeNode.setHierarchyNo(discountNode.getKey().toString());
			discountTreeNode.setRsIds(discountNode.getValue());
			discountTreeNode.setIndicator(GtnWsHierarchyType.DEDUCTION);
			discountTreeNode.setLevelNumber(levelNo);
			discountTreeNode.setLevelValue(discountNode.getKey().toString());
			discountTreeNode.setDiscountAvailable(true);
			resultList.add(discountTreeNode);
		}

		return resultList;
	}

	private List<GtnWsReportEngineTreeNode> applyParentHierarchyFilter(GtnWsReportEngineTreeNode root,
			List<GtnWsReportEngineTreeNode> nodeList, List<Object[]> ccpResult, int currentLevel) {

		List<GtnWsReportEngineTreeNode> resultList = new ArrayList<>();
		int parentHierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(root.getParentIndicator()) ? 1 : 2;
		int currentHierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(root.getIndicator()) ? 1 : 2;
		String hierarchyNo = root.getHierarchyNo();

		boolean discountFlag = GtnWsHierarchyType.DEDUCTION.equals(root.getIndicator());
		boolean allVariableFlag = GtnWsHierarchyType.VARIABLES.equals(root.getIndicator());
		if (discountFlag || allVariableFlag) {
			String[] discountHierarchyNo = root.getHierarchyNo().split("~");
			currentHierarchyIndex = GtnWsHierarchyType.CUSTOMER
					.equals(GtnWsHierarchyType.fromString(discountHierarchyNo[1])) ? 1 : 2;
			hierarchyNo = discountHierarchyNo[0];
		}
		for (GtnWsReportEngineTreeNode node : nodeList) {
			int hierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(node.getIndicator()) ? 1 : 2;
			GtnWsReportEngineTreeNode copyNode = null;
			for (Object[] objects : ccpResult) {

				if (String.valueOf(objects[parentHierarchyIndex]).startsWith(root.getParentHierarchyNo())) {
					if (String.valueOf(objects[currentHierarchyIndex]).startsWith(hierarchyNo)) {
						if (String.valueOf(objects[hierarchyIndex]).startsWith(node.getHierarchyNo())) {
							copyNode = createNewNode(copyNode, objects, root, node, currentLevel);
						}
					}
				}
			}

			if (copyNode != null) {
				resultList.add(copyNode);
			}

		}

		return resultList;

	}

	private void setParentDetails(GtnWsReportEngineTreeNode root, GtnWsReportEngineTreeNode copyNode) {
		if (root.getIndicator().equals(copyNode.getIndicator())
				|| GtnWsHierarchyType.DEDUCTION.equals(root.getIndicator())
				|| GtnWsHierarchyType.VARIABLES.equals(root.getIndicator())) {
			copyNode.setParentIndicator(root.getParentIndicator());
			copyNode.setParentHierarchyNo(root.getParentHierarchyNo());
		} else {
			copyNode.setParentIndicator(root.getIndicator());
			copyNode.setParentHierarchyNo(root.getHierarchyNo());
		}

		copyNode.setDiscountAvailable(root.isDiscountAvailable());
	}

	private List<GtnWsReportEngineTreeNode> applyHierarchyFilter(GtnWsReportEngineTreeNode root,
			List<GtnWsReportEngineTreeNode> nodeList, List<Object[]> ccpResult, int currentLevel) {
		List<GtnWsReportEngineTreeNode> resultList = new ArrayList<>();
		int currentHierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(root.getIndicator()) ? 1 : 2;
		String hierarchyNo = root.getHierarchyNo();

		boolean discountFlag = GtnWsHierarchyType.DEDUCTION.equals(root.getIndicator());
		boolean allVariableFlag = GtnWsHierarchyType.VARIABLES.equals(root.getIndicator());
		if (discountFlag || allVariableFlag) {
			String[] discountHierarchyNo = root.getHierarchyNo().split("~");
			currentHierarchyIndex = GtnWsHierarchyType.CUSTOMER
					.equals(GtnWsHierarchyType.fromString(discountHierarchyNo[1])) ? 1 : 2;
			hierarchyNo = discountHierarchyNo[0];
		}

		for (GtnWsReportEngineTreeNode node : nodeList) {
			int hierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(node.getIndicator()) ? 1 : 2;
			GtnWsReportEngineTreeNode copyNode = null;
			for (Object[] objects : ccpResult) {
				if (String.valueOf(objects[currentHierarchyIndex]).startsWith(hierarchyNo)) {
					if (String.valueOf(objects[hierarchyIndex]).startsWith(node.getHierarchyNo())) {
						copyNode = createNewNode(copyNode, objects, root, node, currentLevel);
					}
				}
			}

			if (copyNode != null) {
				resultList.add(copyNode);
			}

		}

		return resultList;

	}

	private List<GtnWsReportEngineTreeNode> applyLevelOneDeductionFilter(GtnWsReportEngineTreeNode root,
			List<GtnWsReportEngineTreeNode> nodeList, List<Object[]> ccpResult, int currentLevel) {
		List<GtnWsReportEngineTreeNode> resultList = new ArrayList<>();

		for (GtnWsReportEngineTreeNode node : nodeList) {
			int hierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(node.getIndicator()) ? 1 : 2;
			GtnWsReportEngineTreeNode copyNode = null;
			for (Object[] objects : ccpResult) {
				if (String.valueOf(objects[hierarchyIndex]).startsWith(node.getHierarchyNo())) {
					copyNode = createNewNode(copyNode, objects, root, node, currentLevel);
				}
			}

			if (copyNode != null) {
				resultList.add(copyNode);
			}

		}

		return resultList;

	}

	private GtnWsReportEngineTreeNode createNewNode(GtnWsReportEngineTreeNode copyNode, Object[] objects,
			GtnWsReportEngineTreeNode root, GtnWsReportEngineTreeNode node, int currentLevel) {
		GtnWsReportEngineTreeNode copiedNode = copyNode;
		if (root.isDiscountAvailable()) {
			if (objects[3] != null && root.getRsIds().contains(objects[3])) {
				if (copiedNode == null) {
					copiedNode = node.copy();
					copiedNode.setLevelNumber(currentLevel);
					setParentDetails(root, copiedNode);
				}
				root.addCcpIds(Integer.valueOf(objects[0].toString()));
				copiedNode.addCcpIds(Integer.valueOf(objects[0].toString()));
				copiedNode.addRsIds(Integer.valueOf(objects[3].toString()));
			}

		} else {
			if (copiedNode == null) {
				copiedNode = node.copy();
				copiedNode.setLevelNumber(currentLevel);
				setParentDetails(root, copiedNode);
			}
			copiedNode.addCcpIds(Integer.valueOf(objects[0].toString()));
			if (objects[3] != null) {
				copiedNode.addRsIds(Integer.valueOf(objects[3].toString()));
			}
		}
		return copiedNode;
	}

	private List<GtnWsReportEngineTreeNode> getRootChildren(List<GtnWsReportEngineTreeNode> nodeList,
			List<Object[]> ccpResult, int currentLevel) {
		List<GtnWsReportEngineTreeNode> resultList = new ArrayList<>();

		for (GtnWsReportEngineTreeNode node : nodeList) {
			int hierarchyIndex = GtnWsHierarchyType.CUSTOMER.equals(node.getIndicator()) ? 1 : 2;
			GtnWsReportEngineTreeNode copyNode = null;
			for (Object[] objects : ccpResult) {
				if (String.valueOf(objects[hierarchyIndex]).startsWith(node.getHierarchyNo())) {
					if (copyNode == null) {
						copyNode = node.copy();
						copyNode.setLevelNumber(currentLevel);
					}
					copyNode.addCcpIds(Integer.valueOf(objects[0].toString()));
					if (objects[3] != null) {
						copyNode.addRsIds(Integer.valueOf(objects[3].toString()));
					}

				}
			}

			if (copyNode != null) {
				resultList.add(copyNode);
			}

		}

		return resultList;

	}

	public GtnWsCustomTreeData getCustomTreeData(String tableName, String customViewName) {
		MongoCollection<GtnWsReportCustomViewDataBean> collection = connection.getDBInstance().getCollection(tableName,
				GtnWsReportCustomViewDataBean.class);
		FindIterable<GtnWsReportCustomViewDataBean> selectionBean = collection
				.find(and(eq("customViewName", customViewName)));
		return selectionBean.first().getCustomTreeData();
	}

	public GtnWsReportEngineTreeNode getCustomerTree(String tableName, boolean withSessionInfo, String sessionId) {
		MongoCollection<GtnWsReportEngineTreeNode> collection = connection.getDBInstance().getCollection(tableName,
				GtnWsReportEngineTreeNode.class);
		if (withSessionInfo) {
			return collection.find(and(eq("sessionId", sessionId))).first();
		}
		return collection.find().first();
	}

	public void saveCustomTree(GtnWsReportEngineTreeNode root, String tableName) {
		MongoCollection<GtnWsReportEngineTreeNode> collection = connection.getDBInstance().getCollection(tableName,
				GtnWsReportEngineTreeNode.class);
		collection.insertOne(root);
	}

}
