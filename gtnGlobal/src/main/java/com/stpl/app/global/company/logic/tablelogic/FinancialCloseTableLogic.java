/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.company.logic.tablelogic;

import com.stpl.app.global.company.dto.FinancialCloseDTO;
import com.stpl.app.global.company.logic.FinancialCloseLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sathyaseelan.v
 */
public class FinancialCloseTableLogic extends PageTableLogic {

    FinancialCloseDTO binderDto;
    final FinancialCloseLogic logic = new FinancialCloseLogic();
    boolean isGenerate = false;
    public static final Logger LOGGER = Logger.getLogger(FinancialCloseTableLogic.class);
    private final BeanItemContainer<FinancialCloseDTO> resultsContainer = new BeanItemContainer<>(FinancialCloseDTO.class);

    @Override
    public int getCount() {
        try {
            if (isGenerate) {
                binderDto.setFilters(getFilters());
                binderDto.setSortedColumns(getSortByColumns());
                binderDto.setCount(Boolean.FALSE);
                int count = logic.getFinancialCloseCount(binderDto, resultsContainer);
                return count;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            binderDto.setFilters(getFilters());
            binderDto.setStart(start);
            binderDto.setEnd(offset);
            binderDto.setCount(Boolean.TRUE);
            List list = logic.getFinancialCloseData(binderDto, resultsContainer);
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return new ArrayList();
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        FinancialCloseDTO dto = (FinancialCloseDTO) object;
        ((BeanItemContainer<FinancialCloseDTO>) container).addBean(dto);
        return dto;
    }

    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     */
    public boolean loadSetData(final FinancialCloseDTO binderDto) {
        this.binderDto = binderDto;
        clearAll();
        isGenerate = true;
        setRequiredCount(true);
        setCurrentPage(1);
        return getRecordCount() != 0;
    }

    public BeanItemContainer<FinancialCloseDTO> getResultsContainer() {
        return resultsContainer;
    }

    public FinancialCloseDTO getBinderDto() {
        return binderDto;
    }
}
