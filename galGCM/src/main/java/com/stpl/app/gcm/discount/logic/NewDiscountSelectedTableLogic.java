/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.ContractsDetailsDto;
import com.stpl.app.gcm.sessionutils.SessionDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.ExtPagedTable;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class NewDiscountSelectedTableLogic extends PageTableLogic {

    ContractsDetailsDto binderDto = new ContractsDetailsDto();
    SessionDTO sessionDTO;
    DiscountLogic logic = new DiscountLogic();
    boolean generate = false;
    private static final Logger LOGGER = Logger.getLogger(NewDiscountSelectedTableLogic.class);

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            return DiscountLogic.getCountForNewDiscountSelectedItems(binderDto, sessionDTO, true, false);
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
        List<ContractsDetailsDto> resultList = new ArrayList<ContractsDetailsDto>();
        try {
            resultList = DiscountLogic.getCommonResults(binderDto, sessionDTO, true);
        } catch (Exception ex) {
            LOGGER.error(ex);
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
        ContractsDetailsDto dto = (ContractsDetailsDto) object;
        ((BeanItemContainer<ContractsDetailsDto>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(ContractsDetailsDto binderDto, SessionDTO sessionDTO, boolean reset) {
        this.binderDto = binderDto;
        this.sessionDTO = sessionDTO;
        resetAndLoadData(reset);
        return recordCount != 0;
    }
    
    /**
     * To Reset the table and load it
     * @param reset Data load will be prevented if reset flag is true
     */
    public void resetAndLoadData(boolean reset){
        clearAll();
        getFilters().clear();
        setRequiredCount(true);
        for(ExtPagedTable table:this.getTables()){
            table.setValue(null);
        }
        generate = !reset;
        setCurrentPage(1);
    }
}
