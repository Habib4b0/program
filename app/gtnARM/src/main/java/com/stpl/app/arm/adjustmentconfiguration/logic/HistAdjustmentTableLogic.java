/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.adjustmentconfiguration.logic;

import com.stpl.app.arm.adjustmentconfiguration.dto.AdjustmentConfigDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author Srithar.Raju
 */
public class HistAdjustmentTableLogic extends PageTableLogic {

    private boolean isGenerate;
    private AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    private AdjustmentConfigDTO binder;
    public static final org.slf4j.Logger LOGGER = org.slf4j.LoggerFactory.getLogger(HistAdjustmentTableLogic.class);

    public HistAdjustmentTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        if (isGenerate) {
            try {
                return logic.getAdjustmentConfigCountForHistory(getFilters(), binder);
            } catch (SQLException ex) {
                LOGGER.error("Error in getcount :" + ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAdjustmentConfigDataForHistory(start, offset, getFilters(), getSortByColumns(), binder);
        } catch (SQLException ex) {
            LOGGER.error("Error in LoadData" + ex);
        }
        return Collections.emptyList();
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
