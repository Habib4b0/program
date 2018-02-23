/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionVariance.logic.ProjectionVarianceLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Jayaram
 */
public class ComparisonTableLogic extends PageTableLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComparisonTableLogic.class);
    private boolean loadData = false;
    private ComparisonLookupDTO comparisonLookup;
    private final ProjectionVarianceLogic projectionVarianceLogic = new ProjectionVarianceLogic();

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                comparisonLookup.setFilter(getFilters());
                comparisonLookup.setCount(Boolean.TRUE);
                count = projectionVarianceLogic.getComparisonCount(comparisonLookup);
                LOGGER.debug("Count= {}", count);
            } catch (Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<ComparisonLookupDTO> resultList = new ArrayList<>();
        try {
           comparisonLookup.setStart(start);
           comparisonLookup.setOffset(offset);
           comparisonLookup.setCount(Boolean.FALSE);
           comparisonLookup.setFilter(getFilters());
           comparisonLookup.setSortColumns(getSortByColumns());
            resultList = projectionVarianceLogic.getComparisonResults(comparisonLookup);
        } catch (PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ComparisonLookupDTO dto = (ComparisonLookupDTO) object;
        ((BeanItemContainer<ComparisonLookupDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(final ComparisonLookupDTO comparisonLookup, boolean isReset) {
        this.comparisonLookup = comparisonLookup;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
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

    public boolean fireSetData(boolean isReset) {
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
