/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.relationshipbuilder.logic.tableLogic;

import com.stpl.app.adminconsole.relationshipbuilder.dto.HierarchyLevelsDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Jayaram
 */
public class RelationshipBuilderTableLogic extends PageTableLogic{

    
    @Override
    public int getCount() {
        int count =0;
        
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        HierarchyLevelsDTO dto = (HierarchyLevelsDTO) object;
        ((BeanItemContainer<HierarchyLevelsDTO>) container).addBean(dto);
        return dto;
    }
    
}
