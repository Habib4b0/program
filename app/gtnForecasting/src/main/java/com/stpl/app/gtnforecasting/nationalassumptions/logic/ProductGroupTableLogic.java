/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;


import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
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
public class ProductGroupTableLogic extends PageTableLogic {

    protected String productGroupNo = StringUtils.EMPTY;
    protected String productGroupName = StringUtils.EMPTY;
    protected boolean loadData = false;
    protected ProductGroupLookUpDTO productGroupLookUpDTO;
    private static final Logger LOGGER = LoggerFactory.getLogger(DataSelectionSearchLogic.class);

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            count = new ProductGroupLogic().getProductGroupsCount(productGroupNo, productGroupName, getFilters());

        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<ProductGroupLookUpDTO> resultList = new ArrayList<>();
        try {
            resultList = new ProductGroupLogic().getProductGroups(productGroupNo, productGroupName, start, offset, getFilters(), getSortByColumns()); 
        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProductGroupLookUpDTO dto = (ProductGroupLookUpDTO) object;
        ((BeanItemContainer<ProductGroupLookUpDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(String productGroupNo, String productGroupName, boolean isReset) {
        this.productGroupNo = productGroupNo;
        this.productGroupName = productGroupName;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    public boolean fireSetData(ProductGroupLookUpDTO productGroupLookUpDTO, boolean isReset) {
        this.productGroupLookUpDTO = productGroupLookUpDTO;
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
