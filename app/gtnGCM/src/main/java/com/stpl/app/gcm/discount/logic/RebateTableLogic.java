/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.RemoveDiscountDto;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class RebateTableLogic extends PageTableLogic {

    private final RemoveDiscountDto binderDto = new RemoveDiscountDto();
    private final DiscountLogic logic = new DiscountLogic();
    private boolean generate = false;
    private ErrorfulFieldGroup discountChBinder;

    public RebateTableLogic() {
        super();
    }

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
        return logic.getContractSearch(discountChBinder, start, offset, getFilters(), getSortByColumns());
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
    public boolean loadSetData(RemoveDiscountDto binderDto, ErrorfulFieldGroup discountChBinder) {
        this.discountChBinder = discountChBinder;
        clearAll();
        setRequiredCount(true);
       generate = binderDto.isSearch();
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
}
