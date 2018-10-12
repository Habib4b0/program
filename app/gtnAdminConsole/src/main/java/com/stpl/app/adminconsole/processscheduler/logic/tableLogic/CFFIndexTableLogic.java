/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic.tablelogic;

import java.util.ArrayList;
import java.util.List;

import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.adminconsole.common.dto.SessionDTO;
import com.stpl.app.adminconsole.processscheduler.dto.ProcessSchedulerDTO;
import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import com.stpl.ifs.util.constants.BooleanConstant;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;

/**
 *
 * @author ahalya
 */
public class CFFIndexTableLogic extends PageTableLogic {

    /**
     * The logger.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CFFIndexTableLogic.class);
    private ProcessSchedulerDTO binderDto;
    /**
     * The Cff logic
     */
    private final ProcessSchedulerLogic cffLogic = new ProcessSchedulerLogic();
    private boolean isGenerate = false;
    private SessionDTO sessionDTO;
    
    
    
    public CFFIndexTableLogic() {
    	LOGGER.info("Table logic invoked");
	}

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
                binderDto.setCount(BooleanConstant.getFalseFlag());
                binderDto.setFilters(getFilters());
                count = cffLogic.getSearchCount(binderDto, sessionDTO);
            } catch (Exception ex) {

               LOGGER.error(ex.getMessage());
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug(" Start= {}, offset= {} " , start, offset);
        try {
            binderDto.setCount(BooleanConstant.getTrueFlag());
            binderDto.setStartIndex(start);
            binderDto.setEndIndex(offset);
            binderDto.setFilters(getFilters());
            binderDto.setOrderByColumns(getSortByColumns());
            return cffLogic.getSearchResults(binderDto, sessionDTO);
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
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
    public boolean loadSetData(ProcessSchedulerDTO binderDto, Boolean isGenerate, SessionDTO sessionDTO) {
        this.isGenerate = isGenerate;
        this.binderDto = binderDto;
        this.sessionDTO = sessionDTO;
        clearAll();
        setRequiredCount(true);
        setCurrentPage(1);
        return recordCount != 0;
    }
}
