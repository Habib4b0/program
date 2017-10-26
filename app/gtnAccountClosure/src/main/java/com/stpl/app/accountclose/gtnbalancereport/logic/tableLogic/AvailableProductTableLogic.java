/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic;

import com.stpl.app.accountclose.gtnbalancereport.dto.CustomerProductDTO;
import com.stpl.app.accountclose.gtnbalancereport.logic.DataSelectionLogic;
import com.stpl.app.accountclose.sessionutils.SessionDTO;
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
public class AvailableProductTableLogic extends PageTableLogic{
    SessionDTO sessionDTO;
    boolean generate=false;
    DataSelectionLogic dataSelectionLogic=new DataSelectionLogic();
    CustomerProductDTO binderDto =new CustomerProductDTO();
    public static final Logger LOGGER = Logger.getLogger(AvailableProductTableLogic.class);

    @Override
    public int getCount() {
        int count=0;
        if (generate) {
            try {
                count=dataSelectionLogic.getAvailableProductsCount(binderDto, getFilters());
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
         binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
         List<CustomerProductDTO> resultList=new ArrayList<CustomerProductDTO>();
        try {
            resultList=dataSelectionLogic.getAvailableProducts(binderDto, getFilters());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
         return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CustomerProductDTO dto = (CustomerProductDTO) object;
        ((BeanItemContainer<CustomerProductDTO>) container).addBean(dto);
        return dto;
    }
    /**
     * Method to setting up the data which we pass to logic
     *
     * @param binderDto
     * @param sessionDTO
     * @param reset
     * @return 
     */
    public boolean loadSetData(CustomerProductDTO binderDto,SessionDTO sessionDTO,boolean reset) {
        this.binderDto = binderDto;
        this.sessionDTO=sessionDTO;
        clearAll();
        setRequiredCount(true);
        generate = !reset;
        setCurrentPage(1);
        return recordCount != 0;
    }
    
}
