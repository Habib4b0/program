/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.duallistbox;

import java.util.ArrayList;
import java.util.List;

import org.asi.container.ExtTreeContainer;

import com.stpl.gtn.gtn2o.ws.bean.GtnWSTreeNode;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.vaadin.v7.ui.TreeTable;

/**
 *
 * @author STPL
 */
public class GtnUIFrameworkHierarchyTreeBuilder {

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnUIFrameworkHierarchyTreeBuilder.class);

	private GtnWSTreeNode rootTreeNode;

	public GtnUIFrameworkHierarchyTreeBuilder() {
		super();
	}

	public GtnWSTreeNode buildTree(List<GtnWsRecordBean> inputDataList) {
		if (this.rootTreeNode == null) {
			this.createRootNode();
		}
		for (GtnWsRecordBean data : inputDataList) {
			List<Object> record = data.getAdditionalProperties();
			GtnWSTreeNode treeNode = new GtnWSTreeNode();
			treeNode.setTreeCode(record.get(1).toString());
			treeNode.setLevel(Integer.valueOf(record.get(2).toString()));
			treeNode.setNodeData(data);
			if (treeNode.getLevel() == rootTreeNode.getLevel() + 1) {
				rootTreeNode.addChildren(treeNode);
			} else {
				addChildrenRecursively(rootTreeNode, treeNode);
			}
		}
		return rootTreeNode;
	}

	private void addChildrenRecursively(GtnWSTreeNode parentNode, GtnWSTreeNode treeNode) {
		if (treeNode.getLevel() == parentNode.getLevel() + 1) {
			if (treeNode.getTreeCode().startsWith(parentNode.getTreeCode())) {
				parentNode.addChildren(treeNode);
			}
		} else {
			if (parentNode.getChildren() != null) {
				for (GtnWSTreeNode childNode : parentNode.getChildren()) {
					addChildrenRecursively(childNode, treeNode);
				}
			}
		}
	}

	private void createRootNode() {
		this.rootTreeNode = new GtnWSTreeNode();
		this.rootTreeNode.setLevel(0);
		this.rootTreeNode.setTreeCode(null);
		this.rootTreeNode.setNodeData(null);
	}

	public boolean deleteNode(GtnWSTreeNode node) {
		LOGGER.info("Entering  into deleteNode(TreeNode node) of TreeBuilder ");

		try {
			if (node == null || node.getNodeData() == null) {
				return false;
			}
			List<GtnWSTreeNode> childNodeList = node.getParent().getChildren();
			if (childNodeList == null) {
				return false;
			}
			childNodeList.remove(node);

			if (childNodeList.isEmpty()) {
				deleteNode(node.getParent());
			}
			node.setParent(null);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return false;
		}
		LOGGER.info("End  into deleteNode(TreeNode node)  of TreeBuilder ");

		return true;

	}

	public boolean deleteNode(GtnWsRecordBean gtnWsRecordBean) {
		return deleteNode(
				readTreeNodeRecursively(rootTreeNode, gtnWsRecordBean.getAdditionalProperties().get(1).toString()));
	}

	private GtnWSTreeNode readTreeNodeRecursively(final GtnWSTreeNode rootNode, final String hierarchyNo) {

		GtnWSTreeNode node = null;

		if (rootNode.getChildren() != null) {
			for (GtnWSTreeNode treeNode : rootNode.getChildren()) {
				if (hierarchyNo.equals(treeNode.getTreeCode())) {
					return treeNode;
				} else {
					node = readTreeNodeRecursively(treeNode, hierarchyNo);
				}
			}
		}
		return node;
	}

	public GtnWSTreeNode getRootTreeNode() {
		return rootTreeNode;
	}

	public void clearTree() {
		if (rootTreeNode != null && rootTreeNode.getChildren() != null) {
			rootTreeNode.getChildren().clear();
		}
	}

	public List<GtnWsRecordBean> getAllNodeDataAsList() {
		List<GtnWsRecordBean> list = new ArrayList<>();
		if (rootTreeNode != null && rootTreeNode.getChildren() != null) {
			for (GtnWSTreeNode treeNode : rootTreeNode.getChildren()) {
				addTreeNodeDataRecursively(treeNode, list);
			}
		}
		return list;
	}

	private void addTreeNodeDataRecursively(GtnWSTreeNode treeNode, List<GtnWsRecordBean> dataList) {
		dataList.add(treeNode.getNodeData());
		if (treeNode.getChildren() != null) {
			for (GtnWSTreeNode childNode : treeNode.getChildren()) {
				addTreeNodeDataRecursively(childNode, dataList);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public void loadRightTreeTable(TreeTable treeTable, int loadingLevel) {
		ExtTreeContainer<GtnWsRecordBean> container = (ExtTreeContainer<GtnWsRecordBean>) treeTable
				.getContainerDataSource();
		container.removeAllItems();
		recursiveLoad(rootTreeNode, container, loadingLevel);
	}

	private void recursiveLoad(GtnWSTreeNode node, ExtTreeContainer<GtnWsRecordBean> container, int loadingLevel) {
		if (node == null) {
			return;
		}
		List<GtnWSTreeNode> childeren = node.getChildren();
		if (childeren == null) {
			container.setChildrenAllowed(node.getNodeData(), false);
			return;
		}
		if (node.getLevel() > loadingLevel - 1) {
			container.setChildrenAllowed(node.getNodeData(), false);
			return;
		}
		container.setChildrenAllowed(node.getNodeData(), true);
		for (GtnWSTreeNode childNode : childeren) {
			container.addBean(childNode.getNodeData());
			if (node.getNodeData() != null) {
				container.setParent(childNode.getNodeData(), node.getNodeData());
			}
			recursiveLoad(childNode, container, loadingLevel);
		}
	}

	public void clearRootNode() {
		if ((rootTreeNode != null)&&(rootTreeNode.getChildren() != null)) {
				rootTreeNode.getChildren().clear();
		}
		rootTreeNode = null;
	}

}
