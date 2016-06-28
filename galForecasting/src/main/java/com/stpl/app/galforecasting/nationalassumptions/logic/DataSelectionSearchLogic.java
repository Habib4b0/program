/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.nationalassumptions.logic;

import com.stpl.app.galforecasting.nationalassumptions.dto.DataSelectionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author nadhiya
 */
public class DataSelectionSearchLogic extends PageTableLogic {

    String projectionName = StringUtils.EMPTY;
    Object companyValueId = StringUtils.EMPTY;
    Object thearupeticValueId = StringUtils.EMPTY;
    String selectedProducts = StringUtils.EMPTY;
    int productGroupId;
    int selectedProjectionId = 0;
    boolean loadData = false;
    DataSelectionDTO dataSelectionDTO;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionSearchLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {

            count = new DataSelectionLogic().getProjectionResultsCount(projectionName, selectedProducts, companyValueId, thearupeticValueId, productGroupId, getFilters());

        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<DataSelectionDTO> resultList = new ArrayList<DataSelectionDTO>();
        try {
            resultList = new DataSelectionLogic().getProjectionResults(projectionName, selectedProducts, companyValueId, thearupeticValueId, productGroupId, start, offset, getFilters(), getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage() + " in loadData");
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        DataSelectionDTO dto = (DataSelectionDTO) object;
        ((BeanItemContainer<DataSelectionDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, boolean isReset) {
        this.projectionName = projectionName;
        this.selectedProducts = getSelectedProducts;
        this.companyValueId = companyValueId;
        this.thearupeticValueId = thearupeticValueId;
        this.productGroupId = productGroupId;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    public boolean fireSetData(DataSelectionDTO dataSelectionDTO, boolean isReset) {
        this.dataSelectionDTO = dataSelectionDTO;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    @Override
    public void saveCurrentPage() {
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
