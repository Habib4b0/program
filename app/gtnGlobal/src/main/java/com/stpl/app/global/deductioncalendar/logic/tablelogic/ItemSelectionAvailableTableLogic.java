/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.global.deductioncalendar.logic.tablelogic;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;

/**
 *
 * @author gopinath
 */
public class ItemSelectionAvailableTableLogic  extends PageTableLogic {

    public ItemSelectionAvailableTableLogic() {
        super();
    }

    private boolean loadData = false;
    private ErrorfulFieldGroup binder;
    private final SelectionLogic selectionLogic=new SelectionLogic();
    private static final Logger LOGGER = LoggerFactory.getLogger(ItemSelectionAvailableTableLogic.class);
    

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                //To Call Count Query
                count=selectionLogic.getAvailableTableCount(binder,this.getSortByColumns(),this.getFilters());
            }  catch (Exception ex) {
                LOGGER.error(ex + " in getCount");
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<SelectionDTO> resultList = new ArrayList<>();
        try {
            resultList=selectionLogic.getAvailableTableResult(binder, start, start + offset,this.getSortByColumns(),this.getFilters());
        } catch (Exception ex) {
            LOGGER.error(ex + " in loadData");
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        SelectionDTO dto = (SelectionDTO) object;
        ((BeanItemContainer<SelectionDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(ErrorfulFieldGroup binder, boolean isReset) {
        this.binder = binder;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    @Override
    public void saveCurrentPage() {
        return;
    }

    @Override
    protected void createCurrentPageStart() {
        for (ExtPagedTable extPagedTable : tableList) {
            extPagedTable.setValue(null);
        }
        setRefresh(BooleanConstant.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(BooleanConstant.getTrueFlag());
    }

   
}