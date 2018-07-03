/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentrateconfiguration.logic.tablelogic;

import com.stpl.app.arm.adjustmentrateconfiguration.dto.LookUpDTO;
import com.stpl.app.arm.adjustmentrateconfiguration.logic.AdjustmentRateLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class ExclustionLookupTableLogic extends PageTableLogic {

    private LookUpDTO exRateDTO = new LookUpDTO();
    private AdjustmentRateLogic logic = new AdjustmentRateLogic();
    private boolean isGenerate = false;
    private static final Logger LOGGER = LoggerFactory.getLogger(ExclustionLookupTableLogic.class);

    @Override
    public int getCount() {
        try {
            List<Object> count = logic.searchLogicForExclusionLookUp(exRateDTO, true, 0, 0, null, this.getFilters());
            return count.size();
        } catch (Exception ex) {
            LOGGER.error("Error in getcount :" + ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            if (isGenerate) {
                return logic.searchLogicForExclusionLookUp(exRateDTO, false, start, offset, this.getSortByColumns(), this.getFilters());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception ex) {
            LOGGER.error("Error in loadData :" + ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        LookUpDTO dto = (LookUpDTO) object;
        ((BeanItemContainer<LookUpDTO>) container).addBean(dto);
        return dto;
    }

    public boolean configureSearchData(LookUpDTO exRateDTO, boolean isGenerate) {
        this.isGenerate = isGenerate;
        this.exRateDTO = exRateDTO;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return Double.compare(getRecordCount(), 0.0) != 0;
    }

}
