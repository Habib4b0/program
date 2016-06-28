/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.stpl.app.cff.dto.SessionDTO;
import com.stpl.app.cff.ui.projectionVariance.dto.ComparisonLookupDTO;
import com.stpl.app.cff.ui.projectionVariance.logic.ProjectionVarianceLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Jayaram
 */
public class ComparisonTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(ComparisonTableLogic.class);
    boolean loadData = false;
    ComparisonLookupDTO comparisonLookup;
    String notNeededProjectionIds = StringUtils.EMPTY;
    ComparisonLookupDTO lookUpDTO;
    SessionDTO sessionDTO;
    String screenName = StringUtils.EMPTY;
    ProjectionVarianceLogic projectionVarianceLogic = new ProjectionVarianceLogic();

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                comparisonLookup.setFilter(getFilters());
                comparisonLookup.setCount(Boolean.TRUE);
                count = projectionVarianceLogic.getComparisonCount(comparisonLookup);
                LOGGER.info("Count"+count);
            } catch (Exception ex) {
                LOGGER.log(Logger.Level.ERROR, ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<ComparisonLookupDTO> resultList = new ArrayList<ComparisonLookupDTO>();
        try {
           comparisonLookup.setStart(start);
           comparisonLookup.setOffset(offset);
           comparisonLookup.setCount(Boolean.FALSE);
           comparisonLookup.setFilter(getFilters());
           comparisonLookup.setSortColumns(getSortByColumns());
            resultList = projectionVarianceLogic.getComparisonResults(comparisonLookup);
        } catch (Exception ex) {
            LOGGER.log(Logger.Level.ERROR, ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ComparisonLookupDTO dto = (ComparisonLookupDTO) object;
        ((BeanItemContainer<ComparisonLookupDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(final ComparisonLookupDTO comparisonLookup, boolean isReset, String screenName) {
        this.comparisonLookup = comparisonLookup;
        this.screenName = screenName;
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

    public boolean fireSetData(ComparisonLookupDTO lookUpDTO, SessionDTO sessionDTO, String notNeededProjectionIds, boolean isReset, String screenName) {
        this.lookUpDTO = lookUpDTO;
        this.sessionDTO = sessionDTO;
        this.screenName = screenName;
        this.notNeededProjectionIds = notNeededProjectionIds;
        clearAll();
        setRequiredCount(true);
        loadData = !isReset;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
