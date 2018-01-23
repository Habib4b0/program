/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.processscheduler.logic.tableLogic;

import com.stpl.app.adminconsole.processscheduler.dto.HierarchyDefinitionDTO;
import com.stpl.app.adminconsole.processscheduler.logic.OutboundLogic;
import com.stpl.app.ui.errorhandling.ErrorfulFieldGroup;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

/**
 *
 * @author vinodhini
 */
public class HierarchyTableLogic extends PageTableLogic {

    private static final Logger LOGGER = Logger.getLogger(HierarchyTableLogic.class);
    private final OutboundLogic searchLogic = new OutboundLogic();
    private boolean isFirstLoad = false;
    private boolean isResultsEmpty;
    private boolean isReset = false;
    private String hierarchyType = StringUtils.EMPTY;
    private ErrorfulFieldGroup binder;
    private boolean isCheckAll=false;

    public HierarchyTableLogic(){
    	super();
    }
    @Override
    public int getCount() {
        int count = 0;
        try {
            if (isFirstLoad) {
                count = (Integer) searchLogic.getHierarchyDefinitionCount(binder, this.getFilters(), hierarchyType);
            }
            isResultsEmpty = count == 0;
            count = isReset ? 0 : count;
        } catch (Exception ex) {
            LOGGER.error(ex);
           
        }
        return count;
    }

    @Override
    public List loadData(int start, int offset) {
        List list = new ArrayList();
        if (isFirstLoad) {
            try {
                list = (List) searchLogic.loadHierarchyDefinitionResults(binder, start, offset, this.getSortByColumns(), this.getFilters(), hierarchyType,isCheckAll);
            } catch (Exception ex) {
                LOGGER.error(ex);
                
            }
        }
        return list;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        HierarchyDefinitionDTO dto = (HierarchyDefinitionDTO) object;
        ((BeanItemContainer<HierarchyDefinitionDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(final ErrorfulFieldGroup binder, String hierarchyType,boolean isCheckAll) {
        isFirstLoad = true;
        isReset = false;
        this.clearAll();
        this.getFilters().clear();
        this.binder = binder;
        this.hierarchyType = hierarchyType;
        this.isCheckAll=isCheckAll;
        this.setRequiredCount(true);
        this.setCurrentPage(1);
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

    public void setReset(boolean isReset) {
        this.isReset = isReset;
    }
  
}
