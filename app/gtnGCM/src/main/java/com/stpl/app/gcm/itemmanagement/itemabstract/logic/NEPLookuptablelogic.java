/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.itemmanagement.itemabstract.logic;

import com.stpl.app.gcm.itemmanagement.itemabstract.dto.FormulaDTO;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author rohitvignesh.s
 */
public class NEPLookuptablelogic extends PageTableLogic {

    private AbstractLogic logic = AbstractLogic.getInstance();
    private static final Logger LOGGER = LoggerFactory.getLogger(NEPLookuptablelogic.class);
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    private FormulaDTO formulaDto;

    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) logic.lookupCountQuery(formulaDto, 0, 0, null, this.getFilters(), true);
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error("",ex);

        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list =  logic.lookupResultsQuery(formulaDto, start, offset, this.getSortByColumns(), this.getFilters(), false);
            } catch (Exception ex) {
                LOGGER.error("",ex);

            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        FormulaDTO nepDto = (FormulaDTO) object;
        ((BeanItemContainer<FormulaDTO>) container).addBean(nepDto);
        return nepDto;
    }

    /**
     *
     * @param formulaDto
     */
    public void configureSearchData(FormulaDTO formulaDto) {
        clearAll();
        this.formulaDto = formulaDto;
        setRequiredCount(true);
        isFirstLoad = true;
        isReset = false;
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }
}
