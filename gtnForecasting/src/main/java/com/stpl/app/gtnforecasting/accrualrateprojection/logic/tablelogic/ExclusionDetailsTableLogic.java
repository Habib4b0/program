/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.accrualrateprojection.logic.tablelogic;

import com.stpl.app.gtnforecasting.accrualrateprojection.dto.AccrualRateSelectionDTO;
import com.stpl.app.gtnforecasting.accrualrateprojection.logic.AccrualRateProjectionLogic;
import com.stpl.app.gtnforecasting.accrualrateprojection.utils.AccrualRateUtils;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Nadhiya
 */
public class ExclusionDetailsTableLogic  extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(ExclusionDetailsTableLogic.class);

    private final AccrualRateProjectionLogic searchLogic = AccrualRateProjectionLogic.getInstance();

    private AccrualRateSelectionDTO accrualRateSelectionDTO;

    private String tableName = StringUtils.EMPTY;
    
    private boolean isViewMode;

    @Override
    public int getCount() {
        int count = 0;
        switch (tableName) {           
            case AccrualRateUtils.AVAILABLE_VALUES:
            count = searchLogic.getCompanysCount(this.getSortByColumns(), this.getFilters());               
                break;
            case AccrualRateUtils.EXCLUDED_VALUES:    
                 count = searchLogic.getExcludedCompanysCount(accrualRateSelectionDTO, this.getSortByColumns(), this.getFilters(), isViewMode);
                break;           
            default:
                LOGGER.warn("Count - Table Name is not valid: " + tableName);
                break;
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        
        switch (tableName) {           
            case AccrualRateUtils.AVAILABLE_VALUES:
            list = searchLogic.getAvalableValues(this.getSortByColumns(), this.getFilters(),start,offset);          
                break;
            case AccrualRateUtils.EXCLUDED_VALUES:   
                 list = searchLogic.getExcluededCompanys(accrualRateSelectionDTO, this.getSortByColumns(), this.getFilters(), start, offset, isViewMode);
                break;           
            default:
                LOGGER.warn("Count - Table Name is not valid: " + tableName);
                break;
        }
        
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AccrualRateSelectionDTO dto = (AccrualRateSelectionDTO) object;
        ((BeanItemContainer<AccrualRateSelectionDTO>) container).addBean(dto);
        
        return dto;
    }
     public void setRequiredData(final String tableName, final AccrualRateSelectionDTO accrualRateSelectionDTO,final boolean isViewMode) {        
        this.tableName = tableName;
        this.accrualRateSelectionDTO = accrualRateSelectionDTO;   
        this.isViewMode = isViewMode;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
}
