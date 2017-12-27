/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.gtnforecasting.lookups.logic.tablelogic;

import com.stpl.app.gtnforecasting.lookups.dto.PmpyTradingPartnerDTO;
import com.stpl.app.gtnforecasting.lookups.logic.PmpyLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 * The class PmpyTradingPartnerTableLogic.
 * 
 * @author vinodhini
 */
public class PmpyTradingPartnerTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(PmpyTradingPartnerTableLogic.class);
    private final PmpyLogic searchLogic = new PmpyLogic();
    protected String tpNo;
    protected String tpName;
    protected Object contractHolder;
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.tradingPartnerLookUp(tpNo, tpName, contractHolder,0, 0, this.getSortByColumns(), this.getFilters(),true);
            }
            isResultsEmpty = count == 0;            
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List)searchLogic.tradingPartnerLookUp(tpNo, tpName, contractHolder,start, offset, this.getSortByColumns(), this.getFilters(),false);
            } catch (Exception ex) {                
                LOGGER.error(ex);
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        PmpyTradingPartnerDTO dto = (PmpyTradingPartnerDTO) object;
        ((BeanItemContainer<PmpyTradingPartnerDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(String tpNo, String tpName, Object contractHolder) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.tpNo = tpNo;
        this.tpName = tpName;
        this.contractHolder = contractHolder;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }    

}
