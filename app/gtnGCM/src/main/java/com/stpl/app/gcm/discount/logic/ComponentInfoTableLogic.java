/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author lokeshwari
 */
public class ComponentInfoTableLogic extends PageTableLogic {

    int userId;
    int sessionId;
    DiscountLogic logic = new DiscountLogic();
    List<String> rebateList = new ArrayList();
    ContractsDetailsDto dto;
    public String indicator;
    public boolean summaryFlag;

    @Override
    public int getCount() {
        int count = 0;
        count = logic.getSelectedContractCount(userId, sessionId, rebateList, indicator, getFilters(), dto, summaryFlag);
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<RemoveDiscountDto> list;
        list = logic.getSelectedContract(start, offset, userId, sessionId, rebateList, indicator, getFilters(), dto, summaryFlag);
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        RemoveDiscountDto removeDiscountDto = (RemoveDiscountDto) object;
        ((BeanItemContainer<RemoveDiscountDto>) container).addBean(removeDiscountDto);
        return removeDiscountDto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param userId
     * @param sessionId
     */
    public void loadSetData(int userID, int sessionID, List<String> rebateList, String indicator, ContractsDetailsDto dto,boolean summaryFlag) {
        this.userId = userID;
        this.sessionId = sessionID;
        this.rebateList = rebateList;
        this.indicator = indicator;
        this.dto = dto;
        this.summaryFlag = summaryFlag;
        this.clearAll();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
}
