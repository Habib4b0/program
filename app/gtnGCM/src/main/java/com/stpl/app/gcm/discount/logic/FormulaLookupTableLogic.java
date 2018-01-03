/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.discount.logic;

import com.stpl.app.gcm.discount.dto.LookupDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class FormulaLookupTableLogic extends PageTableLogic {
 private static final Logger LOGGER = Logger.getLogger(DiscountLogic.class);
   
    private LookupDTO binderDto = new LookupDTO();
    private final DiscountLogic logic = new DiscountLogic();
    private boolean generate = false;

    /**
     * Record count logic
     *
     * @return integer as count
     */
    @Override
    public int getCount() {
        if (generate) {
            try {
                return logic.getFormulaSearchCount(binderDto);
            } catch (Exception ex) {
                 LOGGER.error(ex);
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
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        List<LookupDTO> resultList = new ArrayList<>();
        try {
            resultList = logic.getFormulaSearchResults(binderDto);
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
        LookupDTO dto = (LookupDTO) object;
        ((BeanItemContainer<LookupDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(LookupDTO binderDto) {
        this.binderDto = binderDto;
        clearAll();
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
        return recordCount != 0;
    }
}
