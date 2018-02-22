package com.stpl.gtn.gtn2o.ws.report.engine.reportcommon.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import org.apache.commons.lang3.StringUtils;

public class GtnWsTreeNode implements Serializable {

    private static final long serialVersionUID = 1L;

    private Object nodeData;

    private int levelNumber;

    private List<GtnWsTreeNode> children;

    private GtnWsTreeNode parent;

    private String hierarchyNo;

    private String levelName;

    private String levelValue;

    private int nodeIndex = -1;

    private TreeSet<Integer> dataIndex;

    private int totalDataIndex;

    private boolean checkedNode = false;

    private String hierarchyNoForTable = StringUtils.EMPTY;

    private Set<Integer> ccpIds = null;

    private Set<Integer> rsIds = null;

    private String indicator = StringUtils.EMPTY;
    
    public Object getNodeData() {
        return nodeData;
    }

    public void setNodeData(Object nodeData) {
        this.nodeData = nodeData;
    }

    public List<GtnWsTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<GtnWsTreeNode> children) {
        this.children = children;
    }

    public GtnWsTreeNode getParent() {
        return parent;
    }

    public void setParent(GtnWsTreeNode parent) {
        this.parent = parent;
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

    public TreeSet<Integer> getDataIndex() {
        return dataIndex;
    }

    public void setDataIndex(TreeSet<Integer> dataIndex) {
        this.dataIndex = dataIndex;
    }

    public int getTotalDataIndex() {
        return totalDataIndex;
    }

    public void setTotalDataIndex(int totalDataIndex) {
        this.totalDataIndex = totalDataIndex;
    }

    public String getHierarchyNoForTable() {
        return hierarchyNoForTable;
    }

    public void setHierarchyNoForTable(String hierarchyNoForTable) {
        this.hierarchyNoForTable = hierarchyNoForTable;
    }

    public GtnWsTreeNode addChildren(GtnWsTreeNode treeNode) {

        if (children != null) {
            for (GtnWsTreeNode childNode : children) {
                if (treeNode.equals(childNode)) {
                    if (treeNode.getCcpIds() != null) {
                        childNode.addAllCcpIds(treeNode.getCcpIds());
                        childNode.addAllRsIds(treeNode.getRsIds());
                    }
                    return childNode;
                }
            }
        }

        treeNode.setParent(this);
        if (this.children == null) {
            children = new ArrayList<>();
        }
        treeNode.generatehierarchyNumberForTable(children.size() + 1);
        children.add(treeNode);
        return treeNode;
    }

    public void generatehierarchyNumberForTable(int position) {
        String parentTableNum = this.parent.getHierarchyNoForTable();
        this.hierarchyNoForTable = parentTableNum + position + ".";
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
        final GtnWsTreeNode other = (GtnWsTreeNode) obj;
        return Objects.equals(this.hierarchyNo, other.hierarchyNo);
    }

    @Override
    public String toString() {
        return "TreeNode{" + " levelNumber=" + levelNumber + ", hierarchyNo=" + hierarchyNo + ",levelValue="
                + levelValue + " ccp ids = " + ccpIds + " RS = " + rsIds + "}";
    }

    public boolean isCheckedNode() {
        return checkedNode;
    }

    public void setCheckedNode(boolean checkedNode) {
        this.checkedNode = checkedNode;
    }

}
