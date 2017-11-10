/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.forecast.service.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.forecast.service.GtnWsReturnsResourceService;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author STPL
 */
@Service
@Scope("singleton")
public class GtnWsTreeService {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsTreeService.class);

	@Autowired
	GtnWsReturnsResourceService resourceService;

	public GtnWsTreeNode buildTree(List<Object[]> resultList, final Map<String, String> hierarchyNames) {

		GtnWsTreeNode rootNode = new GtnWsTreeNode();
		rootNode.setLevelNumber(0);
		rootNode.setHierarchyNo(StringUtils.EMPTY);
		int initialLevelNo = 0;
		int index = 0;
		for (Object[] resultValue : resultList) {
			GtnWsTreeNode treeNode = new GtnWsTreeNode();
			treeNode.setHierarchyNo(resultValue[2].toString());
			treeNode.setLevelValue(hierarchyNames.get(treeNode.getHierarchyNo()));
			treeNode.setLevelName(resultValue[1].toString());
			treeNode.setLevelNumber(Integer.valueOf(resultValue[0].toString()));
			treeNode.setNodeIndex(index++);
			initialLevelNo = initialLevelNo == 0 ? treeNode.getLevelNumber() : initialLevelNo;

			if (treeNode.getLevelNumber() == initialLevelNo) {
				rootNode.addChildren(treeNode);
			} else {
				addChildrenRecursively(rootNode, treeNode);
			}
		}

		return rootNode;
	}

	private void addChildrenRecursively(GtnWsTreeNode parentNode, GtnWsTreeNode treeNode) {
		if (treeNode.getLevelNumber() == parentNode.getLevelNumber() + 1) {
			if (treeNode.getHierarchyNo().startsWith(parentNode.getHierarchyNo())) {
				parentNode.addChildren(treeNode);
			}
		} else {
			for (GtnWsTreeNode childNode : parentNode.getChildren()) {
				addChildrenRecursively(childNode, treeNode);
			}
		}
	}

	public List<List<Object>> loadDataFromMasterFile(List<List<Object>> masterSource, List<List<Object>> resultset,
			GtnWsTreeNode rootNode) {
		loadMasterData(null, rootNode, masterSource, resultset);
		return resultset;
	}

	private void loadMasterData(Object object, GtnWsTreeNode rootNode, List<List<Object>> masterSource,
			List<List<Object>> resultSet) {

		for (int i = 0; i < masterSource.size(); i++) {
			for (GtnWsTreeNode children : rootNode.getChildren()) {
				addMasterData(masterSource.get(i), resultSet, children);
			}
		}
	}

	private void addMasterData(List<Object> masterSourceRow, List<List<Object>> resultSet, GtnWsTreeNode node) {

		try {

			if (node.getNodeIndex() == resultSet.size()) {
				resultSet.add(new ArrayList<>());
			}
			if (masterSourceRow.get(0).toString().startsWith(node.getHierarchyNo())) {
				resultSet.set(node.getNodeIndex(), new ArrayList<>(masterSourceRow));
			}

			if (node.getChildren() != null) {
				for (GtnWsTreeNode children : node.getChildren()) {
					addMasterData(masterSourceRow, resultSet, children);
				}
			}

		} catch (Exception ex) {
			LOGGER.error(ex.getMessage());
		}

	}

}
