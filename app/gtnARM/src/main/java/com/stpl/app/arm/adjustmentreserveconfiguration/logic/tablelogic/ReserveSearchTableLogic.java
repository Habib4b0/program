/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentreserveconfiguration.logic.tablelogic;

import com.stpl.app.arm.adjustmentreserveconfiguration.dto.AdjustmentReserveDTO;
import com.stpl.app.arm.adjustmentreserveconfiguration.logic.AdjustmentReserveLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;

import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author srithar
 */
public class ReserveSearchTableLogic extends PageTableLogic {

    private boolean isGenerate;
    private AdjustmentReserveLogic logic = AdjustmentReserveLogic.getInstance();
    private AdjustmentReserveDTO binderDto;
    /**
     * The Constant LOGGER.
     */
    protected static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(ReserveSearchTableLogic.class);

    public ReserveSearchTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        if (isGenerate) {
            return logic.getSearchCount(binderDto, getFilters());
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getSearchResults(binderDto, start, offset, getFilters(), getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error("Error in loadData :" + ex);
        }
        return Collections.emptyList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentReserveDTO dto = (AdjustmentReserveDTO) object;
        ((BeanItemContainer<AdjustmentReserveDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadsetData(boolean isRest, AdjustmentReserveDTO binderDto) {
        isGenerate = isRest;
        this.binderDto = binderDto;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
        return Double.compare(getRecordCount(), 0.0) != 0;
    }
}
