/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author kasiammal.m
 */
public class RemoveDiscountRsSearchLogic extends PageTableLogic {

    private String field = "";
    private String value = "";
    private int contractSid = 0;
    private int rsSid = 0;
    private final DiscountLogic logic = new DiscountLogic();

    @Override
    public int getCount() {
        int count = 0;
        List<RemoveDiscountDto> resultList = logic.getRebateSearch(field, value, contractSid, rsSid);
        count = resultList.size();
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List<RemoveDiscountDto> list;
        list = logic.getRebateSearch(field, value, contractSid, rsSid);
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        RemoveDiscountDto dto = (RemoveDiscountDto) object;
        ((BeanItemContainer<RemoveDiscountDto>) container).addBean(dto);
        return dto;
    }

    public void loadSetData(String field, String value, int contractSid, int rsSid) {
        this.clearAll();
        this.field = field;
        this.value = value;
        this.contractSid = contractSid;
        this.rsSid = rsSid;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

}
