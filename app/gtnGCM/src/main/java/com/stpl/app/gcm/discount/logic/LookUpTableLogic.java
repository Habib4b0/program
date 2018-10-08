/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author vigneshkanna
 */
public class LookUpTableLogic extends PageTableLogic {

    private final LookupDTO binderDto = new LookupDTO();
    private final LookUpLogic logic = new LookUpLogic();
    private boolean generate = false;
    private ErrorfulFieldGroup discountChBinder;
    private String moduleName;
    
    public LookUpTableLogic() {
        super();
    }

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
        return logic.getSearch(discountChBinder, start, offset, moduleName, getFilters(), getSortByColumns());
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
    public boolean loadSetData(ErrorfulFieldGroup discountChBinder, String moduleName) {
        this.discountChBinder = discountChBinder;
        this.moduleName = moduleName;
        clearAll();
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

}
