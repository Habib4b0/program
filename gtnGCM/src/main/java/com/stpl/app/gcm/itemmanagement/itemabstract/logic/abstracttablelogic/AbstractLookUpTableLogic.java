/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentLookUpDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class AbstractLookUpTableLogic extends PageTableLogic {

    ComponentLookUpDTO binderDto = new ComponentLookUpDTO();
    List<ComponentLookUpDTO> componentList = new ArrayList<ComponentLookUpDTO>();
    AbstractLogic logic = AbstractLogic.getInstance();
    SelectionDTO selection;
    boolean isGenerated = false;

    public AbstractLookUpTableLogic() {
    }

    @Override
    public int getCount() {
        if (isGenerated) {
            binderDto.setIsCount(false);
            selection.setFilters(getFilters());
            return logic.getLookUpSearchCount(binderDto, selection);
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        binderDto.setIsCount(true);
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        selection.setFilters(getFilters());
        return logic.getLookUpSearchResults(binderDto, selection);
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
