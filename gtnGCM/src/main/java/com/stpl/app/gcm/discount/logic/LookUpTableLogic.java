/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.ifs.ui.CustomFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author vigneshkanna
 */
public class LookUpTableLogic extends PageTableLogic {

    LookupDTO binderDto = new LookupDTO();
    LookUpLogic logic = new LookUpLogic();
    boolean generate = false;
    CustomFieldGroup discountChBinder;
    String moduleName;

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        int count = 0;
        if (generate) {
            count = logic.getSearchCount(discountChBinder, moduleName, getFilters());
        }
        return count;
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
        List<LookupDTO> resultList = logic.getSearch(discountChBinder, start, offset, moduleName, getFilters(), getSortByColumns());
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
        LookupDTO dto = (LookupDTO) object;
        ((BeanItemContainer<LookupDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(CustomFieldGroup discountChBinder, String moduleName) {
        this.discountChBinder = discountChBinder;
        this.moduleName = moduleName;
        clearAll();
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

}
