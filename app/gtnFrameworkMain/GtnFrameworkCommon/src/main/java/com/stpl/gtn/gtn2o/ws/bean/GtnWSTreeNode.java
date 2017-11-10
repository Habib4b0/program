package com.stpl.gtn.gtn2o.ws.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class GtnWSTreeNode implements Serializable {

	public GtnWSTreeNode() {
		super();

	}

	private static final long serialVersionUID = 1L;

	private GtnWsRecordBean nodeData;

	private Integer level;

	private List<GtnWSTreeNode> children;

	private GtnWSTreeNode parent;

	private String treeCode;

	public GtnWsRecordBean getNodeData() {
		return nodeData;
	}

	public void setNodeData(GtnWsRecordBean nodeData) {
		this.nodeData = nodeData;
	}

	public List<GtnWSTreeNode> getChildren() {
		return children != null ? new ArrayList<>(children) : null;
	}

	public void setChildren(List<GtnWSTreeNode> children) {
		this.children = children != null ? new ArrayList<>(children) : null;
	}

	public GtnWSTreeNode getParent() {
		return parent;
	}

	public void setParent(GtnWSTreeNode parent) {
		this.parent = parent;
	}

	public GtnWSTreeNode addChildren(GtnWSTreeNode treeNode) {
		if (children != null) {
			for (GtnWSTreeNode childNode : children) {
				if (treeNode.equals(childNode)) {
					return childNode;
				}
			}
		}

		treeNode.setParent(this);
		if (this.children == null) {
			children = new ArrayList<>();
		}
		children.add(treeNode);
		return treeNode;
	}

	public String getTreeCode() {
		return treeCode;
	}

	public void setTreeCode(String treeCode) {
		this.treeCode = treeCode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.level);
		hash = 37 * hash + Objects.hashCode(this.treeCode);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final GtnWSTreeNode other = (GtnWSTreeNode) obj;
		if (!Objects.equals(this.level, other.level)) {
			return false;
		}
		return Objects.equals(this.treeCode, other.treeCode);
	}

	@Override
	public String toString() {
		return "TreeNode{" + "nodeData=" + nodeData + ", level=" + level + ", hierarchyNo=" + treeCode + '}';
	}

}