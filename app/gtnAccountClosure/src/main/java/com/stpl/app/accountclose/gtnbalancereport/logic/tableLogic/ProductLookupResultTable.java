/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.app.accountclose.gtnbalancereport.logic.tableLogic;

import com.stpl.app.accountclose.common.CommonLogic;
import com.stpl.app.accountclose.dto.GroupDTO;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author Santanukumar
 */
public class ProductLookupResultTable extends PageTableLogic{
    boolean generate=false;
    GroupDTO binderDto;
    CommonLogic commonLogic=new CommonLogic();
    public static final Logger LOGGER = Logger.getLogger(ProductLookupResultTable.class);
    @Override
    public int getCount() {
        if (generate) {
            try {
                return commonLogic.getCustomerProductGroupCount(binderDto,getFilters());
            } catch (Exception ex) {
               LOGGER.error(ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
         List<GroupDTO> resultList=new ArrayList<GroupDTO>();
         binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        try {
            resultList=commonLogic.getCustomerProductGroup(binderDto,getFilters());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
         return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        GroupDTO dto = (GroupDTO) object;
        ((BeanItemContainer<GroupDTO>) container).addBean(dto);
        return dto;
    }
     /* Method to setting up the data which we pass to logic
     *
     * @param binderDto
     * @param sessionDTO
     * @param reset
     * @return 
     */
    public boolean loadSetData(GroupDTO binderDto,boolean reset) {
        this.binderDto = binderDto;
        clearAll();
        setRequiredCount(true);
        generate = !reset;
        setCurrentPage(1);
        return recordCount != 0;
    }
    
}