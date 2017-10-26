/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.customergroup.logic.tablelogic;

import com.stpl.app.adminconsole.customergroup.dto.CustomerDetailsDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Mohamed.Shahul
 */
public class CGMTableLogic extends PageTableLogic {

    TableLogic tableLogic;
    CustomerDetailsDTO binderDto;
    boolean isGenerate = Boolean.FALSE;
    /**
     * The Constant LOGGER.
     */
    private static final Logger LOGGER = Logger.getLogger(CGMTableLogic.class);

    public CGMTableLogic(TableLogic tableLogic) {
        this.tableLogic = tableLogic;
    }

    @Override
    public int getCount() {
        LOGGER.debug("Count method starts = = ");
        try {
            if (isGenerate) {
                binderDto.setCount(Boolean.TRUE);
                return tableLogic.getCount(binderDto, getFilters(), getSortByColumns());
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("Count method Ends = = ");
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        LOGGER.debug("Load Data method starts = = ");
        try {
            binderDto.setCount(Boolean.FALSE);
            binderDto.setStart(start);
            binderDto.setOffset(offset);
            binderDto.setSortByColumns(getSortByColumns());
            List list = (List) tableLogic.loadData(binderDto, getFilters(), getSortByColumns());
             LOGGER.debug("Load Data method Ends = = ");
            return list;

        } catch (Exception ex) {
            LOGGER.error(ex);
            return Collections.EMPTY_LIST;
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CustomerDetailsDTO dto = (CustomerDetailsDTO) object;
        ((BeanItemContainer<CustomerDetailsDTO>) container).addBean(dto);
        return dto;
    }

    public boolean loadSetData(CustomerDetailsDTO binderDto) {
        this.binderDto = binderDto;
        clearAll();
        setRequiredCount(true);
        isGenerate = binderDto.isGenerate();
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    public CustomerDetailsDTO getBinderDto() {
        return binderDto;
    }

}
