/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.logic;

import com.stpl.app.arm.adjustmentconfiguration.dto.AdjustmentConfigDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Srithar.Raju
 */
public class HistAdjustmentTableLogic extends PageTableLogic {

    boolean isGenerate;
    AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    AdjustmentConfigDTO binder;
    public static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(HistAdjustmentTableLogic.class);

    @Override
    public int getCount() {
        if (isGenerate) {
            try {
                return logic.getAdjustmentConfigCountForHistory(getFilters(), binder);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAdjustmentConfigDataForHistory(start, offset, getFilters(), getSortByColumns(), binder);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentConfigDTO dto = (AdjustmentConfigDTO) object;
        ((BeanItemContainer<AdjustmentConfigDTO>) container).addBean(dto);
        return dto;
    }

    public void loadsetData(boolean isRest, AdjustmentConfigDTO binder) {
        this.binder = binder;
        isGenerate = isRest;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
    }
}
