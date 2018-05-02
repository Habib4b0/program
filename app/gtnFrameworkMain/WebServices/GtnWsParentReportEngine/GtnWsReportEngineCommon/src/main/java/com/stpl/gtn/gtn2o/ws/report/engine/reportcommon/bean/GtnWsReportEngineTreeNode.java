package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

public class GtnWsReportEngineTreeNode implements Serializable {

	private static final long serialVersionUID = 1L;

	private transient Object nodeData;

	private List<GtnWsReportEngineTreeNode> children;

	private int levelNumber;

	private String hierarchyNo;

	private String parentHierarchyNo;

	private String parentIndicator;

	private String levelName;

	private String levelValue;

	private int nodeIndex = -1;

	private Set<Integer> ccpIds = null;

	private Set<Integer> rsIds = null;

	private String indicator = StringUtils.EMPTY;

	private boolean isDiscountAvailable;

	public Object getNodeData() {
		return nodeData;
	}

	public void setNodeData(Object nodeData) {
		this.nodeData = nodeData;
	}

	public List<GtnWsReportEngineTreeNode> getChildren() {
		return children;
	}

	public void setChildren(List<GtnWsReportEngineTreeNode> children) {
		this.children = children;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getHierarchyNo() {
		return hierarchyNo;
	}

	public void setHierarchyNo(String hierarchyNo) {
		this.hierarchyNo = hierarchyNo;
	}

	public int getLevelNumber() {
		return levelNumber;
	}

	public void setLevelNumber(int levelNumber) {
		this.levelNumber = levelNumber;
	}

	public String getLevelValue() {
		return levelValue;
	}

	public void setLevelValue(String levelValue) {
		this.levelValue = levelValue;
	}

	public int getNodeIndex() {
		return nodeIndex;
	}

	public void setNodeIndex(int nodeIndex) {
		this.nodeIndex = nodeIndex;
	}

	public GtnWsReportEngineTreeNode addChildren(GtnWsReportEngineTreeNode treeNode) {

		if (children != null) {
			for (GtnWsReportEngineTreeNode childNode : children) {
				if (treeNode.equals(childNode)) {
					if (treeNode.getCcpIds() != null) {
						childNode.addAllCcpIds(treeNode.getCcpIds());
						childNode.addAllRsIds(treeNode.getRsIds());
					}
					return childNode;
				}
			}
		}

		if (this.children == null) {
			children = new ArrayList<>();
		}
		children.add(treeNode);
		return treeNode;
	}

	public Set<Integer> getCcpIds() {
		return ccpIds;
	}

	public void addCcpIds(Integer ccpId) {
		if (ccpIds == null) {
			ccpIds = new HashSet<>();
		}
		ccpIds.add(ccpId);
	}

	public Set<Integer> getRsIds() {
		return rsIds;
	}

	public void addRsIds(Integer rsId) {
		if (rsIds == null) {
			rsIds = new HashSet<>();
		}
		rsIds.add(rsId);
	}

	public void addAllRsIds(Set<Integer> rsId) {
		if (rsIds == null && rsId != null) {
			rsIds = new HashSet<>();
		}
		if (rsId != null) {
			rsIds.addAll(rsId);
		}
	}

	public void addAllCcpIds(Set<Integer> ccpId) {
		if (ccpIds == null) {
			ccpIds = new HashSet<>();
		}
		ccpIds.addAll(ccpId);
	}

	public String getIndicator() {
		return indicator;
	}

	public void setIndicator(String indicator) {
		this.indicator = indicator;
	}

	public void setCcpIds(Set<Integer> ccpIds) {
		this.ccpIds = ccpIds;
	}

	public void setRsIds(Set<Integer> rsIds) {
		this.rsIds = rsIds;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 37 * hash + Objects.hashCode(this.hierarchyNo);
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
		final GtnWsReportEngineTreeNode other = (GtnWsReportEngineTreeNode) obj;
		return Objects.equals(this.hierarchyNo, other.hierarchyNo);
	}

	@Override
	public String toString() {
		return "TreeNode{" + " levelNumber=" + levelNumber + ", hierarchyNo=" + hierarchyNo + ",levelValue="
				+ levelValue + " ccp ids = " + ccpIds + " RS = " + rsIds + "}";
	}

	private void writeObject(java.io.ObjectOutputStream s) throws java.io.IOException {
		s.defaultWriteObject();
	}

	// Dont delete. this Method is called during Serialization
	private void readObject(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		ois.defaultReadObject();
	}

	public String getParentHierarchyNo() {
		return parentHierarchyNo;
	}

	public void setParentHierarchyNo(String parentHierarchyNo) {
		this.parentHierarchyNo = parentHierarchyNo;
	}

	public String getParentIndicator() {
		return parentIndicator;
	}

	public void setParentIndicator(String parentIndicator) {
		this.parentIndicator = parentIndicator;
	}

	public GtnWsReportEngineTreeNode copy() {
		GtnWsReportEngineTreeNode gtnWsReportEngineTreeNode = new GtnWsReportEngineTreeNode();

		gtnWsReportEngineTreeNode.setNodeData(this.nodeData);
		gtnWsReportEngineTreeNode.setLevelNumber(levelNumber);
		gtnWsReportEngineTreeNode.setHierarchyNo(hierarchyNo);
		gtnWsReportEngineTreeNode.setParentHierarchyNo(parentHierarchyNo);
		gtnWsReportEngineTreeNode.setParentIndicator(parentIndicator);
		gtnWsReportEngineTreeNode.setLevelName(levelName);
		gtnWsReportEngineTreeNode.setLevelValue(levelValue);
		gtnWsReportEngineTreeNode.setNodeIndex(nodeIndex);
		gtnWsReportEngineTreeNode.setCcpIds(ccpIds);
		gtnWsReportEngineTreeNode.setRsIds(rsIds);
		gtnWsReportEngineTreeNode.setIndicator(indicator);

		return gtnWsReportEngineTreeNode;
	}

	public boolean isDiscountAvailable() {
		return isDiscountAvailable;
	}

	public void setDiscountAvailable(boolean isDiscountAvailable) {
		this.isDiscountAvailable = isDiscountAvailable;
	}
}
