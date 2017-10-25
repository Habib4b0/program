/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojectionresults.tree.node;

import com.stpl.app.gtnforecasting.salesprojection.tree.node.SalesBaseNode;
import java.util.ArrayList;

/**
 *
 * @author Abishek.Ram
 */
public class SalesPRCustom extends SalesPRBaseNode {

    public SalesPRCustom(String hierarchyNumber) {
        this.hierachyNo = hierarchyNumber;
        this.childTreeList = new ArrayList<>();
    }

    @Override
    public int compareTo(SalesPRBaseNode o) {
        return this.getHierarchyForTable().compareTo(o.getHierarchyForTable());
    }
}
