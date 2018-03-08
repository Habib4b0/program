/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.ComponentInfoDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class ComponentInfoTableLogic extends PageTableLogic {

    
    private ComponentInfoDTO binderDto = new ComponentInfoDTO();
    private final AbstractLogic logic = AbstractLogic.getInstance();
    private boolean generate = true;
    private SelectionDTO selection = new SelectionDTO();

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (!generate) {
            binderDto.setIsCount(BooleanConstant.getFalseFlag());
            selection.setFilters(getFilters());
            return logic.getComponentInfoCount(binderDto, selection);
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
        binderDto.setIsCount(BooleanConstant.getTrueFlag());
        selection.setFilters(getFilters());
        List<ComponentInfoDTO> resultList = logic.getComponentInfoResults(binderDto, selection);
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
        ComponentInfoDTO dto = (ComponentInfoDTO) object;
        ((BeanItemContainer<ComponentInfoDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(ComponentInfoDTO binderDto, SelectionDTO selection) {
        this.binderDto = binderDto;
        this.selection = selection;
        clearAll();
        setRequiredCount(true);
        generate = binderDto.getReset();
        setCurrentPage(1);
        return recordCount != 0;
    }
}
