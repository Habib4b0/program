/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.tree.node;

import com.stpl.app.gtnforecasting.tree.node.TreeNode;
import java.util.List;
import java.util.Objects;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author Abishek.Ram
 */
public abstract class SalesBaseNode implements TreeNode, Comparable<SalesBaseNode> {

    protected TreeNode parentNode;
    protected String hierachyNo;
    protected String hierarchyForTable;
    protected int noOfChilds;
    protected int positiontoParent;
    protected List<TreeNode> childTreeList;
    protected boolean apex = false;
    protected int level;
    protected String hierarchyIndicator;
    protected int totalHolders;

    @Override
    public String getHierarchyIndicator() {
        return hierarchyIndicator;
    }

    public void setHierarchyIndicator(String hierarchyIndicator) {
        this.hierarchyIndicator = hierarchyIndicator;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public void addParentNode(TreeNode parentNode) {
        this.parentNode = parentNode;
    }

    public TreeNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(SalesProjectionNodeCP parentNode) {
        this.parentNode = parentNode;
    }

    @Override
    public boolean isApex() {
        return apex;
    }

    public void setApex(boolean apex) {
        this.apex = apex;
    }

    @Override
    public String getHierachyNo() {
        return hierachyNo;
    }

    public void setHierachyNo(String hierachyNo) {
        this.hierachyNo = hierachyNo;
    }

    @Override
    public String getHierarchyForTable() {
        return hierarchyForTable.trim();
    }

    public void setHierarchyForTable(String hierarchyForTable) {
        this.hierarchyForTable = hierarchyForTable;
    }

    @Override
    public int getNoOfChilds() {
        return noOfChilds;
    }

    public void setNoOfChilds(int noOfChilds) {
        this.noOfChilds = noOfChilds;
    }

    public int getPositiontoParent() {
        return positiontoParent;
    }

    @Override
    public void setPositiontoParent(int positiontoParent) {
        this.positiontoParent = positiontoParent;
    }

    public List<TreeNode> getAllChildHierarchies() {
        return childTreeList;
    }


    @Override
    public void addToTotal(int totalBelow) {
        if (parentNode != null) {
            parentNode.addToTotal(totalBelow);
        }
        totalHolders += totalBelow;
    }

    @Override
    public String getParenthierarchies() {
        if (parentNode != null) {
            return parentNode.getParenthierarchies() + "~" + parentNode.getHierachyNo();
        }
        return "";
    }

    @Override
    public int addChild(TreeNode child) {
        childTreeList.add(child);
        child.setPositiontoParent(childTreeList.size());
        child.addParentNode(this);
        child.generateHierarchy();
        addToTotal(1);
        noOfChilds = childTreeList.size();
        return 1;
    }

    @Override
    public void generateHierarchy() {
        if (parentNode != null) {
            StringBuilder hierarchyBuilder = new StringBuilder();
            hierarchyBuilder.append(parentNode.isApex() ? "" : parentNode.getHierarchyForTable());
            hierarchyBuilder.append(positiontoParent).append(".");
            hierarchyForTable = hierarchyBuilder.toString();
        }
    }
    
    
    @Override
    public boolean equals(final Object obj) {
        if (obj.getClass() == SalesProjectionNodeCP.class) {
            SalesProjectionNodeCP node = (SalesProjectionNodeCP) obj;
            return ((this.hierachyNo.equals(node.getHierachyNo())) && (this.hierarchyForTable.equals(node.getHierarchyForTable())));
        } else {
            return false;
        }

    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.hierachyNo + this.hierarchyForTable);
        return hash;
    }

    @Override
    public String getPreviousOppositeHieararchy(String indicator) {
        if (parentNode.isApex()) {
            return StringUtils.EMPTY;
        }
        if (!parentNode.getHierarchyIndicator().equals(indicator)) {
            return parentNode.getHierachyNo();
        } else {
            return parentNode.getPreviousOppositeHieararchy(indicator);
        }
    }

    @Override
    public String toString() {
        return this.getHierachyNo() + " ~ " + this.getHierarchyForTable(); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TreeNode getNthChild(int position) {
        return childTreeList.get(position);
    }
}
