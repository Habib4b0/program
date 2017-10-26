/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.tree.node;

import java.util.ArrayList;

/**
 *
 * @author Abishek.Ram
 */
public class SalesPRCP extends SalesPRBaseNode {

    public SalesPRCP(String hierarchyNumber) {
        this.hierachyNo = hierarchyNumber;
        this.childTreeList = new ArrayList<>();
    }

    @Override
    public int compareTo(SalesPRBaseNode o) {
        return (this.getHierachyNo() + this.getParentNode().getHierachyNo()).compareTo(o.getHierachyNo() + o.getParentNode().getHierachyNo());
    }

}
