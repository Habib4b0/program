/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class RebateTableLogic extends PageTableLogic {

    RemoveDiscountDto binderDto = new RemoveDiscountDto();
    DiscountLogic logic = new DiscountLogic();
    boolean generate = false;
    CustomFieldGroup discountChBinder;

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            return logic.getContractSearchCount(discountChBinder, getFilters());
        }
        return 0;
    }

    /**
     * Loading Grid
     *
     * @param start
     * @param offset
     * @return List of results
     */
    @Override
    public List loadData(int start, int offset) {
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        List<RemoveDiscountDto> resultList = logic.getContractSearch(discountChBinder, start, offset, getFilters(), getSortByColumns());
        return resultList;
    }

    /**
     * Configure container
     *
     * @param object
     * @param container
     * @return Object
     */
    @Override
    public Object configureContainer(Object object, Container container) {
        RemoveDiscountDto dto = (RemoveDiscountDto) object;
        ((BeanItemContainer<RemoveDiscountDto>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(RemoveDiscountDto binderDto, CustomFieldGroup discountChBinder) {
        this.discountChBinder = discountChBinder;
        clearAll();
        setRequiredCount(true);
       generate = binderDto.isSearch();
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
