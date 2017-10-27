/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.arm.dataselection.logic.tablelogic;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.asi.ui.extfilteringtable.paged.logic.PageTableLogic;
import org.jboss.logging.Logger;

import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;

/**
 *
 * @author
 */
public class PrivatePublicViewtableLogic extends PageTableLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = Logger.getLogger(PrivatePublicViewtableLogic.class);

    DataSelectionLogic logic = new DataSelectionLogic();
    boolean isGenerate = false;
    String viewName = StringUtils.EMPTY;
    String viewType = StringUtils.EMPTY;
    String screenName = StringUtils.EMPTY;
    boolean isResultsEmpty;

    @Override
    public int getCount() {
        try {
             int count = logic.searhViewCount(viewName, viewType, screenName, true, getFilters(), getSortByColumns());
            isResultsEmpty = count == 0;
            return count;
        } catch (Exception ex) {
            LOGGER.error("ERROR IN GETCOUNT :"+ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        List<Object[]> list;
        List<ViewDTO> viewList = new ArrayList<>();
        if (isGenerate) {
            list = logic.searhView(viewName, viewType, screenName, false, getFilters(), getSortByColumns(), start, offset);
            viewList = !"Calculation Profile".equals(screenName) ? logic.getCustomizedViews(list) : logic.getCalculationCustomizedViews(list);
        }
        return viewList;
    }

    @Override
    public Object configureContainer(Object object, Container container) {
        ViewDTO dto = (ViewDTO) object;
        ((BeanItemContainer<ViewDTO>) container).addBean(dto);
        return dto;
    }

    public void configureSearchData(boolean isGenerate, String viewName, String viewType, String screenName) {
        this.viewName = viewName;
        this.viewType = viewType;
        this.isGenerate = isGenerate;
        this.screenName = screenName;
        if (!viewName.isEmpty()) {
            this.clearAll();
            this.getFilters().clear();
            this.setRequiredCount(true);
            setCurrentPage(1);
        }
    }

    public boolean isResultsEmpty() {
        return isResultsEmpty;
    }

}
