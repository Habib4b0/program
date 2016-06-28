/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.lazyLoad;

import com.stpl.app.cff.dto.CFFSearchDTO;
import com.stpl.app.cff.logic.CFFLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Manasa
 */
public class CFFIndexTableLogic extends PageTableLogic {

    /**
     * The logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CFFIndexTableLogic.class);
    CFFSearchDTO binderDto;
    /**
     * The Cff logic
     */
    private final CFFLogic cffLogic = new CFFLogic();
    Boolean isGenerate = false;
    Boolean isReset = true;
    /**
     * Method to get the no of count for Searching results.
     *
     * @return int
     */
    @Override
    public int getCount() {
        int count = 0;
        if(!isReset){
        if (isGenerate) {
            try {
                binderDto.setCount(Boolean.FALSE);
                 binderDto.setFilters(this.getFilters());
                count = cffLogic.getSearchCount(binderDto);
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
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
            binderDto.setFilters(this.getFilters());
            binderDto.setOrderByColumns(getSortByColumns());
            return cffLogic.getSearchResults(binderDto);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return new ArrayList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CFFSearchDTO dto = (CFFSearchDTO) object;
        ((BeanItemContainer<CFFSearchDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     * @param isGenerate
     * @return 
     */
    public boolean loadSetData(CFFSearchDTO binderDto, Boolean isGenerate,Boolean isReset) {
        this.isGenerate = isGenerate;
        this.isReset = isReset;
        this.binderDto = binderDto;
        clearAll();
        this.getFilters().clear();
        setRequiredCount(true);
        setCurrentPage(1);
        return recordCount != 0;
    }
}
