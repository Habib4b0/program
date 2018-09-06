/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.deductioncalendar.logic.tablelogic;

import com.stpl.app.global.deductioncalendar.dto.SelectionDTO;
import com.stpl.app.global.deductioncalendar.logic.SelectionLogic;
import com.stpl.app.util.Constants;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author gopinath
 */
public class SelectionTableLogic extends PageTableLogic {
    
    private boolean loadData = false;
    private SelectionDTO selectionDTO;
    private final SelectionLogic selectionLogic = new SelectionLogic();
    private String tabName = StringUtils.EMPTY;
    private String availableOrselected = StringUtils.EMPTY;
    private static final Logger LOGGER = LoggerFactory.getLogger(SelectionTableLogic.class);
    
    
    public SelectionTableLogic(){
    	super();
    }
    /**
     * Returns the number of records available for the given search criteria.
     *
     * @return
     */
    @Override
    public int getCount() {
        LOGGER.debug("Entering SelectionTableLogic getCount");
        int count = 0;
        if (loadData) {
            try {
                if (Constants.CUSTOMER_SELECTION.equals(tabName)) {
                    count = (Integer) selectionLogic.getCustomerSearchResult(selectionDTO,0,0,true,this.getFilters(),this.getSortByColumns(),availableOrselected);
                } else {
                    count = 1;
                }
            } catch (Exception ex) {
                LOGGER.error("{} in getCount", ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug("Entering SelectionTableLogic loadData");
        List<SelectionDTO> resultList = new ArrayList<>();
        try {
            if (Constants.CUSTOMER_SELECTION.equals(tabName)) {
                    resultList = (List) selectionLogic.getCustomerSearchResult(selectionDTO,start,offset,false,this.getFilters(),this.getSortByColumns(),availableOrselected);
                } 
        } catch (Exception ex) {
            LOGGER.error("{} in loadData", ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        SelectionDTO dto = (SelectionDTO) object;
        ((BeanItemContainer<SelectionDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(final SelectionDTO selectionDTO, final boolean isReset, final String tabName, final String availableOrselected) {
        this.selectionDTO = selectionDTO;
        clearAll();
        setRequiredCount(true);
        this.tabName = tabName;
        this.availableOrselected = availableOrselected;
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
