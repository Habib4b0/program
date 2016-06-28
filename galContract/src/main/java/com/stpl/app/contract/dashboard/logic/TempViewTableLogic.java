/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.dashboard.logic;

import com.stpl.app.contract.common.dto.SessionDTO;
import com.stpl.app.contract.global.dto.VwContractPriceInfoDTO;
import com.stpl.app.contract.global.logic.IfpLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Asha
 */
public class TempViewTableLogic extends PageTableLogic {
    
    private static final Logger LOGGER = Logger.getLogger(TempViewTableLogic.class);
    private boolean isFirstLoad = false;
    IfpLogic ifpLogic;
    String record = StringUtils.EMPTY;
    private int count;
    Map<Integer,String> priceType;
    SessionDTO sessionDTO;

    @Override
    public int getCount() {
        LOGGER.info("Count Stasted--->> ");
        int count = 0;
        try {
            if (isFirstLoad) {
                List list = ifpLogic.getLazyItemPricingDeatils(0,0,this.getFilters(),true,getRecord(),false, Boolean.FALSE);
                    count = Integer.valueOf(String.valueOf(list.get(0)));
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
        List<Object[]> returnList = new ArrayList();
        if (isFirstLoad) {
            try {
                returnList =  ifpLogic.getLazyItemPricingDeatils(start, offset,this.getFilters(),false, getRecord(),false, Boolean.FALSE);
                return ifpLogic.getCustomizedPricingDTO(returnList,priceType,false,getRecord());
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex);
            }
        }

        return returnList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        VwContractPriceInfoDTO dto = (VwContractPriceInfoDTO) object;
        ((BeanItemContainer<VwContractPriceInfoDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final SessionDTO sessionDTO,Map<Integer,String> priceType) {
        LOGGER.info("Table Logic Called-->>>");

        isFirstLoad = true;
        this.sessionDTO = sessionDTO;
        this.priceType=priceType;
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
