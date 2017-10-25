/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.dto.IfpItemDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class SelectedTableLogic extends PageTableLogic {

      private static final Logger LOGGER = Logger.getLogger(SelectedTableLogic.class);
    private boolean isFirstLoad = false;
    private IfpLogic  ifpLogic = new IfpLogic();
    SessionDTO sessionDTO;
    @Override
    public int getCount() {
          LOGGER.debug("Count Stasted--->> ");
        int count = 0;
        try {
            if (isFirstLoad) {
                    count = ifpLogic.getLazySelectedItemsCount(this.getFilters(),sessionDTO);
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Count-------->>> " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
       List list = new ArrayList();
        if (isFirstLoad) {
            try {

                    list = ifpLogic.getLazySelectedItemDeatils(start, start+offset, this.getSortByColumns(), this.getFilters(),sessionDTO);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.debug("loadData---->>>>> " + list.size());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
         IfpItemDTO dto = (IfpItemDTO) object;
            ((BeanItemContainer<IfpItemDTO>) container).addBean(dto);
            return dto;
    }
    
    public void configureSearchData(SessionDTO sessionDTO) {
        LOGGER.debug("Table Logic Called-->>>");
        isFirstLoad = true;
        this.sessionDTO=sessionDTO;
        this.setPageLength(NumericConstants.SIX);
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    
}
