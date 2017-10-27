/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.tablelogic;

import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryLookupLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;
import com.stpl.app.arm.abstractforms.AbstractFilter;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class CustomerGrpSelectedTableLogic extends PageTableLogic {

    List<CustomerGroupDTO> listCust = new ArrayList<>();
    boolean generate = false;
    CustomerGroupDTO binderDto;
    PipelineInventoryLookupLogic commonLogic = new PipelineInventoryLookupLogic();
    BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer = new BeanItemContainer<>(CustomerGroupDTO.class);
    boolean firstCount = true;
    AbstractSelectionDTO selectionDto;
    public static final Logger LOGGER = Logger.getLogger(CustomerGrpSelectedTableLogic.class);

    @Override
    public int getCount() {
        if (generate) {
            try {
                binderDto.clearFilters();
                if (firstCount) {
                    commonLogic.getCustomerGroupView(binderDto, resultsinventoryContainer, binderDto.isViewFlag(), selectionDto, "customerGroupSelectedTableSearch");
                }
                for (Container.Filter fil : getFilters()) {
                    binderDto.addFilter(fil);
                }

                AbstractFilter.getInstance().filterQueryForContainer(binderDto.getFilters(), resultsinventoryContainer);
                return resultsinventoryContainer.size();

            } catch (Exception ex) {
                LOGGER.error("Error in getCount"+ex);
            }
        }
        return 0;

    }

    @Override
    public List loadData(int start, int offset) {

        LOGGER.debug("Start:==========>" + start + "Offset ========" + offset);
        List<CustomerGroupDTO> resultList = new ArrayList<>();
        binderDto.setStartIndex(start);
        binderDto.setEndIndex(offset);
        try {
            binderDto.clearFilters();
            for (Container.Filter fil : getFilters()) {
                binderDto.addFilter(fil);
            }

            AbstractFilter.getInstance().filterQueryForContainer(binderDto.getFilters(), resultsinventoryContainer);
            AbstractFilter.getInstance().orderByQueryForContainer(binderDto.getSortedColumns(), resultsinventoryContainer);
            resultList = resultsinventoryContainer.getItemIds(start, offset);
        } catch (Exception ex) {
            LOGGER.error("Error in loadData"+ex);
        }

        return resultList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        CustomerGroupDTO dto = (CustomerGroupDTO) object;
        ((BeanItemContainer<CustomerGroupDTO>) container).addBean(dto);
        return dto;
    }

    /* Method to setting up the data which we pass to logic
     *
     * @param binderDto
     * @param sessionDTO
     * @param reset
     * @return 
     */
    public boolean loadSetData(CustomerGroupDTO binderDto, boolean reset, AbstractSelectionDTO selectionDto) {
        this.binderDto = binderDto;
        this.selectionDto = selectionDto;
        clearAll();
        setRequiredCount(true);
        generate = !reset;
        setCurrentPage(1);
        return recordCount != 0;
    }

    public BeanItemContainer<CustomerGroupDTO> getResultsinventoryContainer() {
        return resultsinventoryContainer;
    }

    public void setResultsinventoryContainer(BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer) {
        this.resultsinventoryContainer = resultsinventoryContainer;
    }

    public void addInventoryContainer(CustomerGroupDTO dtoValue, CustomerGroupDTO binderDto) {
        this.binderDto = binderDto;
        resultsinventoryContainer.addBean(dtoValue);
        setRequiredCount(true);
        generate = true;

        setCurrentPage(1);
    }

    public void addAllInventoryContainer(BeanItemContainer<CustomerGroupDTO> dtoValue, CustomerGroupDTO binderDto) {
        this.binderDto = binderDto;
        this.resultsinventoryContainer = dtoValue;
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
    }

    public void removeInventoryContainer(CustomerGroupDTO dtoValue, CustomerGroupDTO binderDto) {
        this.binderDto = binderDto;
        resultsinventoryContainer.removeItem(dtoValue);
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
    }

    public void removeAllInventoryContainer(CustomerGroupDTO binderDto) {
        this.binderDto = binderDto;
        resultsinventoryContainer.removeAllItems();
        setRequiredCount(true);
        generate = true;
        setCurrentPage(1);
    }

    public void setFirstCount(boolean firstCount) {
        this.firstCount = firstCount;
    }

}
