/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic;

import com.stpl.app.accountclose.gtnbalancereport.dto.DataSelectionDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.DataSelectionLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author santanukumar
 */
public class ResultTableLogic extends PageTableLogic{
    boolean generate=false;
    DataSelectionDTO dataSelectionDTO;
    DataSelectionLogic selectionLogic=new DataSelectionLogic();
    public static final Logger LOGGER = Logger.getLogger(ResultTableLogic.class);
    @Override
    public int getCount() {
        int count=0;
        if (generate) {
            try {
                dataSelectionDTO.setCount(false);
                count=selectionLogic.getSearchCount(dataSelectionDTO,getFilters());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
         List<DataSelectionDTO> resultList=new ArrayList<DataSelectionDTO>();
        try {
            dataSelectionDTO.setCount(true);
            resultList=selectionLogic.getSearchResults(dataSelectionDTO, start, offset, getFilters(),getSortByColumns());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
         return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        DataSelectionDTO dto = (DataSelectionDTO) object;
        ((BeanItemContainer<DataSelectionDTO>) container).addBean(dto);
        return dto;
    }

    public boolean fireSetData(DataSelectionDTO dataSelectionDTO, boolean checkFlag) {
        this.dataSelectionDTO = dataSelectionDTO;
        clearAll();
        setRequiredCount(true);
        generate = !checkFlag;
        setCurrentPage(1);
        return getRecordCount() != 0;
    }
    
}
