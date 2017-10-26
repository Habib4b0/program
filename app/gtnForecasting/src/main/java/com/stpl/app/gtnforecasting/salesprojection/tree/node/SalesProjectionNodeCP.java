/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.tree.node;

import java.util.ArrayList;

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
    
}
