/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.dto.IfpItemDTO;
import com.stpl.app.contract.global.dto.ItemMasterDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sooriya.lakshmanan
 */
public class AvailableTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(AvailableTableLogic.class);
    private boolean isFirstLoad = false;
    private IfpLogic ifpLogic;
    public String searchFields;
    public String searchValue;
    private String tableName = StringUtils.EMPTY;

    @Override
    public int getCount() {
        LOGGER.info("Count Stasted--->> ");
        int count = 0;
        try {
            if (isFirstLoad) {
                    count = ifpLogic.getItemAdditionCount(searchFields, searchValue, this.getFilters());
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("Count-------->>> " + count);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                    list = ifpLogic.getItemForIFP(searchFields, searchValue, start, offset, this.getSortByColumns(), this.getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        LOGGER.info("loadData---->>>>> " + list.size());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
            ItemMasterDTO dto = (ItemMasterDTO) object;
            ((BeanItemContainer<ItemMasterDTO>) container).addBean(dto);
            return dto;
    }

    public void configureSearchData(final String searchFields, String searchValue, String tableName, SessionDTO sessionDTO) {
        LOGGER.info("Table Logic Called-->>>");
        isFirstLoad = true;
        ifpLogic = new IfpLogic(sessionDTO);
        this.clearAll();
        this.getFilters().clear();
        this.searchFields = searchFields;
        this.searchValue = searchValue;
        this.tableName = tableName;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

}
