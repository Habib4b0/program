/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic.tablelogic;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author shyam.d
 */
public class ItemSelectionTableLogic extends PageTableLogic {

    int selectedProjectionId = 0;
    boolean loadData = false;
    SessionDTO sessionDTO;
    SelectionLogic selectionLogic=new SelectionLogic();
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(ItemSelectionAvailableTableLogic.class);

    @Override
    public int getCount() {
        int count = 0;
            try {
                count=selectionLogic.getSelectedTableCount(sessionDTO,this.getFilters());
            }  catch (Exception ex) {
                LOGGER.error(ex + " in getCount");
            }

        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<SelectionDTO> resultList = new ArrayList<>();
        try {
            resultList=selectionLogic.getSelectedTableResult(sessionDTO, start, start + offset, this.getSortByColumns(),this.getFilters());
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

    public ItemSelectionTableLogic(SessionDTO sessionDTO){
        this.sessionDTO = sessionDTO;
        setRequiredCount(true);
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
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(Boolean.TRUE);
    }

   
}