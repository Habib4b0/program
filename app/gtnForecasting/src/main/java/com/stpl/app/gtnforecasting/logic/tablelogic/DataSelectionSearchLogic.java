/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic.tablelogic;

import com.stpl.app.gtnforecasting.logic.NonMandatedLogic;
import com.stpl.ifs.ui.forecastds.dto.DataSelectionDTO;
import com.stpl.portal.kernel.exception.SystemException;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author sooriya.lakshmanan
 */

public class DataSelectionSearchLogic extends PageTableLogic {

    protected int selectedProjectionId = 0;
    protected boolean loadData = false;
    protected DataSelectionDTO dataSelectionDTO;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionSearchLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                count = new NonMandatedLogic().searchDSProjectionsCount(dataSelectionDTO, getFilters());
            }  catch (SystemException ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<DataSelectionDTO> resultList = new ArrayList<>();
        try {
            resultList = new NonMandatedLogic().searchDSProjectionsLazy(dataSelectionDTO, start, offset, getFilters(), getSortByColumns());
        } catch (SystemException | ParseException ex) {
            LOGGER.error(ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        DataSelectionDTO dto = (DataSelectionDTO) object;
        ((BeanItemContainer<DataSelectionDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(DataSelectionDTO dataSelectionDTO, boolean isReset) {
        this.dataSelectionDTO = dataSelectionDTO;
        clearAll();
        getFilters().clear();
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
        setRefresh(Boolean.FALSE);
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(Boolean.TRUE);
    }
}