/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.copycontract.logic.tablelogic;

import com.stpl.app.gcm.copycontract.dto.ExistingComponentDTO;
import com.stpl.app.gcm.copycontract.logic.CopyContractLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author srithar
 */
public class ExistingComponentResultsTableLogic extends PageTableLogic {
    
    boolean generate = false;
    String componentType;
    CopyContractLogic logic = new CopyContractLogic();
    List input;
    List newInput = new ArrayList();
    private static final Logger LOGGER = LoggerFactory.getLogger(ExistingComponentResultsTableLogic.class);

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        newInput.clear();
        
        if (generate) {
            try {
                newInput.addAll(input);
                return Integer.valueOf(String.valueOf(logic.getExistingDetailsCount(componentType, newInput, true, 0, 0)));
            } catch (ParseException ex) {
                LOGGER.error("",ex);
            }
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
        newInput.clear();
        newInput.addAll(input);
        List<ExistingComponentDTO> resultList = null;
        try {
            resultList = (List<ExistingComponentDTO>) logic.getExistingDetailsCount(componentType, newInput, false, start, offset);
        } catch (ParseException ex) {
           LOGGER.error("",ex);
        }
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
        ExistingComponentDTO dto = (ExistingComponentDTO) object;
        ((BeanItemContainer<ExistingComponentDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param componentType
     * @param componentInnerType
     * @param componentSelection
     * @param isReset
     * @return
     */
    public boolean loadSetData(String componentType, List input, boolean isReset) {
        this.componentType = componentType;
        clearAll();
        this.input = input;
        setRequiredCount(true);
        generate = isReset;
        setCurrentPage(1);
        return recordCount == 0;
    }
}
