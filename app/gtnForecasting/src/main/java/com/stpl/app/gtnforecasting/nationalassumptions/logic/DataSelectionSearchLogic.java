/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;

import com.stpl.app.gtnforecasting.nationalassumptions.dto.DataSelectionDTO;
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
 * @author nadhiya
 */
public class DataSelectionSearchLogic extends PageTableLogic {

    protected String projectionName = StringUtils.EMPTY;
    protected Object companyValueId = StringUtils.EMPTY;
    protected Object thearupeticValueId = StringUtils.EMPTY;
    protected String selectedProducts = StringUtils.EMPTY;
    protected int productGroupId;
    protected int selectedProjectionId = 0;
    protected boolean loadData = false;
    protected DataSelectionDTO dataSelectionDTO;
    protected Object businessUnit;
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionSearchLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {

            count = new DataSelectionLogic().getProjectionResultsCount(projectionName, selectedProducts, companyValueId, thearupeticValueId, productGroupId, getFilters(),businessUnit);

        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<DataSelectionDTO> resultList = new ArrayList<>();
        try {
            resultList = new DataSelectionLogic().getProjectionResults(projectionName, selectedProducts, companyValueId, thearupeticValueId, productGroupId, start, offset, getFilters(), getSortByColumns(),businessUnit);
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        DataSelectionDTO dto = (DataSelectionDTO) object;
        ((BeanItemContainer<DataSelectionDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(String projectionName, String getSelectedProducts, Object companyValueId, Object thearupeticValueId, int productGroupId, boolean isReset,Object businessUnit) {
        this.projectionName = projectionName;
        this.selectedProducts = getSelectedProducts;
        this.companyValueId = companyValueId;
        this.thearupeticValueId = thearupeticValueId;
        this.productGroupId = productGroupId;
        this.businessUnit=businessUnit;
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
