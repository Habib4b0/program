/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.projectionvariance.logic.tablelogic;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.gtnforecasting.projectionvariance.dto.ComparisonLookupDTO;
import com.stpl.app.gtnforecasting.projectionvariance.logic.MProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.projectionvariance.logic.NMProjectionVarianceLogic;
import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.CommonUtils;
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
 * @author Jayaram
 */
public class ComparisonTableLogic extends PageTableLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(ComparisonTableLogic.class);
    protected boolean loadData = false;
    protected ComparisonLookupDTO comparisonLookup;
    protected String notNeededProjectionIds = StringUtils.EMPTY;
    protected ComparisonLookupDTO lookUpDTO;
    protected SessionDTO sessionDTO;
    protected String screenName = StringUtils.EMPTY;
    protected MProjectionVarianceLogic projectionVarianceLogic = new MProjectionVarianceLogic();

    @Override
    public int getCount() {
        int count = 0;
        if (loadData) {
            try {
                if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                    count = projectionVarianceLogic.getProjectionCount(lookUpDTO, notNeededProjectionIds, getFilters());
                } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                    count = new NMProjectionVarianceLogic().getComparisonCount(comparisonLookup, getFilters(), screenName);
                }
            } catch (PortalException | SystemException ex) {
                LOGGER.error(StringUtils.EMPTY, ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<ComparisonLookupDTO> resultList = new ArrayList<>();
        try {
            if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_MANDATED)) {
                resultList = projectionVarianceLogic.getProjection(lookUpDTO, notNeededProjectionIds, start, offset, getSortByColumns(), getFilters());
            } else if (screenName.equals(CommonUtils.BUSINESS_PROCESS_TYPE_NONMANDATED)) {
                resultList = new NMProjectionVarianceLogic().getComparisonResults(comparisonLookup, start, offset, getFilters(), getSortByColumns(), screenName);
            }
        } catch (PortalException | SystemException ex) {

            LOGGER.error(StringUtils.EMPTY, ex);
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
