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
import org.jboss.logging.Logger;

/**
 *
 * @author Srithar.Raju
 */
public class AdjustmentConfigTableLogic extends PageTableLogic {

    boolean isGenerate;
    AdjustmentConfigLogic logic = AdjustmentConfigLogic.getInstance();
    public static final Logger LOGGER = Logger.getLogger(AdjustmentConfigTableLogic.class);

    @Override
    public int getCount() {
        if (isGenerate) {
            try {
                return logic.getAdjustmentConfigCount(getFilters());
            } catch (Exception ex) {
                LOGGER.error("Error in getcount :"+ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            return logic.getAdjustmentConfigData(start, offset, getFilters(), getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error("Error in LoadData :"+ex);
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
