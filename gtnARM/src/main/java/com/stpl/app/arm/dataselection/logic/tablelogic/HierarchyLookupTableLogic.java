/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic.tablelogic;

import com.stpl.app.arm.dataselection.dto.HierarchyLookupDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.Collections;
import java.util.List;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author sathyaseelan.v
 */
public class HierarchyLookupTableLogic extends PageTableLogic {
    /**
	 * The Constant LOGGER.
	 */
	public static final Logger LOGGER = Logger.getLogger(HierarchyLookupTableLogic.class);

    DataSelectionLogic logic = new DataSelectionLogic();
    HierarchyLookupDTO hierarchyLookupDTO = new HierarchyLookupDTO();
    boolean isGenerate = false;
    boolean isResultsEmpty;

    @Override
    public int getCount() {
        try {
            List<Object> count = logic.searchLogicForHierarchy(hierarchyLookupDTO, true, 0, 0, null, this.getFilters());
            isResultsEmpty = Integer.valueOf(count.get(0).toString()) == 0;
            return Integer.valueOf(count.get(0).toString());
        } catch (Exception ex) {
           LOGGER.error(ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        try {
            if (isGenerate) {
                List<HierarchyLookupDTO> list = logic.searchLogicForHierarchy(hierarchyLookupDTO, false, start, offset, this.getSortByColumns(), this.getFilters());
                return list;
            } else {
                return Collections.EMPTY_LIST;
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
            return null;
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
