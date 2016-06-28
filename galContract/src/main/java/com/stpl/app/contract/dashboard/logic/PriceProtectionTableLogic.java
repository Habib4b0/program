/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.dashboard.dto.TempPricingDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.stpl.ifs.ui.CustomePagedFilterTable;
import com.stpl.ifs.util.HelperDTO;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
import com.vaadin.data.Container;
import java.util.ArrayList;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;

/**
 *
 * @author asha
 */
public class PriceProtectionTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(PriceProtectionTableLogic.class);
    private boolean isFirstLoad = false;
    SessionDTO sessionDTO;
    IfpLogic ifpLogic;
    String record = StringUtils.EMPTY;
    private int count;
    Map<Integer, HelperDTO> priceProtectionPriceType;
    private BeanItemContainer<TempPricingDTO> saveContainer;
    boolean isEditable;

    @Override
    public int getCount() {
        LOGGER.info("Count Stasted--->> ");
        int count = 0;
        try {
            if (isFirstLoad) {
                List list = ifpLogic.getLazyPriceProtectionDeatils(0, 0, this.getFilters(), true, getRecord(), null);
                count = Integer.valueOf(String.valueOf(list.get(0)));
                return list == null ? 0 : count;
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            LOGGER.error(ex);
        }
        LOGGER.info("Count------>>> " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        if (isFirstLoad) {
            try {
                if (isEditable) {
                    if (saveContainer.size() > 0) {
                        IfpLogic.saveToTempTable(saveContainer.getItemIds());
                        saveContainer.removeAllItems();
                    }
                }
                List<Object[]> returnList = ifpLogic.getLazyPriceProtectionDeatils(start, offset, this.getFilters(), false, getRecord(), this.getSortByColumns());
                return ifpLogic.getCustomizedPriceProtectionDTO(returnList, priceProtectionPriceType, getRecord());
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex);
            }
        }

        return new ArrayList<TempPricingDTO>();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        TempPricingDTO dto = (TempPricingDTO) object;
        ((BeanItemContainer<TempPricingDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ExtPagedTable table, final BeanItemContainer<TempPricingDTO> saveContainer, final SessionDTO sessionDTO, final Map<Integer, HelperDTO> priceProtectionPriceType, boolean isEditable) {
        LOGGER.info("Table Logic Called-->>>");

        this.sessionDTO = sessionDTO;
        this.priceProtectionPriceType = priceProtectionPriceType;
        this.saveContainer = saveContainer;
        this.isEditable = isEditable;
        ifpLogic = new IfpLogic(this.sessionDTO);
        isFirstLoad = true;
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
