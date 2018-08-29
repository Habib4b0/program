/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.tree.node;

import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author
 */
public class SalesProjectionNodeCP extends SalesBaseNode {

    public SalesProjectionNodeCP(String hierarchyNumber) {
        this.hierachyNo = hierarchyNumber;
        this.childTreeList = new ArrayList<>();
    }

    @Override
    public int compareTo(SalesBaseNode o) {
        return this.getHierachyNo().compareTo(o.getHierachyNo());
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj != null && obj.getClass() == this.getClass()) {
            SalesProjectionNodeCP node = (SalesProjectionNodeCP) obj;
            return ((this.hierachyNo.equals(node.getHierachyNo())) && (this.hierarchyForTable.equals(node.getHierarchyForTable())));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + Objects.hashCode(this.hierachyNo + this.hierarchyForTable);
        return hash;
    }
}
