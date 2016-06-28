/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic.abstracttablelogic;

import com.stpl.app.gcm.globalchange.dto.SelectionDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.stpl.app.gcm.itemmanagement.itemabstract.logic.AbstractLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author mohamed.hameed
 */
public class FormulaIdTableLogic extends PageTableLogic {

    FormulaDTO binderDto = new FormulaDTO();
    SelectionDTO selection;
    boolean generate = true;
    AbstractLogic logic = AbstractLogic.getInstance();

    @Override
    public int getCount() {
        int count = 0;
        binderDto.setIsCount(false);

        if (!generate) {
            count = logic.getFormulaIdCount(binderDto, selection);
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        binderDto.setIsCount(true);
        return logic.getFormulaIdRecords(binderDto, selection);
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        FormulaDTO dto = (FormulaDTO) object;
        ((BeanItemContainer<FormulaDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(FormulaDTO binderDto, SelectionDTO selection) {
        this.binderDto = binderDto;
        this.selection = selection;
        clearAll();
        setRequiredCount(true);
        generate = selection.isReset();
        setCurrentPage(1);
        return recordCount != 0;
    }
}
