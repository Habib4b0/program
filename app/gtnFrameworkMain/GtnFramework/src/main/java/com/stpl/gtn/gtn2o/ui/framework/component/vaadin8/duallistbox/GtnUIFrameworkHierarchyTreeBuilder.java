/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.component.vaadin8.duallistbox;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ws.bean.GtnWSTreeNode;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.vaadin.ui.TreeGrid;

/**
 *
 * @author STPL
 */
public class GtnUIFrameworkHierarchyTreeBuilder {

	private GtnWSTreeNode gtnWsRootTreeNode;

	public GtnUIFrameworkHierarchyTreeBuilder() {
		super();
	}

	public GtnWSTreeNode buildTree(List<GtnWsRecordBean> inputDataList) {
		if (this.gtnWsRootTreeNode == null) {
			this.createRootNode();
		}
		for (GtnWsRecordBean data : inputDataList) {
			List<Object> record = data.getProperties();
			GtnWSTreeNode gtnWsTreeNode = new GtnWSTreeNode();
			gtnWsTreeNode.setTreeCode(record.get(8).toString());
			gtnWsTreeNode.setLevel(Integer.valueOf(record.get(0).toString()));
			gtnWsTreeNode.setNodeData(data);
			if (gtnWsTreeNode.getLevel() == gtnWsRootTreeNode.getLevel() + 1) {
				gtnWsRootTreeNode.addChildren(gtnWsTreeNode);
			} else {
				addChildrenRecursively(gtnWsRootTreeNode, gtnWsTreeNode);
			}
		}
		return gtnWsRootTreeNode;
	}

	private void addChildrenRecursively(GtnWSTreeNode parentNode, GtnWSTreeNode gtnWsTreeNode) {
		if (gtnWsTreeNode.getLevel() == parentNode.getLevel() + 1) {
			if (gtnWsTreeNode.getTreeCode().startsWith(parentNode.getTreeCode())) {
				parentNode.addChildren(gtnWsTreeNode);
			}
		} else {
			if (parentNode.getChildren() != null) {
				for (GtnWSTreeNode childTreeNode : parentNode.getChildren()) {
					addChildrenRecursively(childTreeNode, gtnWsTreeNode);
				}
			}
		}
	}

	private void createRootNode() {
		this.gtnWsRootTreeNode = new GtnWSTreeNode();
		this.gtnWsRootTreeNode.setLevel(0);
		this.gtnWsRootTreeNode.setTreeCode(null);
		this.gtnWsRootTreeNode.setNodeData(null);
	}

	public void deleteNode(GtnWsRecordBean gtnWsRecordBean) {
		deleteTreeNodeRecursively(gtnWsRootTreeNode, gtnWsRecordBean.getProperties().get(8).toString());
	}

	public GtnWSTreeNode getRootTreeNode() {
		return gtnWsRootTreeNode;
	}

	public void clearTree() {
		if (gtnWsRootTreeNode != null && gtnWsRootTreeNode.getChildren() != null) {
			gtnWsRootTreeNode.getChildren().clear();
		}
	}

	public List<GtnWsRecordBean> getAllNodeDataAsList() {
		List<GtnWsRecordBean> list = new ArrayList<>();
		if (gtnWsRootTreeNode != null && gtnWsRootTreeNode.getChildren() != null) {
			for (GtnWSTreeNode treeNode : gtnWsRootTreeNode.getChildren()) {
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
	public void loadRightTreeTable(TreeGrid<GtnWsRecordBean> treeTable, int loadingLevel) {
		treeTable.getTreeData().clear();
		recursiveLoad(gtnWsRootTreeNode, treeTable, loadingLevel);
	}

	private void recursiveLoad(GtnWSTreeNode node, TreeGrid<GtnWsRecordBean> treeGrid, int loadingLevel) {

		if (node == null) {
			return;
		}
		List<GtnWSTreeNode> childeren = node.getChildren();
		if (childeren == null) {
			return;
		}
		for (GtnWSTreeNode childNode : childeren) {
			if (node.getLevel() == 0) {
				treeGrid.getTreeData().addItem(null, childNode.getNodeData());
			} else {
				treeGrid.getTreeData().addItem(node.getNodeData(), childNode.getNodeData());

			}
			recursiveLoad(childNode, treeGrid, loadingLevel);
		}
	}

	public void clearRootNode() {
		if ((gtnWsRootTreeNode != null) && (gtnWsRootTreeNode.getChildren() != null)) {
			gtnWsRootTreeNode.getChildren().clear();
		}
		gtnWsRootTreeNode = null;
	}

	private void deleteTreeNodeRecursively(final GtnWSTreeNode treeRootNode, final String hierarchyNo) {
		if (treeRootNode.getChildren() != null) {
			for (GtnWSTreeNode treeNode : treeRootNode.getChildren()) {
				if (hierarchyNo.equals(treeNode.getTreeCode())) {
					treeRootNode.removeChildren(treeNode);
				} else {
					deleteTreeNodeRecursively(treeNode, hierarchyNo);
				}
			}
		}
	}

}
