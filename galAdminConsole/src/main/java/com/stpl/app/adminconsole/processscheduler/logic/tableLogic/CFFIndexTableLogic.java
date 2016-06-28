/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic.tableLogic;

import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author ahalya
 */
public class CFFIndexTableLogic extends PageTableLogic {
    
 /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CFFIndexTableLogic.class);
    ProcessSchedulerDTO binderDto;
    /**
     * The Cff logic
     */
    private final ProcessSchedulerLogic cffLogic = new ProcessSchedulerLogic();
    Boolean isGenerate = false;
    boolean isCheckAll = false;
    /**
     * Method to get the no of count for Searching results.
     *
     * @return int
     */
    @Override
    public int getCount() {
        int count = 0;
        if (isGenerate) {
            try {
                binderDto.setCount(Boolean.FALSE);
                 binderDto.setFilters(getFilters());
                count = cffLogic.getSearchCount(binderDto);
            } catch (Exception ex) {
              
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            binderDto.setCount(Boolean.TRUE);
            binderDto.setStartIndex(start);
            binderDto.setEndIndex(offset);
            binderDto.setFilters(getFilters());
            binderDto.setOrderByColumns(getSortByColumns());
            return cffLogic.getSearchResults(binderDto,isCheckAll);
        } catch (Exception ex) {
           
            LOGGER.error(ex);
        }
        return new ArrayList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ProcessSchedulerDTO dto = (ProcessSchedulerDTO) object;
        ((BeanItemContainer<ProcessSchedulerDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     * @param isGenerate
     * @param isCheckAll
     * @return 
     */
    public boolean loadSetData(ProcessSchedulerDTO binderDto, Boolean isGenerate,boolean isCheckAll) {
        this.isGenerate = isGenerate;
        this.binderDto = binderDto;
        this.isCheckAll=isCheckAll;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return recordCount != 0;
    }
}

