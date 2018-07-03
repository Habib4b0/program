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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stpl.app.arm.dataselection.dto.ViewDTO;
import com.stpl.app.arm.dataselection.logic.DataSelectionLogic;
import com.vaadin.v7.data.Container;
import com.vaadin.v7.data.util.BeanItemContainer;

/**
 *
 * @author
 */
public class PrivatePublicViewtableLogic extends PageTableLogic {

    /**
     * The Constant LOGGER.
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(PrivatePublicViewtableLogic.class);

    private DataSelectionLogic logic = new DataSelectionLogic();
    private boolean isGenerate = false;
    private String viewName = StringUtils.EMPTY;
    private String viewType = StringUtils.EMPTY;
    private String screenName = StringUtils.EMPTY;
    private boolean isResultsEmpty;

    public PrivatePublicViewtableLogic() {
        /*
        THE DEFAULT CONSTRUCTOR
         */
    }

    @Override
    public int getCount() {
        try {
            int count = logic.searhViewCount(viewName, viewType, screenName, true, getFilters(), getSortByColumns());
            isResultsEmpty = count == 0;
            return count;
        } catch (Exception ex) {
            LOGGER.error("ERROR IN GETCOUNT :" + ex);
            return 0;
        }
    }

    @Override
    public List loadData(int start, int offset) {
        List<Object[]> list;
        List<ViewDTO> viewList = new ArrayList<>();
        List<String> searchViewList = new ArrayList<>();
        searchViewList.add(0, viewName);
        searchViewList.add(1, viewType);
        searchViewList.add(2, screenName);
        if (isGenerate) {
            list = logic.searhView(searchViewList, false, getFilters(), getSortByColumns(), start, offset);
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
