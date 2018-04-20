/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.businessprocess.pipelineinventory.tablelogic;

import com.stpl.app.arm.abstractforms.AbstractFilter;
import com.stpl.app.arm.businessprocess.abstractbusinessprocess.dto.AbstractSelectionDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.dto.CustomerGroupDTO;
import com.stpl.app.arm.businessprocess.pipelineinventory.logic.PipelineInventoryLookupLogic;
import com.stpl.app.utils.ExtStringFilter;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import com.vaadin.v7.data.util.filter.Compare;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Porchelvi.Gunasekara
 */
public class CustomerGrpAvailableTableLogic extends PageTableLogic {

    private boolean generate = false;
    private CustomerGroupDTO binderDto;
    private PipelineInventoryLookupLogic commonLogic = new PipelineInventoryLookupLogic();
    private BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer = new BeanItemContainer<>(CustomerGroupDTO.class);
    private boolean firstCount = true;
    private AbstractSelectionDTO selectionDto;
    private static final Logger LOGGER = LoggerFactory.getLogger(CustomerGrpAvailableTableLogic.class);

    public CustomerGrpAvailableTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        if (generate) {
            try {
                binderDto.clearFilters();

                if (firstCount) {
                    resultsinventoryContainer.removeAllContainerFilters();
                    resultsinventoryContainer.removeAllItems();
                    commonLogic.getInventCustomerProductGroupCount(binderDto, resultsinventoryContainer, binderDto.isViewFlag(), selectionDto);

                }
                for (Container.Filter fil : getFilters()) {
                    binderDto.addFilter(fil);
                }
                ExtStringFilter filter = null;
                ExtStringFilter filter1 = null;
                if (!binderDto.getCustomerGroupName().isEmpty()) {
                    filter = new ExtStringFilter("customerGroupName", binderDto.getCustomerGroupName(), true, true, true);
                    binderDto.addFilter(filter);
                }
                if (!binderDto.getCustomerGroupNo().isEmpty()) {
                    filter1 = new ExtStringFilter("customerGroupNo", binderDto.getCustomerGroupNo(), true, true, true);
                    binderDto.addFilter(filter1);
                }
                Container.Filter filter2 = new Compare.Equal("selectedFlag", Boolean.FALSE);
                Set<Container.Filter> filterSet = binderDto.getFilters();
                filterSet.add(filter2);
                binderDto.setFilters(filterSet);
                AbstractFilter.getInstance().filterQueryForContainer(binderDto.getFilters(), resultsinventoryContainer);
                return resultsinventoryContainer.size();

            } catch (Exception ex) {
                LOGGER.error("Error in getCount :" + ex);
            }
        }
        return 0;
    }

    @Override
    public List loadData(int start, int offset) {
        List<CustomerGroupDTO> resultList = new ArrayList<>();

        try {
            binderDto.clearFilters();
            for (Container.Filter fil : getFilters()) {
                binderDto.addFilter(fil);
            }
            ExtStringFilter filter = null;
            ExtStringFilter filter1 = null;
            if (!binderDto.getCustomerGroupName().isEmpty()) {
                filter = new ExtStringFilter("customerGroupName", binderDto.getCustomerGroupName(), true, true, true);
                binderDto.addFilter(filter);
            }
            if (!binderDto.getCustomerGroupNo().isEmpty()) {
                filter1 = new ExtStringFilter("customerGroupNo", binderDto.getCustomerGroupNo(), true, true, true);
                binderDto.addFilter(filter1);
            }
            Container.Filter filter2 = new Compare.Equal("selectedFlag", Boolean.FALSE);
            Set<Container.Filter> filterSet = binderDto.getFilters();
            filterSet.add(filter2);
            binderDto.setFilters(filterSet);
            binderDto.setSortedColumns(getSortByColumns());
            AbstractFilter.getInstance().filterQueryForContainer(binderDto.getFilters(), resultsinventoryContainer);
            AbstractFilter.getInstance().orderByQueryForContainer(binderDto.getSortedColumns(), resultsinventoryContainer);
            resultList = resultsinventoryContainer.getItemIds(start, offset);
        } catch (Exception ex) {
            LOGGER.error("Error in loadData :" + ex);
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
        getFilters().clear();
        setRequiredCount(true);
        generate = !reset;
        setCurrentPage(1);
        return Double.compare(recordCount, 0.0) != 0;
    }

    public BeanItemContainer<CustomerGroupDTO> getResultsinventoryContainer() {
        return resultsinventoryContainer;
    }

    public void setResultsinventoryContainer(BeanItemContainer<CustomerGroupDTO> resultsinventoryContainer) {
        this.resultsinventoryContainer = resultsinventoryContainer;
    }

    public void setFirstCount(boolean firstCount) {
        this.firstCount = firstCount;
    }

}
