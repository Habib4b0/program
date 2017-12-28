/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author santanukumar
 */
public class ExistingTabSearchTableLogic extends PageTableLogic {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExistingTabSearchTableLogic.class);
    ContractsDetailsDto newDiscountTabDto = new ContractsDetailsDto();
    DiscountLogic logic = new DiscountLogic();
    boolean generate = false;

    @Override
    public int getCount() {
        if (generate) {
            try {
                return logic.getRebateScheduleCount(newDiscountTabDto);
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        newDiscountTabDto.setStartIndex(start);
        newDiscountTabDto.setEndIndex(offset);
        List<ContractsDetailsDto> resultList = new ArrayList<>();
        try {
            resultList = logic.getRebateSchedule(newDiscountTabDto);
        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ContractsDetailsDto dto = (ContractsDetailsDto) object;
        ((BeanItemContainer<ContractsDetailsDto>) container).addBean(dto);
        return dto;
    }

    public boolean loadSetData(ContractsDetailsDto newDiscountTabDto, boolean resetFlag) {
        this.newDiscountTabDto = newDiscountTabDto;
        clearAll();
        setRequiredCount(true);
        generate = !resetFlag;
        setCurrentPage(1);
        return recordCount != 0;
    }

}
