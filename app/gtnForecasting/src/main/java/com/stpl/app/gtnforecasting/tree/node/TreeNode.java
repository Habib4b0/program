/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.tree.node;

/**
 *
 * @author Abishek.Ram
 */
public interface TreeNode {

    public String getHierachyNo();

    public void setPositiontoParent(int position);

    public String getParenthierarchies();

    public int addChild(TreeNode child);

    public void addParentNode(TreeNode parent);

    public TreeNode getParentNode();

    public void generateHierarchy();

    public String getPreviousOppositeHieararchy(String indicator);

    public boolean isApex();

    public String getHierarchyForTable();

    public String getHierarchyIndicator();

    public int getLevel();

    public void addToTotal(int totalBelow);

    public int getNoOfChilds();

    public TreeNode getNthChild(int position);

    public Iterable<TreeNode> getAllChildHierarchies();

}
