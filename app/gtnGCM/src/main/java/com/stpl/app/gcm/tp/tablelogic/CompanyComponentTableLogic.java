
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.tp.tablelogic;

import com.stpl.app.gcm.tp.dto.ComponentInformationDTO;
import com.stpl.app.gcm.tp.logic.ContractSelectionLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;

/**
 *
 * @author srithar
 */
public class CompanyComponentTableLogic extends PageTableLogic {

    private ContractSelectionLogic logic = new ContractSelectionLogic();
    boolean generate = false;
    String componentSelectionValue;
    String[] id;

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        int count = 0;
        if (generate) {
            count = logic.getComponentInformationCount(componentSelectionValue, id, getFilters());

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
        return logic.getComponentInformation(componentSelectionValue, id, start, offset, getFilters());
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
        ComponentInformationDTO dto = (ComponentInformationDTO) object;
        ((BeanItemContainer<ComponentInformationDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(String componentSelectionValue, String[] id, boolean reset) {
        this.componentSelectionValue = componentSelectionValue;
        this.id = id;
        clearAll();
        setRequiredCount(true);
        generate = reset;
        setCurrentPage(1);
        return recordCount != 0;
    }
}

