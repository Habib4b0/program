/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.index.logic.ItemLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class ComponentLookUpLogic extends PageTableLogic {

    private ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    private final ItemLogic logic = new ItemLogic();
    private SelectionDTO selection;
    private boolean isGenerated = false;
    public static final Logger LOGGER = LoggerFactory.getLogger(ComponentLookUpLogic.class);
    public ComponentLookUpLogic() {
        LOGGER.debug("Inside Constructor");
    }

    @Override
    public int getCount() {
        if (isGenerated) {
            binderDto.setIsCount(false);
            selection.setFilters(getFilters());
            return logic.getComponentCount(binderDto, selection, getFilters());
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        binderDto.setIsCount(true);
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        selection.setFilters(getFilters());
        if (selection.getComponentScreen().equalsIgnoreCase("Component Lookup")) {
            return logic.getComponentResults(binderDto, selection, getFilters());
        } else {
            return logic.getComponentSearchResults(binderDto, selection);
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ComponentLookUpDTO dto = (ComponentLookUpDTO) object;
        ((BeanItemContainer<ComponentLookUpDTO>) container).addBean(dto);
        return dto;
    }

    public Boolean loadSetData(final ComponentLookUpDTO binderDto, SelectionDTO selection) {
        this.binderDto = binderDto;
        this.selection = selection;
        isGenerated = true;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;

    }

}
