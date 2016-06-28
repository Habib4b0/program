/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
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
    String indicator;

    @Override
    public int getCount() {
        int count = 0;
        count = logic.getSelectedContractCount(userId, sessionId, rebateList,indicator, getFilters());
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<RemoveDiscountDto> list = new ArrayList<>();
        list = logic.getSelectedContract(start, offset, userId, sessionId, rebateList,indicator, getFilters());
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        RemoveDiscountDto dto = (RemoveDiscountDto) object;
        ((BeanItemContainer<RemoveDiscountDto>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param userId
     * @param sessionId
     */
    public void loadSetData(int userId, int sessionId, List<String> rebateList, String indicator) {
        this.userId = userId;
        this.sessionId = sessionId;
        this.rebateList = rebateList;
        this.indicator = indicator;
        this.clearAll();
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }
}
