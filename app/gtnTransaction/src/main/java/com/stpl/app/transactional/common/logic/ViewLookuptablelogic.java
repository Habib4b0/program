/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.logic;

import com.stpl.app.transactional.common.dto.AdjustmentDetailsDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Rohit.Vignesh
 */
public class ViewLookuptablelogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(ViewLookuptablelogic.class);
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    AdjustmentDetailsDTO adjustmentDetailsDTO;
    SearchLogic searchLogic = new SearchLogic();

    @Override
    public int getCount() {
        LOGGER.debug("Entering getCount");
        int count = 0;
        try {
            if (isFirstLoad) {
                adjustmentDetailsDTO.setFilters(getFilters());
                adjustmentDetailsDTO.setSortByColumn(getSortByColumns());
                adjustmentDetailsDTO.setCount(Boolean.TRUE);
                count = searchLogic.getViewCount(adjustmentDetailsDTO);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug("Entering  loadData");
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                adjustmentDetailsDTO.setStart(start);
                adjustmentDetailsDTO.setOffset(offset);
                adjustmentDetailsDTO.setCount(Boolean.FALSE);
                list = (List) searchLogic.getViewDeatils(adjustmentDetailsDTO);

            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        AdjustmentDetailsDTO adjustmentDetailsDTO = (AdjustmentDetailsDTO) object;
        ((BeanItemContainer<AdjustmentDetailsDTO>) container).addBean(adjustmentDetailsDTO);
        return adjustmentDetailsDTO;
    }

    public boolean configureSearchData(AdjustmentDetailsDTO adjustmentDetailsDTO) {
        this.adjustmentDetailsDTO = adjustmentDetailsDTO;
        isFirstLoad = adjustmentDetailsDTO.isGenerate();
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        this.setCurrentPage(NumericConstants.ONE);
        return getRecordCount()!=0;
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

}
