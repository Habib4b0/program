/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.nationalassumptions.logic;


import com.stpl.app.gtnforecasting.nationalassumptions.dto.ProductGroupLookUpDTO;
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
public class ProductGroupTableLogic extends PageTableLogic {

    String productGroupNo = StringUtils.EMPTY;
    String productGroupName = StringUtils.EMPTY;
    boolean loadData = false;
    ProductGroupLookUpDTO productGroupLookUpDTO;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(DataSelectionSearchLogic.class);

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
        List<ProductGroupLookUpDTO> resultList = new ArrayList<ProductGroupLookUpDTO>();
        try {
            resultList = new ProductGroupLogic().getProductGroups(productGroupNo, productGroupName, start, offset, getFilters(), getSortByColumns()); 
        } catch (Exception ex) {
            LOGGER.error(ex);
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
