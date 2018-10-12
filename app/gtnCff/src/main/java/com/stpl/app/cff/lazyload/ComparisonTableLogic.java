/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyload;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionvariance.logic.ProjectionVarianceLogic;
import com.stpl.ifs.util.constants.BooleanConstant;
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
    private boolean isDataSelection = false;
    private ComparisonLookupDTO comparisonLookup;
    private SessionDTO session;
    private final ProjectionVarianceLogic projectionVarianceLogic = new ProjectionVarianceLogic();

    public ComparisonTableLogic() {
        super();
    }

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                comparisonLookup.setFilter(getFilters());
                comparisonLookup.setCount(Boolean.TRUE);
                count = projectionVarianceLogic.getComparisonCount(comparisonLookup,isDataSelection,session);
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
            resultList = projectionVarianceLogic.getComparisonResults(comparisonLookup,isDataSelection,session);
        } catch (Exception ex) {
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

    public boolean fireSetData(final ComparisonLookupDTO comparisonLookup, boolean isReset,boolean isDataSelection,SessionDTO session) {
        this.comparisonLookup = comparisonLookup;
        this.isDataSelection = isDataSelection;
        this.session = session;
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
        setRefresh(BooleanConstant.getFalseFlag());
    }

    @Override
    protected void createCurrentPageEnd() {
        setRefresh(BooleanConstant.getTrueFlag());
    }

    public boolean fireSetData(boolean isReset) {
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
