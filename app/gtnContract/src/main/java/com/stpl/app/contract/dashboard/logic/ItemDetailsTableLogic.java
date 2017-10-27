/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.asi.ui.extfilteringtable.paged.logic.SortByColumn;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class ItemDetailsTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(ItemDetailsTableLogic.class);
    private boolean isFirstLoad = false;
    private IfpLogic ifpLogic;
    SessionDTO sessionDTO;
    private BeanItemContainer<TempPricingDTO> saveContainer;

    Map<Integer, String> priceProtectionPriceType;
    public boolean isIfpItemsTab = false;
    /**
     * Record check box value
     */
    String record = StringUtils.EMPTY;
    ExtPagedTable table;

    @Override
    public int getCount() {
        LOGGER.debug("Count Stasted--->> ");
        int count = 0;
        try {
            if (isFirstLoad) {
                if (saveContainer.size() > 0) {
                    IfpLogic.saveToTempIFP(saveContainer.getItemIds(), "Y".equals(sessionDTO.getEdit()));
                    IfpLogic.saveToTempTable(saveContainer.getItemIds(), "Y".equals(sessionDTO.getEdit()),sessionDTO);
                    saveContainer.removeAllItems();
                }
                List list = ifpLogic.getLazyItemPricingDeatils(0, 0, this.getFilters(), true, getRecord(), isIfpItemsTab, Boolean.FALSE,null);
                count = Integer.valueOf(String.valueOf(list.get(0)));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Count------>>> " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<Object[]> returnList = new ArrayList();
        if (isFirstLoad) {            
            try {          
                 
                for (SortByColumn objects : this.getSortByColumns()) {
                    System.out.println("this.getSortByColumns()" + objects.getName());
                }
                returnList = ifpLogic.getLazyItemPricingDeatils(start, offset, this.getFilters(), false, getRecord(), isIfpItemsTab, Boolean.FALSE,this.getSortByColumns());
                LOGGER.debug("loadData----->>>>> " + returnList.size());
                return ifpLogic.getCustomizedPricingDTO(returnList, isIfpItemsTab, getRecord());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        return returnList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        TempPricingDTO dto = (TempPricingDTO) object;
        ((BeanItemContainer<TempPricingDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ExtPagedTable table, final BeanItemContainer<TempPricingDTO> saveContainer, final SessionDTO sessionDTO, Map<Integer, String> priceProtectionPriceType) {
        LOGGER.debug("Table Logic Called-->>>");

        isFirstLoad = true;
        this.sessionDTO = sessionDTO;
        this.table = table;
        this.saveContainer = saveContainer;
        this.priceProtectionPriceType = priceProtectionPriceType;
        ifpLogic = new IfpLogic(this.sessionDTO);
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

}
