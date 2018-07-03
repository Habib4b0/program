/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic.tablelogic;

import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author
 */
public class HierarchyLookupTableLogic extends PageTableLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(HierarchyLookupTableLogic.class);

    private DataSelectionLogic logic = new DataSelectionLogic();
    private HierarchyLookupDTO hierarchyLookupDTO = new HierarchyLookupDTO();
    private boolean isGenerate = false;
    private boolean isResultsEmpty;

    public HierarchyLookupTableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        try {
            List<Object> count = logic.searchLogicForHierarchy(hierarchyLookupDTO, true, 0, 0, null, this.getFilters());
            isResultsEmpty = Integer.valueOf(count.get(0).toString()) == 0;
            return Integer.valueOf(count.get(0).toString());
        } catch (Exception ex) {
            LOGGER.error("Error in GetCount :" + ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            if (isGenerate) {
                return logic.searchLogicForHierarchy(hierarchyLookupDTO, false, start, offset, this.getSortByColumns(), this.getFilters());
            } else {
                return Collections.emptyList();
            }
        } catch (Exception ex) {
            LOGGER.error("Error in LoadData" + ex);
            return Collections.emptyList();
        }
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        HierarchyLookupDTO dto = (HierarchyLookupDTO) object;
        ((BeanItemContainer<HierarchyLookupDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(HierarchyLookupDTO hierarchyLookupDTO, boolean isGenerate) {
        this.isGenerate = isGenerate;
        this.hierarchyLookupDTO = hierarchyLookupDTO;
        this.clearAll();
        this.getFilters().clear();
        this.setRequiredCount(true);
        setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }
}
