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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigTableLogic extends PageTableLogic {

    private boolean isGenerate;
    private AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    public static final Logger LOGGER = LoggerFactory.getLogger(AdjustmentConfigTableLogic.class);

    public AdjustmentConfigTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        if (isGenerate) {
            try {
                return logic.getAdjustmentConfigCount(getFilters());
            } catch (SQLException ex) {
                LOGGER.error("Error in getcount :" + ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAdjustmentConfigData(start, offset, getFilters(), getSortByColumns());
        } catch (SQLException ex) {
            LOGGER.error("Error in LoadData :" + ex);
        }
        return Collections.emptyList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentConfigDTO dto = (AdjustmentConfigDTO) object;
        ((BeanItemContainer<AdjustmentConfigDTO>) container).addBean(dto);
        return dto;
    }

    public void loadsetData(boolean isRest) {
        isGenerate = isRest;
        clearAll();
        clearFilters();
        setRequiredCount(true);
        setCurrentPage(1);
    }

}
